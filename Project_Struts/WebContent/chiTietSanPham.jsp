<%@page import="model.bean.LoaiSanPham"%>
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
  
    <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="bootstrap/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="bootstrap/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="dist/css/skins/_all-skins.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="plugins/iCheck/flat/blue.css">
  <!-- Morris chart -->
  <link rel="stylesheet" href="plugins/morris/morris.css">
  <!-- jvectormap -->
  <link rel="stylesheet" href="plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <!-- Date Picker -->
  <link rel="stylesheet" href="plugins/datepicker/datepicker3.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">
  <!-- bootstrap wysihtml5 - text editor -->
  <link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
	<link rel="stylesheet" href="dist/css/style.css">
  
  
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
				    <a href="capNhatThongTin.do">Cập nhật thông tin</a>
				    <a href="#">Đăng xuất</a>
				  </div>
				</div>
      			
      			 <html:form action="/timKiem" method="post">
				    <div class="input-group" style="margin: auto;">
				    <html:text property="searchText" styleClass="form-control" ></html:text>
				      <span class="input-group-btn">
				          <html:submit styleClass="btn btn-default">Search</html:submit>
				      </span>
				    </div><!-- /input-group -->
			    </html:form>
			</div><!-- /.col-lg-4 -->
	</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    
    <ul class="nav navbar-nav">
      <li class="active"><a href="trang-chu.do">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Danh mục sản phẩm đấu giá<span class="caret"></span></a>
        <ul class="dropdown-menu">
         
          
          <logic:iterate name="phienDauGiaForm" property="listLoaiSP" id="loaiSP">
			 		
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

	<logic:iterate name="phienDauGiaForm" property="listPhienDauGia" id="pDG">
	
	<div id="container" class="container" style="margin-top:50px;">
	<div class="col-sm-12">
		<div  style="border-bottom:1px dotted gray">
			<h2><bean:write name="pDG" property="tenPDG"/></h2>
		</div>
		
		<div class="col-sm-3" style="margin-top:20px">
		
					<div class="w3-content" class="col-sm-12">
					 <bean:define id="hinhAnh" name="pDG" property="hinhAnh"></bean:define>
			    	 <html:img src="img/${hinhAnh}" style="height:100% ; width:100%; border:1px solid gray"></html:img>
			    	 
					  <img class="mySlides" src="dist/img/a.jpg" style="width:100%;height:100%;border:1px solid gray">
					  <img class="mySlides" src="dist/img/anh1.jpg" style="width:100%;height:100%;border:1px solid gray">
					  <img class="mySlides" src="dist/img/anh2.jpg" style="width:100%;height:100%;border:1px solid gray">
					  <img class="mySlides" src="dist/img/anh4.jpg" style="width:100%;height:100%;border:1px solid gray">
					  <img class="mySlides" src="dist/img/anh3.jpg" style="width:100%;height:100%;border:1px solid gray">
					  <img class="mySlides" src="dist/img/anh5.jpg" style="width:100%;height:100%;border:1px solid gray">

					</div>	
		</div>
		
		<div class="col-sm-4" style="margin-top:20px">
			<ul class="list-group" style="">
				<li>ID: <b><bean:write name="pDG" property="maPDG"/></b></li>
				<li>Seller: <span style="color:red;"><bean:write name="pDG" property="tenNguoiTao"/></span></li>
					
				<li>Bắt đầu: <bean:write name="pDG" property="thoiGianBatDau"/> </li>
				<li>Kết thúc:<bean:write name="pDG" property="thoiGianKetThuc"/></li>
				<li><img alt="" src="img/donghocat.jpg" width="30px" height="20px"> 
				<b>
				
							<bean:define id="id" name="pDG" property="maPDG"></bean:define>
						              		<span id="countdown${id}" style="background-color: red;  color: white"></span>  
						              		<input type="hidden" id="${id}" name="myText" value="<bean:write name="pDG" property="countDown"/>">
						              		
						              				<script>
												var upgradeTime${id} = document.getElementById("${id}").value;
												var seconds${id} = upgradeTime${id}/1000;
													function timer${id}() {
													    console.log(seconds${id});
														var days        = Math.floor(seconds${id}/24/60/60);
													    var hoursLeft   = Math.floor((seconds${id}) - (days*86400));
													    var hours       = Math.floor(hoursLeft/3600);
													    var minutesLeft = Math.floor((hoursLeft) - (hours*3600));
													    var minutes     = Math.floor(minutesLeft/60);
													    var remainingSeconds = seconds${id} % 60;
													    if (remainingSeconds < 10) {
													        remainingSeconds = "0" + remainingSeconds; 
													    }
													    var display = days + ":" + hours + ":" + minutes + ":" + remainingSeconds;
													    console.log(display);
													    document.getElementById('countdown${id}').innerHTML = display;
													    if (seconds${id} < 1) {
													        clearInterval(countdownTimer${id});
													        document.getElementById('countdown${id}').innerHTML = "Completed";
													    } else {
													        seconds${id}--;
													    }
													}
													var countdownTimer${id} = setInterval('timer${id}()', 1000);
													</script>
				

				</b>
				</li>

				<li>Giá khởi điểm: <b><bean:write name="pDG" property="giaDeNghi"/></b></li>
				<li style="color:red"><b>Giá hiện tại:<h3><bean:write name="pDG" property="giaHienTai"/></h3></b>
					<i>Thuộc về:
						
						
						
					</i>
				</li>
				<li>
					<input class="form control" type="text" style="text-align:center;font-size:110%;" value=""> 
					<button type="button" class="btn btn-danger" style="width:20%;padding:3px" onclick="checkbid();">Bid</button>
				</li>
				
			</ul>
		</div>
		
		
		<div class="col-sm-5" >
			<div class="table-responsive">
			
				<table class="table" width="100%">
					<tr>
						<th><b>Số Tiền</b></th>
						<th><b>Thời gian</b></th>
						<th><b>Thành Viên</b></th>
					</tr>
					<logic:iterate name="phienDauGiaForm" property="listLSDGSP" id="lSDG">
					<tr>
					
						<td class="sotien"><bean:write name="lSDG" property="giaTra"/></td>
						<td class="sotien"><bean:write name="lSDG" property="thoiGian"/></td>
						<td ><bean:write name="lSDG" property="tenTaiKhoan"/></td>
					
					</tr>
					</logic:iterate>
				</table>
				
			</div>
			<br>
			<div style="text-align:right">
				Lượt xem: <b>12</b> | Tham gia: 
				<b> 
				<bean:size id="a" name="phienDauGiaForm" property="listLSDGSP" />
					<logic:empty name="phienDauGiaForm" property="listLSDGSP">0
					</logic:empty>
					<logic:notEmpty name="phienDauGiaForm" property="listLSDGSP">
					<bean:write name="a"/>
					</logic:notEmpty>
					
				 </b> <br>
				<a href="#" style="color:red">Xem tất cả »</a>
			</div>
			
		</div>
		
					 
	</div>
	<div>
		<div class="w3-row-padding w3-section" class="col-sm-12" style="margin:auto;">
			<div  class="col-sm-2" class="w3-col s4">
			  <img class="demo w3-opacity w3-hover-opacity-off" src="dist/img/a.jpg"  onclick="currentDiv(1)">
			</div>
			<div  class="col-sm-2" class="w3-col s4">
			  <img class="demo w3-opacity w3-hover-opacity-off" src="dist/img/anh1.jpg"  onclick="currentDiv(2)">
			</div>
			<div class="col-sm-2"class="w3-col s4">
			  <img class="demo w3-opacity w3-hover-opacity-off" src="dist/img/anh2.jpg"  onclick="currentDiv(3)">
			</div>
			<div class="col-sm-2"class="w3-col s4">
			  <img class="demo w3-opacity w3-hover-opacity-off" src="dist/img/anh4.jpg" onclick="currentDiv(4)">
			</div>
			<div class="col-sm-2"class="w3-col s4">
			  <img class="demo w3-opacity w3-hover-opacity-off" src="dist/img/anh3.jpg" onclick="currentDiv(5)">
			</div>
			<div class="col-sm-2"class="w3-col s4">
			  <img class="demo w3-opacity w3-hover-opacity-off" src="dist/img/anh5.jpg" onclick="currentDiv(6)">
			</div>
		</div>
			  
	</div>
	<logic:iterate id="sanPham" name="phienDauGiaForm" property="listSanPham">
	<div class="col-sm-12">
		<ul class="nav nav-tabs">
			<li class="active" ><a data-toggle="tab" href="#home" class="a"><bean:write name="sanPham" property="tenSanPham"/> </a></li>
			
			
		</ul>
		<div class="tab-content">
			<div style="background:#d9d9d9;margin-top:20px;" class="tab-pane fade in active" id="home">
				<b>- Sản phẩm 1 : <bean:write name="sanPham" property="tenSanPham"/> <br>
				- Tương thích với Android / iOS.</b>
				<div class="col-sm" class="container" style="background:#ffffd1;border:1px solid black;margin:10px;padding:10px;" >
					<b style="color:#ce6f1f">Bảo hành</b><br>
						NEW BH 3 tháng.

				</div>	
				<div class="col-sm" class="container" style="background:#ffffd1;border:1px solid black;margin:10px;padding:10px;" >
					<b style="color:#ce6f1f">Địa chỉ nhận hàng:</b><br>
					- Add: 114 Đường Số 2 Cư Xá Đô Thành P.4 Q.3<br>
					- ĐT : 0909.682.607<br>
					- Hoàng Chấn Cường<br>
					- TK Vietcombank : 0181001414722<br>
					- Phí ship hàng: thỏa thuận<br>
					- Ship COD sẽ = phí ship + thêm 10k<br>
					Làm việc : Từ thứ 2 đến thứ 7. Thời gian nhận hàng từ 8h30 đến 12h, chiều từ 13h30 đến 18h. <br>
					- Bạn nào thắng không nhận hàng vì lý do ABC gì đó vui lòng hoàn lại phí 20k để mình đấu giá lại.Thanks\<br>
					-Lưu ý : Thời gian nhận hàng 48h. Quá thời gian mà không nhận hàng sẽ bị ban nick<br><br>
					<img src="dist/img/sodo.jpg" style="width:100%">

				</div>
			</div>
			<div style="background:#d9d9d9;margin:20px;text-align:center;padding:50px"  class="tab-pane fade" id="menu1">
				<h2 style="color:red;margin-bottom:30px">Xiaomi Miband 2</h2>
				<br>
				<div style="">
					<h4 style="color:blue;text-align:left;margin-left:50px;padding:10px">
						Vòng Đeo Tay Xiaomi MiBand 2 Là Phiên Bản Nâng Cấp Mạnh Mẽ Từ Phiên Bản Mi Band 1S Với Màn Hình Cảm Ứng Tiện Lợi
					<p><br>Cải tiến lớn nhất trên Mi Band 2 là việc được trang bị màn hình LED hiển thị thông tin thay vì chỉ có một miếng kim loại gồ lên đầy khô kha
					
					<br>
					<br>
					Để 2 thiết bị ghép nối và trao đổi thông tin với nhau, smartphone cần cài đặt ứng dụng di động Mi Fit "chính chủ" Xiaomi.
					</h2>
				</div>
				
					<img src="dist/img/anh1.jpg" style="width:50%;margin:50px;"><br>
					<div style="">
					<h4 style="color:blue;text-align:left;margin-left:50px;padding:10px">
						Vòng Đeo Tay Xiaomi MiBand 2 Là Phiên Bản Nâng Cấp Mạnh Mẽ Từ Phiên Bản Mi Band 1S Với Màn Hình Cảm Ứng Tiện Lợi
					<p><br>Cải tiến lớn nhất trên Mi Band 2 là việc được trang bị màn hình LED hiển thị thông tin thay vì chỉ có một miếng kim loại gồ lên đầy khô kha
					<br>
					<br>
					Để 2 thiết bị ghép nối và trao đổi thông tin với nhau, smartphone cần cài đặt ứng dụng di động Mi Fit "chính chủ" Xiaomi.
					
					</h2>
				</div>
					<img src="dist/img/anh2.jpg" style="width:50%;margin:50px;"><br>
					<div style="">
					<h4 style="color:blue;text-align:left;margin-left:50px;padding:10px">
						Vòng Đeo Tay Xiaomi MiBand 2 Là Phiên Bản Nâng Cấp Mạnh Mẽ Từ Phiên Bản Mi Band 1S Với Màn Hình Cảm Ứng Tiện Lợi
					<p><br>Cải tiến lớn nhất trên Mi Band 2 là việc được trang bị màn hình LED hiển thị thông tin thay vì chỉ có một miếng kim loại gồ lên đầy khô kha
					<br>
					<br>
					Để 2 thiết bị ghép nối và trao đổi thông tin với nhau, smartphone cần cài đặt ứng dụng di động Mi Fit "chính chủ" Xiaomi.
					
					</h2>
				</div>
					<img src="dist/img/anh3.jpg" style="width:50%;margin:50px;"><br>
				
			</div>
		</div>
	</div>
	</logic:iterate>
	
	</div>
</logic:iterate>


<!-- Footer -->
<footer class="container-fluid bg-4 text-center">
   <h3>NHÓM 3</h3>
</footer>
		
									
									


</body>
</html>