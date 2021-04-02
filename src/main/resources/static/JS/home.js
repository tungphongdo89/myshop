/**
 * 
 */
$(document).ready(function(){
	
	var UserInfor = $("#UserInfor").text();
	
	if($.trim(UserInfor) == ""){
		$(".logoutBtn").hide();
	}
	
	if($.trim(UserInfor) != ""){
		//alert("k có gì");
		$(".li-login").hide();
		$("#UserInfor").addClass("circle_name");
		
	}
	
	var roleAdmin = $("#roleAdmin").text();
	if($.trim(roleAdmin) != "ROLE_ADMIN"){
		$(".li-admin").hide();
	}
	
	
	
	
});