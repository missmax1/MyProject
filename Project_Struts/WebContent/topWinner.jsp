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
  <script src="sweetalert-master/dist/sweetalert.min.js"></script>
  <link rel="stylesheet" href="sweetalert-master/dist/sweetalert.css"/>
</head>
<body style="background-color: #EBEBEB">

	<div class="container">
			<div class="col-lg-8">
				<a href="trang-chu.do"><img src="img/sohot_logo.png" alt=""></a>
			</div>
			<div class="col-lg-4">
        <div class="">
        
       
        
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
         
          
          <logic:iterate name="lsDGSPForm" property="listLoaiSP" id="loaiSP">
			 		
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
      <li><a href="/topWiner.do">TOP WINNERS</a></li>
    </ul>
  </div>
</nav>

<div class="container">
	<div class="col-md-6 col-md-offset-3">
	<table class="table table-striped" style="border: 1px">
    <thead>
      <tr>
      	<th>ID</th>
        <th>Thành viên</th>
        <th>Số lần tham gia</th>
        <th>Số lần thắng</th>
      </tr>
    </thead>
    <tbody>
    <bean:size id="a" name="lsDGSPForm" property="listTopWinner"/>
   	<% int count=1; %>
    <logic:iterate name="lsDGSPForm" property="listTopWinner" id="topWinner" >
      <tr>
      	<td> <%=count++ %> </td>
        <td><bean:write name="topWinner" property="tenNguoiDung"/></td>
        <td><bean:write name="topWinner" property="soLanThamGia"/></td>
        <td><bean:write name="topWinner" property="soLanThang"/></td>
      </tr>
    </logic:iterate>
    </tbody>
  </table>
</div>
</div> 
</hr>

  




<!-- Footer -->
<footer class="container-fluid bg-4 text-center">
   <h3>NHÓM 3</h3>
</footer>
		
									
									


</body>
</html>