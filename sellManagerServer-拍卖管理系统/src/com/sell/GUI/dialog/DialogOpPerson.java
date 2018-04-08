package com.sell.GUI.dialog;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.sell.service.Entity;
import com.sell.service.Person;
import com.sell.service.Type.TypeEntity;
import com.sell.service.Type.TypePerson;

import tools.resource;

/**
 * 拍卖系统管理员 修改、添加Person 界面
 */
public class DialogOpPerson extends JDialog implements ActionListener, resource {

	/* 序列化对象的版本 */
	private static final long serialVersionUID = -7790766426858844936L;

	/* 名字标签 */
	public static JLabel L_name = new JLabel("用户名字：");
	public static JTextField T_name = new JTextField(10);

	/* 类型标签 */
	public static JLabel L_type = new JLabel("类型：");
	public JComboBox<TypePerson> B_type = new JComboBox<>(TypePerson.values());
	/* telephone标签 */
	public static JLabel L_telephone = new JLabel("telephone：");
	public static JTextField T_telephone = new JTextField(20);
	/* email标签 */
	public static JLabel L_email = new JLabel("email：");
	public static JTextField T_email = new JTextField(20);
	/* address标签 */
	public static JLabel L_address = new JLabel("地址：");
	public static JTextField T_address = new JTextField(20);
	/* account标签 */
	public static JLabel L_account = new JLabel("账号：");
	public static JTextField T_account = new JTextField(20);
	/* password标签 */
	public static JLabel L_password = new JLabel("密码：");
	public static JTextField T_password = new JTextField(20);

	private ArrayList<JTextComponent> jtc = new ArrayList<>();

	public static JButton button_confirm = new JButton("确认");
	public static JButton button_cancel = new JButton("取消");

	/**
	 * 类型 Add为添加 Modify为修改
	 */
	String type;

	/**
	 * 用户账号
	 */
	String obj;

	public void Init() {
		/* 设置窗体大小及位置 */
		setBounds(500, 200, 300, 320);
		setLayout(null);

		JPanel j1 = new JPanel(new GridLayout(7, 2, 1, 5));

		JPanel j2 = new JPanel(new FlowLayout());

		/* 禁止resize */
		setResizable(false);

		/* 该窗口不关闭，不能操作其他窗口 */
		setModal(true);

		j1.setBounds(0, 0, this.getWidth() - 20, this.getHeight() -70);
		j2.setBounds(0, this.getHeight() -70, getWidth() - 20, getHeight()-50);

		/* 加入全部组件 */
		add(j1);
		add(j2);

		j1.add(L_name);
		j1.add(T_name);
		j1.add(L_type);
		j1.add(B_type);
		j1.add(L_telephone);
		j1.add(T_telephone);
		
		j1.add(L_email);
		j1.add(T_email);
		j1.add(L_address);
		j1.add(T_address);
		j1.add(L_account);
		j1.add(T_account);
		j1.add(L_password);
		j1.add(T_password);

		j2.add(button_confirm);
		j2.add(button_cancel);

		jtc.add(T_name);
		jtc.add(T_telephone);
		jtc.add(T_email);
		jtc.add(T_address);
		jtc.add(T_account);
		jtc.add(T_password);

		/* 按钮监听事件 */
		button_confirm.addActionListener(this);
		button_cancel.addActionListener(this);
		/* 显示窗体 */
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public DialogOpPerson(JFrame p, String title, String type) {
		super(p, title);
		Init();
		this.type=type;

		for (JTextComponent jt : jtc) {
			jt.setEditable(true);
			jt.setText("");
		}
		B_type.setEnabled(true);

		button_confirm.setVisible(true);
		button_cancel.setVisible(true);

		setVisible(true);
	}

	public DialogOpPerson(JFrame p, String title, String type, String obj) throws SQLException {
		super(p, title);
		this.type = type;
		this.obj = obj;

		Init();

		// 修改模式
		if (type.equals("Modify")) {
			Person ps = sqlOp.getPersonFromAccount(obj, sqlOp.con.createStatement());
			System.out.println(obj);
			if (ps != null) {
				// 读入详细数据
				T_name.setText(ps.getName());
				B_type.setSelectedItem(ps.getType());
				T_telephone.setText(ps.getTelephone());
				T_email.setText(ps.getEmail());
				T_address.setText(ps.getAddress());
				T_account.setText(ps.getAccount());
				T_password.setText(ps.getPassword());

				for (JTextComponent jt : jtc) {
					jt.setEditable(true);
				}

				B_type.setEnabled(true);
				button_confirm.setVisible(true);
				button_cancel.setVisible(true);
			}
		} else if (type.equals("Info")) {
			Person ps = sqlOp.getPersonFromAccount(obj, sqlOp.con.createStatement());
			System.out.println(obj);
			if (ps != null) {
				// 读入详细数据
				T_name.setText(ps.getName());
				B_type.setSelectedItem(ps.getType());
				T_telephone.setText(ps.getTelephone());
				T_email.setText(ps.getEmail());
				T_address.setText(ps.getAddress());
				T_account.setText(ps.getAccount());
				T_password.setText(ps.getPassword());

			}

			for (JTextComponent jt : jtc) {
				jt.setEditable(false);
			}
			B_type.setEnabled(false);

			button_confirm.setVisible(false);
			button_cancel.setVisible(false);
		}
		setVisible(true);

	}

	/* 事件监听器 */
	public void actionPerformed(ActionEvent e) {

		/* 如果event对象源为确认按钮 */
		if (e.getSource() == button_confirm) {
			// 遍历是否有空
			for (JTextComponent jt : jtc) {
				if (jt.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "不能留空！", "错误", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}

			// 添加
			if (type.equals("Add")) {
				// 执行添加
				try {
					if (sqlOp.getPersonFromAccount(T_account.getText(), sqlOp.con.createStatement()) == null) {
						sqlOp.addPerson(new Person(
								T_name.getText(), 
								(TypePerson) B_type.getSelectedItem(), 
								T_telephone.getText(), 
								T_email.getText(), 
								T_address.getText(), 
								T_account.getText(), 
								T_password.getText()
								));
						
						button_confirm.removeActionListener(this);
						button_cancel.removeActionListener(this);
						// 关闭窗口
						this.dispose();
						return;
					} else {
						JOptionPane.showMessageDialog(this, "已存在相同账号用户！", "错误", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (HeadlessException | SQLException e1) {
				}
			} else if (type.equals("Modify")) {
				// 执行更新
				try {
					if (sqlOp.getPersonFromAccount(obj,sqlOp.con.createStatement()) != null) {
						sqlOp.setPerson(new Person(
								T_name.getText(), 
								(TypePerson) B_type.getSelectedItem(), 
								T_telephone.getText(), 
								T_email.getText(), 
								T_address.getText(), 
								T_account.getText(), 
								T_password.getText()
								));
						button_confirm.removeActionListener(this);
						button_cancel.removeActionListener(this);
						// 关闭窗口
						this.dispose();
						return;
					} else {
						JOptionPane.showMessageDialog(this, "找不到该用户！", "错误", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (HeadlessException | SQLException e1) {
				}

			}

		}
		// 取消按钮
		else if (e.getSource() == button_cancel) {
			button_confirm.removeActionListener(this);
			button_cancel.removeActionListener(this);
			// 关闭窗口
			this.dispose();
			return;
		}
	}

}
