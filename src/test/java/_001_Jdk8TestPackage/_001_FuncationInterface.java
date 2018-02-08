package _001_Jdk8TestPackage;

import com.dyy.Jdk8_Package.Modul.Apple;
import com.dyy.Jdk8_Package.Modul.Letter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * 测试函数式接口
 * @auther Dyy
 * @create 2018/2/1
 */
public class _001_FuncationInterface {

    //1.Predicate   断言 Test方法  接受泛型T返回一个Boolean
    //2.Consumer    消费 accept方法  接受泛型T没有返回值
    //3.Function    操作 apply方法  接受泛型T  R 来进行类型的转化

    /**
     * 1.几类函数式接口 JDK8自带的
     */
    @Test
    public void baseUse1(){
        //理解：就是有一个函数式的接口 这个接口可以通过Lambda传递一个行为过来并且生成这个行为的实例
        //      调用这个实例的方法
        //1.Predicate 断言
        Predicate<String> nonEmpty = (String s) -> !s.isEmpty();
        boolean a = nonEmpty.test("s");
        System.out.println(a);

        Predicate<Integer> nonZero = (Integer s) -> s.compareTo(1) == 0;
        boolean test = nonZero.test(1);
        System.out.println(test);

        //2.Consumer 消费
        Consumer<Object> b = (Object s) -> System.out.println(s.toString() + "进入了Consumer方法");
        b.accept("ss");
        b.accept(1);

        //3.Funcation 操作
        Function<String, Integer> stringConsumer = (String s) -> s.length();
        b.accept(stringConsumer.apply("ABC"));

    }


    //2.来谈一下原始类型的转化
    // 装箱和拆箱会产生系统资源的消耗问题 原始值放在栈中 对象放在堆中这样就无形的浪费了空间
    //【1】 对Predicate的扩展 IntPredicate ,LongPredicate ,DoublePredicate
    //【2】 对Consumer的扩展 IntConsumer ， LongConsumer ，DoubleConsumer
    //【3】 对Function的扩展  大概就是int到任意类型  int到long int到double 这三种类型都有这样的性质


    /**
     * 2.关于原始值的上面三种值的扩展
     */
    @Test
    public void baseUser2(){
        //【1】.关于Predicate的扩展
        //                  1.1 IntPredicate ,LongPredicate ,DoublePredicate
        IntPredicate intPredicate = (int s) -> s == 1;
        LongPredicate longPredicate = (long s) -> s == 1;
        DoublePredicate doublePredicate = (double s) -> s == 1;
        System.out.println("int的扩展"+intPredicate.test(1));
        System.out.println("long的扩展"+longPredicate.test(1));
        System.out.println("double的扩展"+doublePredicate.test(1));


        IntConsumer a = (int i) -> {
            System.out.println(i + "IntConsumer扩展");
        };
        LongConsumer b = (long i) -> {
            System.out.println(i + "LongConsumer扩展");
        };
        DoubleConsumer c = (double i) -> {
            System.out.println(i + "DoubleConsumer扩展");
        };

        a.accept(1);
        b.accept(1);
        c.accept(1);



        IntFunction d = (int s) -> s + "IntFuncation";
        IntToDoubleFunction e = (int s) -> (double) s;
        IntToLongFunction f = (int s) -> (long) s;
        LongFunction h = (long s)-> s+"`";

    }


    /**
     * 3.其他的一些操作
     *  【1】supplier （）-> T  get() 返回一个指定的类型   BooleanSupplier ,IntSupplier ,LongSupplier,DoubleSupplier
     *  【2】unaryOperator T -> T  apply() 单元运算  IntUnaryOperator，LongUnaryOperator ， DoubleUnaryOperator  都没有继承关系
     *  【3】BinaryOperator (T,T) -> T     双元运算  IntBinaryOperator LongBinaryOperator,DoubleBinaryOperator
     *  【4】BiPredicate(L,R) (L,R) -> boolean 判断  没有支持元类型的
     *  【5】BiConsumer<T,U>   (T,U) -> void  消费    ObjIntConsumer ObjLongConsumer ObjDoubleCosumer
     *  【6】BiFunction<T,U,R>  (T,U) -> R    操作    ToIntBiFunction  ToLongBiFunction  ToDoubleBiFunction
     *
     */

    @Test
    public void baseUse3(){
        DoubleSupplier runnable = () -> System.currentTimeMillis();
        System.out.println(runnable.getAsDouble());
        Supplier<String> a = () -> "String";
        System.out.println(a.get());

         UnaryOperator<Integer> c = (Integer s) -> s + 1;
        System.out.println(c.apply(1));

        BinaryOperator<Integer> integerComparator = (Integer s, Integer ss) -> s + ss;
        System.out.println(integerComparator.apply(1,2));
    }

    /**
     * 3.简化，可以从赋值的上下文，方法调用的上下文 参数和返回值  ，以及类型转换的上下文中获得目标类型。
     */
    @Test
    public void  baseUser3(){
        List<String>  a= new ArrayList<>();
        //a = new ArrayList<String>(); 必须是终太的
        Predicate<String> aa = s -> a.add(s);
        System.out.println(aa.test("a"));
    }

    @Test
    public void baseUser4(){
        List<String> str = Arrays.asList("a", "c","b");
        //str.sort((s1,s2)->s1.compareTo(s2));
        //System.out.println(str);

        //==============================简写的形式 等效的方法引用==============
        str.sort(String::compareToIgnoreCase);


        //================================================
        Function<String, Integer> a = (String s) -> Integer.parseInt(s);
        Function<String,Integer> b = Integer::parseInt;

        BiPredicate<List<String>,String> contains = (list,element) -> list.contains(element);
        BiPredicate<List<String>,String> contains1= List::contains;

        //-------------new操作
    }

    /**
     * 对new的操做
     */
    @Test
    public void baseUser5(){
        Supplier<Apple> c1 = Apple::new;
        System.out.println(c1.get());
        //===============等价于下面的方法
        Supplier<Apple> c2 = () -> new Apple();
        System.out.println(c2.get());

    }


    /**
     * 函数的复合
     */
    @Test
    public void baseUser6(){
        Function<Integer,Integer> f = x -> x+1;
        Function<Integer,Integer> g = x -> x*2;
        Function<Integer, Integer> c = f.andThen(g);
        Integer apply = c.apply(1);
        System.out.println(apply);

        Function<Integer, Integer> compose = f.compose(g);
        System.out.println(compose.apply(1));
    }

    /**
     * 函数复合2
     */
    @Test
    public void baseUser7(){
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformation = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
        String dyy = transformation.apply("Dyy");
        System.out.println(dyy);
    }

}
