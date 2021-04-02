/**
 * 
 */
$(document).ready(function(){
	
	
//	var priceOff = $(".priceOff").text();
//	
//	var priceOff2 = parseFloat(priceOff) * 100;
//	
//	//alert(priceOff2);
//	$(".priceOff").text(priceOff2);
	
	$(".priceOff").each(function(){
		var priceOff = $(this).text();
		
		var priceOff2 = parseFloat(priceOff) * 100;
		
		//alert(priceOff2);
		$(this).text(priceOff2);
	});
});