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

public class NoConcernSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
        doPost(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out = resp.getWriter();
        UserDao userdao = Factory.getUserDao(new ConnectionDatabase().getConnection());
        int userId = Integer.parseInt(req.getParameter("userId"));
        int concernId = Integer.parseInt(req.getParameter("concernId"));
        boolean flag = userdao.noconcern(userId,concernId);
        out.print(flag);
    }
}
