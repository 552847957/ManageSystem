package OwnerInfo;

public class OwnerInfo 
{
	private int id;
	// ҵ������
	private String name;
	// ҵ������
	private int age;
	// �������ƺ�
	private String houseNum;
	// ��ͥ��Ա����
	private int familyNum;
	// �Ա�
	private String sex;
	// ����״��
	private String marriageState;

	// ���캯��
	public OwnerInfo(int id,String name, int age, String houseNum, int familyNum,
			String sex, String marriageState) {
		this.id=id;
		this.name = name;
		this.age = age;
		this.houseNum = houseNum;
		this.familyNum = familyNum;
		this.sex = sex;
		this.marriageState = marriageState;
	}
	// ��ȡҵ��ID
	public int getID() {
		return id;
	}

	// ��ȡҵ������
	public String getName() {
		return name;
	}

	// ��ȡ����
	public int getAge() {
		return age;
	}

	// ��ȡ�������ƺ�
	public String getHouseNum() {
		return houseNum;
	}

	// ��ȡ��ͥ��Ա����
	public int getFamilyNum() {
		return familyNum;
	}

	// ��ȡ�Ա�
	public String getSex() {
		return sex;
	}

	// ��ȡ����״��
	public String getMarriageState() {
		return marriageState;
	}

	// ����ҵ������
	public void setName(String n) {
		name = n;
	}

	// ��������
	public void setAge(int a) {
		age = a;
	}

	// ���÷������ƺ�
	public void setHouseNum(String hn) {
		houseNum = hn;
	}

	// ���ü�ͥ��Ա����
	public void setFamilyNum(int fn) {
		familyNum = fn;
	}

	// �����Ա�
	public void setSex(String s) {
		sex = s;
	}

	// ���û���״��
	public void setMarriageState(String ms) {
		marriageState = ms;
	}
}
