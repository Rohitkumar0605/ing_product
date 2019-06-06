package com.product.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProductGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productGroupId;
	private String productGroupName;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	public Long getProductGroupId() {
		return productGroupId;
	}
	public ProductGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setProductGroupId(Long productGroupId) {
		this.productGroupId = productGroupId;
	}
	public String getProductGroupName() {
		return productGroupName;
	}
	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "ProductGroup [productGroupId=" + productGroupId + ", productGroupName=" + productGroupName
				+ ", createDate=" + createDate + "]";
	}
}
