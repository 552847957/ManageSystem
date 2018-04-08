package com.sell.GUI.button;



import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

import tools.tools;

/**
 * 图片按钮
 */
public class ImageButton extends JButton{
	/**
	 * 序列化标志
	 */
	private static final long serialVersionUID = 8591389135023188763L;
	
	//按钮标题
	JLabel jl=new JLabel("");

	//非点亮图片
	Icon imgOff;
	
	//点亮图片
	Icon imgOn;
	
	//指向的选项卡
	int select;

	//是否被点亮
	boolean isLight=false;

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		jl.setText(text);
	}

	/* 初始化 */
	private void Init(){
		jl.setBounds(0, 0,this.getWidth(), this.getHeight());
		this.add(jl);
	}
	
	/**
	 * 显示文字的图片按钮
	 * @param img
	 * @param imgOn
	 */
	public ImageButton(Icon img,Icon imgOn,String text){
		super(img);
		this.imgOff=img;
		this.imgOn=imgOn;
		jl.setText(text);
		Init();
	}
	
	/**
	 * 非指向选项卡的图片按钮
	 * @param img
	 * @param imgOn
	 */
	public ImageButton(Icon img,Icon imgOn){
		super(img);
		this.imgOff=img;
		this.imgOn=imgOn;
		Init();
	}

	/**
	 * 指向选项卡的图片按钮
	 * @param img
	 * @param imgOn
	 * @param select
	 */
	public ImageButton(Icon img,Icon imgOn,int select){
		super(img);
		this.imgOff=img;
		this.imgOn=imgOn;
		this.select=select;
		Init();
	}
	

	/**
	 * @return the isLight 返回是否被点亮
	 */
	public boolean getIsLight() {
		return isLight;
	}

	/**
	 * @param isLight the isLight to set 设置是否被点亮
	 */
	public void setLight(boolean isLight) {
		this.isLight = isLight;
		if(isLight==true){
			this.setImg(getImgOn());
			
		}else{
			this.setImg(getImgOff());
		}
	}

	/**
	 * 设置当前显示图片
	 * @param img
	 */
	public void setImg(Icon img){
		//设置当前显示图片
		this.setIcon(img);
		
		//图片平铺
		tools.setIcon(this, this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	/**
	 * @return the select 返回指向的选项卡
	 */
	public int getSelect() {
		return select;
	}

	/**
	 * @param select the select to set 设置指向的选项卡
	 */
	public void setSelect(int select) {
		this.select = select;
	}
	
	/**
	 * @return the imgOff
	 */
	public Icon getImgOff() {
		return imgOff;
	}

	/**
	 * @return the imgOn
	 */
	public Icon getImgOn() {
		return imgOn;
	}
	
	/**
	 * @param imgOff the imgOff to set
	 */
	public void setImgOff(Icon img) {
		this.imgOff=img;
	}

	/**
	 * @param imgOn the imgOn to set
	 */
	public void setImgOn(Icon imgOn) {
		this.imgOn = imgOn;
	}
}
