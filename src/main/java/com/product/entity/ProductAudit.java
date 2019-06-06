package com.product.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ProductAudit {
private Long auditId;
private String productGroupName;
private Integer count;
private Date updatedDate;
@OneToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "product_groupId")
private ProductGroup productGroup;
public Long getAuditId() {
	return auditId;
}
@Override
public String toString() {
	return "ProductAudit [auditId=" + auditId + ", productGroupName=" + productGroupName + ", count=" + count
			+ ", updatedDate=" + updatedDate + ", productGroup=" + productGroup + "]";
}
public void setAuditId(Long auditId) {
	this.auditId = auditId;
}
public String getProductGroupName() {
	return productGroupName;
}
public void setProductGroupName(String productGroupName) {
	this.productGroupName = productGroupName;
}
public Integer getCount() {
	return count;
}
public void setCount(Integer count) {
	this.count = count;
}
public Date getUpdatedDate() {
	return updatedDate;
}
public void setUpdatedDate(Date updatedDate) {
	this.updatedDate = updatedDate;
}
public ProductAudit() {
	super();
}
public ProductGroup getProductGroup() {
	return productGroup;
}
public void setProductGroup(ProductGroup productGroup) {
	this.productGroup = productGroup;
}
}
