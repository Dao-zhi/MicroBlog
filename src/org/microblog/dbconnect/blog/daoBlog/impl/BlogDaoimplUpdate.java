package org.microblog.dbconnect.blog.daoBlog.impl;

import org.microblog.dbconnect.blog.daoBlog.BlogDaoUpdate;
import org.microblog.dbconnect.blog.voBlog.Blog;
import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.DisConnection;
import org.microblog.dbconnect.blog.voBlog.Blog;
import java.sql.*;

public class BlogDaoimplUpdate implements BlogDaoUpdate{
    @Override
    public boolean getBstate(Blog blog,String content){
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr = null;
        String sql = "UPDATE blog SET Blog_content=? WHERE Blog_id = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setString(1,content);
            pr.setInt(2,blog.getBlog_id());
            if(pr.executeUpdate()!=0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(pr!=null) {
                try {
                    pr.close();
                    if(conn!=null)
                        conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
