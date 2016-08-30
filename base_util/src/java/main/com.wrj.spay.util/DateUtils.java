package com.wrj.spay.util;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {

    public final static String DATE_FORMAT_DEFAULT = "yyyy-MM-dd";
    public final static String DATE_FORMAT_SOLIDUS = "yyyy/MM/dd";
    public final static String DATE_FORMAT_COMPACT = "yyyyMMdd";
    public final static String DATE_FORMAT_UTC_DEFAULT = "MM-dd-yyyy";
    public final static String DATE_FORMAT_UTC_SOLIDUS = "MM/dd/yyyy";

    public final static String DATE_FORMAT_CHINESE = "yyyy年MM月dd日";
    public final static String DATE_TIME_FORMAT_COMPACT = "yyyyMMdd HHmmss";
    public final static String DATE_TIME_FORMAT_CHINESE = "yyyy年MM月dd日 HH时mm分ss秒";

    public final static String DATE_TIME_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_TIME_FORMAT_SOLIDUS = "yyyy/MM/dd HH:mm:ss";
    public final static String DATE_TIME_FORMAT_UTC_DEFAULT = "MM-dd-yyyy HH:mm:ss";
    public final static String DATE_TIME_FORMAT_UTC_SOLIDUS = "MM/dd/yyyy HH:mm:ss";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String CMS_DRAW_SEQUENCE_FORMAT = "yyyyMMddhhmmss";
    public static final String XINBAO_DATE_FORMAT = "yyyy/MM/dd";
    public static final String MEMBER_SYSTEM_DATE_FORMAT = "yyyy-MM-dd";
    public static final String MATCH_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_TIME_STRING_FORMAT = "yyyyMMdd HH:mm:ss";
    public static final String DATE_STRING_FORMAT = "yyyyMMdd";
    public static final String SCH_REPORT_DATE_FORMAT = "yyyyMMddHHmmss";

    private static Map<String, String> dateFormatRegisterMap = new HashMap<String, String>();

    static {
        dateFormatRegisterMap.put(DATE_FORMAT_COMPACT, "^\\d{8}$");
        dateFormatRegisterMap.put(DATE_FORMAT_DEFAULT, "^\\d{4}-\\d{1,2}-\\d{1,2}$");
        dateFormatRegisterMap.put(DATE_FORMAT_SOLIDUS, "^\\d{4}/\\d{1,2}/\\d{1,2}$");
        dateFormatRegisterMap.put(DATE_FORMAT_UTC_DEFAULT, "^\\d{1,2}-\\d{1,2}-\\d{4}$");
        dateFormatRegisterMap.put(DATE_FORMAT_UTC_SOLIDUS, "^\\d{1,2}/\\d{1,2}/\\d{4}$");
        dateFormatRegisterMap.put(DATE_TIME_FORMAT_DEFAULT, "^\\d{4}-\\d{1,2}-\\d{1,2}\\s*\\d{1,2}:\\d{1,2}:\\d{1,2}$");
        dateFormatRegisterMap.put(DATE_TIME_FORMAT_SOLIDUS, "^\\d{4}/\\d{1,2}/\\d{1,2}\\s*\\d{1,2}:\\d{1,2}:\\d{1,2}$");
        dateFormatRegisterMap.put(DATE_TIME_FORMAT_UTC_DEFAULT, "^\\d{1,2}-\\d{1,2}-\\d{4}\\s*\\d{1,2}:\\d{1,2}:\\d{1,2}$");
        dateFormatRegisterMap.put(DATE_TIME_FORMAT_UTC_SOLIDUS, "^\\d{1,2}/\\d{1,2}/\\d{4}\\s*\\d{1,2}:\\d{1,2}:\\d{1,2}$");
    }

    public static String format(String formatString, Date date) {
        return new SimpleDateFormat(formatString).format(date);
    }

    public static Date parseDate(String src) {
        if (EmptyChecker.isEmpty(src)) {
            return null;
        }

        return parseDate(src, DATE_FORMAT_DEFAULT);
    }

    public static Date parseDate(String src, String dateTemplate) {
        if (EmptyChecker.isEmpty(src)) {
            return null;
        }

        try {
            return new SimpleDateFormat(dateTemplate).parse(src);
        } catch (ParseException e) {
            throw new RuntimeException(String.format("unsupported date template:%s", src), e);
        }
    }

    public static <T> T parseDate(String src, Class<T> dateClazz) {

        if (EmptyChecker.isEmpty(src)) {
            return null;
        }

        return convertDate(parseDate(src), dateClazz);
    }

    public static <T> T parseDate(String src, String dateTemplate, Class<T> dateClazz) {

        if (EmptyChecker.isEmpty(src)) {
            return null;
        }

        return convertDate(parseDate(src, dateTemplate), dateClazz);
    }

    public static Date fishForParseDate(String src) {

        if (EmptyChecker.isEmpty(src)) {
            return null;
        }

        return fishForParseDate(src, Date.class);
    }

    public static <T> T fishForParseDate(Object obj, Class<T> dateClazz) {

        if (EmptyChecker.isEmpty(obj)) {
            return null;
        }

        if (Date.class.isAssignableFrom(obj.getClass())) {
            return convertDate((Date) obj, dateClazz);
        }

        if (DateTime.class.isAssignableFrom(obj.getClass())) {
            return convertDate((DateTime) obj, dateClazz);
        }

        String src = obj.toString();

        for (Map.Entry<String, String> entry : dateFormatRegisterMap.entrySet()) {
            if (src.matches(entry.getValue())) {
                return convertDate(parseDate(src, entry.getKey()), dateClazz);
            }
        }

        throw new RuntimeException(String.format("unsupported date string format:%s", src));
    }

    public static boolean isDate(Object obj) {

        if (EmptyChecker.isEmpty(obj)) {
            return false;
        }
        return isDateClass(obj.getClass());
    }

    public static boolean isDateClass(Class<?> clazz) {
        return (Date.class.isAssignableFrom(clazz) || DateTime.class.isAssignableFrom(clazz));
    }

    public static String formatDate(Date date) {

        if (EmptyChecker.isEmpty(date)) {
            return null;
        }

        return formatDate(date, DATE_FORMAT_DEFAULT);
    }

    public static String formatDate(Date date, String dateTemplate) {
        if (EmptyChecker.isEmpty(date) || EmptyChecker.isEmpty(dateTemplate)) {
            return null;
        }

        return new SimpleDateFormat(dateTemplate).format(date);
    }

    public static <T> T convertDate(Date src, Class<T> dateClazz) {

        if (EmptyChecker.isEmpty(src)) {
            return null;
        }

        try {

            return dateClazz.getConstructor(long.class).newInstance(src.getTime());
        } catch (Exception e) {
            Logger.error(DateUtils.class, String.format("unsupported date type:%s", dateClazz.getName()), e);
            throw new RuntimeException(String.format("unsupported date type:%s", dateClazz.getName()), e);
        }
    }

    public static <T> T convertDate(DateTime src, Class<T> dateClazz) {

        if (EmptyChecker.isEmpty(src)) {
            return null;
        }

        try {

            return dateClazz.getConstructor(long.class).newInstance(src.getMillis());
        } catch (Exception e) {
            Logger.error(DateUtils.class, String.format("unsupported date type:%s", dateClazz.getName()), e);
            throw new RuntimeException(String.format("unsupported date type:%s", dateClazz.getName()), e);
        }
    }

    private static long render(long i, int j, int k) {
        return (i + (i > 0 ? j : -j)) / k;
    }

    /**
     * 时间相差的秒数
     *
     * @param start 被减的时间
     * @param end   减的时间
     * @return 相差的秒数
     */
    public static long diffSecond(Date start, Date end) {
        return render(end.getTime() - start.getTime(), 999, 1000);
    }

    public static long diffMinute(Date end) {
        return diffMinute(new Date(System.currentTimeMillis()), end);
    }

    public static long diffMinute(Date start, Date end) {
        return render(diffSecond(start, end), 59, 60);
    }

    public static long diffHour(Date start, Date end) {
        return render(diffMinute(start, end), 59, 60);
    }

    public static long diffDay(Date start, Date end) {
        return offset(start, end, Calendar.DAY_OF_YEAR);
    }

    public static long diffMonth(Date start, Date end) {
        return offset(start, end, Calendar.MONTH) + diffYear(start, end);
    }

    public static long diffYear(Date start, Date end) {
        Calendar s = Calendar.getInstance();
        Calendar e = Calendar.getInstance();

        s.setTime(start);
        e.setTime(end);

        return e.get(Calendar.YEAR) - s.get(Calendar.YEAR);
    }

    public static int diffYearByDay(Date start, Date end) {
        Calendar s = Calendar.getInstance();
        Calendar e = Calendar.getInstance();

        s.setTime(start);
        e.setTime(end);

        boolean moreThanOneYear = false;

        int month = e.get(Calendar.MONTH) - s.get(Calendar.MONTH);
        if (month > 0) {
            moreThanOneYear = true;
        } else if (month < 0) {
            moreThanOneYear = false;
        } else if (month == 0) {
            int day = e.get(Calendar.DAY_OF_MONTH) - s.get(Calendar.DAY_OF_MONTH);
            if (day > 0) {
                moreThanOneYear = true;
            } else {
                moreThanOneYear = false;
            }
        }
        if (moreThanOneYear) {
            return e.get(Calendar.YEAR) - s.get(Calendar.YEAR);
        } else {
            return e.get(Calendar.YEAR) - s.get(Calendar.YEAR) - 1;
        }

    }

    public static int gapAnnualDay(Date start, Date end) {
        Calendar s = Calendar.getInstance();
        Calendar e = Calendar.getInstance();
        s.setTime(start);
        e.setTime(end);
        if (e.get(Calendar.MONTH) == s.get(Calendar.MONTH)) {
            return e.get(Calendar.DAY_OF_MONTH) - s.get(Calendar.DAY_OF_MONTH);
        }

        if (e.get(Calendar.MONTH) < s.get(Calendar.MONTH)) {
            s.set(Calendar.YEAR, e.get(Calendar.YEAR) - 1);
            return (int) (e.getTime().getTime() / (24 * 3600 * 1000)) - (int) (s.getTime().getTime() / (24 * 3600 * 1000));
        }

        if (e.get(Calendar.MONTH) > s.get(Calendar.MONTH)) {
            s.set(Calendar.YEAR, e.get(Calendar.YEAR));
            return (int) (e.getTime().getTime() / (24 * 3600 * 1000)) - (int) (s.getTime().getTime() / (24 * 3600 * 1000));
        }

        return -1000;
    }

    private static long offset(Date start, Date end, int offsetCalendarField) {

        boolean bool = start.before(end);
        long rtn = 0;
        Calendar s = Calendar.getInstance();
        Calendar e = Calendar.getInstance();

        s.setTime(bool ? start : end);
        e.setTime(bool ? end : start);

        rtn -= s.get(offsetCalendarField);
        rtn += e.get(offsetCalendarField);

        while (s.get(Calendar.YEAR) < e.get(Calendar.YEAR)) {
            rtn += s.getActualMaximum(offsetCalendarField);
            s.add(Calendar.YEAR, 1);
        }

        return bool ? rtn : -rtn;
    }

    public static Date add(Date date, int n, int calendarField) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, n);

        return c.getTime();
    }

    public static Date addDays(Date date, int days) {
        Calendar cal=Calendar.getInstance();//使用日历类
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + days);
        return new Date(cal.getTimeInMillis());
    }

    public static String formatDateAsCmsDrawSequence(Date date) {
        return formatDate(date, CMS_DRAW_SEQUENCE_FORMAT);
    }

    public static String formatDateSchReport(Date date) {
        return formatDate(date, SCH_REPORT_DATE_FORMAT);
    }

    public static DateTime parseAsDateTime(String date) {
        return new DateTime(parseDate(date));
    }

    public static Date startOfToday() {
        return startOfDay(new Date());
    }

    public static String formatDateTime(Date date) {
        return (date == null) ? null : formatDate(date, DATE_TIME_FORMAT);
    }

    public static Date parseDateTime(String date) {
        return parseDate(date, DATE_TIME_FORMAT);
    }

    public static String formatDateAsMatchTime(Date date) {
        return formatDate(date, MATCH_TIME_FORMAT);
    }


    public static boolean beforeToday(Date date) {
        return date.compareTo(DateUtils.startOfToday()) < 0;
    }

    public static boolean afterToday(Date date) {
        return date.compareTo(DateUtils.startOfToday()) > 0;
    }

    public static Date startOfDay(Date date) {
        return new DateTime(date).dayOfYear().roundFloorCopy().toDate();
    }

    public static String formatDateAsString(Date date) {
        return formatDate(date, DATE_STRING_FORMAT);
    }

    public static Date endOfToday() {
        return endOfDay(new Date());
    }

    public static Date endOfDay(Date date) {
        DateTime startDateTime = new DateTime(date).dayOfYear().roundFloorCopy();
        return startDateTime.plusDays(1).minusMillis(1).toDate();
    }

    public static boolean isOnSameDayOfMonth(DateTime datetime, DateTime other) {
        return datetime.getDayOfMonth() == other.getDayOfMonth();
    }

    public static String formatDateForXinbao(Date date) {
        return formatDate(date, XINBAO_DATE_FORMAT);
    }

    public static Date parseXinbaoDateFormat(String dateString) throws ParseException {
        return parseDate(dateString, XINBAO_DATE_FORMAT);
    }


    public static String parseMemberSystemDateFormat(String xinbaoDateFormat) throws ParseException {
        return formatDate(parseXinbaoDateFormat(xinbaoDateFormat), MEMBER_SYSTEM_DATE_FORMAT);
    }

    public static String getYearOfFourBits(Date date) {
        return new DateTime(date).getYear() + "";
    }

    public static String getMonthOfTwoBits(Date date) {
        String month = new DateTime(date).getMonthOfYear() + "";
        if (month.length() == 1) {
            month = "0" + month;
        }
        return month;
    }

    public static String getDayOfTwoBits(Date date) {
        String day = new DateTime(date).getDayOfMonth() + "";
        if (day.length() == 1) {
            day = "0" + day;
        }
        return day;
    }

    public static boolean compareTillSecond(Date oneDate, Date anotherDate) {
        if (oneDate == null || anotherDate == null) return false;
        return format(DATE_TIME_FORMAT_DEFAULT, oneDate).equals(format(DATE_TIME_FORMAT_DEFAULT, anotherDate));
    }

    public static boolean compareWithInterval(Date oneDate, Date anotherDate, int interval) {
        if (oneDate == null || anotherDate == null) {
            return false;
        }
        long oneTime = oneDate.getTime();
        long anotherTime = anotherDate.getTime();
        return Math.abs(oneTime - anotherTime) / 1000 <= interval;
    }

    public static boolean isMondayToFriday(Date date) {
        int dayOfWeek = new DateTime(date).getDayOfWeek();
        return dayOfWeek != 6 && dayOfWeek != 7;
    }

    public static Date getBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static Date getEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

}
