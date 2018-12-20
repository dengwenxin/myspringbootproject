package com.utils;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Json数据处理工具类
 */
public class JsonUtils {

    // 初始化ObjectMapper，由于ObjectMapper线程安全，可以在不同线程复用
    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        // 允许序列化空的POJO类
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 在遇到未知属性的时候不抛出异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * 将对象转换为json
     */
    public static String objectToJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * 将json转换为对象
     */
    public static <T> T jsonToObject(String json, Class<T> clz) throws IOException {
        return objectMapper.readValue(json, clz);
    }

    /**
     * 将map转换为json
     */
    public static String mapToJson(Map<String, Object> map) throws JsonProcessingException {
        return objectMapper.writeValueAsString(map);
    }

    /**
     * 将json转换为map
     */
    public static Map<String, String> jsonToMap(String json) throws IOException {
        return objectMapper.readValue(json, new TypeReference<Map<String, String>>() {
        });
    }
}