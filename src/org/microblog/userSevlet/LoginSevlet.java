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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class LoginSevlet extends HttpServlet{
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        doPost(req,resp);
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException{
        //req.setCharacterEncoding("utf-8");
        //resp.setCharacterEncoding("utf-8");
        try {
            req.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String name = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        PrintWriter out = resp.getWriter();
        UserDao userdao = Factory.getUserDao(new ConnectionDatabase().getConnection());
        User user = new User();
        user.setName(name);
        user.setPwd(pwd);
        User tuser = userdao.getLstate(user);
        if (tuser.getName()!= null) {
            String s = JSON.toJSONString(tuser);
            out.print(s);
        }else{
            out.print("false");
        }
    }
}
