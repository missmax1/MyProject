package model.bo;

import java.util.ArrayList;

import model.bean.PhienDauGia;
import model.dao.PhienDauGiaDAO;

public class PhienDauGiaBO {
	
	PhienDauGiaDAO phienDGDAO = new PhienDauGiaDAO();
	
	public ArrayList<PhienDauGia> getListPDG(String page){
		return phienDGDAO.getListPDG(page);
		
	}

	public int getNoOfRecords() {
		// TODO Auto-generated method stub
		return phienDGDAO.getNoOfRecords();
	}

	public ArrayList<PhienDauGia> getListNewPDG(String page) {
		// TODO Auto-generated method stub
		return phienDGDAO.getListNewPDG(page);
	}

	public ArrayList<PhienDauGia> getListPDGTheoMa(String maLSP, String page) {
		return phienDGDAO.getListPDGTheoMa(maLSP,page);
	}

	public ArrayList<PhienDauGia> getListPDG(String searchText, String page) {
		 
		return phienDGDAO.getListPDG(searchText,page);
	}

	public ArrayList<PhienDauGia> getPDGofMaPDG(String maPDG) {
		return phienDGDAO.getPDGofMaPDG(maPDG);
	}
	
	
}
