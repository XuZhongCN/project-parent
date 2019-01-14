package com.cpscs.omsorderbill.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpscs.common.utils.PageUtils;
import com.cpscs.common.utils.Result;
import com.cpscs.omsorderbill.constants.PageParam;
import com.cpscs.omsorderbill.domain.TabCommHoliday;
import com.cpscs.omsorderbill.domain.TabCommHolidayExample;
import com.cpscs.omsorderbill.domain.TabCommHolidayExample.Criteria;
import com.cpscs.omsorderbill.service.TabCommHolidayServer;

/**
 *  demo
 * @author dengwenbing
 *
 */

@RequestMapping("/holiday")
@RestController
public class TabCommHolidayController {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private TabCommHolidayServer tabCommHolidayServer;
    // 分页
    @GetMapping(value="/findList")
    public Result listByPage(@RequestParam Map<String, String> params) throws ParseException {
    	// 分页参数
    	PageParam page  = new PageParam(params);
        TabCommHolidayExample example = new TabCommHolidayExample();
        
        Date bDate = StringUtils.isNotEmpty(params.get("holidayBeginDate")) ? sdf.parse(params.get("holidayBeginDate")) : null;
        Date eDate = StringUtils.isNotEmpty(params.get("holidayEndDate")) ? sdf.parse(params.get("holidayEndDate")) : null;
        Criteria c = example.createCriteria();
        if(bDate != null){
        	c.andHolidayDateGreaterThanOrEqualTo(bDate);
        }
        if(eDate != null){
        	c.andHolidayDateLessThanOrEqualTo(eDate);
        }
        PageUtils pageList = tabCommHolidayServer.pageList(page, example);
        Result r =  Result.ok().put("page",pageList);
        return r;
    }
    
	/**
	 * 增加假期
	 * @param user
	 * @return
	 */
	@PostMapping("/addHoliday")
    Result addHoliday(@RequestBody TabCommHoliday holiday) {
		holiday.setId(UUID.randomUUID().toString());
		holiday.setCreateDate(new Date());
		int count = tabCommHolidayServer.addHoliday(holiday);
		return Result.operate(count > 0);
	}


	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@DeleteMapping("/deleteHoliday")
	Result deleteHoliday( String id) {
		int count = tabCommHolidayServer.deleteHoliday(id);
		return Result.operate (count > 0);
	}

	
	/**
	 * 根据id获取假期
	 * @param id
	 * @return
	 */
    @GetMapping("{id}")
	Result getHoliday(@PathVariable("id") String id ){
    	TabCommHoliday holiday = tabCommHolidayServer.getHoliday(id);
    	return Result.ok().put("data",holiday);
	}
    
	/**
	 * 修改假期
	 * @param holiday
	 * @return
	 */
	@PutMapping("/editHoliday")
	Result editHoliday(@RequestBody TabCommHoliday holiday) {
		int count = tabCommHolidayServer.updateHoliday(holiday);
		return Result.operate(count > 0);
	}
}
