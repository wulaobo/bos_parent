package com.itheima.bos.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SimpleTimeZone;

/**
 * User entity. @author MyEclipse Persistence Tools
 * 用户实体
 */

public class User implements java.io.Serializable {

	// Fields
	private String id;
	private String username;
	private String password;
	private Double salary;
	private Date birthday;
	private String gender;
	private String station;
	private String telephone;
	private String remark;

	//用户与角色多对多
	private Set<Role> roles = new HashSet<Role>();

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public String getRoleNames() {
		String roleNames="";
		for (Role role:roles) {
			roleNames += role.getName()+" ";
		}
		return roleNames;
	}

	public String getBirthdayString() {
		if(birthday!=null){
		 String birthdayString = new SimpleDateFormat("yyyy-MM-dd").format(birthday);
		 return birthdayString;
		}else{
			return "暂无数据";
		}
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
    }

	public Double getSalary() {
		return this.salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", salary=" + salary +
				", birthday=" + birthday +
				", gender='" + gender + '\'' +
				", station='" + station + '\'' +
				", telephone='" + telephone + '\'' +
				", remark='" + remark + '\'' +

				'}';
	}
}