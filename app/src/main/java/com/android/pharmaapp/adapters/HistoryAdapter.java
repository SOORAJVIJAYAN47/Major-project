package com.android.pharmaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.pharmaapp.R;
import com.android.pharmaapp.models.Component;
import com.android.pharmaapp.models.UserHistory;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<UserHistory> userHistory;
    private Context context;
    public HistoryAdapter(ArrayList<UserHistory> userHistory, Context context) {
        this.userHistory = userHistory;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserHistory userHistory1 = userHistory.get(position);
        holder.medicineNameTextView.setText(userHistory1.medicineName);
        if(userHistory1.status.equals("0"))
        {
            holder.statusTextView.setText("Pending");
            holder.msgLayout.setVisibility(View.GONE);
        }
        else {
            holder.docName.setText(userHistory1.doctorName);
            holder.docEmail.setText(userHistory1.doctorEmail);
            holder.docMsg.setText(userHistory1.doctorMessage);
            holder.docAltMed.setText(userHistory1.alternativeMedicineName);
        }
    }

    @Override
    public int getItemCount() {
        return userHistory.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView medicineNameTextView;
        public TextView statusTextView,docName,docEmail,docMsg,docAltMed;
        LinearLayout msgLayout;
        public ViewHolder(View view) {
            super(view);
            medicineNameTextView = view.findViewById(R.id.ingredient_name);
            statusTextView = view.findViewById(R.id.status);
            docName = view.findViewById(R.id.doc_name);
            docEmail = view.findViewById(R.id.doctor_email);
            docMsg = view.findViewById(R.id.doctor_message);
            docAltMed = view.findViewById(R.id.doctor_alt_med);
            msgLayout=view.findViewById(R.id.msg_layout);
            // Find other views as needed
        }
    }
}

