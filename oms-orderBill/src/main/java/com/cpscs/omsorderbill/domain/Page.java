package com.cpscs.omsorderbill.domain;

import java.util.List;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/4 0004
 */
public class Page<T> {
    private long total;
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
