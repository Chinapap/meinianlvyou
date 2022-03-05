package com.yinjing.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yinjing.constant.MessageConstant;
import com.yinjing.entity.PageResult;
import com.yinjing.entity.QueryPageBean;
import com.yinjing.entity.Result;
import com.yinjing.pojo.TravelGroup;
import com.yinjing.pojo.TravelItem;
import com.yinjing.service.TraveGroupService;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;


/**
 * @Author尹晶
 * @Date2022/3/2 23:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/travelgroup")
public class TravelGroupController {
    @Reference
    private TraveGroupService travelGroupService;

    @PostMapping("/add.do")
    public Result add(
            @RequestBody TravelGroup travelGroup,
            @RequestParam Integer[] travelItemIds
            ){
        travelGroupService.add(travelGroup,travelItemIds);
        return new Result(true, MessageConstant.ADD_TRAVELGROUP_SUCCESS);
    }

    @PostMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        return  travelGroupService.findPage(queryPageBean);


    }

    @GetMapping("/findById.do")
    public Result findById(@RequestParam Integer id){
        TravelGroup travelGroup = travelGroupService.findById(id);
        return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroup);
    }

    @PostMapping("/update")
    public Result update(@RequestBody TravelGroup travelGroup){
        try {
            travelGroupService.update(travelGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
        return new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS);
    }

    @GetMapping("/find/travelItems/byGroupId.do")
    public Result findTravelItemsByGroupId(@RequestParam Integer groupId){
        Integer[] travelItemIds;
        try {
            travelItemIds = travelGroupService.findTravelItemsByGroupId(groupId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItemIds);
    }

    @GetMapping("/findAll.do")
    public Result findAll(){
        List<TravelGroup> travelGroupList = null;
        try {
            travelGroupList = travelGroupService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_TRAVELGROUP_FAIL);
        }
        return  new Result(true,MessageConstant.QUERY_TRAVELGROUP_SUCCESS,travelGroupList);
    }

}
