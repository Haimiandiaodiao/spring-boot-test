package _009_MybatisTest.model;

import java.math.BigDecimal;
import java.util.Date;

public class TPayLog {
    private Integer id;

    private Integer orderId;

     private String orderSn;

    private String paySn;

    private String thirdSn;

    private BigDecimal amount;

    private String payWayDesc;

    private String payWayCode;

    private String payChannel;

    private String appId;

    private Date startTime;

    private Date timeExpire;

    private Integer isPay;

    private Integer io;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn == null ? null : orderSn.trim();
    }

    public String getPaySn() {
        return paySn;
    }

    public void setPaySn(String paySn) {
        this.paySn = paySn == null ? null : paySn.trim();
    }

    public String getThirdSn() {
        return thirdSn;
    }

    public void setThirdSn(String thirdSn) {
        this.thirdSn = thirdSn == null ? null : thirdSn.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayWayDesc() {
        return payWayDesc;
    }

    public void setPayWayDesc(String payWayDesc) {
        this.payWayDesc = payWayDesc == null ? null : payWayDesc.trim();
    }

    public String getPayWayCode() {
        return payWayCode;
    }

    public void setPayWayCode(String payWayCode) {
        this.payWayCode = payWayCode == null ? null : payWayCode.trim();
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel == null ? null : payChannel.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(Date timeExpire) {
        this.timeExpire = timeExpire;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }

    public Integer getIo() {
        return io;
    }

    public void setIo(Integer io) {
        this.io = io;
    }
}