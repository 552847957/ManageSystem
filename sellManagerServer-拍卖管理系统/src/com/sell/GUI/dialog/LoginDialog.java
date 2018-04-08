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
 * ����ϵͳ����Ա��¼����
 */
public class LoginDialog extends JDialog implements ActionListener, resource {

	/* ���л�����İ汾 */
	private static final long serialVersionUID = -7790766426858844936L;

	/* ����ͼƬLabel */
	public static JLabel background = new JLabel(new ImageIcon(dirSrcImg + "����.png"));

	/* ѡ���¼��ʽ */
	public JComboBox<String> J_select = new JComboBox<>(new String[] { "����Ա", "��ͨ�û�" });

	/* �˺ű�ǩ */
	public static JLabel L_account = new JLabel(new ImageIcon(dirSrcImg + "�û���.png"));

	/* �����ǩ */
	public static JLabel L_password = new JLabel(new ImageIcon(dirSrcImg + "����.png"));

	/* �˺������ */
	public static JTextField account = new JTextField(10);

	/* ��������� */
	public static JPasswordField password = new JPasswordField(10);

	/* ��¼��ť */
	public static JButton button_confirm = new JButton(new ImageIcon(dirSrcImg + "��¼��ť.png"));

	/* ��������� */
	int amount = 3;

	JFrame jf;
	
	public LoginDialog(JFrame jf, String title) {
		super(jf, title);
		this.jf=jf;

		/* ��ֹresize */
		setResizable(false);

		/* �ô��ڲ��رգ����ܲ����������� */
		setModal(true);

		/* ���貼�� */
		background.setLayout(null);

		/* ����ȫ����� */
		add(background);
		background.add(J_select);
		background.add(L_account);
		background.add(account);
		background.add(L_password);
		background.add(password);
		background.add(button_confirm);

		/* �������͸�� */
		J_select.setOpaque(false);
		L_account.setOpaque(false);
		account.setOpaque(false);
		L_password.setOpaque(false);
		password.setOpaque(false);
		button_confirm.setOpaque(false);

		/* ���ø������λ�� */
		J_select.setBounds(370, 140, 78, 20);
		L_account.setBounds(97, 139, 78, 23);
		account.setBounds(190, 140, 170, 22);
		L_password.setBounds(97, 185, 78, 22);
		password.setBounds(191, 186, 170, 22);
		button_confirm.setBounds(97, 233, 271, 30);

		/* ���ô����С��λ�� */
		setBounds(500, 200, 462, 350);

		/* ��ť�����¼� */
		button_confirm.addActionListener(this);

		/* ����رհ�ťִ���˳�������� */
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		/* ��ʾ���� */
		setVisible(true);
	}

	/* �¼������� */
	public void actionPerformed(ActionEvent e) {
		/* ���event����ԴΪ��¼��ť */
		if (e.getSource() == button_confirm) {

			/* ���벻��Ϊ�� */
			if (account.getText().equals("") || password.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "����Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
				return;
			}

			// ����Ա��¼
			if (((String) J_select.getSelectedItem()).equals("����Ա")) {
				/* ��¼���ݿ� */
				if (sqlOp.getConnection(account.getText(), password.getText())) {
					((MainFrame)jf).setModel((String) J_select.getSelectedItem());
					((MainFrame)jf).setAccount(account.getText());
					((MainFrame)jf).setTitle("������-����Ա��¼-"+account.getText());
					setVisible(false);
				}
				/* �����˺�������󣬼�����ִ�д��� */
				else {
					amount--;
					JOptionPane.showMessageDialog(this, "�˻������ڻ�������� �����Դ���:" + amount, "����",
							JOptionPane.WARNING_MESSAGE);
					if (amount == 0) {
						System.exit(0);
					}
				}
			}
			// ��ͨ�û���¼
			else if (((String) J_select.getSelectedItem()).equals("��ͨ�û�")) {
				/* ��¼���ݿ� */
				sqlOp.getConnection("root", "root");
				try {
					Person p=
							sqlOp.getPersonFromAccount(account.getText(), sqlOp.con.createStatement());
					if (p!=null) {
						if(password.getText().equals(p.getPassword())){
							((MainFrame)jf).setModel((String) J_select.getSelectedItem());
							((MainFrame)jf).setAccount(account.getText());
							((MainFrame)jf).setTitle("������-�û���¼-"+account.getText());
							setVisible(false);
						}else{
							amount--;
							JOptionPane.showMessageDialog(this, "�˻������ڻ�������� �����Դ���:" + amount, "����",
									JOptionPane.WARNING_MESSAGE);
							if (amount == 0) {
								System.exit(0);
							}
						}
					}else{
						JOptionPane.showMessageDialog(this, "�˻�������", "����",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e1) {
				}

			}

		}
	}

}
