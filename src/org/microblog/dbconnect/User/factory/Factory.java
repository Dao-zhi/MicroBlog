package org.microblog.dbconnect.User.factory;

import org.microblog.dbconnect.User.dao.UserDao;
import org.microblog.dbconnect.User.dao.impl.UserDaoImpl;

import java.sql.Connection;

public class Factory {
    public static UserDao getUserDao(Connection conn){
        return new UserDaoImpl(conn);
    }
}
