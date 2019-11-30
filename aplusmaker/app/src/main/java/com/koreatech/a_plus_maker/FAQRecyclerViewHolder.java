package com.koreatech.a_plus_maker;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FAQRecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView mquestion;
    public FAQRecyclerViewHolder(View itemView) {
        super(itemView);
        mquestion = (TextView) itemView.findViewById(R.id.question);
    }
}

