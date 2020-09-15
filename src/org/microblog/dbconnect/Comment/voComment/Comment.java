package org.microblog.dbconnect.Comment.voComment;

import java.sql.Timestamp;

public class Comment {
    private int cid;
    private String content;
    private Timestamp time;
    private int bid;
    private int uid;
    private int ccid;                                           //回复的是哪一个评论，若为0代表不是回复的评论
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Comment(int cid, String content, Timestamp time, int bid, int uid,int ccid) {
        this.cid = cid;
        this.content = content;
        this.time = time;
        this.bid = bid;
        this.uid = uid;
        this.ccid = ccid;
    }
    public Comment(String content, Timestamp time, int bid, int uid,int ccid) {
        this.content = content;
        this.time = time;
        this.bid = bid;
        this.uid = uid;
        this.ccid = ccid;
    }

    public int getCcid() {
        return ccid;
    }

    public void setCcid(int ccid) {
        this.ccid = ccid;
    }

    public Comment(){

    }
}
