package org.microblog.dbconnect.blog.daoBlog.impl;
import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.blog.daoBlog.BlogDaoDelete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BlogDaoimplDelete implements BlogDaoDelete {

    @Override
    public boolean blogDeleteByid(int id) {
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr1 = null;
        PreparedStatement pr2 = null;
        PreparedStatement pr3 = null;
        String sql1 = "DELETE FROM blog WHERE Blog_id = ?";
        String sql2 = "DELETE FROM ub WHERE Blog_id = ?";
        String sql3 = "DELETE FROM bc WHERE Blog_id = ?";
        //还应该删除comment、CC、uc表
        try {
            pr1= conn.prepareStatement(sql1);
            pr1.setInt(1, id);
            pr2= conn.prepareStatement(sql2);
            pr2.setInt(1, id);
            pr3= conn.prepareStatement(sql3);
            pr3.setInt(1, id);
            pr3.executeUpdate();
            if (pr1.executeUpdate() != 0&&pr2.executeUpdate() != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if(pr1!=null)
                        pr1.close();
                    if(pr2!=null)
                        pr2.close();
                    if(conn!=null)
                        conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        return false;
    }
}
