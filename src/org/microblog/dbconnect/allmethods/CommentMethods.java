package org.microblog.dbconnect.allmethods;

import org.microblog.dbconnect.Comment.daoComment.CommentDaoDelete;
import org.microblog.dbconnect.Comment.daoComment.CommentDaoInsert;
import org.microblog.dbconnect.Comment.daoComment.CommentDaoQuery;
import org.microblog.dbconnect.Comment.daoComment.impl.CommentDaoimplDelete;
import org.microblog.dbconnect.Comment.daoComment.impl.CommentDaoimplInsert;
import org.microblog.dbconnect.Comment.daoComment.impl.CommentDaoimplQuery;

public class CommentMethods {
    public CommentDaoQuery getCommentDaoQuery(){
        return new CommentDaoimplQuery();
    }
    public CommentDaoDelete getCommentDaoDelete(){
        return new CommentDaoimplDelete();
    }
    public CommentDaoInsert getCommentDaoInsert(){
        return new CommentDaoimplInsert();
    }
}
