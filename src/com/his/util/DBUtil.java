package com.his.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.his.util.DBUtil;

public class DBUtil {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	
	/*
	 * 程序运行的时候就执行初始化操作
	 */
	static {
		init();
	}
	
	/*
	 * 连接的初始化
	 */
	public static void init() {
		Properties prop = new Properties();
		String configFile = "database.properties"; //配置文件路径
		//加载配置文件到输入流中
		InputStream is = DBUtil.class.getClassLoader().getResourceAsStream(configFile);
		try {
			prop.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取指定的相关变量的名字
		driver = prop.getProperty("driver");
		System.out.println(driver);
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		password = prop.getProperty("password");
		
	}
	
	/*
	 * 建立连接
	 */ 
	public Connection getConnection() {
		Connection con = null;
		try {              
			Class.forName(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/*
	 *  关闭流
	 */
	public void closeAll(Connection con, Statement stmt, ResultSet rs) {
			try {
				if (con != null) {
					con.close();
					con = null;
				}
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	protected Connection con = null;
	protected PreparedStatement pstm = null;
	protected ResultSet rs = null;
	/*
	 * 更新操作
	 */
	public int executeUpdate(String sql, Object...params){
		int result = 0;
		Connection con = this.getConnection();
		try {
			pstm = con.prepareStatement(sql);
			//如果有值被传过来，则将他的值循环赋给pstm
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstm.setObject(i + 1, params[i]);
				}
			}
			result = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstm, null);
		}
		return result;
	}
	
	/*
	 * 查询操作
	 */
	
	public ResultSet executeQuery(String sql, Object...params) {
		con = this.getConnection();
		try {
			pstm = con.prepareStatement(sql);
			//如果有值被传过来，则将他的值循环赋给pstm
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstm.setObject(i + 1, params[i]);
				}
			}
			rs = pstm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
//			this.closeAll(con, pstm, rs);
		}
		return rs;
	}
	
	
}
