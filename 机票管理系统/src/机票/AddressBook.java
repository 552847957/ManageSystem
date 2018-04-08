package ��Ʊ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
/*
 * ͨѶ¼
 * ����˵�������ļ�������ͨѶ¼/����/�޸�/ɾ����ϵ��/����ͨѶ¼
 * author:
 
 * date:2013-06-07
 */
public class AddressBook extends ArrayList<Person>{
    public AddressBook(){
        //������ͨѶ¼
    }
    public void loadAddressBook(File bookFile){
        //���ļ�������ͨѶ¼
        try {
            FileReader fr=new FileReader(bookFile);
            BufferedReader bfReader=new BufferedReader(fr);
            String temp;
            while((temp=bfReader.readLine())!=null){
                String attribute[]=temp.split("|");//��һ�����ϸ��ո�ʽ�ָ���ϵ����Ϣ��ȡ�� ����,�绰����,��ϵ�칫���Ա�����
                this.add(new Person(attribute[1],attribute[3],attribute[5], attribute[4], attribute[5])); //ӳ�䵽ͨѶ¼�����е���ϵ��
            }
            bfReader.close();
            fr.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("���ļ�'"+bookFile.getName()+"'����ͨѶ¼ʧ��!");
            e.printStackTrace();
        }
         
    }
    public void saveAddressBook(File bookFile){
        Person p;
        try {
            FileWriter fw=new FileWriter(bookFile);
            BufferedWriter bfWriter=new BufferedWriter(fw);
            Iterator<Person> itor=this.iterator();//������
            while(itor.hasNext()){
                p=itor.next();//��һ����ϵ��
                p.saveMe(bfWriter);//������ϵ�˵ı��湦��
                bfWriter.flush();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("д��ͨѶ¼�ļ�"+bookFile.getName()+"ʧ�ܣ�");
            e.printStackTrace();
        }
         
    }
    public void addPerson(Person p){
        this.add(p);//������ϵ��
    }
    public boolean removePerson(Person p){
        return this.remove(p);//ɾ����ϵ��
    }
    public void modifiedPerson(int i,Person pNew){
        this.set(i, pNew);//�޸���ϵ��
    }
    public Person[] findPerson(String name){
        Iterator<Person> itor=this.iterator();//������
        Person p;
        ArrayList<Person> finds=new ArrayList<Person>(0);//������ҽ��
        while(itor.hasNext()){
            p=itor.next();//����һ����ϵ��
            String pName=p.getName();//ȡ����ϵ������
            boolean b=name.matches("["+pName+"]*"); //ƥ����ϵ������
            if(b) finds.add(p);//�洢����������ϵ��
        }
        Person findp[]=new Person[finds.size()];
        return finds.toArray(findp);
    }
    public int getSize(){
        return this.size();//����ͨѶ¼��ϵ����Ŀ
    }
}
