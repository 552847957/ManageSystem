package com.sell.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * ��Ʒ��ֹʱ���� �����л�ͨ�����紫��
 */
public class time implements Serializable {

	/* ���л�����İ汾 */
	private static final long serialVersionUID = -294906429126537119L;

	/* ��Ʒ���� */
	private String name;

	/* ��Ʒ��ǰ���� */
	private int priceNow;

	/* ������ֹʱ�� */
	private Date deadTime;

	/* ����ʱ���ʽ�� */
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
	 * ��ӷ���
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
