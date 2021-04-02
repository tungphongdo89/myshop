/**
 * 
 */
$(document).ready(function(){
	
	//handle event update information
	$("#btnUpdateCustomer").click(function(){
		//alert("Hello world!");
		var username = $("#form-update #username").val();
	    var password = $("#form-update #password").val();
	    var address = $("#form-update #address").val();
	    var phone = $("#form-update #telephone").val();
	    var email = $("#form-update #email").val();
	    
	   
	    if($('#gender1').is(':checked')) {
	    	var gender = $("#form-update #gender1").val();
	    }
	    if($('#gender2').is(':checked')) {
	    	var gender = $("#form-update #gender2").val();
	    }
	    
	    if($.trim(username)=="" || $.trim(password)=="" || $.trim(address)=="" || $.trim(phone)=="" || $.trim(email)=="" || $.trim(gender)==""){
			alert("Bạn phải nhập đầy đủ thông tin");
		}
	    else {
	    	$.ajax({
				url:"/ajax/update",
				type:"GET",
				data:{
					username:username,
					password:password,
					address:address,
					telephone:phone,
					email:email,
					gender:gender
				},
				success: function(value){
					if(value=="true"){
						alert("Cập nhật thông tin thành công !");
						currentURL = window.location.href;
						newURL = currentURL.replace("/account","/account");
						window.location = newURL;
					}
					else{
						alert("cập nhật thất bại");
					}
						
				}
			})
		}
	});
	
	$("#btnResetPass2").click(function(){
		$("#form-update").hide();
		$("#form-reset-pass").show();
	})
	$("#comeback").click(function(){
		$("#form-update").show();
		$("#form-reset-pass").hide();
	})
	
	
	//handle event confirm password
		$("#rePass2").focusout(function(){
			var newPass = $("#newPass").val();
			var rePass = $("#rePass2").val();

			if($.trim(newPass) != $.trim(rePass)){
				alert("Nhập lại mật khẩu không khớp ! Vui lòng nhập lại");
			}
		});
	
	//handle event change password
	$("#btnSaveChangePass").click(function(){
		var oldPass = $("#oldPass").val();
		var newPass = $("#newPass").val();
		var rePass  = $("#rePass2").val();
		
		if($.trim(oldPass)=="" || $.trim(newPass)=="" || $.trim(rePass)==""){
			alert("Bạn phải nhập đầy đủ thông tin");
		}
		else{
			//alert(oldPass + " - " + newPass);
			$.ajax({
				url:"/ajax/change",
				type:"GET",
				data:{
					oldPass:oldPass,
					newPass:newPass
				},
				success: function(value){
					if(value=="true"){
						alert("Cập nhật mật khẩu thành công !");
						currentURL = window.location.href;
						newURL = currentURL.replace("/account","/account");
						window.location = newURL;
					}
					else{
						alert("Mật khẩu cũ không đúng vui lòng nhập lại !");
					}
						
				}
			})
		}
		
	});
	
});