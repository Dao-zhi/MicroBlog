$(function () {
    // var myCookie = document.cookie.replace(/(?:(?:^|.*;\s*)user_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");
    var myCookie = getCookie("user_id");
    $("#changemyinfo").click(function () {
        info1();
    });
    $('#profile_button').click(function () {
        info1();
    });

    function info1() {
        $('.profile').fadeIn();
        $('.homepage').fadeOut();
        $('.view_detail').fadeOut();
        var avatar_url3="avatar/"+myCookie+".jpg";
        $("#avator_now").attr("src",avatar_url3);

    }

    $('#home_button').click(
        function () {
            $('.homepage').fadeOut();
            $('.profile').fadeOut();
            showBlog();
        }
    );
    $('#homepage_button').click(function () {
            $('.homepage').fadeIn();
            $('.profile').fadeOut();
        showhomeblog(myCookie);
            showIfo(myCookie);
        });
    $("#myHomepage_avtiob_bar_blog_button").click(function () {
        showhomeblog(myCookie);
    })

$("#myHomepage_avtiob_bar_comment_button").click(function () {
    showmycom(myCookie);
});

});



function showIfo(userid) {
    //var myCookie = document.cookie.replace(/(?:(?:^|.*;\s*)user_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");
    $.post("/user/getUserServlet",{
        "User_id":userid
    },function (res) {
        //console.log(res);
        var json=JSON.parse(res);
        var avatar_url3="avatar/"+userid+".jpg";
        $("#homepage_avator").attr("src",avatar_url3);
        $("#myinfo").text(json.info);
    });
}

//id="myHomepage_avtiob_bar_comment_button"
function showhomeblog(userid) {
    //var myCookie = document.cookie.replace(/(?:(?:^|.*;\s*)user_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");

    $("#homepage_blog").remove();
    var $ttttemp=$('<div id="homepage_blog" class="article_list"></div>');
    $(".homepage_scrollable").append($ttttemp);
    $.post("/blog/blogShowServlet",{
        "User_id": userid
    },function (res) {
        var json=JSON.parse(res);
        //console.log(json);
        for (var i = 0; i < json.length; i++) {

            var avatar_url = "url('avatar/" + json[i].user_id + ".jpg')";
            var $temp = crblog(json[i].blog_id + "hb", json[i].user_name, json[i].blog_content, json[i].blog_time, avatar_url);
            $temp.css("display", "none");
            $("#homepage_blog").prepend($temp);
            $temp.fadeIn();
        }
    })
}
function showmycom (userid) {
    $("#homepage_blog").remove();
    var $ttttemp=$('<div id="homepage_blog" class="article_list"></div>');
    $(".homepage_scrollable").append($ttttemp);

    $.post("/comm/getmyComment",
        {
            "userId":userid

        }, function (res) {
            var json = JSON.parse(res);
            for (var i = 0; i < json.length; i++) {

                var avatar_url = "url('avatar/" + json[i].uid + ".jpg')";
                var $temp = crhomecom(json[i].bid+"-"+json[i].ccid+"-"+json[i].cid, json[i].user_name, json[i].content, json[i].time, avatar_url);
                $temp.css("display", "none");
                $("#homepage_blog").prepend($temp);
                $temp.fadeIn();

            }

        });
}


function crhomecom(blog_id,username,blog_content,blog_time,avatar_url) {
    var $box1=$("<article></article>");
//设置id
    $box1.attr("id",blog_id);

    var $box2=$("<div class='article_cell' style='background:#313543;'></div>");
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
    var $box2_2_3=$("<div class='content'><button type='button' id='delcom'></button></div>");

    $box2_2_2.text(blog_content);

    $box2_2.append($box2_2_1);
    $box2_2.append($box2_2_2);
    $box2_2.append($box2_2_3);

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

    return $box1;
}