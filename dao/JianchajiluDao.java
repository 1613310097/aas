package com.dao;

import com.entity.JianchajiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JianchajiluVO;
import com.entity.view.JianchajiluView;


/**
 * 检查记录
 * 
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
public interface JianchajiluDao extends BaseMapper<JianchajiluEntity> {
	
	List<JianchajiluVO> selectListVO(@Param("ew") Wrapper<JianchajiluEntity> wrapper);
	
	JianchajiluVO selectVO(@Param("ew") Wrapper<JianchajiluEntity> wrapper);
	
	List<JianchajiluView> selectListView(@Param("ew") Wrapper<JianchajiluEntity> wrapper);

	List<JianchajiluView> selectListView(Pagination page,@Param("ew") Wrapper<JianchajiluEntity> wrapper);

	
	JianchajiluView selectView(@Param("ew") Wrapper<JianchajiluEntity> wrapper);
	

}
