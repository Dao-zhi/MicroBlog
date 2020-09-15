package org.microblog.dbconnect.Comment.daoComment;

import org.microblog.dbconnect.Comment.voComment.Comment;

public interface CommentDaoDelete {
    boolean DeleteComment(Comment comment);             //删除一条评论,根据comment其中的ccid和bid是否为零判断为blog评论还是评论的评论
}
