package superMary;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame implements KeyListener,Runnable{
	//�������б����ļ���
	private List<background> bg=new ArrayList<>(); 
	//�洢��ǰ�ı���
	private background bgUsing=new background();
	//����˫����
	private Image offScreanImage=null;
	//��������¶���
	private Mario mario=new Mario();
	//����һ���̶߳��󣬿�������µ��ƶ�
	private Thread thread=new Thread(this);
	
   public MyFrame()
   {
	   setVisible(true);
	   setSize(800,600);
	   setLocationRelativeTo(null);
	   setDefaultCloseOperation(EXIT_ON_CLOSE);
	   setResizable(false);
	   setTitle("��������");
	   addKeyListener(this);
	   StaticValue.init();
	   mario=new Mario(10,355);
	   for(int i=1;i<=3;i++)
	   {
		   bg.add(new background(i,i==3?true:false));
	   }
	   bgUsing=bg.get(2);
	   mario.setBackground(bgUsing);
	   //����ͼ��
	   repaint();
	   thread.start();
   }
   
   
   @Override
public void paint(Graphics g) {
	if(offScreanImage==null)
	{
		offScreanImage=createImage(800,600);
	}
	Graphics graphics=offScreanImage.getGraphics();
	graphics.fillRect(0,0,800,600);
	//���Ʊ���
	graphics.drawImage(bgUsing.getImage(),0,0,this);
	//�����ϰ���
	for(zhangai p:bgUsing.getAi())
	{
		graphics.drawImage(p.getShow(),p.getX(),p.getY(),this);
	}
	//���ƳǱ�
	graphics.drawImage(bgUsing.getCastle(), 620, 270, this);
	//��������
	graphics.drawImage(bgUsing.getQi(), 500, 220, this);
	//��������¶���
	graphics.drawImage(mario.getShow(), mario.getX(), mario.getY(), this);
	//��ͼ����Ƶ�������
	g.drawImage(offScreanImage,0,0,this);
}
   
   @Override
public void run() {
	while(true)
	{
		repaint();
		try {
			Thread.sleep(50);
			if(mario.getX()>=775)
			{
				bgUsing=bg.get(bgUsing.getSort());
				mario.setBackground(bgUsing);
				mario.setX(10);
				mario.setY(355);
			}
			if(mario.isOk())
			{
				JOptionPane.showMessageDialog(this,"��ϲͨ����");
				System.exit(0);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
   @Override
public void keyTyped(KeyEvent e) {
	
}
   @Override
public void keyReleased(KeyEvent e) {
	//����ֹͣ
	   if(e.getKeyCode()==65)
	   {
		   mario.leftStop();
	   }
	   //����ֹͣ
	   if(e.getKeyCode()==68)
	   {
		   mario.rightStop();
	   }
}
   @Override
public void keyPressed(KeyEvent e) {
	   //�����ƶ�
	if(e.getKeyCode()==65)
	{
		mario.leftMove();
	}
	//�����ƶ�
	if(e.getKeyCode()==68)
	{
		mario.rightMove();
	}
	//��Ծ
	if(e.getKeyCode()==32)
	{
		mario.jump();
	}
}
   
   
   public static void main(String[]args)
   {
	   MyFrame frame=new MyFrame();
   }
}
