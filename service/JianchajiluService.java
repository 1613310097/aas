package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JianchajiluEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JianchajiluVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JianchajiluView;


/**
 * 检查记录
 *
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
public interface JianchajiluService extends IService<JianchajiluEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JianchajiluVO> selectListVO(Wrapper<JianchajiluEntity> wrapper);
   	
   	JianchajiluVO selectVO(@Param("ew") Wrapper<JianchajiluEntity> wrapper);
   	
   	List<JianchajiluView> selectListView(Wrapper<JianchajiluEntity> wrapper);
   	
   	JianchajiluView selectView(@Param("ew") Wrapper<JianchajiluEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JianchajiluEntity> wrapper);

   	

}

