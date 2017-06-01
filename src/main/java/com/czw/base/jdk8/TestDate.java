package com.czw.base.jdk8;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * @author ZeviChen , 2017/6/1 08:59
 */
public class TestDate {


    @Test
    public void t1() {
        LocalDate today = LocalDate.now();
        System.out.println("today : "+today.toString());
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        System.out.println("tomorrow : "+tomorrow.toString());
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println("yesterday : "+yesterday);

        LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
        DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
        System.out.println(dayOfWeek);    // FRIDAY

    }
}
