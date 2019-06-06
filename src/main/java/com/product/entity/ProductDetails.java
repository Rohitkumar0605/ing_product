package com.product.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProductDetails {
	@Id
	@GeneratedValue
	private Long productNameId;
	private String productName;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	private String interestRate;
	private double percentage;
	private String special;
	private String putInAndWithdrawel;
	private Integer minimalInvestment;

	public ProductDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Integer maxInvestment;

	@Override
	public String toString() {
		return "ProductDetails [productNameId=" + productNameId + ", productName=" + productName + ", createDate="
				+ createDate + ", interestRate=" + interestRate + ", percentage=" + percentage + ", special=" + special
				+ ", putInAndWithdrawel=" + putInAndWithdrawel + ", minimalInvestment=" + minimalInvestment
				+ ", maxInvestment=" + maxInvestment + ", duration=" + duration + ", productGroup=" + productGroup
				+ "]";
	}

	private String duration;
	@ManyToOne
	@JoinColumn(name = "product_group_id")
	private ProductGroup productGroup;

	public Long getProductNameId() {
		return productNameId;
	}

	public void setProductNameId(Long productNameId) {
		this.productNameId = productNameId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getPutInAndWithdrawel() {
		return putInAndWithdrawel;
	}

	public void setPutInAndWithdrawel(String putInAndWithdrawel) {
		this.putInAndWithdrawel = putInAndWithdrawel;
	}

	public Integer getMinimalInvestment() {
		return minimalInvestment;
	}

	public void setMinimalInvestment(Integer minimalInvestment) {
		this.minimalInvestment = minimalInvestment;
	}

	public Integer getMaxInvestment() {
		return maxInvestment;
	}

	public void setMaxInvestment(Integer maxInvestment) {
		this.maxInvestment = maxInvestment;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public ProductGroup getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}

}
