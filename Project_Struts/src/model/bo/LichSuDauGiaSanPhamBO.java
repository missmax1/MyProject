package model.bo;

import java.util.ArrayList;

import model.bean.LichSuDauGiaSanPham;
import model.bean.NguoiDung;
import model.dao.LichSuDauGiaSanPhamDAO;
import model.dao.NguoiDungDAO;

public class LichSuDauGiaSanPhamBO {
	LichSuDauGiaSanPhamDAO lichSuDauGiaSanPhamDAO = new LichSuDauGiaSanPhamDAO();
	
	public ArrayList<LichSuDauGiaSanPham> getlistLSDGSP(String maPDG){
		return lichSuDauGiaSanPhamDAO.getlistLSDGSP(maPDG);
		
	}


}
