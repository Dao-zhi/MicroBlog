package org.microblog.commServlet;


import org.microblog.dbconnect.Comment.CommentMethods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class getCccount extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commentId = req.getParameter("commentId");//评论评论数
        int ccid = Integer.parseInt(commentId);
        CommentMethods cmd = new CommentMethods();
        int count = cmd.getCommentDaoQuery().findCcount(ccid);
        resp.getWriter().print(count);
    }
}
