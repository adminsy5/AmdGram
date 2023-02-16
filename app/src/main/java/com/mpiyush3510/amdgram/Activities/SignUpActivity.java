package com.mpiyush3510.amdgram.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.mpiyush3510.amdgram.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }
    private void setListeners()
    {
        binding.textSignIn.setOnClickListener(view -> onBackPressed());
    }
}