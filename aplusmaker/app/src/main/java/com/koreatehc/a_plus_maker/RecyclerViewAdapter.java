package com.koreatehc.a_plus_maker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<Item> mItems;

    Context mContext;
    public RecyclerViewAdapter(ArrayList itemList) {
        mItems = itemList;
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
        holder.mquestion.setText(mItems.get(position).question);

        // 이벤트처리 : 생성된 List 중 선택된 목록번호를 Toast로 출력
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer ="1번선택";
                Intent intent = new Intent(mContext, AnswerActivity.class);
                switch(mItems.get(position).getNum()){
                    case 1: intent.putExtra("answer", "A. 문의하지마 답변 : ");
                        break;
                    case 2: intent.putExtra("answer", "A. 문의그만 답변 :");
                        break;
                    case 3: intent.putExtra("answer", "A. 게임모드도 그만 답변 : ");
                        break;
                    case 4: intent.putExtra("answer", "A. 게임모드ㄴㄴ 답변 : ");
                        break;
                    case 5: intent.putExtra("answer", "A. 오탑노트 쓰지마 답변 : ");
                        break;
                    case 6: intent.putExtra("answer", "A. 오답노트nono 답변");
                        break;
                    case 7: intent.putExtra("answer", "A. 일반모드왜못해 답변");
                        break;
                    case 8: intent.putExtra("answer", "A. 일반모드면걍해 답변");
                        break;
                    case 9: intent.putExtra("answer", "A. 랜덤모드면막해 답변");
                        break;
                    case 10: intent.putExtra("answer", "A. 랜덤모드가어렵나 답변");
                        break;
                    case 11: intent.putExtra("answer", "A. 깜빡이는어려울만해 답변");
                        break;
                    case 12: intent.putExtra("answer", "A. 깜빡깜빡 답변");
                        break;
                }
                mContext.startActivity(intent);
            }
        });
    }
    // 필수 오버라이드 : 데이터 갯수 반환
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}

