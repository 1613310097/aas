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

import com.entity.DianziqianzhangEntity;
import com.entity.view.DianziqianzhangView;

import com.service.DianziqianzhangService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 电子签章
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
@RestController
@RequestMapping("/dianziqianzhang")
public class DianziqianzhangController {
    @Autowired
    private DianziqianzhangService dianziqianzhangService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DianziqianzhangEntity dianziqianzhang,
		HttpServletRequest request){
        if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
            dianziqianzhang.setUserid((Long)request.getSession().getAttribute("userId"));
        }
        //设置查询条件
        EntityWrapper<DianziqianzhangEntity> ew = new EntityWrapper<DianziqianzhangEntity>();


        //查询结果
		PageUtils page = dianziqianzhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dianziqianzhang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DianziqianzhangEntity dianziqianzhang, 
		HttpServletRequest request){
    	if(!request.getSession().getAttribute("role").toString().equals("管理员")) {
    		dianziqianzhang.setUserid((Long)request.getSession().getAttribute("userId"));
    	}
        //设置查询条件
        EntityWrapper<DianziqianzhangEntity> ew = new EntityWrapper<DianziqianzhangEntity>();

        //查询结果
		PageUtils page = dianziqianzhangService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, dianziqianzhang), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DianziqianzhangEntity dianziqianzhang){
       	EntityWrapper<DianziqianzhangEntity> ew = new EntityWrapper<DianziqianzhangEntity>();
      	ew.allEq(MPUtil.allEQMapPre( dianziqianzhang, "dianziqianzhang")); 
        return R.ok().put("data", dianziqianzhangService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DianziqianzhangEntity dianziqianzhang){
        EntityWrapper< DianziqianzhangEntity> ew = new EntityWrapper< DianziqianzhangEntity>();
 		ew.allEq(MPUtil.allEQMapPre( dianziqianzhang, "dianziqianzhang")); 
		DianziqianzhangView dianziqianzhangView =  dianziqianzhangService.selectView(ew);
		return R.ok("查询电子签章成功").put("data", dianziqianzhangView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DianziqianzhangEntity dianziqianzhang = dianziqianzhangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(dianziqianzhang,deSens);
        return R.ok().put("data", dianziqianzhang);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DianziqianzhangEntity dianziqianzhang = dianziqianzhangService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(dianziqianzhang,deSens);
        return R.ok().put("data", dianziqianzhang);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DianziqianzhangEntity dianziqianzhang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dianziqianzhang);
    	dianziqianzhang.setUserid((Long)request.getSession().getAttribute("userId"));
        dianziqianzhangService.insert(dianziqianzhang);
        return R.ok().put("data",dianziqianzhang.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DianziqianzhangEntity dianziqianzhang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dianziqianzhang);
    	dianziqianzhang.setUserid((Long)request.getSession().getAttribute("userId"));
        dianziqianzhangService.insert(dianziqianzhang);
        return R.ok().put("data",dianziqianzhang.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DianziqianzhangEntity dianziqianzhang, HttpServletRequest request){
        //ValidatorUtils.validateEntity(dianziqianzhang);
        //全部更新
        dianziqianzhangService.updateById(dianziqianzhang);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        dianziqianzhangService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
