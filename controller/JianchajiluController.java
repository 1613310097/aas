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

import com.entity.JianchajiluEntity;
import com.entity.view.JianchajiluView;

import com.service.JianchajiluService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 检查记录
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
@RestController
@RequestMapping("/jianchajilu")
public class JianchajiluController {
    @Autowired
    private JianchajiluService jianchajiluService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JianchajiluEntity jianchajilu,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JianchajiluEntity> ew = new EntityWrapper<JianchajiluEntity>();

        String tableName = request.getSession().getAttribute("tableName").toString();
        ew.andNew();
        if(tableName.equals("jiaoshi")) {
            ew.eq("jianchaduixiang", (String)request.getSession().getAttribute("username"));
        }
        if(tableName.equals("jiaoshi")) {
            ew.or();
            ew.eq("jiaoshigonghao", (String)request.getSession().getAttribute("username"));
        }
        ew.andNew("1=1");

        //查询结果
		PageUtils page = jianchajiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jianchajilu), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JianchajiluEntity jianchajilu, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JianchajiluEntity> ew = new EntityWrapper<JianchajiluEntity>();

        //查询结果
		PageUtils page = jianchajiluService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jianchajilu), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JianchajiluEntity jianchajilu){
       	EntityWrapper<JianchajiluEntity> ew = new EntityWrapper<JianchajiluEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jianchajilu, "jianchajilu")); 
        return R.ok().put("data", jianchajiluService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JianchajiluEntity jianchajilu){
        EntityWrapper< JianchajiluEntity> ew = new EntityWrapper< JianchajiluEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jianchajilu, "jianchajilu")); 
		JianchajiluView jianchajiluView =  jianchajiluService.selectView(ew);
		return R.ok("查询检查记录成功").put("data", jianchajiluView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JianchajiluEntity jianchajilu = jianchajiluService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jianchajilu,deSens);
        return R.ok().put("data", jianchajilu);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JianchajiluEntity jianchajilu = jianchajiluService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jianchajilu,deSens);
        return R.ok().put("data", jianchajilu);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JianchajiluEntity jianchajilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jianchajilu);
        jianchajiluService.insert(jianchajilu);
        return R.ok().put("data",jianchajilu.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JianchajiluEntity jianchajilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jianchajilu);
        jianchajiluService.insert(jianchajilu);
        return R.ok().put("data",jianchajilu.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JianchajiluEntity jianchajilu, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jianchajilu);
        //全部更新
        jianchajiluService.updateById(jianchajilu);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jianchajiluService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    








}
