package com.his.entity;

public class Doctor {
	private String doctor_id;
	private int departament_id;
	private String d_name;
	private int d_age;
	private String d_sex;
	private int count;
	private String departament_name;
	
	
	public String getDepartament_name() {
		return departament_name;
	}
	public void setDepartament_name(String departament_name) {
		this.departament_name = departament_name;
	}
	public String getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}
	public int getDepartament_id() {
		return departament_id;
	}
	public void setDepartament_id(int departament_id) {
		this.departament_id = departament_id;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public int getD_age() {
		return d_age;
	}
	public void setD_age(int d_age) {
		this.d_age = d_age;
	}
	public String getD_sex() {
		return d_sex;
	}
	public void setD_sex(String d_sex) {
		this.d_sex = d_sex;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
