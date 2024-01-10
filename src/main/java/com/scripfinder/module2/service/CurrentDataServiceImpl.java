package com.scripfinder.module2.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scripfinder.module2.dao.CurrentDataDAO;
import com.scripfinder.module2.dto.InstrumentData;
@Service
public class CurrentDataServiceImpl implements CurrentDataService {
    @Autowired
    CurrentDataDAO currentDataDAO;


    @Override
    @Transactional(value = "mainTransactionManager")
    public String save(Map<String, InstrumentData> data) {
        return currentDataDAO.save(data);
    }


    
}
