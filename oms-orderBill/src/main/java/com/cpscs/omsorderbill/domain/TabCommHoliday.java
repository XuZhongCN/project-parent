package com.cpscs.omsorderbill.domain;

import java.io.Serializable;
import java.util.Date;

public class TabCommHoliday implements Serializable {
    /**
     * ID
     * 表字段 : tab_comm_holiday.ID
     */
    private String id;

    /**
     * 节假日日期
     * 表字段 : tab_comm_holiday.HOLIDAY_DATE
     */
    private Date holidayDate;

    /**
     * 创建者ID
     * 表字段 : tab_comm_holiday.CREATE_USER_ID
     */
    private String createUserId;

    /**
     * 创建日期
     * 表字段 : tab_comm_holiday.CREATE_DATE
     */
    private Date createDate;

    /**
     * 更新者ID
     * 表字段 : tab_comm_holiday.UPDATE_USER_ID
     */
    private String updateUserId;

    /**
     * 更新日期
     * 表字段 : tab_comm_holiday.UPDATE_DATE
     */
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}