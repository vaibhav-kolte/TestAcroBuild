package com.vaibhav_kolte.vaibhavacrobuild;

import android.content.Context;

public class MyDatabase {

    private AppDatabase db;
    private UserDao userDao;

    public MyDatabase(Context _context) {
        try {
            db = AppDatabase.getInstance(_context);
            userDao = db.userDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerUser(String userName, String name, String password) {
        try {
            userDao.insert(new User(userName, name, password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User verifyUser(String userId, String password) {
        try {
            return userDao.getUser(userId, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUserExits(String userId) {
        try {
            return userDao.is_exits(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteUser(String userId) {
        try {
            userDao.deleteUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(String userId, String userName,String password) {
        try {
            userDao.updateUser(userId, userName,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
