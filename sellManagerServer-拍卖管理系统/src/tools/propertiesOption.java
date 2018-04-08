package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** �����ļ����� */
public class propertiesOption {
	
	static Properties pps = new Properties();
	
	/** ���ݿ����� */
	public static String sqlClass;
	
	/** ���ݿ����� */
	public static String sqlConnection;

	/** ���ݿ��û��� */
	public static String sqlUser;

	/** ���ݿ����� */
	public static String sqlPassword;
	
	static{
		try {
			/* ���������ļ� */
			pps.load(new FileInputStream(".\\config\\system.properties"));
			
			/* �������� */
			sqlClass=pps.getProperty("sqlClass");
			sqlConnection=pps.getProperty("sqlConnection");
			sqlUser=pps.getProperty("sqlUser");
			sqlPassword=pps.getProperty("sqlPassword");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
