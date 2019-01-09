package _012_alg._001_排序算法;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 16:53 2019/1/8
 */
public class _002_冒泡排序 {

    /**
     * 冒泡排序就是
     * */
    @Test
    public void 冒泡排序(){
        int[] iniData = {4, 5, 2, 41, 4, 234, 2, 51, 31, 3, 23, 1};
        if(iniData == null || iniData.length == 0 ){
            System.out.println("不能处理数据为空的数据");
            return ;
        }
        //正常逻辑
        int length = iniData.length;
        //控制执行的次数
        for (int i = 1; i < length; i++) {

            //控制最大到的交换位置 -1 是为了纠正上面的次数是从1开始的
            for (int j = 0; j < length - i; j++) {
                if(iniData[j] > iniData[j+1]){
                    int tmp = iniData[j];
                    iniData[j] = iniData[j+1];
                    iniData[j+1] = tmp;
                }
            }
        }

        System.out.println("排序结果"+ Arrays.toString(iniData));
    }
}
