package org.microblog.commServlet;


import org.microblog.dbconnect.Comment.CommentMethods;
import org.microblog.dbconnect.Comment.voComment.Comment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class delcomment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commentid = req.getParameter("commentId");
        String blogId = req.getParameter("blogId");//0:不是微博的评论
        String clId = req.getParameter("clId");//0:不是评论的评论
        int cid = Integer.parseInt(commentid);
        int bid = Integer.parseInt(blogId);
        int ccid = Integer.parseInt(clId);
        CommentMethods cmd = new CommentMethods();
        Comment comment = new Comment();
        comment.setCid(cid);
        comment.setBid(bid);
        comment.setCcid(ccid);
        boolean flag = cmd.getCommentDaoDelete().DeleteComment(comment);
        PrintWriter out = resp.getWriter();
        if(flag == true){
            out.print("true");
        }
        else{
            out.print("flase");
        }
    }
}
