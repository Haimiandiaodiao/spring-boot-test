package _002_ThirdProjectTestPackage;

import com.alibaba.fastjson.JSON;
import com.dyy.Modul.Entity.Son;
import org.junit.Test;

import java.util.ArrayList;
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

    }
}
