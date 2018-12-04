package com.tendy.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeUtil {

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String YYYYMMDD = "yyyyMMdd";

	public static final String YYYYMMDDHHMMSS1 = "yyyy/MM/dd/HH/mm/ss";

	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static final String YYYYMMDD1 = "yyyy/MM/dd";
	
	public static String HH_MM_SS = "HH:mm:ss";

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String MMCNDDCNHH_MM_SS = "M月d日 HH:mm:ss";

	public static String MMCNDDCN = "M月d日";

	public static String MMCN = "M月";

	public static String YYYY_MM = "yyyy-MM";

	public static Date string2Date(String str) throws Exception {
		return string2Date(str, YYYYMMDDHHMMSS);
	}

	public static Date string2Date(String str, String formatter) throws ParseException {
		return (new SimpleDateFormat(formatter)).parse(str);
	}

	public static String formatDate(Date date) {

		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDate(Date date, String formatStr) {

		DateFormat sdf = new SimpleDateFormat(formatStr);

		return sdf.format(date);
	}

	
	public static Date parseTimeStr(String timeStr)
			throws ParseException {

		return parseTimeStr(timeStr, "yyyy-MM-dd HH:mm:ss");
	}

	public static Date parseTimeStr(String timeStr, String formatStr)
			throws ParseException {

		DateFormat sdf = new SimpleDateFormat(formatStr);

		return sdf.parse(timeStr);
	}
	
	public static Date getCurTimeDate() {
		
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime();
	}

	public static String getCurTimeStr() {

		Calendar cal = Calendar.getInstance();

		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(cal.getTime());
	}
	
	public static Date getAnotherDate(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		if(date != null){
			cal.setTime(date);
		}
		cal.add(field, amount);
		return cal.getTime();
	}
	
	public static Date getAfterDayDate(int days) {
		
		return getAnotherDate(null, Calendar.DATE, days);
	}
	
	public static Date getAfterDayDate(Date date, int days) {

		return getAnotherDate(date, Calendar.DATE, days);
	}

	public static Date getAfterDayDateZero(){
		return getAfterDayDateZero(1);
	}

	public static Date getAfterDayDateZero(int i){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, i);
		return cal.getTime();
	}
	public static Date getAfterMinuteDateZero(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MINUTE, 1);
		return cal.getTime();
	}
	
	 /**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException
     */    
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);   
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
        return Integer.parseInt(String.valueOf(between_days));
    }

	/**
	 * 获取今天0时0分0秒时间
	 * @return
	 */
	public static Date getTodayStartTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return zero;
	}

	/**
	 * 获取今天0时0分0秒时间
	 * @return
	 */
	public static Date getTodayStartTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return zero;
	}

	/**
	 * 获取今天23时59分59秒时间
	 * @return
	 */
	public static Date getTodayEndTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date zero = calendar.getTime();
		return zero;
	}

	/**
	 * 获取今天23时59分59秒时间
	 * @return
	 */
	public static Date getTodayEndTime(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date zero = calendar.getTime();
		return zero;
	}

	/**
	 * 获取当天剩余的秒
	 * @return
	 */
	public static int getTodayLeftSeconds(){
		Calendar curDate = Calendar.getInstance();
		Calendar tommorowDate = new GregorianCalendar(curDate
				.get(Calendar.YEAR), curDate.get(Calendar.MONTH), curDate
				.get(Calendar.DATE) + 1, 0, 0, 0);
		return (int) Math.abs((tommorowDate.getTimeInMillis() - curDate .getTimeInMillis()) / 1000);
	}

	/**
	 * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
	 *
	 * @param nowTime 当前时间
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 * @author jqlin
	 */
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
		if (nowTime.getTime() == startTime.getTime()
				|| nowTime.getTime() == endTime.getTime()) {
			return true;
		}
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);
		Calendar begin = Calendar.getInstance();
		begin.setTime(startTime);
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断时间是否过期
	 * @return
	 */
	public static boolean isTimeOut(Date time){
		boolean flag = false;
		if(System.currentTimeMillis() >= time.getTime()){
			flag = true;
		}
		return flag;
	}

	public static void main(String[] args) throws ParseException {
		System.out.println(TimeUtil.formatDate(getAfterMinuteDateZero()));
	}

	/**
	 * 获取当前时间的前一天时间
	 * @param date
	 * @return
	 */
	public static Date getBeforeDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//使用set方法直接进行设置
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day-1);
		Date zero = calendar.getTime();
		return zero;
	}
}
