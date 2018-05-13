package com.huaxin.mario;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


//键盘按下监听类
public class KeyListener extends KeyAdapter{
	
	public GameFrame gf;
	public boolean jumpFlag=true;
	
	public KeyListener(GameFrame gf) {
		this.gf=gf;
	}
	
	//键盘监听
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code){
		//向右走
		case 39:
			gf.mario.right=true;
			break;
			//向左走
		case 37:
			gf.mario.left=true;
		break;
		
		case 66:
			addBoom();
		break;
		
		//向上跳
		case 74:
			gf.mario.up=true;
			break;
	}
	}
	
	//添加子弹
	public void addBoom() {	
		Boom b = new Boom(gf.mario.x,gf.mario.y+5,10);
		if(gf.mario.left) b.speed=-2;
		if(gf.mario.right) b.speed=2;
		gf.boomList.add(b);
	}

	//键盘释放监听
	public void keyReleased(KeyEvent e) {
		
		int code=e.getKeyCode();
		if(code==39){
			
			gf.mario.right=false;
			
			gf.mario.img=new ImageIcon("image/mari1.png").getImage();
		}
		if(code==37){
			gf.mario.left=false;
			
			gf.mario.img=new ImageIcon("image/mari_left1.png").getImage();
		}
		
		if(code==74){
			gf.mario.up=false;
			
		}
	}

}
