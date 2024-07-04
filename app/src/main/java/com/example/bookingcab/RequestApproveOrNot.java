package com.example.bookingcab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class RequestApproveOrNot extends AppCompatActivity {
    public DrawerLayout drawerLayout1;
    public ActionBarDrawerToggle actionBarDrawerToggle1;
    DBHelper db;
    String id,mobile1,steta,dname,dno;
    TextView RequestId,Status,DriverContactNo,DriverName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request_approve_or_not);
        Intent i=getIntent();
        id = i.getStringExtra("Mobile");
        mobile1=id;
        db=new DBHelper(this);
        RequestId=(TextView)findViewById(R.id.RequestId);
        Status=(TextView)findViewById(R.id.Status);
        DriverContactNo=(TextView)findViewById(R.id.DriverContactNo);
        DriverName=(TextView)findViewById(R.id.DriverName);
     System.out.println("Mobile no"+mobile1);
        Cursor cursor=db.getApproveAmbulanceDetails(mobile1);

        while(cursor.moveToNext())
        {
            System.out.println("hi");
            id=cursor.getString(0);
            steta=cursor.getString(9);
            dname=cursor.getString(10);
            dno=cursor.getString(11);

        }
        System.out.println(""+id);
        System.out.println(""+steta);
        RequestId.setText(""+id);
        Status.setText(""+steta);
        DriverContactNo.setText(dno);
        DriverName.setText(dname);

        drawerLayout1 = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle1 = new ActionBarDrawerToggle(this, drawerLayout1, R.string.nav_open, R.string.nav_close);
db=new DBHelper(this);
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout1.addDrawerListener(actionBarDrawerToggle1);
        actionBarDrawerToggle1.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final NavigationView nav_view=(NavigationView)findViewById(R.id.nav_bar);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.Book_ambulance)
                {
                    Intent intent = new Intent(getApplicationContext(), BookCab.class);
                    startActivity(intent);
                }
                else if(id==R.id.Check_Request_Status)
                {
                    Intent intent = new Intent(getApplicationContext(), RequestApproveOrNot.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), CommonFrontPage.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle1.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}