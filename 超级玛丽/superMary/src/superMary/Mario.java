package superMary;

import java.awt.image.BufferedImage;

public class Mario implements Runnable{
	//马里奥的坐标
   private int x;
   private int y;
   //用于表示当前状态
   private String status;
   //用于显示当前状态对应的图片
   private BufferedImage show=null;
   //定义一个background对象，用于获取障碍物对象
   private background backGround=new background();
   //用来实现马里奥的动作
   private Thread thread=null;
   //马里奥的移动速度
   private int xspeed;
   //马里奥的跳跃速度
   private int yspeed;
   //定义一个索引
   private int index;
   
   //表示马里奥上升时间
   private int upTime=0;
   //判断马里奥是否到达城堡门口
   private boolean isOk;
   public Mario()
   {
	   
   }
   public Mario(int x,int y)
   {
	   this.x=x;
	   this.y=y;
	   status="stand-right";
	   show=StaticValue.stand_right;
	   thread=new Thread(this);
	   thread.start();
	   
   }
   
   //马里奥跳跃
   public void jump()
   {
	   if(status.indexOf("jump")==-1)
	   {
		   if(status.indexOf("left")!=-1)
		   {
			   status="jump--left";
		   }
		   else
		   {
			   status="jump--right";
		   }
		   yspeed=-10;
		   upTime=7;
	   }
	   //判断马里奥是否碰到旗子
	   if(backGround.isReach())
	   {
		   yspeed=0;
	   }
   }
   //马里奥下落
   public void fall()
   {
	   if(status.indexOf("left")!=-1)
	   {
		   status="jump--left";
	   }
	   else
	   {
		   status="jump--right";
	   }
	   yspeed=10;
   }
   //马里奥向左移动
   public void leftMove() {
       //改变速度
       xspeed = -5;
       //判断马里奥是否碰到旗子
       if(backGround.isReach())
       {
    	   xspeed=0;
       }
       //判断马里奥是否处于空中
       if (status.indexOf("jump") != -1) {
           status = "jump--left";
       }else {
           status = "move--left";
       }
   }

   //马里奥向右移动
   public void rightMove() {
       xspeed = 5;
       //判断马里奥是否碰到旗子
       if(backGround.isReach())
       {
    	   xspeed=0;
       }
       if (status.indexOf("jump") != -1) {
           status = "jump--right";
       }else {
           status = "move--right";
       }
   }

   //马里奥向左停止
   public void leftStop() {
       xspeed = 0;
       if (status.indexOf("jump") != -1) {
           status = "jump--left";
       }else {
           status = "stop--left";
       }
   }

   //马里奥向右停止
   public void rightStop() {
       xspeed = 0;
       if (status.indexOf("jump") != -1) {
           status = "jump--right";
       }else {
           status = "stop--right";
       }
   }
   @Override
public void run() 
  {
	   while (true) {
		   //判断是否位于障碍物上
		   boolean knock=false;
		   //判断是否可以向左走
		   boolean canLeft=true;
		   //判断是否可以向右走
		   boolean canRight=true;
		   //判断马里奥是否到达旗杆位置
		   if(backGround.isFlag()&&this.x>=500)
		   {
			   this.backGround.setReach(true);
			   //判断旗子是否下落完成
			   if(this.backGround.isBase())
			   {
				   status="move--right";
				   if(x<690)
				   {
					   x+=5;
				   }
				   else
				   {
					   isOk=true;
					   y=375;
				   }
			   }
			   
			   {
				   if(y<385)
				   {
					   xspeed=0;
					   this.y+=5;
					   
					   status="jump--right";
				   }
				   if(y>385)
				   {
					   this.y=385;
					   status="stop--right";
				   }
			   }
		   }
		   else
		   {
		   //遍历当前场景中所有的障碍物
		   for(int i=0;i<backGround.getAi().size();i++)
		   {
			   zhangai o=backGround.getAi().get(i);
			   //判断马里奥是否位于障碍物上
			   if(o.getY()==this.y+25&&(o.getX()>this.x-30&&o.getX()<this.x+25))
			   {
				   knock=true;
			   }
			   //判断马里奥跳起来是否顶到砖块
			   if((o.getY()>=this.y-30&&o.getY()<=this.y-20)&&(o.getX()>this.x-30&&o.getX()<this.x+25))
			   {
				 if(o.getType()==0)
				 {
			       backGround.getAi().remove(o);		
				 }
				 upTime=0;
			   }
			   //判断是否可以往右走
			   if(o.getX()==this.x+25&&(o.getY()>this.y-30&&o.getY()<this.y+25))
			   {
				   canRight=false;
			   }
			   //判断是否可以向左走
			   if(o.getX()==this.x-30&&(o.getY()>this.y-30&&o.getY()<this.y+25))
			   {
				   canLeft=false;
			   }
			   
		   }
		   
		   
		  
		  
		   //马里奥的跳跃方法
		   if(knock&&upTime==0)
		   {
			   if(status.indexOf("left")!=-1)
			   {
				   if(xspeed!=0)
				   {
					   status="move--left";
				   }
				   else
				   {
					   status="stop--left";
				   }
			   }
			   else
			   {
				   if(xspeed!=0)
				   {
					   status="move--right";
				   }
				   else
				   {
					   status="stop--right";
				   }
			   }
		   }
		   else
		   {
			   if(upTime!=0)
			   {
				upTime--;   
			   }
			   else
			   {
				   fall();
			   }
			   y+=yspeed;
		   }
		   
		   }
		   
           if ((canLeft&&xspeed < 0) ||(canRight&& xspeed > 0)) {
               x += xspeed;
               //判断马里奥是否到了最左边
               if (x < 0) {
                   x = 0;
               }
           }
           //判断当前是否是移动状态
           if (status.contains("move")) {
               index = index == 0 ? 1 : 0;
           }
           //判断是否向左移动
           if ("move--left".equals(status)) {
               show = StaticValue.run_left.get(index);
           }
           //判断是否向右移动
           if ("move--right".equals(status)) {
               show = StaticValue.run_right.get(index);
           }
           //判断是否向左停止
           if ("stop--left".equals(status)) {
               show = StaticValue.stand_left;
           }

           //判断是否向右停止
           if ("stop--right".equals(status)) {
               show = StaticValue.stand_right;
           }
           //判断马里奥是否向左跳跃
           if("jump--left".equals(status))
           {
        	   show=StaticValue.jump_left;
           }
           //判断马里奥是否向右跳跃
           if("jump--right".equals(status))
           {
        	   show=StaticValue.jump_right;
           }
           try {
               Thread.sleep(50);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }


       }
  }
   public int getX()
   {
	   return x;
   }
   public void setX(int x)
   {
	   this.x=x;
   }
   public int getY()
   {
	   return y;
   }
   public void setY(int y)
   {
	   this.y=y;
   }
   public BufferedImage getShow()
   {
	   return show;
   }
   public void setShow(BufferedImage show)
   {
	   this.show=show;
   }
   public void setBackground(background bg)
   {
	   this.backGround=bg;
   }
   public void setOk(boolean p)
   {
	   this.isOk=p;
   }
   public boolean isOk()
   {
	   return isOk;
   }
   
  }
