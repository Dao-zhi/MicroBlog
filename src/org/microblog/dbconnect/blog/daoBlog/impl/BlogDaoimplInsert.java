package org.microblog.dbconnect.blog.daoBlog.impl;
import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.blog.daoBlog.BlogDaoInsert;
import org.microblog.dbconnect.blog.voBlog.Blog;

import java.sql.*;
import java.util.Date;

public class BlogDaoimplInsert implements BlogDaoInsert{
    @Override
    public int blogInsert(String content){
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr = null;
        ResultSet rs =null;
        Date utildate = new Date();
        java.sql.Timestamp date = new java.sql.Timestamp(utildate.getTime());
        String sql="INSERT INTO blog(Blog_content,Blog_time) VALUES(?,?)";
        int blog_id=0;
        try {
            pr = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pr.setString(1,content);
            pr.setTimestamp(2,date);
            if(pr.executeUpdate()!=0) {
                rs=pr.getGeneratedKeys();
                if(rs.next())
                    blog_id=rs.getInt(1);
                return blog_id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public boolean Insert(int user_id,int blog_id){
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr = null;
        String sql="INSERT INTO ub(User_id,Blog_id) VALUES(?,?)";
        try {
            pr = conn.prepareStatement(sql);
            pr.setInt(1, user_id);
            pr.setInt(2, blog_id);
            if (pr.executeUpdate() != 0) return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        }
}
