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
      <li><a href="topWiner.do">TOP WINNERS</a></li>
    </ul>
  </div>
</nav>

<div class="container">
 <div id="spvd">Sản phẩm vừa đăng</div>
			<logic:iterate name="phienDauGiaForm" property="listNewPDG" id="pDG">
			  			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
						      <div class="thumbnail" style="background-color: #FFFFFF">
						        <bean:define id="hinhAnh" name="pDG" property="hinhAnh"></bean:define>
			    				<html:img src="img/${hinhAnh}" style="height:250px ; width:250px"></html:img>
						        <div style="padding: 10px;">
						          <p><strong>
						          	<html:link href="#">
								 		<bean:write name="pDG" property="tenPDG"/>
								 	</html:link>	
						          </strong>
						          </p>
						          <b><p>Số người tham gia đấu giá: <bean:write name="pDG" property="soNguoiDauGia"/></p>
						          <p>Giá khởi điểm: <bean:write name="pDG" property="giaDeNghi"/> VND</p>
									</b>
						          <div class="text-center">
			
						              <p><img alt="" src="img/glasshour.jpg" style	="height: 18px"><b>
						              
						             		
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
						              		
						              		
						              		
						               </p>
						               
						              <p>Giá hiện tại: <span class="label label-default">
							              <bean:write name="pDG" property="giaHienTai"/>
							              </span>
						              </p>
			              <bean:define id="maPDG" name="pDG" property="maPDG"></bean:define>
			              <html:link styleClass="btn btn-danger" action="/chitiet?maPDG=${maPDG}">Đấu giá</html:link>
			          </div>
			        </div>
			      </div>
			    </div>
					  							  
			    
			    
			  	</logic:iterate>
			  	
   </div> 
   </hr>

  <div class= "container">
  <div id="spvd">Sản phẩm nhiều người quan tâm</div>
  	<logic:iterate name="phienDauGiaForm" property="listPhienDauGia" id="pDG">
  			<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
			      <div class="thumbnail" style="background-color: #FFFFFF">
			      <bean:define id="hinhAnh" name="pDG" property="hinhAnh"></bean:define>
			     <html:img src="img/${hinhAnh}" style="height:250px ; width:250px"></html:img>
			        <div style="padding: 10px;">
			          <p><strong>
			          	<html:link href="#">
					 		<bean:write name="pDG" property="tenPDG"/>
					 	</html:link>	
			          </strong>
			          </p>
			          <p>Số người tham gia đấu giá: <bean:write name="pDG" property="soNguoiDauGia"/></p>
			          <p>Giá khởi điểm: <bean:write name="pDG" property="giaDeNghi"/> VND</p>
			          <div class="text-center">
							
			           <p><img alt="" src="img/glasshour.jpg" style="height: 18px"><b>
			              
			             		
			              		<bean:define id="id" name="pDG" property="maPDG"></bean:define>
			              		<span id="countdowns${id}" style="background-color: red;  color: white"></span>  
			              		<input type="hidden" id="s${id}" name="myText" value="<bean:write name="pDG" property="countDown"/>">
			              		
			              				<script>
									var upgradeTimes${id} = document.getElementById("s${id}").value;
									var secondss${id} = upgradeTimes${id}/1000;
										function timers${id}() {
										    console.log(secondss${id});
											var days        = Math.floor(secondss${id}/24/60/60);
										    var hoursLeft   = Math.floor((secondss${id}) - (days*86400));
										    var hours       = Math.floor(hoursLeft/3600);
										    var minutesLeft = Math.floor((hoursLeft) - (hours*3600));
										    var minutes     = Math.floor(minutesLeft/60);
										    var remainingSeconds = secondss${id} % 60;
										    if (remainingSeconds < 10) {
										        remainingSeconds = "0" + remainingSeconds; 
										    }
										    var display = days + ":" + hours + ":" + minutes + ":" + remainingSeconds;
										    console.log(display);
										    document.getElementById('countdowns${id}').innerHTML = display;
										    if (secondss${id} < 1) {
										        clearInterval(countdownTimer${id});
										        document.getElementById('countdowns${id}').innerHTML = "Completed";
										    } else {
										        secondss${id}--;
										    }
										}
										var countdownTimers${id} = setInterval('timers${id}()', 1000);
										</script>
			              		
			              		
			              		
			               </p>
			               
			              <p>Giá hiện tại: <span class="label label-default">
				              <bean:write name="pDG" property="giaHienTai"/>
				              </span>
			              </p>
              <button class="btn btn-danger">Đấu giá</button>
          </div>
        </div>
      </div>
    </div>
		  							  
    
    
  	</logic:iterate>
  </div>
  
    <div class="text-center">
      <nav aria-label="...">
        <ul class="pagination">
          <ul class="pagination pull-right">
						    
						    <bean:define id="noOfPages" name="phienDauGiaForm" property="noOfPages"></bean:define>
						    
						    <logic:lessEqual name="phienDauGiaForm" property="currentPage" value="1">
							 	<li class="page-item disabled"><span class="page-link">Previous</span></li>
							</logic:lessEqual>
							<logic:greaterThan name="phienDauGiaForm" property="currentPage" value="1">
							 	<li class="page-item">
								    <logic:empty name="phienDauGiaForm" property="searchText">
								    	<bean:define id="currentPage" name="phienDauGiaForm" property="currentPage"></bean:define>
					                	<html:link action="/trang-chu?page=${currentPage-1}" styleClass="page-link">
					                		Previous
					                	</html:link>
								    </logic:empty>
								    <logic:notEmpty name="phienDauGiaForm" property="searchText">
								    	<bean:define id="currentPage" name="phienDauGiaForm" property="currentPage"></bean:define>
								    	<bean:define id="searchText" name="phienDauGiaForm" property="searchText"></bean:define>
					                	<html:link action="/trang-chu?searchText=${searchText}&page=${currentPage-1}" styleClass="page-link">
					                		Previous
					                	</html:link>
								    </logic:notEmpty>
				                </li>
							 </logic:greaterThan>
						    
						    <logic:greaterThan name="phienDauGiaForm" property="currentPage" value="1">
							    <li class="page-item">
								    <logic:empty name="phienDauGiaForm" property="searchText">
								    	<bean:define id="currentPage" name="phienDauGiaForm" property="currentPage"></bean:define>
					                	<html:link action="/trang-chu?page=${currentPage-1}" styleClass="page-link">
					                		${currentPage-1}
					                	</html:link>
								    </logic:empty>
								    <logic:notEmpty name="phienDauGiaForm" property="searchText">
								    	<bean:define id="currentPage" name="phienDauGiaForm" property="currentPage"></bean:define>
								    	<bean:define id="searchText" name="phienDauGiaForm" property="searchText"></bean:define>
					                	<html:link action="/trang-chu?searchText=${searchText}&page=${currentPage-1}" styleClass="page-link">
					                		${currentPage-1}
					                	</html:link>
								    </logic:notEmpty>
				                </li>
						    </logic:greaterThan>
						    
						    <li class="page-item active">
							    <span class="page-link">
									<bean:write name="phienDauGiaForm" property="currentPage"/>
								</span>
							</li>
							
							<logic:lessThan name="phienDauGiaForm" property="currentPage" value="${noOfPages}">
							    <li class="page-item">
								    <logic:empty name="phienDauGiaForm" property="searchText">
								    	<bean:define id="currentPage" name="phienDauGiaForm" property="currentPage"></bean:define>
					                	<html:link action="/trang-chu?page=${currentPage+1}" styleClass="page-link">
					                		${currentPage+1}
					                	</html:link>
								    </logic:empty>
								    <logic:notEmpty name="phienDauGiaForm" property="searchText">
								    	<bean:define id="currentPage" name="phienDauGiaForm" property="currentPage"></bean:define>
								    	<bean:define id="searchText" name="phienDauGiaForm" property="searchText"></bean:define>
					                	<html:link action="/trang-chu?searchText=${searchText}&page=${currentPage+1}" styleClass="page-link">
					                		${currentPage+1}
					                	</html:link>
								    </logic:notEmpty>
				                </li>
			                </logic:lessThan>
			                
			                <logic:greaterEqual name="phienDauGiaForm" property="currentPage" value="${noOfPages}">
							 	<li class="page-item disabled"><span class="page-link">Next</span></li>
							</logic:greaterEqual>
							<logic:lessThan name="phienDauGiaForm" property="currentPage" value="${noOfPages}">
							 	<li class="page-item">
								    <logic:empty name="phienDauGiaForm" property="searchText">
								    	<bean:define id="currentPage" name="phienDauGiaForm" property="currentPage"></bean:define>
					                	<html:link action="/trang-chu?page=${currentPage+1}" styleClass="page-link">
					                		Next
					                	</html:link>
								    </logic:empty>
								    <logic:notEmpty name="phienDauGiaForm" property="searchText">
								    	<bean:define id="currentPage" name="phienDauGiaForm" property="currentPage"></bean:define>
								    	<bean:define id="searchText" name="phienDauGiaForm" property="searchText"></bean:define>
					                	<html:link action="/trang-chu?searchText=${searchText}&page=${currentPage+1}" styleClass="page-link">
					                		Next
					                	</html:link>
								    </logic:notEmpty>
				                </li>
							</logic:lessThan>
				                
						</ul>
        </ul>
      </nav>
    </div>



<!-- Footer -->
<footer class="container-fluid bg-4 text-center">
   <h3>NHÓM 3</h3>
</footer>
		
									
									


</body>
</html>