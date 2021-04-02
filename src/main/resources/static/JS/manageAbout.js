/**
 * 
 */
$(document).ready(function(){
	$(".btnUpdateAbout").click(function(){
		var id1 = $("#id1").text();
		var self1 = $("#self1").text();
		var history1 = $("#history1").text();
		var hiring1 = $("#hiring1").text();
		
		//alert(id1 +"-"+ self1 +"-"+ history1 +"-"+ hiring1);
		$("#id2").val(id1);
		$("#self2").val(self1);
		$("#history2").val(history1);
		$("#hiring2").val(hiring1);
	});
	
	$("#btnUpdate").click(function(){
		var id2 = $("#id2").val();
		var self2 = $("#self2").val();
		var history2 = $("#history2").val();
		var hiring2 = $("#hiring2").val();
		
		if($.trim(self2)=="" || $.trim(history2)=="" || $.trim(hiring2)==""){
			alert("Vui lòng nhập đầy đủ thông tin !")
		}
		else {
			$.ajax({
				url:"/adminAjax/updateAbout",
				type:"GET",
				data:{
					id2:id2,
					self2:self2,
					history2:history2,
					hiring2:hiring2
				},
				success: function(){
					alert("Cập nhật thành công");
					currentURL = window.location.href;
					newURL = currentURL.replace("/manageAbout","/manageAbout");
					window.location = newURL;
				}
			})
		}
	});
	
});