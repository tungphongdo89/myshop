$(document).ready(function(){
	
	//hide-show signup-login
	$("#btnSignUp").click(function(){
		$("#btnSignUp").addClass("actived");
		$("#btnLogin").removeClass("actived");
		$("#row-login").hide();
		$("#row-signup").show();
	});
	$("#btnLogin").click(function(){
		$("#btnLogin").addClass("actived");
		$("#btnSignUp").removeClass("actived");
		$("#row-login").show();
		$("#row-signup").hide();
	});
	
	//handle event confirm password
	$("#rePass").focusout(function(){
		var pass = $("#pass").val();
		var rePass = $("#rePass").val();

		if($.trim(pass) != $.trim(rePass)){
			alert("Nhập lại mật khẩu không khớp ! Vui lòng nhập lại");
		}
	});

	
	//handle signup by ajax
	$("#btnDoSignUp").click(function(){
	    var username = $("#form_signUp #username").val();
	    var rePass = $("#form_signUp #rePass").val();
	    var address = $("#form_signUp #address").val();
	    var gender = $("#form_signUp #gender").val();
	    var phone = $("#form_signUp #phone").val();
	    var email = $("#form_signUp #email").val();
	    
	    if($.trim(username)=="" || $.trim(rePass)=="" || $.trim(address)=="" || $.trim(phone)=="" || $.trim(email)==""){
			alert("Bạn phải nhập đầy đủ thông tin");
		}
	    
	    //alert(gender);
	    $.ajax({
			url:"/ajax/signup",
			type:"GET",
			data:{
				username:username,
				pass:rePass,
				address:address,
				telephone:phone,
				email:email,
				gender:gender
			},
			success: function(value){
				if(value=="true"){
					alert("Đăng ký thành công !");
					currentURL = window.location.href;
					newURL = currentURL.replace("/login","/login");
					window.location = newURL;
				}
				else{
					alert("Đăng ký thất bại : Tài khoản đã tồn tại , vui lòng nhập tài khoản mới");
				}
					
			}
		})
	});
	
	
});