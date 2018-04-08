package com.sell.GUI.button;



import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;

import tools.tools;

/**
 * ͼƬ��ť
 */
public class ImageButton extends JButton{
	/**
	 * ���л���־
	 */
	private static final long serialVersionUID = 8591389135023188763L;
	
	//��ť����
	JLabel jl=new JLabel("");

	//�ǵ���ͼƬ
	Icon imgOff;
	
	//����ͼƬ
	Icon imgOn;
	
	//ָ���ѡ�
	int select;

	//�Ƿ񱻵���
	boolean isLight=false;

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		jl.setText(text);
	}

	/* ��ʼ�� */
	private void Init(){
		jl.setBounds(0, 0,this.getWidth(), this.getHeight());
		this.add(jl);
	}
	
	/**
	 * ��ʾ���ֵ�ͼƬ��ť
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
	 * ��ָ��ѡ���ͼƬ��ť
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
	 * ָ��ѡ���ͼƬ��ť
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
	 * @return the isLight �����Ƿ񱻵���
	 */
	public boolean getIsLight() {
		return isLight;
	}

	/**
	 * @param isLight the isLight to set �����Ƿ񱻵���
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
	 * ���õ�ǰ��ʾͼƬ
	 * @param img
	 */
	public void setImg(Icon img){
		//���õ�ǰ��ʾͼƬ
		this.setIcon(img);
		
		//ͼƬƽ��
		tools.setIcon(this, this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	
	/**
	 * @return the select ����ָ���ѡ�
	 */
	public int getSelect() {
		return select;
	}

	/**
	 * @param select the select to set ����ָ���ѡ�
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
