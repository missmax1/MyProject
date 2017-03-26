package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.LichSuDauGiaSanPham;
import model.bean.LoaiSanPham;
import model.bean.NguoiDung;


public class NguoiDungDAO {

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

	public NguoiDung getThongTinNguoiDung(String iD) {

		 {
			connect();
			String sql=	String.format("SELECT * "+
						" FROM NguoiDung WHERE ID = '%s'", iD);
			ResultSet rs = null;
			try {
				Statement stmt = connection.createStatement();
				rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			NguoiDung nn = new NguoiDung();
			try {
				while(rs.next()){
					nn.setTenNguoiDung(rs.getString("TenNguoiDung"));
					nn.setGioiTinh(rs.getString("GioiTinh"));
					nn.setNgaySinh(rs.getString("NgaySinh"));
					nn.setDiaChi(rs.getString("DiaChi"));
					nn.setcMND(rs.getString("CMND"));
					nn.setTenTaiKhoan(rs.getString("TenTaiKhoan"));
					nn.setSoDuTaiKhoan(rs.getString("SoDuTaiKhoan"));
					nn.setMatKhau(rs.getString("MatKhau"));
					nn.setLoaiTaiKhoan(rs.getString("LoaiTaiKhoan"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return nn;
		
		 }
	}

	public void capNhapThongTin(String iD, String tenNguoiDung, String gioiTinh, String diaChi, String cMND
			, String matKhau) {
	
		connect();
		String sql=	String.format("UPDATE NguoiDung "+
					" SET TenNguoiDung = N'%s', GioiTinh = %s, DiaChi = N'%s'  , CMND = '%s', MatKhau = N'%s'  " +
					" WHERE ID = '%s'", tenNguoiDung, gioiTinh,diaChi , cMND,matKhau, iD);
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<NguoiDung> getlistTopWinner() {
	
		connect();

		String sql = "select ID,TenNguoiDung,solanthamgia, "+
				"CASE when solanthang is null then 0 else solanthang end as solanthang "+
				"from (select A.ID,TenNguoiDung , count(B.GiaTra) as solanthamgia "+
				"from NguoiDung A join LichSuDauGiaSanPham B "+ 
				"on A.ID = B.IDNguoiThamGia "+  
				"group by A.ID,TenNguoiDung) X "+
				"LEFT OUTER join (select A.IDNguoiThamGia, count(distinct A.MaPDG) as solanthang from LichSuDauGiaSanPham A join PhienDauGia B "+
				"on A.MaPDG = B.MaPDG "+
				"where A.IDNguoiThamGia in "+
				"(select C.IDNguoiThamGia  from LichSuDauGiaSanPham C "+ 
				       "where C.GiaTra = (Select top 1 GiaTra from LichSuDauGiaSanPham where MaPDG = B.MaPDG order by GiaTra desc) ) "+
				"group by A.IDNguoiThamGia) Z "+
				"on X.ID = Z.IDNguoiThamGia "+
				"order by solanthang desc ";
		
		System.out.println("test" +sql);
		ResultSet rs = null;
	
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<NguoiDung> list = new ArrayList<NguoiDung>();
		NguoiDung nn;
		try {
			while(rs.next()){
				nn = new NguoiDung();
				nn.setTenNguoiDung(rs.getString("TenNguoiDung"));
				nn.setSoLanThamGia(rs.getString("solanthamgia"));
				nn.setSoLanThang(rs.getString("solanthang"));
				list.add(nn);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
}	

