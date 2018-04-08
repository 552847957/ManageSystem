package com.sell.service;

import java.io.Serializable;

import com.sell.service.Type.TypeEntity;

/**
 * ��Ʒ�� �����л�ͨ�����紫��
 */
public class Entity implements Serializable {

	/* ���л�����İ汾 */
	private static final long serialVersionUID = -294906429126537119L;

	/* ��Ʒ���� */
	private String name;

	/* ��Ʒ����ö�� */
	private TypeEntity type;

	/* ������ */
	private Person seller;

	/* ��Ʒ��� */
	private int priceStart;

	/* ��Ʒһ����ͺ��� */
	private int priceMin;


	/* ���������۸� */ //-1��ʾ��δ�۳�
	private int priceFinal;

	/* ���������� */
	private Person buyer;

	/* ����Ʒ���� */
	private String info;

	/**
	 * ��ֹ�޲ι���ʵ��
	 */
	private Entity() {
	}

	/**
	 * ��Ʒ�вι��췽��
	 * 
	 * @param name
	 *            ��Ʒ����
	 * @param type
	 *            ��Ʒ����
	 * @param seller
	 *            ������
	 * @param priceStart
	 *            ��Ʒ���
	 * @param priceMin
	 *            ��Ʒ��ͺ���
	 * @param priceFinal
	 *            ���������۸�
	 * @param buyer
	 *            ����������
	 */
	public Entity(String name, TypeEntity type, Person seller, int priceStart, int priceMin, int priceFinal,
			Person buyer,String info) {
		this.name = name;
		this.type = type;
		this.seller = seller;
		this.priceStart = priceStart;
		this.priceMin = priceMin;
		this.priceFinal = priceFinal;
		this.buyer = buyer;
		this.info = info;
	}

	/**
	 * ��дtoString���� ���л�������
	 */
	@Override
	public String toString() {
		return "Entity [name=" + name + ", type=" + type + ", seller=" + seller + ", priceStart=" + priceStart
				+ ", priceMin=" + priceMin + ", priceFinal=" + priceFinal + ", buyer=" + buyer + "]";
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info
	 *            the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * ������Ʒ������
	 * 
	 * @return seller ��Ʒ������
	 */
	public Person getSeller() {
		return seller;
	}

	/**
	 * ������Ʒ����������
	 * 
	 * @return buyer ��Ʒ����������
	 */
	public Person getBuyer() {
		return buyer;
	}

	/**
	 * ������Ʒ����
	 * 
	 * @return ��Ʒ����
	 */
	public String getName() {
		return name;
	}

	/**
	 * ������Ʒ����
	 * 
	 * @return ��Ʒ����
	 */
	public TypeEntity getType() {
		return type;
	}

	/**
	 * ������Ʒ���
	 * 
	 * @return ��Ʒ���
	 */
	public int getPriceStart() {
		return priceStart;
	}

	/**
	 * ������Ʒ��ͺ���
	 * 
	 * @return ��Ʒ��ͺ���
	 */
	public int getPriceMin() {
		return priceMin;
	}


	/**
	 * ������Ʒ���������۸�
	 * 
	 * @return ��Ʒ���������۸�
	 */
	public int getPriceFinal() {
		return priceFinal;
	}

	/**
	 * ������Ʒ����
	 * 
	 * @param name
	 *            ��Ʒ������
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ������Ʒ����
	 * 
	 * @param account
	 *            ��Ʒ������
	 */
	public void setType(TypeEntity type) {
		this.type = type;
	}

	/**
	 * ������Ʒ���
	 * 
	 * @param account
	 *            ��Ʒ�����
	 */
	public void setPriceStart(int priceStart) {
		this.priceStart = priceStart;
	}

	/**
	 * ������Ʒ��ͺ���
	 * 
	 * @param account
	 *            ��Ʒ����ͺ���
	 */
	public void setPriceMin(int priceMin) {
		this.priceMin = priceMin;
	}


	/**
	 * ������Ʒ��ͺ���
	 * 
	 * @param account
	 *            ��Ʒ����ͺ���
	 */
	public void setPriceFinal(int priceFinal) {
		this.priceFinal = priceFinal;
	}

	/**
	 * ������Ʒ������
	 * 
	 * @param seller
	 *            ��Ʒ������
	 */
	public void setSeller(Person seller) {
		this.seller = seller;
	}

	/**
	 * ������Ʒ����������
	 * 
	 * @param buyer
	 *            ��Ʒ����������
	 */
	public void setBuyer(Person buyer) {
		this.buyer = buyer;
	}

}
