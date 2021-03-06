package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

import form.LichSuDauGiaSanPhamForm;
import form.PhienDauGiaForm;
import model.bean.LichSuDauGiaSanPham;
import model.bean.LoaiSanPham;
import model.bean.PhienDauGia;
import model.bean.SanPhamDauGia;
import model.bo.LichSuDauGiaSanPhamBO;
import model.bo.LoaiSanPhamBO;
import model.bo.PhienDauGiaBO;
import model.bo.SanPhamDauGiaBO;

public class ChiTietPDGAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
		PhienDauGiaForm phienDauGiaForm = (PhienDauGiaForm) form;
		
		
		LoaiSanPhamBO  loaiSPBO = new LoaiSanPhamBO();
		ArrayList<LoaiSanPham> loaiSanPhams = loaiSPBO.getlistLoaiSP();
		phienDauGiaForm.setListLoaiSP(loaiSanPhams);
		
		//lay mdpdg cua pdg 
		String maPDG = phienDauGiaForm.getMaPDG();
		PhienDauGiaBO phienDGBO = new PhienDauGiaBO();
		ArrayList<PhienDauGia> phienDG;
		phienDG =phienDGBO.getPDGofMaPDG(maPDG);
		phienDauGiaForm.setListPhienDauGia(phienDG);
		// lay list lich su tham gia dau gia tuong ung vs mapdg
		// những ng đã tham gia vào đấu giá
		LichSuDauGiaSanPhamBO lichSuDauGiaBO = new LichSuDauGiaSanPhamBO();
		ArrayList<LichSuDauGiaSanPham> listLSDGSP;
		listLSDGSP = lichSuDauGiaBO.getlistLSDGSP(maPDG);
		phienDauGiaForm.setListLSDGSP(listLSDGSP);
		// lay list các sản phẩm tương ứng với pdg đó
		SanPhamDauGiaBO sanPhamDauGiaBO = new SanPhamDauGiaBO();;
		ArrayList<SanPhamDauGia> listSanPham;
		listSanPham = sanPhamDauGiaBO.getListSanPham(maPDG);
		phienDauGiaForm.setListSanPham(listSanPham);
		
		return mapping.findForward("chitiet");
	}
	

}
