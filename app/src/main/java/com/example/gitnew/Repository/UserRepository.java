package com.example.gitnew.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.gitnew.Database.UserDatabase;
import com.example.gitnew.dao.UserDao;
import com.example.gitnew.model.Data;
import com.example.gitnew.model.User;

import java.util.List;

public class UserRepository {

    private UserDatabase database;
    private LiveData<List<Data>> getAllUsers;

    public UserRepository(Application application)
    {
        database= UserDatabase.getInstance(application);
        getAllUsers=database.userDao().getAllUsers();
    }

    public void insert(List<Data> userList){
        new InsertAsynTask(database).execute(userList);
    }

    public void update(Data userList){
        new UpdateAsynTask(database).execute(userList);
    }
    public void updates(String comment,int id){
        new UpdatesAsynTask(database,comment,id).execute(comment);
    }
    public void getUserData(int id){
        new GetUserAsynTask(database,id).execute(id);
    }

    public LiveData<List<Data>> getAllUsers()
    {
        return getAllUsers;
    }

    static class InsertAsynTask extends AsyncTask<List<Data>,Void,Void> {
        private UserDao userDao;
        InsertAsynTask(UserDatabase userDatabase)
        {
            userDao=userDatabase.userDao();
        }
        @Override
        protected Void doInBackground(List<Data>... lists) {
            userDao.insert(lists[0]);
            return null;
        }
    }
    static class UpdateAsynTask extends AsyncTask<Data,Void,Void> {
        private UserDao userDao;
        UpdateAsynTask(UserDatabase userDatabase)
        {
            userDao=userDatabase.userDao();
        }
        @Override
        protected Void doInBackground(Data... lists) {
            userDao.update(lists[0]);
            return null;
        }
    }
    static class UpdatesAsynTask extends AsyncTask<String,Void,Void> {
        private UserDao userDao;
        private String comment;
        private int id;
        UpdatesAsynTask(UserDatabase userDatabase,String comment,int id)
        {
            userDao=userDatabase.userDao();
            this.comment = comment;
            this.id = id;
        }
        @Override
        protected Void doInBackground(String... lists) {
            userDao.updates(comment,id);
            return null;
        }
    }
    static class GetUserAsynTask extends AsyncTask<Integer,Void,Void> {
        private UserDao userDao;
        private String comment;
        private int id;
        GetUserAsynTask(UserDatabase userDatabase,int id)
        {
            userDao=userDatabase.userDao();
            this.id = id;
        }
        @Override
        protected Void doInBackground(Integer... lists) {
            userDao.getUserData(id);
            return null;
        }
    }


}
