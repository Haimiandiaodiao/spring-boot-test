package _001_Jdk8TestPackage;

import com.alibaba.fastjson.JSON;
import org.joda.time.*;
import org.joda.time.chrono.CopticChronology;
import org.joda.time.chrono.GregorianChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * 使用joda-time来操作时间
 * @auther Dyy
 * @create 2018/1/30
 */
public class _005_DataTimeTest {

    /**
     * 1.即时的概念定义为重1970-01-01指定为毫秒的日期时间
     *    瞬间由ReadableInstant接口表示，主要实现是由DateTime来实现的。这个对象是不可变的是线程安全的
     *    可变的实现是MutableDateTime这个类对象可以被修改但是但是线程不安全的。
     *
     *    1.周的开始是重星期1开始的
     *    2.今年的年初有可能是去年的最后一周u
     *    3.
     */


    /**
     * 1.瞬间的概念 其实就是 instant        时间点的概念
     *  实现的有：
     *      DateTime        不可能改的对象
     *      MutableDateTime  可以被修改的对象
     *      DateMidnight     午夜的时间,不推荐使用
     *
     *      使用dayofweek周中的天
     *      使用getDayOfWeek得到周中的天
     */
    @Test
    public void  baseUse1(){
        DateTime dt = new DateTime(2004, 12, 25, 12, 0, 0, 0);
        int year = dt.getYear();
        int dayOfWeek = dt.getDayOfWeek();
        String asText = dt.dayOfWeek().getAsText(Locale.CHINA);
        System.out.println("年"+year+"一周中的第几天"+dayOfWeek+"星期"+asText);
        //构造日历对象
        long millis = dt.getMillis();
        System.out.println("时间戳"+millis);
    }


    /**
     * 2.局部的使用  partial   代表的是一个局部的时间  不包含时区 包含日期或者时间
     *      实现的类：
     *          LocalDate   表示不带时间或者时区的日期的不可变实现
     *          LocalTime   表示没有日期或者时区的时间的不可变实现
     *          LocalDateTime   表示不带时区的日期的时间的不可变的实现
     *          YearMonth  表示年份和月份 对信用卡到期日期很有用
     *          MonthDay    代表月和天  对没有年份的生日有用
     *
     *          局部+ 缺少的字段+时区 = 瞬间
     *          atXXXX  执行拼接的操作
     *          toXXXX  执行截取的操作
     */
    @Test
    public void baseUse2(){
        LocalDate date = LocalDate.of(2004,12,25);
        LocalTime time = LocalTime.of(12, 20);
        //拼接成整体
        LocalDateTime dt = date.atTime(time);
        System.out.println(dt);

        //获取部分的操作
        LocalDate partialDate = dt.toLocalDate();
        System.out.println(partialDate);
    }


    /**
     * 2.  Interval 间隔
     *       实现的子类
     *          interval
     *          mutableInterval
     *
     */
    @Test
    public void baseUse3(){
        DateTime start = new DateTime(2004, 12, 25, 0, 0, 0);
        DateTime end = new DateTime(2005, 1, 1, 0, 0, 0, 0);

        Interval interval = new Interval(start,end);

        Chronology chronology = interval.getChronology();//获得日历表
        Duration duration = interval.toDuration();//两时间间隔的时间
        Period period = interval.toPeriod(); //不知道什么意思

/*
        System.out.println(duration.getStandardDays());
        System.out.println(duration.getStandardHours());
*/

        System.out.println(period.getDays());
        System.out.println(period.getYears());
        System.out.println(period.getHours());

    }


    /**
     * 4.duration 持续时间 也就是两个时间点的时间差
     *  时间点+持续时间=时间点
     *  DateTime.plus   对时间点进行加法的操作
     */
    @Test
    public void baseUse4(){
        DateTime start = new DateTime(2004, 12, 25, 0, 0, 0);
        DateTime end = new DateTime(2005, 1, 1, 0, 0, 0, 0);

        Duration duration = new Duration(start, end);
        DateTime plus = start.plus(duration);
        System.out.println(plus);
    }

    /**
     * 5.期的概念 Period  查看是相差指定的单位的时间
     *  一些问题
     *      考虑一个月的时间 如果你把时间加上2月1日那么你讲得到3月1号这就是 以月为单位但是月的天数是不固定的
     *      实现的类
     *          Period 是不可以变换的类
     *          mutablePeriod  是可以变换的类
     *         Period 时期
     *         MutablePeriod 可变的时期
     *         Years 年
     *         Months 月
     *         Weeks 星期
     *         Days 天
     *         Hours 小时
     *         Minutes 分钟
     *         Seconds 秒
     *
     */
    @Test
    public void baseUse5(){
        DateTime start = new DateTime(2004, 12, 25, 0, 0, 0);
        DateTime end = new DateTime(2004, 12, 26, 0, 0, 0, 0);

        Period period = new Period(start, end);
        DateTime plus = start.plus(period);

        System.out.println(plus);
        Weeks weeks = Weeks.weeksBetween(start, end);//相差的周数
        Years years = Years.yearsBetween(start, end);//相差的年数
        Days days = Days.daysBetween(start, end);//相差的天数
        System.out.println(years.getYears());
        System.out.println(weeks.getWeeks());
        System.out.println(days.getDays());
    }


    /**
     * 6.支持的年表
     *          支持8中年表
     *           Buddhist
     *           Coptic
     *           Ethiopic
     *           Gregorian-Julian cutover
     *           Gregorian
     *           Islamic
     *           ISO         ISO的
     *           Julian
     */
    @Test
    public void baseUse6(){
        //设置时区
        //设置日历
        CopticChronology instance = CopticChronology.getInstance();
        //根据日历来拿到相应的时间
        DateTime dt = new DateTime(instance);

        System.out.println(dt.getYear());
        System.out.println(dt.getMonthOfYear());
    }

    /**
     * 7.格式化时间
     */
    @Test
    public void baseUse7(){
        DateTime start = new DateTime(2004, 12, 25, 0, 0, 0);
        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(start.toString(df));
        DateTime dateTime = df.parseDateTime("2004-12-25 00:00:00");
        System.out.println(dateTime);
        System.out.println(start.getZone().getID()); //获得时区的名字  Asia/Shanghai

        DateTime dateTime1 = new DateTime(new Date());
        System.out.println(dateTime1);
    }

    @Test
    public  void baseuse8(){
        //DateTimeZone zone = DateTimeZone.forID("America/New_York");
        CopticChronology instance = CopticChronology.getInstance();
        DateTimeField dateTimeField = instance.dayOfWeek();

        Chronology chrono = GregorianChronology.getInstance();
        DateTime start = new DateTime(2017, 12, 25, 0, 0, 0,chrono);

        DateTime dateTime = start.withWeekOfWeekyear(1).withDayOfWeek(1);
        DateTimeFormatter df = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        int maximumValue = dateTime.weekOfWeekyear().getMaximumValue();

        System.out.println(maximumValue);
        System.out.println(dateTime.toString(df));

         ;
        int maximumValue1 = dateTime.dayOfWeek().getMaximumValue();
    }


    /**
     *获得 Map
     */
    @Test
    public void baseUser11(){
        DateTime year = new DateTime(2016, 10, 1, 0, 0, 0);
        DateTime startTime = year.withWeekOfWeekyear(1).withDayOfWeek(1);

        //得到这个年的总共的周数
        int maxWeekCount = startTime.weekOfWeekyear().getMaximumValue();
        Map<Integer,Object> month;
        List<Map<Integer,Object>> result = new ArrayList<>();
        //时间格式
        DateTimeFormatter df = DateTimeFormat.forPattern("MM.dd");

        long l = System.currentTimeMillis();
        for (int i = 1; i <= maxWeekCount; i++) {
            month= new LinkedHashMap();
            DateTime weekStart = startTime.withWeekOfWeekyear(i).withDayOfWeek(1);
            //第i周的开始时间
            int dayCount = weekStart.dayOfWeek().getMaximumValue();
            //第i周的结束时间
            DateTime weekEnd = startTime.withWeekOfWeekyear(i).withDayOfWeek(dayCount);

            //格式化开始时间
            String weekStartStr = weekStart.toString(df);
            //格式化结束时间
            String weekEndStr = weekEnd.toString(df);

            String s = changeToBig(i);

            String monthStr = "第"+s+"周("+weekStartStr+"-"+weekEndStr+")";
            month.put(i,monthStr);
            result.add(month);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("所用的时间是"+(l1-l));
        String s = JSON.toJSONString(result);
        System.out.println(s);

        //得到某一年第几周的开始和结束时间
        Map<String, Object> needTime = getWeekStarEndTime(2016, 1);
        System.out.println("周的开始时间"+needTime.get("startTime"));
        System.out.println("周的结束时间"+needTime.get("endTime"));
    }


    /**
     * 现在只能满足两位数的要求之后扩展
     * 方法:changeToBig</br>
     * 描述:</br>
     * 作者:Dyy</br>
     * 时间:2018/1/30 19:17</br>
     * @param value
     */
    public String changeToBig(int value){
        String[] digit={"零","一","二","三","四","五","六","七","八","九"};
        String[] unit = {"十","百"};
        
        String result = "";
        int i = value / 10;
        if(i > 0){
            result = result+digit[i]+unit[0];
        }
        int i1 = value % 10;
        result += digit[i1];

        return result;
    }


    /**
     * 获得今年第几周的开始和结束时间
     * 需要抽取的参数  年份  第几周
     */
    public Map<String,Object> getWeekStarEndTime(int yearNum , int weekNum){
        DateTime year = new DateTime(yearNum, 10, 1, 0, 0, 0);
        //得到某一周的开始时间
        DateTime startTime = year.withWeekOfWeekyear(weekNum).withDayOfWeek(1);//这一周的开始时间
        int maxWeekCount = startTime.dayOfWeek().getMaximumValue();
        //得到某一周的结束时间
        DateTime endTime = startTime.withWeekOfWeekyear(weekNum).withDayOfWeek(maxWeekCount)
                .withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(999);

        Map<String,Object> result = new HashMap<>();
        result.put("startTime",startTime.getMillis());
        result.put("endTime",endTime.getMillis());

        return result;
    }


    /**
     * 查询上一周的实际
     */
    @Test
    public void findPreWeek(){
        DateTime year = new DateTime(2016, 1, 4, 0, 0, 0);
        int weekOfWeekyear = year.getWeekOfWeekyear();
        System.out.println(weekOfWeekyear);

        DateTime dateTime = year.plusWeeks(-1);
        int weekOfWeekyear1 = dateTime.getWeekOfWeekyear();
        System.out.println(weekOfWeekyear1);
        int year1 = dateTime.getYear();
        int weekOfWeekyear2 = dateTime.getWeekOfWeekyear();
        System.out.println("这一周的年"+year1);
        System.out.println("是这一年的第几周"+weekOfWeekyear2);
        Map<String, Object> weekStarEndTime = getWeekStarEndTime(year1, weekOfWeekyear2);
        System.out.println(weekStarEndTime);
    }
}
