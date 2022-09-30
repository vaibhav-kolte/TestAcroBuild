package com.vaibhav_kolte.vaibhavacrobuild;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.vaibhav_kolte.vaibhavacrobuild.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private ActivityRegisterBinding binding;
    private Context context;
    private MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try {
            context = RegisterActivity.this;

            database = new MyDatabase(context);
            binding.btnLogin.setOnClickListener(v -> registerUser());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void registerUser() {
        try {
            if (TextUtils.isEmpty(binding.etUserName.getText().toString())) {
                binding.etUserName.setError("Enter user name");
                return;
            }
            if (TextUtils.isEmpty(binding.etName.getText().toString())) {
                binding.etName.setError("Enter name");
                return;
            }
            if (TextUtils.isEmpty(binding.etPassword.getText().toString())) {
                binding.etPassword.setError("Enter password");
                return;
            }
            if (TextUtils.isEmpty(binding.etConfirmPassword.getText().toString())) {
                binding.etConfirmPassword.setError("Enter confirm password");
                return;
            }
            if (!binding.etPassword.getText().toString().equals(binding.etConfirmPassword.getText().toString())) {
                binding.etConfirmPassword.setError("Password and confirm password should be same");
                return;
            }

            if(database.isUserExits(binding.etUserName.getText().toString())){
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Alert")
                        .setMessage("User is exits exits.\nChange user name.")
                        .setPositiveButton("OK", (dialogInterface, i) -> {

                        })
                        .show();
                return;
            }
            database.registerUser(binding.etUserName.getText().toString(),
                    binding.etName.getText().toString(),
                    binding.etPassword.getText().toString());

            finish();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}