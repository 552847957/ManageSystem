package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import resource.resource;
import OwnerInfo.OwnerInfo;
import frame.mainFrame;

public class sqlOperation implements resource {
	public Connection con;
	public Statement sta;
  	public sqlOperation() {
		// 连接数据库
		getConnection();
	}

	public void getConnection() {
		try {
			Class.forName(sqlClass);
			con = DriverManager.getConnection(sqlConnection);
			sta = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		try {
			sta.executeUpdate("create table sjk(" 
					+ "id int generated always as identity,"
					+ "name VARCHAR(20)," 
					+ "age INT,"
					+ "houseNum VARCHAR(20)," 
					+ "familyNum INT,"
					+ "sex VARCHAR(20)," 
					+ "marriageState VARCHAR(20)" + ")");
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("表已经存在");
		}
	}
	
	public void addOwner(OwnerInfo oi) {
		try {
			sta.executeUpdate("INSERT INTO sjk(name,age,houseNum,familyNum,sex,marriageState) "
					+ "VALUES ("
					+"'"+oi.getName()+"',"
					+oi.getAge()
					+",'"+oi.getHouseNum()+"',"
					+oi.getFamilyNum()
					+",'"+oi.getSex()+"',"
					+"'"+oi.getMarriageState()+"'"
					+")");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setOwner(OwnerInfo oi) {
		try {
			sta.executeUpdate("DELETE FROM sjk WHERE houseNum = '"+oi.getHouseNum()+"'");
			sta.executeUpdate("INSERT INTO sjk(name,age,houseNum,familyNum,sex,marriageState) "
					+ "VALUES ("
					+"'"+oi.getName()+"',"
					+oi.getAge()+","
					+"'"+oi.getHouseNum()+"'"+","
					+oi.getFamilyNum()+","
					+"'"+oi.getSex()+"'"+","
					+"'"+oi.getMarriageState()+"'"
					+")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteOwner(String houseNum){
		try {
			sta.executeUpdate("DELETE FROM sjk WHERE houseNum = '"+houseNum+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 通过门牌号获取业主信息
	public OwnerInfo getOwner(String houseNum) {
		ResultSet rs = null;
		try {
			
			rs = sta.executeQuery("select * from sjk where houseNum = '"
					+ houseNum + "'");
			rs.next();
			return new OwnerInfo(rs.getInt(1),rs.getString(2), rs.getInt(3),
					rs.getString(4), rs.getInt(5), rs.getString(6),
					rs.getString(7));
		} catch (SQLException e) {
			System.out.println("不存在Owner");
		}
		return null;
	}

	
	// 获取业主信息
	public ResultSet getAll() {
		ResultSet rs = null;
		try {
			
			rs = sta.executeQuery("select * from sjk");
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getAccount() {
		return "qwer";
	}

	public String getPassword() {
		return "zym";
	}
}
