package com.example.bookingcab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBName = "ambulance.db";

    public DBHelper(Context context) {
        super(context, DBName, null, 1);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        onCreate(db);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
      MyDB.execSQL("create Table if not exists CustomerInfo(username TEXT Primary key,password TEXT)");
       //MyDB.execSQL("create Table if not exists BookingDetails(id INTEGER Primary key AUTOINCREMENT,username TEXT,mobile TEXT,Patient_Address TEXT,pincode TEXT,Time TEXT,Date TEXT)");
      MyDB.execSQL("create Table if not exists BookingDetailInformation(id INTEGER Primary key AUTOINCREMENT,reg_Mobile TEXT,patient_Name TEXT,mobile TEXT,Patient_Address TEXT,pincode TEXT,AmbulanceType TEXT,Time TEXT,Date TEXT,Status TEXT,DriverName TEXT,DriverContactNo TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists CustomerInfo");
        MyDB.execSQL("drop Table if exists BookingDetailInformation");
    }


    public Boolean insertData(String username,String password) {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long results=MyDB.insert("CustomerInfo",null,contentValues);
        if(results==-1)
            return false;
        else
            return true;

    }

    public Boolean insertAmbulanceBookingData(String reg_Mobile ,String patient_Name ,String mobile ,String Patient_Address ,String pincode,String AmbulanceType,String Time,String Date,String Status ) {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
      //  contentValues.put("id",id);
        contentValues.put("reg_Mobile",reg_Mobile);
        contentValues.put("patient_Name",patient_Name);
        contentValues.put("mobile",mobile);
        contentValues.put("Patient_Address",Patient_Address);
        contentValues.put("pincode",pincode);
        contentValues.put("AmbulanceType",AmbulanceType);
        contentValues.put("Time",Time);
        contentValues.put("Date",Date);
        contentValues.put("Status",Status);
        long results=MyDB.insert("BookingDetailInformation",null,contentValues);
        if(results==-1)
            return false;
        else
            return true;

    }
    public boolean checkusername(String username)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from BookingDetailInformation where reg_Mobile=?",new String[] {username});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
            return false;
    }
    public boolean UpdateAmbulanceBooking(String s, String s1,String Drivername,String Driverno) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Status", s);
        values.put("DriverName",Drivername);
        values.put("DriverContactNo",Driverno);
        db.update("BookingDetailInformation", values, "id=?", new String[] {s1});
        db.close();
        return true;
    }


    public Cursor retrieveDataFromBookingAmbulance()
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        String status="Under Review";
        Cursor res = MyDB.rawQuery("Select * from BookingDetailInformation where Status=? order by id desc",new String[] {status});
        return res;
    }
    public Cursor getApproveAmbulanceDetails(String mobile)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor res = MyDB.rawQuery("Select * from BookingDetailInformation  where reg_Mobile=?",new String[] {mobile});
        return res;
    }
    public boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase MyDB=this.getWritableDatabase();
        Cursor cursor=MyDB.rawQuery("Select * from CustomerInfo where username=? and password=?",new String[] {username,password});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
