package com.sell.GUI.label.selectLabel;


import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sell.GUI.label.displayLabel.display;

import tools.*;

/**
 * ��ӱ�ǩѡ�
 * @author Zhang
 */
public class SelectLabelUsers extends JLabel implements resource{
	
	JFrame jf;
	
	/** �û���ʾ */
	public display displayUsers;
	
	/**
	 * ���л���־
	 */
	private static final long serialVersionUID = 3429342403323465559L;

	public SelectLabelUsers(Icon img,JFrame jf){
		super(img);
		this.jf=jf;
		displayUsers=new display("Person",jf);
		
		//��ʾ��λ��
		displayUsers.setBounds(7, 5, 540, 600);
		
		// �������������
		this.add(displayUsers);
	}

}
