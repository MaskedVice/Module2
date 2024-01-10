package com.scripfinder.module2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.scripfinder.module2.dao.MonthDataDAO;
import com.scripfinder.module2.dto.Candle;


@Service
public class MonthDataServiceImpl implements MonthDataService {
    @Autowired
    private MonthDataDAO scripMonthDataDAO;

    @Override
    @Transactional(value = "mainTransactionManager")
    public String save(String scripName, List<Candle> candles) {
        return scripMonthDataDAO.save(scripName, candles);
    }
    
}
