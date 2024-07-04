package com.example.bookingcab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.*;
import com.example.bookingcab.*;
import com.google.android.material.navigation.NavigationView;

public class BookCab extends AppCompatActivity {
String user;
EditText AmbulanceType;
    public DrawerLayout drawerLayout1;
    public ActionBarDrawerToggle actionBarDrawerToggle1;
    Validation validation;
    public static final String SHARED_PREFS = "shared_prefs";
    String[] AmbulanceT = {"Collective Ambulance","Individual Ambulance","Mobile ICU Ambulance","Basic Life Support Ambulance",
            "Medical and Nursing Care Vehicle","Hospital Tent","MVA Logistics Unit","Neonatal Incubator"};
    // key for storing email.
    public static final String EMAIL_KEY = "email_key";
String reg_no,reg_number;
    // key for storing password.
    public static final String PASSWORD_KEY = "password_key";
    SharedPreferences sharedpreferences;
    TextView idTVWelcome;
    EditText patientName,MobileNo,patientadddress,pincode;
    TimePicker timePicker1;
    DatePicker datePicker;
    Button bookbtn;
    DBHelper DB;
    String Selecteddate,SelectTime;
    TextView Status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cab);
        validation=new Validation();
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        // getting data from shared prefs and
        // storing it in our string variable.
        reg_no = sharedpreferences.getString(EMAIL_KEY, null);
        // initializing our textview and button.
        TextView welcomeTV = findViewById(R.id.idTVWelcome);
        welcomeTV.setText(reg_no);
        patientName=(EditText) findViewById(R.id.patientName);
        MobileNo=(EditText) findViewById(R.id.MobileNo);
        patientadddress=(EditText) findViewById(R.id.patientadddress);
        pincode=(EditText) findViewById(R.id.pincode);
        AmbulanceType=(EditText)findViewById(R.id.AmbulanceType);
        timePicker1 = (TimePicker)findViewById(R.id.timePicker1);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        idTVWelcome=(TextView)findViewById((R.id.idTVWelcome));
        bookbtn=(Button) findViewById(R.id.bookbtn);
        Status=(TextView)findViewById(R.id.Status);
        drawerLayout1 = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle1 = new ActionBarDrawerToggle(this, drawerLayout1, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout1.addDrawerListener(actionBarDrawerToggle1);
        actionBarDrawerToggle1.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final NavigationView nav_view=(NavigationView)findViewById(R.id.nav_bar1);
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
                    intent.putExtra("Mobile",reg_no);
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

        DB=new DBHelper(this);
        bookbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String pname=patientName.getText().toString();
                String mobile=MobileNo.getText().toString();
                String padd= patientadddress.getText().toString();
                String pcode=pincode.getText().toString();
                String AmbType=AmbulanceType.getText().toString();
                String status=Status.getText().toString();
                reg_number=idTVWelcome.getText().toString();
                timePicker1=(TimePicker)findViewById(R.id.timePicker1);
                timePicker1.setIs24HourView(true);
                SelectTime=timePicker1.getCurrentHour()+":"+timePicker1.getCurrentMinute();
               // datePicker=(datePicker)findViewById(R.id.datePicker);
                Selecteddate=datePicker.getDayOfMonth()+"/"+datePicker.getMonth()+"/"+datePicker.getYear();


                System.out.println("Time="+SelectTime);
                System.out.println("Date="+Selecteddate);

                if(pname.equals("")||mobile.equals("")||padd.equals("")||pcode.equals("")||AmbType.equals("")||((timePicker1.isSelected()))||((datePicker.isSelected()))||(status.equals(""))) {
                    System.out.println("reg_no"+reg_number);
                    System.out.println("pname"+pname);
                    System.out.println("mobile"+mobile);
                    System.out.println("padd"+padd);
                    System.out.println("pcode"+pcode);
                    System.out.println("time"+SelectTime);
                    System.out.println("date"+Selecteddate);
                    System.out.println("Selected Item"+AmbType);
                    System.out.println("Status="+status);
                    System.out.println("Data empty");
                    Toast.makeText(BookCab.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    int x=0;
                    if (pname.isEmpty()){
                        x=1;
                    Toast.makeText(BookCab.this,"Enter patient name",Toast.LENGTH_SHORT).show();

                    }
                    if (!validation.fullname(pname)){
                        x=1;
                        Toast.makeText(BookCab.this,"Only Small/Upper case Letters are allowed",Toast.LENGTH_SHORT).show();
                    }
                    if (mobile.isEmpty()) {
                        x=1;
                        Toast.makeText(BookCab.this, "Enter Your Mobile Number", Toast.LENGTH_SHORT).show();
                    }
                    if (mobile.length()<10) {
                        x=1;
                        Toast.makeText(BookCab.this, "Enter Valid Mobile Number", Toast.LENGTH_SHORT).show();
                    }
                    if (!validation.mobile(mobile)){
                        x=1;
                        Toast.makeText(BookCab.this,"Enter Correct Mobile no Address",Toast.LENGTH_SHORT).show();
                    }


                if(x==0)
                {

                        Boolean insert= DB.insertAmbulanceBookingData(reg_number,pname,mobile,padd,pcode,AmbType,SelectTime,Selecteddate,status);
                        if(insert==true)
                        {
                            System.out.println("Data inserted");
                            Toast.makeText(BookCab.this, "Request send successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),CustomerLogin.class);
                            startActivity(intent);
                        }
                        else
                        {
                            System.out.println("Data not inserted");
                            Toast.makeText(BookCab.this, "Restration end ", Toast.LENGTH_LONG).show();
                        }
                    }

            }}
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