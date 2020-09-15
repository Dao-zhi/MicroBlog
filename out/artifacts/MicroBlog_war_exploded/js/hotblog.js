$(function () {
   $("#hotblog").click(function () {
       $(".test").remove();
           $('.homepage').fadeOut();
           $('.profile').fadeOut();
           var blog=new Object();
           var comment=new Object();
           $.post("/blog/blogHotServlet",
               {},function (res) {
                   var json=JSON.parse(res);
                   console.log(json);
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

   })
});