package com.example.cafe.handlingException;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Map;

@Component
public class CustomErrorAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errors = super.getErrorAttributes(webRequest, options);
        errors.put("success", Boolean.FALSE);
        errors.put("status", errors.get("error"));
        errors.put("exception", errors.get("message"));
        errors.put("details", Arrays.asList(errors.get("message")));
        return errors;
    }
}