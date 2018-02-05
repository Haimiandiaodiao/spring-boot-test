package _002_ThirdProjectTestPackage;

import com.dyy.Jdk8_Package.Modul.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Java8对流的支持
 * @auther Dyy
 * @create 2018/2/2
 */
public class _002_StreamApiTest {

    final static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );
    /**
     * 1.
     */
    @Test
    public void baseUse1(){



//        List<String> collect = menu.stream()
//                .filter(d -> d.getCalories() > 300)
//                .map(Dish::getName)
//                .limit(3)
//                .collect(Collectors.toList());
//        System.out.println(collect);

        Predicate<Dish> greate300 = s -> {
            System.out.println("过滤菜单"+ s.getName());
            return s.getCalories() > 500;
        };
        Function<Dish,String> getNameF = s ->{
            System.out.println("获得"+s.getName());
            return s.getName();
        };

        List<String> collect = menu.stream()
                .filter(greate300)
                .map(getNameF)
                .collect(Collectors.toList());
        System.out.println(collect);

    }


    /**
     * 2.终端的操作
     */
    @Test
    public void baseUser2(){
        menu.stream().forEach(System.out::println);
    }

    /**
     * 3.筛选各异的元素
     */
    @Test
    public void baseUser3(){
        List<Integer> number = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        number.stream()
                .limit(5)
                .skip(2)
                .forEach(System.out::println);
    }

    /**
     * 4.Map和flatMap的是测试
     */
    @Test
    public void baseUser4(){
        String[] strings = {"Hello", "World"};
        List<String> collect = Arrays.stream(strings).map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
//        System.out.println(collec);

    }
}
