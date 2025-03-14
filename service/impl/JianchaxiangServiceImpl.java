package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.JianchaxiangDao;
import com.entity.JianchaxiangEntity;
import com.service.JianchaxiangService;
import com.entity.vo.JianchaxiangVO;
import com.entity.view.JianchaxiangView;

@Service("jianchaxiangService")
public class JianchaxiangServiceImpl extends ServiceImpl<JianchaxiangDao, JianchaxiangEntity> implements JianchaxiangService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JianchaxiangEntity> page = this.selectPage(
                new Query<JianchaxiangEntity>(params).getPage(),
                new EntityWrapper<JianchaxiangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JianchaxiangEntity> wrapper) {
		  Page<JianchaxiangView> page =new Query<JianchaxiangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<JianchaxiangVO> selectListVO(Wrapper<JianchaxiangEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JianchaxiangVO selectVO(Wrapper<JianchaxiangEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JianchaxiangView> selectListView(Wrapper<JianchaxiangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JianchaxiangView selectView(Wrapper<JianchaxiangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
