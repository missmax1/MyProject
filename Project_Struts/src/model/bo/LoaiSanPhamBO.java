package model.bo;

import java.util.ArrayList;

import model.bean.LoaiSanPham;
import model.dao.LoaiSanPhamDAO;
import sun.font.LayoutPathImpl;

public class LoaiSanPhamBO {

	LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO();
	
	public ArrayList<LoaiSanPham> getlistLoaiSP(){
		return loaiSanPhamDAO.getlistLoaiSP();
		
	}
		
}
