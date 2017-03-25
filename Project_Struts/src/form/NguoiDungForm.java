package form;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.StringProcess;

import model.bean.LoaiSanPham;

/**
 * NguoiDungForm
 *
 * Version 1.0
 *
 * Date: Jan 21, 2015
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jan 21, 2015        	DaiLV2          Create
 */

public class NguoiDungForm extends ActionForm{
	
	private String iD;
	private String tenNguoiDung;
	private String gioiTinh;
	private String ngaySinh;
	private String diaChi;
	private String cMND;
	private String tenTaiKhoan;
	private String soDuTaiKhoan;
	private String matKhau;
	private String loaiTaiKhoan;
	private String thongBao;
	private ArrayList<LoaiSanPham> listLoaiSP;
	private String submit;
	
	
	
	


	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public ArrayList<LoaiSanPham> getListLoaiSP() {
		return listLoaiSP;
	}
	public void setListLoaiSP(ArrayList<LoaiSanPham> listLoaiSP) {
		this.listLoaiSP = listLoaiSP;
	}
	public String getThongBao() {
		return thongBao;
	}
	public void setThongBao(String thongBao) {
		this.thongBao = thongBao;
	}
	public String getiD() {
		return iD;
	}
	public void setiD(String iD) {
		this.iD = iD;
	}
	public String getTenNguoiDung() {
		return tenNguoiDung;
	}
	public void setTenNguoiDung(String tenNguoiDung) {
		this.tenNguoiDung = tenNguoiDung;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getcMND() {
		return cMND;
	}
	public void setcMND(String cMND) {
		this.cMND = cMND;
	}
	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}
	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}
	public String getSoDuTaiKhoan() {
		return soDuTaiKhoan;
	}
	public void setSoDuTaiKhoan(String soDuTaiKhoan) {
		this.soDuTaiKhoan = soDuTaiKhoan;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public String getLoaiTaiKhoan() {
		return loaiTaiKhoan;
	}
	public void setLoaiTaiKhoan(String loaiTaiKhoan) {
		this.loaiTaiKhoan = loaiTaiKhoan;
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

