package com.example.managerworkofstatecadres.listWork.work;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hq.manager_work.R;

import java.util.ArrayList;
import java.util.List;

public class workRecycleAdapter extends RecyclerView.Adapter<workRecycleAdapter.MyViewHolder> {

DatabaseReference database  = FirebaseDatabase.getInstance().getReference("user");
    ArrayList<workOject> slist;
    private  Clicklist1 mclick;
interface   Clicklist1 {
    void onclickUpdate(workOject user);
    void onclickDelete(workOject user);
}
    public workRecycleAdapter(ArrayList<workOject> slist, Clicklist1 clicklist) {
        this.slist = slist;
        this.mclick = clicklist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        workOject users = slist.get(position);
        if (users == null) {
            return;
        }
        holder.textTitle.setText("Name work: " + users.getNamecv());
        holder.textContext.setText("Context work: " + users.getContextcv());

        holder.textTime.setText("Time work: " + users.getTimecv());
        holder.textLocation.setText("Location work: " + users.getLocation());
        holder.textFloor.setText("Floor work: " + users.getFloor());
        holder.textRoom.setText("Room work: " + users.getRoom());
        holder.textCritical.setText("Critical work:"+users.getCritical());
        holder.buttonUpdate.setOnClickListener(view -> {
            mclick.onclickUpdate(users);
        });

        holder.buttonDelete.setOnClickListener(view -> {
            mclick.onclickDelete(users);
        });

    }

    @Override
    public int getItemCount() {
        if (slist != null) {
            return slist.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textContext, textTitle, textTime, textLocation,textFloor,textRoom,textCritical;
Button buttonUpdate,buttonDelete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


                    textTitle= itemView.findViewById(R.id.text_view_titleWk);    textFloor= itemView.findViewById(R.id.text_view_floorWk);
                    textContext= itemView.findViewById(R.id.text_view_contextWk);
                    textTime= itemView.findViewById(R.id.text_view_timeWk);
                    textLocation= itemView.findViewById(R.id.text_view_locationWk);
                    textRoom=itemView.findViewById(R.id.text_view_roomWk);
                    textCritical= itemView.findViewById(R.id.text_view_criticalWK);
            buttonUpdate= itemView.findViewById(R.id.buttonUpdate);
                    buttonDelete= itemView.findViewById(R.id.buttonDelete);
        }
    }
}




