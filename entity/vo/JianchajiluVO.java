package com.entity.vo;

import com.entity.JianchajiluEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 检查记录
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
public class JianchajiluVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
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
	 * 完成时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date wanchengshijian;
		
	/**
	 * 完成报告
	 */
	
	private String wanchengbaogao;
		
	/**
	 * 备注
	 */
	
	private String beizhu;
				
	
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
	 * 设置：完成时间
	 */
	 
	public void setWanchengshijian(Date wanchengshijian) {
		this.wanchengshijian = wanchengshijian;
	}
	
	/**
	 * 获取：完成时间
	 */
	public Date getWanchengshijian() {
		return wanchengshijian;
	}
				
	
	/**
	 * 设置：完成报告
	 */
	 
	public void setWanchengbaogao(String wanchengbaogao) {
		this.wanchengbaogao = wanchengbaogao;
	}
	
	/**
	 * 获取：完成报告
	 */
	public String getWanchengbaogao() {
		return wanchengbaogao;
	}
				
	
	/**
	 * 设置：备注
	 */
	 
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	
	/**
	 * 获取：备注
	 */
	public String getBeizhu() {
		return beizhu;
	}
			
}
