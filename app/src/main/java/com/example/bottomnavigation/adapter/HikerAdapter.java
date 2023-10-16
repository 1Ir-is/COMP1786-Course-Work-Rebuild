package com.example.bottomnavigation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.model.Hiker;

import java.util.List;

public class HikerAdapter extends RecyclerView.Adapter<HikerAdapter.HikerViewHolder> {
    private List<Hiker> hikers; // Replace Hiker with your data model

    public HikerAdapter(List<Hiker> hikers) {
        this.hikers = hikers;
    }

    @NonNull
    @Override
    public HikerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_hiker, parent, false);
        return new HikerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HikerViewHolder holder, int position) {
        Hiker hiker = hikers.get(position);

        // Bind the hiker data to the views in the list item layout
        holder.nameTextView.setText(hiker.getName());
        holder.locationTextView.setText(hiker.getLocation());
        // Add more bindings for other fields
    }

    @Override
    public int getItemCount() {
        return hikers.size();
    }

    public class HikerViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView locationTextView;
        // Add more TextViews for other fields

        public HikerViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            locationTextView = itemView.findViewById(R.id.textViewLocation);
            // Initialize other TextViews
        }
    }
}
