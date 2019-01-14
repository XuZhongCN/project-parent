package com.cpscs.omsbase.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpscs.common.dto.LogDO;
import com.cpscs.common.utils.PageUtils;
import com.cpscs.common.utils.Query;
import com.cpscs.common.utils.Result;
import com.cpscs.omsbase.service.LogService;

@RequestMapping("/log")
@RestController
public class LogController {
    @Autowired
    LogService logService;

    @GetMapping()
    Result list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        return Result.page(new PageUtils(logService.queryList(query), logService.count(query)));
    }

    @PostMapping("/save")
    Result save(@RequestBody LogDO logDO) {
        if (logService.save(logDO) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @DeleteMapping()
    Result remove(Long id) {
        if (logService.remove(id) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @PostMapping("/batchRemove")
    Result batchRemove(@RequestParam("ids[]") Long[] ids) {
        int r = logService.batchRemove(ids);
        if (r > 0) {
            return Result.ok();
        }
        return Result.error();
    }
}
