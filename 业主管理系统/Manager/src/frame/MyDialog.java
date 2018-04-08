package frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import OwnerInfo.OwnerInfo;
import resource.resource;

public class MyDialog extends JDialog implements ActionListener, resource {
	// +"'"+oi.getName()+"'"
	// +oi.getAge()
	// +"'"+oi.getHouseNum()+"'"
	// +oi.getFamilyNum()
	// +"'"+oi.getSex()+"'"
	// +"'"+oi.getMarriageState()+"'"
	public static JLabel Label_ID = new JLabel("ID");
	public static JLabel Label_Name = new JLabel("����");;
	public static JLabel Label_Age = new JLabel("����");;
	public static JLabel Label_HouseNum = new JLabel("���ƺ�");;
	public static JLabel Label_FamilyNum = new JLabel("��ͥ��Ա��");;
	public static JLabel Label_Sex = new JLabel("�Ա�");;
	public static JLabel Label_MarriageState = new JLabel("����״��");;
	public static JTextField text_ID = new JTextField(10);
	public static JTextField text_Name = new JTextField(10);
	public static JTextField text_Age = new JTextField(10);
	public static JTextField text_HouseNum = new JTextField(10);
	public static JTextField text_FamilyNum = new JTextField(10);
	public static JTextField text_Sex = new JTextField(10);
	public static JTextField text_MarriageState = new JTextField(10);
	public static JButton button_confirm = new JButton("ȷ��");
	public static JButton button_cancle = new JButton("ȡ��");

	public MyDialog(JFrame jf, String title) {
		super(jf, title);
		setLayout(new GridLayout(8, 2));
		setResizable(false);
		text_ID.setEnabled(false);
		text_HouseNum.setEnabled(false);
		add(Label_ID);
		add(text_ID);
		add(Label_Name);
		add(text_Name);
		add(Label_Age);
		add(text_Age);
		add(Label_HouseNum);
		add(text_HouseNum);
		add(Label_FamilyNum);
		add(text_FamilyNum);
		add(Label_Sex);
		add(text_Sex);
		add(Label_MarriageState);
		add(text_MarriageState);
		add(button_confirm);
		add(button_cancle);
		setBounds(200, 200, 200, 250);
		button_confirm.addActionListener(this);
		button_cancle.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_confirm) {
			if (!(text_Age.getText().equals("") || text_FamilyNum.getText().equals("")
					|| text_HouseNum.getText().equals("") || text_MarriageState.getText().equals("")
					|| text_Name.getText().equals("") || text_Sex.getText().equals(""))) {
				if (text_HouseNum.isEnabled() == false) {// �޸�ģʽ
					sqlOp.setOwner(new OwnerInfo(Integer.valueOf(text_ID.getText()), text_Name.getText(),
							Integer.valueOf(text_Age.getText()), text_HouseNum.getText(),
							Integer.valueOf(text_FamilyNum.getText()), text_Sex.getText(),
							text_MarriageState.getText()));
					setVisible(false);
					mainFrame.refresh();
				} else {// ���ģʽ
					if (sqlOp.getOwner(text_HouseNum.getText()) == null) {
						sqlOp.addOwner(new OwnerInfo(Integer.valueOf(text_ID.getText()), text_Name.getText(),
								Integer.valueOf(text_Age.getText()), text_HouseNum.getText(),
								Integer.valueOf(text_FamilyNum.getText()), text_Sex.getText(),
								text_MarriageState.getText()));
						setVisible(false);
						mainFrame.refresh();
					} else {
						JOptionPane.showMessageDialog(this, "�Ѵ��ڸ����ƺ�ҵ��", "����", JOptionPane.WARNING_MESSAGE);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "����Ϊ��", "����", JOptionPane.WARNING_MESSAGE);
			}
		} else if (e.getSource() == button_cancle) {
			setVisible(false);
		}
	}

	public void setText(OwnerInfo oi) {
		text_ID.setText(oi.getID() + "");
		text_Name.setText(oi.getName() + "");
		text_Age.setText(oi.getAge() + "");
		text_HouseNum.setText(oi.getHouseNum() + "");
		text_FamilyNum.setText(oi.getFamilyNum() + "");
		text_Sex.setText(oi.getSex() + "");
		text_MarriageState.setText(oi.getMarriageState() + "");
	}
}
