package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DianziqianzhangEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DianziqianzhangVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DianziqianzhangView;


/**
 * 电子签章
 *
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
public interface DianziqianzhangService extends IService<DianziqianzhangEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DianziqianzhangVO> selectListVO(Wrapper<DianziqianzhangEntity> wrapper);
   	
   	DianziqianzhangVO selectVO(@Param("ew") Wrapper<DianziqianzhangEntity> wrapper);
   	
   	List<DianziqianzhangView> selectListView(Wrapper<DianziqianzhangEntity> wrapper);
   	
   	DianziqianzhangView selectView(@Param("ew") Wrapper<DianziqianzhangEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DianziqianzhangEntity> wrapper);

   	

}

