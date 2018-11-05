package _000_day._011_11月1号JodaTime的使用;

import org.junit.Test;

import java.util.*;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 10:39 2018/11/1
 */
public class _001_基础比较的使用 {

    @Test
    public void baseUse(){
        //基础的时间判断时间是不是有交集
        Date section1Start = new Date(2018,1,1,1,1,1);
        Date section1End = new Date(2018,5,5,5,5,5);

        Date section2Start = new Date(2018,1,1,1,1,1);
        Date section2End = new Date(2018,1,1,1,1,1);

        if(section1Start == null || section1End == null ||section2Start == null || section2End == null ){
            throw  new IllegalArgumentException("时间段参数都不能为空");
        }

        int a =0,c=0;
        do{
            --c;
            a=a-1;
        }while(a>0);
        System.out.println(c);
    }

   @Test
    public void baseUse2(){
       List<Integer> a1 = new ArrayList<>();
       List<Integer> a2 = new LinkedList<>();

       a1.add(1);
       a1.add(2);
       a1.add(3);
       a1.add(null);
       a1.add(null);
       a1.add(4);
       System.out.println(a2);

       Collections.addAll(a2,  new Integer[a1.size()]);
       Collections.copy(a2,a1);
       System.out.println(a2);
       a2.add(1);
       a2.add(1);

       a2.remove(1);
       System.out.println(a2);

   }

    @Test
    public void 数组测试(){
        Integer[] a = new Integer[10];
        int index = 1;
        int index2 = 15;

        System.out.println(a[index]);
        System.out.println(a[3]);
        System.out.println(a[index2]);


        int index3 = 1;
        String[] test = new String[10];
        String s = test[index3];


    }
}



class  myCompter implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}