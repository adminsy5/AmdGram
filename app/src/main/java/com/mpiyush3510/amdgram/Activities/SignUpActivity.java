package com.mpiyush3510.amdgram.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import com.mpiyush3510.amdgram.databinding.ActivitySignUpBinding;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private String encodedImage;

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
        binding.btnSignUp.setOnClickListener(view -> {
            if(isValidSignUpDetail())
            {
                signUp();
            }
        });
    }

    private void showToasts(String Message)
    {
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
    }

    private void signUp()
    {

    }

    private Boolean isValidSignUpDetail()
    {
        if(encodedImage==null)
        {
            showToasts("Select Profile Picture ");
            return false;
        }else if(binding.inputName.getText().toString().isEmpty()) {
            showToasts("Please Enter Name ");
            return false;
        } else if(binding.inputEmail.getText().toString().isEmpty()) {
            showToasts("Please Enter Email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches()) {
            showToasts("Enter Valid Email");
            return false;
        }else if (binding.inputPassword.getText().toString().isEmpty()){
            showToasts("Please Enter Password");
            return false;
        } else if (binding.inputConfirmPassword.getText().toString().isEmpty()) {
            showToasts("Please Enter Same Password ");
            return false;
        }
        else
        {
            return true;
        }
    }
}