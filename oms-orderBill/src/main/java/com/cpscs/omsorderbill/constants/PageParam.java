package com.cpscs.omsorderbill.constants;

import java.util.Map;


public class PageParam {
	private Integer page; //当前页
	private Integer limit;//查询个数
	private String sidx;//排序字段
	private String sord;// 排序类型
	private Integer offset;
    public PageParam(Map<String, String> params) {
        if (null != params.get("limit")) {
            this.limit = Integer.parseInt(params.get("limit").toString());
        } else {
        	this.limit = Constants.LIMIT;
        }
        if (null != params.get("offset")) {
            this.offset = Integer.parseInt(params.get("offset").toString());
        }
        if (null != params.get("page")) {
            this.page = Integer.parseInt(params.get("page").toString());
        } else {
        	this.page = Constants.PAGE;
        }
    }
    
    
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}


	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
}
