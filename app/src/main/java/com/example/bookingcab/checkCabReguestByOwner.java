package com.example.bookingcab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class checkCabReguestByOwner extends AppCompatActivity {
EditText Reg_no,pname,contact,address,pincode,date,time,status;
    DBHelper DB;
    Button btnviewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_cab_reguest_by_owner);
        Reg_no = (EditText) findViewById(R.id.Reg_no);
        pname = (EditText) findViewById(R.id.pname);
        contact = (EditText) findViewById(R.id.contact);
        address = (EditText) findViewById(R.id.address);
        pincode = (EditText) findViewById(R.id.pincode);
        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);
        status = (EditText) findViewById(R.id.status);
        btnviewAll=(Button) findViewById(R.id.btnviewAll);

    }
   /* public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }
        )
    }*/
  Cursor res;
    public void onClick(View v){
         res = DB.retrieveDataFromBookingAmbulance();
        if(res.getCount() == 0){
            return;
        }


    StringBuffer buffer = new StringBuffer();
while (res.moveToNext()){
        buffer.append("Id: "+ res.getString(0)+"n");
        buffer.append("Name: "+ res.getString(1)+"n");
        buffer.append("Surname: "+ res.getString(2)+"n");
        buffer.append("Marks: "+ res.getString(3)+"n");
        }}

public static final String Col_1 = "ID";
public static final String Col_2 = "Name";
public static final String Col_3 = "Surname";
public static final String Col_4 = "Marks";

public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
        }}