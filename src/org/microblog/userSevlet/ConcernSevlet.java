package org.microblog.userSevlet;

import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.User.dao.UserDao;
import org.microblog.dbconnect.User.factory.Factory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ConcernSevlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        //关注
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        int userId = Integer.parseInt(req.getParameter("userId"));
        int concernId = Integer.parseInt(req.getParameter("concernId"));
        UserDao userdao = Factory.getUserDao(new ConnectionDatabase().getConnection());
        boolean flag = userdao.concern(userId,concernId);
        out.print(flag);
    }
}
