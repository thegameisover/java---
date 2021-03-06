package superMary;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {
   //背景
	public static BufferedImage background01=null;
	public static BufferedImage background02=null;
	//马里奥向左跳跃
	public static BufferedImage jump_left=null;
	//马里奥向右跳跃
	public static BufferedImage jump_right=null;
	//马里奥向左站立
	public static BufferedImage stand_left=null;
	//马里奥向右站立
	public static BufferedImage stand_right=null;
	//城堡
	public static BufferedImage castle=null;
	//旗杆 
	public static BufferedImage flag=null;
	//障碍物
	public static List<BufferedImage> obstruct=new ArrayList<>();
	//马里奥向左跑
    public static List<BufferedImage> run_left=new ArrayList<>();
    //马里奥向右跑
    public static List<BufferedImage> run_right=new ArrayList<>();
    //蘑菇
    public static List<BufferedImage> mashroom=new ArrayList<>();
    //食人花
    public static List<BufferedImage> flower=new ArrayList<>();
    //路径的前缀
    public static String path=System.getProperty("user.dir")+"/src/image/04超级玛丽的图片/";
    //初始化方法
    public static void init()
    {
    	try {
			background01=ImageIO.read(new File(path+"bg.png"));
			background02=ImageIO.read(new File(path+"bg2.png"));
			jump_left=ImageIO.read(new File(path+"s_mario_jump1_L.png"));
			jump_right=ImageIO.read(new File(path+"s_mario_jump1_R.png"));
			stand_left=ImageIO.read(new File(path+"s_mario_stand_L.png"));
			stand_right=ImageIO.read(new File(path+"s_mario_stand_R.png"));
			castle=ImageIO.read(new File(path+"tower.png"));
			flag=ImageIO.read(new File(path+"gan.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	for(int i=1;i<=2;i++)
    	{
    		try {
				run_left.add(ImageIO.read(new File(path+"s_mario_run"+i+"_L.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	for(int i=1;i<=2;i++)
    	{
    		try {
				run_right.add(ImageIO.read(new File(path+"s_mario_run"+i+"_R.png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	try {
			obstruct.add(ImageIO.read(new File(path+"brick.png")));
			obstruct.add(ImageIO.read(new File(path+"soil_base.png")));
			obstruct.add(ImageIO.read(new File(path+"soil_up.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	for(int i=1;i<=4;i++)
    	{
    		try {
				obstruct.add(ImageIO.read(new File(path+"pipe"+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	//添加不可破坏的砖块和旗子
    	try {
			obstruct.add(ImageIO.read(new File(path+"brick2.png")));
			obstruct.add(ImageIO.read(new File(path+"flag.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	//蘑菇
    	for(int i=1;i<=3;i++)
    	{
    		try {
				mashroom.add(ImageIO.read(new File(path+"fungus"+i+".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	//食人花
    	try {
			flower.add(ImageIO.read(new File(path+"flower1.1.png")));
			flower.add(ImageIO.read(new File(path+"flower1.2.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
