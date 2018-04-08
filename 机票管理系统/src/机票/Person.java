package 机票;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;

/*
 * 联系人
 * 功能说明：设置联系人信息/保存联系人到文件/创建修改后新的联系人
 * author:
 
 * date:2013-06-07
 */
public class Person {
    private String name; //联系人名字
    private String mobile;//联系电话
    private String worktel;//联系办公
    private String sex;//性别
    private String birthday;
    public Person(String name,String mobile,String worktel,String sex,String birthday){
        //构造方法，用来构造新的联系人
        this.setName(name);
        this.setMobile(mobile);
        this.setworktel(worktel);
        this.setSex(sex);
        this.setBirthday(birthday);
    }
	public String getName() {
        return name; //获取联系人名字
    }
    public void setName(String name) {
        this.name = name;//设置联系人名字
    }
    public String getMobile() {
        return mobile;//获取联系人电话
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;//设置联系人电话
    }
    public String getworktel() {
        return worktel;//获取联系人通讯地址
    }
    public void setworktel(String worktel) {
        this.worktel = worktel;//设置联系人通讯地址
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
        //需要指定输出对象
        try {
            out.write(this.name +"|"+this.mobile+"|"+this.worktel+"|"+this.sex+"|"+this.birthday); //按照格式保存联系人
            out.newLine();//在文件中写入新行
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("保存联系人时发生异常！");
            e.printStackTrace();
        }
         
         
    }
    public Person getUpdatePerson(){
        //当你修改该联系人部分信息时，想生成一个新的对象，就可以采用该方法
        //该方法的解决不是为了复制对象
        return new Person(this.name,this.mobile,this.worktel,this.sex,this.birthday);
    }
}