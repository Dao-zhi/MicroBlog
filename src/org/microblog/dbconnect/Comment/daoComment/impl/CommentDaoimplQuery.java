package org.microblog.dbconnect.Comment.daoComment.impl;

import org.microblog.dbconnect.Comment.daoComment.CommentDaoQuery;
import org.microblog.dbconnect.Comment.voComment.Comment;
import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.DisConnectionDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoimplQuery implements CommentDaoQuery {
    @Override
    public List<Comment> findUsercomment(int uid) {
        int bid = 0;
        int cc_id = 0;
        List<Comment> commentList = new ArrayList<>();
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr_cid = null;
        ResultSet rs_cid = null;
        PreparedStatement pr_comment = null;
        ResultSet rs_comment = null;
        PreparedStatement pr_bid = null;
        ResultSet rs_bid = null;
        PreparedStatement pr_cc = null;
        ResultSet rs_cc =null;
        String sql_cc = "SELECT CL_id FROM cc WHERE Comment_id = ?";
        String sql_cid = "SELECT Comment_id FROM uc WHERE User_id = ?";
        String sql_comment = "SELECT * FROM Comment WHERE Comment_id = ?";
        String sql_bid = "SELECT Blog_id FROM bc WHERE Comment_id = ?";
        try {
            pr_cid = conn.prepareStatement(sql_cid);
            pr_cid.setInt(1, uid);
            rs_cid = pr_cid.executeQuery();
            while (rs_cid.next()) {
                pr_comment = conn.prepareStatement(sql_comment);
                int Comment_id = rs_cid.getInt("Comment_id");
                pr_comment.setInt(1, Comment_id);
                rs_comment = pr_comment.executeQuery();
                if (rs_comment.next()) {
                    pr_bid = conn.prepareStatement(sql_bid);
                    pr_bid.setInt(1, Comment_id);
                    rs_bid = pr_bid.executeQuery();
                    if(rs_bid.next()){
                        bid = rs_bid.getInt("Blog_id");
                    }
                    pr_cc = conn.prepareStatement(sql_cc);
                    pr_cc.setInt(1,Comment_id);
                    rs_cc = pr_cc.executeQuery();
                    if(rs_cc.next()){
                        cc_id = rs_cc.getInt("CL_id");
                    }
                    Comment comment = new Comment();
                    comment.setBid(bid);
                    comment.setCcid(cc_id);
                    comment.setCid(rs_comment.getInt("Comment_id"));
                    comment.setContent(rs_comment.getString("Comment_content"));
                    Timestamp timestamp = rs_comment.getTimestamp("Comment_time");
                    comment.setTime(timestamp);
                    comment.setUid(uid);
                    commentList.add(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs_comment != null)
                    rs_comment.close();
                if (pr_comment != null)
                    pr_comment.close();
                if (rs_bid != null)
                    rs_bid.close();
                if (pr_bid != null)
                    pr_bid.close();
                if(rs_cc!=null)
                    rs_cc.close();
                if(pr_cc!=null)
                    pr_cc.close();
                new DisConnectionDatabase().disConnectionDatabase(conn, pr_cid, rs_cid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return commentList;
    }

    @Override
    public int findBlogcount(int bid) {
        int count = 0;
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr = null;
        ResultSet rs = null;
        PreparedStatement pr_c = null;
        ResultSet rs_c = null;
        String sql = "SELECT * FROM bc WHERE Blog_id = ?";
        String sql_c = "SELECT * FROM cc WHERE CL_id = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setInt(1, bid);
            rs = pr.executeQuery();
            while (rs.next()) {
                int comment_id = rs.getInt("Comment_id");
                pr = conn.prepareStatement(sql_c);
                pr.setInt(1, comment_id);
                rs_c = pr.executeQuery();
                while (rs_c.next()) {
                    count++;                                                                                          //加上评论的评论数
                }
                count++;                                                                                              //查找blog评论数
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DisConnectionDatabase().disConnectionDatabase(conn, pr, rs);
        }
        return count;
    }

    @Override
    public List<Comment> findBlogcomment(int bid) {
        List<Comment> commentList = new ArrayList<>();
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr_cid = null;
        ResultSet rs_cid = null;
        PreparedStatement pr_comment = null;
        ResultSet rs_comment = null;
        PreparedStatement pr_uid = null;
        ResultSet rs_uid = null;
        String sql_cid = "SELECT Comment_id FROM bc WHERE Blog_id = ?";
        String sql_comment = "SELECT * FROM Comment WHERE Comment_id = ?";
        String sql_uid = "SELECT User_id FROM uc WHERE Comment_id = ?";
        try {
            pr_cid = conn.prepareStatement(sql_cid);
            pr_cid.setInt(1, bid);
            rs_cid = pr_cid.executeQuery();
            while (rs_cid.next()) {
                pr_comment = conn.prepareStatement(sql_comment);
                int Comment_id = rs_cid.getInt("Comment_id");
                pr_uid = conn.prepareStatement(sql_uid);
                pr_uid.setInt(1, Comment_id);
                rs_uid = pr_uid.executeQuery();
                pr_comment.setInt(1, Comment_id);
                rs_comment = pr_comment.executeQuery();
                if (rs_comment.next() && rs_uid.next()) {
                    Comment comment = new Comment();
                    comment.setUid(rs_uid.getInt("User_id"));
                    comment.setCid(rs_comment.getInt("Comment_id"));
                    comment.setContent(rs_comment.getString("Comment_content"));
                    Timestamp timestamp = rs_comment.getTimestamp("Comment_time");
                    comment.setTime(timestamp);
                    comment.setBid(bid);
                    commentList.add(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pr_comment != null)
                    pr_comment.close();
                if (rs_comment != null)
                    rs_comment.close();
                if (pr_uid != null)
                    pr_uid.close();
                if (rs_uid != null)
                    rs_uid.close();
                new DisConnectionDatabase().disConnectionDatabase(conn, pr_cid, rs_cid);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return commentList;
    }

    @Override
    public int findCcount(int cid) {
        int count = 0;
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM cc WHERE CL_id = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setInt(1, cid);
            rs = pr.executeQuery();
            while (rs.next()) {
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DisConnectionDatabase().disConnectionDatabase(conn, pr, rs);
        }
        return count;
    }

    @Override
    public List<Comment> findCComment(int cid) {
        List<Comment> commentList = new ArrayList<>();
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr_cid = null;
        ResultSet rs_cid = null;
        String sql_cid = "SELECT Comment_id FROM cc WHERE CL_id = ?";
        PreparedStatement pr_comm = null;
        ResultSet rs_comm = null;
        String sql_comm = "SELECT * FROM comment WHERE Comment_id = ?";
        PreparedStatement pr_u = null;
        ResultSet rs_u = null;
        String sql_u = "SELECT User_id FROM uc WHERE Comment_id = ?";
        try {
            pr_cid = conn.prepareStatement(sql_cid);
            pr_cid.setInt(1, cid);
            rs_cid = pr_cid.executeQuery();
            while (rs_cid.next()) {
                int comment_id = rs_cid.getInt("Comment_id");
                pr_comm = conn.prepareStatement(sql_comm);
                pr_comm.setInt(1, comment_id);
                rs_comm = pr_comm.executeQuery();
                pr_u = conn.prepareStatement(sql_u);
                pr_u.setInt(1, comment_id);
                rs_u = pr_u.executeQuery();
                if (rs_comm.next() && rs_u.next()) {
                    Comment comment = new Comment();
                    comment.setCid(comment_id);
                    comment.setUid(rs_u.getInt("User_id"));
                    comment.setTime(rs_comm.getTimestamp("Comment_time"));
                    comment.setContent(rs_comm.getString("Comment_content"));
                    comment.setCcid(cid);
                    commentList.add(comment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs_u != null)
                    rs_u.close();
                if (rs_comm != null)
                    rs_comm.close();
                if (pr_u != null)
                    pr_u.close();
                if (pr_comm != null)
                    pr_comm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            new DisConnectionDatabase().disConnectionDatabase(conn, pr_cid, rs_cid);
        }
        return commentList;
    }
}
