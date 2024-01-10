package com.scripfinder.module2.service;

import java.util.List;

import com.scripfinder.module2.dto.Candle;

public interface MonthDataService {

    String save(String scripName,List<Candle> candles);

}
