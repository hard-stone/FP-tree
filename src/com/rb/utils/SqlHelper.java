package com.rb.utils;
//src=类路径
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//这是一个工具栏，主要用于完成对数据库的crud操作
public class SqlHelper 
{
	//定义需要的变量	
	private static Connection ct=null;//连接
	private static ResultSet rs=null;//结果
	//预编译sql，减少sql执行时间；
	private static PreparedStatement ps=null;
	
	public ArrayList executeQuery(String sql,String []paras)
	{
		ArrayList al=new ArrayList();
		try {
			//获得连接
			ct=DBUtil.getCon();
			ps=ct.prepareStatement(sql);
			//给sql问号赋值
			for (int i = 0; i < paras.length; i++) 
			{
				//
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
			//非常有用的 
			ResultSetMetaData rsmd=rs.getMetaData();
			//用法rs可以的到有多少列
			int columnNum=rsmd.getColumnCount();
			//循环从a1中取出数据封装到ArrayList中
			while(rs.next())
			{
				Object []objects=new Object[columnNum];
				for(int i=0;i<objects.length;i++)
				{
					objects[i]=rs.getObject(i+1); //返回对象数组
				}
				al.add(objects);
			}
			return al;
			} catch (Exception e) 
			{
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
		}finally
		{
			DBUtil.close(rs,ps,ct);
		}
		
	}
	
	//根据sql语句；返回一个结果集
	public ResultSet executeQuery(String sqlstr) 
	{
		Statement stmt = null;
		try
		{
			//得到连接
			ct=DBUtil.getCon();
			//ps=ct.prepareStatement(sqlstr);
			stmt = ct.createStatement();
			//创建结果集
			 rs = stmt.executeQuery(sqlstr); 
			//将结果集返回
			return rs;
		}
		catch(SQLException e)
		{
			System.out.print("错误");
		}finally{
			DBUtil.close(rs, ps, ct);
		}
		return null;
	}
	
	public int insertData(String insert) {
		int i = 0;
		ct=DBUtil.getCon();
		try {
			ps=ct.prepareStatement(insert);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, ps, ct);
		}
		return i;
	}
}
