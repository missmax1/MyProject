<%@page import="model.bean.LoaiSanPham"%>
<%@page import="model.bean.NguoiDung"%>
<%@page import="common.StringProcess"%>
<%@page import="model.bean.PhienDauGia"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
  <title>Đấu giá Online</title>
  
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link rel="stylesheet" href="css/style1.css"/>
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
</head>
<body style="background-color: #EBEBEB">

	<div class="container">
			<div class="col-lg-8">
				<a href="trang-chu.do"><img src="img/sohot_logo.png" alt=""></a>
			</div>
			<div class="col-lg-4">
      			<div class="dropdown1">
				  <p class="dropbtn">User 01</p>
				  <div class="dropdown1-content">
				    <a href="#">Quản lý</a>
				    <a href="#">Cập nhật thông tin</a>
				    <a href="#">Đăng xuất</a>
				  </div>
				</div>
      			
      			
			    <div class="input-group" style="margin: auto;">
			      <input type="text" class="form-control" placeholder="Nhập từ khóa cần tìm...">
			      <span class="input-group-btn">
			        <button class="btn btn-default" type="submit">Tìm kiếm</button>
			      </span>
			    </div><!-- /input-group -->
			</div><!-- /.col-lg-4 -->
	</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    
    <ul class="nav navbar-nav">
      <li class="active"><a href="trang-chu.do">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Danh mục sản phẩm đấu giá<span class="caret"></span></a>
        <ul class="dropdown-menu">
         
          
          <logic:iterate name="nguoiDungForm" property="listLoaiSP" id="loaiSP">
			 		
					 <li>
					 <bean:define id="maLoaiSP" name="loaiSP" property="maLoaiSP"></bean:define>
					 
					 <html:link action="/listSPTheoMa?maLoaiSP=${maLoaiSP}">
					 	<bean:write name="loaiSP" property="tenLoaiSP"/>
					 </html:link>	
	                </li>
	 		</logic:iterate>

        </ul>
      </li>
      <li><a href="#">Sản phẩm được quan tâm</a></li>
      <li><a href="#">Kết quả đấu giá</a></li>
      <li><a href="#">TOP WINNERS</a></li>
    </ul>
  </div>
</nav>


 	<div class="contaniner">
			<div class="qltk col-md-6 col-md-offset-3">

							<h3><i>Quản lý tài khoản</i></h3>
							
							<html:form styleClass="form-horizontal" action="/capNhatThongTin" method="post">
							  <div class="form-group">
							    <label for="inputEmail3" class="col-sm-3 control-label">Tên đăng nhập</label>
							    <div class="col-sm-8">
							    <html:text property="tenTaiKhoan" styleClass="form-control" readonly="true"></html:text>
							     
							    </div>
							    <div class="col-sm-offset-4">*</div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-3 control-label">Mật khẩu</label>
							    <div class="col-sm-8">
							      <html:password property="tenTaiKhoan" styleClass="form-control"></html:password>
							    </div>
							      <div class="col-sm-offset-4">*</div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-3 control-label">Xác nhận mật khẩu</label>
							    <div class="col-sm-8">
							      <input type="password" class="form-control" id="idcpass"  placeholder="Xác nhận mật khẩu" value="*****">
							    </div>
							     <div class="col-sm-offset-4">*</div>
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-3 control-label">Họ và tên</label>
							    <div class="col-sm-8">
							      <html:text property="tenNguoiDung" styleClass="form-control" ></html:text>
							    </div>
							    
							  </div>
								
							<div class="form-group">
						    <label for="inputPassword3" class="col-sm-3 control-label">Địa chỉ</label>
							    <div class="col-sm-8">
							      <html:textarea property="diaChi" style="width:390px" styleClass="form-control"></html:textarea>
							    </div>
						    </div>
					    	
							 <div class="form-group">
							    <label for="inputPassword3" class="col-sm-3 control-label">Ngày sinh</label>
							    <div class="col-sm-8">
							      <input type="date" class="form-control" id="idngaysinh" placeholder="Nhập họ tên">
							    </div>
							    
							  </div>
								<div class="form-group">
								    <label for="inputPassword3" class="col-sm-3 control-label">Email</label>
								    <div class="col-sm-8">
								      <input type="email" class="form-control" id="idngaysinh" placeholder="Nhập email">
								    </div>
							    
							  </div>


							<div class="form-group">
							    <label for="inputPassword3" class="col-sm-3 control-label">Điện thoại</label>
							    <div class="col-sm-8">
							      <input type="text" class="form-control" id="idphone"  pattern="
							      \d{10}" title="Điện thoại không đúng định dạng" placeholder="Nhập số điện thoại" >
							    </div>
							     
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-3 control-label">CMND</label>
							    <div class="col-sm-8">
							      <html:text property="cMND" styleClass="form-control" ></html:text>
							    </div>
							     
							  </div>
							  <div class="form-group">
							    <label for="inputPassword3" class="col-sm-3 control-label">Số dư tài khoản</label>
							    <label for="inputPassword3" class="col-sm-5 control-label"> <bean:write name="nguoiDungForm" property="soDuTaiKhoan"/> VND</label>
							    
							     <button type="button" class="btn btn-primary">Nạp tiền</button>
							     
							  </div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-3 control-label">Giới tính</label>
								<div class="col-sm-6">
					    			<html:radio property="gioiTinh" value="1">Nam</html:radio>
            						<html:radio property="gioiTinh" value="0" >Nữ</html:radio>
				    			</div>
							</div>	

							  <div class="form-group">
							    <div class="col-sm-offset-4 col-sm-6">
							       <html:submit styleClass="btn btn-primary" property="submit" value="submit">Update</html:submit>
							      <button type="button" class="btn btn-primary" onclick="history.go(-1);" >Back</button>
							      
							    </div>
							  </div>
							</html:form>
			

			</div>

 
 
 </div>
<!-- Footer -->

		
									
									


</body>
</html>