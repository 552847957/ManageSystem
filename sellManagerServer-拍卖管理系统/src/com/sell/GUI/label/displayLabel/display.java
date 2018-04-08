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
 * 显示增删改查的标签
 * 
 * @author Zhang
 *
 */
public class display extends JLabel implements MouseMotionListener, MouseListener, resource {

	/**
	 * 序列化标志
	 */
	private static final long serialVersionUID = -6479615102937773978L;

	/** 添加按钮 */
	public ImageButton buttonAdd = new ImageButton(imageAdd, imageAddOn);

	/** 搜索按钮 */
	public ImageButton buttonExplorer = new ImageButton(imageExplorer, imageExplorerOn);

	/** 修改按钮 */
	public ImageButton buttonModify = new ImageButton(imageModify, imageModifyOn);

	/** 删除按钮 */
	public ImageButton buttonDel = new ImageButton(imageDel, imageDelOn);

	/** yes按钮 */
	public ImageButton buttonYes = new ImageButton(imageYes, null);

	/** no按钮 */
	public ImageButton buttonNo = new ImageButton(imageNo, null);

	/** 基本操作按钮 */
	public ArrayList<ImageButton> buttons = new ArrayList<>();

	/** 当前显示obj */
	ArrayList<Object> objDisplay;

	/** 当前要删除的列表obj */
	ArrayList<ImageButton> objDel = new ArrayList<>();

	/** 拍卖品/用户按钮 */
	public ArrayList<ImageButton> displayButtons = new ArrayList<>();

	/** 拍卖品/用户标签 */
	public ArrayList<JLabel> displayLabels = new ArrayList<>();

	/** 模式 1 为删除模式 2 为修改模式 */
	int model = -1;

	/** 显示类型Entity或者Person */
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

		// 图片平铺 并设置组件位置
		tools.setIcon(buttonAdd, 0, 0, 50, 50);
		tools.setIcon(buttonExplorer, 0, 50, 50, 50);
		tools.setIcon(buttonModify, 0, 100, 50, 50);
		tools.setIcon(buttonDel, 0, 150, 50, 50);
		tools.setIcon(buttonYes, 0, 200, 50, 50);
		tools.setIcon(buttonNo, 0, 250, 50, 50);
		// 设置组件透明
		for (ImageButton j : buttons) {
			j.setOpaque(false);
		}

		// 标签市场选项卡按钮
		for (ImageButton j : buttons) {
			this.add(j);
		}

		// 设置组件监听器
		for (ImageButton j : buttons) {
			j.addMouseListener(this);
		}
	}

	/**
	 * @param type
	 *            显示类型
	 */
	public display(String type, JFrame jf) {
		this.type = type;
		this.jf = jf;
		Init();

		// 普通用户登录
		if (((MainFrame) jf).getModel().equals("普通用户")) {

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
	 * 刷新显示的user/拍卖品
	 * 
	 * @param obj
	 *            要显示的obj
	 * @param type
	 *            "Person" 或者 "Entity"
	 */
	@SuppressWarnings("unchecked")
	public void refreshDisplay(ArrayList<?> obj) {
		this.objDisplay = (ArrayList<Object>) obj;
		if (obj != null) {
			// 清空已创建的按钮、标签
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
					if (((MainFrame) jf).getModel().equals("普通用户")) {
						ib = new ImageButton(imageMarket, imageMarketOn);
						jl = new JLabel();
						tools.setIcon(ib, 55 + (i % 6) * 75, 5 + i / 6 * 140, 70, 70);
						jl.setBounds(55 + (i % 6) * 75, 5 + i / 6 * 140 + 70, 70, 70);
						String name = ((Entity) obj.get(i)).getName();
						jl.setText("<html>"+"名字：" + name + "<br>" +  sqlOp.getTimeFromName(name).getDeadTimeString() + "<br>"
								+ "报价：" + sqlOp.getTimeFromName(name).getPriceNow() + "</html>");
						ib.setName(name);

					} else if (((MainFrame) jf).getModel().equals("管理员")) {

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
					jl.setText("<html>"+"名字：" + name + "<br>" + sqlOp.getTimeFromName(name).getDeadTimeString() + "<br>"
							+ "报价：" + sqlOp.getTimeFromName(name).getPriceNow() + "</html>");
					ib.setName(name);

					displayButtons.add(ib);
					displayLabels.add(jl);
				}
			}

			// 显示卡按钮
			for (ImageButton j : displayButtons) {
				this.add(j);
			}
			// 显示卡标签
			for (JLabel j : displayLabels) {
				this.add(j);
			}

			// 监听器
			for (ImageButton j : displayButtons) {
				j.addMouseListener(this);
				j.addMouseMotionListener(this);
			}

			repaint();
		} else {
			// 清空已创建的按钮、标签
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
			// 取消模式按钮
			if (e.getSource() == buttonNo) {
				buttonYes.setVisible(false);
				buttonNo.setVisible(false);

				// 取消点亮
				for (ImageButton ib : buttons) {
					ib.setLight(false);
				}
				// 取消点亮
				for (ImageButton ib : objDel) {
					ib.setLight(false);
				}
				// 清空要删除的列表
				objDel.clear();
				model = -1;
			}
			if (type == "Person") {
				if (((MainFrame) jf).getModel().equals("管理员")) {
					// 点击查询按钮
					if (e.getSource() == buttonExplorer) {

						/* 按下特效 */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonExplorer.setImg(buttonExplorer.getImgOn());

						refreshDisplay(sqlOp
								.getPersonFromFuzzy(JOptionPane.showInputDialog(jf, "请输入要查询的用户账号或者名字(模糊查询),留空查询全部")));

						buttonExplorer.setImg(buttonExplorer.getImgOff());
					}

					// 点击添加按钮
					else if (e.getSource() == buttonAdd) {
						/* 按下特效 */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonAdd.setImg(buttonAdd.getImgOn());

						new DialogOpPerson(jf, "添加用户", "Add");
						buttonAdd.setImg(buttonAdd.getImgOff());
					}

					// 点击删除按钮
					else if (e.getSource() == buttonDel) {

						/* 按下特效 */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonDel.setImg(buttonDel.getImgOn());

						// 进入删除模式
						model = 1;
						buttonYes.setVisible(true);
						buttonNo.setVisible(true);
					}
					// 点击修改按钮
					else if (e.getSource() == buttonModify) {

						/* 按下特效 */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonModify.setImg(buttonModify.getImgOn());

						// 进入修改模式
						model = 2;
						buttonYes.setVisible(false);
						buttonNo.setVisible(true);
					} // 点击显示的东西
					else if (displayButtons.contains(e.getSource())) {
						ImageButton ib = (ImageButton) e.getSource();

						// 一般模式 点击查看详细信息
						if (model == -1) {
							try {
								new DialogOpPerson(jf, "用户详情", "Info", ib.getName());
							} catch (SQLException e1) {
							}
						}
						// 删除模式 点击选中/不选中
						else if (model == 1) {

							// 取消选中
							if (ib.getIsLight() == true) {
								objDel.remove(this);
								ib.setLight(false);
							}
							// 选中
							else if (ib.getIsLight() == false) {
								objDel.add(ib);
								ib.setLight(true);
							}
						}
						// 修改模式 点击修改详细信息
						else if (model == 2) {
							try {
								new DialogOpPerson(jf, "修改用户", "Modify", ib.getName());
							} catch (SQLException e1) {
							}
							// model = -1;
						}
					}
					// 删除确定按钮
					else if (e.getSource() == buttonYes) {
						buttonYes.setVisible(false);
						buttonNo.setVisible(false);

						// 执行删除
						int amount = objDel.size();
						for (int i = 0; i < amount; i++) {
							sqlOp.deletePerson(((Person) objDisplay.get(i)).getAccount());
							objDisplay.remove(i);
							i--;
							amount--;
						}

						refreshDisplay(objDisplay);
						// 清空要删除的列表
						objDel.clear();
						model = -1;
					}
				}

				// 普通用户的Person标签显示个人的拍卖品
				else {
					// 点击查询按钮
					if (e.getSource() == buttonExplorer) {

						/* 按下特效 */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}

						buttonExplorer.setImg(buttonExplorer.getImgOn());

						refreshDisplay(sqlOp.getEntityFromNameFuzzyFromAccount(
								JOptionPane.showInputDialog(jf, "请输入要查询你拥有的拍卖品名字(模糊查询)"),
								((MainFrame) jf).getAccount()));
						buttonExplorer.setImg(buttonExplorer.getImgOff());

					}

					// 点击添加按钮
					else if (e.getSource() == buttonAdd) {

						/* 按下特效 */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonAdd.setImg(buttonAdd.getImgOn());

						new DialogOpEntity((MainFrame) jf, "添加拍卖品", "Add");

						buttonAdd.setImg(buttonAdd.getImgOff());
					}

					// 点击删除按钮
					else if (e.getSource() == buttonDel) {

						/* 按下特效 */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonDel.setImg(buttonDel.getImgOn());

						// 进入删除模式
						model = 1;
						buttonYes.setVisible(true);
						buttonNo.setVisible(true);

					}
					// 点击修改按钮
					else if (e.getSource() == buttonModify) {

						/* 按下特效 */
						for (ImageButton ib : buttons) {
							ib.setImg(ib.getImgOff());
						}
						buttonModify.setImg(buttonModify.getImgOn());

						// 进入修改模式
						model = 2;
						buttonYes.setVisible(false);
						buttonNo.setVisible(true);

					}

					// 点击显示的东西
					else if (displayButtons.contains(e.getSource())) {
						ImageButton ib = (ImageButton) e.getSource();

						// 一般模式 点击查看详细信息
						if (model == -1) {
							new DialogOpEntity((MainFrame) jf, "拍卖品详情", "Info", ib.getName());
						}
						// 删除模式 点击选中/不选中
						else if (model == 1) {

							// 取消选中
							if (ib.getIsLight() == true) {
								objDel.remove(this);
								ib.setLight(false);
							}
							// 选中
							else if (ib.getIsLight() == false) {
								objDel.add(ib);
								ib.setLight(true);
							}
						}
						// 修改模式 点击修改详细信息
						else if (model == 2) {
							new DialogOpEntity((MainFrame) jf, "修改拍卖品", "Modify", ib.getName());

							// model = -1;
						}
					}
					// 删除确定按钮
					else if (e.getSource() == buttonYes) {
						buttonYes.setVisible(false);
						buttonNo.setVisible(false);

						// 执行删除
						int amount = objDel.size();
						for (int i = 0; i < amount; i++) {
							sqlOp.deleteEntity(((Entity) objDisplay.get(i)).getName());
							objDisplay.remove(i);
							i--;
							amount--;
						}

						refreshDisplay(objDisplay);
						// 清空要删除的列表
						objDel.clear();
						model = -1;
					}
				}
			}

			else if (type == "Entity") {
				// 点击查询按钮
				if (e.getSource() == buttonExplorer) {

					/* 按下特效 */
					for (ImageButton ib : buttons) {
						ib.setImg(ib.getImgOff());
					}
					buttonExplorer.setImg(buttonExplorer.getImgOn());

					refreshDisplay(sqlOp.getEntityFromNameFuzzy(JOptionPane.showInputDialog(jf, "请输入要查询的拍卖品名字(模糊查询)")));
					buttonExplorer.setImg(buttonExplorer.getImgOff());

				}

				// 点击添加按钮
				else if (e.getSource() == buttonAdd) {

					/* 按下特效 */
					for (ImageButton ib : buttons) {
						ib.setImg(ib.getImgOff());
					}
					buttonAdd.setImg(buttonAdd.getImgOn());

					new DialogOpEntity((MainFrame) jf, "添加拍卖品", "Add");

					buttonAdd.setImg(buttonAdd.getImgOff());
				}

				// 点击删除按钮
				else if (e.getSource() == buttonDel) {

					/* 按下特效 */
					for (ImageButton ib : buttons) {
						ib.setImg(ib.getImgOff());
					}
					buttonDel.setImg(buttonDel.getImgOn());

					// 进入删除模式
					model = 1;
					buttonYes.setVisible(true);
					buttonNo.setVisible(true);

				}
				// 点击修改按钮
				else if (e.getSource() == buttonModify) {

					/* 按下特效 */
					for (ImageButton ib : buttons) {
						ib.setImg(ib.getImgOff());
					}
					buttonModify.setImg(buttonModify.getImgOn());

					// 进入修改模式
					model = 2;
					buttonYes.setVisible(false);
					buttonNo.setVisible(true);

				}

				// 点击显示的东西
				else if (displayButtons.contains(e.getSource())) {
					ImageButton ib = (ImageButton) e.getSource();

					// 一般模式 点击查看详细信息
					if (model == -1) {
						new DialogOpEntity((MainFrame) jf, "拍卖品详情", "Info", ib.getName());
					}
					// 删除模式 点击选中/不选中
					else if (model == 1) {

						// 取消选中
						if (ib.getIsLight() == true) {
							objDel.remove(this);
							ib.setLight(false);
						}
						// 选中
						else if (ib.getIsLight() == false) {
							objDel.add(ib);
							ib.setLight(true);
						}
					}
					// 修改模式 点击修改详细信息
					else if (model == 2) {
						new DialogOpEntity((MainFrame) jf, "修改拍卖品", "Modify", ib.getName());

						// model = -1;
					}
				}
				// 删除确定按钮
				else if (e.getSource() == buttonYes) {
					buttonYes.setVisible(false);
					buttonNo.setVisible(false);

					// 执行删除
					int amount = objDel.size();
					for (int i = 0; i < amount; i++) {
						sqlOp.deleteEntity(((Entity) objDisplay.get(i)).getName());
						objDisplay.remove(i);
						i--;
						amount--;
					}

					refreshDisplay(objDisplay);
					// 清空要删除的列表
					objDel.clear();
					model = -1;
				}
			}
		}
		/* 右键点击显示的按钮 */
		/* 竞拍 */
		else if (e.getButton() == 3) {
			if (((MainFrame) jf).getModel().equals("普通用户") && type.equals("Entity")) {
				if (displayButtons.contains(e.getSource())) {
					ImageButton ib = (ImageButton) e.getSource();
					// 不是自己卖的
					if (!sqlOp.getEntityFromName(ib.getName()).getSeller().getAccount().equals(((MainFrame)jf).getAccount())) {
						new BuyDialog((MainFrame) jf, "竞拍", ib.getName());
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
