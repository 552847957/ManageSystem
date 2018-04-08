package resource;

import sql.sqlOperation;

public interface resource {
	public static final sqlOperation sqlOp = new sqlOperation();
	// 数据库驱动
	public static final String sqlClass = "org.apache.derby.jdbc.EmbeddedDriver";
	//数据库连接
	public static final String sqlConnection="jdbc:derby:Owner;create=false";
}
