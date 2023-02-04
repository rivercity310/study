package com.example.springwebsocket;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private Utils() { }

    /* readValue: JSON -> Java Object (Deserialization) */
    public static Message getObject(final String message) throws Exception {
        return objectMapper.readValue(message, Message.class);
    }

    /* writeValue: Java Object -> JSON (Serialization) */
    public static String getString(final Message message) throws Exception {
        return objectMapper.writeValueAsString(message);
    }
}
