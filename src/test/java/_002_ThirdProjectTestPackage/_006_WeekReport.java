package _002_ThirdProjectTestPackage;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * @auther Dyy
 * @create 2018/1/30
 */
public class _006_WeekReport {

    @Test
    public void baseUse1(){
        Map<String, Object> weekList = getWeekList(1, 2016);

    }

    /**
     * 默认是星期日是周的第一天
     *   获得某一年的一天
     * 方法:getWeekList</br>
     * 描述:</br>
     * 作者:Dyy</br>
     * 时间:2018/1/30 16:43</br>
     * @param correctionDay 矫正的天数可以为负数 这里是正的
     */
    public Map<String,Object> getWeekList(int correctionDay,int year){
        Calendar cal = Calendar.getInstance();

        cal.setWeekDate(year,1,1);//获得某一年的第一周的时间
        cal.add(Calendar.DAY_OF_YEAR,correctionDay);

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sf.format(cal.getTime()));

        return null;
    }

    @Test
    public void base1(){
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR,2015);
        cal.set(Calendar.MONTH,11);
        cal.set(Calendar.DAY_OF_MONTH,27);

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sf.format(cal.getTime()));

        int i = cal.get(Calendar.WEEK_OF_YEAR);
        System.out.println(i);

        cal.setWeekDate(2015,52,1);
        System.out.println(sf.format(cal.getTime()));

    }
}
