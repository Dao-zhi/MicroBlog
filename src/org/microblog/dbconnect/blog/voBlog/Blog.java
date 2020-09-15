package org.microblog.dbconnect.blog.voBlog;

import java.util.Date;

public class Blog {
    public void setBlog(int Blog_id, String Blog_content, Date Blog_time){
        this.Blog_id=Blog_id;
        this.Blog_content=Blog_content;
        this.Blog_time=Blog_time;
    }
    public int getBlog_id() {
        return Blog_id;
    }

    public void setBlog_id(int blog_id) {
        Blog_id = blog_id;
    }

    public String getBlog_content() {
        return Blog_content;
    }

    public void setBlog_content(String blog_content) {
        Blog_content = blog_content;
    }

    public Date getBlog_time() {
        return Blog_time;
    }
    public void setUser_id(int user_id){User_id=user_id;}
    public int getUser_id(){return User_id;}

    public void setBlog_time(Date blog_time) {
        Blog_time = blog_time;
    }
    public void setUser_name(String user_name){User_name=user_name;}
    public String getUser_name(){return User_name;}

    private int Blog_id;
    private String Blog_content;
    private Date Blog_time;
    private int User_id;
    private String User_name;

}
