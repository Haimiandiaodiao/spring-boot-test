package _001_Jdk8TestPackage;

import com.dyy.Jdk8_Package.Modul.Dish;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    /**
     * 5.创建使用
     */
    @Test
    public void baseUse5(){
        List<Integer> a = Arrays.asList(1, 2, 3);
        List<Integer> b = Arrays.asList(4,5);

        List<int[]> collect = a.stream().flatMap(i -> b.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        for (int[] ints : collect) {
            System.out.println(Arrays.toString(ints));
        }

    }


    /**
     * 6.查找和匹配
     */
    @Test
    public void baseUse6(){
        //1.查找是否有蔬菜的菜 任意匹配一个及成功
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("有蔬菜存在");
        }
        //2,全部匹配
        if(menu.stream().allMatch(Dish::isVegetarian)){
            System.out.println("所有的都是蔬菜");
        }else{
            System.out.println("不是所有的都是蔬菜");
        }
        //3.没有一个匹配
        if(menu.stream().noneMatch(Dish::isVegetarian)){
            System.out.println("没有一个是蔬菜");
        }else{
            System.out.println("有蔬菜的菜的存在");
        }
    }

    /**
     * 7.查找任何一个满足条件的元素
     */
    @Test
    public void baseUse7(){
        //查找满足条件的任何一个
        Optional<Dish> any = menu.stream().filter(Dish::isVegetarian).findAny();
        System.out.println("是否存在"+any.isPresent());
        System.out.println("存在时执行Consumer代码块");
        any.ifPresent(s -> System.out.println("执行ifPresent操作"+s));
        System.out.println("执行get方法"+any.get());
        System.out.println("不存在的时候给一个默认值"+any.orElse(new Dish("清蒸鱼",false,100, Dish.Type.FISH)));

    }

    /**
     * 8.查找第一个元素
     */
    @Test
    public void baseUse8(){
        List<Integer> Num = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> first = Num.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
        System.out.println(first.get());

    }

    /**
     * 9.元素reduce
     */
    @Test
    public void baseUse9(){
        List<Integer> Num = Arrays.asList(1, 2, 3, 4, 5);
        Integer reduce = Num.stream().reduce(0, (a, b) -> a + b);
        System.out.println("元素相加的和是 -->"+ reduce);

        //======================计算其中的最大值
        Optional<Integer> reduce1 = Num.stream().reduce(Integer::max);
        System.out.println("最大值是-》"+reduce1.get());
        Optional<Integer> reduce2 = Num.stream().reduce(Integer::min);
        System.out.println("最小值是-》"+reduce2.get());
        //=====================计算流中数据的个数
        System.out.println("流中数据的个数-->"+Num.stream().count());
    }
}
