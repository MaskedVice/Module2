package com.scripfinder.module2.dao;

import com.scripfinder.module2.dto.Candle;

public interface ScripDayDataDAO {
    String save(String scripName, Candle candle);
}
