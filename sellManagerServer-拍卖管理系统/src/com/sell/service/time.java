package com.sell.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 拍品截止时间类 可序列化通过网络传输
 */
public class time implements Serializable {

	/* 序列化对象的版本 */
	private static final long serialVersionUID = -294906429126537119L;

	/* 拍品名字 */
	private String name;

	/* 拍品当前报价 */
	private int priceNow;

	/* 拍卖截止时间 */
	private Date deadTime;

	/* 日期时间格式化 */
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * @param name
	 * @param priceNow
	 * @param deadTime
	 */
	public time(String name, String priceNow, String deadTime) {
		super();
		
		this.name = name;
		this.priceNow = Integer.valueOf(priceNow);
		try {
			this.deadTime = sdf.parse(deadTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param name
	 * @param priceNow
	 * @param deadTime
	 */
	public time(String name, String priceNow, Date deadTime) {
		super();

		this.name = name;
		this.priceNow = Integer.valueOf(priceNow);
		this.deadTime = deadTime;
	}
	
	/**
	 * 添加分钟
	 * @param minutes
	 */
	public void addDeadTimeMinutes(int minutes){
		deadTime.setTime(deadTime.getTime() + minutes*60*1000);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the priceNow
	 */
	public int getPriceNow() {
		return priceNow;
	}

	/**
	 * @return the deadTime
	 */
	public String getDeadTimeString() {
		return sdf.format(deadTime);
	}
	/**
	 * @return the deadTime
	 */
	public Date getDeadTimeDate() {
		return deadTime;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param priceNow the priceNow to set
	 */
	public void setPriceNow(int priceNow) {
		this.priceNow = priceNow;
	}

	/**
	 * @param deadTime the deadTime to set
	 */
	public void setDeadTime(String deadTime) {
		try {
			this.deadTime = sdf.parse(deadTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
