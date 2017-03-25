var slideIndex = 1;
					showDivs(slideIndex);

					function plusDivs(n) {
					  showDivs(slideIndex += n);
					}

					function currentDiv(n) {
					  showDivs(slideIndex = n);
					}

					function showDivs(n) {
					  var i;
					  var x = document.getElementsByClassName("mySlides");
					  var dots = document.getElementsByClassName("demo");
					  if (n > x.length) {slideIndex = 1}
					  if (n < 1) {slideIndex = x.length}
					  for (i = 0; i < x.length; i++) {
						 x[i].style.display = "none";
					  }
					  for (i = 0; i < dots.length; i++) {
						 dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
					  }
					  x[slideIndex-1].style.display = "block";
					  dots[slideIndex-1].className += " w3-opacity-off";
					}
					
					function xoa(){
							if(confirm("Bạn có muốn xóa dữ liệu này"))
								{	
									alert("Không thế xóa vì vẫn còn sản phẩm ở hãng sản xuất này");
									return true;
									
								}
							else{	alert("Xóa Thất Bại");
									return false;
								}
							
						}
		function myFunction(){
			var input,filter,table,tr,td,i,th;
			input=document.getElementById("timkiem");
			filter=input.value.toUpperCase();
			table=document.getElementById("example2");
			tr=table.getElementsByTagName("tr");
			for(i=0;i<tr.length;i++){
				td=tr[i].getElementsByTagName("td")[1];
				if(td){
					if(td.innerHTML.toUpperCase().indexOf(filter)>-1){
						tr[i].style.display="";
					}else{
						tr[i].style.display="none";
					}
				}
				
			}
			
			
}

function Form(){
			var a=document.forms["reg"]["madm"].value;
			var b=document.forms["reg"]["tendm"].value;
			if((a==null||a=="")&&(b==null||b=="")){
				alert("Xin nhập Mã DM và Tên DM");
				return false;
			}
			if((a=='DM001')&&(b==null||b=="")){
				alert("Trùng Mã DM, Xin nhập Tên DM");
				
				return false;
			}
			if(a=='DM001'){
				alert("Trùng Mã DM");
				return false;
			}
			
			if(a==null||a==""){
				alert("Xin nhập Mã DM");
				return false;
			}
			
			if(b==null||b==""){
				alert("Xin nhập Tên DM");
				return false;
			}
			if(a.length<3){
				alert("Mã phailon hon 3 ki tu");
				return false;
			}
			if(b.length<1){
				alert("Tên DM phailon hon 1 ki tu");
				return false;
			}
		
		}
		
		function Form2(){
			var a=document.forms["reg"]["madm"].value;
			var b=document.forms["reg"]["tendm"].value;
			if((a==null||a=="")&&(b==null||b=="")){
				alert("Xin nhập Mã DM và Tên DM");
				return false;
			}
			if(a==null||a==""){
				alert("Xin nhập Mã DM");
				return false;
			}
			
			if(b==null||b==""){
				alert("Xin nhập Tên DM");
				return false;
			}
			if(b!=null||b!=""){
				alert("Sửa danh mục thành công");
				return true;
			}
		
		}