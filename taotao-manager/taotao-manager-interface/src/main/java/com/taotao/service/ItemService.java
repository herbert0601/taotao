package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;

public interface ItemService {

	EasyUIDataGridResult getItemList(Integer page, Integer rows);
}
