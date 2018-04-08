package tools;

import javax.swing.ImageIcon;

import com.sell.sql.SqlOperation;

/**
 * ��Դ������
 */
public interface resource {

	/* ���ݿ�������� */
	public static final SqlOperation sqlOp=new SqlOperation();
	
	/* ���ݿ����� */
	public static final String sqlClass = propertiesOption.sqlClass;

	/* ���ݿ����� */
	public static final String sqlConnection = propertiesOption.sqlConnection;

	/* ���ݿ��û��� */
	public static final String sqlUser = propertiesOption.sqlUser;

	/* ���ݿ����� */
	public static final String sqlPassword = propertiesOption.sqlPassword;
	
	/* ����ͼƬ��ԴĿ¼ */
	public static final String dirSrcImg = ".\\srcImg\\";
	public static final ImageIcon imageAntique = new ImageIcon(dirSrcImg + "����.png");
	public static final ImageIcon imageAntiqueOn = new ImageIcon(dirSrcImg + "����On.png");
	public static final ImageIcon imageCar = new ImageIcon(dirSrcImg + "����.png");
	public static final ImageIcon imageCarOn = new ImageIcon(dirSrcImg + "����On.png");
	public static final ImageIcon imageHouse = new ImageIcon(dirSrcImg + "����.png");
	public static final ImageIcon imageHouseOn = new ImageIcon(dirSrcImg + "����On.png");
	public static final ImageIcon imageSelect = new ImageIcon(dirSrcImg + "ѡ��.png");
	public static final ImageIcon imageMarket = new ImageIcon(dirSrcImg + "�г�.png");
	public static final ImageIcon imageMarketOn = new ImageIcon(dirSrcImg + "�г�On.png");
	public static final ImageIcon imageUser = new ImageIcon(dirSrcImg + "�û�.png");
	public static final ImageIcon imageUserOn = new ImageIcon(dirSrcImg + "�û�On.png");
	public static final ImageIcon imageExplorer = new ImageIcon(dirSrcImg + "����.png");
	public static final ImageIcon imageExplorerOn = new ImageIcon(dirSrcImg + "����On.png");
	public static final ImageIcon imageAdd = new ImageIcon(dirSrcImg + "���.png");
	public static final ImageIcon imageAddOn = new ImageIcon(dirSrcImg + "���On.png");
	
	public static final ImageIcon imageDel = new ImageIcon(dirSrcImg + "ɾ��.png");
	public static final ImageIcon imageDelOn = new ImageIcon(dirSrcImg + "ɾ��On.png");
	public static final ImageIcon imageModify = new ImageIcon(dirSrcImg + "�޸�.png");
	public static final ImageIcon imageModifyOn = new ImageIcon(dirSrcImg + "�޸�On.png");
	public static final ImageIcon imageYes = new ImageIcon(dirSrcImg + "yes.png");
	public static final ImageIcon imageNo = new ImageIcon(dirSrcImg + "no.png");
}
