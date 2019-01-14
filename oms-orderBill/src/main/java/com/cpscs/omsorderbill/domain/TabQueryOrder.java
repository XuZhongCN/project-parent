package com.cpscs.omsorderbill.domain;

import java.io.Serializable;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/2 0002
 */
public class TabQueryOrder implements Serializable {
    private String id;
    private String orderDate;
    private String loadDate;
    private String etaDate;
    private String dnNo;
    private String mailNo;
    private String mstMailNo;
    private String netNo;
    private String pdtCode;
    private String pdtType;
    private String recipient;
    private String telNo;
    private String despatchCity;
    private String destinationProvince;
    private String destinationCity;
    private String destinationCounty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(String loadDate) {
        this.loadDate = loadDate;
    }

    public String getEtaDate() {
        return etaDate;
    }

    public void setEtaDate(String etaDate) {
        this.etaDate = etaDate;
    }

    public String getDnNo() {
        return dnNo;
    }

    public void setDnNo(String dnNo) {
        this.dnNo = dnNo;
    }

    public String getMailNo() {
        return mailNo;
    }

    public void setMailNo(String mailNo) {
        this.mailNo = mailNo;
    }

    public String getMstMailNo() {
        return mstMailNo;
    }

    public void setMstMailNo(String mstMailNo) {
        this.mstMailNo = mstMailNo;
    }

    public String getNetNo() {
        return netNo;
    }

    public void setNetNo(String netNo) {
        this.netNo = netNo;
    }

    public String getPdtCode() {
        return pdtCode;
    }

    public void setPdtCode(String pdtCode) {
        this.pdtCode = pdtCode;
    }

    public String getPdtType() {
        return pdtType;
    }

    public void setPdtType(String pdtType) {
        this.pdtType = pdtType;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getDespatchCity() {
        return despatchCity;
    }

    public void setDespatchCity(String despatchCity) {
        this.despatchCity = despatchCity;
    }

    public String getDestinationProvince() {
        return destinationProvince;
    }

    public void setDestinationProvince(String destinationProvince) {
        this.destinationProvince = destinationProvince;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationCounty() {
        return destinationCounty;
    }

    public void setDestinationCounty(String destinationCounty) {
        this.destinationCounty = destinationCounty;
    }

    @Override
    public String toString() {
        return "TabQueryOrder{" +
                "id='" + id + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", loadDate='" + loadDate + '\'' +
                ", etaDate='" + etaDate + '\'' +
                ", dnNo='" + dnNo + '\'' +
                ", mailNo='" + mailNo + '\'' +
                ", mstMailNo='" + mstMailNo + '\'' +
                ", netNo='" + netNo + '\'' +
                ", pdtCode='" + pdtCode + '\'' +
                ", pdtType='" + pdtType + '\'' +
                ", recipient='" + recipient + '\'' +
                ", telNo='" + telNo + '\'' +
                ", despatchCity='" + despatchCity + '\'' +
                ", destinationProvince='" + destinationProvince + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", destinationCounty='" + destinationCounty + '\'' +
                '}';
    }
}
