package com.entity.vo;

import com.entity.KaohecailiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 考核材料
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
public class KaohecailiaoVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 课程
	 */
	
	private String kecheng;
		
	/**
	 * 封面
	 */
	
	private String fengmian;
		
	/**
	 * 检查项
	 */
	
	private String jianchaxiang;
		
	/**
	 * 检查对象
	 */
	
	private String jianchaduixiang;
		
	/**
	 * 文件
	 */
	
	private String wenjian;
		
	/**
	 * 完成状态
	 */
	
	private String wanchengzhuangtai;
		
	/**
	 * 任务内容
	 */
	
	private String renwuneirong;
		
	/**
	 * 教师工号
	 */
	
	private String jiaoshigonghao;
		
	/**
	 * 批改老师
	 */
	
	private String jiaoshixingming;
		
	/**
	 * 联系手机
	 */
	
	private String lianxishouji;
		
	/**
	 * 专业
	 */
	
	private String zhuanye;
		
	/**
	 * 用户id
	 */
	
	private Long userid;
				
	
	/**
	 * 设置：课程
	 */
	 
	public void setKecheng(String kecheng) {
		this.kecheng = kecheng;
	}
	
	/**
	 * 获取：课程
	 */
	public String getKecheng() {
		return kecheng;
	}
				
	
	/**
	 * 设置：封面
	 */
	 
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	
	/**
	 * 获取：封面
	 */
	public String getFengmian() {
		return fengmian;
	}
				
	
	/**
	 * 设置：检查项
	 */
	 
	public void setJianchaxiang(String jianchaxiang) {
		this.jianchaxiang = jianchaxiang;
	}
	
	/**
	 * 获取：检查项
	 */
	public String getJianchaxiang() {
		return jianchaxiang;
	}
				
	
	/**
	 * 设置：检查对象
	 */
	 
	public void setJianchaduixiang(String jianchaduixiang) {
		this.jianchaduixiang = jianchaduixiang;
	}
	
	/**
	 * 获取：检查对象
	 */
	public String getJianchaduixiang() {
		return jianchaduixiang;
	}
				
	
	/**
	 * 设置：文件
	 */
	 
	public void setWenjian(String wenjian) {
		this.wenjian = wenjian;
	}
	
	/**
	 * 获取：文件
	 */
	public String getWenjian() {
		return wenjian;
	}
				
	
	/**
	 * 设置：完成状态
	 */
	 
	public void setWanchengzhuangtai(String wanchengzhuangtai) {
		this.wanchengzhuangtai = wanchengzhuangtai;
	}
	
	/**
	 * 获取：完成状态
	 */
	public String getWanchengzhuangtai() {
		return wanchengzhuangtai;
	}
				
	
	/**
	 * 设置：任务内容
	 */
	 
	public void setRenwuneirong(String renwuneirong) {
		this.renwuneirong = renwuneirong;
	}
	
	/**
	 * 获取：任务内容
	 */
	public String getRenwuneirong() {
		return renwuneirong;
	}
				
	
	/**
	 * 设置：教师工号
	 */
	 
	public void setJiaoshigonghao(String jiaoshigonghao) {
		this.jiaoshigonghao = jiaoshigonghao;
	}
	
	/**
	 * 获取：教师工号
	 */
	public String getJiaoshigonghao() {
		return jiaoshigonghao;
	}
				
	
	/**
	 * 设置：批改老师
	 */
	 
	public void setJiaoshixingming(String jiaoshixingming) {
		this.jiaoshixingming = jiaoshixingming;
	}
	
	/**
	 * 获取：批改老师
	 */
	public String getJiaoshixingming() {
		return jiaoshixingming;
	}
				
	
	/**
	 * 设置：联系手机
	 */
	 
	public void setLianxishouji(String lianxishouji) {
		this.lianxishouji = lianxishouji;
	}
	
	/**
	 * 获取：联系手机
	 */
	public String getLianxishouji() {
		return lianxishouji;
	}
				
	
	/**
	 * 设置：专业
	 */
	 
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	
	/**
	 * 获取：专业
	 */
	public String getZhuanye() {
		return zhuanye;
	}
				
	
	/**
	 * 设置：用户id
	 */
	 
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
	/**
	 * 获取：用户id
	 */
	public Long getUserid() {
		return userid;
	}
			
}
