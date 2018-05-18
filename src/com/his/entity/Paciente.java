package com.his.entity;

import java.util.List;

public class Paciente {
	private String p_number;
	private String p_name;
	private String p_sex;
	private int p_age;
	private String p_tel;
	private double extraMoney;
	private int department_id;
	private String doctor_id;
	private String option_name;
	private String registo_time;
	private List<Examine> examines;
	private String d_name;
	private String d_time;
	
	
	
	public double getExtraMoney() {
		return extraMoney;
	}
	public void setExtraMoney(double extraMoney) {
		this.extraMoney = extraMoney;
	}
	public void setP_tel(String p_tel) {
		this.p_tel = p_tel;
	}
	public String getD_time() {
		return d_time;
	}
	public void setD_time(String d_time) {
		this.d_time = d_time;
	}
	public List<Examine> getExamines() {
		return examines;
	}
	public void setExamines(List<Examine> examines) {
		this.examines = examines;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getRegisto_time() {
		return registo_time;
	}
	public void setRegisto_time(String registo_time) {
		this.registo_time = registo_time;
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
	public String getP_sex() {
		return p_sex;
	}
	public void setP_sex(String p_sex) {
		this.p_sex = p_sex;
	}
	public int getP_age() {
		return p_age;
	}
	public void setP_age(int p_age) {
		this.p_age = p_age;
	}
	public String getP_tel() {
		return p_tel;
	}
	public void setExtraMoney(int extraMoney) {
		this.extraMoney = extraMoney;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public String getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(String doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getOption_name() {
		return option_name;
	}
	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}
}
