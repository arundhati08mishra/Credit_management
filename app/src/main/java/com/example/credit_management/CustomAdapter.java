package com.example.credit_management;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    private Context context;
    Activity activity;
    int position;
    private ArrayList user_id, user_name, user_email, user_credits;

    CustomAdapter(Activity activity ,Context context, ArrayList user_id,ArrayList user_name,ArrayList user_email,ArrayList user_credits ){

        this.activity= activity;
        this.context = context;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_credits = user_credits;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        this.position = position;


        holder.user_name_txt.setText(String.valueOf(user_name.get(position)));
        holder.user_email_txt.setText(String.valueOf(user_email.get(position)));
        holder.user_credits_txt.setText(String.valueOf(user_credits.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TransferActivity.class);

                //passing values through intent
                intent.putExtra("id", String.valueOf(user_id.get(position)));
                intent.putExtra("name", String.valueOf(user_name.get(position)));
                intent.putExtra("email", String.valueOf(user_email.get(position)));
                intent.putExtra("credits", String.valueOf(user_credits.get(position)));


                activity.startActivityForResult(intent , 1);
            }
        });
    }



    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user_id_txt, user_name_txt,user_email_txt,user_credits_txt;

        LinearLayout mainLayout;

         public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_name_txt=itemView.findViewById(R.id.user_name_txt);
            user_email_txt=itemView.findViewById(R.id.user_email_txt);
            user_credits_txt=itemView.findViewById(R.id.user_credits_txt);

            mainLayout=itemView.findViewById(R.id.mainLayout);




        }
    }
}
