package com.yinjing.dao;

import com.yinjing.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface OrderSettingDao {
    void add(OrderSetting orderSetting);

    long findCountByOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting orderSetting);

    List<OrderSetting> getOrderSettingByMonth(Map<String, Object> map);
}
