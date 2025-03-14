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

import com.entity.KaohecailiaoEntity;
import com.entity.view.KaohecailiaoView;

import com.service.KaohecailiaoService;
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
 * 考核材料
 * 后端接口
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
@RestController
@RequestMapping("/kaohecailiao")
public class KaohecailiaoController {
    @Autowired
    private KaohecailiaoService kaohecailiaoService;






    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,KaohecailiaoEntity kaohecailiao,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<KaohecailiaoEntity> ew = new EntityWrapper<KaohecailiaoEntity>();

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
		PageUtils page = kaohecailiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaohecailiao), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,KaohecailiaoEntity kaohecailiao, 
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<KaohecailiaoEntity> ew = new EntityWrapper<KaohecailiaoEntity>();

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
		PageUtils page = kaohecailiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, kaohecailiao), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( KaohecailiaoEntity kaohecailiao){
       	EntityWrapper<KaohecailiaoEntity> ew = new EntityWrapper<KaohecailiaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( kaohecailiao, "kaohecailiao")); 
        return R.ok().put("data", kaohecailiaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(KaohecailiaoEntity kaohecailiao){
        EntityWrapper< KaohecailiaoEntity> ew = new EntityWrapper< KaohecailiaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( kaohecailiao, "kaohecailiao")); 
		KaohecailiaoView kaohecailiaoView =  kaohecailiaoService.selectView(ew);
		return R.ok("查询考核材料成功").put("data", kaohecailiaoView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        KaohecailiaoEntity kaohecailiao = kaohecailiaoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kaohecailiao,deSens);
        return R.ok().put("data", kaohecailiao);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        KaohecailiaoEntity kaohecailiao = kaohecailiaoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(kaohecailiao,deSens);
        return R.ok().put("data", kaohecailiao);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody KaohecailiaoEntity kaohecailiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaohecailiao);
        kaohecailiaoService.insert(kaohecailiao);
        return R.ok().put("data",kaohecailiao.getId());
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody KaohecailiaoEntity kaohecailiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaohecailiao);
    	kaohecailiao.setUserid((Long)request.getSession().getAttribute("userId"));
        kaohecailiaoService.insert(kaohecailiao);
        return R.ok().put("data",kaohecailiao.getId());
    }

    /**
     * 批量生成数据
     */
    @RequestMapping("/batch/gen")
    public R batch(int recordNum){
        List<KaohecailiaoEntity> list = kaohecailiaoService.selectList(new EntityWrapper<KaohecailiaoEntity>().last("order by rand() limit 50"));
        if(list!=null && list.size()>0 && recordNum>0) {
            List<KaohecailiaoEntity> batchList = new ArrayList<KaohecailiaoEntity>(recordNum);
            int max = list.size();
            Random random = new Random();
            for(int i=0;i<recordNum;i++) {
                KaohecailiaoEntity kaohecailiao = new KaohecailiaoEntity();
               kaohecailiao.setXueqi(list.get(random.nextInt(max)).getXueqi());
               kaohecailiao.setKecheng(list.get(random.nextInt(max)).getKecheng());
               kaohecailiao.setFengmian(list.get(random.nextInt(max)).getFengmian());
               kaohecailiao.setJianchaxiang(list.get(random.nextInt(max)).getJianchaxiang());
               kaohecailiao.setJianchaduixiang(list.get(random.nextInt(max)).getJianchaduixiang());
               kaohecailiao.setWenjian(list.get(random.nextInt(max)).getWenjian());
               kaohecailiao.setWanchengzhuangtai(list.get(random.nextInt(max)).getWanchengzhuangtai());
               kaohecailiao.setRenwuneirong(list.get(random.nextInt(max)).getRenwuneirong());
               kaohecailiao.setJiaoshigonghao(list.get(random.nextInt(max)).getJiaoshigonghao());
               kaohecailiao.setJiaoshixingming(list.get(random.nextInt(max)).getJiaoshixingming());
               kaohecailiao.setLianxishouji(list.get(random.nextInt(max)).getLianxishouji());
               kaohecailiao.setZhuanye(list.get(random.nextInt(max)).getZhuanye());
               kaohecailiao.setUserid(list.get(random.nextInt(max)).getUserid());
                batchList.add(kaohecailiao);
            }
            kaohecailiaoService.insertBatch(batchList);
        }
        return R.ok();
    }




    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody KaohecailiaoEntity kaohecailiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(kaohecailiao);
        //全部更新
        kaohecailiaoService.updateById(kaohecailiao);
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        kaohecailiaoService.deleteBatchIds(Arrays.asList(ids));
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
                    KaohecailiaoEntity kaohecailiaoEntity =new KaohecailiaoEntity();
                    kaohecailiaoEntity.setId(new Date().getTime());
                    String xueqi = CommonUtil.getCellValue(row.getCell(0));
                    kaohecailiaoEntity.setXueqi(xueqi);
                    String jianchaxiang = CommonUtil.getCellValue(row.getCell(1));
                    kaohecailiaoEntity.setJianchaxiang(jianchaxiang);
                    String jianchaduixiang = CommonUtil.getCellValue(row.getCell(2));
                    kaohecailiaoEntity.setJianchaduixiang(jianchaduixiang);
                    String wanchengzhuangtai = CommonUtil.getCellValue(row.getCell(3));
                    kaohecailiaoEntity.setWanchengzhuangtai(wanchengzhuangtai);
                    String renwuneirong = CommonUtil.getCellValue(row.getCell(4));
                    kaohecailiaoEntity.setRenwuneirong(renwuneirong);
                    String jiaoshigonghao = CommonUtil.getCellValue(row.getCell(5));
                    kaohecailiaoEntity.setJiaoshigonghao(jiaoshigonghao);
                    String jiaoshixingming = CommonUtil.getCellValue(row.getCell(6));
                    kaohecailiaoEntity.setJiaoshixingming(jiaoshixingming);
                    String lianxishouji = CommonUtil.getCellValue(row.getCell(7));
                    kaohecailiaoEntity.setLianxishouji(lianxishouji);
                    String zhuanye = CommonUtil.getCellValue(row.getCell(8));
                    kaohecailiaoEntity.setZhuanye(zhuanye);
                    //向数据库中添加新对象
                    kaohecailiaoService.insert(kaohecailiaoEntity);//方法
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
