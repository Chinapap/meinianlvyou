package com.yinjing.dao;

import com.github.pagehelper.Page;
import com.yinjing.pojo.TravelItem;

import java.sql.SQLException;
import java.util.List;

public interface TravelItemDao {
    void add(TravelItem travelItem) throws SQLException;

    Page<TravelItem> findPage(String queryString) ;

    int remove(Integer id) throws SQLException;

    int update(TravelItem travelItem) throws SQLException;

    TravelItem findById(Integer id) throws SQLException;

    List<TravelItem> findAll() throws SQLException;
}
