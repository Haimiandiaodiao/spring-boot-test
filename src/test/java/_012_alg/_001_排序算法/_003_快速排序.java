package _012_alg._001_排序算法;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 17:37 2019/1/8
 */
public class _003_快速排序 {

    @Test
    public void 快速排序(){
        int[] iniData = {4, 5, 2, 41, 4, 234, 2, 51, 31, 3, 23, 1};
        if(iniData == null || iniData.length == 0 ){
            System.out.println("不能处理数据为空的数据");
            return ;
        }
        //正常逻辑
        //用第一个为基准分割
        fastSort(iniData,0,iniData.length-1);

        System.out.println("快速排序之后："+ Arrays.toString(iniData));

    }


    /**
     * 缩小范围的方法【包含头和包含尾】
     * @Author:Dyy
     * @Description:
     * @Date: Created in 15:55 2019/1/9
     * @param iniData           初始话的数据
     * @param startIndex        开始的位置
     * @param endInedx          结束的位置
     */
    public void fastSort(int[] iniData,int startIndex,int endInedx){
        int inistartIndex = startIndex;
        int iniendIndex = endInedx;

        //具体的排序
        int mid = iniData[startIndex];
        for (;startIndex != endInedx;){
            if(iniData[startIndex] <= iniData[endInedx]){
                --endInedx;
            }else{
                //交换数据
                iniData[startIndex] = iniData[startIndex] ^ iniData[endInedx];
                iniData[endInedx] = iniData[startIndex] ^ iniData[endInedx];
                iniData[startIndex] = iniData[startIndex] ^ iniData[endInedx];
                ++startIndex;
            }

        }

        //左边个数大于2 说明还有没有排序完的数据 【其实点---基线点前一个位置】
        if(startIndex - inistartIndex > 2){
            fastSort(iniData,inistartIndex,endInedx-1);
        }
        //右边
        if(iniendIndex - startIndex > 2){
            fastSort(iniData,startIndex+1,endInedx);
        }


    }

}
