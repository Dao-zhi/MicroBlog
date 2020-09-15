$(function(){
    function getCookie(c_name)
    {
        if (document.cookie.length>0)
        {
            c_start = document.cookie.indexOf(c_name + "=")
            if (c_start!=-1)
            {
                c_start = c_start + c_name.length+1
                c_end = document.cookie.indexOf(";",c_start)
                if (c_end==-1) c_end = document.cookie.length
                return unescape(document.cookie.substring(c_start,c_end))
            }
        }
        return ""
    }
    //保存个人信息修改
    $("#save_profile").click(function () {
        //document.cookie = "User_id=173";//设置cookie
        //var User_id = document.cookie.replace(/(?:(?:^|.*;\s*)User_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");//读取cookie
        var User_id = getCookie("user_id");
        // console.log(User_id);
        var birthday = $('#birthday').val();
        var username = $('#username').val();
        var pwd = $('#new_password').val();
        var info = $('#info').val();
        var address = $('#address').val();
        var gender = $('#gender').val();
        var oldpwd = $('#old_password').val();
        $.post("/user/updateServlet",{
            "User_id":User_id,
            "birthday":birthday,
            "username":username,
            "pwd":pwd,
            "info":info,
            "address":address,
            "gender":gender,
            "oldpwd":oldpwd
        },function (res) {
            if(res==="success"){
                layer.msg('修改成功！', { icon: 1 });
            }else if (res === "error")
            {
                layer.msg('密码错误！', { icon: 2 });
            }else if (res==="repeat")
            {
                layer.msg('用户名已存在！', { icon: 2 });
            }else if(res === "failed"){
                layer.msg('修改失败，请重试！', { icon: 2 });
            }
        });
    });
});