package 机票;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class MainClass {

	/**
	 * @param args
	 * @product Java通讯录
	 */
	private static AddressBook myAddressBook = new AddressBook();// 创建通讯录对象
	private static File f = new File("default.txt");// 打开的通讯录文件
	private static String name;
	private static String mobile;
	private static String worktel;
	private static String sex;
	private static String birthday;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		showMainMenu();
	}

	public static void showMainMenu() {
		show("----------------------------我的通讯录----------------------------");
		show("主菜单");
		show("\t1  查看通讯录");
		show("\t2 查找联系人");
		show("\t3 新增联系人");
		show("\t4 修改联系人");
		show("\t5 删除联系人");
		show("\t6 保存通讯录");
		show("\t7 退出通讯录");
		System.out.print("请输入序号选择菜单：");
		Scanner keyin = new Scanner(System.in);
		int select = keyin.nextInt();
		switch (select) {
		case 1:
			showAddressBook();
			break;// 查看通讯录
		case 2:
			findPerson();
			break;// 查找联系人
		case 3:
			addNewPerson();
			break;// 新增联系人
		case 4:
			modifiedPerson();
			break;// 修改联系人
		case 5:
			removePerson();
			break;// 删除联系人
		case 6:
			saveAddressBook();
			break;// 保存通讯录
		case 7:
			System.exit(0);
			break;// 退出通讯录
		default:
			showMainMenu();
		}

	}

	public static void show(String s) {
		System.out.println(s);
	}

	public static void enter() {
		new Scanner(System.in).nextLine();// 按回车确认继续
	}

	public static void showAddressBook() {
		Iterator<Person> itor = myAddressBook.iterator();
		Person p;
		if (myAddressBook.size() == 0) {
			show("通讯录为空！按回车确认返回主菜单。");
			enter();
			showMainMenu();
			return;
		}
		int i = 1;
		while (itor.hasNext()) {
			p = itor.next();
			showPerson(p);// 显示联系人
			if (i++ % 5 == 0)
				enter();// 每次显示5个联系人，按回车继续
		}
		show("已经浏览完全部联系人。按回车确认返回主菜单");
		enter();
		showMainMenu();

	}

	public static void showPerson(Person p) {
		show("[联系人]姓名 " + p.getName() + "电话 " + p.getMobile() + "联系办公 " + p.getworktel() + "性别" + p.getSex() + "生日"
				+ p.getBirthday());
	}

	public static void findPerson() {
		show("----------查找联系人----------");
		System.out.print("请输入联系人姓名_");
		String search = new Scanner(System.in).next();
		Person person[] = myAddressBook.findPerson(search);// 查找联系人
		if (person.length == 0) {
			show("没有找到符合要求的联系人！按回车键确认返回主菜单。");
			enter();
			showMainMenu();
			return;
		}
		int i = 1;
		for (Person p : person) {
			showPerson(p);// 显示联系人
			if (i++ % 5 == 0) {
				enter();
			}
		}
		show("以上是查找到的所有联系人！按回车键确认返回主菜单。");
		enter();
		showMainMenu();

	}

	public static void addNewPerson() {
		show("----------新增联系人-----------");
		show("输入联系人信息：");
		System.out.print("姓名：");
		String name = new Scanner(System.in).next();
		System.out.print("电话：");
		String mobile = new Scanner(System.in).next();
		System.out.print("联系办公：");
		String worktel = new Scanner(System.in).next();
		System.out.print("性别:");
		String sex = new Scanner(System.in).next();
		System.out.print("生日:");
		String birthday = new Scanner(System.in).next();
		Person p = new Person(name, mobile, worktel, sex, birthday);
		show("您将要创建如下的联系人：");
		showPerson(p);
		System.out.print("请问您要确认创建吗？是(y)/否(n)");
		String yesno = new Scanner(System.in).next();
		if ("y".equals(yesno)) {
			myAddressBook.addPerson(p);
			show("联系人已添加！按回车键确认返回主菜单。");
			enter();
			showMainMenu();
		} else {
			show("您已放弃了添加新联系人！按回车键确认返回主菜单。");
			enter();
			showMainMenu();
		}

	}

	private static void removePerson() {
		show("----------删除联系人-----------");
		show("输入联系人姓名：");
		name = new Scanner(System.in).next();
		Person fp[] = myAddressBook.findPerson(name);
		if (fp.length == 0) {
			// 不存在
			return;
		} else {
			fp[0].getName();
			Person p = new Person(fp[0].getName(), fp[0].getMobile(), fp[0].getworktel(), fp[0].getSex(),
					fp[0].getBirthday());
			show("您将要删除如下的联系人：");
			showPerson(p);
			System.out.print("请问您要确认删除吗？是(y)/否(n)");
			String yesno = new Scanner(System.in).next();
			if ("y".equals(yesno)) {

				for (int i = 0; i < myAddressBook.size(); i++) {
					if (myAddressBook.get(i).getName().equals(name)) {
						myAddressBook.remove(i);
						break;
					}
				}
				show("联系人已删除！按回车键确认返回主菜单。");
				enter();
				showMainMenu();
			} else {
				show("您已放弃了删除联系人！按回车键确认返回主菜单。");
				enter();
				showMainMenu();

			}
		}
	}

	public static void modifiedPerson() {
		show("----------修改联系人-----------");
		show("输入联系人姓名：");
		name = new Scanner(System.in).next();
		Person fp[] = myAddressBook.findPerson(name);
		if (fp.length == 0) {
			// 不存在
			return;
		} else {
			fp[0].getName();
			Person p = new Person(fp[0].getName(), fp[0].getMobile(), fp[0].getworktel(), fp[0].getSex(),
					fp[0].getBirthday());
			
			show("确认需要修改联系人信息:");
			showPerson(p);
			System.out.print("请问您要确认修改吗？是(y)/否(n)");
			String yesno = new Scanner(System.in).next();
			if ("y".equals(yesno)) {
				Scanner s=new Scanner(System.in);
				System.out.print("电话：");
				mobile = s.next();
				System.out.print("联系办公：");
				worktel = s.next();
				System.out.print("性别:");
				sex = s.next();
				System.out.print("生日:");
				birthday = s.next();
				Person p2 = new Person(name, mobile, worktel, sex, birthday);
				for (int i = 0; i < myAddressBook.size(); i++) {
					if (myAddressBook.get(i).getName().equals(name))
						myAddressBook.modifiedPerson(i, p2);
				}

				show("联系人已修改！按回车键确认返回主菜单。");
				enter();
				showMainMenu();
			} else {
				show("您已放弃了修改联系人！按回车键确认返回主菜单。");
				enter();
				showMainMenu();

			}

		}

	}

	public static void openAddressBook() {
		show("----------打开通讯录----------");
		System.out.print("输入通讯录文件名_");
		f = new File(new Scanner(System.in).next());
		myAddressBook.loadAddressBook(f);
		show("打开成功！按回车键确认返回主菜单。");
		enter();
		showMainMenu();
	}

	public static void saveAddressBook() {
		myAddressBook.saveAddressBook(f);
		show("保存成功！按回车键确认返回主菜单。");
		enter();
		showMainMenu();
	}
}
