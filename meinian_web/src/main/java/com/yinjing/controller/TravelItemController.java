package com.yinjing.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yinjing.constant.MessageConstant;
import com.yinjing.entity.PageResult;
import com.yinjing.entity.QueryPageBean;
import com.yinjing.entity.Result;
import com.yinjing.pojo.TravelItem;
import com.yinjing.service.TravelItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author尹晶
 * @Date2022/3/2 17:04
 * @Version 1.0
 */
@RestController
@RequestMapping("/travelItem")
public class TravelItemController {

    @Reference //远程调用
    private TravelItemService travelItemService;

    //添加新的自由行
    @PostMapping("/add.do")
    public Result add(@RequestBody TravelItem travelItem){
        //调用service进行添加
        try {
            travelItemService.add(travelItem);
        } catch (Exception e) {
            return  new Result(false, MessageConstant.ADD_TRAVELITEM_FAIL);
        }
        return new Result(true,MessageConstant.ADD_TRAVELITEM_SUCCESS);
    }
    @PostMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = travelItemService.findPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;

    }
    @GetMapping("/findAll.do")
    public Result findAll(){
        List<TravelItem> travelItemList = null;
        try {
            travelItemList = travelItemService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_TRAVELITEM_FAIL);
        }
        return new Result(true,MessageConstant.QUERY_TRAVELITEM_SUCCESS,travelItemList);
    }


    @GetMapping("/remove")
    public Result remove(@RequestParam Integer id){
        try {
            travelItemService.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_TRAVELITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_TRAVELITEM_SUCCESS);
    }

    @PostMapping("/update.do")
    public Result update(@RequestBody TravelItem travelItem){
        try {
            travelItemService.update(travelItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_TRAVELITEM_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_TRAVELITEM_SUCCESS);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam Integer id){
        TravelItem travelItem =null;
        try {
             travelItem = travelItemService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"");
        }
        return  new Result(true,"",travelItem);
    }

}
