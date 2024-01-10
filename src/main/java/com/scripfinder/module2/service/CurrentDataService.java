package com.scripfinder.module2.service;

import java.util.Map;
import com.scripfinder.module2.dto.InstrumentData;

public interface CurrentDataService {
        String save(Map<String, InstrumentData> data);
}
