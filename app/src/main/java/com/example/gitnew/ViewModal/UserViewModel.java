package com.example.gitnew.ViewModal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.gitnew.Repository.UserRepository;
import com.example.gitnew.model.Data;
import com.example.gitnew.model.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<Data>> getAllUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository=new UserRepository(application);
        getAllUsers=userRepository.getAllUsers();
    }

    public void insert(List<Data> list)
    {
        userRepository.insert(list);
    }

    public LiveData<List<Data>> getAllUser()
    {
        return getAllUsers ;
    }

}
