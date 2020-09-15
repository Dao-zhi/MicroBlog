package org.microblog.dbconnect.Comment.daoComment;

import org.microblog.dbconnect.Comment.voComment.Comment;

public interface CommentDaoInsert {
    boolean CommentInsert(Comment comment);         //增加新评论，对blog的评论
    boolean CcInsert(Comment comment);              //增加新坪路，对comment的评论
}
