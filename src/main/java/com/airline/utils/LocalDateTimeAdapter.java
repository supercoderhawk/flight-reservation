package com.airline.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by airline on 2017/5/15.
 * Gson日期时间序列化类
 */
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>,JsonDeserializer<LocalDateTime> {
  private static DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(date.format(formatter));
  }

  @Override
  public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    return LocalDateTime.from(formatter.parse(jsonElement.getAsString()));
  }
}
