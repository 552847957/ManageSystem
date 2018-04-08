package com.sell.sql;

import java.sql.*;
import java.util.ArrayList;

import tools.resource;

import com.sell.service.Entity;
import com.sell.service.Person;
import com.sell.service.time;
import com.sell.service.Type.TypeEntity;
import com.sell.service.Type.TypePerson;

/**
 * 数据库操作类
 */
public class SqlOperation implements resource {

	/* 数据库连接对象 */
	public Connection con;

	/* 数据库语句发送对象 */
	private Statement sta;

	/** 
	 * 初始化连接数据库 
	 */
	public SqlOperation() {
	}

	/**
	 * 连接数据库
	 * @param account 登录账户
	 * @param password 登录密码
	 */
	public boolean getConnection(String account,String password) {
		try {

			/* 利用反射加载驱动 */
			Class.forName(sqlClass);

			/* 登录数据库 */
			con = DriverManager.getConnection(sqlConnection, account, password);

			/* 获取语句执行对象 */
			sta = con.createStatement();

		} catch (Exception e) {
			//e.printStackTrace();
			/* 登录连接失败 */
			return false;

		}

		try {
			/* 执行sql语句，创建entities表 */
			sta.executeUpdate("create table entities(" + "name VARCHAR(20)," /* 拍品名字 */
					+ "type VARCHAR(20)," /* 拍品属性 */
					+ "seller VARCHAR(20),"/* 出售人 */
					+ "priceStart INT,"/* 拍品起价 */
					+ "priceMin INT," /* 拍品一次最低喊价 */
					+ "priceFinal INT," /* 最终拍卖价格 */
					+ "buyer VARCHAR(20)," /* 最终拍卖人 */
					+ "info VARCHAR(100)" /* 拍卖品介绍 */
					+ ")");
		} catch (SQLException e) {

			/* 表已经存在 */
			// e.printStackTrace();
			System.out.println("entities表已经存在");
		}
		try {
			/* 执行sql语句，创建persons表 */
			sta.executeUpdate("create table persons(" 
					+ "name VARCHAR(20)," /* 名字 */
					+ "type VARCHAR(20)," /* 类型属性 */
					+ "telephone VARCHAR(20),"/* 联系方式 */
					+ "email VARCHAR(20),"/* 邮件地址 */
					+ "address VARCHAR(20)," /* 住址 */
					+ "account VARCHAR(20)," /* 账号 */
					+ "password VARCHAR(20)" /* 密码 */
					+ ")");
		} catch (SQLException e) {

			/* 表已经存在 */
			// e.printStackTrace();
			System.out.println("persons表已经存在");
		}
		
		try {
			/* 执行sql语句，创建time表 */
			sta.executeUpdate("create table time(" 
					+ "name VARCHAR(20)," /* 名字 */
					+ "nowprice VARCHAR(20)," /* 当前报价 */
					+ "deadtime VARCHAR(20)" /* 截止时间 */
					+ ")");
		} catch (SQLException e) {

			/* 表已经存在 */
			// e.printStackTrace();
			System.out.println("time表已经存在");
		}
		return true;
		
	}

	/**
	 * 添加一个拍卖品时间
	 * @param time 拍卖品时间
	 */
	public void addTime(time time) {
		try {
			sta.executeUpdate("INSERT INTO time "
					+ "VALUES (" + 
					"'" + time.getName() + "',"+
					"'" + time.getPriceNow() + "',"+
					"'" + time.getDeadTimeString()+ "'"+
					")");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改一个拍卖品时间
	 * @param time 拍卖品时间
	 */
	public void setTime(time time) {
		try {
			sta.executeUpdate("UPDATE time SET "
					+"nowprice="+"'" + time.getPriceNow() + "',"
					+"deadtime="+"'" +time.getDeadTimeString()+ "' "+
					"WHERE "+ "name=" +"'" + time.getName() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除一个拍卖品时间（通过拍卖品名字）
	 * @param name 拍卖品名字
	 */
	public void deleteTime(String name) {
		try {
			sta.executeUpdate("DELETE FROM time WHERE name = '" + name + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 通过拍卖品名字准确查询 拍卖品截止时间
	 * @param name 拍卖品名字
	 * @return 拍卖品时间
	 */
	public time getTimeFromName(String name) {
		
		/* 结果返回集 */
		ResultSet rs = null;
		
		try {

			/* 执行查询语句（准确匹配） */
			rs = sta.executeQuery("SELECT * FROM time WHERE name = '" + name+ "'");
			
			/* 将符合条件的拍卖品时间返回 */
			rs.next();
				return new time(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3)
						);
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("不存在拍卖品");
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	/**
	 * 添加一个拍卖品
	 * @param en 拍卖品
	 */
	public void addEntity(Entity en) {
		try {
			sta.executeUpdate("INSERT INTO entities(name,type,seller,priceStart,priceMin,priceFinal,buyer,info) "
					+ "VALUES (" + 
					"'" + en.getName() + "',"+
					"'" + en.getType() + "',"+
					"'" + en.getSeller().getAccount()+ "',"+
					"" + en.getPriceStart() + "," +
					en.getPriceMin() + "," +
					en.getPriceFinal() + ",'"+
					en.getBuyer().getAccount() + 
					"','" + en.getInfo() + 
					"')");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改一个拍卖品(通过拍卖品名字修改)
	 * @param en 拍卖品
	 */
	public void setEntity(Entity en) {
		try {
			sta.executeUpdate("UPDATE entities SET "
					+ "type=" + "'" + en.getType() + "'," 
					+ "seller=" + "'" + en.getSeller().getAccount() + "'," 
					+ "priceStart="  + en.getPriceStart() + "," 
					+ "priceMin=" +en.getPriceMin() + "," 
					+ "priceFinal=" +en.getPriceFinal() + ","
					+ "buyer='" +en.getBuyer().getAccount() +"'," 
					+ "info=" + "'" + en.getInfo()+ "'" 
					+ " WHERE name = " + "'"
					+ en.getName() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 删除一个拍卖品（通过拍卖品名字）
	 * @param name 拍卖品名字
	 */
	public void deleteEntity(String name) {
		try {
			sta.executeUpdate("DELETE FROM entities WHERE name = '" + name + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 通过拍卖品类型查询
	 * @param Type 拍卖品类型 0为古玩 1为汽车 2为房产
	 * @return 拍卖品数组
	 */
	public ArrayList<Entity> getEntityFromType(int type) {
		
		/* 结果返回集 */
		ResultSet rs = null;
		
		/* 返回拍卖品 */
		ArrayList<Entity> ens=new ArrayList<>();
		
		try {

			/* 执行查询语句 */
			rs = sta.executeQuery("SELECT * FROM entities WHERE type ='" + TypeEntity.values()[type]+ "'");
			
			/* 遍历结果集，将符合条件的拍卖品加入数组 */
			while(rs.next()){
				ens.add(new Entity(
						rs.getString(1), 
						Enum.valueOf(TypeEntity.class, rs.getString(2)), 
						getPersonFromAccount(rs.getString(3),con.createStatement()), 
						rs.getInt(4), 
						rs.getInt(5),
						rs.getInt(6), 
						getPersonFromAccount(rs.getString(7),con.createStatement()),
						rs.getString(8)
						));
			}
			return ens;

		} catch (SQLException e) {
			System.out.println("不存在拍卖品");
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
			}
		}
		return null;
	}
	
	/**
	 * 通过拍卖品名字准确查询
	 * @param name 拍卖品名字
	 * @return 拍卖品
	 */
	public Entity getEntityFromName(String name) {
		
		/* 结果返回集 */
		ResultSet rs = null;
		
		try {

			/* 执行查询语句（准确匹配） */
			rs = sta.executeQuery("SELECT * FROM entities WHERE name = '" + name+ "'");
			
			/* 将符合条件的拍卖品返回 */
			rs.next();
				return new Entity(
						rs.getString(1), 
						Enum.valueOf(TypeEntity.class, rs.getString(2)), 
						getPersonFromAccount(rs.getString(3),con.createStatement()), 
						rs.getInt(4), 
						rs.getInt(5),
						rs.getInt(6), 
						getPersonFromAccount(rs.getString(7),con.createStatement()),
						rs.getString(8)
						);
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("不存在拍卖品");
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	/**
	 * 通过拍卖品名字模糊查询
	 * @param name 拍卖品名字
	 * @return 拍卖品数组
	 */
	public ArrayList<Entity> getEntityFromNameFuzzy(String name) {
		
		/* 结果返回集 */
		ResultSet rs = null;
		
		/* 返回拍卖品 */
		ArrayList<Entity> ens=new ArrayList<>();
		
		try {

			/* 执行查询语句（模糊匹配） */
			rs = sta.executeQuery("SELECT * FROM entities WHERE name LIKE '%" + name+ "%'");
			
			
			/* 遍历结果集，将符合条件的拍卖品加入数组 */
			while(rs.next()){
				ens.add(new Entity(
						rs.getString(1), 
						Enum.valueOf(TypeEntity.class, rs.getString(2)), 
						getPersonFromAccount(rs.getString(3),con.createStatement()), 
						rs.getInt(4), 
						rs.getInt(5),
						rs.getInt(6), 
						getPersonFromAccount(rs.getString(7),con.createStatement()),
						rs.getString(8)
						));
			}
			return ens;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("不存在拍卖品");
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 通过拍卖品名字模糊查询 某人的拍卖品
	 * @param name 拍卖品名字
	 * @return 拍卖品数组
	 */
	public ArrayList<Entity> getEntityFromNameFuzzyFromAccount(String name,String account) {
		
		/* 结果返回集 */
		ResultSet rs = null;
		
		/* 返回拍卖品 */
		ArrayList<Entity> ens=new ArrayList<>();
		
		try {

			/* 执行查询语句（模糊匹配） */
			rs = sta.executeQuery("SELECT * FROM entities WHERE name LIKE '%" + name+ "%' AND seller= '"+account+"'");
			
			
			/* 遍历结果集，将符合条件的拍卖品加入数组 */
			while(rs.next()){
				ens.add(new Entity(
						rs.getString(1), 
						Enum.valueOf(TypeEntity.class, rs.getString(2)), 
						getPersonFromAccount(rs.getString(3),con.createStatement()), 
						rs.getInt(4), 
						rs.getInt(5),
						rs.getInt(6), 
						getPersonFromAccount(rs.getString(7),con.createStatement()),
						rs.getString(8)
						));
			}
			return ens;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("不存在拍卖品");
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 添加一个人
	 * @param ps 人
	 */
	public void addPerson(Person ps) {
		try {
			
			/* 执行数据库更新语句 */
			sta.executeUpdate("INSERT INTO persons "
					+ "VALUES (" + 
					"'" + ps.getName() + "',"+
					"'" + ps.getType() + "',"+
					"'" + ps.getTelephone()+ "',"+
					"'" + ps.getEmail() + "'," +
					"'" + ps.getAddress() + "'," +
					"'" + ps.getAccount() + "'," +
					"'" + ps.getPassword() + "'"+
					")");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改一个人员信息(通过账号修改)
	 * @param ps 人员
	 */
	public void setPerson(Person ps) {
		try {
			sta.executeUpdate("UPDATE persons SET "
					+ "name=" + "'" + ps.getName() + "'," 
					+ "type=" + "'" + ps.getType() + "'," 
					+ "telephone='"  + ps.getTelephone() + "'," 
					+ "email='" +ps.getEmail() + "'," 
					+ "address='" +ps.getAddress() + "',"
					+ "password='" +ps.getPassword() +"'" 
					+ " WHERE account = '"+ps.getAccount() + "'"
					);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除一个人员（通过账号）
	 * @param account 账号
	 */
	public void deletePerson(String account) {
		try {
			sta.executeUpdate("DELETE FROM persons WHERE account = '" + account + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过人账号准确查询
	 * @param account 人账号
	 * @return 拍卖/竞拍人
	 */
	public Person getPersonFromAccount(String account,Statement sta) {
		
		/* 结果返回集 */
		ResultSet rs = null;
		try {

			rs = sta.executeQuery("SELECT * FROM persons WHERE account ='" + account+ "'");
			rs.next();
			
			return new Person(
					rs.getString(1), 
					Enum.valueOf(TypePerson.class, rs.getString(2)), 
					rs.getString(3), 
					rs.getString(4), 
					rs.getString(5),
					rs.getString(6), 
					rs.getString(7));
		} catch (SQLException e) {
			//System.out.println("不存在拍卖/竞拍人");
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
			}
		}
		return null;
	}
	
	/**
	 * 通过人名字准确查询
	 * @param name 人名字
	 * @return 拍卖/竞拍人
	 */
	public Person getPersonFromName(String name) {
		
		/* 结果返回集 */
		ResultSet rs = null;
		try {

			rs = sta.executeQuery("SELECT * FROM persons WHERE name ='" + name+ "'");
			rs.next();
			
			return new Person(
					rs.getString(1), 
					Enum.valueOf(TypePerson.class, rs.getString(2)), 
					rs.getString(3), 
					rs.getString(4), 
					rs.getString(5),
					rs.getString(6), 
					rs.getString(7));
		} catch (SQLException e) {
			//System.out.println("不存在拍卖/竞拍人");
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 通过人名字模糊查询
	 * @param name 人名字
	 * @return 拍卖/竞拍人数组
	 */
	public ArrayList<Person> getPersonFromNameFuzzy(String name) {
		
		/* 结果返回集 */
		ResultSet rs = null;
		
		/* 返回拍卖/竞拍人数组 */
		ArrayList<Person> ps=new ArrayList<>();
		
		try {
			/* 执行查询语句（模糊匹配） */
			rs = sta.executeQuery("SELECT * FROM persons WHERE name LIKE '%" + name+ "%'");
			
			/* 遍历结果集，将符合条件的竞拍人加入数组 */
			while(rs.next()){
				ps.add(new Person(
						rs.getString(1), 
						Enum.valueOf(TypePerson.class, rs.getString(2)), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5),
						rs.getString(6), 
						rs.getString(7)));
			}
			return ps;
			

		} catch (SQLException e) {
			System.out.println("不存在竞拍人");
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 模糊查询
	 * @param name 人名字或者账号
	 * @return 拍卖/竞拍人数组
	 */
	public ArrayList<Person> getPersonFromFuzzy(String name) {
		
		/* 结果返回集 */
		ResultSet rs = null;
		
		/* 返回拍卖/竞拍人数组 */
		ArrayList<Person> ps=new ArrayList<>();
		
		try {

			/* 执行查询语句（模糊匹配） */
			rs = sta.executeQuery("SELECT * FROM persons WHERE name LIKE '%" + name+ "%' OR account LIKE '%" + name+ "%'");
			
			/* 遍历结果集，将符合条件的竞拍人加入数组 */
			while(rs.next()){
				ps.add(new Person(
						rs.getString(1), 
						Enum.valueOf(TypePerson.class, rs.getString(2)), 
						rs.getString(3), 
						rs.getString(4), 
						rs.getString(5),
						rs.getString(6), 
						rs.getString(7)));
			}
			return ps;
			

		} catch (SQLException e) {
			System.out.println("不存在竞拍人");
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 
	 * @return 返回全部拍卖品
	 */
	public ResultSet getEntityAll() {
		
		/* 结果返回集 */
		ResultSet rs = null;
		try {

			rs = sta.executeQuery("select * from entities");
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
