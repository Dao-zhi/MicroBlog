package org.microblog.commServlet;


import com.alibaba.fastjson.JSON;
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
import java.util.ArrayList;
import java.util.List;

public class getCommslet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String blogid = req.getParameter("blogId");//评论获取
        int bid = Integer.parseInt(blogid);
        PrintWriter out=resp.getWriter();
        List<Comment> commentList = new ArrayList<>();
        CommentMethods cmd = new CommentMethods();
        commentList = cmd.getCommentDaoQuery().findBlogcomment(bid);
        for(Comment comment : commentList){
            UserDao userdao  = Factory.getUserDao(new ConnectionDatabase().getConnection());
            User user = userdao.getUser(comment.getUid());
            comment.setUsername(user.getName());
            //System.out.println(comment.getUsername());
        }
        String commentlist = JSON.toJSONString(commentList);
        out.print(commentlist);
    }
}
