package tools;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 工具类
 */
public final class tools {

	/**
	 * 重新设置图片大小（平铺）
	 * 
	 * @param img
	 *            图片
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @return
	 */
	public static Image setImageSize(Image img, int width, int height) {

		// bufferImage绘制对象
		Graphics gBuffer;

		// 缓冲图片对象
		BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// 获取图片绘制对象
		gBuffer = bufferImage.getGraphics();

		// 将原图片绘制到新图片
		gBuffer.drawImage(img, 0, 0, width, height, null);

		return bufferImage;
	}

	/**
	 * 设置按钮或标签位置，并且图片平铺
	 * @param jb JButton
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void setIcon(Object obj, int x, int y, int width, int height) {
		if(obj instanceof JButton){
			JButton jb=(JButton)obj;
			jb.setBounds(x, y, width, height);
			jb.setIcon(new ImageIcon(
					tools.setImageSize(((ImageIcon) jb.getIcon()).getImage(), jb.getWidth(), jb.getHeight())));
		}else if(obj instanceof JLabel){
			JLabel jb=(JLabel)obj;
			jb.setBounds(x, y, width, height);
			jb.setIcon(new ImageIcon(
					tools.setImageSize(((ImageIcon) jb.getIcon()).getImage(), jb.getWidth(), jb.getHeight())));
		}
		
	}

	/**
	 * 特效选择框的形状变换计算 
	 * 选框初始位置大小和目的位置大小
	 * 计算x、y、宽度、高度变化矢量、以及时间 10毫秒
	 * @param startX
	 * @param startY
	 * @param startWidth
	 * @param startHeight
	 * @param endX
	 * @param endY
	 * @param endWidth
	 * @param endHeight
	 * @param v 速度 像素/10毫秒
	 * @return
	 */
	public static int[] calculate(int startX, int startY, int startWidth, int startHeight, int endX, int endY,int endWidth,
			 int endHeight,int v) {
		double distance=Math.sqrt(Math.pow((endX-startX), 2)+Math.pow((endY-startY), 2));
		int t=Math.abs((int)distance/v);
		//System.out.println(t);
		double cos=Math.abs(endX-startX)/distance;
		double sin=Math.abs(endY-startY)/distance;
		int vX=(int)(cos*v*((endX-startX)>0?1:-1));
		int vY=(int)(sin*v*((endY-startY)>0?1:-1));
		
		int vW=(int)((endWidth-startWidth)/t);
		int vH=(int)((endHeight-startHeight)/t);
		
		return new int[]{vX,vY,vW,vH,t};
	}
}
