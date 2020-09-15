package org.microblog.userSevlet;

import com.alibaba.fastjson.JSON;
import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.User.dao.UserDao;
import org.microblog.dbconnect.User.factory.Factory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class IdolSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        //关注人列表
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("userId"));
        UserDao userdao = Factory.getUserDao(new ConnectionDatabase().getConnection());
        String s = JSON.toJSONString(userdao.getIdol(id));
        out.print(s);
    }
}
