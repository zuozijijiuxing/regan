package com.regan.time;

/**
 *  This is GNSS Time Helper, it must contain some
 *  functions to convert different time format.
 * @author gaokang
 *
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
public class RTimeUtil extends GregorianCalendar {	
	/**
     *日历的参考纪元（即1970年1月1日，00:00:00 ）到GPS参考纪元（1980年1月6日，00:00:00 GMT）之间相差的的毫秒数。
     */
    private long gpsEpochMSec = 315964800000L;

    /**
     * 默认空构造函数。 日历使用GMT时区设置为当前时间。
     */
    public RTimeUtil() {
    	
        /*这是世界时的*/
        super(TimeZone.getTimeZone("GMT"));
        /*下面是北京时间，即北京是东八区的*/
       //super(TimeZone.getTimeZone("GMT+8:00"));           */
        complete();
        computeFields();
        //setEpoch();
    }

    /**
     * 使用GPS时间（毫秒）构建日历，以定义时间并将时区设置为GMT。即输入GPS毫秒
     */
    public RTimeUtil(long gpsMilliSec) {
        super(TimeZone.getTimeZone("GMT"));
        complete();
        super.setTimeInMillis(gpsMilliSec + gpsEpochMSec);
        computeFields();
    }

    /**
     * 使用称为GMT的常规时间符号（而不是小时）构造GPSCalendar日历。
     */
    public RTimeUtil(int year, int month, int day, int min, int sec) {
        super(TimeZone.getTimeZone("GMT"));
        complete();
        clear();
        set(year, month, day, min, sec);
        computeTime();
    }

    /**
     *使用GMT的常规时间符号（包括小时）构造GPSCalendar日历。
     */
    public RTimeUtil(int year, int month, int day, int hour, int min, int sec) {
        super(TimeZone.getTimeZone("GMT"));
        complete();
        clear();
        set(year, month, day, hour, min, sec);
        computeTime();

    }

    public long getSeconds() {
        return getGPSSeconds();
    }
    /**
     * 获取与当前日历中设置的时间对应的GPS秒数。 一秒的分数被截断。
     */
    public long getGPSSeconds() {
        long msec = super.getTimeInMillis();
        msec -= gpsEpochMSec;
        long sec = msec / 1000;
        return sec;
    }
    /**
     * 获取与当前日历中设置的时间对应的GPS时间（毫秒）。
     */
    public long getGPSMilliseconds() {
        long msec = super.getTimeInMillis();
        msec -= gpsEpochMSec;
        return msec;
    }

    /**通过给出GPS时间（以秒为单位）设置此日历的时间。
     */
    public void setGPSSeconds(long gpsTime) {
        long time = (gpsTime * 1000) + gpsEpochMSec;
        super.setTimeInMillis(time);
    }

    public long getTimeInMillis() {
        return super.getTimeInMillis();
    }

    public int getUTCYear() {
        return get(Calendar.YEAR);
    }

    public int getUTCMonth() {
    	/*外国人似乎月份是0——11所以Calendar.MONTH比我们的少一，所以加上一了*/
        return get(Calendar.MONTH)+1;
    }

    public int getUTCDay() {
        return get(Calendar.DATE);
    }

    public int getUTCDayOfYear() {
        return get(Calendar.DAY_OF_YEAR);
    }

    public int getUTCHour() {
        return get(Calendar.HOUR_OF_DAY);
    }

    public int getUTCMinute() {
        return get(Calendar.MINUTE);
    }
    public int getUTCSecond() {
        return get(Calendar.SECOND);
    }
    public int getUTCMillisecond() {
        return get(Calendar.MILLISECOND);
    }
}
