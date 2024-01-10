package com.scripfinder.module2.dao;

import java.util.List;

import com.scripfinder.module2.dto.Candle;

public interface MonthDataDAO {

    String save(String scripName, List<Candle> candles);

}
