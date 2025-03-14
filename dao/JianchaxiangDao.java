package com.dao;

import com.entity.JianchaxiangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JianchaxiangVO;
import com.entity.view.JianchaxiangView;


/**
 * 检查项
 * 
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
public interface JianchaxiangDao extends BaseMapper<JianchaxiangEntity> {
	
	List<JianchaxiangVO> selectListVO(@Param("ew") Wrapper<JianchaxiangEntity> wrapper);
	
	JianchaxiangVO selectVO(@Param("ew") Wrapper<JianchaxiangEntity> wrapper);
	
	List<JianchaxiangView> selectListView(@Param("ew") Wrapper<JianchaxiangEntity> wrapper);

	List<JianchaxiangView> selectListView(Pagination page,@Param("ew") Wrapper<JianchaxiangEntity> wrapper);

	
	JianchaxiangView selectView(@Param("ew") Wrapper<JianchaxiangEntity> wrapper);
	

}
