package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.entity.TokenEntity;
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

import com.entity.JiaoshiEntity;
import com.entity.view.JiaoshiView;

import com.service.JiaoshiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 教师
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-06 00:25:16
 */
@RestController
@RequestMapping("/jiaoshi")
public class JiaoshiController {
    @Autowired
    private JiaoshiService jiaoshiService;






    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        JiaoshiEntity u = jiaoshiService.selectOne(new EntityWrapper<JiaoshiEntity>().eq("jiaoshigonghao", username));
        // 判断用户锁定状态
        if(u!=null && u.getStatus().intValue()==1) {
            //返回已锁定提示
            return R.error("账号已锁定，请联系管理员。");
        }
        // 当用户不存在或验证密码不通过时
		if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
		
        // 获取登录token
		String token = tokenService.generateToken(u.getId(), username,"jiaoshi",  "教师" );
        //返回token
		return R.ok().put("token", token);
	}


	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody JiaoshiEntity jiaoshi){
    	//ValidatorUtils.validateEntity(jiaoshi);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	JiaoshiEntity u = jiaoshiService.selectOne(new EntityWrapper<JiaoshiEntity>().eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同教师工号，否则返回错误信息
        if(jiaoshiService.selectCount(new EntityWrapper<JiaoshiEntity>().eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()))>0) {
            return R.error("教师工号已存在");
        }
		Long uId = new Date().getTime();
		jiaoshi.setId(uId);
        //保存用户
        jiaoshiService.insert(jiaoshi);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        JiaoshiEntity u = jiaoshiService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        JiaoshiEntity u = jiaoshiService.selectOne(new EntityWrapper<JiaoshiEntity>().eq("jiaoshigonghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        jiaoshiService.updateById(u);
        return R.ok("密码已重置为：123456");
    }



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JiaoshiEntity jiaoshi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JiaoshiEntity> ew = new EntityWrapper<JiaoshiEntity>();


        //查询结果
		PageUtils page = jiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoshi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JiaoshiEntity jiaoshi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JiaoshiEntity> ew = new EntityWrapper<JiaoshiEntity>();

        //查询结果
		PageUtils page = jiaoshiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jiaoshi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JiaoshiEntity jiaoshi){
       	EntityWrapper<JiaoshiEntity> ew = new EntityWrapper<JiaoshiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jiaoshi, "jiaoshi")); 
        return R.ok().put("data", jiaoshiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JiaoshiEntity jiaoshi){
        EntityWrapper< JiaoshiEntity> ew = new EntityWrapper< JiaoshiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jiaoshi, "jiaoshi")); 
		JiaoshiView jiaoshiView =  jiaoshiService.selectView(ew);
		return R.ok("查询教师成功").put("data", jiaoshiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JiaoshiEntity jiaoshi = jiaoshiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiaoshi,deSens);
        return R.ok().put("data", jiaoshi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JiaoshiEntity jiaoshi = jiaoshiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jiaoshi,deSens);
        return R.ok().put("data", jiaoshi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JiaoshiEntity jiaoshi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(jiaoshiService.selectCount(new EntityWrapper<JiaoshiEntity>().eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()))>0) {
            return R.error("教师工号已存在");
        }
        //ValidatorUtils.validateEntity(jiaoshi);
        //验证账号唯一性，否则返回错误信息
        JiaoshiEntity u = jiaoshiService.selectOne(new EntityWrapper<JiaoshiEntity>().eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	jiaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		jiaoshi.setId(new Date().getTime());
        jiaoshiService.insert(jiaoshi);
        return R.ok().put("data",jiaoshi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JiaoshiEntity jiaoshi, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(jiaoshiService.selectCount(new EntityWrapper<JiaoshiEntity>().eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()))>0) {
            return R.error("教师工号已存在");
        }
        //ValidatorUtils.validateEntity(jiaoshi);
        //验证账号唯一性，否则返回错误信息
        JiaoshiEntity u = jiaoshiService.selectOne(new EntityWrapper<JiaoshiEntity>().eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	jiaoshi.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		jiaoshi.setId(new Date().getTime());
        jiaoshiService.insert(jiaoshi);
        return R.ok().put("data",jiaoshi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JiaoshiEntity jiaoshi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jiaoshi);
        //验证字段唯一性，否则返回错误信息
        if(jiaoshiService.selectCount(new EntityWrapper<JiaoshiEntity>().ne("id", jiaoshi.getId()).eq("jiaoshigonghao", jiaoshi.getJiaoshigonghao()))>0) {
            return R.error("教师工号已存在");
        }
        //全部更新
        jiaoshiService.updateById(jiaoshi);
        if(null!=jiaoshi.getJiaoshigonghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(jiaoshi.getJiaoshigonghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", jiaoshi.getId()));
        }
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jiaoshiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    





    /**
    * 文件导入
    */
    @RequestMapping("/importExcel")
    public R importExcel(@RequestParam("file") MultipartFile file){
        try {
            //获取输入流
            InputStream inputStream = file.getInputStream();
            //创建读取工作簿
            Workbook workbook = WorkbookFactory.create(inputStream);
            //获取工作表
            Sheet sheet = workbook.getSheetAt(0);
            //获取总行
            int rowTotal=sheet.getPhysicalNumberOfRows();
            if(rowTotal > 1) {
                //获取单元格
                for (int i = 1; i < rowTotal; i++) {
                    Row row = sheet.getRow(i);
                    JiaoshiEntity jiaoshiEntity =new JiaoshiEntity();
                    jiaoshiEntity.setId(new Date().getTime());
                    String jiaoshigonghao = CommonUtil.getCellValue(row.getCell(0));
                    jiaoshiEntity.setJiaoshigonghao(jiaoshigonghao);
                    String mima = CommonUtil.getCellValue(row.getCell(1));
                    jiaoshiEntity.setMima(mima);
                    String jiaoshixingming = CommonUtil.getCellValue(row.getCell(2));
                    jiaoshiEntity.setJiaoshixingming(jiaoshixingming);
                    String xingbie = CommonUtil.getCellValue(row.getCell(3));
                    jiaoshiEntity.setXingbie(xingbie);
                    String youxiang = CommonUtil.getCellValue(row.getCell(4));
                    jiaoshiEntity.setYouxiang(youxiang);
                    String lianxishouji = CommonUtil.getCellValue(row.getCell(5));
                    jiaoshiEntity.setLianxishouji(lianxishouji);
                    String zhicheng = CommonUtil.getCellValue(row.getCell(6));
                    jiaoshiEntity.setZhicheng(zhicheng);
                    String zhuanye = CommonUtil.getCellValue(row.getCell(7));
                    jiaoshiEntity.setZhuanye(zhuanye);
                    String xueqi = CommonUtil.getCellValue(row.getCell(8));
                    jiaoshiEntity.setXueqi(xueqi);
                    String kecheng = CommonUtil.getCellValue(row.getCell(9));
                    jiaoshiEntity.setKecheng(kecheng);
                    String shangkebanji = CommonUtil.getCellValue(row.getCell(10));
                    jiaoshiEntity.setShangkebanji(shangkebanji);
                    //向数据库中添加新对象
                    jiaoshiService.insert(jiaoshiEntity);//方法
                }
            }
            inputStream.close();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok("导入成功");
    }



}
