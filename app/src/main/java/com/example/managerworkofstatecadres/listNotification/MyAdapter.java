package com.example.managerworkofstatecadres.listNotification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hq.manager_work.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<ntObject> slist;

    public MyAdapter(ArrayList<ntObject> slist) {
        this.slist = slist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_holder_nt,parent,false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            ntObject  object = slist.get(position);
            if (object==null){
                return;
            }
            holder.nament.setText(object.getNament());
            holder.contextnt.setText(object.getContextnt());
            holder.timent.setText(object.getTiment());
            holder.critical.setText(object.getCritical());

    }

    @Override
    public int getItemCount() {
        if(slist!=null){return slist.size();} return 0;
    }

    public static class MyViewHolder  extends  RecyclerView.ViewHolder{
TextView  nament, contextnt,timent, critical;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nament = itemView.findViewById(R.id.tvnament);
            contextnt = itemView.findViewById(R.id.tvcontextnt);
            timent = itemView.findViewById(R.id.tvtiment);
            critical = itemView.findViewById(R.id.tvcritical);
        }
    }
}
