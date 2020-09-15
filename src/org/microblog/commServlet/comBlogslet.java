package org.microblog.commServlet;

import org.microblog.dbconnect.Comment.CommentMethods;
import org.microblog.dbconnect.Comment.voComment.Comment;
import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.User.dao.UserDao;
import org.microblog.dbconnect.User.factory.Factory;
import org.microblog.dbconnect.User.vo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

public class comBlogslet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String blogId = req.getParameter("blogId");
        String userId = req.getParameter("userId");
        String content = req.getParameter("comment");
        int bid = Integer.parseInt(blogId);
        int uid = Integer.parseInt(userId);
        PrintWriter out = resp.getWriter();
        CommentMethods cmd = new CommentMethods();
        Comment comment = new Comment();
        comment.setBid(bid);
        UserDao userdao  = Factory.getUserDao(new ConnectionDatabase().getConnection());
        User user = userdao.getUser(uid);
        comment.setUsername(user.getName());
        comment.setContent(content);
        comment.setUid(uid);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        comment.setTime(d);
        boolean flag = cmd.getCommentDaoInsert().CommentInsert(comment);
        if (flag == true) {
            out.print("true");
        }
        else
            out.print("false");
    }
}
