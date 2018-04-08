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
 * ���ݿ������
 */
public class SqlOperation implements resource {

	/* ���ݿ����Ӷ��� */
	public Connection con;

	/* ���ݿ���䷢�Ͷ��� */
	private Statement sta;

	/** 
	 * ��ʼ���������ݿ� 
	 */
	public SqlOperation() {
	}

	/**
	 * �������ݿ�
	 * @param account ��¼�˻�
	 * @param password ��¼����
	 */
	public boolean getConnection(String account,String password) {
		try {

			/* ���÷���������� */
			Class.forName(sqlClass);

			/* ��¼���ݿ� */
			con = DriverManager.getConnection(sqlConnection, account, password);

			/* ��ȡ���ִ�ж��� */
			sta = con.createStatement();

		} catch (Exception e) {
			//e.printStackTrace();
			/* ��¼����ʧ�� */
			return false;

		}

		try {
			/* ִ��sql��䣬����entities�� */
			sta.executeUpdate("create table entities(" + "name VARCHAR(20)," /* ��Ʒ���� */
					+ "type VARCHAR(20)," /* ��Ʒ���� */
					+ "seller VARCHAR(20),"/* ������ */
					+ "priceStart INT,"/* ��Ʒ��� */
					+ "priceMin INT," /* ��Ʒһ����ͺ��� */
					+ "priceFinal INT," /* ���������۸� */
					+ "buyer VARCHAR(20)," /* ���������� */
					+ "info VARCHAR(100)" /* ����Ʒ���� */
					+ ")");
		} catch (SQLException e) {

			/* ���Ѿ����� */
			// e.printStackTrace();
			System.out.println("entities���Ѿ�����");
		}
		try {
			/* ִ��sql��䣬����persons�� */
			sta.executeUpdate("create table persons(" 
					+ "name VARCHAR(20)," /* ���� */
					+ "type VARCHAR(20)," /* �������� */
					+ "telephone VARCHAR(20),"/* ��ϵ��ʽ */
					+ "email VARCHAR(20),"/* �ʼ���ַ */
					+ "address VARCHAR(20)," /* סַ */
					+ "account VARCHAR(20)," /* �˺� */
					+ "password VARCHAR(20)" /* ���� */
					+ ")");
		} catch (SQLException e) {

			/* ���Ѿ����� */
			// e.printStackTrace();
			System.out.println("persons���Ѿ�����");
		}
		
		try {
			/* ִ��sql��䣬����time�� */
			sta.executeUpdate("create table time(" 
					+ "name VARCHAR(20)," /* ���� */
					+ "nowprice VARCHAR(20)," /* ��ǰ���� */
					+ "deadtime VARCHAR(20)" /* ��ֹʱ�� */
					+ ")");
		} catch (SQLException e) {

			/* ���Ѿ����� */
			// e.printStackTrace();
			System.out.println("time���Ѿ�����");
		}
		return true;
		
	}

	/**
	 * ���һ������Ʒʱ��
	 * @param time ����Ʒʱ��
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
	 * �޸�һ������Ʒʱ��
	 * @param time ����Ʒʱ��
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
	 * ɾ��һ������Ʒʱ�䣨ͨ������Ʒ���֣�
	 * @param name ����Ʒ����
	 */
	public void deleteTime(String name) {
		try {
			sta.executeUpdate("DELETE FROM time WHERE name = '" + name + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * ͨ������Ʒ����׼ȷ��ѯ ����Ʒ��ֹʱ��
	 * @param name ����Ʒ����
	 * @return ����Ʒʱ��
	 */
	public time getTimeFromName(String name) {
		
		/* ������ؼ� */
		ResultSet rs = null;
		
		try {

			/* ִ�в�ѯ��䣨׼ȷƥ�䣩 */
			rs = sta.executeQuery("SELECT * FROM time WHERE name = '" + name+ "'");
			
			/* ����������������Ʒʱ�䷵�� */
			rs.next();
				return new time(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3)
						);
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("����������Ʒ");
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
	 * ���һ������Ʒ
	 * @param en ����Ʒ
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
	 * �޸�һ������Ʒ(ͨ������Ʒ�����޸�)
	 * @param en ����Ʒ
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
	 * ɾ��һ������Ʒ��ͨ������Ʒ���֣�
	 * @param name ����Ʒ����
	 */
	public void deleteEntity(String name) {
		try {
			sta.executeUpdate("DELETE FROM entities WHERE name = '" + name + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * ͨ������Ʒ���Ͳ�ѯ
	 * @param Type ����Ʒ���� 0Ϊ���� 1Ϊ���� 2Ϊ����
	 * @return ����Ʒ����
	 */
	public ArrayList<Entity> getEntityFromType(int type) {
		
		/* ������ؼ� */
		ResultSet rs = null;
		
		/* ��������Ʒ */
		ArrayList<Entity> ens=new ArrayList<>();
		
		try {

			/* ִ�в�ѯ��� */
			rs = sta.executeQuery("SELECT * FROM entities WHERE type ='" + TypeEntity.values()[type]+ "'");
			
			/* ���������������������������Ʒ�������� */
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
			System.out.println("����������Ʒ");
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
			}
		}
		return null;
	}
	
	/**
	 * ͨ������Ʒ����׼ȷ��ѯ
	 * @param name ����Ʒ����
	 * @return ����Ʒ
	 */
	public Entity getEntityFromName(String name) {
		
		/* ������ؼ� */
		ResultSet rs = null;
		
		try {

			/* ִ�в�ѯ��䣨׼ȷƥ�䣩 */
			rs = sta.executeQuery("SELECT * FROM entities WHERE name = '" + name+ "'");
			
			/* ����������������Ʒ���� */
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
			System.out.println("����������Ʒ");
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
	 * ͨ������Ʒ����ģ����ѯ
	 * @param name ����Ʒ����
	 * @return ����Ʒ����
	 */
	public ArrayList<Entity> getEntityFromNameFuzzy(String name) {
		
		/* ������ؼ� */
		ResultSet rs = null;
		
		/* ��������Ʒ */
		ArrayList<Entity> ens=new ArrayList<>();
		
		try {

			/* ִ�в�ѯ��䣨ģ��ƥ�䣩 */
			rs = sta.executeQuery("SELECT * FROM entities WHERE name LIKE '%" + name+ "%'");
			
			
			/* ���������������������������Ʒ�������� */
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
			System.out.println("����������Ʒ");
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
	 * ͨ������Ʒ����ģ����ѯ ĳ�˵�����Ʒ
	 * @param name ����Ʒ����
	 * @return ����Ʒ����
	 */
	public ArrayList<Entity> getEntityFromNameFuzzyFromAccount(String name,String account) {
		
		/* ������ؼ� */
		ResultSet rs = null;
		
		/* ��������Ʒ */
		ArrayList<Entity> ens=new ArrayList<>();
		
		try {

			/* ִ�в�ѯ��䣨ģ��ƥ�䣩 */
			rs = sta.executeQuery("SELECT * FROM entities WHERE name LIKE '%" + name+ "%' AND seller= '"+account+"'");
			
			
			/* ���������������������������Ʒ�������� */
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
			System.out.println("����������Ʒ");
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
	 * ���һ����
	 * @param ps ��
	 */
	public void addPerson(Person ps) {
		try {
			
			/* ִ�����ݿ������� */
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
	 * �޸�һ����Ա��Ϣ(ͨ���˺��޸�)
	 * @param ps ��Ա
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
	 * ɾ��һ����Ա��ͨ���˺ţ�
	 * @param account �˺�
	 */
	public void deletePerson(String account) {
		try {
			sta.executeUpdate("DELETE FROM persons WHERE account = '" + account + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ͨ�����˺�׼ȷ��ѯ
	 * @param account ���˺�
	 * @return ����/������
	 */
	public Person getPersonFromAccount(String account,Statement sta) {
		
		/* ������ؼ� */
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
			//System.out.println("����������/������");
		}finally{
			try {
				rs.last();
			} catch (SQLException e) {
			}
		}
		return null;
	}
	
	/**
	 * ͨ��������׼ȷ��ѯ
	 * @param name ������
	 * @return ����/������
	 */
	public Person getPersonFromName(String name) {
		
		/* ������ؼ� */
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
			//System.out.println("����������/������");
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
	 * ͨ��������ģ����ѯ
	 * @param name ������
	 * @return ����/����������
	 */
	public ArrayList<Person> getPersonFromNameFuzzy(String name) {
		
		/* ������ؼ� */
		ResultSet rs = null;
		
		/* ��������/���������� */
		ArrayList<Person> ps=new ArrayList<>();
		
		try {
			/* ִ�в�ѯ��䣨ģ��ƥ�䣩 */
			rs = sta.executeQuery("SELECT * FROM persons WHERE name LIKE '%" + name+ "%'");
			
			/* ����������������������ľ����˼������� */
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
			System.out.println("�����ھ�����");
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
	 * ģ����ѯ
	 * @param name �����ֻ����˺�
	 * @return ����/����������
	 */
	public ArrayList<Person> getPersonFromFuzzy(String name) {
		
		/* ������ؼ� */
		ResultSet rs = null;
		
		/* ��������/���������� */
		ArrayList<Person> ps=new ArrayList<>();
		
		try {

			/* ִ�в�ѯ��䣨ģ��ƥ�䣩 */
			rs = sta.executeQuery("SELECT * FROM persons WHERE name LIKE '%" + name+ "%' OR account LIKE '%" + name+ "%'");
			
			/* ����������������������ľ����˼������� */
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
			System.out.println("�����ھ�����");
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
	 * @return ����ȫ������Ʒ
	 */
	public ResultSet getEntityAll() {
		
		/* ������ؼ� */
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
