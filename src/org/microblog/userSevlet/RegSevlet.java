package org.microblog.userSevlet;

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

public class RegSevlet extends HttpServlet{
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        doPost(req,resp);
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        PrintWriter out = resp.getWriter();
        UserDao userdao = Factory.getUserDao(new ConnectionDatabase().getConnection());
        User user = new User();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        boolean answer = userdao.testUserName(name);
        if (answer){
            out.print("error");//重名
        }else {
            user.setName(name);
            user.setPwd(pwd);
            user.setTime(time);
            boolean flag = userdao.getRstate(user);
            //System.out.println(flag);
            out.print(flag);

        }
    }
}
