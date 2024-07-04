package com.example.bookingcab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.*;
import com.example.*;
import com.example.bookingcab.*;

public class ApprovCustomerRequest extends AppCompatActivity {
Button ApprovButton,RejectButton;
EditText Req_id,DriverName,DriverNo,Req_id1;
DBHelper DB;
    String Sta="Approved";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approv_customer_request);
        ApprovButton =(Button) findViewById(R.id.ApprovButton);
        RejectButton =(Button)findViewById(R.id.RejectButton);
        Req_id=(EditText) findViewById(R.id.Req_id);
        DriverName=(EditText) findViewById(R.id.DriverName);
        DriverNo=(EditText) findViewById(R.id.DriverNo);
        Req_id1=(EditText) findViewById(R.id.Req_no1);
        DB=new DBHelper(this);
        RejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String req_id = Req_id1.getText().toString();
                String Drivername="";
                String Driverno="";
                if (req_id.equals("")) {
                    Toast.makeText(ApprovCustomerRequest.this, "pease enter all the fields", Toast.LENGTH_SHORT).show();
                } else
                {
                    String s="Rejected";
                    Boolean insert = DB.UpdateAmbulanceBooking(s, req_id,Drivername,Driverno);
                    if (insert == true) {
                        Toast.makeText(ApprovCustomerRequest.this, "updated Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), CustomerLogin.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(ApprovCustomerRequest.this, "Restration end ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


        ApprovButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String req_id = Req_id.getText().toString();
                String Drivername = DriverName.getText().toString();
                String Driverno = DriverNo.getText().toString();
                if (req_id.equals("")||(Driverno.equals(""))||(Driverno.equals(""))) {
                    Toast.makeText(ApprovCustomerRequest.this, "pease enter all the fields", Toast.LENGTH_SHORT).show();
                } else
                {
String s="Approved";
                Boolean insert = DB.UpdateAmbulanceBooking(s, req_id,Drivername,Driverno);
                if (insert == true) {
                    Toast.makeText(ApprovCustomerRequest.this, "updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), CustomerLogin.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(ApprovCustomerRequest.this, "Restration end ", Toast.LENGTH_SHORT).show();
                }

            }
        }
        });
    }
}



