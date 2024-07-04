package com.example.bookingcab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FillVisitorForm extends AppCompatActivity {
TextView date;
EditText vname,vno,vflatno,vmob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_visitor_form);
        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
//get hold of textview.
         date  = (TextView) findViewById(R.id.date1);
//set it as current date.
        date.setText(date_n);
        vname=(EditText) findViewById(R.id.name1);
        vno=(EditText) findViewById(R.id.vehicleNO);
        vflatno=(EditText) findViewById(R.id.Flatno);
        vmob=(EditText) findViewById(R.id.MobileNo);
    }
}