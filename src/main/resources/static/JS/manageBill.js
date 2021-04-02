/**
 * 
 */
$(document).ready(function(){
	
	//handle show/hide status
	$(".billStatus").each(function(){
		var billStatus = $(this).text();
		
		if($.trim(billStatus) == "1"){
			$(this).closest("tr").find(".btnConfirmBill").hide();
			$(this).closest("tr").find(".btnCancelConfirmBill").show();
		}
		if($.trim(billStatus) == "0"){
			$(this).closest("tr").find(".btnConfirmBill").show();
			$(this).closest("tr").find(".btnCancelConfirmBill").hide();
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
	
	//handle hiding pagination when searching
	var search = $("#searchOption").text();
	if($.trim(search)=="a"){
		
	}
	else{
		$("#navPagination").hide();
	}
	
	//handle view bill
	$(".btnViewBill").click(function(){
		var tableBill = $(this).closest("tr").find("#tableBill").html();
		var totalMoney = $(this).closest("tr").find("#totalMoney").text();
		var userId = $(this).closest("tr").find("#userId").text();
		var payments = $(this).closest("tr").find("#payments").text();
		var note = $(this).closest("tr").find("#note").text();
		
		$("#viewBill #tableBill").html(tableBill);
		$("#viewBill #totalMoney").val(totalMoney);
		$("#viewBill #userId").val(userId);
		$("#viewBill #payments").val(payments);
		$("#viewBill #note").val(note);
	})
	
	//handle confirm/cancelConfirm
	$(".btnConfirmBill").click(function(){
		var billId = $(this).closest("tr").find("#billId").text();
		var date = $(this).closest("tr").find("#date").text();
		var totalMoney = $(this).closest("tr").find("#totalMoney").text();
		var payments = $(this).closest("tr").find("#payments").text();
		var note = $(this).closest("tr").find("#note").text();
		var userId = $(this).closest("tr").find("#userId").text();
		
		//alert(billId +"-"+ totalMoney +"-"+ userId +"-"+ payments +"-"+ note);
		$.ajax({
			url:"/adminAjax/confirmBill",
			type:"GET",
			data:{
				billId:billId,
				date:date,
				totalMoney:totalMoney,
				payments:payments,
				note:note,
				userId:userId
			},
			success: function(value){
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageBill/"+currentPage+"/5","/manageBill/"+currentPage+"/5");
				window.location = newURL;
			}
		});
	});
	
	$(".btnCancelConfirmBill").click(function(){
		var billId = $(this).closest("tr").find("#billId").text();
		var date = $(this).closest("tr").find("#date").text();
		var totalMoney = $(this).closest("tr").find("#totalMoney").text();
		var payments = $(this).closest("tr").find("#payments").text();
		var note = $(this).closest("tr").find("#note").text();
		var userId = $(this).closest("tr").find("#userId").text();
		
		//alert(billId +"-"+ totalMoney +"-"+ userId +"-"+ payments +"-"+ note);
		$.ajax({
			url:"/adminAjax/cancelConfirmBill",
			type:"GET",
			data:{
				billId:billId,
				date:date,
				totalMoney:totalMoney,
				payments:payments,
				note:note,
				userId:userId
			},
			success: function(value){
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageBill/"+currentPage+"/5","/manageBill/"+currentPage+"/5");
				window.location = newURL;
			}
		});
	});
	
	//handle deleting bill
	$(".btnDeleteBill").click(function(){
		var billId = $(this).closest("tr").find("#billId").text();
		$("#deleteBill #billId").val(billId);
	});
	
	$("#deleteBill #btnDelete").click(function(){
		var billId = $("#deleteBill #billId").val();
		
		$.ajax({
			url:"/adminAjax/deleteBill",
			type:"GET",
			data:{
				billId:billId
			},
			success: function(value){
				alert("Xóa đơn hàng thành công !")
				currentURL = window.location.href;
				newURL = currentURL.replace("/manageBill/"+currentPage+"/5","/manageBill/"+currentPage+"/5");
				window.location = newURL;
			}
		});
	});
});