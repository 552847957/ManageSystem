package com.sell.service;

import java.io.Serializable;

import com.sell.service.Type.TypePerson;

/**
 * 出售/竞拍者类
 */
public class Person implements Serializable {

	/* 序列化对象的版本 */
	private static final long serialVersionUID = -3468840783358004734L;

	/* 出售/竞拍者 名字 */
	private String name;

	/* 出售/竞拍者属性 */
	private TypePerson type;

	/* 联系方式 */
	private String telephone;

	/* 邮箱地址 */
	private String email;

	/* 住址 */
	private String address;

	/* 登录账号 */
	private String account;

	/* 登录密码 */
	private String password;

	/**
	 * 禁止无参构造方法
	 */
	private Person(){
		
	}
	
	/**
	 * 出售/竞拍者初始化有参构造方法
	 * 
	 * @param name
	 *            名字
	 * @param type
	 *            类型属性
	 * @param telephone
	 *            联系方式
	 * @param email
	 *            邮件地址
	 * @param address
	 *            住址
	 * @param account
	 *            账号
	 * @param password
	 *            密码
	 */
	public Person(String name, TypePerson type, String telephone, String email, String address, String account,
			String password) {
		this.name = name;
		this.type = type;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.account = account;
		this.password = password;
	}

	/**
	 * 重写toString方法 序列化对象传输
	 */
	@Override
	public String toString() {
		return "Person [name=" + name + ", type=" + type + ", telephone=" + telephone + ", email=" + email
				+ ", address=" + address + ", account=" + account + ", password=" + password + "]";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public TypePerson getType() {
		return type;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(TypePerson type) {
		this.type = type;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
