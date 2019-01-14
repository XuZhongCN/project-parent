package com.cpscs.omsorderbill.dao;

import com.cpscs.omsorderbill.domain.TabQueryOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/2 0002
 */
public interface TabQueryOrderMapper {
    List<TabQueryOrder> queryAllOrder(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
    Long queryCount();
    int updateOneOrder(TabQueryOrder tabQueryOrder);
    int insertOneOrder(TabQueryOrder tabQueryOrder);
    int deleteOneOrder(@Param("id") String id);
}
