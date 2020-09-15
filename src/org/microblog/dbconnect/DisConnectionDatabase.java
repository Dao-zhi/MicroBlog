package org.microblog.dbconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisConnectionDatabase {
    public void disConnectionDatabase(Connection conn, PreparedStatement pr, ResultSet rs){
        try{
            if(rs!=null)
                rs.close();
            if(pr!=null)
                pr.close();
            if(conn!=null)
                conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void disConnectionDatabase(Connection conn, PreparedStatement pr){
        try{
            if(pr!=null)
                pr.close();
            if(conn!=null)
                conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
