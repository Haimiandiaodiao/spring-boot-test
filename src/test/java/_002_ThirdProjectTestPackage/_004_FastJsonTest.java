package _002_ThirdProjectTestPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dyy.Modul.Entity.Son;
import org.junit.Test;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

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
    }

    @Test
    public void 测试自定义Seriablizer(){
        JsonTestInsert entity = new JsonTestInsert(11,"dd",new Date());
        String s = JSON.toJSONString(entity);
        System.out.println(s);
        JsonTestInsert jsonTestInsert = JSON.parseObject(s, JsonTestInsert.class);
        System.out.println(jsonTestInsert);
    }
}
