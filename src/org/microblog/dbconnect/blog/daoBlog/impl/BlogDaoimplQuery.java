package org.microblog.dbconnect.blog.daoBlog.impl;
import java.util.*;

import org.microblog.dbconnect.ConnectionDatabase;
import org.microblog.dbconnect.DisConnection;
import org.microblog.dbconnect.DisConnectionDatabase;
import org.microblog.dbconnect.blog.daoBlog.BlogDaoQuery;
import org.microblog.dbconnect.blog.voBlog.Blog;
import org.microblog.dbconnect.User.dao.impl.UserDaoImpl;
import java.sql.*;


public class BlogDaoimplQuery implements BlogDaoQuery {
    @Override
    public List<Blog> getHotBlog() {
       List<Blog> list_blog = new ArrayList<>();
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr1 = null;
        PreparedStatement pr2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        // String sql1 = "SELECT Blog_id,COUNT(Comment_id) AS CNUM FROM bc GROUP BY Blog_id ORDER BY CNUM DESC ";//显示评论数多的微博
        String sql1 = "SELECT Blog_id FROM ub";
        String sql2="SELECT * from blog WHERE Blog_id=?";
        try{
            pr1 = conn.prepareStatement(sql1);
            rs1 = pr1.executeQuery();
            while (rs1.next()) {
                Blog blog = new Blog();
                pr2 = conn.prepareStatement(sql2);
                pr2.setInt(1,rs1.getInt("Blog_id"));
                rs2 = pr2.executeQuery();
                if(rs2.next()) {
                    setblog(blog, rs2);
                    blog.setUser_id(getUserid(rs1.getInt("Blog_id")));
                    blog.setUser_name("NULL");
                }
                list_blog.add(blog);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        } finally {
            new DisConnection().disconnection(rs1, pr1, conn);
        }
        return list_blog;
      }


    @Override
    public List<Blog> getMyBlog(int User_id) {
        List<Blog> list_blog = new ArrayList<>();
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr1 = null;
        PreparedStatement pr2 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        String sql1 = "SELECT * FROM ub WHERE User_id = ?";
        String sql2 = "SELECT * FROM blog WHERE Blog_id = ?";
        try {
            pr1 = conn.prepareStatement(sql1);
            pr1.setInt(1,User_id);
            rs1 = pr1.executeQuery();
            while (rs1.next()) {
                Blog blog = new Blog();
                pr2 = conn.prepareStatement(sql2);
                pr2.setInt(1, rs1.getInt("Blog_id"));
                rs2 = pr2.executeQuery();
                if (rs2.next()) {
                    setblog(blog, rs2);
                    setUserid(blog,rs1);
                    blog.setUser_name("NULL");
                    list_blog.add(blog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            new DisConnection().disconnection(rs1, pr1, conn);
        }
        return list_blog;
    }

    @Override
    public Blog getBlog(int Blog_id){
        Blog blog=new Blog();
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT * FROM blog WHERE Blog_id = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setInt(1,Blog_id);
            rs = pr.executeQuery();
            if(rs.next()) {
                setblog(blog, rs);
                blog.setUser_name("NULL");
                blog.setUser_id(getUserid(Blog_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            new DisConnection().disconnection(rs,pr,conn);
        }
        return blog;
    }

    public int getUserid(int Blog_id){
        int user_id=0;
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT User_id FROM ub WHERE Blog_id = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setInt(1,Blog_id);
            rs = pr.executeQuery();
            if(rs.next())
                user_id =rs.getInt("User_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            new DisConnection().disconnection(rs,pr,conn);
        }
        return user_id;
    }

    private void setblog(Blog blog, ResultSet rs) {
        try {
            blog.setBlog_id(rs.getInt("Blog_id"));
            blog.setBlog_content(rs.getString("Blog_content"));
            blog.setBlog_time(rs.getTime("Blog_time"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void setUserid(Blog blog, ResultSet rs) {
        try {
            blog.setUser_id(rs.getInt("User_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Blog> getBloggerBlog(int User_id) {
        List<Blog> list_blog = new ArrayList<>();
        Blog blog = new Blog();
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr1 = null;
        PreparedStatement pr2 = null;
        PreparedStatement pr3=null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3=null;
        String sql1="SELECT User_id FROM fan WHERE Fan_id=?";
        String sql2="SELECT * FROM ub WHERE User_id=?";
        String sql3="SELECT * FROM blog WHERE Blog_id=?  ";
        try{
            pr1 = conn.prepareStatement(sql1);
            pr1.setInt(1,User_id);
            rs1 = pr1.executeQuery();
            while (rs1.next()) {
                pr2 = conn.prepareStatement(sql2);
                pr2.setInt(1,rs1.getInt("User_id"));
                rs2 = pr2.executeQuery();
                while (rs2.next()) {
                    pr3 = conn.prepareStatement(sql3);
                    pr3.setInt(1, rs2.getInt("Blog_id"));
                    rs3 = pr3.executeQuery();
                    if(rs3.next()) {
                        blog = new Blog();
                        setblog(blog, rs3);
                        setUserid(blog,rs2);
                        blog.setUser_name("NULL");
                        list_blog.add(blog);
                    }
                }
                Collections.sort(list_blog, new Comparator<Blog>() {
                    @Override
                    public int compare(Blog o1, Blog o2) {
                        if(o1.getBlog_id() > o2.getBlog_id()){
                        return 1;
                    }
                        if(o1.getBlog_id() == o2.getBlog_id()){
                            return 0;
                        }
                        return -1;
                    }
                });
            }
        }catch(SQLException e) {
            e.printStackTrace();
        } finally {
            new DisConnection().disconnection(rs1, pr1, conn);
        }
        return list_blog;
    }
    @Override
    public Blog getRandomBlog(){
        int r=(int)(0+Math.random()*(10));
        Blog blog=new Blog();
        Connection conn = new ConnectionDatabase().getConnection();
        PreparedStatement pr = null;
        ResultSet rs = null;
        String sql = " SELECT * FROM blog WHERE Blog_id = ?";
        try {
            pr = conn.prepareStatement(sql);
            pr.setInt(1,r);
            rs = pr.executeQuery();
            if(rs.next()) {
                setblog(blog, rs);
                blog.setUser_id(getUserid(r));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            new DisConnection().disconnection(rs,pr,conn);
        }
        return blog;
    }

}