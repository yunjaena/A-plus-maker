package com.koreatehc.a_plus_maker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<FandQItem> mFandQItems;

    Context mContext;
    public RecyclerViewAdapter(ArrayList itemList) {
        mFandQItems = itemList;
    }
    // 필수 오버라이드 : View 생성, ViewHolder 호출
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        mContext = parent.getContext();

        RecyclerViewHolder holder = new RecyclerViewHolder(v);

        return holder;
    }
    // 필수 오버라이드 : 재활용되는 View 가 호출, Adapter 가 해당 position 에 해당하는 데이터를 결합
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {

        // 해당 position 에 해당하는 데이터 결합
        holder.mquestion.setText(mFandQItems.get(position).getQuestion());

        // 이벤트처리 : 생성된 List 중 선택된 목록번호를 Toast로 출력
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer ="1번선택";
                Intent intent = new Intent(mContext, AnswerActivity.class);
                intent.putExtra("answer", mFandQItems.get(position).getAnswer());
                mContext.startActivity(intent);
            }
        });
    }
    // 필수 오버라이드 : 데이터 갯수 반환
    @Override
    public int getItemCount() {
        return mFandQItems.size();
    }
}

