package com.example.tastybits.ui.questionview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastybits.R;

import java.util.ArrayList;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "QuestionsRecyclerViewAdapter";
    private ArrayList<QuestionItem> questionList;

    public QuestionRecyclerViewAdapter(Context context, ArrayList<QuestionItem> questionData ) {
        this.questionList = questionData;
    }


    /**
     * Needs to be executed on main UI thread
     * @param questionItem
     */
    public void addQuestion(QuestionItem questionItem) {
        questionList.add(0, questionItem);
        notifyItemInserted(0);
    }

    public QuestionItem getQuestion(int position) {
        return questionList.get(position);
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
        holder.bindTo(qi);
        Bundle bundle = new Bundle();
        bundle.putString("QuestionId", qi.getId());
        holder.itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.answerview_fragment, bundle));
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionTextView;
        private final TextView upvotesTextView;
        private final TextView viewsTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            upvotesTextView = itemView.findViewById(R.id.upvotesTextView);
            viewsTextView = itemView.findViewById(R.id.viewsTextView);
        }

        void bindTo(QuestionItem questionItem) {
            questionTextView.setText(questionItem.getQuestionText());
        }
    }

}
