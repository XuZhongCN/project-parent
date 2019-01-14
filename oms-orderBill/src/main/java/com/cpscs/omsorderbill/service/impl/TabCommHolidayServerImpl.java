package com.cpscs.omsorderbill.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpscs.common.utils.PageUtils;
import com.cpscs.omsorderbill.constants.PageParam;
import com.cpscs.omsorderbill.dao.TabCommHolidayMapper;
import com.cpscs.omsorderbill.domain.TabCommHoliday;
import com.cpscs.omsorderbill.domain.TabCommHolidayExample;
import com.cpscs.omsorderbill.service.TabCommHolidayServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
 * @author wang_zhen
 * 2018-12-21 15:58:00
 */
@Service
public class TabCommHolidayServerImpl implements TabCommHolidayServer{

	@Autowired
	private TabCommHolidayMapper tabCommHolidayMapper;
	@Override
	public PageUtils pageList(PageParam page, TabCommHolidayExample example) {
		PageHelper.startPage(page.getPage(), page.getLimit());
		List<TabCommHoliday> list = tabCommHolidayMapper.selectByExample(example);
		PageInfo<TabCommHoliday> pageInfo = new PageInfo<TabCommHoliday>(list);
		PageUtils pageUtil = new PageUtils(list, (int)pageInfo.getTotal());
		return pageUtil;
	}
	@Override
	public int addHoliday(TabCommHoliday holiday) {
		int count = tabCommHolidayMapper.insertSelective(holiday);
		return count;
	}
	@Override
	public int deleteHoliday(String id) {
		int count = tabCommHolidayMapper.deleteByPrimaryKey(id);
		return count;
	}
	@Override
	public TabCommHoliday getHoliday(String id) {
		TabCommHoliday holiday = tabCommHolidayMapper.selectByPrimaryKey(id);
		return holiday;
	}
	@Override
	public int updateHoliday(TabCommHoliday holiday) {
		int count = tabCommHolidayMapper.updateByPrimaryKeySelective(holiday);
		return count;
	}

}
