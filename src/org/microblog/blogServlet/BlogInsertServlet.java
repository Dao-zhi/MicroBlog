package org.microblog.blogServlet;

import org.microblog.dbconnect.allmethods.blogMethods;
import org.microblog.dbconnect.blog.voBlog.Blog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class BlogInsertServlet extends HttpServlet { //发微博
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        int User_id = Integer.parseInt(req.getParameter("User_id"));
        String article = req.getParameter("Blog_content");
        PrintWriter out = resp.getWriter();
        blogMethods method = new blogMethods();
        int Blog_id = method.getBlogInsert().blogInsert(article);
        if (method.getBlogInsert().Insert(User_id, Blog_id))
            out.print("true");
    }
}