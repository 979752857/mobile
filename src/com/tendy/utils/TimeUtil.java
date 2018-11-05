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
	
	public static Date getAfterDayMinuteZero(int i){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MINUTE, i);
		return cal.getTime();
	}
	
	public static Date getAfterMinuteDate(int minutes) {
		
		return getAnotherDate(null, Calendar.MINUTE, minutes);
	}
	
	public static Date getAfterMonthDate(Date date, int months) {
		
		return getAnotherDate(date, Calendar.MONTH, months);
	}
	
	public static Date getAfterYearDate(Date date, int years) {
		
		return getAnotherDate(date, Calendar.YEAR, years);
	}

	public static String getNextNDaysStr(String timeStr, String formatStr, int days) {

		Date endTime = null;
		try {
			endTime = parseTimeStr(timeStr, formatStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Calendar cal = Calendar.getInstance();

		cal.setTime(endTime);

		cal.add(Calendar.DATE, days);

		return formatDate(cal.getTime(), formatStr);
	}
	
	public static String formateSeconds(int seconds){
		
		return seconds/3600+"时"+seconds%3600/60+"分"+seconds%3600%60+"秒";
	}
	
	/**
	 * 计算时间之间的年差
	 * 
	 * @return
	 */
	public static int getYearDiff(Date first_date, Date second_date) {
		if(first_date == null || second_date == null){
			return 0;
		}
		boolean flag = false;
		int years = -1;
		try {
			first_date = parseTimeStr(formatDate(first_date, "yyyy-MM-dd"), "yyyy-MM-dd");
			second_date = parseTimeStr(formatDate(second_date, "yyyy-MM-dd"), "yyyy-MM-dd");
			if(first_date.after(second_date)){
				Date tempDate = first_date;
				first_date = second_date;
				second_date = tempDate;
				flag = true;
			}
			
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(first_date);
        
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(second_date);
			
			while(!cal1.after(cal2)){
			    years++;
			    cal1.add(Calendar.YEAR, 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
        if(flag && years>0){
        	years = Integer.parseInt("-"+years);
        }
      return years;
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
	 * 计算某时间距当前时间的天数
	 * @param date_target
	 * @return
	 */
	public static int getDaysBetweenToday(String date_target){
		Integer days = 0;
		try {
			Date beginDate = parseTimeStr(date_target, "yyyy-MM-dd");
			days = daysBetween(beginDate, new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}
	
	  /**
     * 某一个月第一天和最后一天
     * @param date
     * @return
     */
    public static Map<String, String> getFirstday_Lastday_Month(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        //当月第一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(calendar.getTime());

        //当月最后一天
        calendar.add(Calendar.MONTH, 1);
        //加一个月
        calendar.add(Calendar.DATE, -1);
        //再减一天即为上个月最后一天
        String day_last = df.format(calendar.getTime());

        Map<String, String> map = new HashMap<String, String>();
        map.put("first", day_first);
        map.put("last", day_last);
        return map;
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK,calendar.getFirstDayOfWeek());
        return calendar.getTime();
    }

    /**
     * 取得当前日期所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,calendar.getFirstDayOfWeek() + 6);
        return calendar.getTime();
    }

    /**
     * 获取本周周一
     * @param date
     */
    public static String getFirstDayOfThisWeek(Date date){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return df.format(calendar.getTime());
    }
    
    /**
     * 获取本周周天
     * @param date
     */
    public static String getLastDayOfThisWeek(Date date){
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
    	return df.format(getAfterDayDate(calendar.getTime(),1));
    }

	/**
	 * 判断是周几
	 * @param pTime
	 * @return
	 */
	public static int dayForWeek(String pTime) {
    	int dayForWeek=7;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			  Calendar c = Calendar.getInstance();
			  c.setTime(format.parse(pTime));
			  dayForWeek = 0;
			  if(c.get(Calendar.DAY_OF_WEEK) == 1){
			   dayForWeek = 7;
			  }else{
			   dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
			  }
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return dayForWeek;
   }
	/**
	 * 判断是周几
	 * @param date
	 * @return
	 */
	public static int dayForWeek(Date date) {
		int dayForWeek=7;
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		dayForWeek = 0;
		if(c.get(Calendar.DAY_OF_WEEK) == 1){
			dayForWeek = 7;
		}else{
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

    /**
     * 获取日期所在的周
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getDayOfWeek(String date) throws ParseException {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(TimeUtil.parseTimeStr(date, "yyyy-MM-dd"));
    	return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
    
	public static int getHour(String date) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(TimeUtil.parseTimeStr(date, "yyyy-MM-dd hh:mm:ss"));
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 获得日期的年
	 * @param date
	 */
	public static int getYear(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	public static Date getDateFromTime(long time){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		return calendar.getTime();
	}
	
	
	
	/**
	 * 是否同一天
	 * @param date1
	 * @param date2
	 */
	public static boolean isSameDate(Date date1, Date date2) {
       Calendar cal1 = Calendar.getInstance();
       cal1.setTime(date1);
       Calendar cal2 = Calendar.getInstance();
       cal2.setTime(date2);
       return  (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
    		   && (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
    		   && (cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH));
	}

	public static Integer getSecondBetweenDate(Date date1, Date date2){
		Long betweenSecond = (date2.getTime()-date1.getTime())/1000;
		return betweenSecond.intValue();
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
	 * 判断时间是否在两个时间区间内 true在区间内
	 * @param startTime
	 * @param endTime
	 * @param checkTime
	 * @return
	 */
	public static boolean isValidTime(Date startTime, Date endTime, Date checkTime){
		boolean flag = false;
		if(startTime.getTime() <= checkTime.getTime() && endTime.getTime() >= checkTime.getTime()){
			flag = true;
		}
		return flag;
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

	public static Date getFirstDayOfLastWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		calendar.set(Calendar.DAY_OF_WEEK,calendar.getFirstDayOfWeek());
		return calendar.getTime();
	}

	public static Date getLastDayOfLastWeek(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		calendar.set(Calendar.DAY_OF_WEEK,calendar.getFirstDayOfWeek() + 6);
		return calendar.getTime();
	}
	public static String date2String(Date date) throws Exception {
		return (new SimpleDateFormat("yyyyMMdd")).format(date);
	}

	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(5, -1);
		cal.set(7, 2);
		return cal.getTime();
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
