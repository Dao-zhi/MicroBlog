package org.microblog.dbconnect.User;

import com.alibaba.fastjson.JSON;
import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.User.dao.UserDao;
import org.microblog.dbconnect.User.factory.Factory;
import org.microblog.dbconnect.User.vo.User;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;

import static java.lang.System.out;
import static org.microblog.dbconnect.ConnectionDatabase.conn;

public class test {
    public static void main(String[] args){
        UserDao userdao = Factory.getUserDao(new ConnectionDatabase().getConnection());
        boolean flag = userdao.concern(173,175);
        out.println(flag);
    }
}
