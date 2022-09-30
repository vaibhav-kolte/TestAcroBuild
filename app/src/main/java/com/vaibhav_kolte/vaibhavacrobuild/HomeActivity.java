package com.vaibhav_kolte.vaibhavacrobuild;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.vaibhav_kolte.vaibhavacrobuild.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private ActivityHomeBinding binding;
    private Context context;
    private MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        try {
            context = HomeActivity.this;
            database = new MyDatabase(context);
            setData();
            binding.btnLogout.setOnClickListener(v -> {
                SharePref.clearSharedPref(context);
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Alert")
                        .setMessage("Your logout successfully.")
                        .setPositiveButton("OK", (dialogInterface, i) -> {
                            finish();
                        })
                        .show();

            });

            binding.btnDelete.setOnClickListener(v -> {
                deleteUser();
            });

            binding.btnUpdate.setOnClickListener(v -> {
                updateUser();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUser() {
        try {
            binding.updateLayout.setVisibility(View.VISIBLE);
            binding.etName.setText(SharePref.readSharedPref(context, SharePref.name));

            binding.btnConfirmUpdate.setOnClickListener(v -> {
                confirmUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void confirmUpdate() {
        try {
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

            database.updateUser(SharePref.readSharedPref(context, SharePref.userId),
                    binding.etName.getText().toString(),
                    binding.etPassword.getText().toString());

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Alert")
                    .setMessage("User updated successfully.")
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        binding.updateLayout.setVisibility(View.GONE);
                    })
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteUser() {
        try {
            database.deleteUser(SharePref.readSharedPref(context, SharePref.userId));
            SharePref.clearSharedPref(context);
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Alert")
                    .setMessage("User deleted successfully.")
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        startActivity(new Intent(context, CrudOperationActivity.class));
                        finish();
                    })
                    .show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setData() {
        try {
            binding.tvName.setText("Name : " + SharePref.readSharedPref(context, SharePref.name));
            binding.tvUserId.setText("User Id : " + SharePref.readSharedPref(context, SharePref.userId));
            binding.tvPassword.setText("Password : " + SharePref.readSharedPref(context, SharePref.password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}