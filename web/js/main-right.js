$(function(){
	$("#right-bottom1").click(function(){
		$("#right-bottom2").toggle();
		$("#right-box1").slideDown("slow",function(){
			
			$("#right-box2").slideUp();
		});
		$("#right-bottom1").toggle();
	});
	$("#right-bottom2").click(function(){
		$("#right-bottom1").toggle();
		$("#right-box2").slideDown("slow",function(){
			
			$("#right-box1").slideUp();
		});
		$("#right-bottom2").toggle();
	});
});
