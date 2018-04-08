package ��Ʊ;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

public class MainClass {

	/**
	 * @param args
	 * @product JavaͨѶ¼
	 */
	private static AddressBook myAddressBook = new AddressBook();// ����ͨѶ¼����
	private static File f = new File("default.txt");// �򿪵�ͨѶ¼�ļ�
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
		show("----------------------------�ҵ�ͨѶ¼----------------------------");
		show("���˵�");
		show("\t1  �鿴ͨѶ¼");
		show("\t2 ������ϵ��");
		show("\t3 ������ϵ��");
		show("\t4 �޸���ϵ��");
		show("\t5 ɾ����ϵ��");
		show("\t6 ����ͨѶ¼");
		show("\t7 �˳�ͨѶ¼");
		System.out.print("���������ѡ��˵���");
		Scanner keyin = new Scanner(System.in);
		int select = keyin.nextInt();
		switch (select) {
		case 1:
			showAddressBook();
			break;// �鿴ͨѶ¼
		case 2:
			findPerson();
			break;// ������ϵ��
		case 3:
			addNewPerson();
			break;// ������ϵ��
		case 4:
			modifiedPerson();
			break;// �޸���ϵ��
		case 5:
			removePerson();
			break;// ɾ����ϵ��
		case 6:
			saveAddressBook();
			break;// ����ͨѶ¼
		case 7:
			System.exit(0);
			break;// �˳�ͨѶ¼
		default:
			showMainMenu();
		}

	}

	public static void show(String s) {
		System.out.println(s);
	}

	public static void enter() {
		new Scanner(System.in).nextLine();// ���س�ȷ�ϼ���
	}

	public static void showAddressBook() {
		Iterator<Person> itor = myAddressBook.iterator();
		Person p;
		if (myAddressBook.size() == 0) {
			show("ͨѶ¼Ϊ�գ����س�ȷ�Ϸ������˵���");
			enter();
			showMainMenu();
			return;
		}
		int i = 1;
		while (itor.hasNext()) {
			p = itor.next();
			showPerson(p);// ��ʾ��ϵ��
			if (i++ % 5 == 0)
				enter();// ÿ����ʾ5����ϵ�ˣ����س�����
		}
		show("�Ѿ������ȫ����ϵ�ˡ����س�ȷ�Ϸ������˵�");
		enter();
		showMainMenu();

	}

	public static void showPerson(Person p) {
		show("[��ϵ��]���� " + p.getName() + "�绰 " + p.getMobile() + "��ϵ�칫 " + p.getworktel() + "�Ա�" + p.getSex() + "����"
				+ p.getBirthday());
	}

	public static void findPerson() {
		show("----------������ϵ��----------");
		System.out.print("��������ϵ������_");
		String search = new Scanner(System.in).next();
		Person person[] = myAddressBook.findPerson(search);// ������ϵ��
		if (person.length == 0) {
			show("û���ҵ�����Ҫ�����ϵ�ˣ����س���ȷ�Ϸ������˵���");
			enter();
			showMainMenu();
			return;
		}
		int i = 1;
		for (Person p : person) {
			showPerson(p);// ��ʾ��ϵ��
			if (i++ % 5 == 0) {
				enter();
			}
		}
		show("�����ǲ��ҵ���������ϵ�ˣ����س���ȷ�Ϸ������˵���");
		enter();
		showMainMenu();

	}

	public static void addNewPerson() {
		show("----------������ϵ��-----------");
		show("������ϵ����Ϣ��");
		System.out.print("������");
		String name = new Scanner(System.in).next();
		System.out.print("�绰��");
		String mobile = new Scanner(System.in).next();
		System.out.print("��ϵ�칫��");
		String worktel = new Scanner(System.in).next();
		System.out.print("�Ա�:");
		String sex = new Scanner(System.in).next();
		System.out.print("����:");
		String birthday = new Scanner(System.in).next();
		Person p = new Person(name, mobile, worktel, sex, birthday);
		show("����Ҫ�������µ���ϵ�ˣ�");
		showPerson(p);
		System.out.print("������Ҫȷ�ϴ�������(y)/��(n)");
		String yesno = new Scanner(System.in).next();
		if ("y".equals(yesno)) {
			myAddressBook.addPerson(p);
			show("��ϵ������ӣ����س���ȷ�Ϸ������˵���");
			enter();
			showMainMenu();
		} else {
			show("���ѷ������������ϵ�ˣ����س���ȷ�Ϸ������˵���");
			enter();
			showMainMenu();
		}

	}

	private static void removePerson() {
		show("----------ɾ����ϵ��-----------");
		show("������ϵ��������");
		name = new Scanner(System.in).next();
		Person fp[] = myAddressBook.findPerson(name);
		if (fp.length == 0) {
			// ������
			return;
		} else {
			fp[0].getName();
			Person p = new Person(fp[0].getName(), fp[0].getMobile(), fp[0].getworktel(), fp[0].getSex(),
					fp[0].getBirthday());
			show("����Ҫɾ�����µ���ϵ�ˣ�");
			showPerson(p);
			System.out.print("������Ҫȷ��ɾ������(y)/��(n)");
			String yesno = new Scanner(System.in).next();
			if ("y".equals(yesno)) {

				for (int i = 0; i < myAddressBook.size(); i++) {
					if (myAddressBook.get(i).getName().equals(name)) {
						myAddressBook.remove(i);
						break;
					}
				}
				show("��ϵ����ɾ�������س���ȷ�Ϸ������˵���");
				enter();
				showMainMenu();
			} else {
				show("���ѷ�����ɾ����ϵ�ˣ����س���ȷ�Ϸ������˵���");
				enter();
				showMainMenu();

			}
		}
	}

	public static void modifiedPerson() {
		show("----------�޸���ϵ��-----------");
		show("������ϵ��������");
		name = new Scanner(System.in).next();
		Person fp[] = myAddressBook.findPerson(name);
		if (fp.length == 0) {
			// ������
			return;
		} else {
			fp[0].getName();
			Person p = new Person(fp[0].getName(), fp[0].getMobile(), fp[0].getworktel(), fp[0].getSex(),
					fp[0].getBirthday());
			
			show("ȷ����Ҫ�޸���ϵ����Ϣ:");
			showPerson(p);
			System.out.print("������Ҫȷ���޸�����(y)/��(n)");
			String yesno = new Scanner(System.in).next();
			if ("y".equals(yesno)) {
				Scanner s=new Scanner(System.in);
				System.out.print("�绰��");
				mobile = s.next();
				System.out.print("��ϵ�칫��");
				worktel = s.next();
				System.out.print("�Ա�:");
				sex = s.next();
				System.out.print("����:");
				birthday = s.next();
				Person p2 = new Person(name, mobile, worktel, sex, birthday);
				for (int i = 0; i < myAddressBook.size(); i++) {
					if (myAddressBook.get(i).getName().equals(name))
						myAddressBook.modifiedPerson(i, p2);
				}

				show("��ϵ�����޸ģ����س���ȷ�Ϸ������˵���");
				enter();
				showMainMenu();
			} else {
				show("���ѷ������޸���ϵ�ˣ����س���ȷ�Ϸ������˵���");
				enter();
				showMainMenu();

			}

		}

	}

	public static void openAddressBook() {
		show("----------��ͨѶ¼----------");
		System.out.print("����ͨѶ¼�ļ���_");
		f = new File(new Scanner(System.in).next());
		myAddressBook.loadAddressBook(f);
		show("�򿪳ɹ������س���ȷ�Ϸ������˵���");
		enter();
		showMainMenu();
	}

	public static void saveAddressBook() {
		myAddressBook.saveAddressBook(f);
		show("����ɹ������س���ȷ�Ϸ������˵���");
		enter();
		showMainMenu();
	}
}
