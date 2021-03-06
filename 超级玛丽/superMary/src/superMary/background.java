package superMary;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class background {
	//当前场景要使用的对象
   private BufferedImage bg=null;
   //记录当前是第几个场景
   private int sort;
   //判断是否是最后一个场景
   private boolean flag;
   //显示旗杆
   private BufferedImage qi=null;
   //显示城堡
   private BufferedImage castle=null;
   //场景中所有障碍物
   private List<zhangai> ai=new ArrayList<>();
   //判断旗子是否落地
   private boolean isBase=false;
   //判断马里奥是否到达旗杆位置
   private boolean isReach=false;
   public background()
   {
	   
   }
   
   public background(int sort,boolean flag)
   {
	   this.sort=sort;
	   this.flag=flag;
	   if(flag)
	   {
		   bg=StaticValue.background02;
	   }
	   else
	   {
		   bg=StaticValue.background01;
	   }
	   //判断是否是第一关
	   if(sort==1)
	   {
		   //绘制第一关图片，上地面type=1，下地面type=2
		   for(int i=0;i<27;i++)
		   {
			   ai.add(new zhangai(i*30,420,1,this));
		   }
		   for(int j=0;j<=120;j+=30)
		   {
			   for(int i=0;i<27;i++)
			   {
				   ai.add(new zhangai(i*30,570-j,2,this));
			   }
		   }
		   
		   //绘制砖块A
		   for(int i=120;i<=150;i+=30)
		   {
			   ai.add(new zhangai(i,300,7,this));
		   }
		   //绘制砖块B~F
		   for(int i=300;i<=570;i+=30)
		   {
			   if(i==360||i==390||i==480||i==510||i==540)
			   {
				   ai.add(new zhangai(i,300,7,this));
			   }
			   else
			   {
				   ai.add(new zhangai(i,300,0,this));
			   }
		   }
		   //绘制砖块G
		   for(int i=420;i<=450;i+=30)
		   {
			   ai.add(new zhangai(i,240,7,this));
		   }
		   //绘制水管
		   for(int i=360;i<=600;i+=25)
		   {
			   if(i==360)
			   {
				   ai.add(new zhangai(620,i,3,this));
				   ai.add(new zhangai(645,i,4,this));
			   }
			   else
			   {
				   ai.add(new zhangai(620,i,5,this));
				   ai.add(new zhangai(645,i,6,this));
			   }
		   }
		   
	   }
	 //判断是否是第二关
       if (sort == 2) {
           //绘制第二关的地面,上地面type=1,下地面type=2
           for (int i = 0;i < 27;i++) {
               ai.add(new zhangai(i*30,420,1,this));
           }

           for (int j = 0;j <= 120;j += 30) {
               for (int i = 0;i < 27;i++) {
                   ai.add(new zhangai(i*30,570-j,2,this));
               }
           }

           //绘制第一个水管
           for (int i = 360;i <= 600;i += 25) {
               if (i == 360) {
                   ai.add(new zhangai(60,i,3,this));
                   ai.add(new zhangai(85,i,4,this));
               }else {
                   ai.add(new zhangai(60,i,5,this));
                  ai.add(new zhangai(85,i,6,this));
               }
           }

           //绘制第二个水管
           for (int i = 330;i <= 600;i += 25) {
               if (i == 330) {
                   ai.add(new zhangai(620,i,3,this));
                   ai.add(new zhangai(645,i,4,this));
               }else {
                   ai.add(new zhangai(620,i,5,this));
                   ai.add(new zhangai(645,i,6,this));
               }
           }

           //绘制砖块C
           ai.add(new zhangai(300,330,0,this));

           //绘制砖块B,E,G
           for (int i = 270;i <= 330;i += 30) {
               if (i == 270 || i == 330) {
                   ai.add(new zhangai(i,360,0,this));
               }else {
                   ai.add(new zhangai(i,360,7,this));
               }
           }

           //绘制砖块A,D,F,H,I
           for (int i = 240;i <= 360;i += 30) {
               if (i == 240 || i == 360) {
                   ai.add(new zhangai(i,390,0,this));
               }else {
                   ai.add(new zhangai(i,390,7,this));
               }
           }

           //绘制妨碍1砖块
           ai.add(new zhangai(240,300,0,this));

           //绘制空1-4砖块
           for (int i = 360;i <= 540;i += 60) {
               ai.add(new zhangai(i,270,7,this));
           }
       }
       
       if (sort == 3) {
           //绘制第三关的地面,上地面type=1,下地面type=2
           for (int i = 0;i < 27;i++) {
               ai.add(new zhangai(i*30,420,1,this));
           }

           for (int j = 0;j <= 120;j += 30) {
               for (int i = 0;i < 27;i++) {
                   ai.add(new zhangai(i*30,570-j,2,this));
               }
           }

           //绘制第三个背景的A-O砖块
           int temp = 290;
           for (int i = 390;i >= 270;i -= 30) {
               for (int j = temp;j <= 410;j += 30) {
                   ai.add(new zhangai(j,i,7,this));
               }
               temp += 30;
           }

           //绘制第三个背景的P-R砖块
           temp = 60;
           for (int i = 390;i >= 360;i -= 30) {
               for (int j = temp;j <= 90;j += 30) {
                   ai.add(new zhangai(j,i,7,this));
               }
               temp += 30;
           }

           //绘制旗杆
           qi = StaticValue.flag;

           //绘制城堡
           castle = StaticValue.castle;

           //添加旗子到旗杆上
           ai.add(new zhangai(515,220,8,this));
       }

	   
   }
   
   
   public BufferedImage getImage()
   {
	   return bg;
   }
   
   public int getSort()
   {
	   return sort;
   }
   public boolean getFlag()
   {
	   return flag;
   }
   public List<zhangai> getAi()
   {
	   return ai;
   }
   public BufferedImage getQi()
   {
	   return qi;
   }
   public BufferedImage getCastle()
   {
	   return castle;
   }
   public boolean isReach()
   {
	   return isReach;
   }
   public void setReach(boolean p)
   {
	   this.isReach=p;
   }
   public boolean isBase()
   {
	   return isBase;
   }
   public void setBase(boolean p)
   {
	   this.isBase=p;
   }
   public boolean isFlag()
   {
	   return flag;
   }
}
