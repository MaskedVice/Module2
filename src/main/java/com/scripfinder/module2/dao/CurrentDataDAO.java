package com.scripfinder.module2.dao;

import java.util.Map;
import com.scripfinder.module2.dto.InstrumentData;

public interface CurrentDataDAO {
    String save(Map<String, InstrumentData> data);
}
