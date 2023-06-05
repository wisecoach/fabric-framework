package com.wisecoach.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(LocalDateTime date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//        String formattedDate = dateFormat.format(date);
        jsonGenerator.writeString(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(date));
    }
}
