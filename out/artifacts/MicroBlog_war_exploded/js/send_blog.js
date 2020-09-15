$(function(){
    //发微博
    $("#send_button").click(function () {
        // document.cookie = "User_id=173";//设置cookie
        // var User_id = document.cookie.replace(/(?:(?:^|.*;\s*)user_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");//读取cookie
        var User_id = getCookie("user_id");
        var Blog_content=$(".textarea").val();
        //console.log(Blog_content);
        $.post("/blog/blogInsertServlet",{
            "User_id":User_id,
            "Blog_content":Blog_content
        },function (res) {
            if(res==="true"){
                layer.msg('发送成功！', { icon: 1 });
                $(".textarea").val("");
            }
            else {
                layer.msg('发送失败，请重试！', { icon: 2 });
            }
        });
    });
});