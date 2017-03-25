package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.PhienDauGiaForm;
import model.bean.LoaiSanPham;
import model.bean.PhienDauGia;
import model.bo.LoaiSanPhamBO;
import model.bo.PhienDauGiaBO;

public class TimKiemPDGAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
			PhienDauGiaForm phienDauGiaForm = (PhienDauGiaForm) form;
			// list các loại sản phẩm
			LoaiSanPhamBO  loaiSPBO = new LoaiSanPhamBO();
			ArrayList<LoaiSanPham> loaiSanPhams = loaiSPBO.getlistLoaiSP();
			phienDauGiaForm.setListLoaiSP(loaiSanPhams);
		
			ArrayList<PhienDauGia> listPDG;
			PhienDauGiaBO phienDGBO = new PhienDauGiaBO();
			
			String searchText = phienDauGiaForm.getSearchText();
			String page = phienDauGiaForm.getPage() != null ? phienDauGiaForm.getPage() : "1";
			System.out.println("pag"+page);
			if(searchText==null || searchText.length()==0){
				listPDG = phienDGBO.getListPDG(page);
			}
			else{
				listPDG = phienDGBO.getListPDG(searchText,page);
			}
			
			
			phienDauGiaForm.setListPhienDauGia(listPDG);
			
			int recordsPerPage = 4;
			int noOfRecords = phienDGBO.getNoOfRecords();
			int noOfPages = (int) Math.ceil((double)noOfRecords / recordsPerPage);
			
			phienDauGiaForm.setCurrentPage((page));
			phienDauGiaForm.setNoOfPages(Integer.toString(noOfPages));
		
		return mapping.findForward("list");
	}
	
	
}
