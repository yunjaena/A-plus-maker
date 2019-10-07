package com.koreatehc.a_plus_maker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheatAdapter extends RecyclerView.Adapter<CheatAdapter.ViewHolder> {
    private RecyclerViewClicked recyclerViewClicked;
    private ArrayList<String> cheatSheetList;

    public void setOnClickedRecyclerView(RecyclerViewClicked recyclerViewClicked) {
        this.recyclerViewClicked = recyclerViewClicked;
    }

    public CheatAdapter(ArrayList<String> cheatSheetList) {
        this.cheatSheetList = cheatSheetList;
    }

    @NonNull
    @Override
    public CheatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cheat_sheet_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheatAdapter.ViewHolder holder, final int position) {
        String title = cheatSheetList.get(position);
        holder.titleTextview.setText(title);
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewClicked != null)
                    recyclerViewClicked.onClickedRecyclerView(position, R.id.cheat_sheet_delete_button);
            }
        });

        holder.rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewClicked != null)
                    recyclerViewClicked.onClickedRecyclerView(position, R.id.cheat_sheet_rewind_button);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cheatSheetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public Button rewindButton;
        public Button deleteButton;
        public TextView titleTextview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rewindButton = itemView.findViewById(R.id.cheat_sheet_rewind_button);
            deleteButton = itemView.findViewById(R.id.cheat_sheet_delete_button);
            titleTextview = itemView.findViewById(R.id.cheat_sheet_title_textview);
        }
    }

    public interface RecyclerViewClicked {
        void onClickedRecyclerView(int position, int id);
    }
}
