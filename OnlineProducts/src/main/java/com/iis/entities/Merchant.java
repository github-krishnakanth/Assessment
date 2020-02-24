package com.iis.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "merchant")
public class Merchant implements Serializable{
	private static final long serialVersionUID = 5332714485972658732L;
	
	@Id
	@Column(name = "merchant_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int merchantId;
	@Column(name = "merchant_name")
	private String merchantName;
	private String description;
	
	@OneToMany
	@JoinColumn(name = "merchant_id")
	private Set<Product> products;

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
}
