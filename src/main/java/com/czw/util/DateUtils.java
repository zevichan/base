package com.czw.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @author Zevi Chan
 * @Date 2016-07-20 14:45:02
 */
public class DateUtils {
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	
	
	
	public static void main(String[] args) {
		String d = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		System.out.println(d);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

}
