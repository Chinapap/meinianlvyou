package com.yinjing.service;

import com.yinjing.entity.PageResult;
import com.yinjing.entity.Result;
import com.yinjing.pojo.TravelItem;

import java.sql.SQLException;
import java.util.List;

public interface TravelItemService {
    void add(TravelItem travelItem) throws Exception;

    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void remove(Integer id) throws Exception;

    void update(TravelItem travelItem) throws Exception;


    TravelItem findById(Integer id) throws Exception;

    List<TravelItem> findAll() throws SQLException;
}
