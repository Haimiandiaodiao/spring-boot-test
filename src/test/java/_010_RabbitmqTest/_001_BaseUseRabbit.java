package _010_RabbitmqTest;

import com.rabbitmq.client.*;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.channels.SelectableChannel;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.regex.Pattern;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/5/22 22:26<br/>
 */
public class _001_BaseUseRabbit {

    @Test
    public void baseUse20() throws Exception {
        KeyStore jks = KeyStore.getInstance("JKS");
        jks.load(new FileInputStream("/Users/dyy/Documents/MyZone/aKey.keystore"),"123456".toCharArray());
        Enumeration<String> aliases = jks.aliases();
        while (aliases.hasMoreElements()){
            System.out.println(aliases.nextElement());
        }

        Certificate srykey = jks.getCertificate("srykey");
        System.out.println(srykey.getPublicKey());
        System.out.println(srykey.getType());
    }


    @Test
    public void baseUse19(){
        BigDecimal a = new BigDecimal("10");
        BigDecimal b = new BigDecimal("30");
        BigDecimal[] bigDecimals = a.divideAndRemainder(b);
        System.out.println(bigDecimals[0]);
        System.out.println(bigDecimals[1]);
        assert false;

    }


    @Test
    public void baseUseGetQos(){
        String regex = "^(?:(?:2[0-4][0-9]\\.)|(?:25[0-5]\\.)|(?:1[0-9][0-9]\\.)|(?:[1-9][0-9]\\.)|(?:[0-9]\\.)){3}" +
                "(?:(?:2[0-4][0-9])|(?:25[0-5])|(?:1[0-9][0-9])|(?:[1-9][0-9])|(?:[0-9]))$";
        String rexid = "10.100.120.249";
        boolean matches = rexid.matches(regex);
        System.out.println(matches);

        String regex1 = "(?:2[0-5][2])";
        Pattern compile = Pattern.compile(regex1);
        String stt= "232";
        System.out.println(compile.matcher(stt).matches());

        String s = String.valueOf(12);
        System.out.println(s);
    }


    @Test
    public void baseUseGetACK() throws Exception{
        String exchange = "exchange_demo";
        String routing = "routing_demo";
        String queue = "passenger_queue_5";
        String ip= "47.104.206.145";
        int port = 5672;
        //和生产者不同的连接方式
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("rccl123321");
        factory.setHost(ip);
        factory.setPort(port);
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        GetResponse getResponse = channel.basicGet(queue, false);//不自动应答的
        System.out.println(new String(getResponse.getBody()));
        Thread.sleep(5000);
        channel.basicRecover(true);
//        channel.basicAck(getResponse.getEnvelope().getDeliveryTag(),true);
        while (true){
            Thread.sleep(10000);
        }

    }


    @Test
    public void baseUseCustome() throws Exception{
        //队列的前缀
         final  String PASSENGER_QUEUE_KEY_PREFIX = "passenger_queue_";
         final  String NORMA_QUEUE_KEY_PREFIX = "normal_queue_";
         final  String TAXI_QUEUE_KEY_PREFIX = "taxi_queue_";
        
        
        
        String passengerId ="5";//乘客的id
        String queue = PASSENGER_QUEUE_KEY_PREFIX+passengerId;
        String ip= "47.104.206.145";
        int port = 5672;
        //和生产者不同的连接方式
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("rccl123321");
        factory.setHost(ip);
        factory.setPort(port);
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        channel.basicQos(10);//可以处理10条没有相应的信息

        //防止队列不存在创建队列  例如创建乘客id为2的队列
        channel.queueDeclare(queue,true,false,false,null);

        Consumer defaultConsumer = new DefaultConsumer(channel) {
            private  int i = 0;
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    System.out.println("接收到的信息=====>：" + new String(body));
                    //模拟业务逻辑
                    Thread.sleep(200);
                    System.out.println("=====>i=="+i);
                    if(++i%2 == 0){
                        throw new RuntimeException("==》有异常了==>"+i+"===>消息编号==>"+envelope.getDeliveryTag());
                    }
                    channel.basicAck(envelope.getDeliveryTag(),false);//false只应到本次的消息
                }catch (Exception e){//具体的异常来判断是否响应应答
                    //异常来拒绝应答
                    System.out.println("查看信息====>"+e.getMessage());
                    channel.basicReject(envelope.getDeliveryTag(),true);//将消息加入到队列中
                }
            }
        };
        
        //监听这个队列
        channel.basicConsume(queue,false,defaultConsumer);//设置需要手动应答的操作

        while (true){
            Thread.sleep(50000);
        }
    }


    /**
     * 1.对RabbitMq的基本使用 生产者的基本使用
     */
    @Test
    public void baseUse() throws Exception {
        String exchange = "exchange_demo";
        String routing = "routing_demo";
        String queue = "queue_demo";
        String ip= "192.168.25.180";
        int port = 5672;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(ip);
        factory.setPort(port);
        factory.setUsername("admin");
        factory.setPassword("123456");
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //创建一个type ="direct" 持久化的，非自动删除的交换器
        channel.exchangeDeclare(exchange,"direct",true,false,null);
        //创建一个持久化，非排他的，非自动删除的队列
        channel.queueDeclare(queue,true,false,false,null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(queue,exchange,routing);

        //发送一条持久化的消息：hello world
        channel.basicPublish(exchange,routing,MessageProperties.TEXT_PLAIN,"Hello Dyy".getBytes());
        //发送一条带有过期时间的消息
        channel.basicPublish(exchange,routing,
                new AMQP.BasicProperties.Builder()
        .expiration("60000").build(),"Dyy".getBytes());
        //关闭资源
        channel.close();
        connection.close();
    }

    /**
     * 2.消费端的使用
     *  不推荐使用QueueingConsumer了
     */
    @Test
    public void baseUse2() throws Exception {


        //交换器名字
        String exchange = "orderexchange";
        //路由器的名字
        String routing = "vie.order";
        //队列的名字
        String queue = "vie_order";
        String ip= "47.104.206.145";
        int port = 5672;
        //和生产者不同的连接方式
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("rccl123321");
        Address[] addresses = {new Address(ip, port)};
        factory.setAutomaticRecoveryEnabled(true);
        //创建连接
        Connection connection = factory.newConnection(addresses);
        Channel channel = connection.createChannel();
        channel.basicQos(1);//设置客户端最多接受未被ack的消息的个数？  一次只接受一个未被应答的消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接收到的信息："+new String(body));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Tage-->:"+envelope.getDeliveryTag());
//                channel.basicAck(envelope.getDeliveryTag(),false);
                //设置拒接
                channel.basicReject(envelope.getDeliveryTag(),true);
            }
        };


        channel.basicConsume(queue,false,consumer);//设置需要手动应答的操作
        //等待毁掉幻术执行完毕之后，关闭资源
        TimeUnit.SECONDS.sleep(5);
        while (true){

        }
//        channel.close();
//        connection.close();
    }


    /**
     * 3.设置mandatory的参数
     * 当mandatory设置为true的时候找不到指定的queue的时候回返回Basic.Return
     * 但mandatory设置为false的时找不到指定的队列的时候回直接丢弃
     */
    @Test
    public void baseUse3() throws IOException, TimeoutException {
        String exchange = "exchange_demo";
        String routing = "routing_demo";
        String queue = "queue_demo";
        String ip= "192.168.25.180";
        int port = 5672;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setHost(ip);
        factory.setPort(port);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicPublish(exchange,"aa",false,MessageProperties.TEXT_PLAIN,"DyyTestmandatory".getBytes());

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyTest, String exchange, String routingKey, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                System.out.println("Basic.return返回的结果是:"+new String(bytes));
            }
        });

        connection.close();
    }

    /**
     * 4.备份交换机的使用
     */
    @Test
    public void baseUse4() throws IOException, TimeoutException {
        String exchange = "exchange_demo";
        String routing = "routing_demo";
        String queue = "queue_demo";
        String ip= "192.168.25.180";
        int port = 5672;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setHost(ip);
        factory.setPort(port);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        Map<String,Object> args = new HashMap<>();
        args.put("alternate-exchange","myAe");

        channel.exchangeDeclare("normalExchange","direct",true,false,args);
        channel.exchangeDeclare("myAe","fanout",true,false,null);
        channel.queueDeclare("normalQueue",true,false,false,null);
        channel.queueDeclare("unroutedQueue",true,false,false,null);

        channel.queueBind("normalQueue","normalExchange","normalKey");
        channel.queueBind("unroutedQueue","myAe","");

        //channel.basicPublish("normalExchange","normalKey",false,MessageProperties.TEXT_PLAIN,"DyyTestmandatory".getBytes());
        //channel.basicPublish("normalExchange","dd",false,MessageProperties.TEXT_PLAIN,"测试不存在".getBytes());
        //设置消息的生存时间
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        builder.deliveryMode(2);//持久化消息
        builder.expiration("60000");//设置时间时1分钟
        AMQP.BasicProperties properties = builder.build();
        channel.basicPublish("normalExchange","dd",false,properties,"ttlMessage".getBytes());

        connection.close();
    }

    /**
     * 6.死信队列的配置
     */
    @Test
    public void baseUse6() throws IOException, TimeoutException {
        String exchange = "exchange_demo";
        String routing = "routing_demo";
        String queue = "queue_demo";
        String ip= "192.168.25.180";
        int port = 5672;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("admin");
        factory.setPassword("123456");
        factory.setHost(ip);
        factory.setPort(port);

        Connection connection = factory.newConnection();
        //设置死信路由器和死信路由键
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("exchange.dlx","direct",true);
        channel.exchangeDeclare("exchange.normal","direct",true);
//
        Map<String,Object> args = new HashMap<>();
        args.put("x-message-ttl",60000);
        args.put("x-dead-letter-exchange","exchange.dlx");
        args.put("x-dead-letter-routing-key","routingkey");
        channel.queueDeclare("queue.normal",true,false,false,args);
        channel.queueDeclare("queue.dlx",true,false,false,null);

        channel.queueBind("queue.normal","exchange.normal","kk");
        channel.queueBind("queue.dlx","exchange.dlx","routingkey");


        //设置消息的生存时间
        AMQP.BasicProperties.Builder builder1 = new AMQP.BasicProperties.Builder();
        builder1.deliveryMode(2);//持久化
        builder1.expiration("60000");
        builder1.contentType("text/plain");
        AMQP.BasicProperties build = builder1.build();

        channel.basicPublish("exchange.normal","kk",build,"测试".getBytes());

        connection.close();
    }


    /**
     * 12.使用rabbitmq-spring的连接池配置
     */
    @Test
    public void baseUse12(){
        //1.配置缓冲池连接工厂
        CachingConnectionFactory cachingConnect = new CachingConnectionFactory();
        cachingConnect.setUsername("admin");
        cachingConnect.setPassword("rccl123321");
        cachingConnect.setHost("47.104.206.145");
        cachingConnect.setPort(5672);
        cachingConnect.setConnectionCacheSize(5);
        cachingConnect.setChannelCacheSize(50);
        cachingConnect.setVirtualHost("/");//设置虚拟机


        //发布确认的相关配置
        cachingConnect.setPublisherReturns(true);
        //配置重试与熔断
        //对于每一个RabbitTemplate只支持一个ReturnCallback
        //=======================【1】操作的交换器，队列，和消息==========
        org.springframework.amqp.core.MessageProperties messageProperties = new org.springframework.amqp.core.MessageProperties();
        messageProperties.setContentType("application/json");
        messageProperties.setMessageId("Dyy_message1");
        messageProperties.setTimestamp(new Date());
        //可以自定义标头进行扩展
//      messageProperties.setHeader();
        Message message = new Message("Dyy".getBytes(), messageProperties);

        //(2)交换器  持久化的  但是不是自动删除的  其中还有Topic的 Header的 和Fanout的
        DirectExchange directExchange = new DirectExchange("直接转的发器",true,false);

        //（3）队列  持久化的 ，排他的 ，自动删除的
        Queue queue = new Queue("Dyy_的队列", true, true, false);

        //============================【1】===========================
        //============================【2】绑定 交换器可以绑定交换器的=======================
        Binding with = BindingBuilder.bind(queue).to(directExchange).with("Gen_Order");
        //应为有备用交换机的东西所以交换器可以绑定交换器
//        Binding binding = new Binding("Dyy_的队列", Binding.DestinationType.QUEUE, "直接转的发器", "foo.*", null);


        //===========================================================


        //1.设置熔断策略
        ExponentialBackOffPolicy policy = new ExponentialBackOffPolicy();
        policy.setInitialInterval(500);//设置重试间隔
        policy.setMultiplier(10.0);//设置递增级数
        policy.setMaxInterval(10000);//设置最大重试的时间

        //2.设置熔断的模板
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setBackOffPolicy(policy);

        //3.给模板设置熔断的模板
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(cachingConnect);
        rabbitTemplate.setRetryTemplate(retryTemplate);
        rabbitTemplate.setMandatory(true);


        //控制
        RabbitAdmin rabbitAdmin = new RabbitAdmin(cachingConnect);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareExchange(directExchange);
        rabbitAdmin.declareBinding(with);

        rabbitTemplate.convertAndSend("直接转的发器","Gen_Order","我抢单了");

    }


    /**
     * 10.使用RabbtTemplate
     */
    @Test
    public void baseUse10(){
        //1.配置缓冲池连接工厂
        CachingConnectionFactory cachingConnect = new CachingConnectionFactory();
        cachingConnect.setUsername("admin");
        cachingConnect.setPassword("123456");
        cachingConnect.setHost("192.168.25.180");
        cachingConnect.setPort(5672);
        cachingConnect.setConnectionCacheSize(5);
        cachingConnect.setChannelCacheSize(50);
        cachingConnect.setVirtualHost("/");//设置虚拟机

        RabbitAdmin rabbitAdmin = new RabbitAdmin(cachingConnect);
        Queue queue = new Queue("模板生成的Queue");
        DirectExchange exchange = new DirectExchange("模板生成对的Exchange");
        Binding binding = new Binding("模板生成的Queue", Binding.DestinationType.QUEUE, "模板生成对的Exchange", "Dyy_routint_key", null);

        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);

        //3.给模板设置熔断的模板
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(cachingConnect);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.convertAndSend("模板生成对的Exchange","Dyy_routint_key","Dyy的信息");

    }


    /**
     * 11.使用RabbtTemplate
     */
    @Test
    public void baseUse11(){
        //1.配置缓冲池连接工厂
        CachingConnectionFactory cachingConnect = new CachingConnectionFactory();
        cachingConnect.setUsername("admin");
        cachingConnect.setPassword("123456");
        cachingConnect.setHost("192.168.25.180");
        cachingConnect.setPort(5672);
        cachingConnect.setConnectionCacheSize(5);
        cachingConnect.setChannelCacheSize(50);
        cachingConnect.setVirtualHost("/");//设置虚拟机

        RabbitAdmin rabbitAdmin = new RabbitAdmin(cachingConnect);
        Queue queue = new Queue("1模板生成的Queue");
        DirectExchange exchange = new DirectExchange("1模板生成对的Exchange");
        Binding binding = new Binding("1模板生成的Queue", Binding.DestinationType.QUEUE, "1模板生成对的Exchange", "1Dyy_routint_key", null);

        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(binding);

        //3.给模板设置熔断的模板
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(cachingConnect);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.convertAndSend("1模板生成对的Exchange","1Dyy_routint_key","1Dyy的信息");

    }



    /**
     * 13.使用RabbtTemplate进行消息的接受
     */
    @Test
    public void baseUse13(){
        //1.配置缓冲池连接工厂
        CachingConnectionFactory cachingConnect = new CachingConnectionFactory();
        cachingConnect.setUsername("admin");
        cachingConnect.setPassword("123456");
        cachingConnect.setHost("192.168.25.180");
        cachingConnect.setPort(5672);
        cachingConnect.setConnectionCacheSize(5);
        cachingConnect.setChannelCacheSize(50);
        cachingConnect.setVirtualHost("/");//设置虚拟机

        //3.给模板设置熔断的模板
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(cachingConnect);

        Message receive = rabbitTemplate.receive("模板生成的Queue");
        System.out.println(new String(receive.getBody()));

    }


    /**
     * 14.使用MessageListener来异步处理消息
     */
    @Test
    public void baseUse14(){
        //1.配置缓冲池连接工厂
        CachingConnectionFactory cachingConnect = new CachingConnectionFactory();
        cachingConnect.setUsername("admin");
        cachingConnect.setPassword("123456");
        cachingConnect.setHost("192.168.25.180");
        cachingConnect.setPort(5672);
        cachingConnect.setConnectionCacheSize(5);
        cachingConnect.setChannelCacheSize(50);
        cachingConnect.setVirtualHost("/");//设置虚拟机

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(cachingConnect);
        container.setQueueNames("1模板生成的Queue","模板生成的Queue");
        //设置监听程序
        MessageListener messageListener = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                System.out.println("接受到的信息：" + new String(message.getBody()));
            }
        };

        container.setMessageListener(messageListener);
        container.start();
        while (true);
    }


    //单线程的同送消息
    @Test
    public void baseUse15() throws Exception{
        String exchange = "exchange_demo";
        String routing = "routing_demo";
        String queue = "queue_demo";
        String ip= "47.104.206.145";
        int port = 5672;
        //
        System.out.println("===>"+"开始"+"<=====");

        ConnectionFactory factory = new ConnectionFactory();//

        factory.setPort(port);
        factory.setHost(ip);
        factory.setPassword("rccl123321");
        factory.setUsername("admin");
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //创建一个type ="direct" 持久化的，非自动删除的交换器
        channel.exchangeDeclare(exchange,"topic",true,false,null);
        //创建一个持久化，非排他的，非自动删除的队列
        channel.queueDeclare(queue,true,false,false,null);
        //将交换器与队列通过路由键绑定
        channel.queueBind(queue,exchange,routing);

        for (int i = 0; i < 1000; i++) {
            //发送一条持久化的消息：hello world
            Thread.sleep(1000);
            System.out.println("我是生产者我放入了第"+i+"条记录");
            channel.basicPublish(exchange,routing,MessageProperties.TEXT_PLAIN,("这是我的第："+i+"<==条消息").getBytes());
        }

        //关闭资源
        channel.close();
        connection.close();
    }


    //多线程的带自动的应答的消费模式
    @Test
    public void baseUse16() throws Exception{
        //队列的前缀
        String exchange = "exchange_demo";
        String routing = "routing_demo";
        String queue = "queue_demo";
//

        String ip= "47.104.206.145";
        int port = 5672;
        //和生产者不同的连接方式
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPassword("rccl123321");
        factory.setUsername("admin");
        factory.setHost(ip);
        factory.setPort(port);
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        channel.basicQos(1);//可以处理10条没有相应的信息

        //防止队列不存在创建队列  例如创建乘客id为2的队列
        channel.queueDeclare(queue,true,false,false,null);

        Consumer defaultConsumer = new DefaultConsumer(channel) {
            private  int i = 0;
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    //模拟业务逻辑
                    System.out.println("接收到的信息=====>：" + new String(body));
                    Thread.sleep(500);
                    System.out.println("=====>i=="+i);
//                    if(++i%2 == 0){
//                        throw new RuntimeException("==》有异常了==>"+i+"===>消息编号==>"+envelope.getDeliveryTag());
//                    }
                    channel.basicAck(envelope.getDeliveryTag(),false);//false只应到本次的消息
                }catch (Exception e){//具体的异常来判断是否响应应答
                    //异常来拒绝应答
                    System.out.println("查看信息====>"+e.getMessage());
                    channel.basicReject(envelope.getDeliveryTag(),true);//将消息加入到队列中
                }
            }
        };

        //监听这个队列
        channel.basicConsume(queue,false,defaultConsumer);//设置需要手动应答的操作

        while (true){
            Thread.sleep(50000);
        }
    }



    @Test
    public void baseUse17() throws Exception{
        //队列的前缀
        String exchange = "exchange_demo";
        String routing = "routing_demo";
        String queue = "queue_demo";
//

        String ip= "47.104.206.145";
        int port = 5672;
        //和生产者不同的连接方式
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPassword("rccl123321");
        factory.setUsername("admin");
        factory.setHost(ip);
        factory.setPort(port);
        //创建连接
        Connection connection = factory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        channel.basicQos(1);//可以处理10条没有相应的信息

        //防止队列不存在创建队列  例如创建乘客id为2的队列
        channel.queueDeclare(queue,true,false,false,null);

        Consumer defaultConsumer = new DefaultConsumer(channel) {
            private  int i = 0;
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    //模拟业务逻辑
                    System.out.println("接收到的信息=====>：" + new String(body));
                    Thread.sleep(500);
                    System.out.println("=====>i=="+i);
//                    if(++i%2 == 0){
//                        throw new RuntimeException("==》有异常了==>"+i+"===>消息编号==>"+envelope.getDeliveryTag());
//                    }
                    channel.basicAck(envelope.getDeliveryTag(),false);//false只应到本次的消息
                }catch (Exception e){//具体的异常来判断是否响应应答
                    //异常来拒绝应答
                    System.out.println("查看信息====>"+e.getMessage());
                    channel.basicReject(envelope.getDeliveryTag(),true);//将消息加入到队列中
                }
            }
        };

        //监听这个队列
        channel.basicConsume(queue,false,defaultConsumer);//设置需要手动应答的操作

        while (true){
            Thread.sleep(50000);
        }
    }


    //创建线程池并且进行接受消息
    @Test
    public void  baseUse7(){
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 8,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        Runnable[] tasks = new Runnable[1000];
        for (int i = 0; i < 1000; i++) {
            String name = "第=>"+i+"<=任务";
            tasks[i]=new baseUser(name);
        }


        for (int i = 0; i < 1000; i++) {
            pool.execute(tasks[i]);
        }
        while (true);
    }
}

//连接并且Rabbitmq被动消费
class baseUser implements Runnable{
    private String runnbaleName;

    public baseUser(String runnbaleName) {
        this.runnbaleName = runnbaleName;
    }

    @Override
    public void run() {
        try{
            String queue = "queue_demo";
//

            String ip= "47.104.206.145";
            int port = 5672;
            //和生产者不同的连接方式
            ConnectionFactory factory = new ConnectionFactory();
            factory.setPassword("rccl123321");
            factory.setUsername("admin");
            factory.setHost(ip);
            factory.setPort(port);
            //创建连接
            Connection connection = factory.newConnection();
            //创建信道
            Channel channel = connection.createChannel();
            channel.basicQos(1);//可以处理10条没有相应的信息

            //防止队列不存在创建队列  例如创建乘客id为2的队列
            channel.queueDeclare(queue,true,false,false,null);

            Consumer defaultConsumer = new DefaultConsumer(channel) {
                private  int i = 0;
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    try {
                        //模拟业务逻辑
                        System.out.println(runnbaleName+"==>接收到的信息=====>：" + new String(body));
                        Thread.sleep(500);
                        channel.basicAck(envelope.getDeliveryTag(),false);//false只应到本次的消息
                    }catch (Exception e){//具体的异常来判断是否响应应答
                        //异常来拒绝应答
                        System.out.println("查看信息====>"+e.getMessage());
                        channel.basicReject(envelope.getDeliveryTag(),true);//将消息加入到队列中
                    }
                }
            };

            //监听这个队列
            channel.basicConsume(queue,false,defaultConsumer);//设置需要手动应答的操作


        }catch (Exception e){
            System.out.println(e);
        }
    }


    @Test
    public void baseUse111(){

    }

}