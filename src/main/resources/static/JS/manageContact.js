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
	
	//handle viewing comment
	$(".btnViewContact").click(function(){
		var comment = $(this).closest("tr").find("#comment").val();
		$("#viewContact #comment").val(comment);
	});
	
	//handle deleting comment
	$(".btnDeleteContact").click(function(){
		var contactId = $(this).closest("tr").find("#contactId").text();
		$("#deleteContact #contactId").val(contactId);
	})
	
	$("#deleteContact #btnDelete").click(function(){
		var contactId = $("#deleteContact #contactId").val();
		
		$.ajax({
			url:"/adminAjax/deleteContact",
			type:"GET",
			data:{
				contactId:contactId
			},
			success: function(){
				alert("Xóa thành công");
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageContact/"+currentPage+"/5","/manageContact/"+currentPage+"/5");
				window.location = newURL;
			}
		})
	});
});