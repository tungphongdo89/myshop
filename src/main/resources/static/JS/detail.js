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
	
	$(".btnAddCart").click(function(){
		if($.trim(UserInfor) == ""){
			alert("Bạn cần đăng nhập trước khi mua hàng !");
		}
		else
		{
			var productDetail_id    = $(this).closest("tr").find(".productDetailId").attr("data-productDetailId");
			var product_id = $("#productId").text();
			var productName= $("#productName").text();
			var size_id    = $(this).closest("tr").find(".sizeId").attr("data-sizeId");
			var color_id   = $(this).closest("tr").find(".colorId").attr("data-colorId");
			var sizeName   = $(this).closest("tr").find(".sizeName").attr("data-sizeName");
			var colorName  = $(this).closest("tr").find(".colorName").attr("data-colorName");
			var price      = $("#productPrice").text();
			//var amount     = $(".amount").val();
			
			
			//alert(amount);
			//alert(product_id+"-"+color_id+"-"+size_id+"-"+productName+"-"+sizeName+"-"+colorName+"-"+price+"-"+amount);
			
			$.ajax({
				url:"/ajax/addCart",
				type:"GET",
				data:{
					productDetail_id:productDetail_id,
					product_id:product_id,
					size_id:size_id,
					color_id:color_id,
					productName:productName,
					sizeName:sizeName,
					colorName:colorName,
					price:price
					//amount:amount
				},
				success: function(value){
						alert("Sản phẩm của bạn đã được thêm vào giỏ hàng");
				}
			})
		}

	});
});