package com.rb.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

////这是一个工具类，用于得到连接和关闭连接
public class DBUtil {
	private static Connection ct = null;// 连接
	// 连接数据库参数
	private static String url = "";
	private static String drivername = "";
	private static String username = "";
	private static String password = "";
	// 加载驱动，一次
	static {
		try {
			Properties properties = new Properties();
			InputStream is = DBUtil.class.getClassLoader().getResourceAsStream(
					"com/rb/utils/dbinfo.properties");
			properties.load(is);
			// 属性文件读取信息
			drivername = properties.getProperty("driver");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			url = properties.getProperty("url");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	// 得到连接
	public static Connection getCon() {
		try {
			//加载驱动
			Class.forName(drivername);
			//得到连接"jdbc:mysql://127.0.0.1:3306/spdb1","root","1234567"
			ct = DriverManager.getConnection(url, username, password);// 注意配置文件
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ct;// 谁调用谁拿到Connection
	}

	// 关闭资源函数
	public static void close(ResultSet rs, Statement ps, Connection ct) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {

			}
			rs = null;// 使用垃圾回收
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ps = null;
		}

		if (ct != null) {
			try {
				ct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ct = null;
		}

	}

}
