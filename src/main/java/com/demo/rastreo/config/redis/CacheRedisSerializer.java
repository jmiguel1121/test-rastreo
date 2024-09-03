package com.demo.rastreo.config.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.util.*;

public class CacheRedisSerializer<T> extends Jackson2JsonRedisSerializer<T> {

    private static final String TYPE = "class";
    private static final String VALUE = "value";
    private final JavaType javaType;
    private ObjectMapper mapper;

    public CacheRedisSerializer(ObjectMapper mapper, Class<T> type) {
        super(mapper, type);
        this.mapper = mapper;
        this.javaType = getJavaType(type);
    }

    @Override
    public byte[] serialize(@Nullable T value) throws SerializationException {
        if (value == null) {
            return new byte[0];
        }
        try {
            var res = serializeMap(value);
            return res.getBytes();
        } catch (Exception ex) {
            throw new SerializationException("Could not write JSON: " + ex.getMessage(), ex);
        }
    }

    @Override
    public T deserialize(@Nullable byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)) {
            return null;
        }
        try {
            String objStr = new String(bytes);
            var obj = this.mapper.readValue(objStr, Object.class);
            var res = deserializeMap(obj);
            return (T) res;
        } catch (Exception ex) {
            throw new SerializationException("Could not read JSON: " + ex.getMessage(), ex);
        }
    }

    private <T> String serializeMap(T value) throws IOException {
        if (value instanceof Collection collection) {
            List<Map<String, String>> listObj = new ArrayList<>();
            for (var item : collection) {
                var map = serializeMapObj(item);
                listObj.add(map);
            }
            return this.mapper.writeValueAsString(listObj);
        } else {
            var obj = serializeMapObj(value);
            return this.mapper.writeValueAsString(obj);
        }
    }

    private <T> Map<String, String> serializeMapObj(T value) throws JsonProcessingException {
        Class<?> clazz = value.getClass();
        Map<String, String> map = new HashMap<>();
        map.put(TYPE, clazz.getName());
        map.put(VALUE, this.mapper.writeValueAsString(value));
        return map;
    }


    private Object deserializeMap(@Nullable Object rdsObj) throws IOException, ClassNotFoundException {
        if (rdsObj instanceof Collection collection) {
            Collection<Object> listObj = new ArrayList<>();
            for (var item : collection) {
                var obj = deserializeMap(item);
                listObj.add(obj);
            }
            return listObj;
        } else if (rdsObj instanceof Map map) {
            return deserializeMapObj(map);
        }
        return null;
    }

    private Object deserializeMapObj(@Nullable Map<String, String> map) throws IOException, ClassNotFoundException {
        String className = map.get(TYPE).toString();
        Class<?> clazz = Class.forName(className);
        var obj = map.get(VALUE);
        var val = this.mapper.readValue(obj, clazz);
        return val;
    }


    static boolean isEmpty(@Nullable byte[] data) {
        return (data == null || data.length == 0);
    }
}
