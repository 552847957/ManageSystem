package com.sell.GUI.dialog;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.sell.GUI.MainFrame;
import com.sell.service.Entity;
import com.sell.service.Person;
import com.sell.service.time;
import com.sell.service.Type.TypeEntity;
import com.sell.service.Type.TypePerson;

import tools.resource;

/**
 * 拍卖系统 竞拍界面
 */
public class BuyDialog extends JDialog implements ActionListener, resource {

	/* 序列化对象的版本 */
	private static final long serialVersionUID = -7790766426858844936L;

	/* 名字标签 */
	public static JLabel L_name = new JLabel("拍卖品名字：");
	public static JTextField T_name = new JTextField(10);

	/* 价格标签 */
	public static JLabel L_nowprice = new JLabel("当前价格：");
	public static JTextField T_nowprice = new JTextField(10);

	/* 最低增价标签 */
	public static JLabel L_minprice = new JLabel("最低增价：");
	public static JTextField T_minprice = new JTextField(10);

	/* 报价标签 */
	public static JLabel L_price = new JLabel("报价：");
	public static JTextField T_price = new JTextField(10);

	public static JButton button_confirm = new JButton("竞拍");
	public static JButton button_cancel = new JButton("取消");

	MainFrame jf=null;
	Entity en = null;
	time t = null;

	/**
	 * 拍卖品名字
	 */
	String obj;

	public void Init() {
		/* 设置窗体大小及位置 */
		setBounds(500, 200, 250, 200);

		this.setLayout(new GridLayout(5, 2, 1, 5));
		/* 禁止resize */
		setResizable(false);

		/* 该窗口不关闭，不能操作其他窗口 */
		setModal(true);

		/* 加入全部组件 */
		T_name.setEditable(false);
		T_nowprice.setEditable(false);
		T_minprice.setEditable(false);

		add(L_name);
		add(T_name);

		add(L_nowprice);
		add(T_nowprice);

		add(L_minprice);
		add(T_minprice);

		add(L_price);
		add(T_price);

		add(button_confirm);
		add(button_cancel);

		/* 按钮监听事件 */
		button_confirm.addActionListener(this);
		button_cancel.addActionListener(this);

		/* 显示窗体 */
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

	}

	public BuyDialog(MainFrame p, String title, String obj) {
		super(p, title);
		this.obj = obj;

		this.jf = p;
		Init();

		en = sqlOp.getEntityFromName(obj);
		t = sqlOp.getTimeFromName(obj);

		T_name.setText(en.getName());
		T_minprice.setText(String.valueOf(en.getPriceMin()));
		T_nowprice.setText(String.valueOf(t.getPriceNow()));
		T_price.setText(String.valueOf(t.getPriceNow() + en.getPriceMin()));
		setVisible(true);

	}

	/* 事件监听器 */
	public void actionPerformed(ActionEvent e) {

		/* 如果event对象源为确认按钮 */
		if (e.getSource() == button_confirm) {
			// 遍历是否有空
			if (T_price.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "不能留空！", "错误", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			if (Integer.valueOf(T_price.getText()).intValue() < t.getPriceNow() + en.getPriceMin()) {
				JOptionPane.showMessageDialog(this, "不能小于(增价+现价)！", "错误", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			if(new Date().after(t.getDeadTimeDate())){
				JOptionPane.showMessageDialog(this, "超过拍卖时间！", "错误", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			// 更新拍卖品截止时间
			time t = new time(T_name.getText(), T_price.getText(), new Date());
			t.addDeadTimeMinutes(5);
			sqlOp.setTime(t);

			/* 设置当前购买者 */
			try {
				en.setBuyer(sqlOp.getPersonFromAccount(jf.getAccount(),sqlOp.con.createStatement()));
			} catch (SQLException e1) {
			}
			
			/* 更新拍卖品 */
			sqlOp.setEntity(en);
			
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
