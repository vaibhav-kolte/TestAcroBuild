package com.vaibhav_kolte.vaibhavacrobuild;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.vaibhav_kolte.vaibhavacrobuild.databinding.ActivityCrudOperationBinding;

import java.io.Serializable;

public class CrudOperationActivity extends AppCompatActivity {

    private static final String TAG = "CrudOperationActivity";
    private ActivityCrudOperationBinding binding;
    private Context context;
    private MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCrudOperationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        try{
            context = CrudOperationActivity.this;
            database = new MyDatabase(context);
            binding.btnLogin.setOnClickListener( v -> {
                verifyUser();
            });

            binding.tvRegisterUser.setOnClickListener(v -> {
                Intent intent = new Intent(context,RegisterActivity.class);
                startActivity(intent);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void verifyUser() {
        try{
            if(TextUtils.isEmpty(binding.etUserName.getText().toString())){
                binding.etUserName.setError("Enter UserName");
                return;
            }
            if(TextUtils.isEmpty(binding.etPassword.getText().toString())){
                binding.etPassword.setError("Enter Password");
                return;
            }

            User user = database.verifyUser(binding.etUserName.getText().toString(),
                    binding.etPassword.getText().toString());

            if(user == null){
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Alert")
                        .setMessage("User does not exits.\nDo you want to register new user?")
                        .setPositiveButton("YES", (dialogInterface, i) -> startActivity(new Intent(context,RegisterActivity.class)))
                        .setNegativeButton("No", (dialogInterface, i) -> {

                        })
                        .show();
            }else{
                SharePref.storeSharedPref(context,SharePref.userId,user.userName);
                SharePref.storeSharedPref(context,SharePref.name,user.name);
                SharePref.storeSharedPref(context,SharePref.password,user.password);
                Intent intent = new Intent(context,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}