package 机票;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
/*
 * 通讯录
 * 功能说明：从文件中载入通讯录/查找/修改/删除联系人/保存通讯录
 * author:
 
 * date:2013-06-07
 */
public class AddressBook extends ArrayList<Person>{
    public AddressBook(){
        //创建空通讯录
    }
    public void loadAddressBook(File bookFile){
        //从文件中载入通讯录
        try {
            FileReader fr=new FileReader(bookFile);
            BufferedReader bfReader=new BufferedReader(fr);
            String temp;
            while((temp=bfReader.readLine())!=null){
                String attribute[]=temp.split("|");//这一步将严格按照格式分割联系人信息，取得 姓名,电话号码,联系办公，性别，生日
                this.add(new Person(attribute[1],attribute[3],attribute[5], attribute[4], attribute[5])); //映射到通讯录对象中的联系人
            }
            bfReader.close();
            fr.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("从文件'"+bookFile.getName()+"'载入通讯录失败!");
            e.printStackTrace();
        }
         
    }
    public void saveAddressBook(File bookFile){
        Person p;
        try {
            FileWriter fw=new FileWriter(bookFile);
            BufferedWriter bfWriter=new BufferedWriter(fw);
            Iterator<Person> itor=this.iterator();//迭代器
            while(itor.hasNext()){
                p=itor.next();//下一个联系人
                p.saveMe(bfWriter);//调用联系人的保存功能
                bfWriter.flush();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("写入通讯录文件"+bookFile.getName()+"失败！");
            e.printStackTrace();
        }
         
    }
    public void addPerson(Person p){
        this.add(p);//新增联系人
    }
    public boolean removePerson(Person p){
        return this.remove(p);//删除联系人
    }
    public void modifiedPerson(int i,Person pNew){
        this.set(i, pNew);//修改联系人
    }
    public Person[] findPerson(String name){
        Iterator<Person> itor=this.iterator();//迭代器
        Person p;
        ArrayList<Person> finds=new ArrayList<Person>(0);//保存查找结果
        while(itor.hasNext()){
            p=itor.next();//查下一个联系人
            String pName=p.getName();//取得联系人名字
            boolean b=name.matches("["+pName+"]*"); //匹配联系人名字
            if(b) finds.add(p);//存储搜索到的联系人
        }
        Person findp[]=new Person[finds.size()];
        return finds.toArray(findp);
    }
    public int getSize(){
        return this.size();//返回通讯录联系人数目
    }
}
