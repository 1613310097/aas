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


import com.dao.DianziqianzhangDao;
import com.entity.DianziqianzhangEntity;
import com.service.DianziqianzhangService;
import com.entity.vo.DianziqianzhangVO;
import com.entity.view.DianziqianzhangView;

@Service("dianziqianzhangService")
public class DianziqianzhangServiceImpl extends ServiceImpl<DianziqianzhangDao, DianziqianzhangEntity> implements DianziqianzhangService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DianziqianzhangEntity> page = this.selectPage(
                new Query<DianziqianzhangEntity>(params).getPage(),
                new EntityWrapper<DianziqianzhangEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DianziqianzhangEntity> wrapper) {
		  Page<DianziqianzhangView> page =new Query<DianziqianzhangView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DianziqianzhangVO> selectListVO(Wrapper<DianziqianzhangEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DianziqianzhangVO selectVO(Wrapper<DianziqianzhangEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DianziqianzhangView> selectListView(Wrapper<DianziqianzhangEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DianziqianzhangView selectView(Wrapper<DianziqianzhangEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
