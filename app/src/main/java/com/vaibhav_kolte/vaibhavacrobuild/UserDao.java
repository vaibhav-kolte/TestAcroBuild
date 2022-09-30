package com.vaibhav_kolte.vaibhavacrobuild;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM user WHERE userId =:userId and user_password =:userPassword")
    User getUser(String userId, String userPassword);

    @Query("SELECT EXISTS(SELECT * FROM user WHERE userId =:userId )")
    Boolean is_exits(String userId);

    @Query("DELETE FROM user WHERE userId =:userId")
    void deleteUser(String userId);

    @Query("UPDATE user set user_name =:userName, user_password =:password WHERE userId =:userId")
    void updateUser(String userId,String userName,String password);

}
