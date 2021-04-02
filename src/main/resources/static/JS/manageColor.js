/**
 * 
 */
$(document).ready(function(){
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
	
	//handle hiding pagination when searching
	var search = $("#searchOption").text();
	if($.trim(search)=="a"){
		
	}
	else{
		$("#navPagination").hide();
	}
	
	//handle insert new size
	$("#insertColor #btnInsert").click(function(){
		var colorName = $("#insertColor #colorName").val();
		if($.trim(colorName)==""){
			alert("Vui lòng nhập tên màu !")
		}
		else {
			$.ajax({
				url:"/adminAjax/saveColor",
				type:"GET",
				data:{
					colorName:colorName
				},
				success: function(){
					alert("Thêm màu thành công");
					currentURL = window.location.href;
					newURL = currentURL.replace("/manageColor/"+currentPage+"/5","/manageColor/"+currentPage+"/5");
					window.location = newURL;
				}
			})
		}
	});
	
	//handle update size
	$(".btnUpdateColor").click(function(){
		var id_color = $(this).closest("tr").find("#id_color").val();
		var name_color = $(this).closest("tr").find("#name_color").val();
		
		$("#updateColor #colorId").val(id_color);
		$("#updateColor #colorName").val(name_color);
	})
	
	$("#updateColor #btnUpdate").click(function(){
		var colorId = $("#updateColor #colorId").val();
		var colorName = $("#updateColor #colorName").val();
		if($.trim(colorName)==""){
			alert("Vui lòng nhập tên màu !")
		}
		else {
			$.ajax({
				url:"/adminAjax/updateColor",
				type:"GET",
				data:{
					colorId:colorId,
					colorName:colorName
				},
				success: function(){
					alert("cập nhật màu thành công");
					currentURL = window.location.href;
					newURL = currentURL.replace("/manageColor/"+currentPage+"/5","/manageColor/"+currentPage+"/5");
					window.location = newURL;
				}
			})
		}
	});
	
	//handle delete size
	$(".btnDeleteColor").click(function(){
		var id_color = $(this).closest("tr").find("#id_color").val();
		$("#deleteColor #colorId").val(id_color);
	});
	
	$("#deleteColor #btnUpdate").click(function(){
		var colorId = $("#deleteColor #colorId").val();
		
			$.ajax({
				url:"/adminAjax/deleteColor",
				type:"GET",
				data:{
					colorId:colorId
				},
				success: function(){
					alert("Xóa size thành công");
					currentURL = window.location.href;
					newURL = currentURL.replace("/manageColor/"+currentPage+"/5","/manageColor/"+currentPage+"/5");
					window.location = newURL;
				}
			})
	});
});