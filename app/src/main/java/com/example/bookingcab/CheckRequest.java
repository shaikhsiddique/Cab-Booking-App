package com.example.bookingcab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
public class CheckRequest extends AppCompatActivity {
RecyclerView recyclerView;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    private ArrayList<model> dataholder= new ArrayList<model>();
//ArrayList<model> dataholder;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_request);
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
                if(id==R.id.Check_Request)
                {
                    Intent intent = new Intent(getApplicationContext(), CheckRequest.class);
                    startActivity(intent);
                }
                else  if(id==R.id.Approv_request)
                {
                    Intent intent = new Intent(getApplicationContext(), ApprovCustomerRequest.class);
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

        recyclerView=(RecyclerView)findViewById(R.id.review);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
     /*   drawerLayout1.addDrawerListener(actionBarDrawerToggle1);
        actionBarDrawerToggle1.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final NavigationView nav_view=(NavigationView)findViewById(R.id.nav_bar);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.Check_Request)
                {
                    Intent intent = new Intent(getApplicationContext(), CheckRequest.class);
                    startActivity(intent);
                }
                else if(id==R.id.Approv_request)
                {
                    Intent intent = new Intent(getApplicationContext(), ApprovCustomerRequest.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(), CommonFrontPage.class);
                    startActivity(intent);
                }
                return true;
            }
        });  */
        Cursor cursor=new DBHelper(this).retrieveDataFromBookingAmbulance();
        while(cursor.moveToNext())
        {
            model obj=new model(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
        dataholder.add(obj);
        }
        System.out.println("Hello");
        myadapter adapter =new myadapter(dataholder);
        recyclerView.setAdapter(adapter);
        System.out.println("Bye");
    }
  @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}