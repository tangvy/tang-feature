package com.tangv.feature.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "oms_order")
public class OmsOrder implements Serializable {

    @TableId(value = "id")
    private Integer id;

    private String ownerCode;

    private String warehouseCode;

    private String orderNo;

    private String businessType;

    private String orderStatus;

    private Date orderStatusTime;

    private String consignee;

    private String consigneeId;

    private String orderType;

    private String platformCode;

    private String tid;

    private String userNick;

    private String sellerNick;

    private Integer consigneeDocType;

    private String consigneeDocNo;

    private Integer orderPrice;

    private String orderCurrency;

    private Date orderTime;

    private String address;

    private Integer fromOrderId;

    private Integer distributionStatus;

    private String orderExceptionCode;

    private Integer stockOccupyStatus;

    private String stockOccupyRemark;

    private Integer invoiceType;

    private String invoiceTitle;

    private String invoiceContent;

    private Integer invoiceAmount;

    private Integer distributionMode;

    private String distributionName;

    private Integer mergeOrderStatus;

    private Integer changeOrderStatus;

    private Integer splitOrderStatus;

    private Integer copyOrderStatus;

    private String expressCode;

    private String expressNo;

    private String buyerRemark;

    private String sellerRemark;

    private Integer shopId;

    private String phone;

    private String mobile;

    private String email;

    private String createByUserId;

    private String updateByUserId;

    private String createByClientId;

    private String updateByClientId;

    private String requestId;

    private Date gmtCreate;

    private Date gmtModified;

    private String remark;

    private String orderPaymentType;

    private String rateName;

    private Integer isSendToSh;

    private String marketplace;

    private String hqOrderId;

    private String orderFlag;

    private Integer isInsurance;

    private String orderSource;

    private Integer isLogisticsOrder;

    private String waitingStatus;

    private String originalOrderNo;

    private Boolean hasSplit;

    private String shippingLabelUrl;

    private String ext;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode == null ? null : ownerCode.trim();
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode == null ? null : warehouseCode.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    public Date getOrderStatusTime() {
        return orderStatusTime;
    }

    public void setOrderStatusTime(Date orderStatusTime) {
        this.orderStatusTime = orderStatusTime;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee == null ? null : consignee.trim();
    }

    public String getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(String consigneeId) {
        this.consigneeId = consigneeId == null ? null : consigneeId.trim();
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode == null ? null : platformCode.trim();
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid == null ? null : tid.trim();
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick == null ? null : userNick.trim();
    }

    public String getSellerNick() {
        return sellerNick;
    }

    public void setSellerNick(String sellerNick) {
        this.sellerNick = sellerNick == null ? null : sellerNick.trim();
    }

    public Integer getConsigneeDocType() {
        return consigneeDocType;
    }

    public void setConsigneeDocType(Integer consigneeDocType) {
        this.consigneeDocType = consigneeDocType;
    }

    public String getConsigneeDocNo() {
        return consigneeDocNo;
    }

    public void setConsigneeDocNo(String consigneeDocNo) {
        this.consigneeDocNo = consigneeDocNo == null ? null : consigneeDocNo.trim();
    }

    public Integer getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Integer orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency == null ? null : orderCurrency.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getFromOrderId() {
        return fromOrderId;
    }

    public void setFromOrderId(Integer fromOrderId) {
        this.fromOrderId = fromOrderId;
    }

    public Integer getDistributionStatus() {
        return distributionStatus;
    }

    public void setDistributionStatus(Integer distributionStatus) {
        this.distributionStatus = distributionStatus;
    }

    public String getOrderExceptionCode() {
        return orderExceptionCode;
    }

    public void setOrderExceptionCode(String orderExceptionCode) {
        this.orderExceptionCode = orderExceptionCode == null ? null : orderExceptionCode.trim();
    }

    public Integer getStockOccupyStatus() {
        return stockOccupyStatus;
    }

    public void setStockOccupyStatus(Integer stockOccupyStatus) {
        this.stockOccupyStatus = stockOccupyStatus;
    }

    public String getStockOccupyRemark() {
        return stockOccupyRemark;
    }

    public void setStockOccupyRemark(String stockOccupyRemark) {
        this.stockOccupyRemark = stockOccupyRemark == null ? null : stockOccupyRemark.trim();
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle == null ? null : invoiceTitle.trim();
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent == null ? null : invoiceContent.trim();
    }

    public Integer getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Integer invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Integer getDistributionMode() {
        return distributionMode;
    }

    public void setDistributionMode(Integer distributionMode) {
        this.distributionMode = distributionMode;
    }

    public String getDistributionName() {
        return distributionName;
    }

    public void setDistributionName(String distributionName) {
        this.distributionName = distributionName == null ? null : distributionName.trim();
    }

    public Integer getMergeOrderStatus() {
        return mergeOrderStatus;
    }

    public void setMergeOrderStatus(Integer mergeOrderStatus) {
        this.mergeOrderStatus = mergeOrderStatus;
    }

    public Integer getChangeOrderStatus() {
        return changeOrderStatus;
    }

    public void setChangeOrderStatus(Integer changeOrderStatus) {
        this.changeOrderStatus = changeOrderStatus;
    }

    public Integer getSplitOrderStatus() {
        return splitOrderStatus;
    }

    public void setSplitOrderStatus(Integer splitOrderStatus) {
        this.splitOrderStatus = splitOrderStatus;
    }

    public Integer getCopyOrderStatus() {
        return copyOrderStatus;
    }

    public void setCopyOrderStatus(Integer copyOrderStatus) {
        this.copyOrderStatus = copyOrderStatus;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode == null ? null : expressCode.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public String getBuyerRemark() {
        return buyerRemark;
    }

    public void setBuyerRemark(String buyerRemark) {
        this.buyerRemark = buyerRemark == null ? null : buyerRemark.trim();
    }

    public String getSellerRemark() {
        return sellerRemark;
    }

    public void setSellerRemark(String sellerRemark) {
        this.sellerRemark = sellerRemark == null ? null : sellerRemark.trim();
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCreateByUserId() {
        return createByUserId;
    }

    public void setCreateByUserId(String createByUserId) {
        this.createByUserId = createByUserId == null ? null : createByUserId.trim();
    }

    public String getUpdateByUserId() {
        return updateByUserId;
    }

    public void setUpdateByUserId(String updateByUserId) {
        this.updateByUserId = updateByUserId == null ? null : updateByUserId.trim();
    }

    public String getCreateByClientId() {
        return createByClientId;
    }

    public void setCreateByClientId(String createByClientId) {
        this.createByClientId = createByClientId == null ? null : createByClientId.trim();
    }

    public String getUpdateByClientId() {
        return updateByClientId;
    }

    public void setUpdateByClientId(String updateByClientId) {
        this.updateByClientId = updateByClientId == null ? null : updateByClientId.trim();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOrderPaymentType() {
        return orderPaymentType;
    }

    public void setOrderPaymentType(String orderPaymentType) {
        this.orderPaymentType = orderPaymentType == null ? null : orderPaymentType.trim();
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName == null ? null : rateName.trim();
    }

    public Integer getIsSendToSh() {
        return isSendToSh;
    }

    public void setIsSendToSh(Integer isSendToSh) {
        this.isSendToSh = isSendToSh;
    }

    public String getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(String marketplace) {
        this.marketplace = marketplace == null ? null : marketplace.trim();
    }

    public String getHqOrderId() {
        return hqOrderId;
    }

    public void setHqOrderId(String hqOrderId) {
        this.hqOrderId = hqOrderId == null ? null : hqOrderId.trim();
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag == null ? null : orderFlag.trim();
    }

    public Integer getIsInsurance() {
        return isInsurance;
    }

    public void setIsInsurance(Integer isInsurance) {
        this.isInsurance = isInsurance;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource == null ? null : orderSource.trim();
    }

    public Integer getIsLogisticsOrder() {
        return isLogisticsOrder;
    }

    public void setIsLogisticsOrder(Integer isLogisticsOrder) {
        this.isLogisticsOrder = isLogisticsOrder;
    }

    public String getWaitingStatus() {
        return waitingStatus;
    }

    public void setWaitingStatus(String waitingStatus) {
        this.waitingStatus = waitingStatus == null ? null : waitingStatus.trim();
    }

    public String getOriginalOrderNo() {
        return originalOrderNo;
    }

    public void setOriginalOrderNo(String originalOrderNo) {
        this.originalOrderNo = originalOrderNo == null ? null : originalOrderNo.trim();
    }

    public Boolean getHasSplit() {
        return hasSplit;
    }

    public void setHasSplit(Boolean hasSplit) {
        this.hasSplit = hasSplit;
    }

    public String getShippingLabelUrl() {
        return shippingLabelUrl;
    }

    public void setShippingLabelUrl(String shippingLabelUrl) {
        this.shippingLabelUrl = shippingLabelUrl == null ? null : shippingLabelUrl.trim();
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ownerCode=").append(ownerCode);
        sb.append(", warehouseCode=").append(warehouseCode);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", businessType=").append(businessType);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", orderStatusTime=").append(orderStatusTime);
        sb.append(", consignee=").append(consignee);
        sb.append(", consigneeId=").append(consigneeId);
        sb.append(", orderType=").append(orderType);
        sb.append(", platformCode=").append(platformCode);
        sb.append(", tid=").append(tid);
        sb.append(", userNick=").append(userNick);
        sb.append(", sellerNick=").append(sellerNick);
        sb.append(", consigneeDocType=").append(consigneeDocType);
        sb.append(", consigneeDocNo=").append(consigneeDocNo);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", orderCurrency=").append(orderCurrency);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", address=").append(address);
        sb.append(", fromOrderId=").append(fromOrderId);
        sb.append(", distributionStatus=").append(distributionStatus);
        sb.append(", orderExceptionCode=").append(orderExceptionCode);
        sb.append(", stockOccupyStatus=").append(stockOccupyStatus);
        sb.append(", stockOccupyRemark=").append(stockOccupyRemark);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", invoiceTitle=").append(invoiceTitle);
        sb.append(", invoiceContent=").append(invoiceContent);
        sb.append(", invoiceAmount=").append(invoiceAmount);
        sb.append(", distributionMode=").append(distributionMode);
        sb.append(", distributionName=").append(distributionName);
        sb.append(", mergeOrderStatus=").append(mergeOrderStatus);
        sb.append(", changeOrderStatus=").append(changeOrderStatus);
        sb.append(", splitOrderStatus=").append(splitOrderStatus);
        sb.append(", copyOrderStatus=").append(copyOrderStatus);
        sb.append(", expressCode=").append(expressCode);
        sb.append(", expressNo=").append(expressNo);
        sb.append(", buyerRemark=").append(buyerRemark);
        sb.append(", sellerRemark=").append(sellerRemark);
        sb.append(", shopId=").append(shopId);
        sb.append(", phone=").append(phone);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", createByUserId=").append(createByUserId);
        sb.append(", updateByUserId=").append(updateByUserId);
        sb.append(", createByClientId=").append(createByClientId);
        sb.append(", updateByClientId=").append(updateByClientId);
        sb.append(", requestId=").append(requestId);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", remark=").append(remark);
        sb.append(", orderPaymentType=").append(orderPaymentType);
        sb.append(", rateName=").append(rateName);
        sb.append(", isSendToSh=").append(isSendToSh);
        sb.append(", marketplace=").append(marketplace);
        sb.append(", hqOrderId=").append(hqOrderId);
        sb.append(", orderFlag=").append(orderFlag);
        sb.append(", isInsurance=").append(isInsurance);
        sb.append(", orderSource=").append(orderSource);
        sb.append(", isLogisticsOrder=").append(isLogisticsOrder);
        sb.append(", waitingStatus=").append(waitingStatus);
        sb.append(", originalOrderNo=").append(originalOrderNo);
        sb.append(", hasSplit=").append(hasSplit);
        sb.append(", shippingLabelUrl=").append(shippingLabelUrl);
        sb.append(", ext=").append(ext);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}