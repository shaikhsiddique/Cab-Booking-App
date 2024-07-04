package com.example.bookingcab;

public class Validation {
    public boolean fullname(String name){
        String namepattern = "[A-Za-z ]{2,}";
        if (name.matches(namepattern))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean mobile(String mobile){
        String mobilepattern = "[7-9]{1}[0-9]{9}";
        if (mobile.matches(mobilepattern))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean password(String pass){
        String passpattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        if (pass.matches(passpattern))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
