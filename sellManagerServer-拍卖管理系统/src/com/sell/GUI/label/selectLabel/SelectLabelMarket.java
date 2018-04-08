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
	 * 序列化标志
	 */
	private static final long serialVersionUID = 3429342403323465559L;
	
	// 市场标签选项卡按钮
	/** 古玩按钮 */
	public ImageButton buttonAntique = new ImageButton(imageAntique, imageAntiqueOn,0);

	/** 汽车按钮 */
	public ImageButton buttonCar = new ImageButton(imageCar, imageCarOn,1);

	/** 房产按钮 */
	public ImageButton buttonHouse = new ImageButton(imageHouse, imageHouseOn,2);
	
	/** 二级市场选项卡按钮 */
	public ArrayList<ImageButton> jbsMarket = new ArrayList<>();
	
	JFrame jf;
	
	/** 拍卖品显示 */
	public display displayMarket;

	public SelectLabelMarket(Icon img,JFrame jf){
		super(img);
		this.jf=jf;
		
		displayMarket=new display("Entity",jf);
		jbsMarket.add(buttonAntique);
		jbsMarket.add(buttonCar);
		jbsMarket.add(buttonHouse);
		
		// 图片平铺 并设置组件位置
		tools.setIcon(buttonAntique, 7, 5, 176, 50);
		tools.setIcon(buttonCar, 183, 5, 175, 50);
		tools.setIcon(buttonHouse, 358, 5, 175, 50);
		
		//显示卡位置
		displayMarket.setBounds(7, 55, 540, 600);
		
		// 设置组件透明
		for (ImageButton j : jbsMarket) {
			j.setOpaque(false);
		}
		
		// 将组件加入容器
		this.add(displayMarket);
		// 标签市场选项卡按钮
		for (ImageButton j : jbsMarket) {
			this.add(j);
		}
		
		//设置组件监听器
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
		// 市场选项按钮
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