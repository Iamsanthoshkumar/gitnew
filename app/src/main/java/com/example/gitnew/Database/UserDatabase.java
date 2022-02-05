package com.example.gitnew.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.gitnew.dao.UserDao;
import com.example.gitnew.model.Data;
import com.example.gitnew.model.User;


@Database(entities = {Data.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DATABASE_NAME="UserDatabase";

    public abstract UserDao userDao();

    private static volatile UserDatabase INSTANCE;

    public static UserDatabase getInstance(Context context){
        if(INSTANCE == null)
        {
            synchronized (UserDatabase.class){
                if(INSTANCE == null)
                {
                    INSTANCE= Room.databaseBuilder(context,UserDatabase.class,
                            DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback=new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };
    static class PopulateAsynTask extends AsyncTask<Void,Void,Void>
    {
        private UserDao userDao;
        PopulateAsynTask(UserDatabase userDatabase)
        {
            userDao=userDatabase.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAll();
            return null;
        }
    }
}
