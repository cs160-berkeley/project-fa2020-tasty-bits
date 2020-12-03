package com.example.tastybits.ui.answerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastybits.Constants;
import com.example.tastybits.R;
import com.example.tastybits.ui.questionview.QuestionItem;
import com.example.tastybits.ui.questionview.QuestionRecyclerViewAdapter;

import java.util.ArrayList;

public class AnswerRecyclerViewAdapter extends RecyclerView.Adapter<AnswerRecyclerViewAdapter.ViewHolder> {
    private ArrayList<AnswerItem> answerList;
    private Activity activity;

    public AnswerRecyclerViewAdapter(Activity activity, ArrayList<AnswerItem> answerData) {
        this.answerList = answerData;
        this.activity = activity;
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
        AnswerItem ai = answerList.get(position);
        holder.bindTo(activity, ai, position);
    }

    @Override
    public int getItemCount() {
        return answerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView answerTextView;
        private final TextView upvotesTextView;
        private final ToggleButton upvoteButton;
        private final CardView baseCardView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            answerTextView = itemView.findViewById(R.id.answerTextView);
            upvotesTextView = itemView.findViewById(R.id.upvotesTextView);
            upvoteButton = itemView.findViewById(R.id.upvotesButton);
            baseCardView = itemView.findViewById(R.id.baseView);
        }

        void bindTo(Activity activity, AnswerItem answerItem, int position) {
            answerTextView.setText(answerItem.getAnswerText());
            upvotesTextView.setText(String.valueOf(answerItem.getUpvotes()));
            baseCardView.setCardBackgroundColor(Color.parseColor(Constants.cycleColors[position % Constants.cycleColors.length]));
        }
    }

}
