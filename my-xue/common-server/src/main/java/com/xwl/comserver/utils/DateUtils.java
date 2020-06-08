package com.xwl.comserver.utils;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @Auther: 薛
 * @Date: 2020/6/8 17:04
 * @Description:
 * 一个ThreadLocal 用于多线程当中使用 每一个使用一次释放之后再去创建实例 保证时间准确性以及数据隔离性
 */
@Component
public class DateUtils {

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    private static ThreadLocal<DateFormat> threadLocalTwo = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    private static ThreadLocal<DateFormat> threadLocalThree = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };

    /**
     * 当天 格式 YYYY-MM-dd
     * @return
     */
    public static DateFormat getTodayTime(){
       return threadLocalTwo.get();
    } /**
     * 当天 格式 YYYY-MM-dd
     * @return
     */
    public static DateFormat getTodayNowTime(){
       return threadLocal.get();
    } /**
     * 当天 格式 YYYY-MM-dd
     * @return
     */
    public static DateFormat getHms(){
       return threadLocalThree.get();
    }
}
