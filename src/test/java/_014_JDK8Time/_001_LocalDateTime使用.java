package _014_JDK8Time;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.omg.CORBA.ObjectHelper;
import org.springframework.retry.context.RetryContextSupport;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 16:36 2018/8/27
 */
public class _001_LocalDateTime使用 {


    @Test
    public void show(){

        Integer integer = new Integer(1);
        Integer integer1 = new Integer(1);
        if (1 == integer1){
            System.out.println("Y");
        }else {
            System.out.println("N");
        }

    }

    @Test
    public void show1(){
        LocalDateTime of = LocalDateTime.of(2018, 1, 1, 00, 00, 10);
        System.out.println(of);

        LocalDateTime localDateTime = of.plusDays(-1);
        System.out.println(localDateTime);
    }

    @Test
    public void getWeekAndMonthAndDay(){
        DateTimeFormatter tf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        DateTime currentTime = new DateTime(2019,10,1,1,1,1);
        DateTime dateTime5 = currentTime.minusDays(1);
        System.out.println("昨天的时间:"+dateTime5.toString(tf));


        int monthOfYear = currentTime.getMonthOfYear();
        System.out.println("查看是第几个月:"+monthOfYear);
//        DateTime currentTime = new DateTime();
        //得到今年的周数
        DateTime dateTime = currentTime.withWeekOfWeekyear(1).withDayOfWeek(1);
        int maximumValue = dateTime.weekOfWeekyear().getMaximumValue();
        System.out.println("今年有几周:"+maximumValue);

        //得到第一周的开始时间和结束时间
        DateTime yearStartWeek = dateTime.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
        String s = yearStartWeek.toString(tf);
        System.out.println("今天第一周的开始时间是："+s);
        DateTime dateTime1 = yearStartWeek.withDayOfWeek(7);
        String s1 = dateTime1.toString(tf);
        System.out.println("今天第一周的结束时间是:"+s1);
        //得到最后一周的开始时间和结束时间
        DateTime dateTime2 = currentTime.withWeekOfWeekyear(maximumValue);
        DateTime dateTime3 = dateTime2.withDayOfWeek(1);
        System.out.println("最后一周的开始时间是"+dateTime3.toString(tf));
        DateTime dateTime4 = dateTime2.withDayOfWeek(7);
        System.out.println("最后一周的结束时间是"+dateTime4.toString(tf));


        //今天是今年的第几周
        int weekOfWeekyear = currentTime.getWeekOfWeekyear();
        int i = currentTime.weekOfWeekyear().get();
        System.out.println(weekOfWeekyear);
        System.out.println("今天是第几周"+i);
    }

    /**
     * 给一个时间点，并根据类型获得开始数据和结束时间
     * @Author:Dyy
     * @Description:
     * @Date: Created in 15:26 2018/10/8
     * @param dateStr  符合日期的字符串"2018-10-9 10:10:10"
     * @param type    1:天 2:周 3:月 4:年
     */
    @Test
    public void computerStartAndEndTime(){
        String dateStr = "2018-10-8 15:31:12";
        Integer type = 2;
        DateTimeFormatter tf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = null;
        DateTime startTime = null;
        DateTime endTime = null;

        try {
            dateTime = tf.parseDateTime(dateStr);
        }catch (Exception e){
            throw new RuntimeException("请输入合规的时间 如 2018-10-8 10:10:10");
        }

        if(type == null || type < 1 || type >4){
           throw  new RuntimeException("请输入合规的type参数 1:天 2:周 3:月 4:年");
        }

        Map<String, Object> result = new HashMap<>();


        if(type == 1){
            //查询这一天的开始和结束时间
            startTime = dateTime;
            endTime = dateTime;
        }

        if(type == 2){
            //查询这一周的开始时间和结束时间
            startTime = dateTime.withDayOfWeek(1);
            endTime = dateTime.withDayOfWeek(7);
            //获得这是一年中的第几周
            int weekOfWeekyear = startTime.getWeekOfWeekyear();
            result.put("weekYear",weekOfWeekyear);
        }

        if(type == 3){
            //查询这一月的开始时间和结束时间
            startTime = dateTime.withDayOfMonth(1);
            int monthDays = dateTime.dayOfMonth().getMaximumValue();
            endTime = dateTime.withDayOfMonth(monthDays);

        }

        if(type == 4){
            startTime = dateTime.withDayOfYear(1);
            int yearDays = dateTime.dayOfYear().getMaximumValue();
            endTime = dateTime.withDayOfYear(yearDays);
        }

        startTime = startTime.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
        endTime = endTime.withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(999);

        long millis = startTime.getMillis();
        long millis1 = endTime.getMillis();
        Date date = startTime.toDate();
        Date date1 = endTime.toDate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        result.put("startTime",dateFormat.format(date));
        result.put("endTime",dateFormat.format(date1));

        System.out.println(millis);
        System.out.println(millis1);
        System.out.println(dateFormat.format(new Date(millis)));
        System.out.println(dateFormat.format(new Date(millis1)));
//        System.out.println(dateFormat.format(date1));
        System.out.println(result.get("weekYear"));
    }



    @Test
    public void getDay(){
        DateTimeFormatter tf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        //今日
        DateTime currentTime = new DateTime();
        int year = currentTime.getYear();
        int monthOfYear = currentTime.getMonthOfYear();
        int dayOfMonth = currentTime.getDayOfMonth();

        //昨日
        DateTime yearTime = currentTime.minusDays(1);
        int yearYest = yearTime.getYear();
        int monthOfYearYest = yearTime.getMonthOfYear();
        int dayOfMonthYest = yearTime.getDayOfMonth();
        //本周
        int weekOfWeekyear = currentTime.getWeekOfWeekyear();
        //上周
        DateTime dateTime = currentTime.minusWeeks(1);
        int year1 = dateTime.getYear();
        int weekOfWeekyear1 = dateTime.getWeekOfWeekyear();
        //本月
        //monthOfYear;
        //上月
        DateTime dateTime1 = currentTime.minusMonths(1);
        int year2 = dateTime1.getYear();
        int monthOfYear1 = dateTime1.getMonthOfYear();
        int dayOfMonth1 = dateTime1.getDayOfMonth();

    }
}
