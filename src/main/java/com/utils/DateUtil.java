package com.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {
    public static String format(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String formatToGmtTime(Date date) {
        return format(date, "YYYY-MM-dd HH:mm:ss");
    }

    public static String formatToShort(Date date) {
        return format(date, "YYYY-MM-dd");
    }

    public static String format(long currentTimestampMs, String pattern) {
        return format(new Date(currentTimestampMs), pattern);
    }

    public static String formatTodayToShort(Date date) {
        return format(today(date), "YYYYMMdd");
    }

    public static Date parse(String date, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(date);
    }

    public static Date parseFromGmtTime(String date) throws ParseException {
        return parse(date, "YYYY-MM-dd HH:mm:ss");
    }

    public static Date parseFromShort(String date) throws ParseException {
        return parse(date, "yyyy-MM-dd");
    }

    public static long currentTimestamp() {
        return currentTimestampMs() / 1000;
    }

    public static long currentTimestampMs() {
        return System.currentTimeMillis();
    }

    public static Date now() {
        return new Date(currentTimestampMs());
    }

    public static Date today() {
        return truncate(now());
    }

    public static Date today(Date date) {
        return truncate(date);
    }

    public static Date tomorrow() {
        return addDays(today(), 1);
    }

    public static Date tomorrow(Date date) {
        return addDays(today(date), 1);
    }

    public static Date yesterday() {
        return addDays(today(), -1);
    }

    public static Date yesterday(Date date) {
        return addDays(today(date), -1);
    }

    public static Date nextWeek() {
        return addDays(today(), 7);
    }

    public static Date nextWeek(Date date) {
        return addDays(today(date), 7);
    }

    /**
     * 获取当前日期，并且转换为整型
     */
    @SuppressWarnings("deprecation")
    public static Long currentDateToLong() {
        Date currentDate = today();
        return Long.valueOf(
                currentDate.getYear() * 10000 + currentDate.getMonth() * 100 + currentDate.getDay());
    }

    /**
     * @return 例如2018-01-01 12:00:00返回2018-01-01
     */
    public static Date truncate(Date date) {
        return DateUtils.truncate(date, Calendar.DATE);
    }

    /**
     * @return 例如2018-01-01 12:00:00返回2018-01-02
     */
    public static Date ceiling(Date date) {
        return DateUtils.ceiling(date, Calendar.DATE);
    }

    /**
     * 获取添加n天的日期
     */
    public static Date addDays(Date date, int amount) {
        return DateUtils.addDays(date, amount);
    }

    public static Date addSeconds(Date date, int amount) {
        return DateUtils.addSeconds(date, amount);
    }

    public static Date addSeconds(int amount) {
        return addSeconds(now(), amount);
    }

    public static Date addMonths(Date date, int amount) {
        return DateUtils.addMonths(date, amount);
    }

    /**
     * 比较2个日期先后
     */
    public static int compare(Date d1, Date d2) {
        return d1.compareTo(d2);
    }

    /**
     * 获取本周日期列表
     */
    public static List<Date> findDates(LocalDate start_time, LocalDate end_time) {
        LocalDate today = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = start_time.atStartOfDay(zoneId);
        ZonedDateTime zdt1 = end_time.atStartOfDay(zoneId);

        Date dBegin = Date.from(zdt.toInstant());
        Date dEnd = Date.from(zdt1.toInstant());
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    //获取今天的开始时间
    public static Date getStartTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date reuslt = null;
        try {
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String yearMouth = df2.format(new Date()) + " 00:00:00";
            reuslt = df.parse(yearMouth);
        } catch (Exception e) {

        }
        return reuslt;
    }

    //获取今天的结束时间
    public static Date getnowEndTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date reuslt = null;
        try {
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String yearMouth = df2.format(new Date()) + " 23:59:59";
            reuslt = df.parse(yearMouth);
        } catch (Exception e) {

        }
        return reuslt;
    }

    public static Date formatTime(Date date) {
        Date reuslt = null;
        try {
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
            String yearMouth = df2.format(date);
            reuslt = df2.parse(yearMouth);
        } catch (Exception e) {

        }
        return reuslt;
    }

    /**
     * 时间戳转格式化
     *
     * @param timestamp
     * @return
     */
    public static Date timestamp2DateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        Date date = localDateTime2Date(localDateTime);
        return date;
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        //Combines this date-time with a time-zone to create a  ZonedDateTime.
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取距离当前时间n天的时间戳
     *
     * @param timeMills
     * @param days
     * @return
     */
    public static long getTimestampByDays(long timeMills, int days) {
        Instant instant = Instant.ofEpochMilli(timeMills);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime currentDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDateTime finalDateTime;
        if (days > 0) {
            finalDateTime = currentDateTime.plusDays(days);
        } else {
            finalDateTime = currentDateTime.minusDays(-days);
        }
        long finalTimestamp = finalDateTime.atZone(zone).toInstant().toEpochMilli();
        return finalTimestamp;
    }

    public static long getTodayStartTimestamp() {
        LocalDateTime currentDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        long startTimestamp = currentDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return startTimestamp;
    }

    public static long getTodayEndTimestamp() {
        //当天零点
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        long startTimestamp = todayEnd.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return startTimestamp;
    }

    /**
     * @param localDateTime
     * @param format
     * @return
     */
    public static String getLocalDateTime2String(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }
}
