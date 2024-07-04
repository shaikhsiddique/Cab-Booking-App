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
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class CustomerSignup extends AppCompatActivity {
    EditText Username,address,mobileno,password,cpassword;
    Button signupbtn,signinbtn;
   public DrawerLayout drawerLayout;
   Validation validation;
   public ActionBarDrawerToggle actionBarDrawerToggle;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_signup);
        validation=new Validation();
        Username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        cpassword=(EditText)findViewById(R.id.cpassword);
        signupbtn=(Button)findViewById(R.id.signupbtn);
        signinbtn=(Button)findViewById(R.id.signinbtn);
        DB=new DBHelper(this);
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


        signupbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String user=Username.getText().toString();
                String pass=password.getText().toString();
                String cpass= cpassword.getText().toString();

                if(user.equals("")||pass.equals("")||cpass.equals("")) {
                    Toast.makeText(CustomerSignup.this, "pease enter all the fields", Toast.LENGTH_SHORT).show();
                }
              /*
                }
*/
                else {

int x=0;
                        if (!validation.password(pass)) {
                            System.out.println("Hi");
                            x=1;
                            Toast.makeText(CustomerSignup.this, "Enter 8 Digit with upper & lower case letter and special charaters Password", Toast.LENGTH_SHORT).show();
                        }

                        if (user.length()<10) {
                            x=1;
                            System.out.println("Byee");
                            Toast.makeText(CustomerSignup.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                        }
                        if (!validation.mobile(user)){
                            x=1;
                            System.out.println("cya");
                            Toast.makeText(CustomerSignup.this,"Enter Correct Mobile no Address",Toast.LENGTH_SHORT).show();
                        }
                    if (pass.equals(cpass) && x==0) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(CustomerSignup.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), CustomerLogin.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(CustomerSignup.this, "Restration end ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(CustomerSignup.this, "User Already Exist,please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CustomerSignup.this, "Passwords Not match", Toast.LENGTH_SHORT).show();
                    }
                }}
        });
        signinbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),CustomerLogin.class);
                startActivity(intent);
            }
        });


    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}