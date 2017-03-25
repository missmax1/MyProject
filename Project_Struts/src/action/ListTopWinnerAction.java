package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.LichSuDauGiaSanPhamForm;
import model.bean.LoaiSanPham;
import model.bean.NguoiDung;
import model.bo.LichSuDauGiaSanPhamBO;
import model.bo.LoaiSanPhamBO;
import model.bo.NguoiDungBO;

public class ListTopWinnerAction extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		LichSuDauGiaSanPhamForm lichSuDauGiaSanPhamForm = (LichSuDauGiaSanPhamForm) form;
		
		//tao menu ds loai sp
		LoaiSanPhamBO loaiSanPhamBO = new LoaiSanPhamBO();
		ArrayList<LoaiSanPham> loaiSP;
		loaiSP = loaiSanPhamBO.getlistLoaiSP();
		lichSuDauGiaSanPhamForm.setListLoaiSP(loaiSP);
		
		// lay ds top ng tham gia va tôp nguoi chien thang
		LichSuDauGiaSanPhamBO lichSuDauGiaSPBO = new LichSuDauGiaSanPhamBO();
		NguoiDungBO nguoiDungBO = new NguoiDungBO();
		ArrayList<NguoiDung> listTopWinner;
		listTopWinner = nguoiDungBO.getlistTopWinner();
		lichSuDauGiaSanPhamForm.setListTopWinner(listTopWinner);
		
		
		return mapping.findForward("topWin");
		
	}

	
	
}
