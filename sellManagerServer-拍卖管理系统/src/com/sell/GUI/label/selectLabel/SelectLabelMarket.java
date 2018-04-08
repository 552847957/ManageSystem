package com.sell.GUI.label.selectLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sell.GUI.button.ImageButton;
import com.sell.GUI.label.displayLabel.display;
import com.sell.service.Type.TypeEntity;

import tools.resource;
import tools.tools;

public class SelectLabelMarket extends JLabel implements MouseListener,resource{
	
	/**
	 * ���л���־
	 */
	private static final long serialVersionUID = 3429342403323465559L;
	
	// �г���ǩѡ���ť
	/** ���水ť */
	public ImageButton buttonAntique = new ImageButton(imageAntique, imageAntiqueOn,0);

	/** ������ť */
	public ImageButton buttonCar = new ImageButton(imageCar, imageCarOn,1);

	/** ������ť */
	public ImageButton buttonHouse = new ImageButton(imageHouse, imageHouseOn,2);
	
	/** �����г�ѡ���ť */
	public ArrayList<ImageButton> jbsMarket = new ArrayList<>();
	
	JFrame jf;
	
	/** ����Ʒ��ʾ */
	public display displayMarket;

	public SelectLabelMarket(Icon img,JFrame jf){
		super(img);
		this.jf=jf;
		
		displayMarket=new display("Entity",jf);
		jbsMarket.add(buttonAntique);
		jbsMarket.add(buttonCar);
		jbsMarket.add(buttonHouse);
		
		// ͼƬƽ�� ���������λ��
		tools.setIcon(buttonAntique, 7, 5, 176, 50);
		tools.setIcon(buttonCar, 183, 5, 175, 50);
		tools.setIcon(buttonHouse, 358, 5, 175, 50);
		
		//��ʾ��λ��
		displayMarket.setBounds(7, 55, 540, 600);
		
		// �������͸��
		for (ImageButton j : jbsMarket) {
			j.setOpaque(false);
		}
		
		// �������������
		this.add(displayMarket);
		// ��ǩ�г�ѡ���ť
		for (ImageButton j : jbsMarket) {
			this.add(j);
		}
		
		//�������������
		for (ImageButton j : jbsMarket) {
			j.addMouseListener(this);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// �г�ѡ�ť
		if (jbsMarket.contains(e.getSource())) {
			ImageButton t = (ImageButton) e.getSource();
			for (ImageButton j : jbsMarket) {
				j.setImg(j.getImgOff());
			}
			t.setImg(t.getImgOn());
			displayMarket.refreshDisplay(sqlOp.getEntityFromType(t.getSelect()));
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}