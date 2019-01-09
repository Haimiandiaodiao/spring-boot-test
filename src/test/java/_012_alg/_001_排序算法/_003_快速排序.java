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

    /**
     * 开素拍素的大概思想就是每次可以确定本次比较的基准数的位置，然后在以基准数的位置为基础分割层左右两端在
     * 重复这样的操作，如果使用递归的话也就是调用的次数会和树一样指数级的变多
     */

    @Test
    public void 快速排序(){
        int[] iniData = {4, 5, 2, 41, 4, 234, 2, 51, 31, 3, 23, 1,2322,1,23,52,223,52,34,234,1,114123,5,134};
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
     *                          {4, 5, 2, 41, 4, 234, 2, 51, 31, 3, 23, 1}
     */
    public void fastSort(int[] iniData,int startIndex,int endInedx){
        //基准数位置
        int baseIndex = startIndex;
        //当前移动的指针的位置
        int port = endInedx;

        //具体的排序
        //借用startIndex作为
        for (;baseIndex != port;){

            //使用靠近算法 base在前也就是port要去追她就是port++才能靠近
            //当base在port后面的时候port要去追她就要port--才能靠近她
            if (baseIndex > port){
                //基数在大的一方,指针要找比基础值小的值 port要++
                if(iniData[baseIndex] < iniData[port]){
                    //交换数据
                    swap(iniData,baseIndex,port);
                    //交换指针
                    int tmp = baseIndex;
                    baseIndex = port;
                    port = tmp;
                    //指针下移
                }else{
                    ++port;
                }
            }else{
                //基数在小的一方,指针要找比基础值da的值 port要--
                if(iniData[baseIndex] > iniData[port]){
                    //交换数据
                    swap(iniData,baseIndex,port);
                    //交换指针
                    int tmp = baseIndex;
                    baseIndex = port;
                    port = tmp;
                    //指针下移

                }else{
                    --port;
                }
            }
        }

        //左边个数大于2 说明还有没有排序完的数据 【其实点---基线点前一个位置】
        if(baseIndex - startIndex  > 2){
            fastSort(iniData,startIndex,baseIndex-1);
        }
        //右边格式大于2就是还需要排序的知道不
        if(endInedx - baseIndex > 2){
            fastSort(iniData,baseIndex+1,endInedx);
        }


    }
    
    /**
     * 数组的交换
     * <br/>
     * @author Dyy <br/>
     * @description <br/>
     * @date 2019/1/9 11:38 PM <br/>
     * @param array<br/>
     * @param i<br/>
     * @param j <br/>  
     */
    public void swap(int[] array ,int i , int j){
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }
}
