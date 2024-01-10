package com.scripfinder.module2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scripfinder.module2.dto.CurrentDataRequest;
import com.scripfinder.module2.dto.MonthRequestData;
import com.scripfinder.module2.dto.ResponseObject;
import com.scripfinder.module2.service.CurrentDataService;
import com.scripfinder.module2.service.MonthDataService;




@RestController
@RequestMapping("/save")
public class DataController {

    @Autowired(required = true)
    private MonthDataService scripMonthDataService;

    @Autowired(required = true)
    private CurrentDataService currentDataService;
    
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
    @PostMapping(value = "/saveCurrentData")
    public ResponseObject<String> saveDayData(@RequestBody CurrentDataRequest requestData) {
        ResponseObject<String> responseEntity = new ResponseObject<>();
        try{
            responseEntity.setResponseResult(currentDataService.save(requestData.getData()));
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
