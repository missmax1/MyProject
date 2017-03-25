package action;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.NguoiDungForm;
import form.PhienDauGiaForm;
import model.bean.LoaiSanPham;
import model.bean.NguoiDung;
import model.bo.LoaiSanPhamBO;
import model.bo.NguoiDungBO;

public class CapNhatTaiKhoanAction  extends Action{

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");

		NguoiDungForm nguoiDungForm = (NguoiDungForm) form;
		//lay danh sach cac loai san pham
		LoaiSanPhamBO  loaiSPBO = new LoaiSanPhamBO();
		ArrayList<LoaiSanPham> loaiSanPhams = loaiSPBO.getlistLoaiSP();
		nguoiDungForm.setListLoaiSP(loaiSanPhams);
		
		//lay danh sach nguoi dung
	
		NguoiDungBO nguoiDungBO = new NguoiDungBO();
		
		String iD = "2";
		
		if("submit".equals(nguoiDungForm.getSubmit())){					//nhan nut Xac nhan o trang Them sinh vien
			String tenTaiKhoan= nguoiDungForm.getTenTaiKhoan();
			String tenNguoiDung = nguoiDungForm.getTenNguoiDung();
			
			
			
			String gioiTinh = nguoiDungForm.getGioiTinh();
			String diaChi = nguoiDungForm.getDiaChi();
			
			
			
			String cMND = nguoiDungForm.getcMND();
			String soDuTaiKhoan = nguoiDungForm.getSoDuTaiKhoan();
			String matKhau = nguoiDungForm.getMatKhau();
			
			nguoiDungBO.capNhapThongTin(iD,tenNguoiDung,gioiTinh,diaChi,cMND,matKhau);
			return mapping.findForward("capNhatXong");
		} else {
		
		NguoiDung nguoiDung = nguoiDungBO.getThongTinNguoiDung(iD);
		nguoiDungForm.setTenNguoiDung(nguoiDung.getTenNguoiDung());
		nguoiDungForm.setGioiTinh(nguoiDung.getGioiTinh());
		nguoiDungForm.setNgaySinh(nguoiDung.getNgaySinh());
		nguoiDungForm.setDiaChi(nguoiDung.getDiaChi());
		nguoiDungForm.setcMND(nguoiDung.getcMND());
		nguoiDungForm.setTenTaiKhoan(nguoiDung.getTenTaiKhoan());
		nguoiDungForm.setSoDuTaiKhoan(nguoiDung.getSoDuTaiKhoan());
		nguoiDungForm.setMatKhau(nguoiDung.getMatKhau());
		nguoiDungForm.setLoaiTaiKhoan(nguoiDung.getLoaiTaiKhoan());
		System.out.println("azzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzs");
		return mapping.findForward("capNhat");
		}
	}

	
	
}
