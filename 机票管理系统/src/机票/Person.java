package ��Ʊ;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;

/*
 * ��ϵ��
 * ����˵����������ϵ����Ϣ/������ϵ�˵��ļ�/�����޸ĺ��µ���ϵ��
 * author:
 
 * date:2013-06-07
 */
public class Person {
    private String name; //��ϵ������
    private String mobile;//��ϵ�绰
    private String worktel;//��ϵ�칫
    private String sex;//�Ա�
    private String birthday;
    public Person(String name,String mobile,String worktel,String sex,String birthday){
        //���췽�������������µ���ϵ��
        this.setName(name);
        this.setMobile(mobile);
        this.setworktel(worktel);
        this.setSex(sex);
        this.setBirthday(birthday);
    }
	public String getName() {
        return name; //��ȡ��ϵ������
    }
    public void setName(String name) {
        this.name = name;//������ϵ������
    }
    public String getMobile() {
        return mobile;//��ȡ��ϵ�˵绰
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;//������ϵ�˵绰
    }
    public String getworktel() {
        return worktel;//��ȡ��ϵ��ͨѶ��ַ
    }
    public void setworktel(String worktel) {
        this.worktel = worktel;//������ϵ��ͨѶ��ַ
    }
    public String getSex() {
    	return sex;
    }
    public void setSex(String sex){
    	this.sex=sex;
    }
    public String getBirthday(){
    	return birthday;
    }
    public void setBirthday(String birthday){
    	this.birthday=birthday;
    }
    public void saveMe(BufferedWriter out){
        //��Ҫָ���������
        try {
            out.write(this.name +"|"+this.mobile+"|"+this.worktel+"|"+this.sex+"|"+this.birthday); //���ո�ʽ������ϵ��
            out.newLine();//���ļ���д������
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("������ϵ��ʱ�����쳣��");
            e.printStackTrace();
        }
         
         
    }
    public Person getUpdatePerson(){
        //�����޸ĸ���ϵ�˲�����Ϣʱ��������һ���µĶ��󣬾Ϳ��Բ��ø÷���
        //�÷����Ľ������Ϊ�˸��ƶ���
        return new Person(this.name,this.mobile,this.worktel,this.sex,this.birthday);
    }
}