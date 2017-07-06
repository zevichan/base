package com.czw.base.jdk8;

import org.testng.annotations.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

/**
 *
 * ISO8601 : https://en.wikipedia.org/wiki/ISO_8601
 * RFC 3339 : http://www.ietf.org/rfc/rfc3339.txt
 *
 * REF: http://blog.csdn.net/lonely_fireworks/article/details/7962171/
 * REF: http://blog.csdn.net/zzq900503/article/details/38925225
 *
 * Created by zevi on 2017/7/1.
 */
public class TestTime {

    @Test
    public void test() throws ParseException {

        // Get the system clock as UTC offset
        System.out.println("-----------------------------Clock");
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        System.out.println("-----------------------------LocalDate and LocalTime");

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

        System.out.println("-----------------------------LocalDateTime");

        // Get the local date/time
        final LocalDateTime datetime = LocalDateTime.now();
        final LocalDateTime datetimeFromClock = LocalDateTime.now(clock);

        System.out.println(datetime);
        System.out.println(datetimeFromClock);
        System.out.println("-----------------------------ZonedDateTime");

        // Get the zoned date/time
        final ZonedDateTime zonedDatetime = ZonedDateTime.now();
        final ZonedDateTime zonedDatetimeFromClock = ZonedDateTime.now(clock);
        final ZonedDateTime zonedDatetimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));

        System.out.println(zonedDatetime);
        System.out.println(zonedDatetimeFromClock);
        System.out.println(zonedDatetimeFromZone);

        System.out.println("-----------------------------LocalDateTime and Duration");

        // Get duration between two dates
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        final LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);

        final Duration duration = Duration.between(from, to);
        System.out.println("Duration in days: " + duration.toDays());
        System.out.println("Duration in hours: " + duration.toHours());

        System.out.println("-----------------------------Year and Month YearMonth and MonthDay");

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

        System.out.println("-----------------------------LocalDateTime and DateTimeFormatter and TemporalAccessor and Instant");

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd HH mm ss");
        System.out.println("date formatter :" + dateTimeFormatter.format(localDateTime));

        //  解析字符串形式的日期时间
        TemporalAccessor temporalAccessor = dateTimeFormatter.parse("2017 01 01 10 10 10");
        System.out.println("temporalAccessor :" + LocalDate.from(temporalAccessor));

        Instant instant = Instant.now(); //  时间戳
        System.out.println("instant :" + instant);

        System.out.println("-----------------------------DateTimeFormatter and ChronoField");
        String HTTP_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss zzz";
        String ifModifiedSince = "Thu, 06 Jul 2017 01:02:23 GMT";
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern(HTTP_DATE_FORMAT, Locale.US);
        LocalDateTime localDateTime1 = LocalDateTime.parse(ifModifiedSince, dateTimeFormatter1);
        long ifModifiedSinceDateSeconds = Date.from(localDateTime1.toInstant(ZoneOffset.UTC)).getTime() / 1000;
        System.out.println("ifModifiedSinceDateSeconds: " + ifModifiedSinceDateSeconds);

        SimpleDateFormat dateFormatter = new SimpleDateFormat(HTTP_DATE_FORMAT, Locale.US);
        Date ifModifiedSinceDate = dateFormatter.parse(ifModifiedSince);
        long ifModifiedSinceDateSeconds2 = ifModifiedSinceDate.getTime() / 1000;

        System.out.println("ifModifiedSinceDateSeconds2: " + ifModifiedSinceDateSeconds2);

        System.out.println("-----------------------------DateTimeFormatter");
        //使用Locale.US格式,时区+8即北京时间,当前子午线在英国格林尼治(GMT),然而实际上在麦加(世界标准时间UTC,暂时等于GMT,也许那天改过来了)
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern(HTTP_DATE_FORMAT, Locale.US);
        System.out.println("current us datetime: " + dateTimeFormatter2.format(ZonedDateTime.now(ZoneOffset.of("+8"))));

        long currentDateLong = new Date().getTime();
        String HTTP_DATE_FORMAT1 = "EEE, dd MMM yyyy HH:mm:ss Z";
        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern(HTTP_DATE_FORMAT1, Locale.US);
        System.out.println("current utc datetime: " + Instant.ofEpochMilli(currentDateLong)
                .atZone(ZoneOffset.UTC).format(dateTimeFormatter3));


    }

}
