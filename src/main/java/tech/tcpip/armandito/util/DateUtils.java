package tech.tcpip.armandito.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Slf4j
public class DateUtils {

    /**
     * America/Mexico_City timezone
     */
    public static final String MX_TIME_ZONE = "America/Mexico_City";

    /**
     * MX locale - all HTTP dates are in english
     */
    public final static Locale LOCALE_MX = new Locale("es", "MX");

    /**
     * all HTTP dates are on America/Mexico_City timezone
     */
    public final static TimeZone AMERICA_MX_TIME_ZONE = TimeZone.getTimeZone(MX_TIME_ZONE);

    private DateUtils() {
        log.info("Global Parameters: \n TIME_ZONE={}\n LOCALE={}\n ", AMERICA_MX_TIME_ZONE, LOCALE_MX);
    }

    public static Timestamp getDefaultTimestamp() {
        Clock clock = Clock.system(ZoneId.of(MX_TIME_ZONE));
        return Timestamp.from(Instant.now(clock));
    }

    public static LocalDate getDefaultLocalDate() {
        Clock clock = Clock.system(ZoneId.of(MX_TIME_ZONE));
        return LocalDate.now(clock);
    }

    public static java.sql.Date getDefaultSqlDate() {
        LocalDate lt = getDefaultLocalDate();
        return java.sql.Date.valueOf(lt);
    }

    public static LocalDate getLocalDateFromDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.of(MX_TIME_ZONE)).toLocalDate();
    }

    public static java.sql.Date getSqlDateFromDate(final Date date) {
        LocalDate lt = getLocalDateFromDate(date);
        return java.sql.Date.valueOf(lt);
    }

    public static java.sql.Date getSqlDateFromDate(final String date) {
        LocalDate lt = getLocalDateFromISOLocalDateFormat(date);
        return java.sql.Date.valueOf(lt);
    }

    public static LocalDate getLocalDateFromISOLocalDateFormat(final String date) {
        LocalDate lt = null;
        if (date != null) {
            try {
                DateTimeFormatter formatter =
                        DateTimeFormatter.ISO_LOCAL_DATE;
                lt = LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                log.error("Invalid ISO format date", e.getCause());
                throw e;
            }
        }
        return lt;
    }

    public static Instant getDefaultInstant() {
        Clock clock = Clock.system(ZoneId.of(MX_TIME_ZONE));
        return Instant.now(clock);
    }

    public static Clock getDefaultClock() {
        return Clock.system(ZoneId.of(MX_TIME_ZONE));
    }

    /**
     * Convert a sql timestamp to string date with ISO format 'yyyy-MM-dd'
     *
     * @param sqlTimestamp
     * @return
     */
    public static String getStringDateFromTimestamp(Timestamp sqlTimestamp) {
        LocalDateTime ldt = sqlTimestamp.toLocalDateTime();
        LocalDate localDate = ldt.toLocalDate();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String date = localDate.format(formatter);
        return date;
    }


    /**
     * Convert a sql timestamp to string date with ISO format 'yyyy-MM-dd'
     *
     * @param sqlTimestamp
     * @return
     */
    public static java.util.Date getDateFromTimestamp(Timestamp sqlTimestamp) {
        LocalDateTime ldt = sqlTimestamp.toLocalDateTime();
       return getDateFromLocalDate(ldt.toLocalDate());
    }

    public static Date getDateFromLocalDate(LocalDate ld) {
        Date date = null;
        if (ld != null)
            date = Date.from(ld.atStartOfDay(ZoneId.of(MX_TIME_ZONE)).toInstant());
        return date;
    }

    public static String convertToJson(final Object object) {
        try {
            log.debug("Starting convertToJson... value: {}", object.getClass().getName());
            final ObjectMapper mapper = new ObjectMapper();
            final String json = mapper.writeValueAsString(object);
            return json;
        } catch (final JsonProcessingException e) {
            log.error("Error when triying to convert the objet to json string with cause:{} and message: {}",
                    e.getLocalizedMessage(), e.getMessage());
        }
        return null;
    }

    public static <T> T convertJsonToClass(final String jsonValue, final Class<T> returnType) {
        try {
            log.debug("Start convertJsonToClass...");
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonValue, returnType);
        } catch (final IOException e) {
            log.error("Error when triying to parse string json to object, with cause:{} and message: {}",
                    e.getLocalizedMessage(), e.getMessage());
        }
        return null;
    }

}
