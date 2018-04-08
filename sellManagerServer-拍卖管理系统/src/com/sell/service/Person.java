package com.sell.service;

import java.io.Serializable;

import com.sell.service.Type.TypePerson;

/**
 * ����/��������
 */
public class Person implements Serializable {

	/* ���л�����İ汾 */
	private static final long serialVersionUID = -3468840783358004734L;

	/* ����/������ ���� */
	private String name;

	/* ����/���������� */
	private TypePerson type;

	/* ��ϵ��ʽ */
	private String telephone;

	/* �����ַ */
	private String email;

	/* סַ */
	private String address;

	/* ��¼�˺� */
	private String account;

	/* ��¼���� */
	private String password;

	/**
	 * ��ֹ�޲ι��췽��
	 */
	private Person(){
		
	}
	
	/**
	 * ����/�����߳�ʼ���вι��췽��
	 * 
	 * @param name
	 *            ����
	 * @param type
	 *            ��������
	 * @param telephone
	 *            ��ϵ��ʽ
	 * @param email
	 *            �ʼ���ַ
	 * @param address
	 *            סַ
	 * @param account
	 *            �˺�
	 * @param password
	 *            ����
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
	 * ��дtoString���� ���л�������
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
