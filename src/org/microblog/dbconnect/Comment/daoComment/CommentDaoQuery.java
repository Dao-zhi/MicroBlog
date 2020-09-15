package org.microblog.dbconnect.Comment.daoComment;

import org.microblog.dbconnect.Comment.voComment.Comment;

import java.util.List;

public interface CommentDaoQuery {
    List<Comment> findUsercomment(int uid);                  //查找某人的历史评论
    int findBlogcount(int bid);                             //查找某个blog的评论数，包含评论的评论
    List<Comment> findBlogcomment(int bid);                 //查找某blog的评论,不包含评论的评论
    int findCcount(int cid);                                //查找某个评论的评论数
    List<Comment> findCComment(int cid);                    //查找某个评论的所有评论
}
