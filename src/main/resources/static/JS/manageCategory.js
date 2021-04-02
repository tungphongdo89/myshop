/**
 * 
 */
$(document).ready(function(){
	
	//handle update Category
	$(".btnModalUpdate").click(function(){
		var id_category = $(this).closest("tr").find("#id_category").val();
		var name_category = $(this).closest("tr").find("#name_category").val();
		
		$("#update #categoryId").val(id_category);
		$("#update #categoryName").val(name_category);
	});
	
	$("#btnUpdate").click(function(){
		var categoryId = $("#update #categoryId").val();
		var categoryName = $("#update #categoryName").val();
		
		$(function () {
		    var token = $("input[name='_csrf']").val();
		    var header = "X-CSRF-TOKEN";
		    $(document).ajaxSend(function(e, xhr, options) {
		        xhr.setRequestHeader(header, token);
		    });
		});
		
		$.ajax({
			url:"/adminAjax/updateCategory",
			type:"POST",
			data:{
				categoryId:categoryId,
				categoryName:categoryName
			},
			success: function(){
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageCategory/"+currentPage+"/5","/manageCategory/"+currentPage+"/5");
				window.location = newURL;
			}
		})
	});
	
	//handle save new category
	$("#btnInsert").click(function(){
		var categoryName = $("#insertCategory #categoryName").val();
		if($.trim(categoryName)==""){
			alert("Vui lòng nhập tên danh mục !")
		}
		else {
			$.ajax({
				url:"/adminAjax/saveCategory",
				type:"GET",
				data:{
					categoryName:categoryName
				},
				success: function(){
					currentURL = window.location.href;
					newURL = currentURL.replace("/manageCategory/"+currentPage+"/5","/manageCategory/"+currentPage+"/5");
					window.location = newURL;
				}
			})
		}
	});
	
	//handle delete category
	$(".btnDeleteCategory").click(function(){
		var id_category = $(this).closest("tr").find("#id_category").val();
		$("#deleteCategory #categoryId").val(id_category);
	});
	$("#deleteCategory #btnModalDeleteCategory").click(function(){
		var categoryId = $("#deleteCategory #categoryId").val();
		
		$.ajax({
			url:"/adminAjax/deleteCategory",
			type:"GET",
			data:{
				categoryId:categoryId
			},
			success: function(){
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageCategory/"+currentPage+"/5","/manageCategory/"+currentPage+"/5");
				window.location = newURL;
			}
		})
	});
	
	//handle product category
	$(".btnListProduct").click(function(){
		var tableProduct = $(this).closest("tr").find("#tableProduct").html();
		//alert(tableProduct);
		$("#tableProductCopy").html(tableProduct);
	});
	
});