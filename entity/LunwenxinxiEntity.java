package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 论文信息
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
@TableName("lunwenxinxi")
public class LunwenxinxiEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public LunwenxinxiEntity() {
		
	}
	
	public LunwenxinxiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 学期
	 */
					
	private String xueqi;
	
	/**
	 * 论文名称
	 */
					
	private String lunwenmingcheng;
	
	/**
	 * 学生姓名
	 */
					
	private String xueshengxingming;
	
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
	
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 设置：学期
	 */
	public void setXueqi(String xueqi) {
		this.xueqi = xueqi;
	}
	/**
	 * 获取：学期
	 */
	public String getXueqi() {
		return xueqi;
	}
	/**
	 * 设置：论文名称
	 */
	public void setLunwenmingcheng(String lunwenmingcheng) {
		this.lunwenmingcheng = lunwenmingcheng;
	}
	/**
	 * 获取：论文名称
	 */
	public String getLunwenmingcheng() {
		return lunwenmingcheng;
	}
	/**
	 * 设置：学生姓名
	 */
	public void setXueshengxingming(String xueshengxingming) {
		this.xueshengxingming = xueshengxingming;
	}
	/**
	 * 获取：学生姓名
	 */
	public String getXueshengxingming() {
		return xueshengxingming;
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
