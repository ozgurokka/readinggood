package com.getir.readingGood.service;

import com.getir.readingGood.model.dto.StatisticDTO;

import java.util.List;

/**
 * Created by ozgurokka on 2/12/22 6:34 PM
 */
public interface StatisticsService {
    public List<StatisticDTO> getCustomerMonthlyStatistics(String customerId);
}
