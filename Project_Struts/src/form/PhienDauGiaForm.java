package form;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import model.bean.LichSuDauGiaSanPham;
import model.bean.LoaiSanPham;
import model.bean.PhienDauGia;

public class PhienDauGiaForm extends ActionForm{
	
	private String maLoaiSP;
	private ArrayList<LoaiSanPham> listLoaiSP;
	private ArrayList<PhienDauGia> listPhienDauGia;
	private ArrayList<PhienDauGia> listNewPDG;
	private ArrayList<LichSuDauGiaSanPham> listLSDGSP;
	private String page;
	private String noOfPages;
	private String currentPage;
	private String searchText;
	private String maPDG;
	
	
	
	
	

	public ArrayList<LichSuDauGiaSanPham> getListLSDGSP() {
		return listLSDGSP;
	}
	public void setListLSDGSP(ArrayList<LichSuDauGiaSanPham> listLSDGSP) {
		this.listLSDGSP = listLSDGSP;
	}
	public String getMaPDG() {
		return maPDG;
	}
	public void setMaPDG(String maPDG) {
		this.maPDG = maPDG;
	}
	public ArrayList<PhienDauGia> getListNewPDG() {
		return listNewPDG;
	}
	public void setListNewPDG(ArrayList<PhienDauGia> listNewPDG) {
		this.listNewPDG = listNewPDG;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getNoOfPages() {
		return noOfPages;
	}
	public void setNoOfPages(String noOfPages) {
		this.noOfPages = noOfPages;
	}
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getMaLoaiSP() {
		return maLoaiSP;
	}
	public void setMaLoaiSP(String maLoaiSP) {
		this.maLoaiSP = maLoaiSP;
	}
	public ArrayList<LoaiSanPham> getListLoaiSP() {
		return listLoaiSP;
	}
	public void setListLoaiSP (ArrayList<LoaiSanPham> listLoaiSP) {
		this.listLoaiSP = listLoaiSP;
	}
	public ArrayList<PhienDauGia> getListPhienDauGia() {
		return listPhienDauGia;
	}
	public void setListPhienDauGia(ArrayList<PhienDauGia> listPhienDauGia) {
		this.listPhienDauGia = listPhienDauGia;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
}
