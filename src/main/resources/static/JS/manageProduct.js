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
	
	//handle view producrt
	$(".btnViewProduct").click(function(){
		var id_product = $(this).closest("tr").find("#id_product").val();
		var name_product = $(this).closest("tr").find("#name_product").val();
		var price_product = $(this).closest("tr").find("#price_product").val();
		var desc_product = $(this).closest("tr").find("#desc_product").val();
		var category_product = $(this).closest("tr").find("#category_product").val();
		var image_product = $(this).closest("tr").find("#image_product").html();
		
		//alert(id_product +"-"+ name_product +"-"+ price_product +"-"+ 
		//desc_product +"-"+ category_product +"-"+ image_product )
		$("#productId").val(id_product);
		$("#productName").val(name_product);
		$("#productPrice").val(price_product);
		$("#productDesc").val(desc_product);
		$("#productCategory").val(category_product);
		$("#productImage").html(image_product);
	});
	
	// handle event upload Image
	var files = [];  // áp dụng khi muốn upload nhiều file
	$(".uploadImage").change(function(event){
		files = event.target.files; // gán tất cả các file đã lấy ra từ máy vào cho files (target.files là mặc định lấy ra file)
		//Tạo ra một form bằng jquery để thực hiện chức năng submit
		forms = new FormData();
		forms.append("file", files[0]);  // giống như ở HTML ta cho files nằm trong thẻ form, files tại vtri 0 vì 
										 // files của chúng ta chỉ có 1 giá trị (chúng ta chỉ upload 1 file)
		
//		forms.append("file1", files[1]);   Trường hợp có nhiều parameters
//		forms.append("file2", files[2]);
//		....
		
		$(function () {
		    var token = $("input[name='_csrf']").val();
		    var header = "X-CSRF-TOKEN";
		    $(document).ajaxSend(function(e, xhr, options) {
		        xhr.setRequestHeader(header, token);
		    });
		});
		
		$.ajax({
			url:"/adminAjax/uploadImage",
			type:"POST",
			data:forms,
			contentType:false,
			processData:false,    // báo cho jquery là chúng ta sẽ không xử lý data của form
			enctype:"multipart/form-data",   // để mã hóa file
			success: function(value){
				$(".productImageName").val(value);
			}
		});
	});
	
	//handle insert product
	$("#insertProduct #btnInsert").click(function(){
		var productName = $("#insertProduct #productName").val();
		var productPrice = $("#insertProduct #productPrice").val();
		var productDesc = $("#insertProduct #productDesc").val();
		var productImageName = $(".productImageName").val();
		var productCategoryId = $("#insertProduct #productCategoryId").val();
		
		//alert(productName +"-"+ productPrice +"-"+ productDesc +"-"+ productImage +"-"+ productCategory);
		
		$.ajax({
			url:"/adminAjax/insertProduct",
			type:"GET",
			data:{
				productName:productName,
				productPrice:productPrice,
				productDesc:productDesc,
				productImageName:productImageName,
				productCategoryId:productCategoryId
			},
			success: function(value){
				alert("thêm sản phẩm thành công");
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageProduct/"+currentPage+"/5","/manageProduct/"+currentPage+"/5");
				window.location = newURL;
			}
		})
	});
	
	//handle update product
	$(".btnModalUpdate").click(function(){
		var id_product = $(this).closest("tr").find("#id_product").val();
		var name_product = $(this).closest("tr").find("#name_product").val();
		var price_product = $(this).closest("tr").find("#price_product").val();
		var desc_product = $(this).closest("tr").find("#desc_product").val();
		var categoryId_product = $(this).closest("tr").find("#categoryId_product").val();
		var image_product = $(this).closest("tr").find("#image_product").html();
		var image_product_name = $(this).closest("tr").find("#image_product_name").val();

		$("#updateProduct #productId").val(id_product);
		$("#updateProduct #productName").val(name_product);
		$("#updateProduct #productPrice").val(price_product);
		$("#updateProduct #productDesc").val(desc_product);
		$("#updateProduct #productCategoryId").val(categoryId_product);
		$(".productImageName").val(image_product_name);
		$("#updateProduct #productImage").html(image_product);
	});
	
	$("#updateProduct #btnUpdate").click(function(){
		var productId = $("#updateProduct #productId").val();
		var productName = $("#updateProduct #productName").val();
		var productPrice = $("#updateProduct #productPrice").val();
		var productDesc = $("#updateProduct #productDesc").val();
		var productImageName = $(".productImageName").val();
		var productCategoryId = $("#updateProduct #productCategoryId").val();
		
		$.ajax({
			url:"/adminAjax/updateProduct",
			type:"GET",
			data:{
				productId:productId,
				productName:productName,
				productPrice:productPrice,
				productDesc:productDesc,
				productImageName:productImageName,
				productCategoryId:productCategoryId
			},
			success: function(value){
				alert("cập nhật sản phẩm thành công");
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageProduct/"+currentPage+"/5","/manageProduct/"+currentPage+"/5");
				window.location = newURL;
			}
		})
	});
	
	//handle deleting products
	$(".btnDeleteProduct").click(function(){
		var id_product = $(this).closest("tr").find("#id_product").val();
		$("#deleteProduct #productId").val(id_product);
	});
	
	$("#deleteProduct #btnDelete").click(function(){
		var productId = $("#deleteProduct #productId").val();
		$.ajax({
			url:"/adminAjax/deleteProduct",
			type:"GET",
			data:{
				productId:productId
			},
			success: function(value){
				alert("Xóa sản phẩm thành công");
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageProduct/"+currentPage+"/5","/manageProduct/"+currentPage+"/5");
				window.location = newURL;
			}
		})
	});
	
	
});