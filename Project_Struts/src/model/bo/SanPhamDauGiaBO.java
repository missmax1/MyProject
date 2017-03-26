package model.bo;

import java.util.ArrayList;

import model.bean.SanPhamDauGia;
import model.dao.SanPhamDauGiaDAO;

public class SanPhamDauGiaBO {
	SanPhamDauGiaDAO sanPhamDauGiaDAO = new SanPhamDauGiaDAO();

	public ArrayList<SanPhamDauGia> getListSanPham(String maPDG) {
		// TODO Auto-generated method stub
		return sanPhamDauGiaDAO.getListSanPham(maPDG);
	}
	
}
