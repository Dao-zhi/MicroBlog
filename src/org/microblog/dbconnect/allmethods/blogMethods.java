package org.microblog.dbconnect.allmethods;

import org.microblog.dbconnect.blog.daoBlog.BlogDaoDelete;
import org.microblog.dbconnect.blog.daoBlog.BlogDaoInsert;
import org.microblog.dbconnect.blog.daoBlog.BlogDaoQuery;
import org.microblog.dbconnect.blog.daoBlog.BlogDaoUpdate;
import org.microblog.dbconnect.blog.daoBlog.impl.BlogDaoimplDelete;
import org.microblog.dbconnect.blog.daoBlog.impl.BlogDaoimplInsert;
import org.microblog.dbconnect.blog.daoBlog.impl.BlogDaoimplQuery;
import org.microblog.dbconnect.blog.daoBlog.impl.BlogDaoimplUpdate;

public class blogMethods {
    public BlogDaoUpdate getBlogUpdate(){return new BlogDaoimplUpdate();}
    public BlogDaoDelete getBlogDelete(){return new BlogDaoimplDelete();}
    public BlogDaoInsert getBlogInsert(){return new BlogDaoimplInsert();}
    public BlogDaoQuery getBlogQuery(){return new BlogDaoimplQuery();}

}
