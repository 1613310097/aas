package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.KaohecailiaoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.KaohecailiaoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.KaohecailiaoView;


/**
 * 考核材料
 *
 * @author 
 * @email 
 * @date 2025-03-06 00:25:17
 */
public interface KaohecailiaoService extends IService<KaohecailiaoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<KaohecailiaoVO> selectListVO(Wrapper<KaohecailiaoEntity> wrapper);
   	
   	KaohecailiaoVO selectVO(@Param("ew") Wrapper<KaohecailiaoEntity> wrapper);
   	
   	List<KaohecailiaoView> selectListView(Wrapper<KaohecailiaoEntity> wrapper);
   	
   	KaohecailiaoView selectView(@Param("ew") Wrapper<KaohecailiaoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<KaohecailiaoEntity> wrapper);

   	

}

