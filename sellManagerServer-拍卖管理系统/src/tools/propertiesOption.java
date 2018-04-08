package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** 配置文件读入 */
public class propertiesOption {
	
	static Properties pps = new Properties();
	
	/** 数据库驱动 */
	public static String sqlClass;
	
	/** 数据库连接 */
	public static String sqlConnection;

	/** 数据库用户名 */
	public static String sqlUser;

	/** 数据库密码 */
	public static String sqlPassword;
	
	static{
		try {
			/* 载入配置文件 */
			pps.load(new FileInputStream(".\\config\\system.properties"));
			
			/* 读入配置 */
			sqlClass=pps.getProperty("sqlClass");
			sqlConnection=pps.getProperty("sqlConnection");
			sqlUser=pps.getProperty("sqlUser");
			sqlPassword=pps.getProperty("sqlPassword");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
