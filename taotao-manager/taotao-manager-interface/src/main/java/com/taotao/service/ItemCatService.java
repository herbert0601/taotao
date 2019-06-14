package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @author Herbert
 * @create 2019-06-14 17:18
 */
public interface ItemCatService {
    List<EasyUITreeNode> getCatList(long parentId);
}
