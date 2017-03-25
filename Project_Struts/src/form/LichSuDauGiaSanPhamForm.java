package form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import model.bean.LichSuDauGiaSanPham;
import model.bean.LoaiSanPham;
import model.bean.NguoiDung;



public class LichSuDauGiaSanPhamForm extends ActionForm{
	
	private ArrayList<LichSuDauGiaSanPham> listLSDGSP;
	private ArrayList<NguoiDung> listTopWinner;
	private ArrayList<LoaiSanPham> listLoaiSP;
	
	
	
	public ArrayList<LoaiSanPham> getListLoaiSP() {
		return listLoaiSP;
	}

	public void setListLoaiSP(ArrayList<LoaiSanPham> listLoaiSP) {
		this.listLoaiSP = listLoaiSP;
	}

	public ArrayList<NguoiDung> getListTopWinner() {
		return listTopWinner;
	}

	public void setListTopWinner(ArrayList<NguoiDung> listTopWinner) {
		this.listTopWinner = listTopWinner;
	}

	public ArrayList<LichSuDauGiaSanPham> getListLSDGSP() {
		return listLSDGSP;
	}

	public void setListLSDGSP(ArrayList<LichSuDauGiaSanPham> listLSDGSP) {
		this.listLSDGSP = listLSDGSP;
	}
	
	
	
}
