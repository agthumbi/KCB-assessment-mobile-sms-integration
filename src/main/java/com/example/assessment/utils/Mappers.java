package com.example.assessment.utils;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class Mappers<T> {

    public Object toXmlObject(String jsonString, Class<T> objClass) {
        XmlMapper xmlMapper = new XmlMapper();

        try {
            xmlMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return xmlMapper.readValue(jsonString, objClass);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Object toJsonObject(String jsonString, Class<T> objClass) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonString, objClass);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Map<String, String> objectToMap(Object jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            String jsonStr=objectMapper.writeValueAsString(jsonObject);
           return  new ObjectMapper().readValue(jsonStr, HashMap.class);
            //return objectMapper.writeValueAsString(jsonObject);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public Map<String, String> jsonToMap(String jsonStr) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            return  new ObjectMapper().readValue(jsonStr, HashMap.class);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }




    public String objectToJson(Object jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //objectMapper.configure(DeserializationFeature)
            //  objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true)
            return objectMapper.writeValueAsString(jsonObject);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String objectToXML(Object xmlObject) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            // xmlMapper.setSerializerFactory(serializerFactory);
            return xmlMapper.writeValueAsString(xmlObject);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }


    }
}