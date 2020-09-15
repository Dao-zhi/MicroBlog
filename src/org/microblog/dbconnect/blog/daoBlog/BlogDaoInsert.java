package org.microblog.dbconnect.blog.daoBlog;

import org.microblog.dbconnect.blog.voBlog.Blog;

public interface BlogDaoInsert {
    int blogInsert(String content);
    boolean Insert(int user_id, int blog_id);
}
