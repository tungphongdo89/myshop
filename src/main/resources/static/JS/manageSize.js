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
	$("#insertSize #btnInsert").click(function(){
		var sizeName = $("#insertSize #sizeName").val();
		if($.trim(sizeName)==""){
			alert("Vui lòng nhập tên size !")
		}
		else {
			$.ajax({
				url:"/adminAjax/saveSize",
				type:"GET",
				data:{
					sizeName:sizeName
				},
				success: function(){
					alert("Thêm size thành công");
					currentURL = window.location.href;
					newURL = currentURL.replace("/manageSize/"+currentPage+"/5","/manageSize/"+currentPage+"/5");
					window.location = newURL;
				}
			})
		}
	});
	
	//handle update size
	$(".btnUpdateSize").click(function(){
		var id_size = $(this).closest("tr").find("#id_size").val();
		var name_size = $(this).closest("tr").find("#name_size").val();
		
		$("#updateSize #sizeId").val(id_size);
		$("#updateSize #sizeName").val(name_size);
	})
	
	$("#updateSize #btnUpdate").click(function(){
		var sizeId = $("#updateSize #sizeId").val();
		var sizeName = $("#updateSize #sizeName").val();
		if($.trim(sizeName)==""){
			alert("Vui lòng nhập tên size !")
		}
		else {
			$.ajax({
				url:"/adminAjax/updateSize",
				type:"GET",
				data:{
					sizeId:sizeId,
					sizeName:sizeName
				},
				success: function(){
					alert("cập nhật size thành công");
					currentURL = window.location.href;
					newURL = currentURL.replace("/manageSize/"+currentPage+"/5","/manageSize/"+currentPage+"/5");
					window.location = newURL;
				}
			})
		}
	});
	
	//handle delete size
	$(".btnDeleteSize").click(function(){
		var id_size = $(this).closest("tr").find("#id_size").val();
		$("#deleteSize #sizeId").val(id_size);
	});
	
	$("#deleteSize #btnUpdate").click(function(){
		var sizeId = $("#deleteSize #sizeId").val();
		
			$.ajax({
				url:"/adminAjax/deleteSize",
				type:"GET",
				data:{
					sizeId:sizeId
				},
				success: function(){
					alert("Xóa size thành công");
					currentURL = window.location.href;
					newURL = currentURL.replace("/manageSize/"+currentPage+"/5","/manageSize/"+currentPage+"/5");
					window.location = newURL;
				}
			})
	});
});