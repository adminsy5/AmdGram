package com.mpiyush3510.amdgram.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.mpiyush3510.amdgram.Adapters.UsersAdapter;
import com.mpiyush3510.amdgram.Models.User;
import com.mpiyush3510.amdgram.R;
import com.mpiyush3510.amdgram.Utilities.Constants;
import com.mpiyush3510.amdgram.Utilities.PreferenceManager;
import com.mpiyush3510.amdgram.databinding.ActivityMainBinding;
import com.mpiyush3510.amdgram.databinding.ActivityUsersBinding;

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    private ActivityUsersBinding binding;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUsersBinding.inflate(getLayoutInflater());
        preferenceManager= new PreferenceManager(getApplicationContext());
        setContentView(binding.getRoot());
        setListeners();
        getUsers();
    }

    private void setListeners(){
        binding.imageBack.setOnClickListener(view -> onBackPressed());
    }
    private void getUsers(){
        isLoading(true);
        FirebaseFirestore database=FirebaseFirestore.getInstance();
        database.collection(Constants.KET_COLLECTIONS_USERS)
                .get()
                .addOnCompleteListener(task -> {
                    isLoading(false);
                    String currentUserId   = preferenceManager.getString(Constants.KEY_USER_ID);
                    if(task.isSuccessful() && task.getResult()!=null){
                        List<User> users=new ArrayList<>();
                        for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                            if(currentUserId.equals(queryDocumentSnapshot.getId())){
                                continue;
                            }
                            User user=new User();
                            user.name=queryDocumentSnapshot.getString(Constants.KEY_NAME);
                            user.email=queryDocumentSnapshot.getString(Constants.KEY_EMAIL);
                            user.image=queryDocumentSnapshot.getString(Constants.KEY_IMAGE);
                            user.token=queryDocumentSnapshot.getString(Constants.KEY_FCM_TOKEN);
                            users.add(user);
                        }
                        if (users.size()>0){
                            UsersAdapter usersAdapter=new UsersAdapter(users);
                            binding.UserRecyclerView.setAdapter(usersAdapter);
                            binding.UserRecyclerView.setVisibility(View.VISIBLE);
                        }else {
                            showErrorMessage();
                        }
                    }
                    else
                    {
                        showErrorMessage();
                    }
                });
    }

    private void showErrorMessage(){
        binding.textErrorMessage.setText(String.format("%s","No User Available"));
        binding.textErrorMessage.setVisibility(View.VISIBLE);
    }

    private void isLoading(Boolean isLoading){
        if(isLoading)
            binding.progressBar.setVisibility(View.INVISIBLE);
        else
            binding.progressBar.setVisibility(View.VISIBLE);
    }
}