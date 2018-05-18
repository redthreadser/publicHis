package com.his.entity;

import java.util.Date;

public class Examine {
	private int id;
	private String p_number ;
	private String doctor_id;
	private int cost_type;
	private String project_name;
	private String ex_result;
	private Date ex_time;
	private String option_name;
	private int ex_state;
	private String add_content;
	private String p_name;
	private int p_age;
	private String p_sex;
	private String d_name;
	private String department_name;
	private double sum_price;
	private int number;
	private String d_time;
	private double price; //单价
	private String model; //规格
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getD_time() {
		return d_time;
	}
	public void setD_time(String d_time) {
		this.d_time = d_time;
	}
	public String getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}
	public double getSum_price() {
		return sum_price;
	}
	public void setSum_price(double sum_price) {
		this.sum_price = sum_price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getP_number() {
		return p_number;
	}
	public void setP_number(String p_number) {
		this.p_number = p_number;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_age() {
		return p_age;
	}
	public void setP_age(int p_age) {
		this.p_age = p_age;
	}
	public String getP_sex() {
		return p_sex;
	}
	public void setP_sex(String p_sex) {
		this.p_sex = p_sex;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCost_type() {
		return cost_type;
	}
	public void setCost_type(int cost_type) {
		this.cost_type = cost_type;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getEx_result() {
		return ex_result;
	}
	public void setEx_result(String ex_result) {
		this.ex_result = ex_result;
	}
	public Date getEx_time() {
		return ex_time;
	}
	public void setEx_time(Date ex_time) {
		this.ex_time = ex_time;
	}
	public String getOption_name() {
		return option_name;
	}
	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}
	public int getEx_state() {
		return ex_state;
	}
	public void setEx_state(int ex_state) {
		this.ex_state = ex_state;
	}
	public String getAdd_content() {
		return add_content;
	}
	public void setAdd_content(String add_content) {
		this.add_content = add_content;
	}
	
}
