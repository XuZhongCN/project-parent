package com.cpscs.omsorderbill.dao;

import com.cpscs.omsorderbill.domain.TabCommHoliday;
import com.cpscs.omsorderbill.domain.TabCommHolidayExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;
public interface TabCommHolidayMapper {
    int countByExample(TabCommHolidayExample example);

    int deleteByExample(TabCommHolidayExample example);

    int deleteByPrimaryKey(String id);

    int insert(TabCommHoliday record);

    int insertSelective(TabCommHoliday record);

    List<TabCommHoliday> selectByExample(TabCommHolidayExample example);

    TabCommHoliday selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TabCommHoliday record, @Param("example") TabCommHolidayExample example);

    int updateByExample(@Param("record") TabCommHoliday record, @Param("example") TabCommHolidayExample example);

    int updateByPrimaryKeySelective(TabCommHoliday record);

    int updateByPrimaryKey(TabCommHoliday record);
}