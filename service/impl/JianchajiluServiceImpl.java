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


import com.dao.JianchajiluDao;
import com.entity.JianchajiluEntity;
import com.service.JianchajiluService;
import com.entity.vo.JianchajiluVO;
import com.entity.view.JianchajiluView;

@Service("jianchajiluService")
public class JianchajiluServiceImpl extends ServiceImpl<JianchajiluDao, JianchajiluEntity> implements JianchajiluService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JianchajiluEntity> page = this.selectPage(
                new Query<JianchajiluEntity>(params).getPage(),
                new EntityWrapper<JianchajiluEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JianchajiluEntity> wrapper) {
		  Page<JianchajiluView> page =new Query<JianchajiluView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<JianchajiluVO> selectListVO(Wrapper<JianchajiluEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JianchajiluVO selectVO(Wrapper<JianchajiluEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JianchajiluView> selectListView(Wrapper<JianchajiluEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JianchajiluView selectView(Wrapper<JianchajiluEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
