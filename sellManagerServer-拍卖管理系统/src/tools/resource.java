package tools;

import javax.swing.ImageIcon;

import com.sell.sql.SqlOperation;

/**
 * 资源加载类
 */
public interface resource {

	/* 数据库操作对象 */
	public static final SqlOperation sqlOp=new SqlOperation();
	
	/* 数据库驱动 */
	public static final String sqlClass = propertiesOption.sqlClass;

	/* 数据库连接 */
	public static final String sqlConnection = propertiesOption.sqlConnection;

	/* 数据库用户名 */
	public static final String sqlUser = propertiesOption.sqlUser;

	/* 数据库密码 */
	public static final String sqlPassword = propertiesOption.sqlPassword;
	
	/* 程序图片资源目录 */
	public static final String dirSrcImg = ".\\srcImg\\";
	public static final ImageIcon imageAntique = new ImageIcon(dirSrcImg + "古玩.png");
	public static final ImageIcon imageAntiqueOn = new ImageIcon(dirSrcImg + "古玩On.png");
	public static final ImageIcon imageCar = new ImageIcon(dirSrcImg + "汽车.png");
	public static final ImageIcon imageCarOn = new ImageIcon(dirSrcImg + "汽车On.png");
	public static final ImageIcon imageHouse = new ImageIcon(dirSrcImg + "房产.png");
	public static final ImageIcon imageHouseOn = new ImageIcon(dirSrcImg + "房产On.png");
	public static final ImageIcon imageSelect = new ImageIcon(dirSrcImg + "选框.png");
	public static final ImageIcon imageMarket = new ImageIcon(dirSrcImg + "市场.png");
	public static final ImageIcon imageMarketOn = new ImageIcon(dirSrcImg + "市场On.png");
	public static final ImageIcon imageUser = new ImageIcon(dirSrcImg + "用户.png");
	public static final ImageIcon imageUserOn = new ImageIcon(dirSrcImg + "用户On.png");
	public static final ImageIcon imageExplorer = new ImageIcon(dirSrcImg + "搜索.png");
	public static final ImageIcon imageExplorerOn = new ImageIcon(dirSrcImg + "搜索On.png");
	public static final ImageIcon imageAdd = new ImageIcon(dirSrcImg + "添加.png");
	public static final ImageIcon imageAddOn = new ImageIcon(dirSrcImg + "添加On.png");
	
	public static final ImageIcon imageDel = new ImageIcon(dirSrcImg + "删除.png");
	public static final ImageIcon imageDelOn = new ImageIcon(dirSrcImg + "删除On.png");
	public static final ImageIcon imageModify = new ImageIcon(dirSrcImg + "修改.png");
	public static final ImageIcon imageModifyOn = new ImageIcon(dirSrcImg + "修改On.png");
	public static final ImageIcon imageYes = new ImageIcon(dirSrcImg + "yes.png");
	public static final ImageIcon imageNo = new ImageIcon(dirSrcImg + "no.png");
}
