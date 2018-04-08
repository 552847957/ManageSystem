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
 * ����ϵͳ������
 */
public class MainFrame extends JFrame implements  MouseListener, resource {

	/**
	 * ���л���־
	 */
	private static final long serialVersionUID = 5806651875362651255L;

	/** ��¼���� */
	public static LoginDialog loginDlg;

	// ����ѡ���ť(ָ��ѡ�)
	/** �г���ť */
	public ImageButton buttonMarket = new ImageButton(imageMarket, imageMarketOn, 1);

	/** �û���ť */
	public ImageButton buttonUser = new ImageButton(imageUser, imageUserOn, 2);

	// ѡ���ǩ
	/** �г���ǩ װ���淿���Ȱ�ť */
	public SelectLabelMarket labelMarket;
	
	/** �û���ǩ */
	public SelectLabelUsers labelUsers;
	
	/** ������Ҫѡ���ť */
	public ArrayList<ImageButton> jbsMain = new ArrayList<>();

	/** ѡ���ǩ */
	public ArrayList<JLabel> jls = new ArrayList<>();

	/** ����Ա������ͨ�û�ģʽ */
	String model="";
	
	//�û���
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

	/** ���ó�ʼ������ */
	public MainFrame() {
		loginDlg = new LoginDialog(this, "�����з�����-����Ա��¼");
		init();
		this.setVisible(true);
		// Server s=new Server();
	}

	/** �������ʼ�� */
	public void init() {
		
		labelMarket= new SelectLabelMarket(imageSelect,this);
		labelUsers = new SelectLabelUsers(imageSelect,this);
		// �������������
		//this.setTitle("�����з�����");

		// ���������洰�ڳߴ� �����ʾ
		this.setBounds(400, 100, 545, 600);
		this.setLayout(null);
		this.setResizable(false);
		this.setBackground(Color.white);

		jbsMain.add(buttonMarket);
		jbsMain.add(buttonUser);

		jls.add(labelMarket);
		jls.add(labelUsers);

		// ѡ�λ��
		for (int i = 0; i < jls.size(); i++) {
			tools.setIcon(jls.get(i), 540 * i, 140, 540, 400);
		}
		

		// ����ѡ���ťλ��
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
		
		// ��ǩѡ�
		for (JLabel j : jls) {
			this.add(j);
		}

		// ��ǩ��ѡ���ť
		for (ImageButton j : jbsMain) {
			this.add(j);
		}

		// �������������
		for (ImageButton j : jbsMain) {
			j.addMouseListener(this);
		}


		this.addMouseListener(this);

		// ����=false
		this.setVisible(false);

		// ����Ĭ���˳�����
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// ��ѡ�ť
		if (jbsMain.contains(e.getSource())) {
			ImageButton t = (ImageButton) e.getSource();
			for (ImageButton j : jbsMain) {
				j.setImg(j.getImgOff());
			}
			t.setImg(t.getImgOn());

			// �л�ѡ�
			switchSelect(t.getSelect());
		} 
	}

	/**
	 * �л�ѡ�
	 * 
	 * @param select
	 *            1��ʾmarket 2��ʾusers
	 */
	public void switchSelect(final int select) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				JLabel tempLabel = null;

				// ����select����Ŀ�� 1��ʾmarket 2��ʾusers
				switch (select) {
				case 1:
					tempLabel = labelMarket;
					break;
				case 2:
					tempLabel = labelUsers;
					break;
				}

				// �����л��ƶ���Ч
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
