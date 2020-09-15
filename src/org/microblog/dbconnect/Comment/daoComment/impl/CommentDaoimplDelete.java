package org.microblog.dbconnect.Comment.daoComment.impl;

import org.microblog.dbconnect.Comment.daoComment.CommentDaoDelete;
import org.microblog.dbconnect.Comment.voComment.Comment;
import org.microblog.dbconnect.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentDaoimplDelete implements CommentDaoDelete {
    @Override
    public boolean DeleteComment(Comment comment) {
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr_c = null;
        String sql_c = "DELETE FROM comment WHERE Comment_id = ?";
        PreparedStatement pr_uc = null;
        String sql_uc ="DELETE FROM uc WHERE Comment_id = ?";
        PreparedStatement pr_cc = null;
        String sql_cc = "DELETE FROM cc WHERE Comment_id = ?";
        PreparedStatement pr_bc = null;
        String sql_bc = "DELETE FROM bc WHERE Comment_id = ?";
        PreparedStatement pr_cl = null;
        String sql_cl = "DELETE FROM cc WHERE CL_id = ?";
        try{
            pr_c = conn.prepareStatement(sql_c);
            pr_c.setInt(1, comment.getCid());
            pr_uc = conn.prepareStatement(sql_uc);
            pr_uc.setInt(1,comment.getCid());
            if((pr_c.executeUpdate()!=0)&& (pr_uc.executeUpdate()!=0)){
                if(comment.getBid()!=0){
                    pr_bc = conn.prepareStatement(sql_bc);
                    pr_bc.setInt(1,comment.getCid());
                    pr_cl =conn.prepareStatement(sql_cl);
                    pr_cl.setInt(1,comment.getCid());
                    pr_cl.executeUpdate();
                    if((pr_bc.executeUpdate()!=0))
                        return true;
                }
                if(comment.getCcid()!=0){
                    pr_cc = conn.prepareStatement(sql_cc);
                    pr_cc.setInt(1,comment.getCid());
                    if(pr_cc.executeUpdate()!=0)
                        return true;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
