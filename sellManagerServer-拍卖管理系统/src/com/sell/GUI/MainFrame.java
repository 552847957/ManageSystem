package com.sell.GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sell.GUI.button.ImageButton;
import com.sell.GUI.dialog.LoginDialog;
import com.sell.GUI.label.displayLabel.display;
import com.sell.GUI.label.selectLabel.SelectLabelMarket;
import com.sell.GUI.label.selectLabel.SelectLabelUsers;

import tools.resource;
import tools.tools;

/**
 * 拍卖系统主界面
 */
public class MainFrame extends JFrame implements  MouseListener, resource {

	/**
	 * 序列化标志
	 */
	private static final long serialVersionUID = 5806651875362651255L;

	/** 登录窗口 */
	public static LoginDialog loginDlg;

	// 顶级选项卡按钮(指向选项卡)
	/** 市场按钮 */
	public ImageButton buttonMarket = new ImageButton(imageMarket, imageMarketOn, 1);

	/** 用户按钮 */
	public ImageButton buttonUser = new ImageButton(imageUser, imageUserOn, 2);

	// 选项卡标签
	/** 市场标签 装古玩房产等按钮 */
	public SelectLabelMarket labelMarket;
	
	/** 用户标签 */
	public SelectLabelUsers labelUsers;
	
	/** 顶级主要选项卡按钮 */
	public ArrayList<ImageButton> jbsMain = new ArrayList<>();

	/** 选项卡标签 */
	public ArrayList<JLabel> jls = new ArrayList<>();

	/** 管理员或者普通用户模式 */
	String model="";
	
	//用户名
	String account="";

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/** 调用初始化方法 */
	public MainFrame() {
		loginDlg = new LoginDialog(this, "拍卖行服务器-管理员登录");
		init();
		this.setVisible(true);
		// Server s=new Server();
	}

	/** 主界面初始化 */
	public void init() {
		
		labelMarket= new SelectLabelMarket(imageSelect,this);
		labelUsers = new SelectLabelUsers(imageSelect,this);
		// 设置主界面标题
		//this.setTitle("拍卖行服务器");

		// 设置主界面窗口尺寸 风格显示
		this.setBounds(400, 100, 545, 600);
		this.setLayout(null);
		this.setResizable(false);
		this.setBackground(Color.white);

		jbsMain.add(buttonMarket);
		jbsMain.add(buttonUser);

		jls.add(labelMarket);
		jls.add(labelUsers);

		// 选项卡位置
		for (int i = 0; i < jls.size(); i++) {
			tools.setIcon(jls.get(i), 540 * i, 140, 540, 400);
		}
		

		// 顶级选项卡按钮位置
		tools.setIcon(buttonMarket, 100, 20, 100, 100);
		tools.setIcon(buttonUser, getWidth()-200, 20, 100, 100);

		buttonMarket.setImg(buttonMarket.getImgOn());

		for (ImageButton j : jbsMain) {
			j.setOpaque(false);
		}

		for (JLabel j : jls) {
			j.setOpaque(false);
		}


		//labelUsers.add(displayUsers);
		
		// 标签选项卡
		for (JLabel j : jls) {
			this.add(j);
		}

		// 标签主选项卡按钮
		for (ImageButton j : jbsMain) {
			this.add(j);
		}

		// 设置组件监听器
		for (ImageButton j : jbsMain) {
			j.addMouseListener(this);
		}


		this.addMouseListener(this);

		// 可视=false
		this.setVisible(false);

		// 设置默认退出操作
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// 主选项按钮
		if (jbsMain.contains(e.getSource())) {
			ImageButton t = (ImageButton) e.getSource();
			for (ImageButton j : jbsMain) {
				j.setImg(j.getImgOff());
			}
			t.setImg(t.getImgOn());

			// 切换选项卡
			switchSelect(t.getSelect());
		} 
	}

	/**
	 * 切换选项卡
	 * 
	 * @param select
	 *            1表示market 2表示users
	 */
	public void switchSelect(final int select) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				JLabel tempLabel = null;

				// 根据select分析目标 1表示market 2表示users
				switch (select) {
				case 1:
					tempLabel = labelMarket;
					break;
				case 2:
					tempLabel = labelUsers;
					break;
				}

				// 进行切换移动特效
				if (tempLabel != null) {
					while (tempLabel.getX() != 0) {
						for (JLabel j : jls) {
							j.setBounds(j.getX() + 5 * (tempLabel.getX() > 0 ? -1 : 1), j.getY(), j.getWidth(),
									j.getHeight());
						}
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

			}
		}).start();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
	}





}
