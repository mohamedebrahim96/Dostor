package com.vacuum.app.dostor.Dostor2014.Comments;

/**
 * Created by Home on 10/3/2017.
 */

public class Comment_Model {
    private String user_name, user_image,comment,comment_Date,likes,dislikes;


    public Comment_Model(String comment)
    {
        this.comment = comment;
    }
    //===========================================================
    public Comment_Model(String user_name,String comment,String user_image,String comment_Date,String likes,String dislikes)
    {
        this.user_name = user_name;
        this.comment = comment;
        this.user_image = user_image;
        this.comment_Date = comment_Date;
        this.likes = likes;
        this.dislikes = dislikes;

    }
    //===========================================================

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment_Date() {
        return comment_Date;
    }

    public void setComment_Date(String comment_Date) {
        this.comment_Date = comment_Date;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
    }
}
