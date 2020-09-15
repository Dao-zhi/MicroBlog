package org.microblog.dbconnect.User.dao.impl;

import org.microblog.dbconnect.User.dao.UserDao;
import org.microblog.dbconnect.User.vo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Connection conn;
    private boolean caution = true;

    public UserDaoImpl(Connection conn) {
        this.conn = conn;
    }

    private boolean testUser(int User_id) { //验证该id是否存在
        boolean flag = false;
        String sql = "SELECT * from user WHERE User_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, User_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean testUserName(String User_name) { //验证该name是否存在
        boolean flag = false;
        String sql = "SELECT * from user WHERE User_name = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, User_name);
            rs = ps.executeQuery();
            if (rs.next()) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public User getUser(int User_id) { //不存在该用户则返回null，空内容返回null
        User user = new User();
        String sql = "SELECT * from user WHERE User_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, User_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("User_id"));
                user.setGender(rs.getInt("User_gender"));
                user.setName(rs.getString("User_name"));
                user.setPwd(rs.getString("User_pwd"));
                user.setAddress(rs.getString("User_address"));
                user.setInfo(rs.getString("User_info"));
                user.setBirthday(rs.getDate("User_birthday"));
                user.setTime(rs.getTimestamp("User_time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null & caution)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null & caution)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public List<User> getFan(int User_id) {
        List<User> list_user = new ArrayList<>();
        String sql = "SELECT Fan_id From fan WHERE User_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, User_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                caution = false;
                User user = getUser(rs.getInt("Fan_id"));
                user.setPwd(null);
                list_user.add(user);
            }
            caution = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list_user;
    }
    @Override
    public List<User> getIdol(int User_id) {
        List<User> list_user = new ArrayList<>();
        String sql = "SELECT User_id From fan WHERE Fan_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, User_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                caution = false;
                User user = getUser(rs.getInt("User_id"));
                user.setPwd(null);
                list_user.add(user);
            }
            caution = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (rs != null)
                    rs.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list_user;
    }
    @Override
    public User getLstate(User tuser){//用户名，密码正确时返回true，否则返回false
        User user = new User();
        String name = tuser.getName();
        String pwd = tuser.getPwd();
        String sql = "SELECT * from user WHERE User_name=? AND User_pwd=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,pwd);
            rs = ps.executeQuery();
            if (rs.next()){
                user.setId(rs.getInt("User_id"));
                user.setGender(rs.getInt("User_gender"));
                user.setName(rs.getString("User_name"));
                user.setAddress(rs.getString("User_address"));
                user.setInfo(rs.getString("User_info"));
                user.setBirthday(rs.getDate("User_birthday"));
                user.setTime(rs.getTimestamp("User_time"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(ps != null)
                    ps.close();
                if(rs != null)
                    rs.close();
                if(conn != null)
                    conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return user;
    }
    @Override
    public boolean getRstate(User user){//如果id重复或不为空字段为空则false，成功则ture，不指定id时顺延
        boolean flag = false;
        int count;
        String sql = "INSERT INTO user(User_id,User_name,User_pwd,User_time) values (?,?,?,?)";
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1,user.getId());
            ps.setString(2,user.getName());
            ps.setString(3,user.getPwd());
            ps.setTimestamp(4,user.getTime());
            count = ps.executeUpdate();
            if (count != 0)
                flag = true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(ps != null)
                    ps.close();
                if(conn != null)
                    conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return flag;
    }
    @Override
    public boolean concern (int userId, int concernId){//向fan表中插入关注与取关的项，用户不存在或关注失败返回false
        boolean flag = false;
        int count;
        String sql = "INSERT INTO fan(Fan_id,User_id) values (?,?)";
        PreparedStatement ps = null;
        try {
            if (!testUser(userId) | !testUser(concernId))
                return false;
            ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.setInt(2,concernId);
            count = ps.executeUpdate();
            //System.out.println(count);
            if (count != 0)
                flag = true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(ps != null)
                    ps.close();
                if(conn != null)
                    conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return flag;
    }
    @Override
    public boolean noconcern (int userId,int concernId){//不存在或删除失败返回false，成功返回true
        boolean flag = false;
        int count;
        String sql = "DELETE From fan WHERE Fan_id=? and User_id=?";
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);
            ps.setInt(2,concernId);
            count = ps.executeUpdate();
            if (count!=0)
                flag = true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
    @Override
    public boolean getUstate (User user){//不存在或非空字段为空返回false，成功返回true(修改内容为删除user_time的修改)
        boolean flag = false;
        int count;
        String sql = "UPDATE user SET User_name=?, User_pwd=?," +
                " User_address=?, User_birthday=?, User_gender=?, User_info=? " +
                "WHERE User_id=?";
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPwd());
            ps.setString(3,user.getAddress());
            ps.setDate(4,user.getBirthday());
            ps.setInt(5,user.getGender());
            ps.setString(6,user.getInfo());
            ps.setInt(7,user.getId());
            count = ps.executeUpdate();
            if(count!=0)
                flag = true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(ps != null)
                    ps.close();
                if(conn != null)
                    conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return flag;
    }
}
