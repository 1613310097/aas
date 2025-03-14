package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.JianchaxiangEntity;
import com.entity.view.JianchaxiangView;

import com.service.JianchaxiangService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 检查项
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
@RestController
@RequestMapping("/jianchaxiang")
public class JianchaxiangController {
    @Autowired
    private JianchaxiangService jianchaxiangService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JianchaxiangEntity jianchaxiang,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JianchaxiangEntity> ew = new EntityWrapper<JianchaxiangEntity>();


        //查询结果
		PageUtils page = jianchaxiangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jianchaxiang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,JianchaxiangEntity jianchaxiang, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JianchaxiangEntity> ew = new EntityWrapper<JianchaxiangEntity>();

        //查询结果
		PageUtils page = jianchaxiangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jianchaxiang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JianchaxiangEntity jianchaxiang){
       	EntityWrapper<JianchaxiangEntity> ew = new EntityWrapper<JianchaxiangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jianchaxiang, "jianchaxiang")); 
        return R.ok().put("data", jianchaxiangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JianchaxiangEntity jianchaxiang){
        EntityWrapper< JianchaxiangEntity> ew = new EntityWrapper< JianchaxiangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jianchaxiang, "jianchaxiang")); 
		JianchaxiangView jianchaxiangView =  jianchaxiangService.selectView(ew);
		return R.ok("查询检查项成功").put("data", jianchaxiangView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JianchaxiangEntity jianchaxiang = jianchaxiangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jianchaxiang,deSens);
        return R.ok().put("data", jianchaxiang);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JianchaxiangEntity jianchaxiang = jianchaxiangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jianchaxiang,deSens);
        return R.ok().put("data", jianchaxiang);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JianchaxiangEntity jianchaxiang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jianchaxiang);
        jianchaxiangService.insert(jianchaxiang);
        return R.ok().put("data",jianchaxiang.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JianchaxiangEntity jianchaxiang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jianchaxiang);
        jianchaxiangService.insert(jianchaxiang);
        return R.ok().put("data",jianchaxiang.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JianchaxiangEntity jianchaxiang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jianchaxiang);
        //全部更新
        jianchaxiangService.updateById(jianchaxiang);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jianchaxiangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
