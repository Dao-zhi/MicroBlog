$(function(){
	$(".login_button").click(function(){
		$("#log_reg-log").fadeToggle();
		$(".log_reg-background").fadeToggle();
	});
	$("#log-quit").click(function(){
		$("#log_reg-log").fadeToggle();
		$(".log_reg-background").fadeToggle();
	});
	$("#reg-a").click(function(){
		$("#log_reg-log").slideToggle("slow",function(){
			$("#log_reg-reg").slideToggle();
		});
		
	});
	$("#log-a").click(function(){
		$("#log_reg-reg").slideToggle("slow",function(){
			$("#log_reg-log").slideToggle();
		});
		
	});
	$("#reg-quit").click(function(){
		$("#log_reg-reg").fadeToggle();
		$(".log_reg-background").fadeToggle();
	});

	//登陆
	$("#log_button").click(function () {
		//console.log("开始登录")
		var name=$("#log_name").val();
		var pwd=$("#log_pwd").val();
		if(name===""){
			layer.msg("用户名不能为空！",{icon: 2});
			return false;
		}
		if(pwd===""){
			layer.msg("密码不能为空！", {icon: 2});
			return false;
		}
		$.post("/user/loginServlet",{
			"username":name,
			"pwd":pwd
		},function (res) {
			if(res==="false"){
				$("#log_pwd:password").val("");
				$("#log_pwd").attr("placeholder","用户名或密码错误");
			}
			else {
			    var json=JSON.parse(res);
			    //console.log(res);
				layer.msg('登录成功', { icon: 1 });
                document.cookie = "user_id="+json.id+";path=/";//设置cookie
                //var myCookie = document.cookie.replace(/(?:(?:^|.*;\s*)test2\s*\=\s*([^;]*).*$)|^.*$/, "$1");
                document.cookie = "user_name="+json.name+";path=/";
                setTimeout(function () {
					window.location.href="home.html";
				},1000);

			}
		});
	});

	//注册
	//用户名
	$("#reg_name").blur(function () {
		if($("#reg_name").val()===""){
			$("#reg_name").attr("placeholder","用户名不能为空");
		}
	});
	//用户密码
	$("#reg_pwd").blur(function () {
		if($("#reg_pwd").val().length<=6){
			$("#reg_pwd:password").val("");
			$("#reg_pwd").attr("placeholder","密码最少6位");
		}
	});
	//确认密码
	$("#reg_pwd_again").blur(function () {
		if($("#reg_pwd_again").val()!==$("#reg_pwd").val()){
			$("#reg_pwd_again:password").val("");
			$("#reg_pwd_again").attr("placeholder","两次输入密码不一致");
		}
	});

	$("#reg_button").click(function () {
		if($("#reg_name").val()===""
			|$("#reg_pwd").val()===""
			|$("#reg_pwd_again").val()===""){
			$("#reg_pwd_again").attr("placeholder","请将信息填写完整");
			$("#reg_pwd").attr("placeholder","请将信息填写完整");
			$("#reg_name").attr("placeholder","请将信息填写完整");
		}else{
			$.post("/user/regServlet",{
				"username":$("#reg_name").val(),
				"pwd":$("#reg_pwd").val()
			},function (res) {
				if(res==="error"){
					$("#reg_name").val("");
					$("#reg_name").attr("placeholder","用户名已存在");
				}
				else {
					//console.log(res);
					layer.msg('注册成功，请重新登陆', { icon: 1 });
					setTimeout(function () {
						window.location.href="index.html";
					},1000);

				}
			});
		}
	});
});