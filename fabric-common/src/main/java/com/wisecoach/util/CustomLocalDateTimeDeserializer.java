package com.wisecoach.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String dateStr = jsonParser.getText();
        return this.getLocalDateTimeBySource(dateStr);
    }

    Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    Pattern DATETIME_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");

    /**
     * 将页面传入的原日期字符串进行转换
     */
    private LocalDateTime getLocalDateTimeBySource(String source) {
        if (source == null || source.trim().length() == 0) {
            return null;
        }
        source = source.trim();

        if (!DATE_PATTERN.matcher(source).matches() && !DATETIME_PATTERN.matcher(source).matches()) {
            throw new RuntimeException("日期参数格式错误: " + source);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        if (DATE_PATTERN.matcher(source).matches()) {
            source += " 23:50:00";
        }
        LocalDateTime parse = LocalDateTime.parse(source, formatter);
        return parse;
    }
}
