package com.example.tastybits.ui.questionview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastybits.Constants;
import com.example.tastybits.R;

import java.util.ArrayList;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder>{

    private ArrayList<QuestionItem> questionList;
    public QuestionRecyclerViewAdapter(Context context, ArrayList<QuestionItem> questionData) {
        questionList = questionData;
    }


    /**
     * Needs to be executed on main UI thread
     * @param questionItem
     */
    public void addQuestion(QuestionItem questionItem) {
        questionList.add(0, questionItem);
        notifyItemInserted(0);
    }

    @NonNull
    @Override
    public QuestionRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionItem qi = questionList.get(position);
        holder.bindTo(qi, position);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView questionTextView;
        private TextView upvotesTextView;
        private TextView viewsTextView;
        private CardView baseCardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            upvotesTextView = itemView.findViewById(R.id.upvotesTextView);
            viewsTextView = itemView.findViewById(R.id.viewsTextView);
            baseCardView = itemView.findViewById(R.id.base_cardview);
        }

        void bindTo(QuestionItem questionItem, int position) {
            questionTextView.setText(questionItem.getQuestionText());
            upvotesTextView.setText(String.valueOf(questionItem.getUpvotes()));
            viewsTextView.setText(String.valueOf(questionItem.getViews()));
            baseCardView.setCardBackgroundColor(Color.parseColor(Constants.cycleColors[position % Constants.cycleColors.length]));
        }
    }
}
