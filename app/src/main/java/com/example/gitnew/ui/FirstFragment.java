package com.example.gitnew.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitnew.Adapter.UserAdapter;
import com.example.gitnew.Network.Retrofit;
import com.example.gitnew.Repository.UserRepository;
import com.example.gitnew.ViewModal.UserViewModel;
import com.example.gitnew.databinding.FirstFragmentBinding;
import com.example.gitnew.model.Data;
import com.example.gitnew.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragment extends Fragment {

    private static String TAG = FirstFragment.class.getSimpleName();

    private FirstFragmentBinding firstFragmentBinding;
    private UserViewModel userViewModel;
    private List<Data> userList;
    private UserRepository userRepository;
    private UserAdapter userAdapter;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        firstFragmentBinding = FirstFragmentBinding.inflate(inflater,container,false);

        initView();
        return firstFragmentBinding.getRoot();
    }

    private void initView(){

        Log.i(TAG, "initViewinitView: "+"");
        firstFragmentBinding.recyclerview.setHasFixedSize(true);
        firstFragmentBinding.recyclerview.setLayoutManager(new LinearLayoutManager(context));
        firstFragmentBinding.recyclerview.setItemAnimator(new DefaultItemAnimator());
        userRepository=new UserRepository(getActivity().getApplication());
        userList=new ArrayList<>();
        userAdapter=new UserAdapter(context,userList);

        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        networkRequest();
        userViewModel.getAllUser().observe(getActivity(), new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> userList) {
                firstFragmentBinding.recyclerview.setAdapter(userAdapter);
                userAdapter.getAllUsers(userList);

                Log.d("main", "onChanged: "+userList);
            }
        });

    }

    private void networkRequest() {

        Retrofit retrofit=new Retrofit();
        Call<User> call = retrofit.api.getAllUsers();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("TAG", "networkRequestonResponse: "+response.body());
                if(response.isSuccessful())
                {
                    User map = response.body();
                    Log.i("TAG", "networkRequestonResponse: "+response);
                    List<Data> newList = new ArrayList<>();
                    for (int i=0;i<response.body().getData().size();i++){
                        newList.add(response.body().getData().get(i));
                    }

                    userRepository.insert(newList);
                    Log.d("main", "onResponse: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("TAG", "networkRequestonFailure: "+t.getMessage());
            }
        });

    }

}
