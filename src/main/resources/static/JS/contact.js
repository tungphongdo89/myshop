/*
 * 
 */
$(document).ready(function(){
	$("#btnSend").click(function(){
		var name = $("#name").val();
		var email = $("#email").val();
		var phone = $("#phone").val();
		var comment = $("#comment").val();
		
		//alert(name +"-"+ email +"-"+ phone +"-"+ comment);
		$.ajax({
			url:"/ajax/doContact",
			type:"GET",
			data:{
				name:name,
				email:email,
				phone:phone,
				comment:comment
			},
			success: function(){
				alert("Cám ơn ý kiến đóng góp của bạn về shop !");
				currentURL = window.location.href;
				newURL = currentURL.replace("/contact","/contact");
				window.location = newURL;
			}
		})
	});
});