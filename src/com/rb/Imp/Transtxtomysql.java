package com.rb.Imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.rb.utils.SqlHelper;


public class Transtxtomysql {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] temp = fileread("D:\\eclipse for javaweb\\workplace\\TxttoMysql\\zhuirishuju.txt", "GBK");
		String[] inserts = null;
		SqlHelper sqlHelper = new SqlHelper();
		
		for (int i = 1; i < temp.length; i++) {
			inserts = temp[i].split(" ");
			StringBuffer insert = new StringBuffer("INSERT INTO S201711062131568300500 VALUES(");
			for (int j = 0; j < inserts.length-1; j++) {
				insert.append(inserts[j]+",");
			}
			insert.append(inserts[inserts.length-1]+")");
			sqlHelper.insertData(insert.toString());
		}
		
	}

	private static String[] fileread(String path,String encodeString) {
		File file = new File(path);
		String encoding = encodeString;
		ArrayList list = new ArrayList();//储存读取的txt每行string
		String[] reStrings = null;//返回的数组	
		if (file.isFile()&&file.exists()) {
			InputStreamReader read = null;
			try {
				read = new InputStreamReader(new FileInputStream(file),encoding);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				System.out.println("不支持编码");
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("找不到指定的文件");
				e.printStackTrace();
			}
			BufferedReader br = new BufferedReader(read);
			String line = null;
			try {
				while ((line=br.readLine())!=null) {
					list.add(line);
				}
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		reStrings = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			String s = (String) list.get(i);
			reStrings[i] =s;
		}
		return reStrings;
	}
}
