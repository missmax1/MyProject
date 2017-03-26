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

import model.bean.LichSuDauGiaSanPham;
import model.bean.NguoiDung;
import model.bean.PhienDauGia;

public class LichSuDauGiaSanPhamDAO {


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
	
	public ArrayList<LichSuDauGiaSanPham> getlistLSDGSP(String maPDG) {
		
		connect();

		String sql = "select * from LichSuDauGiaSanPham join NguoiDung on NguoiDung.ID = LichSuDauGiaSanPham.IDNguoiThamGia "
				+ " where MaPDG = "+maPDG+" order by GiaTra desc";
		ResultSet rs = null;
	
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<LichSuDauGiaSanPham> list = new ArrayList<LichSuDauGiaSanPham>();
		LichSuDauGiaSanPham pDG;
		try {
			while(rs.next()){
				pDG = new LichSuDauGiaSanPham();
				pDG.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
				//chuyen doi format ngay
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				try {
				Date dt = formatter.parse(rs.getString("ThoiGian"));
				SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
				pDG.setThoiGian(formatter1.format(dt)); 
				} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
				// convert currenry
				Double giatra = Double.parseDouble(rs.getString("GiaTra").toString());
				DecimalFormat qw = new DecimalFormat("###,###,###");
				String resp = (qw.format(giatra*1000)+" VNĐ");
				resp = resp.replaceAll(",", ".");
				pDG.setGiaTra(resp);
				
				list.add(pDG);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}



}
