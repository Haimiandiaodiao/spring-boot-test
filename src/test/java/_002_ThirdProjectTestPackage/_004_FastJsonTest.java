package _002_ThirdProjectTestPackage;

import _000_day._001_7月17日反射.Student;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dyy.Modul.Entity.Father;
import com.dyy.Modul.Entity.Son;
import io.swagger.models.auth.In;
import net.sf.cglib.beans.BeanCopier;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import springfox.documentation.spring.web.json.Json;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @auther Dyy
 * @create 2018/1/26
 */
public class _004_FastJsonTest {

    @Test
    public void baseUser1(){
        String name1= "WD";
        String name = "Dyy";
        Integer age = 11;
        List<String> hobbies= new ArrayList<>();
        hobbies.add("篮球");
        hobbies.add("足球");
        hobbies.add("排球");

        Son son = new Son(name, age, hobbies,null);

        //SimplePropertyExcludPreFilter filter =  new SimplePropertyExcludPreFilter();
       // filter.setExcludesProperties("name");
        String s = JSON.toJSONString(son);

        System.out.println(s);
    }

    @Test
    public void baseUser2(){
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        map.put("validUser",null);
        map.put("personConsume",null);
        map.put("teamConsume",null);
        map.put("updateTo",2);
        map.put("canUse",0);

        String s = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);

        LinkedHashMap linkedHashMap = JSON.parseObject(s, map.getClass());

        System.out.println(s);

        Father student = new Father();
        String s1 = JSON.toJSONString(student,SerializerFeature.WriteMapNullValue);
        System.out.println(s1);

    }

    @Test
    public void 测试自定义Seriablizer(){
        JsonTestInsert entity = new JsonTestInsert(11,"dd",new Date(),20929008912961536L);
        String s = JSON.toJSONString(entity);
        System.out.println(s);
        JsonTestInsert jsonTestInsert = JSON.parseObject(s, JsonTestInsert.class);
        System.out.println(jsonTestInsert);

        String base = "{\"age\":11,\"create\":1539842505349,\"nama\":\"dd\"}";
        JsonTestInsert a11 = JSON.parseObject(base, JsonTestInsert.class);

        System.out.println(a11);
    }

    @Test
    public void 测试JsonObject(){
        JSONArray a1 = new JSONArray();
        JSONObject a2 = new JSONObject();
        JSONObject a3 = new JSONObject();

        a2.put("name","Dyy");
        a2.put("id",100000000000000001L);
        a2.put("age",10);
        a2.put("like",null);

        a3.put("name","Dyy2");
        a3.put("id",100000000000000002L);
        a3.put("age",12);
        a3.put("like",null);

        a1.add(a2);
        a1.add(a3);
        String s = a1.toJSONString();
        System.out.println(s);
    }


    @Test
    public void testtet(){
        Map<String,String> a= new HashMap();
        Map<String,String> b= new HashMap();

        a.put("1","a");
        a.put("2","b");

        for (Map.Entry<String, String> aa : a.entrySet()) {
            String key = aa.getKey();
            String value = aa.getValue();
            b.put(key,value);
        }

        a.remove("1");

        System.out.println(a);
        System.out.println(b);
    }

    @Test
    public void addSkuNorm(){
        SkuNormVO a = new SkuNormVO();
        Set b = new HashSet();

        b.add("小");
        b.add("大");

        a.setSysGoodsId(21971857511157760L);
        //重量
        a.setItemId(21972058216992768L);
        a.setNormList(b);



        SkuNormVO c = new SkuNormVO();
        Set d = new HashSet();

        d.add("小甜甜");
        d.add("小酸酸");

        c.setSysGoodsId(21971857511157760L);
        //重量
        c.setItemId(21972058321850368L);
        c.setNormList(d);

        ArrayList ddd= new ArrayList();
        ddd.add(a);
        ddd.add(c);

        String s = JSON.toJSONString(ddd);
        System.out.println(s);
    }


    @Test
    public void 测试内部类的Json化(){
        ImportSkuVO importSkuVO = new ImportSkuVO();
        importSkuVO.setGoodsId("商品的Id是那个短的Id");

        List<ImportSkuVO.ImportSkuNormListItem> normLIst = new ArrayList<>();
        ImportSkuVO.ImportSkuNormListItem importSkuNormListItem1 = importSkuVO.new ImportSkuNormListItem();
        importSkuNormListItem1.setName("颜色");
        List<String> normList1 = new ArrayList<>();
        normList1.add("红");
        normList1.add("黄");
        normList1.add("蓝");
        importSkuNormListItem1.setNorms(normList1);

        ImportSkuVO.ImportSkuNormListItem importSkuNormListItem2 = importSkuVO.new ImportSkuNormListItem();
        importSkuNormListItem2.setName("大小");
        List<String> normList2 = new ArrayList<>();
        normList2.add("X");
        normList2.add("XXL");
        normList2.add("SM");
        importSkuNormListItem2.setNorms(normList2);
        normLIst.add(importSkuNormListItem1);
        normLIst.add(importSkuNormListItem2);




        ImportSkuVO.ImporSkuSkuListItem imporSkuSkuListItem1 = importSkuVO.new ImporSkuSkuListItem();
        ImportSkuVO.ImporSkuSkuListItem imporSkuSkuListItem2 = importSkuVO.new ImporSkuSkuListItem();

        List list1=new ArrayList();
        List list3=new ArrayList();

        list1.add("红");
        list1.add("X");

        list3.add("黄");
        list3.add("XXL");
        imporSkuSkuListItem1.setSkuName(list1);
        imporSkuSkuListItem2.setSkuName(list3);
        imporSkuSkuListItem1.setGoodsId("121331313131");
        imporSkuSkuListItem1.setPrice(new BigDecimal("1.11"));
        imporSkuSkuListItem1.setWeight(new BigDecimal("100"));
        imporSkuSkuListItem1.setBarCode("10101010");
        imporSkuSkuListItem1.setDataStatus(0);
        imporSkuSkuListItem1.setDefaultPhotoId("1111111111");
        imporSkuSkuListItem1.setMallPrice(new BigDecimal("100"));
        imporSkuSkuListItem1.setNetWeight(new BigDecimal("200"));
        imporSkuSkuListItem1.setPlatSkuId("1111111");
        imporSkuSkuListItem1.setProfit(new BigDecimal(10));
        imporSkuSkuListItem1.setStock(new BigDecimal("3333"));


        imporSkuSkuListItem2.setGoodsId("22222222");
        imporSkuSkuListItem2.setPrice(new BigDecimal("2.22"));
        imporSkuSkuListItem2.setWeight(new BigDecimal("200"));
        imporSkuSkuListItem2.setBarCode("22222");
        imporSkuSkuListItem2.setDataStatus(0);
        imporSkuSkuListItem2.setDefaultPhotoId("22222");
        imporSkuSkuListItem2.setMallPrice(new BigDecimal("222"));
        imporSkuSkuListItem2.setNetWeight(new BigDecimal("222"));
        imporSkuSkuListItem2.setPlatSkuId("22222");
        imporSkuSkuListItem2.setProfit(new BigDecimal(20));
        imporSkuSkuListItem2.setStock(new BigDecimal("444"));

        List<ImportSkuVO.ImporSkuSkuListItem>  skuList = new ArrayList<>();
        skuList.add(imporSkuSkuListItem1);
        skuList.add(imporSkuSkuListItem2);

        importSkuVO.setNormList(normLIst);
        importSkuVO.setSkuList(skuList);
        String s = JSONObject.toJSONString(importSkuVO, SerializerFeature.WriteMapNullValue);
        System.out.println(s);
    }


    @Test
    public void show (){
        PromotionVO promotionVO = new PromotionVO();
        promotionVO.setPrName("Dyy接口测试活动1");
        LocalDateTime of = LocalDateTime.of(2018, 10, 10, 10, 10, 10);
        long l = of.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Date prStart = new Date(l);

        LocalDateTime of1 = LocalDateTime.of(2018, 11, 11, 11, 11, 11);
        long l1 = of1.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        Date prEnd = new Date(l1);
        promotionVO.setPrStart(prStart);
        promotionVO.setPrEnd(prEnd);
        promotionVO.setPrType(1);

        promotionVO.setDataStatus(0);
        promotionVO.setPrGroupnum(10);
        promotionVO.setScoreType("AA");
        promotionVO.setScoreTime(new BigDecimal("1.11"));
        promotionVO.setImgUrl("www.baidu.com1");
        promotionVO.setBannerImgUrl("www.baidu.com2");

        List<PromotionVO.PromotionGoodsVO> goods = new ArrayList<>();
        PromotionVO.PromotionGoodsVO good1 = new PromotionVO.PromotionGoodsVO();
        good1.setSkuId(256);
        good1.setGoodsNum(333);
        good1.setSort(1);
        good1.setGoPrPrice(new BigDecimal("1.11"));
        good1.setGoLimit(10L);
        good1.setSalenumMode(1);
        good1.setGoStock(99L);


        PromotionVO.PromotionGoodsVO good2 = new PromotionVO.PromotionGoodsVO();
        good2.setSkuId(256);
        good2.setGoodsNum(333);
        good2.setSort(1);
        good2.setGoPrPrice(new BigDecimal("2.22"));
        good2.setGoLimit(10L);
        good2.setSalenumMode(1);
        good2.setGoStock(99L);

        goods.add(good1);
        goods.add(good2);
        promotionVO.setPromotionGoodsList(goods);

        String s = JSON.toJSONString(promotionVO);
        System.out.println(s);

    }
}
