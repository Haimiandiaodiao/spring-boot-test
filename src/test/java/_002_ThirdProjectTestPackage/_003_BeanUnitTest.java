package _002_ThirdProjectTestPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dyy.Modul.Entity.Father;
import com.dyy.Modul.Entity.Son;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.oracle.deploy.update.Updater;
import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * BeanUtil的使用
 * @auther Dyy
 * @create 2018/1/25
 */
public class _003_BeanUnitTest {

    @Test
    public void ddddddd(){
        HashMap<Integer, Map<String, Object>> shareMap = Maps.newHashMapWithExpectedSize(5);
        HashMap<String, Object> aa = Maps.newHashMap();
        aa.put("name","Dyy");
        aa.put("age",1);

        shareMap.put(1,aa);

        Collection<Map<String, Object>> values = shareMap.values();

        
        System.out.println("");
    }


    @Test
    public void dddd(){
        Map aa= new HashMap();
        aa.put("MsgId","201811150939111");
        aa.put("Channel",0);
        aa.put("MobileNo","15652606177");
        aa.put("Content","验证码23232323");
        aa.put("Zone","86");

        String s = JSON.toJSONString(aa);
        System.out.println(s);
        String s1 = JSON.toJSONString(s);
        System.out.println(s1);
    }


    @Test
    public void contain(){
    
           int i= 0;
           A:if(true){
               System.out.println("come");
             if(i == 0){
                break A;
             }else{
                 System.out.println("BBBB");
             }
            System.out.println("AAA");
           }
        
        System.out.println("s");
        
        LinkedList<Integer> ints = Lists.newLinkedList();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(4);

        System.out.println(ints.contains(1));
        System.out.println(ints.contains(new Integer(1)));
        System.out.println(ints.contains(6));
        System.out.println(ints.contains(Integer.valueOf(6)));


    }



    @Test
    public void 内部类及关系(){

        String json = "{\"integral\":1,\"recommendedNum\":2,\"directlyVipCnt\":4,\"totalVipCnt\":7,\"vipCntExceptDirectly\":3,\"teamManagerCnt\":5,\"totalVipNum\":8,\"heirIntegral\":6,\"takeoutIntegral\":9}";
        UpdateRule.UpdateToMangerRule updateToMangerRule = JSON.parseObject(json, UpdateRule.UpdateToMangerRule.class);
        UpdateRule.UpdateToShopManagerRule updateToMangerRul1e = JSON.parseObject(json, UpdateRule.UpdateToShopManagerRule.class);





        UpdateRule.UpdateToMajorRule a = new UpdateRule.UpdateToMajorRule();


        a.setDirectlyVipCnt(1);

        UpdateRule b = a;
        
        System.out.println("");
        
        
    }

    /**
     * 使用BeanMap将Bean进行赋值
     * @Author:Dyy
     * @Description:
     * @Date: Created in 16:27 2018/11/14
     * @param
     */
    @Test
    public void BeanMapUser(){
        Map<String,String> map = new HashMap<>();
        map.put("xopenid","1");
        map.put("gopenid","2");
        map.put("unionid","3");
        map.put("nickname","4");
        UserVOV2 en= new UserVOV2();
        BeanMap bean = BeanMap.create(en);
        bean.putAll(map);

        System.out.println();
    }

    /**
     * 1.将Map封装成Bean
     */
    @Test
    public void baseUse1() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, Object> faMap = new HashMap();
        Map<String, Object> soMap = new HashMap();

        List<String> soH = new ArrayList<>();
        soH.add("篮球");
        soH.add("足球");
        List<String> foH = new ArrayList<>();
        foH.add("飞机");
        foH.add("坦克");


        soMap.put("name", "小名");
        soMap.put("age", 1);
        soMap.put("hobbies", soH);

        faMap.put("name", "大名");
        faMap.put("age", 50);
        faMap.put("hobbies", foH);
        //faMap.put("son",soMap);
        //=============================核心===========
        Father result = new Father();
        // BeanUtils.populate(result,faMap);

        long l = System.currentTimeMillis();
        Son son = new Son();
        org.apache.commons.beanutils.BeanUtils.populate(son, soMap);
        result.setSon(son);
        org.apache.commons.beanutils.BeanUtils.populate(result, faMap);

        long l1 = System.currentTimeMillis();
        System.out.println("所用的时间--->" + (l1 - l));

        Object son1 = PropertyUtils.getSimpleProperty(result, "son");
        System.out.println(son1);

        System.out.println(result.toString());
    }

    /**
     * 设置属性的值
     */
    @Test
    public void baseUse2() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Son son = new Son();
        Father f = new Father();
        f.setSon(son);
        f.setAge(48);
        f.setName("666");

        son.setName("西欧阿米哥");
        son.setAge(1);
        Map<String, Object> addr = new HashMap();
        addr.put("school", "天津");
        addr.put("home", "北京");
        son.setAddr(addr);
        List<String> strings = Arrays.asList("A", "B", "C");
        son.setHobbies(strings);
        /*PropertyUtils.setIndexedProperty(son,"hobbies",1,"G");
        Object name = PropertyUtils.getIndexedProperty(son, "hobbies",0);
        Object mappedProperty = PropertyUtils.getMappedProperty(son, "addr(home)");

        System.out.println(mappedProperty);

        System.out.println(name);*/

//        Object nestedProperty = PropertyUtils.getNestedProperty(f, "son.addr(school)");
//        Object property = PropertyUtils.getProperty(f, "son.addr(school)");
//        System.out.println(property);

//        Map<String, String> describe = BeanUtils.describe(f);
//        String name = describe.get("name");
//
//        System.out.println(name);

//        DynaBean dynaBean = new LazyDynaBean();
//        dynaBean.set("name","666");
//        dynaBean.set("age",100);
//        dynaBean.set("shool","牛");
//        dynaBean.set("address",1,"A");
//        dynaBean.set("address",2,"B");


        List<Son> listSons = new ArrayList<>();
        Son a = new Son("A", 1, null, null);
        Son b = new Son("A", 2, null, null);
        Son c = new Son("A", 3, null, null);

        listSons.add(a);
        listSons.add(b);
        listSons.add(c);

//        BeanPropertyValueChangeClosure age = new BeanPropertyValueChangeClosure("age", 100);
//        CollectionUtils.forAllDo(listSons,age);
//        System.out.println(listSons);

        BeanPropertyValueEqualsPredicate age = new BeanPropertyValueEqualsPredicate("age", 2);
        CollectionUtils.filter(listSons,age);
        System.out.println(listSons);

    }



        @Test
        public void sixsix(){

                List<SeekGoodsItemVO> list = new ArrayList<SeekGoodsItemVO>();

                SeekGoodsItemVO seekGoodsItemVO34 = new SeekGoodsItemVO();
                seekGoodsItemVO34.setItemId(1);
                seekGoodsItemVO34.setItemName("人群");
                List<SeekGoodsNormVO> norm03 = new ArrayList<SeekGoodsNormVO>();

                SeekGoodsNormVO seekGoodsNormVO33 = new SeekGoodsNormVO();
                seekGoodsNormVO33.setNoreName("少儿");
                seekGoodsNormVO33.setNormId(1);
                norm03.add(seekGoodsNormVO33);

                SeekGoodsNormVO seekGoodsNormVO333 = new SeekGoodsNormVO();
                seekGoodsNormVO333.setNoreName("成年人");
                seekGoodsNormVO333.setNormId(2);
                norm03.add(seekGoodsNormVO333);
                seekGoodsItemVO34.setNorms(norm03);





                SeekGoodsItemVO seekGoodsItemVO1 = new SeekGoodsItemVO();
                seekGoodsItemVO1.setItemId(1);
                seekGoodsItemVO1.setItemName("大小");
                List<SeekGoodsNormVO> norm01 = new ArrayList<SeekGoodsNormVO>();

                SeekGoodsNormVO seekGoodsNormVO1 = new SeekGoodsNormVO();
                seekGoodsNormVO1.setNoreName("大");
                seekGoodsNormVO1.setNormId(1);
                norm01.add(seekGoodsNormVO1);

                SeekGoodsNormVO seekGoodsNormVO2 = new SeekGoodsNormVO();
                seekGoodsNormVO2.setNoreName("中");
                seekGoodsNormVO2.setNormId(2);
                norm01.add(seekGoodsNormVO2);

                SeekGoodsNormVO seekGoodsNormVO3 = new SeekGoodsNormVO();
                seekGoodsNormVO3.setNoreName("小");
                seekGoodsNormVO3.setNormId(3);
                norm01.add(seekGoodsNormVO3);

                SeekGoodsNormVO seekGoodsNormVO4 = new SeekGoodsNormVO();
                seekGoodsNormVO3.setNoreName("中小");
                seekGoodsNormVO3.setNormId(4);
                norm01.add(seekGoodsNormVO4);
                seekGoodsItemVO1.setNorms(norm01);
                list.add(seekGoodsItemVO1);

                SeekGoodsItemVO seekGoodsItemVO2 = new SeekGoodsItemVO();
                List<SeekGoodsNormVO> norm02 = new ArrayList<SeekGoodsNormVO>();
                seekGoodsItemVO2.setItemName("颜色");
                seekGoodsItemVO2.setItemId(2);
                SeekGoodsNormVO seekGoodsNormVO5 = new SeekGoodsNormVO();
                seekGoodsNormVO5.setNormId(5);
                seekGoodsNormVO5.setNoreName("红色");
                norm02.add(seekGoodsNormVO5);
                SeekGoodsNormVO seekGoodsNormVO6 = new SeekGoodsNormVO();
                seekGoodsNormVO6.setNormId(6);
                seekGoodsNormVO6.setNoreName("绿色");
                norm02.add(seekGoodsNormVO6);
                SeekGoodsNormVO seekGoodsNormVO7 = new SeekGoodsNormVO();
                seekGoodsNormVO7.setNormId(7);
                seekGoodsNormVO7.setNoreName("黄色");
                norm02.add(seekGoodsNormVO7);
                SeekGoodsNormVO seekGoodsNormVO8 = new SeekGoodsNormVO();
                seekGoodsNormVO8.setNormId(8);
                seekGoodsNormVO8.setNoreName("黑色");
                norm02.add(seekGoodsNormVO8);
                seekGoodsItemVO2.setNorms(norm02);

                list.add(seekGoodsItemVO2);

                SeekGoodsItemVO seekGoodsItemVO3 = new SeekGoodsItemVO();
                List<SeekGoodsNormVO> normVOS3 = new ArrayList<SeekGoodsNormVO>();
                SeekGoodsNormVO seekGoodsNormV09 = new SeekGoodsNormVO();
                seekGoodsNormV09.setNormId(9);
                seekGoodsNormV09.setNoreName("辣的");
                normVOS3.add(seekGoodsNormV09);
                SeekGoodsNormVO seekGoodsNormV10 = new SeekGoodsNormVO();
                seekGoodsNormV10.setNormId(10);
                seekGoodsNormV10.setNoreName("甜的");
                normVOS3.add(seekGoodsNormV10);
                SeekGoodsNormVO seekGoodsNormV11 = new SeekGoodsNormVO();
                seekGoodsNormV11.setNormId(11);
                seekGoodsNormV11.setNoreName("苦的");
                normVOS3.add(seekGoodsNormV11);
                seekGoodsItemVO3.setItemId(3);
                seekGoodsItemVO3.setItemName("口味");
    //        seekGoodsItemVO3.setSeekGoodsNormVOS(normVOS3);
                seekGoodsItemVO3.setNorms(normVOS3);
                list.add(seekGoodsItemVO3);

                System.out.println(list);

                //拿到每一个item下的所有norm
                List<SeekGoodsItemVO> items = new ArrayList();
                items.add(seekGoodsItemVO1);
                items.add(seekGoodsItemVO2);
                items.add(seekGoodsItemVO3);
                items.add(seekGoodsItemVO34);

                //拿到第一层的作为基础项
                //拼接项从第二个数据开始
                int joinStart = 1;
                //每次要拼接的次数
                int joinCount = items.size()-1;
                //创建基础的拼接器
                SeekGoodsItemVO base = items.get(0);
                List<SeekGoodsNormVO> norms = base.getNorms();
                List<List<SeekGoodsNormVO>> sbs = new ArrayList<>();
                for (SeekGoodsNormVO norm : norms) {
                    if(norm != null) {
                        //基础的数据结构应该是[[大],[小],[中]]
                        List<SeekGoodsNormVO> baseNorm = new ArrayList<>();
                        baseNorm.add(norm);
                        sbs.add(baseNorm);
                    }
                }

            //中间组合临时集合
            List<List<SeekGoodsNormVO>> minnList = new ArrayList<>();

            for (int i = 0; i < joinCount; i++) {
                    SeekGoodsItemVO seekGoodsItemVO = items.get(joinStart);
                    List<SeekGoodsNormVO> norms1 = seekGoodsItemVO.getNorms();
                    //sbs是上一轮拼接之后的list数组
                    for (List<SeekGoodsNormVO> basetmp : sbs) {
                        for (SeekGoodsNormVO seekGoodsNormVO : norms1) {
                            //如果下个元素不存在不进行添加
                            if(seekGoodsNormVO != null) {
                                List<SeekGoodsNormVO> baseNorm = new ArrayList<>();
                                //加入基础数据的所有元素
                                baseNorm.addAll(basetmp);
                                //添加新的sku项
                                baseNorm.add(seekGoodsNormVO);
                                //讲拼接之后的组合放入新的中间集合中
                                minnList.add(baseNorm);
                            }
                        }
                    }
                    joinStart++;
                    //将中间临时集合变为下一次迭代中的基础集合
                    sbs = minnList;
                    //将临时变量复位
                    minnList = new ArrayList<>();
                }
                System.out.println(sbs);
                String s = JSON.toJSONStringWithDateFormat(sbs,"yyyy-MM-dd", SerializerFeature.DisableCircularReferenceDetect);
                System.out.println(s);
        }


}
