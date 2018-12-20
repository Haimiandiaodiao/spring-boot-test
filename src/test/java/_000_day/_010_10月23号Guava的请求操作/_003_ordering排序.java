package _000_day._010_10月23号Guava的请求操作;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.junit.Test;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 15:34 2018/10/26
 */
public class _003_ordering排序 {

    /**
     *  Ordering 实例无非就是一个特殊的Comparator 实例。Ordering只是需要依赖于一个比较器（例如，Collections.max）的方法，
     *  并使其可作为实例方法。另外，Ordering提供了链式方法调用和加强现有的比较器，可以轻松构造复杂的comparator。
     *
     *  所涉及到大概所有方法
     *  static Ordering<Object> allEqual()
     * 返回一个排序，它把所有的值相等，说明“没有顺序。”通过这个顺序以任何稳定的排序算法的结果，在改变没有​顺序元素。
     * 2	static Ordering<Object> arbitrary()
     * 返回一个任意顺序对所有对象，其中compare(a, b) == 0 意味着a == b（身份平等）。
     * 3	int binarySearch(List<? extends T> sortedList, T key)
     * 搜索排序列表使用键的二进制搜索算法。
     * 4	abstract int compare(T left, T right)
     * 比较两个参数的顺序。
     * 5	<U extends T> Ordering<U> compound(Comparator<? super U> secondaryComparator)
     * 返回首先使用排序这一点，但它排序中的“tie”，然后委托给secondaryComparator事件。
     * 6	static <T> Ordering<T> compound(Iterable<? extends Comparator<? super T>> comparators)
     * 返回一个排序它尝试每个给定的比较器，以便直到一个非零结果找到，返回该结果，并返回零仅当所有比较器返回零。
     * 7	static <T> Ordering<T> explicit(List<T> valuesInOrder)
     * 返回根据它们出现的定列表中的顺序比较对象进行排序。
     * 8	static <T> Ordering<T> explicit(T leastValue, T... remainingValuesInOrder)
     * 返回根据它们所赋予本方法的顺序进行比较的对象进行排序。
     * 9	static <T> Ordering<T> from(Comparator<T> comparator)
     * 返回基于现有的比较实例进行排序。
     * 10	<E extends T> List<E> greatestOf(Iterable<E> iterable, int k)
     * 返回根据这个顺序给出迭代，为了从最大到最小的k个最大的元素。
     * 11	<E extends T> List<E> greatestOf(Iterator<E> iterator, int k)
     * 返回从给定的迭代器按照这个顺序，从最大到最小k个最大的元素。
     * 12	<E extends T> ImmutableList<E> immutableSortedCopy(Iterable<E> elements)
     * 返回包含的元素排序这种排序的不可变列表。
     * 13	boolean isOrdered(Iterable<? extends T> iterable)
     * 返回true如果在迭代后的第一个的每个元素是大于或等于在它之前，根据该排序的元素。
     * 14	boolean isStrictlyOrdered(Iterable<? extends T> iterable)
     * 返回true如果在迭代后的第一个的每个元素是严格比在它之前，根据该排序的元素更大。
     * 15	<E extends T> List<E> leastOf(Iterable<E> iterable, int k)
     * 返回根据这个顺序给出迭代，从而从低到最大的k个最低的元素。
     * 16	<E extends T> List<E> leastOf(Iterator<E> elements, int k)
     * 返回第k从给定的迭代器，按照这个顺序从最低到最大至少元素。
     *
     * 17	<S extends T> Ordering<Iterable<S>> lexicographical()
     * 返回一个新的排序它通过比较对应元素两两直到非零结果发现排序迭代;规定“字典顺序”。
     * 18	<E extends T> E max(E a, E b)
     * 返回两个值按照这个顺序的较大值。
     * 19	<E extends T> E max(E a, E b, E c, E... rest)
     * 返回指定的值，根据这个顺序是最大的。
     * 20	<E extends T> E max(Iterable<E> iterable)
     * 返回指定的值，根据这个顺序是最大的。
     * 21	<E extends T> E max(Iterator<E> iterator)
     * 返回指定的值，根据这个顺序是最大的。
     * 22	<E extends T> E min(E a, E b)
     * 返回两个值按照这个顺序的较小者。
     * 23	<E extends T> E min(E a, E b, E c, E... rest)
     * 返回最少指定的值，根据这个顺序。
     * 24	<E extends T> E min(Iterable<E> iterable)
     * 返回最少指定的值，根据这个顺序。
     * 25	<E extends T> E min(Iterator<E> iterator)
     * 返回最少指定的值，根据这个顺序。
     * 26	static <C extends Comparable> Ordering<C> natural()
     * 返回使用值的自然顺序排序序列化。
     * 27	<S extends T> Ordering<S> nullsFirst()
     * 返回对待null小于所有其他值，并使用此来比较非空值排序。
     * 28	<S extends T> Ordering<S> nullsLast()
     * 返回对待null作为大于所有其他值，并使用这个顺序来比较非空值排序。
     * 29	<F> Ordering<F> onResultOf(Function<F,? extends T> function)
     * 返回一个新的排序在F上，首先应用功能给它们，然后比较使用此这些结果的顺序元素。
     * 30	<S extends T> Ordering<S> reverse()
     * 返回相反顺序; 顺序相当于Collections.reverseOrder（Comparator）。
     * 31	<E extends T> List<E> sortedCopy(Iterable<E> elements)
     * 返回包含的元素排序此排序可变列表;使用这个只有在结果列表可能需要进一步修改，或可能包含null。
     * 32	static Ordering<Object> usingToString()
     * 返回由它们的字符串表示的自然顺序，toString()比较对象进行排序。
     *
     *
     *
     * */

    @Test
    public void 基础排序的使用(){
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(new Integer(5));
        numbers.add(new Integer(2));
        numbers.add(new Integer(15));
        numbers.add(new Integer(51));
        numbers.add(new Integer(53));
        numbers.add(new Integer(35));
        numbers.add(new Integer(45));
        numbers.add(new Integer(32));
        numbers.add(new Integer(43));
        numbers.add(new Integer(16));

        Ordering<Comparable> natural = Ordering.natural();
        //1.拿到最大值，最小值
        Integer max = natural.max(numbers);
        Integer min = natural.min(numbers);
        System.out.println("最大值"+max);
        System.out.println("最小值"+min);


        //1.判断是否是有序的
        boolean ordered = natural.isOrdered(numbers);
        System.out.println("是否是排序过的:"+ordered);
        //2.正序
        Collections.sort(numbers,natural);
        System.out.println(numbers);

        boolean ordered1 = natural.isOrdered(numbers);
        System.out.println("是否是排序过的:"+ordered1);

        //3.反序
        Collections.sort(numbers,natural.reversed());
        System.out.println(numbers);

        //================添加null值==================
        numbers.add(null);
        //带null的排序
        Ordering<Comparable> nullOrdering = natural.nullsFirst();
//        Ordering<Comparable> nullOrdering = natural.nullsLast();

        Collections.sort(numbers,nullOrdering);
        System.out.println("带空值的排序"+numbers);
        Integer min1 = nullOrdering.min(numbers);
        System.out.println("带有null值的排序器"+min1);


        //4.使用usingToString来进行排序的统计 意思是根据返回值的转化为字符串值 来进行排序
        Ordering<Integer> objectOrdering = Ordering.usingToString().onResultOf(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer;
            }
        }).nullsFirst();

        Collections.sort(numbers,objectOrdering);
        //[null, 15, 16, 2, 32, 35, 43, 45, 5, 51, 53] 结果是字符串排序
        System.out.println(numbers);
    }



    @Test
    public void 基础字符排序的使用(){
        List<String> names = new ArrayList<String>();
        names.add("Ram");
        names.add("Shyam");
        names.add("Mohan");
        names.add("Sohan");
        names.add("Ramesh");
        names.add("Suresh");
        names.add("Naresh");
        names.add("Mahesh");
        names.add(null);
        names.add("Vikas");
        names.add("Deepak");
        names.add("Deepak");

        Ordering<Comparable> natural = Ordering.natural().nullsFirst();
        String min = natural.min(names);
        String max = natural.max(names);
        System.out.println("最小值"+min);
        System.out.println("最大值"+max);

        //1.返回最小的5个元素 和 最大的5个元素
        List<String> strings = natural.leastOf(names, 2);
        List<String> strings1 = natural.greatestOf(names, 5);
        System.out.println(strings1);
        System.out.println(strings);

        //2.排序
        Collections.sort(names,natural);
        System.out.println(names);
        //3.判断是否是已排序的
        System.out.println(natural.isOrdered(names));

        //4.排序完之后返回一个新的排序list对象但是是浅copy
        List<String> strings2 = natural.sortedCopy(names);



        /**
         * 该方法使用自然排序规则生成排序器，如从小到大、日期先后顺序。使用这个方法之前先介绍一下onResultOf 方法，这个方法接收一个Function函数，该函数的返回值可以用于natural方法排序的依据，即根据这个返回值来进行自然排序，示例代码如下：
         * OnResultOf的使用
         * Ordering<People> ordering = Ordering.natural().onResultOf(new Function<People, Comparable>() {
         *     @Override
         *     public Comparable apply(People people) {
         *         return people.getAge();
         *     }
         * });
         *
         * for (People p : ordering.sortedCopy(peopleList)) {
         *     System.out.println(MoreObjects.toStringHelper(p)
         *                     .add("name", p.getName())
         *                     .add("age", p.getAge())
         *     );
         * }
         *
         * */

        //5.Ordering.from()是根据comparator接口来创建ording比较器对象
    }


    //它把所有的值相等，说明“没有顺序 也是无需的和添加顺序一致
    @Test
    public void allEqual的使用(){
        List<String> names = new ArrayList<String>();
        names.add("Ram");
        names.add("Shyam");
        names.add("Mohan");
        names.add("Sohan");
        names.add("Ramesh");
        names.add("Suresh");
        names.add("Naresh");
        names.add("Mahesh");
        names.add(null);
        names.add("Vikas");
        names.add("Deepak");
        names.add("Deepak");
        //返回一个没有排序的数组
        Ordering<Object> objectOrdering = Ordering.allEqual();
        Collections.sort(names,objectOrdering);
        String max = objectOrdering.max(names);
        System.out.println(names);
        System.out.println(max);
        //[Ram, Shyam, Mohan, Sohan, Ramesh, Suresh, Naresh, Mahesh, null, Vikas, Deepak, Deepak]
        //Ram
    }

    //任意固定的排序 虽然是无序 但是每次返回的顺序都是相同的
    @Test
    public void arbitrary(){
        List<String> names = new ArrayList<String>();
        names.add("Ram");
        names.add("Shyam");
        names.add("Mohan");
        names.add("Sohan");
        names.add("Ramesh");
        names.add("Suresh");
        names.add("Naresh");
        names.add("Mahesh");
        names.add(null);
        names.add("Vikas");
        names.add("Deepak");
        names.add("Deepak");
        Ordering<Object> d = Ordering.arbitrary();
        Collections.sort(names,d);
        System.out.println(names);
        //[null, Mahesh, Naresh, Deepak, Deepak, Sohan, Mohan, Vikas, Ramesh, Shyam, Ram, Suresh]
        //返回的是一个乱序的排序
        String max = d.max(names);
        System.out.println(max);
        //Suresh
    }


    @Test
    public void 二分查找法查找(){
        List<String> names = new ArrayList<String>();
        names.add("Ram");
        names.add("Shyam");
        names.add("Mohan");
        names.add("Sohan");
        names.add("Ramesh");
        names.add("Suresh");
        names.add("Naresh");
        names.add("Mahesh");
//        names.add(null);
        names.add("Vikas");
        names.add("Deepak");
        names.add("Deepak");

        //1.测试有序的
//        Ordering<Comparable> comparableOrdering = Ordering.natural().nullsFirst();
//        Collections.sort(names,comparableOrdering);
//        System.out.println(names);
//        int shyam = comparableOrdering.binarySearch(names, "Shyam");
//        System.out.println(shyam);
        //[null, Deepak, Deepak, Mahesh, Mohan, Naresh, Ram, Ramesh, Shyam, Sohan, Suresh, Vikas]
        //8

//        //2.测试无须的
//        Ordering<Object> o = Ordering.allEqual();
//        Collections.sort(names,o);
//        System.out.println(names);
//        int shyam = o.binarySearch(names, "Shyam");
//        System.out.println(shyam);
//        //[Ram, Shyam, Mohan, Sohan, Ramesh, Suresh, Naresh, Mahesh, null, Vikas, Deepak, Deepak]
//        //5
//        //错误 二分查找必须建立在有序的基础上才可以
//
//        //所有值都相等
//        int compare = o.compare("a", "b");
//        System.out.println(compare);
//        //0 证明他的比较是所有值都相等
//
//
//
//        //6.第二比较器 当相等时就调用第二比较器
//        o.compound((Comparator)null);
//
//        //7.返回一个排序它尝试每个给定的比较器，以便直到一个非零结果找到，返回该结果，并返回零仅当所有比较器返回零。
//        Ordering compound = Ordering.compound((Iterable) null);


      //immutableSortedCopy 返回排序之后的不可变数组
    }

    @Test
    public void 复习orering的使用(){

        ArrayList<Integer> init = Lists.newArrayList(1, 11, 2,4, 22, 3, 33, 4, 44);
        System.out.println(init);

        Ordering<Comparable> natural1 = Ordering.natural().reverse();

        List<Integer> integers = natural1.sortedCopy(init);
        integers.add(100);
        ImmutableList<Integer> integers1 = natural1.immutableSortedCopy(init);
//        integers1.add(99);

        Integer min = natural1.min(init);
        System.out.println("集合中最小的元素："+min);


        Collections.sort(init,natural1);
        boolean ordered = natural1.isOrdered(init);
        //严格的排序不能有排序值相等的元素
        boolean strictlyOrdered = natural1.isStrictlyOrdered(init);
        System.out.println(ordered);
        System.out.println(strictlyOrdered);

        System.out.println(natural1.greatestOf(init,3));
        System.out.println(natural1.leastOf(init,3));

        //1.通过自然排序
        Ordering<Integer> natural = Ordering.natural();
        Collections.sort(init,natural);
        System.out.println(init);
        //2.使用字符串排序
        Ordering<Object> string = Ordering.usingToString();
        Collections.sort(init,string);
        System.out.println("字符串的==》"+init);

        //使用comparator构建排序器
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };
        Ordering<Integer> from = Ordering.from(comparator);
        Collections.sort(init,from);
        System.out.println("自定义排序器==》"+init);

        //可以拿到追加的排序规则

        Ordering<Object> compound = string.reverse().nullsLast();
        Ordering<Iterable<Object>> lexicographical = compound.lexicographical();

    }


}
