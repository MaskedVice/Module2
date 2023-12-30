package com.scripfinder.module2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.scripfinder.module2.dto.Candle;
import com.scripfinder.module2.dto.DayRequestData;
import com.scripfinder.module2.dto.MonthRequestData;
import com.scripfinder.module2.dto.ResponseObject;
import com.scripfinder.module2.service.ScripDayDataService;
import com.scripfinder.module2.service.ScripMonthDataService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/save")
public class ScripDataController {

    @Autowired(required = true)
    private ScripMonthDataService scripMonthDataService;

    @Autowired(required = true)
    private ScripDayDataService scripDayDataService;
    
    @ResponseBody
    @PostMapping(value = "/saveMonthData")
    public ResponseObject<String> saveMonthData(@RequestBody MonthRequestData requestData) {
        ResponseObject<String> responseEntity = new ResponseObject<>();
        try{
            responseEntity.setResponseResult(scripMonthDataService.save(requestData.getScripName(), requestData.getCandles()));
            responseEntity.setResponseType(true);
            responseEntity.setResponseCount(1);
            responseEntity.setResponseCode(200);
            
            return responseEntity;
        }catch(Exception e){
            
            responseEntity.setResponseType(false);
            responseEntity.setResponseResult(e.getMessage());
            responseEntity.setResponseCount(0);
            responseEntity.setResponseCode(400);
            return responseEntity;
        }
    }

    @ResponseBody
    @PostMapping(value = "/saveDayData")
    public ResponseObject<String> saveDayData(@RequestBody DayRequestData requestData) {
        ResponseObject<String> responseEntity = new ResponseObject<>();
        try{
            responseEntity.setResponseResult(scripDayDataService.save(requestData.getScripName(), requestData.getCandle()));
            responseEntity.setResponseType(true);
            responseEntity.setResponseCount(1);
            responseEntity.setResponseCode(200);
            
            return responseEntity;
        }catch(Exception e){
            
            responseEntity.setResponseType(false);
            responseEntity.setResponseResult(e.getMessage());
            responseEntity.setResponseCount(0);
            responseEntity.setResponseCode(400);
            return responseEntity;
        }
    }

    @ResponseBody
    @GetMapping("/getHello")
    public String getMethodName() {
        return "HelloWorld";
    }
    
    
}
