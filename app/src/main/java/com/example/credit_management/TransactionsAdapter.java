package com.example.credit_management;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.MyViewHolder> {

    private Context context;
    int position;
    private ArrayList T_id, sender, receiver, sender_b1,sender_b2,receiver_b1,receiver_b2 ;

    TransactionsAdapter(Context context, ArrayList T_id,ArrayList sender,ArrayList receiver,ArrayList sender_b1, ArrayList sender_b2,
                         ArrayList receiver_b1, ArrayList receiver_b2){


        this.context = context;
        this.T_id = T_id;
        this.sender = sender;
        this.receiver = receiver;
        this.sender_b1 = sender_b1;
        this.sender_b2 = sender_b2;
        this.receiver_b1 = receiver_b1;
        this.receiver_b2 = receiver_b2;

    }


    @NonNull
    @Override
    public TransactionsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater2 = LayoutInflater.from(context);
        View view2 = inflater2.inflate(R.layout.transaction_row, parent, false);
        return new MyViewHolder(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionsAdapter.MyViewHolder holder, int position) {
        this.position = position;
        holder.T_id_txt.setText(String.valueOf(T_id.get(position)));
        holder.sender_txt.setText(String.valueOf(sender.get(position)));
        holder.receiver_txt.setText(String.valueOf(receiver.get(position)));
        holder.sender_b1_txt.setText(String.valueOf(sender_b1.get(position)));
        holder.sender_b2_txt.setText(String.valueOf(sender_b2.get(position)));
        holder.receiver_b1_txt.setText(String.valueOf(receiver_b1.get(position)));
        holder.receiver_b2_txt.setText(String.valueOf(receiver_b2.get(position)));



    }

    @Override
    public int getItemCount() {return T_id.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView T_id_txt , sender_txt, receiver_txt, sender_b1_txt, sender_b2_txt, receiver_b1_txt, receiver_b2_txt;

        LinearLayout mainLayout2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            T_id_txt=itemView.findViewById(R.id.T_id);
            sender_txt=itemView.findViewById(R.id.T_sender);
            receiver_txt=itemView.findViewById(R.id.T_receiver);
            sender_b1_txt=itemView.findViewById(R.id.T_sender_balance1);
            sender_b2_txt=itemView.findViewById(R.id.T_sender_balance2);
            receiver_b1_txt=itemView.findViewById(R.id.T_receiver_balance1);
            receiver_b2_txt=itemView.findViewById(R.id.T_receiver_balance2);
            mainLayout2=itemView.findViewById(R.id.mainLayout2);

        }
    }
}
