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
 * ����ϵͳ����Ա �޸ġ����Entity ����
 */
public class DialogOpEntity extends JDialog implements ActionListener, resource {

	/* ���л�����İ汾 */
	private static final long serialVersionUID = -7790766426858844936L;

	/* ���ֱ�ǩ */
	public static JLabel L_name = new JLabel("����Ʒ���֣�");
	public static JTextField T_name = new JTextField(10);

	/* ���ͱ�ǩ */
	public static JLabel L_type = new JLabel("���ͣ�");
	public JComboBox<TypeEntity> B_type = new JComboBox<>(TypeEntity.values());

	/* seller��ǩ */
	public static JLabel L_seller = new JLabel("seller��");
	public static JTextField T_seller = new JTextField(20);
	/* priceStart��ǩ */
	public static JLabel L_priceStart = new JLabel("��ۣ�");
	public static JTextField T_priceStart = new JTextField(20);
	/* priceMin��ǩ */
	public static JLabel L_priceMin = new JLabel("������ۣ�");
	public static JTextField T_priceMin = new JTextField(20);
	/* priceFinal��ǩ */
	public static JLabel L_priceFinal = new JLabel("���ձ��ۣ�(-1Ϊδ�۳�)");
	public static JTextField T_priceFinal = new JTextField(20);
	/* buyer��ǩ */
	public static JLabel L_buyer = new JLabel("buyer��");
	public static JTextField T_buyer = new JTextField(20);
	/* info��ǩ */
	public static JLabel L_info = new JLabel("��ע��");
	public static JTextArea T_info = new JTextArea();

	private ArrayList<JTextComponent> jtc = new ArrayList<>();

	public static JButton button_confirm = new JButton("ȷ��");
	public static JButton button_cancel = new JButton("ȡ��");

	/**
	 * ���� AddΪ��� ModifyΪ�޸�
	 */
	String type;

	/**
	 * ����Ʒ���ֻ����û��˺�
	 */
	String obj;

	public void Init(){
		/* ���ô����С��λ�� */
		setBounds(500, 200, 300, 350);
		setLayout(null);

		JPanel j1 = new JPanel(new GridLayout(7, 2, 1, 5));

		JPanel j2 = new JPanel(new GridLayout(1, 2, 1, 5));

		JPanel j3 = new JPanel(new FlowLayout());

		/* ��ֹresize */
		setResizable(false);

		/* �ô��ڲ��رգ����ܲ����������� */
		setModal(true);

		j1.setBounds(0, 0, this.getWidth() - 20, this.getHeight() / 2);
		j2.setBounds(0, getHeight() / 2, getWidth() - 20, getHeight() / 3 - 20);
		j3.setBounds(0, getHeight() / 2 + getHeight() / 3 - 20, getWidth() - 10, getHeight() / 6);
		/* ����ȫ����� */
		add(j1);
		add(j2);
		add(j3);

		j1.add(L_name);
		j1.add(T_name);
		j1.add(L_type);
		j1.add(B_type);
		j1.add(L_seller);
		j1.add(T_seller);
		j1.add(L_priceStart);
		j1.add(T_priceStart);
		j1.add(L_priceMin);
		j1.add(T_priceMin);
		j1.add(L_priceFinal);
		j1.add(T_priceFinal);
		j1.add(L_buyer);
		j1.add(T_buyer);

		j2.add(L_info);
		j2.add(T_info);
		T_info.setLineWrap(true);

		j3.add(button_confirm);
		j3.add(button_cancel);

		jtc.add(T_name);
		jtc.add(T_seller);
		jtc.add(T_priceStart);
		jtc.add(T_priceMin);
		jtc.add(T_priceFinal);
		jtc.add(T_buyer);
		jtc.add(T_info);

		/* ��ť�����¼� */
		button_confirm.addActionListener(this);
		button_cancel.addActionListener(this);
		
		/* ��ʾ���� */
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
		

	}
	
	public DialogOpEntity(MainFrame p, String title, String type) {
		super(p, title);
		Init();
		this.type=type;
		
		for(JTextComponent jt:jtc){
			jt.setEditable(true);
			jt.setText("");
		}
		B_type.setEnabled(true);
		
		button_confirm.setVisible(true);
		button_cancel.setVisible(true);
		
		if(p.getModel().equals("��ͨ�û�")){
			T_buyer.setEditable(false);
			T_seller.setEditable(false);
			T_priceFinal.setEditable(false);
			
			T_buyer.setText(p.getAccount());
			T_priceFinal.setText("-1");
		}
		
		setVisible(true);
	}

	public DialogOpEntity(MainFrame p, String title, String type, String obj) {
		super(p, title);
		this.type = type;
		this.obj = obj;

		Init();
		
		// �޸�ģʽ
		if (type.equals("Modify")) {
			Entity en = sqlOp.getEntityFromName(obj);
			//System.out.println(obj);
			if (en != null) {
				// ������ϸ����
				T_name.setText(en.getName());
				B_type.setSelectedItem(en.getType());
				T_seller.setText(en.getSeller().getAccount());
				T_priceStart.setText("" + en.getPriceStart());
				T_priceMin.setText("" + en.getPriceMin());
				T_priceFinal.setText("" + en.getPriceFinal());
				
				
				if(en.getBuyer()!=null){
					T_buyer.setText(en.getBuyer().getAccount());
				}else{
					T_buyer.setText("");
				}
				
				for(JTextComponent jt:jtc){
					jt.setEditable(true);
				}
				B_type.setEnabled(true);
				
				button_confirm.setVisible(true);
				button_cancel.setVisible(true);
				
				T_info.setText(en.getInfo());
				
				
				if(p.getModel().equals("��ͨ�û�")){
					T_name.setEditable(false);
					T_buyer.setEditable(false);
					T_seller.setEditable(false);
					T_priceFinal.setEditable(false);
				}
			}
		}else if (type.equals("Info")){
			Entity en = sqlOp.getEntityFromName(obj);
			System.out.println(obj);
			if (en != null) {
				// ������ϸ����
				T_name.setText(en.getName());
				B_type.setSelectedItem(en.getType());
				T_seller.setText(en.getSeller().getAccount());
				T_priceStart.setText("" + en.getPriceStart());
				T_priceMin.setText("" + en.getPriceMin());
				T_priceFinal.setText("" + en.getPriceFinal());
				
				
				if(en.getBuyer()!=null){
					T_buyer.setText(en.getBuyer().getAccount());
				}else{
					T_buyer.setText("");
				}
				
				T_info.setText(en.getInfo());
			}
			
			for(JTextComponent jt:jtc){
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
				if (jt != T_buyer) {
					if (jt.getText().equals("")) {
						JOptionPane.showMessageDialog(this, "�������գ�(��buyer)", "����", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
			}

			Person seller = null;
			Person buyer = null;
			try {
				seller = sqlOp.getPersonFromAccount(T_seller.getText(), sqlOp.con.createStatement());
				buyer = sqlOp.getPersonFromAccount(T_buyer.getText(), sqlOp.con.createStatement());
			} catch (SQLException e1) {
			}
			if (buyer == null) {
				buyer = new Person("", TypePerson.����, "", "", "", "", "");
			}
			if (seller != null) {

				// ���
				if (type.equals("Add")) {
					// ִ�����
					if (sqlOp.getEntityFromName(T_name.getText()) == null) {
						sqlOp.addEntity(new Entity(T_name.getText(), (TypeEntity) B_type.getSelectedItem(), seller,
								Integer.valueOf(T_priceStart.getText()), Integer.valueOf(T_priceMin.getText()),
								Integer.valueOf(T_priceFinal.getText()), buyer, T_info.getText()));
						
						//�������Ʒ��ֹʱ��
						time t=new time(T_name.getText(), T_priceStart.getText(), new Date());
						t.addDeadTimeMinutes(5);
						sqlOp.addTime(t);
						
						button_confirm.removeActionListener(this);
						button_cancel.removeActionListener(this);
						// �رմ���
						this.dispose();
						return;
					} else {
						JOptionPane.showMessageDialog(this, "�Ѵ�����ͬ��������Ʒ��", "����", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} else if (type.equals("Modify")) {
					// ִ�и���
					if (sqlOp.getEntityFromName(obj) != null) {
						System.out.println("�޸�");
						sqlOp.setEntity(new Entity(T_name.getText(), (TypeEntity) B_type.getSelectedItem(), seller,
								Integer.valueOf(T_priceStart.getText()), Integer.valueOf(T_priceMin.getText()),
								Integer.valueOf(T_priceFinal.getText()), buyer, T_info.getText()));
						
						button_confirm.removeActionListener(this);
						button_cancel.removeActionListener(this);
						
						// �رմ���
						this.dispose();
						return;
					} else {
						JOptionPane.showMessageDialog(this, "�Ҳ���������Ʒ��", "����", JOptionPane.INFORMATION_MESSAGE);
						return;
					}

				}

			} else {
				JOptionPane.showMessageDialog(this, "�Ҳ�����������ߣ����ȴ����˺ţ�", "����", JOptionPane.INFORMATION_MESSAGE);
				return;
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
