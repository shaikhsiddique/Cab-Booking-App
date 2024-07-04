package com.example.bookingcab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class CustomerLogin extends AppCompatActivity {
    Button signupbtn, loginbtn1,OwnerLogin,ApprovBtn;
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing email.
    public static final String EMAIL_KEY = "email_key";

    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";

    // variable for shared preferences.
    SharedPreferences sharedpreferences;
    EditText username1, password1;
    public static final String MyPREFERENCES = "MyPrefs" ;

    String email, password;
    DBHelper DB;
    Validation validation;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        validation=new Validation();
        username1 = (EditText) findViewById(R.id.username1);
        password1 = (EditText) findViewById(R.id.password1);
        loginbtn1 = (Button) findViewById(R.id.loginbtn1);
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

        DB = new DBHelper(this);

        signupbtn = (Button) findViewById(R.id.signupbtn);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // in shared prefs inside het string method
        // we are passing key value as EMAIL_KEY and
        // default value is
        // set to null if not present.
        email = sharedpreferences.getString(EMAIL_KEY, null);
        password = sharedpreferences.getString(PASSWORD_KEY, null);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signupfun();
            }
        });

        loginbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username1.getText().toString();
                String pass = password1.getText().toString();

System.out.println("User"+user);

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(CustomerLogin.this, "please enter all the fields", Toast.LENGTH_SHORT).show();
                }
             else {
                    int x=0;
                    if (user.isEmpty()) {
                        x=1;
                        Toast.makeText(CustomerLogin.this, "Enter Your Mobile Number", Toast.LENGTH_SHORT).show();
                    }
                    if (user.length()<10) {
                        x=1;
                        Toast.makeText(CustomerLogin.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                    }
                    if (!validation.mobile(user)){
                        x=1;
                        Toast.makeText(CustomerLogin.this,"Enter Correct Mobile no Address",Toast.LENGTH_SHORT).show();
                    }

                    if (!validation.password(pass)) {
                        x=1;
                        Toast.makeText(CustomerLogin.this, "Enter 8 Digit with upper & lower case letter and special charaters Password", Toast.LENGTH_SHORT).show();
                    }
                    if(x==0)
                    {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass == true) {
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        // below two lines will put values for
                        // email and password in shared preferences.
                        editor.putString(EMAIL_KEY, user);
                        editor.putString(PASSWORD_KEY, pass);

                        // to save our data with key and value.
                        editor.apply();
                        System.out.println("Email"+EMAIL_KEY);
                        Toast.makeText(CustomerLogin.this, "Signin Successfully", Toast.LENGTH_SHORT).show();

                        // starting new activity.
                        Intent i = new Intent(CustomerLogin.this, BookCab.class);
                        startActivity(i);
                        finish();


                       // Intent intent = new Intent(CustomerLogin.this, BookAmbulance.class);
                      //  startActivity(intent);

                    } else {
                        Toast.makeText(CustomerLogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }}
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Signupfun()
    {
        Intent intent=new Intent(this,CustomerSignup.class);
        startActivity(intent);
    }
    public void ApprovBtnFun()
    {
        Intent intent=new Intent(this,ApprovCustomerRequest.class);
        startActivity(intent);
    }
    public void OwnerLoginFun()
    {
        Intent intent=new Intent(this,OwnerLogin.class);
        startActivity(intent);
    }
}
