package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.bean.SanPhamDauGia;

public class SanPhamDauGiaDAO {


	String url = "jdbc:sqlserver://localhost:1433;databaseName=WebsiteDauGia";
	String userName = "sa";
	String password = "123";
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
	Statement stmt;
	
	
	public ArrayList<SanPhamDauGia> getListSanPham(String maPDG) {
		
		connect();

		String sql = "select  * from SanPhamDauGia join HinhAnhSP on HinhAnhSP.MaSP = SanPhamDauGia.MaSP  "+
					"where IDHinhAnh= "+
					" (select top 1 IDHinhAnh from HinhAnhSP where MaSP = SanPhamDauGia.MaSP ) "+
					" and MaPDG ="+maPDG+"  ";
		ResultSet rs = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<SanPhamDauGia> list = new ArrayList<SanPhamDauGia>();
		SanPhamDauGia sanPhamDG;
		try {
			while(rs.next()){
				sanPhamDG = new SanPhamDauGia();
				sanPhamDG.setTenSanPham(rs.getString("TenSanPham"));
				sanPhamDG.setHangSanXuat(rs.getString("HangSanXuat"));
				sanPhamDG.setThoiGianBaoHanh(rs.getString("ThoiGianBaoHanh"));
				sanPhamDG.setMoTa(rs.getString("MoTa"));
				sanPhamDG.setTenHinhAnh(rs.getString("TenHinhAnh"));
				list.add(sanPhamDG);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

		
		
	}

}
