package com.huaxin.mario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.huaxin.enery.Enery;
import com.huaxin.enery.Pipe;

import Util.Map;

public class GameFrame extends JFrame{
	
	public Mario mario;
	public Enery pipe,cion,brick;
	//背景图片
	public BackgroundImage bg ;
	
	//容器装敌人
	public ArrayList<Enery> eneryList = new ArrayList<Enery>();
	
	//子弹容器
	public ArrayList<Boom> boomList = new ArrayList<Boom>();
	
	//子弹的速度
	public int bspeed=0;
	
	//画地图，制定规则，是1画砖头，是2画金币，是3画水管
	public int [][] map =null;
	
	//构造函数里面初始化背景图片和马里奥对象
	public GameFrame() throws Exception {
		
		mario = new Mario(this);
		mario.start();
		Map mp= new Map();
		bg = new BackgroundImage();
		
		//窗体重绘线程
		new Thread(){
			public void run(){
				while(true){
					//重绘窗体
					repaint();
					//检查子弹是否出界
					checkBoom();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		map=mp.readMap();
		//读取地图，并配置地图
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				//读取到的是1，画砖头
				if(map[i][j]==1){
					brick = new Pipe(j*30,i*30,30,30,new ImageIcon("image/brick.png").getImage());
					eneryList.add(brick);
				}
				//读到2画金币
				if(map[i][j]==2){
					cion = new Pipe(j*30,i*30,30,30,new ImageIcon("image/coin_brick.png").getImage());
					eneryList.add(cion);
				}
				//读到3画水管
				if(map[i][j]==3){
					pipe = new Pipe(j*30,i*30,60,120,new ImageIcon("image/pipe.png").getImage());
					eneryList.add(pipe);
				}
				
			}
		}
		//设置背景音乐
		com.huaxin.music.Util.startMusic("music/bg1.wav");
	}
	
	public void initFrame(){
		//设置窗体相关属性
		this.setSize(800,450);
		this.setTitle("山寨版超级玛丽");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(3);
		this.setVisible(true);
		
		//该窗体添加键盘监听
		KeyListener kl = new KeyListener(this);
		this.addKeyListener(kl);
	}
	
	public void paint(Graphics g) {
		//利用双缓冲画背景图片和马里奥
		BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);
		Graphics big =bi.getGraphics();
		big.drawImage(bg.img, bg.x, bg.y, null);
		
		for (int i = 0; i <eneryList.size(); i++) {
			Enery e =eneryList.get(i);
			big.drawImage(e.img, e.x, e.y, e.width, e.height,null);
		}
		
		//画子弹
		for (int i = 0; i < boomList.size(); i++) {
			Boom b =boomList.get(i);
			Color c =big.getColor();
			big.setColor(Color.red);
			big.fillOval(b.x+=b.speed, b.y, b.width, b.width);
			big.setColor(c);
		}
		
		//画人物
		big.drawImage(mario.img, mario.x, mario.y, mario.width, mario.height,null);
		g.drawImage(bi,0,0,null);
		
	}
	
	//检查子弹是否出界，出界则从容器中移除，不移除的话，内存会泄漏
	public void checkBoom(){
		for (int i = 0; i < boomList.size(); i++) {
			Boom b = boomList.get(i);
			if(b.x<0 || b.x>800){
				boomList.remove(i);
			}
		}
	}

}
