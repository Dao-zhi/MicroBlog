package org.microblog.blogServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.User.dao.UserDao;
import org.microblog.dbconnect.User.dao.impl.UserDaoImpl;
import com.alibaba.fastjson.JSON;
import org.microblog.dbconnect.User.factory.Factory;
import org.microblog.dbconnect.User.vo.User;
import org.microblog.dbconnect.blog.voBlog.Blog;
import org.microblog.dbconnect.allmethods.blogMethods;

public class BlogHotServlet extends HttpServlet {    //热搜
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        blogMethods method = new blogMethods();
        List<Blog> list_blog=new ArrayList<>();
        PrintWriter out = resp.getWriter();
        list_blog = method.getBlogQuery().getHotBlog();
        for(Blog blog:list_blog) {
            User user=new User();
            UserDao userdao = Factory.getUserDao(new ConnectionDatabase().getConnection());
            user=userdao.getUser(blog.getUser_id());
            blog.setUser_name(user.getName());
            //System.out.println(blog.getBlog_id()+"--"+blog.getUser_name()+"--"+blog.getBlog_content());
        }
        String s = JSON.toJSONString(list_blog);
        System.out.println(s);
        out.println(s);
    }
}
