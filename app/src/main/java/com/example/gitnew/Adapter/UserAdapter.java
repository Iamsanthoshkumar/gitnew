package com.example.gitnew.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gitnew.R;
import com.example.gitnew.model.Data;
import com.example.gitnew.model.User;
import com.example.gitnew.ui.DetailFragment;
import com.example.gitnew.ui.FirstFragment;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<Data> userList;
    private DetailFragment detailFragment = new DetailFragment();

    public UserAdapter(Context context, List<Data> userList) {
        this.context =  context;
        this.userList = userList;
    }
    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        Data user=userList.get(position);
//        holder.id.setText("Id "+user.getData().get(position).getId());
        Log.i("TAG", "networkRequestonBindViewHolder: "+user);
        holder.name.setText("Name "+user.getName());
        holder.mail.setText("Mail "+user.getMail());
        holder.itemLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id",userList.get(position).getId());
                bundle.putString("name",userList.get(position).getName());
                bundle.putString("mail",userList.get(position).getMail());
                bundle.putString("gender",userList.get(position).getGender());
                bundle.putString("status",userList.get(position).getStatus());
                bundle.putString("comments",userList.get(position).getComment());
                detailFragment.setArguments(bundle);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment).addToBackStack(null).commit();
            }
        });
    }
    public void getAllUsers(List<Data> userList)
    {
        this.userList=userList;
    }
    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder{
        public TextView id,name,mail;
        public ImageView image;
        CardView itemLay;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
//            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            mail=itemView.findViewById(R.id.mail);
            itemLay=itemView.findViewById(R.id.itemLay);
//            image=itemView.findViewById(R.id.image);
        }
    }
}
