package com.his.entity;

import java.math.BigDecimal;

public class Drug {
	private int id; //药品的编号
	private String drug_name;
	private String model;
	private double drug_price;
	private String drug_product;
	private int cost_type;
	private String key;
	private int count;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getDrug_price() {
		return drug_price;
	}
	public void setDrug_price(double drug_price) {
		this.drug_price = drug_price;
	}
	public String getDrug_name() {
		return drug_name;
	}
	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getDrug_product() {
		return drug_product;
	}
	public void setDrug_product(String drug_product) {
		this.drug_product = drug_product;
	}
	public int getCost_type() {
		return cost_type;
	}
	public void setCost_type(int cost_type) {
		this.cost_type = cost_type;
	}
	
	
}
