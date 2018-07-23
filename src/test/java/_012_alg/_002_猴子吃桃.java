package _012_alg;

import org.junit.Test;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/23 上午10:17<br/>
 */
public class _002_猴子吃桃 {

    /**
     * 猴子吃桃每天吃掉1半+1个 第十天的时候就剩下1个桃子  问第一天摘下来多少
     */
    @Test
    public void mokeyEactPaich() {
        //反推公式(（X+1）*2)^10
        int days = 9;//吃的天数
        int mostEatConts =1; //每天多吃的个数
        int perDayCount = 1; //前一天的桃子熟数
        int totalCount = 0; //总共的桃子数

        for ( ; days > 0; days--) {
            perDayCount = (perDayCount + mostEatConts)*2;
            totalCount+=perDayCount;
            System.out.println("第"+days+"天的桃子数："+perDayCount);
        }
        }
}
