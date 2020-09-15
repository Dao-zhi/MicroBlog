package org.microblog.dbconnect.Comment.daoComment.impl;

import org.microblog.dbconnect.Comment.daoComment.CommentDaoInsert;
import org.microblog.dbconnect.Comment.voComment.Comment;
import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.DisConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDaoimplInsert implements CommentDaoInsert {

    @Override
    public boolean CommentInsert(Comment comment) {
        Connection conn = new ConnectionDatabase().getConnection();
        int cid = 0;
        int bid = comment.getBid();
        int uid = comment.getUid();
        PreparedStatement pr_comment = null;
        PreparedStatement pr_bc = null;
        PreparedStatement pr_uc = null;
        PreparedStatement pr_cid = null;
        ResultSet rs_cid = null;
        String sql_comment = "INSERT INTO Comment(Comment_content,Comment_time) VALUES(?,?)";
        String sql_bc = "INSERT INTO bc(Blog_id,Comment_id) VALUES(?,?)";
        String sql_uc = "INSERT INTO uc(User_id,Comment_id) VALUES(?,?)";
        String sql_cid = "SELECT Comment_id FROM Comment ORDER BY Comment_id DESC LIMIT 1";
        try {
            pr_comment = conn.prepareStatement(sql_comment);
            pr_comment.setString(1, comment.getContent());
            pr_comment.setTimestamp(2, comment.getTime());
            if(pr_comment.executeUpdate()!=0){
                pr_cid = conn.prepareStatement(sql_cid);
                rs_cid = pr_cid.executeQuery();
                if(rs_cid.next()){
                    comment.setCid(rs_cid.getInt("Comment_id"));
                }
                pr_bc = conn.prepareStatement(sql_bc);
                pr_bc.setInt(1,bid);
                pr_bc.setInt(2,comment.getCid());
                pr_uc = conn.prepareStatement(sql_uc);
                pr_uc.setInt(1,uid);
                pr_uc.setInt(2,comment.getCid());
                if ((pr_bc.executeUpdate()!=0)&&(pr_uc.executeUpdate()!= 0)){
                    if(pr_bc!=null)
                        pr_bc.close();
                    if(pr_uc!=null)
                        pr_uc.close();
                    if(pr_cid!=null)
                        pr_cid.close();
                    if(rs_cid!= null)
                        rs_cid.close();
                    new DisConnectionDatabase().disConnectionDatabase(conn,pr_comment);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DisConnectionDatabase().disConnectionDatabase(conn, pr_comment);
        }
        return false;
    }

    @Override
    public boolean CcInsert(Comment comment) {
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr_c = null;
        PreparedStatement pr_cc = null;
        PreparedStatement pr_uc = null;
        PreparedStatement pr_cid = null;
        ResultSet rs_cid = null;
        String sql_cid = "SELECT Comment_id FROM Comment ORDER BY Comment_id DESC LIMIT 1";
        String sql_c = "INSERT INTO comment(Comment_content,Comment_time) VALUES(?,?)";
        String sql_cc = "INSERT INTO cc(Comment_id,CL_id) VALUES(?,?)";
        String sql_uc = "INSERT INTO uc(User_id,Comment_id) VALUES(?,?)";
        try{
            pr_c = conn.prepareStatement(sql_c);
            pr_c.setString(1,comment.getContent());
            pr_c.setTimestamp(2,comment.getTime());
            if(pr_c.executeUpdate()!=0) {
                pr_cid = conn.prepareStatement(sql_cid);
                rs_cid = pr_cid.executeQuery();
                if(rs_cid.next()){
                    comment.setCid(rs_cid.getInt("Comment_id"));
                }
                pr_cc = conn.prepareStatement(sql_cc);
                pr_cc.setInt(1,comment.getCid());
                pr_cc.setInt(2,comment.getCcid());
                pr_uc = conn.prepareStatement(sql_uc);
                pr_uc.setInt(1,comment.getUid());
                pr_uc.setInt(2,comment.getCid());
                if((pr_cc.executeUpdate()!=0)&&pr_uc.executeUpdate()!=0){
                    if(pr_uc!=null)
                        pr_uc.close();
                    if(pr_cid!=null)
                        pr_cid.close();
                    if(pr_c!=null)
                        pr_c.close();
                    if(pr_cc!=null)
                        pr_cc.close();
                    return true;
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(pr_uc!=null)
                    pr_uc.close();
                if(pr_cid!=null)
                    pr_cid.close();
                if(pr_c!=null)
                    pr_c.close();
                if(pr_cc!=null)
                    pr_cc.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
