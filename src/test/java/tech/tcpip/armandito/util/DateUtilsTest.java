package tech.tcpip.armandito.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.mockito.Mockito.*;

class DateUtilsTest {
    @Mock
    TimeZone AMERICA_MX_TIME_ZONE;
    @InjectMocks
    DateUtils dateUtils;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetDefaultTimestamp() {
        Timestamp result = DateUtils.getDefaultTimestamp();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetDefaultLocalDate() {
        LocalDate result = DateUtils.getDefaultLocalDate();
        Assertions.assertEquals(LocalDate.of(2018, Month.FEBRUARY, 19), result);
    }

    @Test
    void testGetDefaultSqlDate() {
        Date result = DateUtils.getDefaultSqlDate();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetLocalDateFromDate() {
        LocalDate result = DateUtils.getLocalDateFromDate(new GregorianCalendar(2018, Calendar.FEBRUARY, 19, 11, 15).getTime());
        Assertions.assertEquals(LocalDate.of(2018, Month.FEBRUARY, 19), result);
    }

    @Test
    void testGetSqlDateFromDate() {
        Date result = DateUtils.getSqlDateFromDate(new GregorianCalendar(2018, Calendar.FEBRUARY, 19, 11, 15).getTime());
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetSqlDateFromDate2() {
        Date result = DateUtils.getSqlDateFromDate("date");
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetLocalDateFromISOLocalDateFormat() {
        LocalDate result = DateUtils.getLocalDateFromISOLocalDateFormat("date");
        Assertions.assertEquals(LocalDate.of(2018, Month.FEBRUARY, 19), result);
    }

    @Test
    void testGetDefaultInstant() {
        Instant result = DateUtils.getDefaultInstant();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetDefaultClock() {
        Clock result = DateUtils.getDefaultClock();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetStringDateFromTimestamp() {
        String result = DateUtils.getStringDateFromTimestamp(null);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetDateFromTimestamp() {
        java.util.Date result = DateUtils.getDateFromTimestamp(null);
        Assertions.assertEquals(new GregorianCalendar(2018, Calendar.FEBRUARY, 19, 11, 15).getTime(), result);
    }

    @Test
    void testGetDateFromLocalDate() {
        java.util.Date result = DateUtils.getDateFromLocalDate(LocalDate.of(2018, Month.FEBRUARY, 19));
        Assertions.assertEquals(new GregorianCalendar(2018, Calendar.FEBRUARY, 19, 11, 15).getTime(), result);
    }

    @Test
    void testConvertToJson() {
        String result = DateUtils.convertToJson(null);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testConvertJsonToClass() {
        T result = DateUtils.convertJsonToClass("jsonValue", null);
        Assertions.assertEquals(new T(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme