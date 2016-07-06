/**
 * Copyright 2014 Zhenguo Jin
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sythealth.library.common.tools;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 *
 * @author jingle1267@163.com
 */
@SuppressLint("SimpleDateFormat")
public class DateUtils {

    /**
     * 日期类型 *
     */
    public static final String MMddhhss = "MM-dd HH:mm";
    public static final String yyyyMMDD = "yyyy-MM-dd";
    public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String HHmmss = "HH:mm:ss";
    public static final String hhmmss = "HH:mm:ss";
    public static final String LOCALE_DATE_FORMAT = "yyyy年M月d日 HH:mm:ss";
    public static final String DB_DATA_FORMAT = "yyyy-MM-DD HH:mm:ss";
    public static final String NEWS_ITEM_DATE_FORMAT = "hh:mm M月d日 yyyy";


    public static String dateToString(Date date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).format(date);
        } catch (Exception e) {
            return "";
        }

    }

    public static Date stringToDate(String dateStr, String pattern)
            throws Exception {
        return new SimpleDateFormat(pattern).parse(dateStr);
    }

    /**
     * 判断是否同一天
     * @param dateA
     * @param dateB
     * @return
     */
    public static boolean areSameDay(Date dateA, Date dateB) {
        Calendar calDateA = Calendar.getInstance();
        calDateA.setTime(dateA);

        Calendar calDateB = Calendar.getInstance();
        calDateB.setTime(dateB);

        return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
                && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
                && calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前日期
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDate() {
        try {
            return dateToString(new Date(), yyyyMMDD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取当前日期后的一周日期
     *
     * @return
     */
    public static List<Date> getWeekByNowDate() {
        Date date = DateUtils.parseDate(getCurrentDate(), yyyyMMDD);
        List<Date> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            list.add(getDateBefore(date, i));
        }
        return list;
    }

    /**
     * 获取date之后一周的日期
     *
     * @param date
     * @return
     */
    public static List<Date> getNextWeekByDate(Date date) {
        List<Date> list = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            list.add(getDateBefore(date, i));
        }
        return list;
    }

    /**
     * 获取date之前一周的日期
     *
     * @param date
     * @return
     */
    public static List<Date> getLastWeekByDate(Date date) {
        List<Date> list = new ArrayList<>();
        for (int i = -7; i < 0; i++) {
            list.add(getDateBefore(date, i));
        }
        return list;
    }

    /**
     * @param date
     * @param before
     * @return
     */
    public static Date getDateBefore(Date date, int before) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, before);
        return calendar.getTime();
    }

    /**
     * 获得指定日期所在月的第几天
     *
     * @param date
     * @return int
     */
    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 两小时内 返回文字形式 如： 2秒前
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String ago(String time) {
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sf1 = new SimpleDateFormat("HH:mm");
            SimpleDateFormat sf2 = new SimpleDateFormat("MM-dd HH:mm");
            SimpleDateFormat sf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            SimpleDateFormat sf4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sf4.parse(time);
            Long sec = 1000l;
            Long min = sec * 60;
            Long hor = min * 60;
            int courrentYear = cal.get(Calendar.YEAR);
            int courrentDay = cal.get(Calendar.DAY_OF_YEAR);
            Long currentTime = cal.getTime().getTime();
            Long dateTime = date.getTime();
            Long bettwen = currentTime - dateTime;
            // //////
            cal.setTime(date);
            int dateYear = cal.get(Calendar.YEAR);
            int dateDay = cal.get(Calendar.DAY_OF_YEAR);
            if (bettwen < sec) {
                return "刚刚";
            } else if (bettwen < min) {
                return (bettwen / sec) + "秒前";
            } else if (bettwen < hor) {
                return (bettwen / min) + "分钟前";
            } else if (bettwen < (hor * 3)) {
                return (bettwen / hor) + "小时前";
            } else if (courrentYear == dateYear) {
                if (dateDay == courrentDay) {
                    return "今天 " + sf1.format(date);
                } else if ((courrentDay - dateDay) == 1) {
                    return "昨天 " + sf1.format(date);
                } else {
                    return sf2.format(date);
                }
            }
            return sf3.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 将Date类型转换为日期字符串
     *
     * @param date Date对象
     * @param type 需要的日期格式
     * @return 按照需求格式的日期字符串
     */
    public static String formatDate(Date date, String type) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(type);
            return df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将日期字符串转换为Date类型
     *
     * @param dateStr 日期字符串
     * @param type    日期字符串格式
     * @return Date对象
     */
    public static Date parseDate(String dateStr, String type) {
        if (TextUtils.isEmpty(dateStr))
            return null;
        SimpleDateFormat df = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = df.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    /**
     * 得到年
     *
     * @param date Date对象
     * @return 年
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 得到月
     *
     * @param date Date对象
     * @return 月
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;

    }

    /**
     * 得到日
     *
     * @param date Date对象
     * @return 日
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 转换日期 将日期转为今天, 昨天, 前天, XXXX-XX-XX, ...
     *
     * @param time 时间
     * @return 当前日期转换为更容易理解的方式
     */
    public static String translateDate(Long time) {
        long oneDay = 24 * 60 * 60 * 1000;
        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();    //今天

        today.set(Calendar.YEAR, current.get(Calendar.YEAR));
        today.set(Calendar.MONTH, current.get(Calendar.MONTH));
        today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        long todayStartTime = today.getTimeInMillis();

        if (time >= todayStartTime && time < todayStartTime + oneDay) { // today
            return "今天";
        } else if (time >= todayStartTime - oneDay && time < todayStartTime) { // yesterday
            return "昨天";
        } else if (time >= todayStartTime - oneDay * 2 && time < todayStartTime - oneDay) { // the day before yesterday
            return "前天";
        } else if (time > todayStartTime + oneDay) { // future
            return "将来某一天";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(time);
            return dateFormat.format(date);
        }
    }

}
