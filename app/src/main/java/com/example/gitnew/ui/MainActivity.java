package com.example.gitnew.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.gitnew.Adapter.UserAdapter;
import com.example.gitnew.Network.Retrofit;
import com.example.gitnew.R;
import com.example.gitnew.Repository.UserRepository;
import com.example.gitnew.ViewModal.UserViewModel;
import com.example.gitnew.databinding.ActivityMainBinding;
import com.example.gitnew.model.Data;
import com.example.gitnew.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{

    private static String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding activityMainBinding;

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        initView();
    }


    private void initView(){
        Log.i(TAG, "initView: "+"");
        changeFragment(firstFragment,"First");
        activityMainBinding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.first:
//                        changeFragment(firstFragment,"First");
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, firstFragment).addToBackStack(null).commit();
                        break;
                    case R.id.second:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, secondFragment).addToBackStack(null).commit();
                        break;
                    case R.id.third:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, thirdFragment).addToBackStack(null).commit();
                        break;
                }
                return false;
            }
        });
    }
    public void changeFragment(Fragment fragment, String tagFragmentName) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment currentFragment = mFragmentManager.getPrimaryNavigationFragment();
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }

        Fragment fragmentTemp = mFragmentManager.findFragmentByTag(tagFragmentName);
        if (fragmentTemp == null) {
            fragmentTemp = fragment;
            fragmentTransaction.add(R.id.container, fragmentTemp, tagFragmentName);
        } else {
            fragmentTransaction.show(fragmentTemp);
        }

        fragmentTransaction.setPrimaryNavigationFragment(fragmentTemp);
        fragmentTransaction.setReorderingAllowed(true);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if(manager.getBackStackEntryCount() > 0 ) {
            manager.popBackStack();//Pops one of the added fragments
        }else {
            Log.i(TAG, "onBackPressed: "+manager.getBackStackEntryCount());
            super.onBackPressed();
        }
//                getSupportFragmentManager().beginTransaction()
//                .add(R.id.bottom_navigation,new FirstFragment()).addToBackStack(null).commit();

        /*if (fragmentManager.getBackStackEntryCount() == 1) {
      AlertExit();
} else {
      int backStackId = fragmentManager.getBackStackEntryAt(i).getId();
      fragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);*/
    }
}