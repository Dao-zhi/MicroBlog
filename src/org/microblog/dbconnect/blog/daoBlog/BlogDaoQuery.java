package org.microblog.dbconnect.blog.daoBlog;

import org.microblog.dbconnect.blog.voBlog.Blog;


import java.util.List;

public interface BlogDaoQuery {
    List<Blog> getHotBlog();//热搜
    List<Blog> getMyBlog(int User_id);//历史微博
    Blog getBlog(int Blog_id);//查看微博
    int getUserid(int Blog_id);//通过微博查博主
    Blog getRandomBlog();
    List<Blog> getBloggerBlog(int User_id);//查看关注微博



}
