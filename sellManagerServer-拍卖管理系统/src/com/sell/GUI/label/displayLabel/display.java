package com.sell.GUI.label.displayLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.sell.GUI.MainFrame;
import com.sell.GUI.button.ImageButton;
import com.sell.GUI.dialog.BuyDialog;
import com.sell.GUI.dialog.DialogOpEntity;
import com.sell.GUI.dialog.DialogOpPerson;
import com.sell.service.Entity;
import com.sell.service.Person;
import com.sell.service.time;
import com.sell.service.Type.TypeEntity;
import com.sell.service.Type.TypePerson;

import tools.resource;
import tools.tools;

/**
 * ��ʾ��ɾ�Ĳ�ı�ǩ
 * 
 * @author Zhang
 *
 */
public class display extends JLabel implements MouseMotionListener, MouseListener, resource {

	/**
	 * ���л���־
	 */
	private static final long serialVersionUID = -6479615102937773978L;

	/** ��Ӱ�ť */
	public ImageButton buttonAdd = new ImageButton(imageAdd, imageAddOn);

	/** ������ť */
	public ImageButton buttonExplorer = new ImageButton(imageExplorer, imageExplorerOn);

	/** �޸İ�ť */
	public ImageButton buttonModify = new ImageButton(imageModify, imageModifyOn);

	/** ɾ����ť */
	public ImageButton buttonDel = new ImageButton(imageDel, imageDelOn);

	/** yes��ť */
	public ImageButton buttonYes = new ImageButton(imageYes, null);

	/** no��ť */
	public ImageButton buttonNo = new ImageButton(imageNo, null);

	/** ����������ť */
	public ArrayList<ImageButton> buttons = new ArrayList<>();

	/** ��ǰ��ʾobj */
	ArrayList<Object> objDisplay;

	/** ��ǰҪɾ�����б�obj */
	ArrayList<ImageButton> objDel = new ArrayList<>();

	/** ����Ʒ/�û���ť */
	public ArrayList<ImageButton> displayButtons = new ArrayList<>();

	/** ����Ʒ/�û���ǩ */
	public ArrayList<JLabel> displayLabels = new ArrayList<>();

	/** ģʽ 1 Ϊɾ��ģʽ 2 Ϊ�޸�ģʽ */
	int model = -1;

	/** ��ʾ����Entity����Person */
	String type;

	JFrame jf;

	public void Init() {

		buttons.add(buttonAdd);
		buttons.add(buttonExplorer);
		buttons.add(buttonModify);
		buttons.add(buttonDel);
		buttons.add(buttonYes);
		buttons.add(buttonNo);
		buttonYes.setVisible(false);
		buttonNo.setVisible(false);

		// ͼƬƽ�� ���������λ��
		tools.setIcon(buttonAdd, 0, 0, 50, 50);
		tools.setIcon(buttonExplorer, 0, 50, 50, 50);
		tools.setIcon(buttonModify, 0, 100, 50, 50);
		tools.setIcon(buttonDel, 0, 150, 50, 50);
		tools.setIcon(buttonYes, 0, 200, 50, 50);
		tools.setIcon(buttonNo, 0, 250, 50, 50);
		// �������͸��
		for (ImageButton j : buttons) {
			j.setOpaque(false);
		}

		// ��ǩ�г�ѡ���ť
		for (ImageButton j : buttons) {
			this.add(j);
		}

		// �������������
		for (ImageButton j : buttons) {
			j.addMouseListener(this);
		}
	}

	/**
	 * @param type
	 *            ��ʾ����
	 */
	public display(String type, JFrame jf) {
		this.type = type;
		this.jf = jf;
		Init();

		// ��ͨ�û���¼
		if (((MainFrame) jf).getModel().equals("��ͨ�û�")) {

			if (type.equals("Entity")) {
				buttonAdd.setVisible(false);

				buttonModify.setVisible(false);
				buttonDel.setVisible(false);
			} else if (type.equals("Person")) {
				buttonModify.setVisible(false);
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * ˢ����ʾ��user/����Ʒ
	 * 
	 * @param obj
	 *            Ҫ��ʾ��obj
	 * @param type
	 *            "Person" ���� "Entity"
	 */
	@SuppressWarnings("unchecked")
	public void refreshDisplay(ArrayList<?> obj) {
		this.objDisplay = (ArrayList<Object>) obj;
		if (obj != null) {
			// ����Ѵ����İ�ť����ǩ
			for (ImageButton j : displayButtons) {
				this.remove(j);
			}
			for (JLabel j : displayLabels) {
				this.remove(j);
			}
			displayLabels.clear();
			displayButtons.clear();

			if (type.equals("Person")) {
				for (int i = 0; i < obj.size(); i++) {
					ImageButton ib = null;
					JLabel jl = null;
					if (((MainFrame) jf).getModel().equals("��ͨ�û�")) {
						ib = new ImageButton(imageMarket, imageMarketOn);
						jl = new JLabel();
						tools.setIcon(ib, 55 + (i % 6) * 75, 5 + i / 6 * 140, 70, 70);
						jl.setBounds(55 + (i % 6) * 75, 5 + i / 6 * 140 + 70, 70, 70);
						String name = ((Entity) obj.get(i)).getName();
						jl.setText("<html>"+"���֣�" + name + "<br>" +  sqlOp.getTimeFromName(name).getDeadTimeString() + "<br>"
								+ "���ۣ�" + sqlOp.getTimeFromName(name).getPriceNow() + "</html>");
						ib.setName(name);

					} else if (((MainFrame) jf).getModel().equals("����Ա")) {

						ib = new ImageButton(imageUser, imageUserOn);
						jl = new JLabel();
						tools.setIcon(ib, 55 + (i % 6) * 75, 5 + i / 6 * 90, 70, 70);
						jl.setBounds(55 + (i % 6) * 75, 5 + i / 6 * 90 + 70, 70, 20);

						ib.setName(((Person) obj.get(i)).getAccount());
						jl.setText(((Person) obj.get(i)).getAccount());
					}
					displayLabels.add(jl);
					displayButtons.add(ib);
				}
			} else if (type.equals("Entity")) {
				for (int i = 0; i < obj.size(); i++) {
					ImageButton ib = new ImageButton(imageMarket, imageMarketOn);
					JLabel jl = new JLabel();

					tools.setIcon(ib, 55 + (i % 6) * 75, 5 + i / 6 * 140, 70, 70);
					jl.setBounds(55 + (i % 6) * 75, 5 + i / 6 * 140 + 70, 70, 70);
					String name = ((Entity) obj.get(i)).getName();
					jl.setText("<html>"+"���֣�" + name + "<br>" + sqlOp.getTimeFromName(name).getDeadTimeString() + "<br>"
							+ "���ۣ�" + sqlOp.getTimeFromName(name).getPriceNow() + "</html>");
					ib.setName(name);

					displayButtons.add(ib);
					displayLabels.add(jl);
				}
			}

			// ��ʾ����ť
			for (ImageButton j : displayButtons) {
				this.add(j);
			}
			// ��ʾ����ǩ
			for (JLabel j : displayLabels) {
				this.add(j);
			}

			// ������
			for (ImageButton j : displayButtons) {
				j.addMouseListener(this);
				j.addMouseMotionListener(this);
			}

			repaint();
		} else {
			// ����Ѵ����İ�ť����ǩ
			for (ImageButton j : displayButtons) {
				this.remove(j);
			}
			for (JLabel j : displayLabels) {
				this.remove(j);
			}
			displayLabels.clear();
			displayButtons.clear();
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 1) {
			// ȡ��ģʽ��ť
			if (e.getSource() == buttonNo) {
				buttonYes.setVisible(false);
				buttonNo.setVisible(false);

				// ȡ������
				for (ImageButton ib : buttons) {
					ib.setLight(false);
				}
				// ȡ������
				for (ImageButton ib : objDel) {
					ib.setLight(false);
				}
				// ���Ҫɾ�����б�
				objDel.clear();
				model = -1;
			}
			if (type == "Person") {
				if (((MainFrame) jf).getModel().equals("����Ա")) {
					// �����ѯ��ť
					if (e.getSource() == buttonExplorer) {

						/* ������Ч */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonExplorer.setImg(buttonExplorer.getImgOn());

						refreshDisplay(sqlOp
								.getPersonFromFuzzy(JOptionPane.showInputDialog(jf, "������Ҫ��ѯ���û��˺Ż�������(ģ����ѯ),���ղ�ѯȫ��")));

						buttonExplorer.setImg(buttonExplorer.getImgOff());
					}

					// �����Ӱ�ť
					else if (e.getSource() == buttonAdd) {
						/* ������Ч */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonAdd.setImg(buttonAdd.getImgOn());

						new DialogOpPerson(jf, "����û�", "Add");
						buttonAdd.setImg(buttonAdd.getImgOff());
					}

					// ���ɾ����ť
					else if (e.getSource() == buttonDel) {

						/* ������Ч */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonDel.setImg(buttonDel.getImgOn());

						// ����ɾ��ģʽ
						model = 1;
						buttonYes.setVisible(true);
						buttonNo.setVisible(true);
					}
					// ����޸İ�ť
					else if (e.getSource() == buttonModify) {

						/* ������Ч */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonModify.setImg(buttonModify.getImgOn());

						// �����޸�ģʽ
						model = 2;
						buttonYes.setVisible(false);
						buttonNo.setVisible(true);
					} // �����ʾ�Ķ���
					else if (displayButtons.contains(e.getSource())) {
						ImageButton ib = (ImageButton) e.getSource();

						// һ��ģʽ ����鿴��ϸ��Ϣ
						if (model == -1) {
							try {
								new DialogOpPerson(jf, "�û�����", "Info", ib.getName());
							} catch (SQLException e1) {
							}
						}
						// ɾ��ģʽ ���ѡ��/��ѡ��
						else if (model == 1) {

							// ȡ��ѡ��
							if (ib.getIsLight() == true) {
								objDel.remove(this);
								ib.setLight(false);
							}
							// ѡ��
							else if (ib.getIsLight() == false) {
								objDel.add(ib);
								ib.setLight(true);
							}
						}
						// �޸�ģʽ ����޸���ϸ��Ϣ
						else if (model == 2) {
							try {
								new DialogOpPerson(jf, "�޸��û�", "Modify", ib.getName());
							} catch (SQLException e1) {
							}
							// model = -1;
						}
					}
					// ɾ��ȷ����ť
					else if (e.getSource() == buttonYes) {
						buttonYes.setVisible(false);
						buttonNo.setVisible(false);

						// ִ��ɾ��
						int amount = objDel.size();
						for (int i = 0; i < amount; i++) {
							sqlOp.deletePerson(((Person) objDisplay.get(i)).getAccount());
							objDisplay.remove(i);
							i--;
							amount--;
						}

						refreshDisplay(objDisplay);
						// ���Ҫɾ�����б�
						objDel.clear();
						model = -1;
					}
				}

				// ��ͨ�û���Person��ǩ��ʾ���˵�����Ʒ
				else {
					// �����ѯ��ť
					if (e.getSource() == buttonExplorer) {

						/* ������Ч */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}

						buttonExplorer.setImg(buttonExplorer.getImgOn());

						refreshDisplay(sqlOp.getEntityFromNameFuzzyFromAccount(
								JOptionPane.showInputDialog(jf, "������Ҫ��ѯ��ӵ�е�����Ʒ����(ģ����ѯ)"),
								((MainFrame) jf).getAccount()));
						buttonExplorer.setImg(buttonExplorer.getImgOff());

					}

					// �����Ӱ�ť
					else if (e.getSource() == buttonAdd) {

						/* ������Ч */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonAdd.setImg(buttonAdd.getImgOn());

						new DialogOpEntity((MainFrame) jf, "�������Ʒ", "Add");

						buttonAdd.setImg(buttonAdd.getImgOff());
					}

					// ���ɾ����ť
					else if (e.getSource() == buttonDel) {

						/* ������Ч */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonDel.setImg(buttonDel.getImgOn());

						// ����ɾ��ģʽ
						model = 1;
						buttonYes.setVisible(true);
						buttonNo.setVisible(true);

					}
					// ����޸İ�ť
					else if (e.getSource() == buttonModify) {

						/* ������Ч */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonModify.setImg(buttonModify.getImgOn());

						// �����޸�ģʽ
						model = 2;
						buttonYes.setVisible(false);
						buttonNo.setVisible(true);

					}

					// �����ʾ�Ķ���
					else if (displayButtons.contains(e.getSource())) {
						ImageButton ib = (ImageButton) e.getSource();

						// һ��ģʽ ����鿴��ϸ��Ϣ
						if (model == -1) {
							new DialogOpEntity((MainFrame) jf, "����Ʒ����", "Info", ib.getName());
						}
						// ɾ��ģʽ ���ѡ��/��ѡ��
						else if (model == 1) {

							// ȡ��ѡ��
							if (ib.getIsLight() == true) {
								objDel.remove(this);
								ib.setLight(false);
							}
							// ѡ��
							else if (ib.getIsLight() == false) {
								objDel.add(ib);
								ib.setLight(true);
							}
						}
						// �޸�ģʽ ����޸���ϸ��Ϣ
						else if (model == 2) {
							new DialogOpEntity((MainFrame) jf, "�޸�����Ʒ", "Modify", ib.getName());

							// model = -1;
						}
					}
					// ɾ��ȷ����ť
					else if (e.getSource() == buttonYes) {
						buttonYes.setVisible(false);
						buttonNo.setVisible(false);

						// ִ��ɾ��
						int amount = objDel.size();
						for (int i = 0; i < amount; i++) {
							sqlOp.deleteEntity(((Entity) objDisplay.get(i)).getName());
							objDisplay.remove(i);
							i--;
							amount--;
						}

						refreshDisplay(objDisplay);
						// ���Ҫɾ�����б�
						objDel.clear();
						model = -1;
					}
				}
			}

			else if (type == "Entity") {
				// �����ѯ��ť
				if (e.getSource() == buttonExplorer) {

					/* ������Ч */
					for (ImageButton ib : buttons) {
						ib.setImg(ib.getImgOff());
					}
					buttonExplorer.setImg(buttonExplorer.getImgOn());

					refreshDisplay(sqlOp.getEntityFromNameFuzzy(JOptionPane.showInputDialog(jf, "������Ҫ��ѯ������Ʒ����(ģ����ѯ)")));
					buttonExplorer.setImg(buttonExplorer.getImgOff());

				}

				// �����Ӱ�ť
				else if (e.getSource() == buttonAdd) {

					/* ������Ч */
					for (ImageButton ib : buttons) {
						ib.setImg(ib.getImgOff());
					}
					buttonAdd.setImg(buttonAdd.getImgOn());

					new DialogOpEntity((MainFrame) jf, "�������Ʒ", "Add");

					buttonAdd.setImg(buttonAdd.getImgOff());
				}

				// ���ɾ����ť
				else if (e.getSource() == buttonDel) {

					/* ������Ч */
					for (ImageButton ib : buttons) {
						ib.setImg(ib.getImgOff());
					}
					buttonDel.setImg(buttonDel.getImgOn());

					// ����ɾ��ģʽ
					model = 1;
					buttonYes.setVisible(true);
					buttonNo.setVisible(true);

				}
				// ����޸İ�ť
				else if (e.getSource() == buttonModify) {

					/* ������Ч */
					for (ImageButton ib : buttons) {
						ib.setImg(ib.getImgOff());
					}
					buttonModify.setImg(buttonModify.getImgOn());

					// �����޸�ģʽ
					model = 2;
					buttonYes.setVisible(false);
					buttonNo.setVisible(true);

				}

				// �����ʾ�Ķ���
				else if (displayButtons.contains(e.getSource())) {
					ImageButton ib = (ImageButton) e.getSource();

					// һ��ģʽ ����鿴��ϸ��Ϣ
					if (model == -1) {
						new DialogOpEntity((MainFrame) jf, "����Ʒ����", "Info", ib.getName());
					}
					// ɾ��ģʽ ���ѡ��/��ѡ��
					else if (model == 1) {

						// ȡ��ѡ��
						if (ib.getIsLight() == true) {
							objDel.remove(this);
							ib.setLight(false);
						}
						// ѡ��
						else if (ib.getIsLight() == false) {
							objDel.add(ib);
							ib.setLight(true);
						}
					}
					// �޸�ģʽ ����޸���ϸ��Ϣ
					else if (model == 2) {
						new DialogOpEntity((MainFrame) jf, "�޸�����Ʒ", "Modify", ib.getName());

						// model = -1;
					}
				}
				// ɾ��ȷ����ť
				else if (e.getSource() == buttonYes) {
					buttonYes.setVisible(false);
					buttonNo.setVisible(false);

					// ִ��ɾ��
					int amount = objDel.size();
					for (int i = 0; i < amount; i++) {
						sqlOp.deleteEntity(((Entity) objDisplay.get(i)).getName());
						objDisplay.remove(i);
						i--;
						amount--;
					}

					refreshDisplay(objDisplay);
					// ���Ҫɾ�����б�
					objDel.clear();
					model = -1;
				}
			}
		}
		/* �Ҽ������ʾ�İ�ť */
		/* ���� */
		else if (e.getButton() == 3) {
			if (((MainFrame) jf).getModel().equals("��ͨ�û�") && type.equals("Entity")) {
				if (displayButtons.contains(e.getSource())) {
					ImageButton ib = (ImageButton) e.getSource();
					// �����Լ�����
					if (!sqlOp.getEntityFromName(ib.getName()).getSeller().getAccount().equals(((MainFrame)jf).getAccount())) {
						new BuyDialog((MainFrame) jf, "����", ib.getName());
					}

				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
