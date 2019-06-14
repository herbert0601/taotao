package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper tbItemMapper;

	@Override
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		//设置分页
		PageHelper.startPage(page, rows);
		//设置查询条件
		TbItemExample example = new TbItemExample();
		List<TbItem> list = tbItemMapper.selectByExample(example);
		PageInfo pageInfo = new PageInfo(list);
		//封装
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
	}



}
