package com.example.gitnew.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.gitnew.Database.UserDatabase;
import com.example.gitnew.Repository.UserRepository;
import com.example.gitnew.ViewModal.UserViewModel;
import com.example.gitnew.databinding.DetailFragmentBinding;
import com.example.gitnew.model.Data;

import java.util.List;

public class DetailFragment extends Fragment {

    private static String TAG = DetailFragment.class.getSimpleName();
//    List<Data> data;
    private DetailFragmentBinding detailFragmentBinding;
    private int id;
    private String name,mail,gender,status,comments;
    private UserDatabase userDatabase;
    private UserRepository userRepository;
    private UserViewModel userViewModel;
    private Data data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailFragmentBinding = DetailFragmentBinding.inflate(inflater,container,false);

        id = getArguments().getInt("id");
        name = getArguments().getString("name");
        mail = getArguments().getString("mail");
        gender = getArguments().getString("gender");
        status = getArguments().getString("status");
        comments = getArguments().getString("comments");
        initView();

        data = new Data();
//        data.setName(name);

        userRepository=new UserRepository(getActivity().getApplication());

        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUser().observe(getActivity(), new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> userList) {
                Log.d("initViewmain", "onChanged: "+userList);
/*
                for (int i =0;i<userList.size();i++){
                    String name = userList.get(i).getName();
                    if (userList.get(i).getName().equals(name)){
//                        Log.i(TAG, "initViewmainonChanged: "+userList.get(i).getName());
                    }
                }
*/
            }
        });

        return detailFragmentBinding.getRoot();
    }
    private void initView(){

        Log.i(TAG, "initViewdetail: "+id+name);

//        userRepository.getUserData(id);
//        userDatabase.userDao().getOneUser(id);
//        Log.i(TAG, "initViewdetailinitView: "+userDatabase.userDao().getOneUser(id));
        detailFragmentBinding.nameTxt.setText(name);
        detailFragmentBinding.mailTxt.setText(mail);
        detailFragmentBinding.genderTxt.setText(gender);
        detailFragmentBinding.statusTxt.setText(status);
        detailFragmentBinding.commentTxt.setText(comments);
        detailFragmentBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = detailFragmentBinding.commentTxt.getText().toString().trim();
                Log.i(TAG, "initViewdetailonClick: "+comment+id);
//                data.setComment(comment);
                userRepository.updates(comment,id);
            }
        });

/*        if (userDatabase.userDao().getAllUsers()!=null)
        Log.i(TAG, "initViews: "+userDatabase.userDao().getAllUsers());*/

//        detailFragmentBinding.nameTxt.setText("");
    }

/*
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if( keyCode == KeyEvent.KEYCODE_BACK ){
            // back to previous fragment by tag
            myfragmentclass fragment = (myfragmentclass) getActivity().getSupportFragmentManager().findFragmentByTag(TAG);
            if(fragment != null){
                (getActivity().getSupportFragmentManager().beginTransaction()).replace(R.id.cf_g1_mainframe_fm, fragment).commit();
            }
            return true;
        }
        return false;
    }
*/

}
