package com.example.demo.utils;

import com.example.demo.exceptions.LinkServiceException;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public final class SerializingUtils {
    public static <T> void serializeStructure(String serializationFileName, T structure) throws LinkServiceException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        File destFile = new File(serializationFileName);
        try {
            objectMapper.writeValue(destFile, structure);
        } catch (IOException ex) {
            throw new LinkServiceException("Error while serializing " + structure.toString() + "\n", ex);
        }
    }

    public static <T> T deserializeStructure(String serializationFileName, Class<T> structureClass) throws LinkServiceException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        File srcFile = new File(serializationFileName);

        try {
            return objectMapper.readValue(srcFile, structureClass);
        } catch (IOException ex) {
            throw new LinkServiceException("Error while deserializing " + structureClass.getName() + "\n", ex);
        }
    }
}
