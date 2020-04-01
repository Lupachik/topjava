package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalTime;



public final class DataTimeConverterUtil {
    public final static class LocalDatesConverter implements Converter<String, LocalDate> {

        @Override
        public LocalDate convert(String source) {
            return DateTimeUtil.parseLocalDate(source);
        }
    }

    public final static class LocalTimeConverter implements Converter<String, LocalTime>{

        @Override
        public LocalTime convert(String source) {
            return DateTimeUtil.parseLocalTime(source);
        }
    }
}
