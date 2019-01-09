package _012_alg._001_排序算法;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 16:23 2019/1/8
 */
public class _001_选择排序 {
    /**
     * 选择排序的操作过程就是每一次循环 总个数-n 个数拿出最小的数
     * 相比于冒泡排序减少了冒泡中交换的次数
     * */

    @Test
    public void 选择排序(){
        int[] iniData = {4, 5, 2, 41, 4, 234, 2, 51, 31, 3, 23, 1};
        if(iniData == null || iniData.length == 0 ){
            System.out.println("不能处理数据为空的数据");
            return ;
        }

        //正常逻辑
        //最小数的临时变量位置
        int tmpMinIndex = 0;
        //交换中的原
        int swapSource = 0;
        int dataLength = iniData.length;
        //总共要循环的次数 用来控制每次的交换
        for (int i = 0; i < dataLength-1; i++) {
            //拿到每次最小的数据
            //将下一个数当做默认数据
            tmpMinIndex = i;
            for (int j = i+1; j < dataLength; j++) {
                if(iniData[j] < iniData[tmpMinIndex]){
                    tmpMinIndex = j;
                }
            }

            //一开始是先交换位置0的数据
            //数据交换
            swapSource = iniData[i];
            iniData[i] = iniData[tmpMinIndex];
            iniData[tmpMinIndex] = swapSource;
        }

        //输出结果
        System.out.println("排序结果"+Arrays.toString(iniData));
    }
}
