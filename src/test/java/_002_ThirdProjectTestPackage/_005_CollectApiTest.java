package _002_ThirdProjectTestPackage;

import com.dyy.Jdk8_Package.Modul.Dish;
import com.dyy.Jdk8_Package.Modul.Trader;
import com.dyy.Jdk8_Package.Modul.Transaction;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.maxBy;

/**
 * 对收集器的操作
 * @auther Dyy
 * @create 2018/2/6
 */
public class _005_CollectApiTest {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");
    List<Transaction> tr = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );


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
     * 1.对交易对年份进行分组
     */
    @Test
    public void baseUse1(){
        //使用debug来进行查看
        Map<Integer, List<Transaction>> collect = tr.stream().collect(groupingBy(Transaction::getYear));
        System.out.println("dd");
        //2.查找交易额最多的单子
        Optional<Transaction> collect1 = tr.stream().collect(maxBy(comparingInt(Transaction::getValue)));
    }


    /**
     * 2.把收集器的结果转换成为另外一种类型
     */
    @Test
    public void baseUse2(){
        Map<Dish.Type, Dish> collect = menu.stream().collect(groupingBy(Dish::getType,
                collectingAndThen(
                        maxBy(comparing(Dish::getCalories)),
                        Optional::get)
        ));
    }
}
