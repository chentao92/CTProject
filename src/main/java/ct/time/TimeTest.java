package ct.time;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @program: CTProject
 * @description: 时间测试类
 * @author: chentao
 * @create: 2020-10-15 11:04
 **/

public class TimeTest {

    public static void main(String[] args) {
        TimeTest t = new TimeTest();
        //新增加的操作时间的类，推荐使用
        t.testLocalTime();
        t.testLocalDate();
        t.testLocalDateTime();
        t.testInstant(); //时间戳

        //时间操作不推荐使用
        t.testDate();

        t.dateToLoLocalDateTime();
        t.loLocalDateTimeToDate();
    }

    /**
     * 测试LocalTime的一些方法
     */
    private void testLocalTime() {
        LocalTime now  = LocalTime.now();
        System.out.println(now.getHour()); //24小时制
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println(now.format(dateTimeFormatter));
        //生成了新的实体
        LocalTime newTime = now.plusHours(1).plusMinutes(1).plusSeconds(1);
        System.out.println(newTime.getHour());
        System.out.println(newTime.getMinute());
        System.out.println(newTime.getSecond());
    }

    /**
     * 测试LocalDate的一些方法
     */
    private void testLocalDate() {
        LocalDate now = LocalDate.now();
        System.out.println(now.getYear());
//        System.out.println(now.getMonth());  //英文
        System.out.println(now.getMonthValue()); //从1开始
        System.out.println(now.getDayOfMonth());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(now.format(dateTimeFormatter));
        //生成了新的实体
        LocalDate newDate = now.plusYears(1).plusMonths(1).plusDays(1);
        System.out.println(newDate.getYear());
//        System.out.println(newDate.getMonth());  //英文
        System.out.println(newDate.getMonthValue()); //从1开始
        System.out.println(newDate.getDayOfMonth());
    }

    /**
     * 测试LocalDateTime的一些方法
     */
    private void testLocalDateTime() {
        LocalDateTime now =LocalDateTime.now();
        System.out.println(now.getYear());
//        System.out.println(now.getMonth());  //英文
        System.out.println(now.getMonthValue()); //从1开始
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getHour()); //24小时制
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(now.format(dateTimeFormatter));
        String dateTimeStr = "2020-10-10 12:23:34";
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        System.out.println(dateTime.toString());

        Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        System.out.println(milliSecond);
        //生成了新的实体
        LocalDateTime newDateTime = now.plusYears(1).plusMonths(1).plusDays(1).plusHours(1).plusMinutes(1).plusSeconds(1);
        System.out.println(newDateTime.getYear());
//        System.out.println(newDateTime.getMonth());  //英文
        System.out.println(newDateTime.getMonthValue()); //从1开始
        System.out.println(newDateTime.getDayOfMonth());
        System.out.println(newDateTime.getHour()); //24小时制
        System.out.println(newDateTime.getMinute());
        System.out.println(newDateTime.getSecond());

    }

    /**
     * java.util.Date的一些方法
     */

    @SneakyThrows
    private void testDate() {
        Date now = new Date();
        System.out.println(now.getYear()); //从1900开始
        System.out.println(now.getMonth()); //从0开始
        System.out.println(now.getDate()); //每月第几天
        System.out.println(now.getDay()); //每周第几天
        System.out.println(now.toLocaleString()); //是根据系统中的设置来生成的，慎用
        //线程不安全
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(now));
        String dateTimeStr = "2020-10-10 12:23:34";
        Date date = sdf.parse(dateTimeStr);
        System.out.println(date);
    }

    /**
     * LoLocalDateTime转化为Date
     */
    private void loLocalDateTimeToDate() {
        LocalDateTime now =LocalDateTime.now();
        Date date = Date.from(now.toInstant(ZoneOffset.of("+8")));
        System.out.println(date);
    }

    /**
     * Date转化为LoLocalDateTime
     */
    private void dateToLoLocalDateTime() {
        Date now = new Date();
        LocalDateTime localDateTime = now.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        System.out.println(localDateTime);
    }

    /**
     * 时间戳类
     */
    private void testInstant() {
        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(now.getEpochSecond());
        System.out.println(now.toEpochMilli());
        //转化为LocalDateTime需要时间修正
        LocalDateTime localDateTime = now.atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        System.out.println(localDateTime);
        //转化为Date不需要时区修正
        Date date = Date.from(now);
        System.out.println(date);
    }

}
