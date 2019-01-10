package _012_alg._001_排序算法;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2019/1/10 11:40 PM<br/>
 */
public class _004_归并排序 {
    /**
     *归并排序的核心思想在于合并的算法 使用双指针方法进行移动
     */

    @Test
    public  void 归并排序(){

        int[] iniData = {4, 5, 2, 41, 4, 234, 2, 51, 31, 3, 23, 1,2322,1,23,52,223,52,34,234,1,114123,5,134};
        if(iniData == null || iniData.length == 0 ){
            System.out.println("不能处理数据为空的数据");
            return ;
        }
        //这里使用临时变量来存放以已经排序好的
        int[] tempData = new int[iniData.length];

        mergeSore(iniData,tempData,0,iniData.length-1);

        System.out.println("排序之后的数据:"+ Arrays.toString(iniData));
    }


    public void mergeSore(int[] initData,int[] tempData, int startIndex,int endIndex){
        if(startIndex != endIndex){
            //除以2
            int mid = (startIndex + endIndex) >> 1;
            //接着分
            //左边
            mergeSore(initData,tempData,startIndex,mid);
            //右边
            mergeSore(initData,tempData,mid+1,endIndex);

            //合并的操纵
            megerOp(initData,tempData,startIndex,endIndex,mid);
        }
    }

    public void megerOp(int[] initData,int[] tempData,int startIndex,int endIndex,int midIndex){
        //元素存放的起始位置是最左端的位置start
        //控制合并两端的起始位置
        int partionFistIndex = startIndex;
        int partionSecondIndex = midIndex+1;
        int setIndex = startIndex;

        while(true){
            if(partionFistIndex == (midIndex+1) && partionSecondIndex == (endIndex+1)){
                //当两个都移动到分子分区的尾部时就不再移动指针了
                break;
            }
            //1.第一部分到头但是第二部分不到头
            //2.第二部分到头但是第一部分不到头
            //3.第一部分不到头第二部分不到头
            //1
            if(partionFistIndex == midIndex+1 && partionSecondIndex != endIndex+1){
                tempData[setIndex] = initData[partionSecondIndex];
                setIndex++;
                partionSecondIndex++;
            }
            //2
            if(partionFistIndex != midIndex+1 && partionSecondIndex == endIndex+1){
                tempData[setIndex] = initData[partionFistIndex];
                setIndex++;
                partionFistIndex++;
            }
            //3
            if (partionFistIndex != midIndex+1 && partionSecondIndex != endIndex+1){
                if(initData[partionFistIndex] <= initData[partionSecondIndex]){
                    tempData[setIndex] = initData[partionFistIndex];
                    setIndex++;
                    partionFistIndex++;
                }else{
                    tempData[setIndex] = initData[partionSecondIndex];
                    setIndex++;
                    partionSecondIndex++;
                }
            }

        }

        //将排序后的数组复制回去
        System.arraycopy(tempData,startIndex,initData,startIndex,endIndex-startIndex+1);
        cleanArrays(tempData);
    }

    public void cleanArrays(int[] arrays){
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = 0;
        }
    }
}
