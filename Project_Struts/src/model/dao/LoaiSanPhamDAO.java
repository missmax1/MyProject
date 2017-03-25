package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.LoaiSanPham;;

/**
 * KhoaDAO.java
 *
 * Version 1.0
 *
 * Date: Jan 20, 2015
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jan 20, 2015        	DaiLV2          Create
 */

public class LoaiSanPhamDAO {

	String url = "jdbc:sqlserver://localhost:1433;databaseName=WebsiteDauGia";
	String userName = "sa";
	String password = "12345678";
	Connection connection;
	
	void connect(){
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url, userName, password);
			System.out.println("Ket noi thanh cong");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Ket noi loi");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Ket noi loi");
		}
	}
	
	public ArrayList<LoaiSanPham> getlistLoaiSP() {
		connect();
		String sql=	"SELECT * FROM LoaiSanPham";
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		ArrayList<LoaiSanPham> list = new ArrayList<LoaiSanPham>();
		LoaiSanPham khoa;
		try {
			while(rs.next()){
				khoa = new LoaiSanPham();
				khoa.setMaLoaiSP(rs.getString("MaLoaiSP"));
				khoa.setTenLoai(rs.getString("TenLoaiSP"));
				list.add(khoa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}

