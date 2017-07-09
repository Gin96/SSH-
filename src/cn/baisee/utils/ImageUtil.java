package cn.baisee.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpSession;

/**
 * ͼƬ������
 * @author Administrator
 *
 */
public class ImageUtil {

	/**
	 * ������̬��֤��
	 * @param session ���session����
	 * @param sessionKey ���session����key
	 * @return ����ͼƬ����
	 */
	public static BufferedImage createCode(HttpSession session,String sessionKey){
		//���ڴ��д���ͼ��  
	    int width = 60;  
	    int height = 20;  
	    BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);  
	    //����ͼ��  
	    Graphics g = image.getGraphics();  
	    //�����������  
	    Random random = new Random();  
	    //���ñ���ɫ  
	    g.setColor(Color.GRAY);  
	    g.fillRect(0,0,width,height);  
	    //��������  
	    g.setFont(new Font("����",Font.PLAIN,18));  
	    //���������֤��,4λ����  
	    String sRand = "";  
	    for(int i = 0; i < 4; i++){  
	        String rand = String.valueOf(random.nextInt(10));  
	        sRand  += rand;  
	        //����֤����ʾ��ͼ����  
	        g.setColor(new Color(20 + random.nextInt(110),20 + random.nextInt(110),20 + random.nextInt(110)));  
	        g.drawString(rand,13*i+6,16);  
	    }  
	    session.setAttribute(sessionKey,sRand);  
	    //ͼ����Ч  
	    g.dispose();
	    return image;
	}
}
