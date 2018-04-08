package tools;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * ������
 */
public final class tools {

	/**
	 * ��������ͼƬ��С��ƽ�̣�
	 * 
	 * @param img
	 *            ͼƬ
	 * @param width
	 *            ���
	 * @param height
	 *            �߶�
	 * @return
	 */
	public static Image setImageSize(Image img, int width, int height) {

		// bufferImage���ƶ���
		Graphics gBuffer;

		// ����ͼƬ����
		BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		// ��ȡͼƬ���ƶ���
		gBuffer = bufferImage.getGraphics();

		// ��ԭͼƬ���Ƶ���ͼƬ
		gBuffer.drawImage(img, 0, 0, width, height, null);

		return bufferImage;
	}

	/**
	 * ���ð�ť���ǩλ�ã�����ͼƬƽ��
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
	 * ��Чѡ������״�任���� 
	 * ѡ���ʼλ�ô�С��Ŀ��λ�ô�С
	 * ����x��y����ȡ��߶ȱ仯ʸ�����Լ�ʱ�� 10����
	 * @param startX
	 * @param startY
	 * @param startWidth
	 * @param startHeight
	 * @param endX
	 * @param endY
	 * @param endWidth
	 * @param endHeight
	 * @param v �ٶ� ����/10����
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
