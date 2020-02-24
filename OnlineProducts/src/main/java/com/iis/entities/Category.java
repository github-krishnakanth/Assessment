package com.iis.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable{
	private static final long serialVersionUID = -7526436590091747166L;
	
	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int categoryId;
	@Column(name = "category_name")
	private String categoryName;
	@Column(name = "for_whome")
	private String forWhome;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Set<Product> products;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getForWhome() {
		return forWhome;
	}

	public void setForWhome(String forWhome) {
		this.forWhome = forWhome;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
