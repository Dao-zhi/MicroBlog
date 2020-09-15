
$(function () {
//显示微博
    showBlog();

	$("#birthday").datepicker();

    $("#com-quit").click(function () {
        $("#comArea").fadeToggle();
    });

});

function showBlog(){
    $(".test").remove();
    // var myCookie = document.cookie.replace(/(?:(?:^|.*;\s*)user_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");
    var user_id = getCookie("user_id");
    var blog=new Object();
    var comment=new Object();
    $.post("/blog/blogBloggerServlet",
        {
            "User_id":user_id
        },function (res) {
            var json=JSON.parse(res);
            for(var i=0;i<json.length;i++){

                var avatar_url="url('avatar/"+json[i].user_id+".jpg')";
                var $temp=crblog(json[i].blog_id+"b",json[i].user_name,json[i].blog_content,json[i].blog_time,avatar_url,json[i].user_id);
                $temp.css("display","none");
                $(".scrollable").prepend($temp);
                $temp.slideDown();
                //给每条微博绑定点击显示评论的函数
                //点击微博显示评论
                //只能获取一次评论
                $("#"+json[i].user_id+"u").click(function () {
                    $('.homepage').fadeIn();
                    showhomeblog(parseInt(this.id));
                    showIfo(parseInt(this.id));
                    document.cookie = "concern_id="+parseInt(this.id)+";path=/";//设置cookie
                    return false;
                });
                var blogid=json[i].blog_id;
                blog["b"+blogid]=false;
                $("#"+blogid+"b").click(function () {
                    showComArea("false",parseInt(this.id),$("#"+this.id).find("strong")[0].innerHTML);
                    if(blog["b"+parseInt(this.id)]==true){
                        $(".commbox").remove();
                        blog["b"+parseInt(this.id)]=false;
                    }else {
                        $(".commbox").remove();
                        showCom(parseInt(this.id),blog,comment);
                    }
                });
            }
        });
}

//点击显示评论的函数
function showCom(blogid,obj,obj2) {

//创建一个放评论的div
	var $comBox=$("<div class='commbox'></div>");
	$comBox.attr("id",blogid+"cb");
	$comBox.css("display","none");

	//获取评论添加到comBox
	$.post("/comm/getComment",{
		"blogId":blogid+""
	},function (cres) {
		var cjson=JSON.parse(cres);
		//console.log(cjson);
		for(var i=0;i<cjson.length;i++){
			var cdate=new Date();
			cdate.setTime(cjson[i].time);
            var avatar_url2="url('avatar/"+cjson[i].uid+".jpg')";
			var $tempCom=crcommentF(cjson[i].cid+"c",cjson[i].username,cjson[i].content,cdate.getHours()+"时"+cdate.getMinutes()+"分",avatar_url2);
			$("#"+blogid+"cb").prepend($tempCom);
			var comid=cjson[i].cid;
			obj2["c"+comid]=false;
            $("#"+comid+"c").click(function () {

                showComArea("true",parseInt(this.id),$("#"+this.id).find("strong")[0].innerHTML);
                if(obj2["c"+parseInt(this.id)]==true){
                    $(".ccommbox").remove();
                    obj2["c"+parseInt(this.id)]=false;
                }else {
                    $(".ccommbox").remove();
                    showCCom(parseInt(this.id),obj2);
                }

            });
		}
		if(cjson.length!=0){
            layer.msg("评论加载中，请稍等");
            obj["b"+blogid]=true;
        }else{
            layer.msg('还没有评论，快来评论吧');
        }
	});
	$("#"+blogid+"b").after($comBox);
	$comBox.slideDown();

}

//点击显示评论的评论
function showCCom(comid,obj) {

    var $ccomBox=$("<div class='ccommbox'></div>");
    $ccomBox.attr("id",comid+"ccb");
    $ccomBox.css("display","none");

    //获取评论de评论添加到ccomBox
    $.post("/comm/getCComment",{
        "commentId":comid+""
    },function (ccres) {
        var ccjson=JSON.parse(ccres);
        //console.log(ccjson);
        for(var i=0;i<ccjson.length;i++){
            var ccdate=new Date();
            ccdate.setTime(ccjson[i].time);
            var avatar_url3="url('avatar/"+ccjson[i].uid+".jpg')";
            var $tempCCom=crcommentC(ccjson[i].cid,ccjson[i].username,ccjson[i].content,ccdate.getHours()+"时"+ccdate.getMinutes()+"分",avatar_url3);
            $("#"+comid+"ccb").prepend($tempCCom);

        }
        if(ccjson.length!=0){
            layer.msg("评论加载中，请稍等");
            obj["c"+comid]=true;
        }else{
            layer.msg('还没有评论，快来评论吧');
        }
    });
    $("#"+comid+"c").append($ccomBox);
    $ccomBox.slideDown();

}


//js操作dom将微博填充到对应区域
function crblog(blog_id,username,blog_content,blog_time,avatar_url,userId) {
    var $box1=$("<article class='test'></article>");
//设置id
    $box1.attr("id",blog_id);

    var $box2=$("<div class='article_cell' style='background:#313543;'></div>");
    $box1.append($box2);
//用户头像
    var $box2_1=$("<div class='avatar'></div>");
    $box2_1.attr("id",userId+"u");
    $box2.append($box2_1);

    var $box2_1_1=$("<div class='account__avatar'></div>");
	$box2_1_1.css('background-image',avatar_url);
    $box2_1.append($box2_1_1);

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
	
	var $blog_button=$('<div style="background:#313543" class="action_bar">'+
                                        '<button title="回复" class="action_bar_button">'+
                                            '<ion-icon name="undo"></ion-icon>'+
                                        '</button>'+
                                        '<button title="转发" class="action_bar_button">'+
                                            '<ion-icon name="repeat"></ion-icon>'+
                                        '</button>'+
                                        '<button title="关注" class="action_bar_button">'+
                                           ' <ion-icon name="star"></ion-icon>'+
                                        '</button>'+
                                        '<button title="更多" class="action_bar_button">'+
                                            '<ion-icon name="paper-plane"></ion-icon>'+
                                        '</button>'+
										'</div>');
	$box1.append($blog_button);
	
    return $box1;
}


//js操作dom将评论填充到对应区域


function crcommentF(commentId,username,comment_content,comment_time,avatar_url) {
    var $box1=$("<article></article>");
//设置id
    $box1.attr("id",commentId);

    var $box2=$("<div class='article_cell' style='border-left:solid 70px #313543;'></div>");
    $box1.append($box2);
//用户头像
    var $box2_1=$("<div class='avatar'></div>");
    $box2.append($box2_1);

    var $box2_1_1=$("<div class='account__avatar'></div>");
	$box2_1_1.css('background-image',avatar_url);
    $box2_1.append($box2_1_1);

    var $box2_2=$("<div class='details'></div>");
    $box2.append($box2_2);
	
	var $box2_2_1=$("<div class='status'></div>");//status
	var $box2_2_2=$("<div class='content'></div>");//content
	
	$box2_2_2.text(comment_content);
	
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
	$status_span_time.text(comment_time);
	$box2_2_1.append($status_span_time);
	//设置用户名
	$status_bdi.find("strong").text(username);
	
    return $box1;
}


//js操作dom将评论的评论填充到对应区域

function crcommentC(commentId,username,comment_content,comment_time,avatar_url) {
    var $box1=$("<article></article>");
//设置id
    $box1.attr("id",commentId);

    var $box2=$("<div class='article_cell' style='border-left:solid 140px #313543;'></div>");
    $box1.append($box2);
//用户头像
    var $box2_1=$("<div class='avatar'></div>");
    $box2.append($box2_1);

    var $box2_1_1=$("<div class='account__avatar'></div>");
    $box2_1_1.css('background-image',avatar_url);
    $box2_1.append($box2_1_1);

    var $box2_2=$("<div class='details'></div>");
    $box2.append($box2_2);
	
	var $box2_2_1=$("<div class='status'></div>");//status
	var $box2_2_2=$("<div class='content'></div>");//content
	
	$box2_2_2.text(comment_content);
	
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
	$status_span_time.text(comment_time);
	$box2_2_1.append($status_span_time);
	//设置用户名
	$status_bdi.find("strong").text(username);
	
    return $box1;
}



function comment(iscomment,id,cotent) {
    // var myid = document.cookie.replace(/(?:(?:^|.*;\s*)user_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");
    var myid = getCookie("user_id");
    if(iscomment=="true"){
        //是评论的评论
        var commentid=id;
        var cot=cotent;
        $.post("/comm/comComServlet",{
            "blogId":commentid,
            "userId":myid,
            "comment":cotent
        },function (res) {
            if(res==="true"){
                layer.msg('评论成功！', { icon: 1 });
                $(".textarea").val("");
            }
            else {
                layer.msg('评论失败，请重试！', { icon: 2 });
            }
        });
    }else{
        //是微博的评论
        var blogid=id;
        var cot=cotent;
        $.post("/comm/comBlogServlet",{
            "blogId":blogid,
            "userId":myid,
            "comment":cotent
        },function (res) {
            if(res==="true"){
                layer.msg('评论成功！', { icon: 1 });
                $(".textarea").val("");
            }
            else {
                layer.msg('评论失败，请重试！', { icon: 2 });
            }
        });
    }
}
function showComArea(iscomment,id,name) {
    $(".hidden_waring").slideDown();
    $("#targetcont").val("");
    $("#targetid").val(id);
    $("#iscom").val(iscomment);
    $("#targetcont").attr("placeholder","@"+name);
}

$(function () {
    $("#comment_button").click(function () {
        comment($("#iscom").val(),$("#targetid").val(),$("#targetcont").val());
        $("#targetcont").val("");
    });
    $("#comment_cancel_button").click(function () {
        $(".hidden_waring").slideUp();
    });

    // var myid = document.cookie.replace(/(?:(?:^|.*;\s*)user_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");
    // var myname = document.cookie.replace(/(?:(?:^|.*;\s*)user_name\s*\=\s*([^;]*).*$)|^.*$/, "$1");
    var myid = getCookie("user_id");
    var myname = getCookie("user_name");
    $("#myavator").css("background-image","url('avatar/"+myid+".jpg')");
    $("#myname").text("@"+myname);
});
