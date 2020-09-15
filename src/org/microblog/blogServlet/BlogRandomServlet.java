package org.microblog.blogServlet;

import com.alibaba.fastjson.JSON;
import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.User.dao.UserDao;
import org.microblog.dbconnect.User.factory.Factory;
import org.microblog.dbconnect.User.vo.User;
import org.microblog.dbconnect.allmethods.blogMethods;
import org.microblog.dbconnect.blog.voBlog.Blog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class BlogRandomServlet extends HttpServlet {//显示随机微博
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out=resp.getWriter();
        Blog blog = new Blog();
        blogMethods method = new blogMethods();
        blog = method.getBlogQuery().getRandomBlog();
        User user=new User();
        UserDao userdao = Factory.getUserDao(new ConnectionDatabase().getConnection());
        user=userdao.getUser(blog.getUser_id());
        blog.setUser_name(user.getName());
        String s= JSON.toJSONString(blog);
        out.print(s);
    }
}
