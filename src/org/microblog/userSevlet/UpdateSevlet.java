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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UpdateSevlet extends HttpServlet {
    private void change(User user,PrintWriter out) {
        UserDao userdao = Factory.getUserDao(new ConnectionDatabase().getConnection());
        /*boolean tflag = userdao.testUserName(user.getName());
        if (tflag) {
            out.print("repeat");
        } else {

            boolean flag = userdao.getUstate(user);
            if (flag) {
                out.print("success");
            } else {
                out.print("failed");
            }
        }*/
        boolean flag = userdao.getUstate(user);
        if (flag) {
            out.print("success");
        } else {
            out.print("failed");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        User user = new User();
        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        int id = Integer.parseInt(req.getParameter("User_id"));
        String sbirthday = req.getParameter("birthday");
        String name = req.getParameter("username");
        String pwd = req.getParameter("pwd");
        String oldpwd = req.getParameter("oldpwd");
        String info = req.getParameter("info");
        String address = req.getParameter("address");
        int gender = 0;
        try{
            gender = Integer.parseInt(req.getParameter("gender"));
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            Date birthday = new Date(sdf.parse(sbirthday).getTime());
            user.setBirthday(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setAddress(address);
        user.setInfo(info);
        user.setGender(gender);
        user.setName(name);
        user.setId(id);
        UserDao userdao = Factory.getUserDao(new ConnectionDatabase().getConnection());
        User user1 = userdao.getUser(id);
        if (pwd.equals("")) {
            user.setPwd(user1.getPwd());
            change(user,out);
        } else {
            if (oldpwd.equals(user1.getPwd())) {
                user.setPwd(pwd);
                change(user,out);
            } else {
                out.print("error");
            }
        }
    }
}