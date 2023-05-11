package com.android.pharmaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.pharmaapp.R;
import com.android.pharmaapp.models.Component;

import java.util.ArrayList;

public class ComponentAdapter extends RecyclerView.Adapter<ComponentAdapter.ViewHolder> {
    private ArrayList<Component> components;
    private Context context;
    public ComponentAdapter(ArrayList<Component> components, Context context) {
        this.components = components;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Component component = components.get(position);
        holder.componentNameTextView.setText(component.componentName);
        holder.descriptionTextView.setText(component.Description);
        switch (component.Harmful_not)
        {
            case "Harmful":
            {
                 holder.descriptionTextView.setTextColor(ContextCompat.getColor(context, R.color.red));
                 break;
            }
            case "Not Harmful":
            {
                holder.descriptionTextView.setTextColor(ContextCompat.getColor(context, R.color.green));
                holder.descriptionTextView.setText("Safe");
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return components.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView componentNameTextView;
        public TextView descriptionTextView;

        public ViewHolder(View view) {
            super(view);
            componentNameTextView = view.findViewById(R.id.ingredient_name);
            descriptionTextView = view.findViewById(R.id.harm_desc);
            // Find other views as needed
        }
    }
}

