package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.table.DefaultTableModel;

import OwnerInfo.OwnerInfo;
import resource.resource;

public class mainFrame extends JFrame implements resource, ActionListener {
	String d[][] = {
			// { "ID", "NAME", "AGE", "HOUSE_NUM", "FAMILY_NUM", "SEX",
			// "MARRIAGE_STATE" },
			{ "1", "67", "18", "A01", "3", "Ů", "δ��" } };

	static String d1[] = { "ID", "����", "����", "���ƺ�", "��ͥ��Ա��", "�Ա�", "����״��" };
	private static JButton button_showAll = new JButton("��ʾȫ��");
	private static JButton button_addOwner = new JButton("���ҵ��");
	private static JButton button_changeOwner = new JButton("�޸�ҵ��");
	private static JButton button_findOwner = new JButton("��ѯҵ��");
	private static JButton button_deleteOwner = new JButton("ɾ��ҵ��");
	private static JTextField text_Num = new JTextField(11);
	public static JTable jt;
	private static MyDialog dia;
	private static loginDialog logindia;
	public static DefaultTableModel tableModel = new DefaultTableModel(null, d1);

	public mainFrame() {
		
		INIT();
		logindia=new loginDialog(this, "��¼");
		dia=new MyDialog(this, "���");
		dia.setModal(true);
		setVisible(true);
	}

	private void INIT() {
		setTitle("ҵ������ϵͳ");
		setBounds(200, 200, 680, 250);
		setLayout(new FlowLayout());
		add(button_showAll);
		add(button_addOwner);
		add(button_changeOwner);
		add(button_deleteOwner);
		add(new JLabel("���ƺ�"));
		add(text_Num);
		add(button_findOwner);
		
		setResizable(false);

		jt = new JTable(tableModel) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jt.setAutoResizeMode(2);
		jt.setSelectionMode(0);
		jt.setPreferredScrollableViewportSize(new Dimension(655, 160));
		JScrollPane jsp = new JScrollPane(jt);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		button_showAll.addActionListener(this);
		button_findOwner.addActionListener(this);
		button_addOwner.addActionListener(this);
		button_changeOwner.addActionListener(this);
		button_deleteOwner.addActionListener(this);
		// jt.setEnabled(false);
		add(jsp);
		// sqlOp.addOwner(new OwnerInfo( "67", 18, "A01", 3, "Ů", "δ��"));
		// add(jt, BorderLayout.PAGE_END);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void refresh(){
		ResultSet rs = sqlOp.getAll();
		tableModel.setRowCount(0);
		try {
			while (rs.next()) {
				tableModel.addRow(new String[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7) });
			}
		} catch (SQLException e1) {
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_showAll) {
			refresh();
		} else if (e.getSource() == button_findOwner) {
			if (!text_Num.getText().equals("")) {
				OwnerInfo oi = sqlOp.getOwner(text_Num.getText());
				if (oi != null) {
					tableModel.setRowCount(0);
					tableModel.addRow(new String[] { oi.getID() + "", oi.getName(), oi.getAge() + "", oi.getHouseNum(),
							oi.getFamilyNum() + "", oi.getSex(), oi.getMarriageState() });
				} else {
					JOptionPane.showMessageDialog(this, "�����ڸ����ƺ�ҵ��", "����", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "������������ƺ�", "����", JOptionPane.WARNING_MESSAGE);
			}
		}else if(e.getSource()==button_addOwner){
			dia.setTitle("���ҵ��");
			dia.text_HouseNum.setEnabled(true);
			dia.setText(new OwnerInfo(0,"",0,"",0,"",""));
			dia.setVisible(true);
		}else if(e.getSource()==button_changeOwner){
			if(jt.getSelectedRow()!=-1){
				dia.text_HouseNum.setEnabled(false);
				dia.setText(new OwnerInfo(
						Integer.valueOf((String)tableModel.getValueAt(jt.getSelectedRow(), 0)),
						(String)tableModel.getValueAt(jt.getSelectedRow(), 1), 
						Integer.valueOf((String)tableModel.getValueAt(jt.getSelectedRow(), 2)), 
						(String)tableModel.getValueAt(jt.getSelectedRow(), 3), 
						Integer.valueOf((String)tableModel.getValueAt(jt.getSelectedRow(), 4)), 
						(String)tableModel.getValueAt(jt.getSelectedRow(), 5), 
						(String)tableModel.getValueAt(jt.getSelectedRow(), 6)));
				dia.setTitle("�޸�ҵ��");
				dia.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(this, "��ѡ��ҵ��", "����", JOptionPane.WARNING_MESSAGE);
			}
			
		}else if(e.getSource()==button_deleteOwner){
			if(jt.getSelectedRow()!=-1){
				sqlOp.deleteOwner((String)tableModel.getValueAt(jt.getSelectedRow(), 3));
				refresh();
			}else{
				JOptionPane.showMessageDialog(this, "��ѡ��ҵ��", "����", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
