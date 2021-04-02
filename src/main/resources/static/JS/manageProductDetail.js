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
	
	
	//handle inserting product detail
	$("#insertProductDetail #btnInsert").click(function(){
		var productId = $("#insertProductDetail #productId").val();
		var colorId = $("#insertProductDetail #colorId").val();
		var sizeId = $("#insertProductDetail #sizeId").val();
		var amount = $("#insertProductDetail #amount").val();
		var date = $("#insertProductDetail #date").val();
		
		$.ajax({
			url:"/adminAjax/insertProductDetail",
			type:"GET",
			data:{
				productId:productId,
				colorId:colorId,
				sizeId:sizeId,
				amount:amount,
				date:date
			},
			success: function(value){
				alert("thêm thành công !");
			}
		});
	});
	
	//handle viewing product detail
	$(".btnViewProductDetail").click(function(){
		var productDetailId = $(this).closest("tr").find("#productDetailId").text();
		var productId = $(this).closest("tr").find("#productId").text();
		var productName = $(this).closest("tr").find("#productName").text();
		var colorId = $(this).closest("tr").find("#colorId").text();
		var colorName = $(this).closest("tr").find("#colorName").text();
		var sizeId = $(this).closest("tr").find("#sizeId").text();
		var sizeName = $(this).closest("tr").find("#sizeName").text();
		var amount = $(this).closest("tr").find("#amount").text();
		var date = $(this).closest("tr").find("#date").text();
		
		$("#viewProductDetail #productDetailId").val(productDetailId);
		$("#viewProductDetail #productId").val(productId);
		$("#viewProductDetail #productName").val(productName);
		$("#viewProductDetail #colorId").val(colorId);
		$("#viewProductDetail #colorName").val(colorName);
		$("#viewProductDetail #sizeId").val(sizeId);
		$("#viewProductDetail #sizeName").val(sizeName);
		$("#viewProductDetail #amount").val(amount);
		$("#viewProductDetail #date").val(date);
	});
	
	//handle updating product detail
	$(".btnUpdateProductDetail").click(function(){
		var productDetailId = $(this).closest("tr").find("#productDetailId").text();
		var productId = $(this).closest("tr").find("#productId").text();
		var colorId = $(this).closest("tr").find("#colorId").text();
		var sizeId = $(this).closest("tr").find("#sizeId").text();
		var amount = $(this).closest("tr").find("#amount").text();
		var date = $(this).closest("tr").find("#date").text();
		
		$("#updateProductDetail #productDetailId").val(productDetailId);
		$("#updateProductDetail #productId").val(productId);
		$("#updateProductDetail #colorId").val(colorId);
		$("#updateProductDetail #sizeId").val(sizeId);
		$("#updateProductDetail #amount").val(amount);
		$("#updateProductDetail #date").val(date);

	});
	
	$("#updateProductDetail #btnSaveUpdateProductDetail").click(function(){
		var productDetailId = $("#updateProductDetail #productDetailId").val();
		var productId = $("#updateProductDetail #productId").val();
		var colorId = $("#updateProductDetail #colorId").val();
		var sizeId = $("#updateProductDetail #sizeId").val();
		var amount = $("#updateProductDetail #amount").val();
		var date = $("#updateProductDetail #date").val();
		
		$.ajax({
			url:"/adminAjax/updateProductDetail",
			type:"GET",
			data:{
				productDetailId:productDetailId,
				productId:productId,
				colorId:colorId,
				sizeId:sizeId,
				amount:amount,
				date:date
			},
			success: function(value){
				alert("Cập nhật thành công !");
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageProductDetail/"+currentPage+"/5",
						"/manageProductDetail/"+currentPage+"/5");
				window.location = newURL;
			}
		});  
		
	});
	
	//handle deteting productDetail
	$(".btnDeleteProductDetail").click(function(){
		var productDetailId = $(this).closest("tr").find("#productDetailId").text();
		var productId = $(this).closest("tr").find("#productId").text();
		var colorId = $(this).closest("tr").find("#colorId").text();
		var sizeId = $(this).closest("tr").find("#sizeId").text();
		
		//alert(productDetailId +"-"+ productId +"-"+ colorId +"-"+ sizeId);
		$("#deleteProductDetail #productDetailId").val(productDetailId);
		$("#deleteProductDetail #productId").val(productId);
		$("#deleteProductDetail #colorId").val(colorId);
		$("#deleteProductDetail #sizeId").val(sizeId);
	});
	
	$("#btnConfirmDelete").click(function(){
		var productDetailId = $("#deleteProductDetail #productDetailId").val();
		var productId = $("#deleteProductDetail #productId").val();
		var colorId = $("#deleteProductDetail #colorId").val();
		var sizeId = $("#deleteProductDetail #sizeId").val();
		
		//alert(productDetailId +"-"+ productId +"-"+ colorId +"-"+ sizeId);
		$.ajax({
			url:"/adminAjax/deleteProductDetail",
			type:"GET",
			data:{
				productDetailId:productDetailId
			},
			success: function(value){
				alert("Xóa thành công thành công !");
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageProductDetail/"+currentPage+"/5",
						"/manageProductDetail/"+currentPage+"/5");
				window.location = newURL;
			}
		});  
	});
});