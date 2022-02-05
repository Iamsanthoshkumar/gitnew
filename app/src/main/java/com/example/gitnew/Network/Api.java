package com.example.gitnew.Network;


import com.example.gitnew.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("users")
    Call<User> getAllUsers();
}
