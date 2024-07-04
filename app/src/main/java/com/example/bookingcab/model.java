package com.example.bookingcab;

public class model {
    String Reg_no,pname,mobile,padd,AmbType,pcode,date,time,Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public model(String reg_no, String pname, String mobile, String padd, String pcode, String AmbType, String date, String time, String Status) {
        Reg_no = reg_no;
        this.pname = pname;
        this.mobile = mobile;
        this.padd = padd;
        this.pcode = pcode;
        this.AmbType=AmbType;
        this.date = date;
        this.time = time;
        this.Status=Status;
    }

    public String getAmbType() {
        return AmbType;
    }

    public void setAmbType(String ambType) {
        AmbType = ambType;
    }

    public String getReg_no() {
        return Reg_no;
    }

    public void setReg_no(String reg_no) {
        Reg_no = reg_no;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPadd() {
        return padd;
    }

    public void setPadd(String padd) {
        this.padd = padd;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
