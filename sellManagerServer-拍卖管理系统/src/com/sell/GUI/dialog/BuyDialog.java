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
 * ����ϵͳ ���Ľ���
 */
public class BuyDialog extends JDialog implements ActionListener, resource {

	/* ���л�����İ汾 */
	private static final long serialVersionUID = -7790766426858844936L;

	/* ���ֱ�ǩ */
	public static JLabel L_name = new JLabel("����Ʒ���֣�");
	public static JTextField T_name = new JTextField(10);

	/* �۸��ǩ */
	public static JLabel L_nowprice = new JLabel("��ǰ�۸�");
	public static JTextField T_nowprice = new JTextField(10);

	/* ������۱�ǩ */
	public static JLabel L_minprice = new JLabel("������ۣ�");
	public static JTextField T_minprice = new JTextField(10);

	/* ���۱�ǩ */
	public static JLabel L_price = new JLabel("���ۣ�");
	public static JTextField T_price = new JTextField(10);

	public static JButton button_confirm = new JButton("����");
	public static JButton button_cancel = new JButton("ȡ��");

	MainFrame jf=null;
	Entity en = null;
	time t = null;

	/**
	 * ����Ʒ����
	 */
	String obj;

	public void Init() {
		/* ���ô����С��λ�� */
		setBounds(500, 200, 250, 200);

		this.setLayout(new GridLayout(5, 2, 1, 5));
		/* ��ֹresize */
		setResizable(false);

		/* �ô��ڲ��رգ����ܲ����������� */
		setModal(true);

		/* ����ȫ����� */
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

		/* ��ť�����¼� */
		button_confirm.addActionListener(this);
		button_cancel.addActionListener(this);

		/* ��ʾ���� */
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

	/* �¼������� */
	public void actionPerformed(ActionEvent e) {

		/* ���event����ԴΪȷ�ϰ�ť */
		if (e.getSource() == button_confirm) {
			// �����Ƿ��п�
			if (T_price.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "�������գ�", "����", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			if (Integer.valueOf(T_price.getText()).intValue() < t.getPriceNow() + en.getPriceMin()) {
				JOptionPane.showMessageDialog(this, "����С��(����+�ּ�)��", "����", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			if(new Date().after(t.getDeadTimeDate())){
				JOptionPane.showMessageDialog(this, "��������ʱ�䣡", "����", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			// ��������Ʒ��ֹʱ��
			time t = new time(T_name.getText(), T_price.getText(), new Date());
			t.addDeadTimeMinutes(5);
			sqlOp.setTime(t);

			/* ���õ�ǰ������ */
			try {
				en.setBuyer(sqlOp.getPersonFromAccount(jf.getAccount(),sqlOp.con.createStatement()));
			} catch (SQLException e1) {
			}
			
			/* ��������Ʒ */
			sqlOp.setEntity(en);
			
		}
		// ȡ����ť
		else if (e.getSource() == button_cancel) {

			button_confirm.removeActionListener(this);
			button_cancel.removeActionListener(this);
			// �رմ���
			this.dispose();
			return;
		}
	}

}
