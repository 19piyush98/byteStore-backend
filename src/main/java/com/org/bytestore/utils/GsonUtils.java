package com.org.bytestore.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Type;
import java.util.List;

@UtilityClass
public class GsonUtils {

    private final Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> List<T> fromJsonList(String json, Class<T> classOfT) {
        Type listType = new TypeToken<List<T>>() {}.getType();
        return gson.fromJson(json, listType);
    }

    public static <T> String toJson(T object) {
        return gson.toJson(object);
    }

    public static String toJson(List<?> list) {
        return gson.toJson(list);
    }
}
