package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemDescMapper tbItemDescMapper;

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

    @Override
    public TaotaoResult addItem(TbItem tbItem, String desc) {
        /*
        商品信息是添加到两张表，因为商品描述信息是富文本类型，数据太多tbitemdesc，单独一张表存储，其他信息tbitem
         */
        //1。添加tbitem
		//自己生成ID,并添加到tbitem
		long id = IDUtils.genItemId();
		tbItem.setId(id);
		//设置商品状态信息 ，1-正常，2-下架，3-删除
		tbItem.setStatus((byte) 1);
		//设置商品上架时间和更新时间
		Date date = new Date();
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		tbItemMapper.insert(tbItem);
		//2。添加tbitemdesc
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(id);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		tbItemDescMapper.insert(tbItemDesc);

		return TaotaoResult.ok();
	}


}
