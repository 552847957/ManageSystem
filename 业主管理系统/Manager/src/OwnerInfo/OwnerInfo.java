package OwnerInfo;

public class OwnerInfo 
{
	private int id;
	// 业主名字
	private String name;
	// 业主年龄
	private int age;
	// 房子门牌号
	private String houseNum;
	// 家庭成员数量
	private int familyNum;
	// 性别
	private String sex;
	// 婚姻状况
	private String marriageState;

	// 构造函数
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
	// 获取业主ID
	public int getID() {
		return id;
	}

	// 获取业主名字
	public String getName() {
		return name;
	}

	// 获取年龄
	public int getAge() {
		return age;
	}

	// 获取房子门牌号
	public String getHouseNum() {
		return houseNum;
	}

	// 获取家庭成员数量
	public int getFamilyNum() {
		return familyNum;
	}

	// 获取性别
	public String getSex() {
		return sex;
	}

	// 获取婚姻状况
	public String getMarriageState() {
		return marriageState;
	}

	// 设置业主名字
	public void setName(String n) {
		name = n;
	}

	// 设置年龄
	public void setAge(int a) {
		age = a;
	}

	// 设置房子门牌号
	public void setHouseNum(String hn) {
		houseNum = hn;
	}

	// 设置家庭成员数量
	public void setFamilyNum(int fn) {
		familyNum = fn;
	}

	// 设置性别
	public void setSex(String s) {
		sex = s;
	}

	// 设置婚姻状况
	public void setMarriageState(String ms) {
		marriageState = ms;
	}
}
