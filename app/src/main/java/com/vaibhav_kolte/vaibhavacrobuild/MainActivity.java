package com.vaibhav_kolte.vaibhavacrobuild;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;



public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    PieChart pieChartActiveUser, piechartProjectWiseUser;
    private TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tvResponse);
        pieChartActiveUser = findViewById(R.id.piechartActiveUser);
        piechartProjectWiseUser = findViewById(R.id.piechartProjectWiseUser);
        navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);


        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.string.nav_open,
                R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        showActiveUserGraph();
        showProjectWiseUser();


        callLogin_LeaderBoardAPI();

    }

    private void callLogin_LeaderBoardAPI() {
        try{
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://ezzixbuildwebapi.azurewebsites.net/webapi/api/admindashboard/login-leaderboard?projectId=all&startDate=2022-08-01&endDate=2022-08-29";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        // Display the first 500 characters of the response string.
                        textView.setText("login-leaderboard Response is: " + response.toString());
                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("That didn't work!");
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProjectWiseUser() {
        piechartProjectWiseUser.addPieSlice(
                new PieModel(
                        "R",
                        Integer.parseInt("40"),
                        Color.parseColor("#FFA726")));
        piechartProjectWiseUser.addPieSlice(
                new PieModel(
                        "Python",
                        Integer.parseInt("20"),
                        Color.parseColor("#66BB6A")));
        piechartProjectWiseUser.addPieSlice(
                new PieModel(
                        "C++",
                        Integer.parseInt("20"),
                        Color.parseColor("#EF5350")));
        piechartProjectWiseUser.addPieSlice(
                new PieModel(
                        "Java",
                        Integer.parseInt("20"),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        piechartProjectWiseUser.startAnimation();
    }

    private void showActiveUserGraph() {
        pieChartActiveUser.addPieSlice(
                new PieModel(
                        "R",
                        Integer.parseInt("40"),
                        Color.parseColor("#FFA726")));
        pieChartActiveUser.addPieSlice(
                new PieModel(
                        "Python",
                        Integer.parseInt("60"),
                        Color.parseColor("#66BB6A")));

        pieChartActiveUser.startAnimation();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile:
                Toast.makeText(this, "Click profile", Toast.LENGTH_LONG).show();
                break;
            case R.id.notification:
                Toast.makeText(this, "Click notification", Toast.LENGTH_LONG).show();
                break;
            case R.id.search:
                Toast.makeText(this, "Click search", Toast.LENGTH_LONG).show();
                break;
//            default:
//                Toast.makeText(this, "Click nothing", Toast.LENGTH_LONG).show();
//                break;
        }
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        try {
            int id = item.getItemId();
            if (id == R.id.nav_account) {
                Toast.makeText(this, "Click account", Toast.LENGTH_LONG).show();
            } else if (id == R.id.nav_logout) {
                Toast.makeText(this, "Click logout", Toast.LENGTH_LONG).show();
            } else if (id == R.id.nav_settings) {
                Toast.makeText(this, "Click setting", Toast.LENGTH_LONG).show();
            }

            DrawerLayout drawer = findViewById(R.id.my_drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.right_corner_menu, menu);
        return true;
    }
}