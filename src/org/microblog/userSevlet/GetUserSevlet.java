package org.microblog.userSevlet;

import com.alibaba.fastjson.JSON;
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

public class GetUserSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        doPost(req,resp);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("User_id"));
        PrintWriter out = resp.getWriter();
        UserDao userdao = Factory.getUserDao(new ConnectionDatabase().getConnection());
        User user = new User();
        user = userdao.getUser(id);
        String s = JSON.toJSONString(user);
        out.print(s);
    }
}
