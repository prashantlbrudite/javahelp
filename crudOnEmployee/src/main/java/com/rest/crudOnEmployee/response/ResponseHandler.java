package com.rest.crudOnEmployee.response;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
     public static ResponseEntity<Object> responseBuilder(String message, HttpStatus httpStatus, Object responseObject,String errors ){
         Map<String , Object> response = new HashMap<>();
         response.put("message", message);
         response.put("httpStatus",httpStatus );
         response.put("data", responseObject);
         response.put("error", errors);
         return new ResponseEntity<>(response,httpStatus);
     }
}
