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

import com.entity.LunwenxinxiEntity;
import com.entity.view.LunwenxinxiView;

import com.service.LunwenxinxiService;
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
 * 论文信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
@RestController
@RequestMapping("/lunwenxinxi")
public class LunwenxinxiController {
    @Autowired
    private LunwenxinxiService lunwenxinxiService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,LunwenxinxiEntity lunwenxinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<LunwenxinxiEntity> ew = new EntityWrapper<LunwenxinxiEntity>();

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
		PageUtils page = lunwenxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lunwenxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,LunwenxinxiEntity lunwenxinxi, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<LunwenxinxiEntity> ew = new EntityWrapper<LunwenxinxiEntity>();

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
		PageUtils page = lunwenxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, lunwenxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( LunwenxinxiEntity lunwenxinxi){
       	EntityWrapper<LunwenxinxiEntity> ew = new EntityWrapper<LunwenxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( lunwenxinxi, "lunwenxinxi")); 
        return R.ok().put("data", lunwenxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(LunwenxinxiEntity lunwenxinxi){
        EntityWrapper< LunwenxinxiEntity> ew = new EntityWrapper< LunwenxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( lunwenxinxi, "lunwenxinxi")); 
		LunwenxinxiView lunwenxinxiView =  lunwenxinxiService.selectView(ew);
		return R.ok("查询论文信息成功").put("data", lunwenxinxiView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        LunwenxinxiEntity lunwenxinxi = lunwenxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(lunwenxinxi,deSens);
        return R.ok().put("data", lunwenxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        LunwenxinxiEntity lunwenxinxi = lunwenxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(lunwenxinxi,deSens);
        return R.ok().put("data", lunwenxinxi);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LunwenxinxiEntity lunwenxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(lunwenxinxi);
        lunwenxinxiService.insert(lunwenxinxi);
        return R.ok().put("data",lunwenxinxi.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody LunwenxinxiEntity lunwenxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(lunwenxinxi);
    	lunwenxinxi.setUserid((Long)request.getSession().getAttribute("userId"));
        lunwenxinxiService.insert(lunwenxinxi);
        return R.ok().put("data",lunwenxinxi.getId());
    }

    /**
     * 批量生成数据
     */
    @RequestMapping("/batch/gen")
    public R batch(int recordNum){
        List<LunwenxinxiEntity> list = lunwenxinxiService.selectList(new EntityWrapper<LunwenxinxiEntity>().last("order by rand() limit 50"));
        if(list!=null && list.size()>0 && recordNum>0) {
            List<LunwenxinxiEntity> batchList = new ArrayList<LunwenxinxiEntity>(recordNum);
            int max = list.size();
            Random random = new Random();
            for(int i=0;i<recordNum;i++) {
                LunwenxinxiEntity lunwenxinxi = new LunwenxinxiEntity();
               lunwenxinxi.setXueqi(list.get(random.nextInt(max)).getXueqi());
               lunwenxinxi.setLunwenmingcheng(list.get(random.nextInt(max)).getLunwenmingcheng());
               lunwenxinxi.setXueshengxingming(list.get(random.nextInt(max)).getXueshengxingming());
               lunwenxinxi.setFengmian(list.get(random.nextInt(max)).getFengmian());
               lunwenxinxi.setJianchaxiang(list.get(random.nextInt(max)).getJianchaxiang());
               lunwenxinxi.setJianchaduixiang(list.get(random.nextInt(max)).getJianchaduixiang());
               lunwenxinxi.setWenjian(list.get(random.nextInt(max)).getWenjian());
               lunwenxinxi.setWanchengzhuangtai(list.get(random.nextInt(max)).getWanchengzhuangtai());
               lunwenxinxi.setRenwuneirong(list.get(random.nextInt(max)).getRenwuneirong());
               lunwenxinxi.setJiaoshigonghao(list.get(random.nextInt(max)).getJiaoshigonghao());
               lunwenxinxi.setJiaoshixingming(list.get(random.nextInt(max)).getJiaoshixingming());
               lunwenxinxi.setLianxishouji(list.get(random.nextInt(max)).getLianxishouji());
               lunwenxinxi.setZhuanye(list.get(random.nextInt(max)).getZhuanye());
               lunwenxinxi.setUserid(list.get(random.nextInt(max)).getUserid());
                batchList.add(lunwenxinxi);
            }
            lunwenxinxiService.insertBatch(batchList);
        }
        return R.ok();
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody LunwenxinxiEntity lunwenxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(lunwenxinxi);
        //全部更新
        lunwenxinxiService.updateById(lunwenxinxi);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        lunwenxinxiService.deleteBatchIds(Arrays.asList(ids));
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
                    LunwenxinxiEntity lunwenxinxiEntity =new LunwenxinxiEntity();
                    lunwenxinxiEntity.setId(new Date().getTime());
                    String xueqi = CommonUtil.getCellValue(row.getCell(0));
                    lunwenxinxiEntity.setXueqi(xueqi);
                    String lunwenmingcheng = CommonUtil.getCellValue(row.getCell(1));
                    lunwenxinxiEntity.setLunwenmingcheng(lunwenmingcheng);
                    String xueshengxingming = CommonUtil.getCellValue(row.getCell(2));
                    lunwenxinxiEntity.setXueshengxingming(xueshengxingming);
                    String jianchaxiang = CommonUtil.getCellValue(row.getCell(3));
                    lunwenxinxiEntity.setJianchaxiang(jianchaxiang);
                    String jianchaduixiang = CommonUtil.getCellValue(row.getCell(4));
                    lunwenxinxiEntity.setJianchaduixiang(jianchaduixiang);
                    String wanchengzhuangtai = CommonUtil.getCellValue(row.getCell(5));
                    lunwenxinxiEntity.setWanchengzhuangtai(wanchengzhuangtai);
                    String renwuneirong = CommonUtil.getCellValue(row.getCell(6));
                    lunwenxinxiEntity.setRenwuneirong(renwuneirong);
                    String jiaoshigonghao = CommonUtil.getCellValue(row.getCell(7));
                    lunwenxinxiEntity.setJiaoshigonghao(jiaoshigonghao);
                    String jiaoshixingming = CommonUtil.getCellValue(row.getCell(8));
                    lunwenxinxiEntity.setJiaoshixingming(jiaoshixingming);
                    String lianxishouji = CommonUtil.getCellValue(row.getCell(9));
                    lunwenxinxiEntity.setLianxishouji(lianxishouji);
                    String zhuanye = CommonUtil.getCellValue(row.getCell(10));
                    lunwenxinxiEntity.setZhuanye(zhuanye);
                    //向数据库中添加新对象
                    lunwenxinxiService.insert(lunwenxinxiEntity);//方法
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
