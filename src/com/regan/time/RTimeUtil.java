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
     *�����Ĳο���Ԫ����1970��1��1�գ�00:00:00 ����GPS�ο���Ԫ��1980��1��6�գ�00:00:00 GMT��֮�����ĵĺ�������
     */
    private long gpsEpochMSec = 315964800000L;

    /**
     * Ĭ�Ͽչ��캯���� ����ʹ��GMTʱ������Ϊ��ǰʱ�䡣
     */
    public RTimeUtil() {
    	
        /*��������ʱ��*/
        super(TimeZone.getTimeZone("GMT"));
        /*�����Ǳ���ʱ�䣬�������Ƕ�������*/
       //super(TimeZone.getTimeZone("GMT+8:00"));           */
        complete();
        computeFields();
        //setEpoch();
    }

    /**
     * ʹ��GPSʱ�䣨���룩�����������Զ���ʱ�䲢��ʱ������ΪGMT��������GPS����
     */
    public RTimeUtil(long gpsMilliSec) {
        super(TimeZone.getTimeZone("GMT"));
        complete();
        super.setTimeInMillis(gpsMilliSec + gpsEpochMSec);
        computeFields();
    }

    /**
     * ʹ�ó�ΪGMT�ĳ���ʱ����ţ�������Сʱ������GPSCalendar������
     */
    public RTimeUtil(int year, int month, int day, int min, int sec) {
        super(TimeZone.getTimeZone("GMT"));
        complete();
        clear();
        set(year, month, day, min, sec);
        computeTime();
    }

    /**
     *ʹ��GMT�ĳ���ʱ����ţ�����Сʱ������GPSCalendar������
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
     * ��ȡ�뵱ǰ���������õ�ʱ���Ӧ��GPS������ һ��ķ������ضϡ�
     */
    public long getGPSSeconds() {
        long msec = super.getTimeInMillis();
        msec -= gpsEpochMSec;
        long sec = msec / 1000;
        return sec;
    }
    /**
     * ��ȡ�뵱ǰ���������õ�ʱ���Ӧ��GPSʱ�䣨���룩��
     */
    public long getGPSMilliseconds() {
        long msec = super.getTimeInMillis();
        msec -= gpsEpochMSec;
        return msec;
    }

    /**ͨ������GPSʱ�䣨����Ϊ��λ�����ô�������ʱ�䡣
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
    	/*������ƺ��·���0����11����Calendar.MONTH�����ǵ���һ�����Լ���һ��*/
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
