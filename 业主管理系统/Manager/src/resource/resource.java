package resource;

import sql.sqlOperation;

public interface resource {
	public static final sqlOperation sqlOp = new sqlOperation();
	// ���ݿ�����
	public static final String sqlClass = "org.apache.derby.jdbc.EmbeddedDriver";
	//���ݿ�����
	public static final String sqlConnection="jdbc:derby:Owner;create=false";
}
