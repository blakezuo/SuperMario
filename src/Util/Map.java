package Util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//地图配置类
public class Map {
	
	//数据容器
	public ArrayList<String> list = new ArrayList<String>();
	
	public int [][] map=null;
	
	public  int[][] readMap() throws Exception {
		// 构造文件输入流
		FileInputStream fis = new FileInputStream("map.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		
		//直接读取一行数据
		String value =br.readLine();
		
	    while(value!=null){
	    	//将读取到的一行数据加入到容器中
	    	list.add(value);
	    	value =br.readLine();
	    }
	    
	    br.close();
	    
	    //得到多少行多少列
	    int row=list.size();
	    int cloum=0;
	
	    for (int i = 0; i < 1; i++) {
			String str=list.get(i);
			String [] values=str.split(",");
			cloum=values.length;
		}
	    
	    
	    map = new int [row][cloum];
	    
	    //将读到的字符创转换成整数，并赋值给二位数组map
	   for (int i = 0; i < list.size(); i++) {
		   String str=list.get(i);
			String [] values=str.split(",");
			for (int j = 0; j < values.length; j++) {
				map[i][j]=Integer.parseInt(values[j]);
			}
	}
	  return map;

	}

}
