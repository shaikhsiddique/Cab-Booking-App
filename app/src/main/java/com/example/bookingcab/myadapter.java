package com.example.bookingcab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.*;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myviewholder> {
    private ArrayList<model>dataholder = new ArrayList <model>();
//ArrayList<model> dataholder;

    public myadapter(ArrayList<model> dataholder) {
        System.out.println("h1");
         this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("h2");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        System.out.println("h3");
        holder.dreg_no.setText(dataholder.get(position).getReg_no());
        holder.dpname.setText(dataholder.get(position).getPname());
        holder.dcontact.setText(dataholder.get(position).getMobile());
        holder.dpadd.setText(dataholder.get(position).getPadd());
        holder.dpno.setText(dataholder.get(position).getPcode());
        holder.dAmbType.setText(dataholder.get(position).getAmbType());
        holder.ddate.setText(dataholder.get(position).getDate());
        holder.dtime.setText(dataholder.get(position).getTime());
        holder.dStatus.setText(dataholder.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        System.out.println("h4");
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {

TextView dreg_no,dpname,dcontact,dpadd,dpno,dAmbType,ddate,dtime,dStatus;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            dreg_no =(TextView)itemView.findViewById(R.id.Reg_no);
            System.out.println("dreg_no "+dreg_no);
            dpname =(TextView)itemView.findViewById(R.id.pname);
            System.out.println("dpname "+dpname);
            dcontact =(TextView)itemView.findViewById(R.id.contact);
            System.out.println("dcontact "+dcontact);
            dpadd =(TextView)itemView.findViewById(R.id.add);
            System.out.println("dpadd "+dpadd);
            dpno =(TextView)itemView.findViewById(R.id.pincode);
            System.out.println("dpno"+dpno);
            dAmbType=(TextView)itemView.findViewById(R.id.AmbulanceType);
            System.out.println("Amb type"+dAmbType);
            ddate =(TextView)itemView.findViewById(R.id.date);
            System.out.println("ddate "+ddate);
            dtime =(TextView)itemView.findViewById(R.id.time);
            System.out.println("dtime "+dtime);
            dStatus =(TextView)itemView.findViewById(R.id.Status);
            System.out.println("dstatus "+dStatus);

        }
    }
}
