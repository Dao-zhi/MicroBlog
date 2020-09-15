package org.microblog.dbconnect.User.dao;

import org.microblog.dbconnect.User.vo.User;

import java.util.List;

public interface UserDao {
    boolean testUserName(String User_name);
    User getUser(int User_id);
    boolean getRstate(User user);
    User getLstate(User user);
    boolean getUstate(User user);
    List<User> getFan(int User_id);
    List<User> getIdol(int User_id);
    boolean concern(int userId, int concernId);
    boolean noconcern(int userId, int concernId);
}
