package org.microblog.dbconnect.blog.daoBlog;

import org.microblog.dbconnect.blog.voBlog.Blog;

public interface BlogDaoUpdate {
    boolean getBstate(Blog blog, String content);//修改内容
}
