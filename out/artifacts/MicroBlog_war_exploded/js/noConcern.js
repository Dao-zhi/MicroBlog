function noconcern(concernId) {
    layer.confirm('确认取关？', {
            btn: ['我考虑好了', '我再想想'] //按钮
        }, function () {
            // var userId = document.cookie.replace(/(?:(?:^|.*;\s*)user_id\s*\=\s*([^;]*).*$)|^.*$/, "$1");//读取cookie
            var userId = getCookie("user_id");
            $.post("/user/noconcernServlet", {
                "userId": userId,
                "concernId":concernId
            }, function (res) {
                if (res === "true") {
                    layer.msg('再见 ！亲爱的', {icon: 1});
                    $('#'+concernId+'action_bar_button').css('color', '#393f4f');
                } else {
                    layer.msg('取关失败，可能是他舍不得你！', {icon: 2});
                }
            });

        }, function () {
            layer.msg('你还是舍不得我', {icon: 3});
        }
    );
}