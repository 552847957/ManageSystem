package frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import resource.resource;

public class loginDialog extends JDialog implements ActionListener, resource {
	public static JLabel L_account = new JLabel("账号");
	public static JLabel L_password = new JLabel("密码");
	public static JTextField account = new JTextField(10);
	public static JPasswordField password = new JPasswordField(10);
	public static JButton button_confirm = new JButton("登录");
	public static JButton button_exit = new JButton("退出");
	int amount = 3;

	public loginDialog(JFrame p, String title) {
		super(p, title);
		setResizable(false);
		setModal(true);
		setLayout(new GridLayout(3, 2));
		add(L_account);
		add(account);
		add(L_password);
		add(password);
		add(button_confirm);
		add(button_exit);
		setBounds(500, 200, 230, 140);
		button_confirm.addActionListener(this);
		button_exit.addActionListener(this);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_confirm) {
			if (account.getText().equals("") || password.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "不能为空", "警告", JOptionPane.WARNING_MESSAGE);
			} else if (account.getText().equals(sqlOp.getAccount()) && password.getText().equals(sqlOp.getPassword())) {
				setVisible(false);
			} else {
				amount--;
				JOptionPane.showMessageDialog(this, "账户不存在或密码错误 可重试次数:" + amount, "警告", JOptionPane.WARNING_MESSAGE);
				if (amount == 0) {
					System.exit(0);
				}
			}
		} else if (e.getSource() == button_exit) {
			System.exit(0);
		}
	}

}
