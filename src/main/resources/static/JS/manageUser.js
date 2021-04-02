/**
 * 
 */
$(document).ready(function(){
	//hide-show LockAccount
	$(".userEnabled").each(function(){
		var userEnabled = $(this).text();
		//alert(userEnabled);
		if($.trim(userEnabled) == 1){
			$(this).closest("tr").find(".btnLockAcc").show();
			$(this).closest("tr").find(".btnOpenAcc").hide();
		}
		if($.trim(userEnabled) == 0){
			$(this).closest("tr").find(".btnLockAcc").hide();
			$(this).closest("tr").find(".btnOpenAcc").show();
		}
	});
	
	
	//hide preURL
	var pageNumberPre = $("#pageNumberPre").text();
	if($.trim(pageNumberPre) == "0"){
		$("#paginationURLPre").hide();
	}
	
	//hide nextURL
	var pageNumberNext = $("#pageNumberNext").text();
	var pageNumber     = $("#pageNumber").text();
	var realPageNumber = parseInt(pageNumber) + 1;
	
	if($.trim(pageNumberNext) == $.trim(realPageNumber)){
		$("#paginationURLNext").hide();
	}
	
	//Handle view user
	$(".btnViewUser").click(function(){
		var id_user = $(this).closest("tr").find("#id_user").val();
		var name_user = $(this).closest("tr").find("#name_user").val();
		var address_user = $(this).closest("tr").find("#address_user").val();
		var email_user = $(this).closest("tr").find("#email_user").val();
		var phone_user = $(this).closest("tr").find("#phone_user").val();
		var gender_user = $(this).closest("tr").find("#gender_user").val();
		var enabled_user = $(this).closest("tr").find("#enabled_user").val();
		
		$("#userId").val(id_user);
		$("#userName").val(name_user);
		$("#address").val(address_user);
		$("#email").val(email_user);
		$("#phone").val(phone_user);
		$("#gender").val(gender_user);
		if($.trim(enabled_user) == "1"){
			$("#enabled").val("Mở");
		}
		else {
			$("#enabled").val("Đóng");
		}
	})
	
	//Handle lock account
	$(".btnLockAcc").click(function(){
		var id_user = $(this).closest("tr").find("#id_user").val();
		var name_user = $(this).closest("tr").find("#name_user").val();
		var pass_user = $(this).closest("tr").find("#pass_user").val();
		var address_user = $(this).closest("tr").find("#address_user").val();
		var email_user = $(this).closest("tr").find("#email_user").val();
		var phone_user = $(this).closest("tr").find("#phone_user").val();
		var gender_user = $(this).closest("tr").find("#gender_user").val();
		//var enabled_user = $(this).closest("tr").find("#enabled_user").val();
		var currentPage = $("#currentPage").text();
		//alert(currentPage);
		
		if($.trim(id_user)=="1"){
			alert("Bạn không thể khóa tài khoản Admin");
		}
		else{
			$.ajax({
				url:"/adminAjax/lockAcc",
				type:"GET",
				data:{
					id_user:id_user,
					name_user:name_user,
					pass_user:pass_user,
					address_user:address_user,
					email_user:email_user,
					phone_user:phone_user,
					gender_user:gender_user,
					enabled_user:0
				},
				success: function(value){
					alert("khóa tài khoản thành công");
					currentURL = window.location.href;
					newURL = currentURL.replace("/manageUser/"+currentPage+"/5","/manageUser/"+currentPage+"/5");
					window.location = newURL;
				}
			});
		}

	});
	
	//Handle open account
	$(".btnOpenAcc").click(function(){
		var id_user = $(this).closest("tr").find("#id_user").val();
		var name_user = $(this).closest("tr").find("#name_user").val();
		var pass_user = $(this).closest("tr").find("#pass_user").val();
		var address_user = $(this).closest("tr").find("#address_user").val();
		var email_user = $(this).closest("tr").find("#email_user").val();
		var phone_user = $(this).closest("tr").find("#phone_user").val();
		var gender_user = $(this).closest("tr").find("#gender_user").val();
		//var enabled_user = $(this).closest("tr").find("#enabled_user").val();
		var currentPage = $("#currentPage").text();
		//alert(currentPage);
		
		$.ajax({
			url:"/adminAjax/openAcc",
			type:"GET",
			data:{
				id_user:id_user,
				name_user:name_user,
				pass_user:pass_user,
				address_user:address_user,
				email_user:email_user,
				phone_user:phone_user,
				gender_user:gender_user,
				enabled_user:1
			},
			success: function(value){
				alert("Mở khóa tài khoản thành công");
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageUser/"+currentPage+"/5","/manageUser/"+currentPage+"/5");
				window.location = newURL;
			}
		});
	});
	
	//handle delete acc
	$(".btnDeleteAcc1").click(function(){
		var userId = $(this).closest("tr").find("#id_user").val();
		$("#userId_del").val(userId);
	});
	
	$("#btnModalDeleteAcc").click(function(){
		 var userId = $("#userId_del").val();
		 //alert(userId);
		 $.ajax({
			 url:"/adminAjax/deleteAcc",
			 type:"GET",
			 data:{
				 userId:userId
			 },
			 success: function(value){
				alert("Xóa tài khoản thành công");
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageUser/"+currentPage+"/5","/manageUser/"+currentPage+"/5");
				window.location = newURL;
			 }
		 });
	});
	
	//handle hiding pagination when searching
	var search = $("#searchOption").text();
	if($.trim(search)=="a"){
		
	}
	else{
		$("#navPagination").hide();
	}
	
});