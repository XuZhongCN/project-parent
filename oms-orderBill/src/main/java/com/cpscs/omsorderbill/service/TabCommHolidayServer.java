package com.cpscs.omsorderbill.service;

import com.cpscs.common.utils.PageUtils;
import com.cpscs.omsorderbill.constants.PageParam;
import com.cpscs.omsorderbill.domain.TabCommHoliday;
import com.cpscs.omsorderbill.domain.TabCommHolidayExample;
/**
 * 
 * @author wang_zhen
 * 2018-12-21 15:58:00
 */
public interface TabCommHolidayServer {
    // 分页
	PageUtils pageList(PageParam page, TabCommHolidayExample example) ;
	// 添加
	int addHoliday(TabCommHoliday holiday);
	// 删除
	int deleteHoliday(String id);
	
	// 加载单个
	TabCommHoliday getHoliday(String id);
	//修改
	int updateHoliday(TabCommHoliday holiday);
	
	
}
	


