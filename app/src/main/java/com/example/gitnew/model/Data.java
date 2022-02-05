package com.example.gitnew.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "users", indices = @Index(value = {"id"}, unique = true))

public class Data {

    @PrimaryKey(autoGenerate = true)
    private int userId;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;

    @SerializedName("email")
    @ColumnInfo(name = "mail")
    private String mail;

    @SerializedName("gender")
    @ColumnInfo(name = "gender")
    private String gender;

    @SerializedName("status")
    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "comment")
    private String comment;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getComment() {
        return comment;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", status='" + status + '\'' +
                ", comment='" + comment + '\'' +
                ", gender=" + gender +
                '}';
    }

}
