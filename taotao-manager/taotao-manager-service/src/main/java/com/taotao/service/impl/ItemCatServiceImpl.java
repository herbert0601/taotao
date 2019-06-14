package com.taotao.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Herbert
 * @create 2019-06-14 17:23
 */
@Service
@Transactional
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EasyUITreeNode> getCatList(long parentId) {

        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(tbItemCatExample);
        //tbitemcat中无用信息太多，转成我们自定义的EasyUITreeNode
        ArrayList<EasyUITreeNode> nodes = new ArrayList<>();

        for (TbItemCat tbItemCat : list) {
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(tbItemCat.getId());
            easyUITreeNode.setText(tbItemCat.getName());
            easyUITreeNode.setState(tbItemCat.getIsParent() ? "closed" : "open");
            nodes.add(easyUITreeNode);
        }

        return nodes;
    }
}
