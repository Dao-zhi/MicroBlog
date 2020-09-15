$(function(){

$("#concern_button").click(function () {
    // var userid= document.cookie.replace(/(?:(?:^|.*;\s*)user_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");
    // var concernid = document.cookie.replace(/(?:(?:^|.*;\s*)concern_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");
    var userid = getCookie("user_id");
    var concernid = getCookie("concern_id")
    $.post("/user/concernServlet",{
        "userId":userid,
        "concernId":concernid
    },function (res) {
        if(res==="true"){
            layer.msg('关注成功', { icon: 1 });
            $("#concern_button").css("color", "red");
        }else {
            layer.msg('关注失败请重试！', {icon: 2});
        }


    })
});


var concernFlag=false;
    //获取关注列表
    $("#right-bottom2").click(function () {
        // var userId = document.cookie.replace(/(?:(?:^|.*;\s*)user_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");//读取cookie
        var userId = getCookie("user_id");
        if(concernFlag==false){

            $.post("/user/idolServlet",{
                "userId":userId
            },function (res) {
                if(res===""){
                    layer.msg('获取关注列表失败，请重试！', { icon: 2 });
                }
                else {
                    var concernJson=JSON.parse(res);
                    for(var i=0;i<concernJson.length;i++){
                        var $tempConcern=crcConcern(concernJson[i].id, concernJson[i].name);
                        $("#right-box2").prepend($tempConcern);

                        $("#"+concernJson[i].id+"action_bar").click(function () {

                            noconcern(parseInt(this.id));
                        });
                    }
                }
            });
            concernFlag=true;
        }
    });
});

//js操作dom将关注的人加到对应区域
function crcConcern(userId, userName) {
    var $article=$("<article></article>");
    var $attention_cell = $("<div class='attention_cell'></div>");
    $article.append($attention_cell);

    var $attention_avatar = $("<div class='attention_avatar'></div>");
    var $attention_details = $("<div class='attention_details'></div>");
    var $attention_action_bar = $("<div id='"+userId+"action_bar' class='attention_action_bar'></div>");
    $attention_cell.append($attention_avatar);
    $attention_cell.append($attention_details);
    $attention_cell.append($attention_action_bar);

    var $account_avatar = $("<div class='account_avatar' style='width: 48px; height: 48px; background-size: 48px 48px; border-radius: 5px;'></div>");
    var avatar_url = "url('avatar/" + userId + ".jpg')";
    $account_avatar.css('background-image',avatar_url);
    $attention_avatar.append($account_avatar);

    var $status = $("<div class='status'></div>");
    $attention_details.append($status);
    var $status_display_a = $("<a class='status__display-name'></a>");
    var $status_display_span = $("<span class='display-name'></span>");
    var $status_display_bdi = $("<bdi></bdi>");
    var $status_display_strong = $("<strong class='display-name__html'>"+ userName +"</strong>");
    $status_display_bdi.append($status_display_strong);
    $status_display_span.append($status_display_bdi);
    $status_display_a.append($status_display_span);
    $status.append($status_display_a);

    var $action_bar_button = $("<button title='取关' class='action_bar_button'></button>");
    $attention_action_bar.append($action_bar_button);
    var $action_button_icon = $("<ion-icon name='heart' id='"+userId+"action_bar_button' role='img' class='hydrated' aria-label='heart'></ion-icon>");
    $action_bar_button.append($action_button_icon);

    return $article;
}