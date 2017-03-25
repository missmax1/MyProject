package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import model.bean.LoaiSanPham;
import model.bean.PhienDauGia;



public class PhienDauGiaDAO {

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
	Statement stmt;
	private int noOfRecords = 0;

	public ArrayList<PhienDauGia> getListPDG(String page) {
		
		
		
		connect();
		int recordsPerPage = 4;
		int offsetRow = recordsPerPage*(Integer.parseInt(page)-1);
		

				
		String sql = "select A.MaPDG,A.TenPDG,A.GiaDeNghi,A.ThoiGianBatDau,A.ThoiGianKetThuc,C.TenHinhAnh, "+
				 		"(count( DISTINCT IDNguoiThamGia))as tong,max(GiaTra)as giahientai "+
						"from PhienDauGia A  full OUTER JOIN lichsudaugiasanpham E on E.MaPDG = A.MaPDG "+
						 "JOIN SanPhamDauGia B "+ 
						"on A.MaPDG = B.MaPDG JOIN HinhAnhSP C "+
						"on B.MaSP = C.MaSP "+
						"where B.MaSP in ( select top 1 MaSP from SanPhamDauGia where MaPDG = A.MaPDG) "+
						"and c.IDHinhAnh=(select top 1 IDHinhAnh from HinhAnhSP where MaSP = B.MaSP) "+ 
						"and ThoiGianKetThuc-  (SELECT GETDATE() AS CurrentDateTime) >0 "+
						"group by A.MaPDG,A.TenPDG,A.GiaDeNghi,A.ThoiGianBatDau,A.ThoiGianKetThuc,C.TenHinhAnh order by tong desc,A.MaPDG "+
						" OFFSET "+offsetRow+" ROWS FETCH NEXT "+recordsPerPage+" ROWS ONLY";
		ResultSet rs = null;
		System.out.println("123123123123123123123333333333 "+ sql);
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<PhienDauGia> list = new ArrayList<PhienDauGia>();
		PhienDauGia pDG;
		try {
			while(rs.next()){
				pDG = new PhienDauGia();
				pDG.setMaPDG(rs.getString("mapdg"));
				pDG.setTenPDG(rs.getString("TenPDG"));
				pDG.setGiaDeNghi(rs.getString("GiaDeNghi"));
				pDG.setThoiGianKetThuc(rs.getString("ThoiGianKetThuc"));

				// bien doi time
				//HH converts hour in 24 hours format (0-23), day calculation
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				// lấy ngày giờ hiện tại
				String curentDate = format.format(new Date());
//				System.out.println("vvv"+curentDate);
				
				// lấy ngày giờ kết thúc đấu giá
				String temp = pDG.getThoiGianKetThuc();
				temp = temp.replace("-", "/");
//				System.out.println("temp"+temp);

				

				Date d1 = null; // thời gian kết thúc
				Date d2 = null; // thời gian bắt đầu
				
				// convert String to datetime: format("yyyy/MM/dd HH:mm:ss")
				d1 = format.parse(temp);
				d2 = format.parse(curentDate);
				
				//in milliseconds
				long diff = d1.getTime() - d2.getTime();
				
				String countdown = String.valueOf(diff);
				System.out.println(diff);
				System.out.println("SFSDFSDFSDFD"+countdown);
				pDG.setCountDown(countdown);
				
				
				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				
				System.out.println("phien " + rs.getString("TenPDG") + " ");
				System.out.print(diffDays + " days, ");
				System.out.print(diffHours + " hours, ");
				System.out.print(diffMinutes + " minutes, ");
				System.out.print(diffSeconds + " seconds.\n");

				
				
				
				pDG.setSoNguoiDauGia(rs.getString("tong"));
				
				if(rs.getString("giahientai")==null){
					pDG.setGiaHienTai(pDG.getGiaDeNghi());
				}
				else
				pDG.setGiaHienTai(rs.getString("giahientai"));
				pDG.setHinhAnh(rs.getString("TenHinhAnh"));
				// lay hinh anh
				
				
				
				
				list.add(pDG);
			}
			rs = stmt.executeQuery("SELECT count(*) from phiendaugia");
			
			if(rs.next()) {
				this.noOfRecords = rs.getInt(1);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	

	public ArrayList<PhienDauGia> getListNewPDG(String page) {
	
		connect();
			int recordsPerPage = 4;
			int offsetRow = recordsPerPage*(Integer.parseInt(page)-1);
			
			String sql = "select A.MaPDG,A.TenPDG,A.GiaDeNghi,A.ThoiGianBatDau,A.ThoiGianKetThuc,C.TenHinhAnh, "+
			 		"(count( DISTINCT IDNguoiThamGia))as tong,max(GiaTra)as giahientai "+
					"from PhienDauGia A  full OUTER JOIN lichsudaugiasanpham E on E.MaPDG = A.MaPDG "+
					 "JOIN SanPhamDauGia B "+ 
					"on A.MaPDG = B.MaPDG JOIN HinhAnhSP C "+
					"on B.MaSP = C.MaSP "+
					"where B.MaSP in ( select top 1 MaSP from SanPhamDauGia where MaPDG = A.MaPDG) "+
					"and c.IDHinhAnh=(select top 1 IDHinhAnh from HinhAnhSP where MaSP = B.MaSP) "+ 
					"and ThoiGianKetThuc-  (SELECT GETDATE() AS CurrentDateTime) >0 "+
					"group by A.MaPDG,A.TenPDG,A.GiaDeNghi,A.ThoiGianBatDau,A.ThoiGianKetThuc,C.TenHinhAnh order by thoigianbatdau desc,A.MaPDG "+
					" OFFSET "+offsetRow+" ROWS FETCH NEXT "+recordsPerPage+" ROWS ONLY";
			ResultSet rs = null;
			System.out.println("123123123123123123123 "+ sql);
			try {
				stmt = connection.createStatement();
				rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ArrayList<PhienDauGia> list = new ArrayList<PhienDauGia>();
			PhienDauGia pDG;
			try {
				while(rs.next()){
					pDG = new PhienDauGia();
					pDG.setMaPDG(rs.getString("mapdg"));
					pDG.setTenPDG(rs.getString("TenPDG"));
					pDG.setGiaDeNghi(rs.getString("GiaDeNghi"));
					pDG.setThoiGianKetThuc(rs.getString("thoigianketthuc"));
					
					
					// bien doi time
					//HH converts hour in 24 hours format (0-23), day calculation
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					
					// lấy ngày giờ hiện tại
					String curentDate = format.format(new Date());
	//				System.out.println("vvv"+curentDate);
					
					// lấy ngày giờ kết thúc đấu giá
					String temp = pDG.getThoiGianKetThuc();
					temp = temp.replace("-", "/");
	//				System.out.println("temp"+temp);
	
					
	
					Date d1 = null; // thời gian kết thúc
					Date d2 = null; // thời gian bắt đầu
					
					// convert String to datetime: format("yyyy/MM/dd HH:mm:ss")
					d1 = format.parse(temp);
					d2 = format.parse(curentDate);
					
					//in milliseconds
					long diff = d1.getTime() - d2.getTime();
					
					String countdown = String.valueOf(diff);
					System.out.println(diff);
					System.out.println("SFSDFSDFSDFD"+countdown);
					pDG.setCountDown(countdown);
					
					
					long diffSeconds = diff / 1000 % 60;
					long diffMinutes = diff / (60 * 1000) % 60;
					long diffHours = diff / (60 * 60 * 1000) % 24;
					long diffDays = diff / (24 * 60 * 60 * 1000);
					
					System.out.println("phien " + rs.getString("TenPDG") + " ");
					System.out.print(diffDays + " days, ");
					System.out.print(diffHours + " hours, ");
					System.out.print(diffMinutes + " minutes, ");
					System.out.print(diffSeconds + " seconds.\n");
	
					
					
					
					pDG.setSoNguoiDauGia(rs.getString("tong"));
					
					if(rs.getString("giahientai")==null){
						pDG.setGiaHienTai(pDG.getGiaDeNghi());
					}
					else
					pDG.setGiaHienTai(rs.getString("giahientai"));
					
					// lay hinh anh
					
					pDG.setHinhAnh(rs.getString("TenHinhAnh"));
					
					
					list.add(pDG);
				}
				rs = stmt.executeQuery("SELECT count(*) from phiendaugia");
				
				if(rs.next()) {
					this.noOfRecords = rs.getInt(1);
				}
					
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
		
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}
	
	public ArrayList<PhienDauGia> getListPDG(String searchText, String page){
		connect();
		int recordsPerPage = 4;
		int offsetRow = recordsPerPage*(Integer.parseInt(page)-1);

		String sql = "select A.MaPDG,A.TenPDG,A.GiaDeNghi,A.ThoiGianBatDau,A.ThoiGianKetThuc,C.TenHinhAnh, "+
		 		"(count( DISTINCT IDNguoiThamGia))as tong,max(GiaTra)as giahientai "+
				"from PhienDauGia A  full OUTER JOIN lichsudaugiasanpham E on E.MaPDG = A.MaPDG "+
				 "JOIN SanPhamDauGia B "+ 
				"on A.MaPDG = B.MaPDG JOIN HinhAnhSP C "+
				"on B.MaSP = C.MaSP "+
				"where B.MaSP in ( select top 1 MaSP from SanPhamDauGia where MaPDG = A.MaPDG) "+
				"and c.IDHinhAnh=(select top 1 IDHinhAnh from HinhAnhSP where MaSP = B.MaSP) "+ 
				"and ThoiGianKetThuc-  (SELECT GETDATE() AS CurrentDateTime) >0 "+
				"and A.TenPDG like N'%"+searchText+"%' "+
				"group by A.MaPDG,A.TenPDG,A.GiaDeNghi,A.ThoiGianBatDau,A.ThoiGianKetThuc,C.TenHinhAnh "+
				"order by thoigianbatdau desc,A.MaPDG OFFSET "+offsetRow+" ROWS FETCH NEXT "+recordsPerPage+" ROWS ONLY";

		ResultSet rs = null;
		System.out.println("123123123123123123123 "+ sql);
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<PhienDauGia> list = new ArrayList<PhienDauGia>();
		PhienDauGia pDG;
		try {
			while(rs.next()){
				pDG = new PhienDauGia();
				pDG.setMaPDG(rs.getString("mapdg"));
				pDG.setTenPDG(rs.getString("TenPDG"));
				pDG.setGiaDeNghi(rs.getString("GiaDeNghi"));
				pDG.setThoiGianKetThuc(rs.getString("thoigianketthuc"));
				
				
				// bien doi time
				//HH converts hour in 24 hours format (0-23), day calculation
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				// lấy ngày giờ hiện tại
				String curentDate = format.format(new Date());
//				System.out.println("vvv"+curentDate);
				
				// lấy ngày giờ kết thúc đấu giá
				String temp = pDG.getThoiGianKetThuc();
				temp = temp.replace("-", "/");
//				System.out.println("temp"+temp);

				

				Date d1 = null; // thời gian kết thúc
				Date d2 = null; // thời gian bắt đầu
				
				// convert String to datetime: format("yyyy/MM/dd HH:mm:ss")
				try {
					d1 = format.parse(temp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					d2 = format.parse(curentDate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//in milliseconds
				long diff = d1.getTime() - d2.getTime();
				
				String countdown = String.valueOf(diff);
				System.out.println(diff);
				System.out.println("SFSDFSDFSDFD"+countdown);
				pDG.setCountDown(countdown);
				
				
				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				
				System.out.println("phien " + rs.getString("TenPDG") + " ");
				System.out.print(diffDays + " days, ");
				System.out.print(diffHours + " hours, ");
				System.out.print(diffMinutes + " minutes, ");
				System.out.print(diffSeconds + " seconds.\n");

				pDG.setSoNguoiDauGia(rs.getString("tong"));
				
				if(rs.getString("giahientai")==null){
					pDG.setGiaHienTai(pDG.getGiaDeNghi());
				}
				else
				pDG.setGiaHienTai(rs.getString("giahientai"));
				// lay hinh anh
				pDG.setHinhAnh(rs.getString("TenHinhAnh"));
					
				list.add(pDG);
			}
			
			rs = stmt.executeQuery("select count(*) from PhienDauGia where TenPDG like N'%"+searchText+"%' ");	
			
			if(rs.next())
					this.noOfRecords = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public ArrayList<PhienDauGia> getListPDGTheoMa(String maLSP, String page) {
		
		connect();
		int recordsPerPage = 4;
		int offsetRow = recordsPerPage*(Integer.parseInt(page)-1);
		
	
				
	
		String sql = "select A.MaPDG,A.TenPDG,A.GiaDeNghi,A.ThoiGianBatDau,A.ThoiGianKetThuc,C.TenHinhAnh, "+
		 		"(count( DISTINCT IDNguoiThamGia))as tong,max(GiaTra)as giahientai "+
				"from PhienDauGia A  full OUTER JOIN lichsudaugiasanpham E on E.MaPDG = A.MaPDG "+
				 "JOIN SanPhamDauGia B "+ 
				"on A.MaPDG = B.MaPDG JOIN HinhAnhSP C "+
				"on B.MaSP = C.MaSP "+
				"where B.MaSP in ( select top 1 MaSP from SanPhamDauGia where MaPDG = A.MaPDG) "+
				"and c.IDHinhAnh=(select top 1 IDHinhAnh from HinhAnhSP where MaSP = B.MaSP) "+ 
				"and ThoiGianKetThuc-  (SELECT GETDATE() AS CurrentDateTime) >0 "+
				"and B.MaLoaiSP ="+maLSP+ " "+
				"group by A.MaPDG,A.TenPDG,A.GiaDeNghi,A.ThoiGianBatDau,A.ThoiGianKetThuc,C.TenHinhAnh "+
				"order by thoigianbatdau desc,A.MaPDG OFFSET "+offsetRow+" ROWS FETCH NEXT "+recordsPerPage+" ROWS ONLY";
		
		
		ResultSet rs = null;
		System.out.println("123123123123123123123 "+ sql);
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<PhienDauGia> list = new ArrayList<PhienDauGia>();
		PhienDauGia pDG;
		try {
			while(rs.next()){
				pDG = new PhienDauGia();
				pDG.setMaPDG(rs.getString("mapdg"));
				pDG.setTenPDG(rs.getString("TenPDG"));
				pDG.setGiaDeNghi(rs.getString("GiaDeNghi"));
				pDG.setThoiGianKetThuc(rs.getString("thoigianketthuc"));
				
				
				// bien doi time
				//HH converts hour in 24 hours format (0-23), day calculation
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				
				// lấy ngày giờ hiện tại
				String curentDate = format.format(new Date());
//				System.out.println("vvv"+curentDate);
				
				// lấy ngày giờ kết thúc đấu giá
				String temp = pDG.getThoiGianKetThuc();
				temp = temp.replace("-", "/");
//				System.out.println("temp"+temp);

				

				Date d1 = null; // thời gian kết thúc
				Date d2 = null; // thời gian bắt đầu
				
				// convert String to datetime: format("yyyy/MM/dd HH:mm:ss")
				d1 = format.parse(temp);
				d2 = format.parse(curentDate);
				
				//in milliseconds
				long diff = d1.getTime() - d2.getTime();
				
				String countdown = String.valueOf(diff);
				System.out.println(diff);
				System.out.println("SFSDFSDFSDFD"+countdown);
				pDG.setCountDown(countdown);
				
				
				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				
				System.out.println("phien " + rs.getString("TenPDG") + " ");
				System.out.print(diffDays + " days, ");
				System.out.print(diffHours + " hours, ");
				System.out.print(diffMinutes + " minutes, ");
				System.out.print(diffSeconds + " seconds.\n");

				
				
				
				pDG.setSoNguoiDauGia(rs.getString("tong"));
				
				if(rs.getString("giahientai")==null){
					pDG.setGiaHienTai(pDG.getGiaDeNghi());
				}
				else
				pDG.setGiaHienTai(rs.getString("giahientai"));
				
				// lay hinh anh
				pDG.setHinhAnh(rs.getString("TenHinhAnh"));
				
				
				
				list.add(pDG);
			}
			rs = stmt.executeQuery("SELECT count(*) from phiendaugia a join sanphamdaugia b on a.MaPDG = b.MaPDG "+
					"join loaisanpham c on c.MaLoaiSP = b.MaLoaiSP where c.maloaisanpham = "+maLSP+" "
					);
			
			if(rs.next()) {
				this.noOfRecords = rs.getInt(1);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}


	//chi tiet phien dau gia
	public ArrayList<PhienDauGia>  getPDGofMaPDG(String maPDG) {
		connect();

		String sql = "select top 1 R.TenNguoiDung,A.IDNguoiTao,A.MaPDG,A.TenPDG,A.GiaDeNghi,A.ThoiGianBatDau,A.ThoiGianKetThuc,C.TenHinhAnh, "+
				 		"(count( DISTINCT IDNguoiThamGia))as tong,max(GiaTra)as giahientai "+
						"from PhienDauGia A join NguoiDung R on R.ID = A.IDNguoiTao full OUTER JOIN lichsudaugiasanpham E on E.MaPDG = A.MaPDG "+
						 "JOIN SanPhamDauGia B "+ 
						"on A.MaPDG = B.MaPDG JOIN HinhAnhSP C "+
						"on B.MaSP = C.MaSP "+
						"where B.MaSP in ( select top 1 MaSP from SanPhamDauGia where MaPDG = A.MaPDG) "+
						"and c.IDHinhAnh=(select top 1 IDHinhAnh from HinhAnhSP where MaSP = B.MaSP) "+ 
						"and ThoiGianKetThuc-  (SELECT GETDATE() AS CurrentDateTime) >0 "+
						"and A.MaPDG = "+maPDG+" "+
						"group by A.MaPDG,A.TenPDG,A.GiaDeNghi,A.ThoiGianBatDau,A.ThoiGianKetThuc,C.TenHinhAnh,R.TenNguoiDung,A.IDNguoiTao order by tong desc,A.MaPDG ";
						
		ResultSet rs = null;
		System.out.println("123123123123123123123333333333 555555555 "+ sql);
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ArrayList<PhienDauGia> list = new ArrayList<PhienDauGia>();
		PhienDauGia pDG;
		try {
			while(rs.next()){
				pDG = new PhienDauGia();
				pDG.setMaPDG(rs.getString("mapdg"));
				pDG.setTenPDG(rs.getString("TenPDG"));
				pDG.setGiaDeNghi(rs.getString("GiaDeNghi"));
				pDG.setThoiGianBatDau(rs.getString("ThoiGianBatDau"));
				pDG.setThoiGianKetThuc(rs.getString("ThoiGianKetThuc"));
				pDG.setTenNguoiTao(rs.getString("TenNguoiDung"));
				// bien doi time
				//HH converts hour in 24 hours format (0-23), day calculation
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				
				// lấy ngày giờ hiện tại
				String curentDate = format.format(new Date());
//				System.out.println("vvv"+curentDate);
				
				// lấy ngày giờ kết thúc đấu giá
				String temp = pDG.getThoiGianKetThuc();
				temp = temp.replace("-", "/");
//				System.out.println("temp"+temp);

				

				Date d1 = null; // thời gian kết thúc
				Date d2 = null; // thời gian bắt đầu
				
				// convert String to datetime: format("yyyy/MM/dd HH:mm:ss")
				d1 = format.parse(temp);
				d2 = format.parse(curentDate);
				
				//in milliseconds
				long diff = d1.getTime() - d2.getTime();
				
				String countdown = String.valueOf(diff);
				System.out.println(diff);
				System.out.println("SFSDFSDFSDFD"+countdown);
				pDG.setCountDown(countdown);
				
				
				long diffSeconds = diff / 1000 % 60;
				long diffMinutes = diff / (60 * 1000) % 60;
				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				
				System.out.println("phien " + rs.getString("TenPDG") + " ");
				System.out.print(diffDays + " days, ");
				System.out.print(diffHours + " hours, ");
				System.out.print(diffMinutes + " minutes, ");
				System.out.print(diffSeconds + " seconds.\n");

				
				
				
				pDG.setSoNguoiDauGia(rs.getString("tong"));
				
				if(rs.getString("giahientai")==null){
					pDG.setGiaHienTai(pDG.getGiaDeNghi());
				}
				else
				pDG.setGiaHienTai(rs.getString("giahientai"));
				pDG.setHinhAnh(rs.getString("TenHinhAnh"));
				// lay hinh anh
				
				
				
				
				list.add(pDG);
			}
			rs = stmt.executeQuery("SELECT count(*) from phiendaugia");
			
			if(rs.next()) {
				this.noOfRecords = rs.getInt(1);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
				
				
	}

	
	
	

	
}
