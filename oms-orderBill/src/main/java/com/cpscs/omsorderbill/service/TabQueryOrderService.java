package com.cpscs.omsorderbill.service;

import com.cpscs.omsorderbill.domain.Page;
import com.cpscs.omsorderbill.domain.TabQueryOrder;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/2 0002
 */
public interface TabQueryOrderService {
   Page<TabQueryOrder> getOnePage(int pageNo, int pageSize);
   TabQueryOrder  editOnePage(TabQueryOrder tabQueryOrder);
   int addOnePage(TabQueryOrder tabQueryOrder);
   int removeOnePage(String id);
}
