package com.coalsal.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenxin on 2017/5/9 0009.
 * 时间操作
 */
public class DateUtils {

    /**
     *  yyyyMMddHHmmss->
     * yyyy-MM-dd HH:mm:ss
     * 2016-08-29 14:33:20
     */
	
	public static String getDateTimeStrFmt(String dateTimeStr){
		 SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmss");
		 Date d=null;
		try {
			d = f.parse(dateTimeStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 SimpleDateFormat fn=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String res=fn.format(d);
		 
		return res;
	}
	public static String getDateTimeStr(Date day){
		 SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmss");
		 
		 
		 String res=f.format(day);
		 
		return res;
	}

    /**
     * 根据字符串和格式，返回时间对象
     * @param dateStr
     * @param dateFormat
     * @return
     */
    public static Date getDateByFormat(String dateStr, String dateFormat){
        SimpleDateFormat simpleDate = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = simpleDate.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 根据指定格式，返回更改后的时间
     * @param date
     * @param dateFormat yyyy-MM-dd 00:00:00
     * @return
     */
    public static Date getDateByFormat2(Date date, String dateFormat){
        SimpleDateFormat simpleDate = new SimpleDateFormat(dateFormat);
        String dateStr = simpleDate.format(date);
        return getDateByFormat(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据指定格式，返回时间字符串
     * @param date
     * @param dateFormat
     * @return
     */
    public static String getDateByFormat(Date date, String dateFormat){
        SimpleDateFormat simpleDate = new SimpleDateFormat(dateFormat);
        return simpleDate.format(date);
    }

    /**
     * 加减时间
     * @param date 需要加减的时间
     * @param cal 1为秒，2为分，3为小时，4为天，5为月，6为年
     * @param num 加减的数量
     * @return 加减后的时间
     */
    public static Date addDate(Date date, Integer cal, Integer num){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        if(cal == 1){
            //秒
            rightNow.add(Calendar.SECOND, num);
        }else if(cal == 2){
            //分
            rightNow.add(Calendar.MINUTE, num);
        }else if(cal == 3){
            //小时
            rightNow.add(Calendar.HOUR, num);
        }else if(cal == 4){
            //天
            rightNow.add(Calendar.DATE, num);
        }else if(cal == 5){
            //月
            rightNow.add(Calendar.MONTH, num);
        }else if(cal == 6){
            //年
            rightNow.add(Calendar.YEAR, num);
        }
        return rightNow.getTime();
    }

    /**
     * 当前时间的 10位时间戳
     * @return
     */
    public static Long getDateLong(){
        Date date = new Date();
        return getDateLong(date);
    }

    /**
     * 获取该时间的10位时间戳
     * @param date 时间
     * @return 时间戳
     */
    public static Long getDateLong(Date date){
        return date.getTime() / 1000;
    }

    /**
     * 根据date 获取周几
     * @param date 时间
     * @return 周几
     */
    public static Integer getDateWday(Date date){
        Integer wday = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //if(Locale.getDefault() == Locale.ENGLISH){
        wday = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(wday == 0){
            wday = 7;
        }
        //}else{
        //	wday = cal.get(Calendar.DAY_OF_WEEK);
        //}
        return wday;
    }

    /**
     * 根据date 获取几号
     * @param date
     * @return
     */
    public static Integer getDateDate(Date date){
        Integer dateNum = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        dateNum = cal.get(Calendar.DATE);
        return dateNum;
    }

}
