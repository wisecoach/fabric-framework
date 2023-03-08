package com.wisecoach.util;

import com.owlike.genson.Genson;

/**
 * {@code @author:} wisecoach
 * {@code @date:} 2023/3/8 上午10:01
 * {@code @version:} 1.0.0
 */


public class JSONUtils {


    private static final Genson genson = new Genson();

    public static String serialize(Object obj) {
        if (obj == null) {
            return "";
        } else if (obj.getClass().isPrimitive()) {
            return String.valueOf(obj);
        } else if (obj instanceof String) {
            return (String) obj;
        } else {
            return genson.serialize(obj);
        }
    }

    public static  <T> T deserialize(byte[] json, Class<T> clazz) {
        return genson.deserialize(json, clazz);
    }

    public static  <T> T deserialize(String json, Class<T> clazz) {
        return genson.deserialize(json, clazz);
    }

}
