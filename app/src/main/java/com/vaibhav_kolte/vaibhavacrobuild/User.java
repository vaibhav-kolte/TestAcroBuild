package com.vaibhav_kolte.vaibhavacrobuild;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {


    @NonNull
    @ColumnInfo(name = "userId")
    @PrimaryKey
    public String userName;

    @ColumnInfo(name = "user_name")
    public String name;

    @ColumnInfo(name = "user_password")
    public String password;


    public User(String userName, String name, String password) {
        this.userName = userName;
        this.name = name;
        this.password = password;
    }
}
