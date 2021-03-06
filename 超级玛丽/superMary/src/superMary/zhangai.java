package superMary;

import java.awt.image.BufferedImage;

public class zhangai implements Runnable{
	//障碍物的坐标
    private int x;
    private int y;
    //用于定义障碍物类型
    private int type;
    //用于显示图像
    private BufferedImage show=null;
    //定义当前场景对象
    private background bg=null;
    
    Thread thread=new Thread(this);
    public zhangai(int x,int y,int type,background bg)
    {
    	this.x=x;
    	this.y=y;
    	this.type=type;
    	this.bg=bg;
    	show=StaticValue.obstruct.get(type);
    	//如果是旗子的话启动线程
    	if(type==8)
    	{
    	thread.start();
    	}
    }
    
    @Override
    public void run() {
     while(true)
     {
    	 if(this.bg.isReach())
    	 {
    		 if(this.y<374)
    		 {
    			 this.y+=5;
    		 }
    		 else
    		 {
    			 this.bg.setBase(true);
    		 }
    	 }
    	try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
    	
    }
    
    public int getY()
    {
    	return y;
    }
    public int getX()
    {
    	return x;
    }
    public int getType()
    {
    	return type;
    }
    public BufferedImage getShow()
    {
    	return show;
    }
}
