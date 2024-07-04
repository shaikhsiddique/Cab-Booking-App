package com.example.bookingcab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;

public class OwnerLogin extends AppCompatActivity {
EditText username,password;
Button loginbtn;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginbtn = (Button) findViewById(R.id.loginbtn);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final NavigationView nav_view=(NavigationView)findViewById(R.id.nav_bar);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.Customer_login)
                {
                    Intent intent = new Intent(getApplicationContext(), CustomerLogin.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), OwnerLogin.class);
                    startActivity(intent);
                }
                return true;
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginfun();
            }
        });
    }

    public void loginfun() {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        System.out.println("Admin=="+user);
        System.out.println("pass=="+pass);
        if(user.equals("Admin")&& pass.equals("Admin@123"))
        {
            System.out.println("hello");
            Intent intent = new Intent(this, CheckRequest.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
