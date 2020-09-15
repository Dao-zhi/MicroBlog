$(function () {
	var timeeeeeee=3000;
    setInterval(function () {
    	timeeeeeee=(Math.random()*2+30)*1000;
    	var date1=new Date();
		$.post("/blog/blogRandomServlet",
			{},function (res) {
			var json=JSON.parse(res);
			var avatar_url="url('avatar/"+json.user_id+".jpg')";
			var $temp=crblog("b"+json.blog_id,json.user_name,json.blog_content,date1.getMinutes()+"分"+date1.getSeconds()+"秒",avatar_url);
				$temp.css("display","none");
				$(".scrollable").prepend($temp);
				$temp.slideDown();
			});
    },timeeeeeee);
});
//js操作dom将微博填充到对应区域
function crblog(blog_id,username,blog_content,blog_time,avatar_url) {
    var $box1=$("<article></article>");
//设置id
    $box1.attr("id",blog_id);

    var $box2=$("<div class='article_cell'></div>");
    $box1.append($box2);
//用户头像
    var $box2_1=$("<div class='avatar'></div>");
    $box2.append($box2_1);

    var $box2_1_1=$("<div class='account__avatar'></div>");
    $box2_1.append($box2_1_1);
	$box2_1_1.css("background-image",avatar_url);


    var $box2_2=$("<div class='details'></div>");
    $box2.append($box2_2);
	
	var $box2_2_1=$("<div class='status'></div>");//status
	var $box2_2_2=$("<div class='content'></div>");//content
	
	$box2_2_2.text(blog_content);
	
	$box2_2.append($box2_2_1);
	$box2_2.append($box2_2_2);
	
	var $status_a=$("<a class='status__display-name'></a>");
	$box2_2_1.append($status_a);
	
	var $status_span=$("<span class='display-name'></span>");
	$status_a.append($status_span);
	
	var $status_bdi=$("<bdi><strong class='display-name__html'></strong></bdi>");
	$status_span.append($status_bdi);
	
	var $status_span_time=$("<span class='status_span_time'></span>");
	
	//设置发博时间
	$status_span_time.text(blog_time);
	$box2_2_1.append($status_span_time);
	//设置用户名
	$status_bdi.find("strong").text(username);
	//设置微博内容
	//var $content_p=$("<p></p>");
	//$content_p.text(blog_content);
	
	//$box2_2_2.append($content_p);
	
    return $box1;
}