package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @author Herbert
 * @create 2019-06-16 17:28
 */
public interface ContentCatService {
    List<EasyUITreeNode> getContentCatList(long parentId);
}
