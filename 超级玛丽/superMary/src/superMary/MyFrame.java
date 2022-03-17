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
	//储存所有背景的集合
	private List<background> bg=new ArrayList<>(); 
	//存储当前的背景
	private background bgUsing=new background();
	//用于双缓存
	private Image offScreanImage=null;
	//生成马里奥对象
	private Mario mario=new Mario();
	//定义一个线程对象，孔子马里奥的移动
	private Thread thread=new Thread(this);
	
   public MyFrame()
   {
	   setVisible(true);
	   setSize(800,600);
	   setLocationRelativeTo(null);
	   setDefaultCloseOperation(EXIT_ON_CLOSE);
	   setResizable(false);
	   setTitle("超级玛丽");
	   addKeyListener(this);
	   StaticValue.init();
	   mario=new Mario(10,355);
	   for(int i=1;i<=3;i++)
	   {
		   bg.add(new background(i,i==3?true:false));
	   }
	   bgUsing=bg.get(2);
	   mario.setBackground(bgUsing);
	   //绘制图像
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
	//绘制背景
	graphics.drawImage(bgUsing.getImage(),0,0,this);
	//绘制障碍物
	for(zhangai p:bgUsing.getAi())
	{
		graphics.drawImage(p.getShow(),p.getX(),p.getY(),this);
	}
	//绘制城堡
	graphics.drawImage(bgUsing.getCastle(), 620, 270, this);
	//绘制旗子
	graphics.drawImage(bgUsing.getQi(), 500, 220, this);
	//绘制马里奥对象
	graphics.drawImage(mario.getShow(), mario.getX(), mario.getY(), this);
	//将图像绘制到窗口中
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
				JOptionPane.showMessageDialog(this,"恭喜通关了");
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
	//向左停止
	   if(e.getKeyCode()==65)
	   {
		   mario.leftStop();
	   }
	   //向右停止
	   if(e.getKeyCode()==68)
	   {
		   mario.rightStop();
	   }
}
   @Override
public void keyPressed(KeyEvent e) {
	   //向左移动
	if(e.getKeyCode()==65)
	{
		mario.leftMove();
	}
	//向右移动
	if(e.getKeyCode()==68)
	{
		mario.rightMove();
	}
	//跳跃
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
