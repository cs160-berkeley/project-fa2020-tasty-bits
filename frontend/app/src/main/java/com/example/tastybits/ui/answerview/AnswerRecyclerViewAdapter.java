package com.example.tastybits.ui.answerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastybits.R;
import com.example.tastybits.ui.questionview.QuestionItem;
import com.example.tastybits.ui.questionview.QuestionRecyclerViewAdapter;

import java.util.ArrayList;

public class AnswerRecyclerViewAdapter extends RecyclerView.Adapter<AnswerRecyclerViewAdapter.ViewHolder> {
    private ArrayList<AnswerItem> answerList;

    public AnswerRecyclerViewAdapter(Context context, ArrayList<AnswerItem> answerData) {
        answerList = answerData;
        //fakeSomeData(questionList);

    }
    private void fakeSomeData(ArrayList<AnswerItem> alist) {
        for (int i=0; i<100; i++) {
            alist.add(new AnswerItem(String.valueOf(i),"question ", String.valueOf(i)));
        }
    }
    /**
     * Needs to be executed on main UI thread
     * @param answerItem
     */
    public void addAnswer(AnswerItem answerItem) {
        answerList.add(0, answerItem);
        notifyItemInserted(0);
    }

    @NonNull
    @Override
    public AnswerRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_answers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AnswerItem qi = answerList.get(position);
        holder.bindTo(qi);
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView answerTextView;
        private final TextView upvotesTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            answerTextView = itemView.findViewById(R.id.answerTextView);
            upvotesTextView = itemView.findViewById(R.id.upvotesTextView);

        }

        void bindTo(AnswerItem answerItem) {
            answerTextView.setText(answerItem.getAnswerText());
        }
    }

}
