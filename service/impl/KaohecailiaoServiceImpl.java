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


import com.dao.KaohecailiaoDao;
import com.entity.KaohecailiaoEntity;
import com.service.KaohecailiaoService;
import com.entity.vo.KaohecailiaoVO;
import com.entity.view.KaohecailiaoView;

@Service("kaohecailiaoService")
public class KaohecailiaoServiceImpl extends ServiceImpl<KaohecailiaoDao, KaohecailiaoEntity> implements KaohecailiaoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<KaohecailiaoEntity> page = this.selectPage(
                new Query<KaohecailiaoEntity>(params).getPage(),
                new EntityWrapper<KaohecailiaoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<KaohecailiaoEntity> wrapper) {
		  Page<KaohecailiaoView> page =new Query<KaohecailiaoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<KaohecailiaoVO> selectListVO(Wrapper<KaohecailiaoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public KaohecailiaoVO selectVO(Wrapper<KaohecailiaoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<KaohecailiaoView> selectListView(Wrapper<KaohecailiaoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public KaohecailiaoView selectView(Wrapper<KaohecailiaoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
