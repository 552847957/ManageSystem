package com.sell.service;

import java.io.Serializable;

import com.sell.service.Type.TypeEntity;

/**
 * 拍品类 可序列化通过网络传输
 */
public class Entity implements Serializable {

	/* 序列化对象的版本 */
	private static final long serialVersionUID = -294906429126537119L;

	/* 拍品名字 */
	private String name;

	/* 拍品属性枚举 */
	private TypeEntity type;

	/* 出售人 */
	private Person seller;

	/* 拍品起价 */
	private int priceStart;

	/* 拍品一次最低喊价 */
	private int priceMin;


	/* 最终拍卖价格 */ //-1表示还未售出
	private int priceFinal;

	/* 最终拍卖人 */
	private Person buyer;

	/* 拍卖品介绍 */
	private String info;

	/**
	 * 禁止无参构造实例
	 */
	private Entity() {
	}

	/**
	 * 拍品有参构造方法
	 * 
	 * @param name
	 *            拍品名字
	 * @param type
	 *            拍品类型
	 * @param seller
	 *            出售者
	 * @param priceStart
	 *            拍品起价
	 * @param priceMin
	 *            拍品最低喊价
	 * @param priceFinal
	 *            最终拍卖价格
	 * @param buyer
	 *            最终拍卖人
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
	 * 重写toString方法 序列化对象传输
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
	 * 返回拍品出售人
	 * 
	 * @return seller 拍品出售人
	 */
	public Person getSeller() {
		return seller;
	}

	/**
	 * 返回拍品最终拍卖人
	 * 
	 * @return buyer 拍品最终拍卖人
	 */
	public Person getBuyer() {
		return buyer;
	}

	/**
	 * 返回拍品名字
	 * 
	 * @return 拍品名字
	 */
	public String getName() {
		return name;
	}

	/**
	 * 返回拍品类型
	 * 
	 * @return 拍品类型
	 */
	public TypeEntity getType() {
		return type;
	}

	/**
	 * 返回拍品起价
	 * 
	 * @return 拍品起价
	 */
	public int getPriceStart() {
		return priceStart;
	}

	/**
	 * 返回拍品最低喊价
	 * 
	 * @return 拍品最低喊价
	 */
	public int getPriceMin() {
		return priceMin;
	}


	/**
	 * 返回拍品最终拍卖价格
	 * 
	 * @return 拍品最终拍卖价格
	 */
	public int getPriceFinal() {
		return priceFinal;
	}

	/**
	 * 设置拍品名字
	 * 
	 * @param name
	 *            拍品新名字
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置拍品类型
	 * 
	 * @param account
	 *            拍品新类型
	 */
	public void setType(TypeEntity type) {
		this.type = type;
	}

	/**
	 * 设置拍品起价
	 * 
	 * @param account
	 *            拍品新起价
	 */
	public void setPriceStart(int priceStart) {
		this.priceStart = priceStart;
	}

	/**
	 * 设置拍品最低喊价
	 * 
	 * @param account
	 *            拍品新最低喊价
	 */
	public void setPriceMin(int priceMin) {
		this.priceMin = priceMin;
	}


	/**
	 * 设置拍品最低喊价
	 * 
	 * @param account
	 *            拍品新最低喊价
	 */
	public void setPriceFinal(int priceFinal) {
		this.priceFinal = priceFinal;
	}

	/**
	 * 设置拍品出售人
	 * 
	 * @param seller
	 *            拍品出售人
	 */
	public void setSeller(Person seller) {
		this.seller = seller;
	}

	/**
	 * 设置拍品最终拍卖人
	 * 
	 * @param buyer
	 *            拍品最终拍卖人
	 */
	public void setBuyer(Person buyer) {
		this.buyer = buyer;
	}

}
