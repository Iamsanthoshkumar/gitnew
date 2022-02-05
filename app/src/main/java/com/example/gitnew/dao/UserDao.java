package com.example.gitnew.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gitnew.model.Data;
import com.example.gitnew.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Data> userList);

    @Query("SELECT * FROM users")
    LiveData<List<Data>> getAllUsers();

    @Query("SELECT * FROM users WHERE id =:id")
    LiveData<List<Data>> getOneUser(int id);

    /*@Query("SELECT * FROM users WHERE id =:id")
    LiveData<List<Data>> getUserData(int id);*/

    @Query("SELECT * FROM users WHERE id =:id")
    LiveData<List<Data>> getUserData(int id);

    @Query("UPDATE users SET comment=:comment WHERE id = :id")
    void updates(String comment, int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void update(Data data);

    @Query("DELETE FROM users")
    void deleteAll();

}
