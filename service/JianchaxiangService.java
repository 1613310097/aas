package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JianchaxiangEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JianchaxiangVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JianchaxiangView;


/**
 * 检查项
 *
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
public interface JianchaxiangService extends IService<JianchaxiangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JianchaxiangVO> selectListVO(Wrapper<JianchaxiangEntity> wrapper);
   	
   	JianchaxiangVO selectVO(@Param("ew") Wrapper<JianchaxiangEntity> wrapper);
   	
   	List<JianchaxiangView> selectListView(Wrapper<JianchaxiangEntity> wrapper);
   	
   	JianchaxiangView selectView(@Param("ew") Wrapper<JianchaxiangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JianchaxiangEntity> wrapper);

   	

}

