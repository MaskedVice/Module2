package com.scripfinder.module2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scripfinder.module2.dao.ScripDayDataDAO;
import com.scripfinder.module2.dto.Candle;
@Service
public class ScripDayDataServiceImpl implements ScripDayDataService {
    @Autowired
    private ScripDayDataDAO scripDayDataDAO;


    @Override
    public String save(String scripName, Candle candles) {
        return scripDayDataDAO.save(scripName, candles);
    }
    
}
