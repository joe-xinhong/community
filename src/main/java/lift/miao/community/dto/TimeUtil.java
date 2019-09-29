package lift.miao.community.dto;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @Description:    时间工具类
* @Author:         Joe
* @CreateDate:     2019/9/29 14:15
*/
public class TimeUtil {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
    public static DateTimeFormatter FMT = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.s");
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    // 2017-08-09 上午/下午
    public static final String YYYY_MM_DD_A = "yyyy-MM-dd a";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static SimpleDateFormat YYYY_MM = new SimpleDateFormat("yyyy-MM");
    public static SimpleDateFormat YYYY = new SimpleDateFormat("yyyy");

    public static String getCurrentYear(){
        return YYYY.format(new Date());
    }

    /**
     *
     * @param planTime
     * @return
     */
    public static String getMonthFirstDay(String planTime) {
        try {
            Calendar calendar = Calendar.getInstance();
            if (!StringUtils.isEmpty(planTime)) {
                Date planTime1 = sdf1.parse(planTime);
                calendar.setTime(planTime1);
            }
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @param planTime
     * @return
     */
    public static String getMonthLastDay(String planTime) {
        try {
            Calendar calendar = Calendar.getInstance();
            if (!StringUtils.isEmpty(planTime)) {
                Date planTime1 = sdf1.parse(planTime);
                calendar.setTime(planTime1);
            }
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 得到转换后的日期格式
     */
    public static String getFormatTime(Date time) {
        if (time == null) {
            return "";
        }
        return sdf.format(time);
    }

    //得到转换后的日期格式
    public static String getFormatPlanTime(Date time) {
        if (time == null) {
            return " ";
        }
        return sdf1.format(time);
    }

    //获取当天的起始时间
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    public static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    //获取昨天天的起始时间
    public static Date getLastDayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.add(Calendar.DATE, -1);
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    public static Date getLastDayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.add(Calendar.DATE, -1);
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    public static boolean greaterEqualEvlent(String time) {
        try {
            Calendar todayStart = Calendar.getInstance();
            todayStart.set(Calendar.HOUR_OF_DAY, 11);
            todayStart.set(Calendar.MINUTE, 40);
            todayStart.set(Calendar.SECOND, 0);
            todayStart.set(Calendar.MILLISECOND, 0);
            Date time1 = sdf.parse(time);
            if (time1.getTime() < todayStart.getTime().getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 判断输入日期是否当天日期
     *
     * @param current
     * @return
     */
    public static boolean isToday(Date current) {
        DateTime begin = new DateTime().withTime(0, 0, 0, 0);
        DateTime end = new DateTime().withTime(23, 59, 59, 999);
        Interval interval = new Interval(begin, end);
        return interval.contains(current.getTime());
    }

    /**
     * 判断输入日期是否今天和昨天的日期
     *
     * @param current
     * @return
     */
    public static boolean isTodayAndYesterday(Date current) {
        DateTime begin = new DateTime().withTime(0, 0, 0, 0).minusDays(1);
        DateTime end = new DateTime().withTime(23, 59, 59, 999);
        Interval interval = new Interval(begin, end);
        return interval.contains(current.getTime());
    }

    /**
     * 转换不同类型的日期格式
     *
     * @param to
     * @param from
     * @param target
     * @return
     */
    public static String transferFormat(String target, String from, String to) {
        try {
            SimpleDateFormat fromFormat = new SimpleDateFormat(from);
            Date targetDate = fromFormat.parse(target);
            return new SimpleDateFormat(to).format(targetDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据日期区间转换成步长为1的递增数组
     *
     * @param start
     * @param end
     * @return
     */
    public static List<String> turnToArrayBetweenDate(Date start, Date end) {
        List<String> result = Lists.newArrayList();
        int days = Days.daysBetween(new DateTime(start), new DateTime(end)).getDays();
        if (days < 1) {
            return result;
        }
        int i = 0;
        do {
            result.add(new DateTime(start).plusDays(i).toString(YYYY_MM_DD));
            i++;
        } while (days >= i);
        return result;
    }

    /**
     * 获取当前时间戳（秒）
     */
    public static long getCurrentTimeStampSecond() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取指定年份日历
     * @param year
     * @return
     */
    private static Calendar getCalendarFormYear(int year){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }
    /**
     * 获取指定年指定周的开始日期
     * @param year
     * @param weekNo
     * @return
     */
    public static String getStartDayOfWeekNo(int year,int weekNo){
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String monthStr = null;
        String dayStr = null;
        StringBuilder monthBuilder = new StringBuilder();
        StringBuilder dayBuilder = new StringBuilder();
        if(month < 10){
            monthStr = monthBuilder.append("0").append(month).toString();
        } else {
            monthStr = monthBuilder.append(month).toString();
        }
        if(day < 10) {
            dayStr = dayBuilder.append("0").append(day).toString();
        } else {
            dayStr = dayBuilder.append(day).toString();
        }
        return cal.get(Calendar.YEAR) + "-" + monthStr + "-" + dayStr;

    }

    /**
     * 获取指定年指定周的结束日期
     * @param year
     * @param weekNo
     * @return
     */
    public static String getEndDayOfWeekNo(int year,int weekNo){
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String monthStr = null;
        String dayStr = null;
        StringBuilder monthBuilder = new StringBuilder();
        StringBuilder dayBuilder = new StringBuilder();
        if(month < 10){
            monthStr = monthBuilder.append("0").append(month).toString();
        } else {
            monthStr = monthBuilder.append(month).toString();
        }
        if(day < 10) {
            dayStr = dayBuilder.append("0").append(day).toString();
        } else {
            dayStr = dayBuilder.append(day).toString();
        }
        return cal.get(Calendar.YEAR) + "-" + monthStr + "-" +  dayStr;
    }


    //获取 某日是某年的第几周
    public static int getWeekByDay(String day) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(format.parse(day));
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    //获取 指定开始日期和结束日期，包含日期列表。
    public static List<String> getDays(String beginDate, String endDate) throws ParseException {
        List<String> result = Lists.newArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf1.parse(beginDate));
        for (long  time= calendar.getTimeInMillis(); time <= sdf1.parse(endDate).getTime(); time = getNextDay(calendar)) {
            result.add(sdf1.format(time));
        }
        return result;
    }

    public static long getNextDay(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        return calendar.getTimeInMillis();
    }

    /**
     * 得到某一天是这一年的第几周
     * @param date
     * @return
     */
    public static int getWeek(String date) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf1.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        return week;
    }

    /**
     * 获取周区间
     * @param start yyyy-MM-dd
     * @param end yyyy-MM-dd
     * @return
     */
    public static List<String> findWeeks(String start,String end){
        int days = Days.daysBetween(new DateTime(start), new DateTime(end)).getDays();
        if (days < 1) {
            return Lists.newArrayList();
        }
        int i = 0;
        Set<Integer> sets = Sets.newHashSet();
        do {
            int week = getWeek(new DateTime(start).plusDays(i).toString(YYYY_MM_DD));
            sets.add(week);
            i++;
        } while (days >= i);
        return sets.stream().sorted().map(num -> String.valueOf(num)).collect(Collectors.toList());
    }

    public static List<String> findMonths(String start,String end) throws ParseException {
        List<String> list = Lists.newArrayList();
        Date d1 = sdf1.parse(start);//定义起始日期
        Date d2 = sdf1.parse(end);//定义结束日期
        Calendar dd = Calendar.getInstance();//定义日期实例
        dd.setTime(d1);//设置日期起始时间
        while (dd.getTime().before(d2)) {//判断是否到结束日期
            list.add(YYYY_MM.format(dd.getTime()));
            dd.add(Calendar.MONTH, 1);//进行当前日期月份加1
        }
        return list;
    }


    public static void main(String[] args) throws ParseException {

        List<String> weeks = findWeeks("2018-05-04", "2018-09-20");
        System.out.println(weeks);


//        for(int i =1 ; i< 10; i++){
//            int week = getWeek("2018-09-0" + i);
//            System.out.println(week);
//        }
//        String startDayOfWeekNo = getStartDayOfWeekNo(2018, 35);
//        System.out.println(startDayOfWeekNo);
//        String endDayOfWeekNo = getEndDayOfWeekNo(2018, 35);
//        System.out.println(endDayOfWeekNo);

        String start = "2018-07-08";
        String end = "2018-09-30";
//        List<String> months = findMonths(start, end);
//        System.out.println(months);

//        System.out.println(new Timestamp(sdf1.parse(start).getTime()));
//        System.out.println(new Timestamp(sdf.parse(end + " 23:59:59").getTime()));

//        String lastDay = getMonthLastDay(start);
//        System.out.println(lastDay);

    }
}
