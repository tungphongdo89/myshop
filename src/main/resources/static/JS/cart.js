/**
 * 
 */
$(document).ready(function(){
	
	//Tính tiền số sản phẩm
	
	//set giá tiền ban đầu khi mới bắt đầu vào trang giỏ hàng
	var totalMoney = 0;
	$(".amount").each(function(){
		var price = $(this).closest("tr").find(".price").text();
		var amount = $(this).val();
		var parsePrice  = parseInt(price) * amount;
		$(this).closest("tr").find(".priceAmount").html(parsePrice + " VNĐ");
		
		totalMoney  = totalMoney + parsePrice;
		
	});
	$("#totalMoney").html(totalMoney + " VNĐ");
	
	
	//handle update amount in cart
	$(".amount").on('input', function(){

	    //set giá trị khi click tăng số lượng
		var price = $(this).closest("tr").find(".price").text();
		var amount = $(this).val();
		var parsePrice  = parseInt(price) * amount;
		$(this).closest("tr").find(".priceAmount").html(parsePrice + " VNĐ");
		
		
		var product_id  = $(this).closest("tr").find(".productId").attr("data-productId");
		var size_id     = $(this).closest("tr").find(".sizeId").attr("data-sizeId");
		var color_id    = $(this).closest("tr").find(".colorId").attr("data-colorId");
		var amount      = $(this).val();
		
			$.ajax({
				url:"/ajax/changeCart",
				type:"GET",
				data:{
					product_id:product_id,
					size_id:size_id,
					color_id:color_id,
					amount:amount
				},
				success: function(value){
					currentURL = window.location.href;
					newURL = currentURL.replace("/cart","/cart");
					window.location = newURL;
				}
			});
		
	});
	

	//handle delete cart
	$(".btnDeleteCart").click(function(){
		var self        = $(this);
		var product_id  = $(this).closest("tr").find(".productId").attr("data-productId");
		var size_id     = $(this).closest("tr").find(".sizeId").attr("data-sizeId");
		var color_id    = $(this).closest("tr").find(".colorId").attr("data-colorId"); 
		
		$.ajax({
			url:"/ajax/deleteCart",
			type:"GET",
			data:{
				product_id:product_id,
				size_id:size_id,
				color_id:color_id
			},
			success: function(value){
				self.closest("tr").remove();
				currentURL = window.location.href;
				newURL = currentURL.replace("/cart","/cart");
				window.location = newURL;
			}
		});
		
	});
	
    //handle event buy 
	$("#btnBuy").click(function(){
		
		if($('#payment1').is(':checked')) {
	    	var payments = $("#choosePayment #payment1").val();
	    }
		if($('#payment2').is(':checked')) {
	    	var payments = $("#choosePayment #payment2").val();
	    }
		if($('#payment3').is(':checked')) {
	    	var payments = $("#choosePayment #payment3").val();
	    }
		
		var note             = $("#note").val();
		var totalMoney       = $("#totalMoney").text();
		var userId           = $("#userId").val();
		
		if($.trim(totalMoney)=="0 VNĐ"){
			alert("Bạn chưa có sản phẩm nào ! vui lòng chọn sản phẩm");
		}
		else{
			if($.trim(payments)==""){
				alert("Bạn chưa chọn hình thức thanh toán");
			}
			else{
				$.ajax({
					url:"/ajax/buyProduct",
					type:"GET",
					data:{
						note:note,
						totalMoney:totalMoney,
						payments:payments,
						userId:userId
					},
					success: function(value){
						alert("Cảm ơn quý khách đã mua sản phẩm của chúng tôi !")
					}
				});
			}
			
		}
		
		
	});
	

});