package com.sell.GUI.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sell.GUI.MainFrame;
import com.sell.service.Person;

import tools.resource;

/**
 * 拍卖系统管理员登录界面
 */
public class LoginDialog extends JDialog implements ActionListener, resource {

	/* 序列化对象的版本 */
	private static final long serialVersionUID = -7790766426858844936L;

	/* 背景图片Label */
	public static JLabel background = new JLabel(new ImageIcon(dirSrcImg + "背景.png"));

	/* 选择登录方式 */
	public JComboBox<String> J_select = new JComboBox<>(new String[] { "管理员", "普通用户" });

	/* 账号标签 */
	public static JLabel L_account = new JLabel(new ImageIcon(dirSrcImg + "用户名.png"));

	/* 密码标签 */
	public static JLabel L_password = new JLabel(new ImageIcon(dirSrcImg + "密码.png"));

	/* 账号输入框 */
	public static JTextField account = new JTextField(10);

	/* 密码输入框 */
	public static JPasswordField password = new JPasswordField(10);

	/* 登录按钮 */
	public static JButton button_confirm = new JButton(new ImageIcon(dirSrcImg + "登录按钮.png"));

	/* 最大错误次数 */
	int amount = 3;

	JFrame jf;
	
	public LoginDialog(JFrame jf, String title) {
		super(jf, title);
		this.jf=jf;

		/* 禁止resize */
		setResizable(false);

		/* 该窗口不关闭，不能操作其他窗口 */
		setModal(true);

		/* 无需布局 */
		background.setLayout(null);

		/* 加入全部组件 */
		add(background);
		background.add(J_select);
		background.add(L_account);
		background.add(account);
		background.add(L_password);
		background.add(password);
		background.add(button_confirm);

		/* 设置组件透明 */
		J_select.setOpaque(false);
		L_account.setOpaque(false);
		account.setOpaque(false);
		L_password.setOpaque(false);
		password.setOpaque(false);
		button_confirm.setOpaque(false);

		/* 设置各个组件位置 */
		J_select.setBounds(370, 140, 78, 20);
		L_account.setBounds(97, 139, 78, 23);
		account.setBounds(190, 140, 170, 22);
		L_password.setBounds(97, 185, 78, 22);
		password.setBounds(191, 186, 170, 22);
		button_confirm.setBounds(97, 233, 271, 30);

		/* 设置窗体大小及位置 */
		setBounds(500, 200, 462, 350);

		/* 按钮监听事件 */
		button_confirm.addActionListener(this);

		/* 点击关闭按钮执行退出程序操作 */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		/* 显示窗体 */
		setVisible(true);
	}

	/* 事件监听器 */
	public void actionPerformed(ActionEvent e) {
		/* 如果event对象源为登录按钮 */
		if (e.getSource() == button_confirm) {

			/* 输入不能为空 */
			if (account.getText().equals("") || password.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "不能为空", "警告", JOptionPane.WARNING_MESSAGE);
				return;
			}

			// 管理员登录
			if (((String) J_select.getSelectedItem()).equals("管理员")) {
				/* 登录数据库 */
				if (sqlOp.getConnection(account.getText(), password.getText())) {
					((MainFrame)jf).setModel((String) J_select.getSelectedItem());
					((MainFrame)jf).setAccount(account.getText());
					((MainFrame)jf).setTitle("拍卖行-管理员登录-"+account.getText());
					setVisible(false);
				}
				/* 输入账号密码错误，减少能执行次数 */
				else {
					amount--;
					JOptionPane.showMessageDialog(this, "账户不存在或密码错误 可重试次数:" + amount, "警告",
							JOptionPane.WARNING_MESSAGE);
					if (amount == 0) {
						System.exit(0);
					}
				}
			}
			// 普通用户登录
			else if (((String) J_select.getSelectedItem()).equals("普通用户")) {
				/* 登录数据库 */
				sqlOp.getConnection("root", "root");
				try {
					Person p=
							sqlOp.getPersonFromAccount(account.getText(), sqlOp.con.createStatement());
					if (p!=null) {
						if(password.getText().equals(p.getPassword())){
							((MainFrame)jf).setModel((String) J_select.getSelectedItem());
							((MainFrame)jf).setAccount(account.getText());
							((MainFrame)jf).setTitle("拍卖行-用户登录-"+account.getText());
							setVisible(false);
						}else{
							amount--;
							JOptionPane.showMessageDialog(this, "账户不存在或密码错误 可重试次数:" + amount, "警告",
									JOptionPane.WARNING_MESSAGE);
							if (amount == 0) {
								System.exit(0);
							}
						}
					}else{
						JOptionPane.showMessageDialog(this, "账户不存在", "警告",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e1) {
				}

			}

		}
	}

}
