package model.bo;

import java.util.ArrayList;

import model.bean.NguoiDung;
import model.dao.NguoiDungDAO;

public class NguoiDungBO {

	NguoiDungDAO nguoiDungDAO = new NguoiDungDAO();
	public boolean checkLogin(String tenDangNhap, String matKhau) {
		// TODO Auto-generated method stub
		return false;
	}

	public  NguoiDung getThongTinNguoiDung(String iD) {
		
		return nguoiDungDAO.getThongTinNguoiDung(iD);
	}

	public void capNhapThongTin(String iD, String tenNguoiDung, String gioiTinh, String diaChi,
			String cMND, String matKhau) {
		 nguoiDungDAO.capNhapThongTin(iD,tenNguoiDung,gioiTinh,diaChi,cMND,matKhau);
		
	}

	public ArrayList<NguoiDung> getlistTopWinner() {
		
		return nguoiDungDAO.getlistTopWinner();
	}

}
