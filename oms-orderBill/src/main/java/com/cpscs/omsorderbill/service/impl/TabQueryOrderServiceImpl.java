package com.cpscs.omsorderbill.service.impl;

import com.cpscs.omsorderbill.dao.TabQueryOrderMapper;
import com.cpscs.omsorderbill.domain.Page;
import com.cpscs.omsorderbill.domain.TabQueryOrder;
import com.cpscs.omsorderbill.service.TabQueryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/2 0002
 */
@Service
public class TabQueryOrderServiceImpl implements TabQueryOrderService {

    @Autowired
    private TabQueryOrderMapper tabQueryOrderMapper;

    @Override
    public Page<TabQueryOrder> getOnePage(int pageNo,int pageSize) {
        List<TabQueryOrder> rows =null;
        if(pageNo==-1){
            rows=tabQueryOrderMapper.queryAllOrder(-1, 0);
        }else {
            rows=tabQueryOrderMapper.queryAllOrder((pageNo - 1) * pageSize, pageSize);
        }
        Long total=tabQueryOrderMapper.queryCount();
        Page<TabQueryOrder> page=new Page<>();
        page.setRows(rows);
        page.setTotal(total);
        return page;
    }

    @Override
    public TabQueryOrder editOnePage(TabQueryOrder tabQueryOrder) {
        int result=tabQueryOrderMapper.updateOneOrder(tabQueryOrder);
        if(result>0){
            return tabQueryOrder;
        }
        return null;
    }

    @Override
    public int addOnePage(TabQueryOrder tabQueryOrder) {
        tabQueryOrder.setId(UUID.randomUUID().toString());
        int result=tabQueryOrderMapper.insertOneOrder(tabQueryOrder);
        return result;
    }

    @Override
    public int removeOnePage(String id) {
        int result = tabQueryOrderMapper.deleteOneOrder(id);
        return result;
    }
}
