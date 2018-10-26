package _000_day._010_10月23号Guava的请求操作;

import com.google.common.base.Preconditions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 14:26 2018/10/26
 */
public class _002_Preconditions参数校验 {

    /**
     * 使用Preconditions的好处
     *
     *
     * 在静态导入后, 方法很明确无歧义, checkNotNull可以清楚地告诉你它是干什么的, 它会抛出怎样的异常.
     * checkNotNull在验证通过后直接返回, 可以这样方便地写代码: this.field = checkNotNull(field).
     * 简单而又强大的可变参数'printf'风格的自定义错误信息.
     *
     * 作者：it_zzy
     * 链接：https://www.jianshu.com/p/ca7ac36615df
     *
     * */




    /**
     * 1.checkArgument(boolean) 检查boolean是否为真。 用作方法中检查参数
     * 2.checkNotNull(T) 检查value不为null， 直接返回value；
     * 3.checkState(boolean)检查对象的一些状态，不依赖方法参数。例如，Ierator可以用来next是否在remove之前被调用
     * 4.checkElementIndex(int index, int size) 检查index是否为在一个长度为size的list， string或array合法的范围
     * 5.checkPositionIndex(int index, int size)检查位置index是否为在一个长度为size的list， string或array合法的范围
     * 6.checkPositionIndexes(int start, int end, int size)检查[start, end)是一个长度为size的list，string或array合法的范围子集
     *
     */
    @Test
    public void baseUse() {
        //1.判断位置
        List<Integer> intList = new ArrayList<Integer>(15);
        for (int i = 0; i < 10; i++) {
                intList.add(i);

            }
       // Preconditions.checkPositionIndex(11,intList.size());

        //2.判断是否为空
        Object a = null;
//        Object o = Preconditions.checkNotNull(a,"不能为空");

        //3.判断参数
        Preconditions.checkArgument(1!=1,"1!=1");
        Preconditions.checkState(1!=1,"1!=1");
    }
}
