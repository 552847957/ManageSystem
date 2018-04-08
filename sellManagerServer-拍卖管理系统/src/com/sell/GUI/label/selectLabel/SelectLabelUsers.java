package com.sell.GUI.label.selectLabel;


import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sell.GUI.label.displayLabel.display;

import tools.*;

/**
 * 添加标签选项卡
 * @author Zhang
 */
public class SelectLabelUsers extends JLabel implements resource{
	
	JFrame jf;
	
	/** 用户显示 */
	public display displayUsers;
	
	/**
	 * 序列化标志
	 */
	private static final long serialVersionUID = 3429342403323465559L;

	public SelectLabelUsers(Icon img,JFrame jf){
		super(img);
		this.jf=jf;
		displayUsers=new display("Person",jf);
		
		//显示卡位置
		displayUsers.setBounds(7, 5, 540, 600);
		
		// 将组件加入容器
		this.add(displayUsers);
	}

}
