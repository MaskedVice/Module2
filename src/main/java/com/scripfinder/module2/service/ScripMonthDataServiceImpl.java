package com.scripfinder.module2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.scripfinder.module2.dao.ScripMonthDataDAO;
import com.scripfinder.module2.dto.Candle;


@Service
public class ScripMonthDataServiceImpl implements ScripMonthDataService {
    @Autowired
    private ScripMonthDataDAO scripMonthDataDAO;

    @Override
    @Transactional
    public String save(String scripName, List<Candle> candles) {
        return scripMonthDataDAO.save(scripName, candles);
    }
    
}
