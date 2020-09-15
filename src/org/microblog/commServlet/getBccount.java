package org.microblog.commServlet;


import org.microblog.dbconnect.Comment.CommentMethods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class getBccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String blogId = req.getParameter("blogId");//微博评论数
        int bid = Integer.parseInt(blogId);
        int count = new CommentMethods().getCommentDaoQuery().findBlogcount(bid);
        resp.getWriter().print(count);
    }
}
