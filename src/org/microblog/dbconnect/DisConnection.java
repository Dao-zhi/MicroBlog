package org.microblog.dbconnect;

import java.sql.*;

public class DisConnection {
    public void disconnection(ResultSet rs, Statement stmt, Connection conn){
            try {
                if(rs!=null)
                    rs.close();
                if(stmt!= null)
                    stmt.close();
                if(conn!= null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    public void disconnection(ResultSet rs, PreparedStatement pr, Connection conn){
            try {
                if(rs!=null)
                    rs.close();
                if(pr!= null)
                    pr.close();
                if(conn!=null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
