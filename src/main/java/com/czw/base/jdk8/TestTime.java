package com.czw.base.jdk8;

import org.testng.annotations.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zevi on 2017/7/1.
 */
public class TestTime {

    @Test
    public void test() {
        // Get the system clock as UTC offset
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        System.out.println("-----------------------------");

        // Get the local date and local time
        final LocalDate date = LocalDate.now();
        final LocalDate dateFromClock = LocalDate.now(clock);

        System.out.println(date);
        System.out.println(dateFromClock);

        // Get the local date and local time
        final LocalTime time = LocalTime.now();
        final LocalTime timeFromClock = LocalTime.now(clock);

        System.out.println(time);
        System.out.println(timeFromClock);

        System.out.println("-----------------------------");

        // Get the local date/time
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);

        System.out.println(datetime);
        System.out.println(datetimeFromClock);
        System.out.println("-----------------------------");

        // Get the zoned date/time
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

        System.out.println(zonedDatetime);
        System.out.println(zonedDatetimeFromClock);
        System.out.println(zonedDatetimeFromZone);

        System.out.println("-----------------------------");

        // Get duration between two dates
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);

        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());

        System.out.println("-----------------------------");

        Year year = Year.now();
        Year year1 = Year.now(clock);
        Month month = Month.of(1);
        YearMonth ym = YearMonth.now();
        MonthDay monthDay = MonthDay.now();
        System.out.println(year);
        System.out.println(year1);
        System.out.println(month);
        System.out.println(ym);
        System.out.println(monthDay);

        System.out.println("-----------------------------");

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
        System.out.println("date formatter :" + dateTimeFormatter.format(localDateTime));

        //  解析字符串形式的日期时间
        TemporalAccessor temporalAccessor = dateTimeFormatter.parse("2017 01 01 10 10 10");
        System.out.println("temporalAccessor :" + LocalDate.from(temporalAccessor));

        Instant instant = Instant.now(); //  时间戳
        System.out.println("instant :" + instant);


    }

}
