package com.automation.libraries.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Provides reusable methods for sending GET, POST, PUT, DELETE requests.
 * Handles query parameters, headers, authentication, and request logging.
 */


    public class RequestHelper {
        private static final ObjectMapper objectMapper = new ObjectMapper();

        public static String toJson(Object object) {
            try {
                return objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error converting object to JSON", e);
            }
        }
    }
