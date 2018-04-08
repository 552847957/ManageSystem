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
 * ����ϵͳ����Ա �޸ġ����Person ����
 */
public class DialogOpPerson extends JDialog implements ActionListener, resource {

	/* ���л�����İ汾 */
	private static final long serialVersionUID = -7790766426858844936L;

	/* ���ֱ�ǩ */
	public static JLabel L_name = new JLabel("�û����֣�");
	public static JTextField T_name = new JTextField(10);

	/* ���ͱ�ǩ */
	public static JLabel L_type = new JLabel("���ͣ�");
	public JComboBox<TypePerson> B_type = new JComboBox<>(TypePerson.values());
	/* telephone��ǩ */
	public static JLabel L_telephone = new JLabel("telephone��");
	public static JTextField T_telephone = new JTextField(20);
	/* email��ǩ */
	public static JLabel L_email = new JLabel("email��");
	public static JTextField T_email = new JTextField(20);
	/* address��ǩ */
	public static JLabel L_address = new JLabel("��ַ��");
	public static JTextField T_address = new JTextField(20);
	/* account��ǩ */
	public static JLabel L_account = new JLabel("�˺ţ�");
	public static JTextField T_account = new JTextField(20);
	/* password��ǩ */
	public static JLabel L_password = new JLabel("���룺");
	public static JTextField T_password = new JTextField(20);

	private ArrayList<JTextComponent> jtc = new ArrayList<>();

	public static JButton button_confirm = new JButton("ȷ��");
	public static JButton button_cancel = new JButton("ȡ��");

	/**
	 * ���� AddΪ��� ModifyΪ�޸�
	 */
	String type;

	/**
	 * �û��˺�
	 */
	String obj;

	public void Init() {
		/* ���ô����С��λ�� */
		setBounds(500, 200, 300, 320);
		setLayout(null);

		JPanel j1 = new JPanel(new GridLayout(7, 2, 1, 5));

		JPanel j2 = new JPanel(new FlowLayout());

		/* ��ֹresize */
		setResizable(false);

		/* �ô��ڲ��رգ����ܲ����������� */
		setModal(true);

		j1.setBounds(0, 0, this.getWidth() - 20, this.getHeight() -70);
		j2.setBounds(0, this.getHeight() -70, getWidth() - 20, getHeight()-50);

		/* ����ȫ����� */
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

		/* ��ť�����¼� */
		button_confirm.addActionListener(this);
		button_cancel.addActionListener(this);
		/* ��ʾ���� */
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

		// �޸�ģʽ
		if (type.equals("Modify")) {
			Person ps = sqlOp.getPersonFromAccount(obj, sqlOp.con.createStatement());
			System.out.println(obj);
			if (ps != null) {
				// ������ϸ����
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
				// ������ϸ����
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

	/* �¼������� */
	public void actionPerformed(ActionEvent e) {

		/* ���event����ԴΪȷ�ϰ�ť */
		if (e.getSource() == button_confirm) {
			// �����Ƿ��п�
			for (JTextComponent jt : jtc) {
				if (jt.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "�������գ�", "����", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}

			// ���
			if (type.equals("Add")) {
				// ִ�����
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
						// �رմ���
						this.dispose();
						return;
					} else {
						JOptionPane.showMessageDialog(this, "�Ѵ�����ͬ�˺��û���", "����", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (HeadlessException | SQLException e1) {
				}
			} else if (type.equals("Modify")) {
				// ִ�и���
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
						// �رմ���
						this.dispose();
						return;
					} else {
						JOptionPane.showMessageDialog(this, "�Ҳ������û���", "����", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (HeadlessException | SQLException e1) {
				}

			}

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
