package com.example.tastybits.ui.questionview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastybits.R;

import java.util.ArrayList;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "QuestionsRecyclerViewAdapter";
    private ArrayList<QuestionItem> questionList;
    private  onQuestionListener onQuestionListener;
    public QuestionRecyclerViewAdapter(Context context, ArrayList<QuestionItem> questionData, onQuestionListener onQuestionListener ) {
        this.questionList = questionData;
        this.onQuestionListener = onQuestionListener;
        //fakeSomeData(questionList);
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
        return new ViewHolder(view, onQuestionListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuestionItem qi = questionList.get(position);
        holder.bindTo(qi);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView questionTextView;
        private final TextView upvotesTextView;
        private final TextView viewsTextView;
        onQuestionListener onQuestionListener;

        ViewHolder(@NonNull View itemView, onQuestionListener onQuestionListener) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            upvotesTextView = itemView.findViewById(R.id.upvotesTextView);
            viewsTextView = itemView.findViewById(R.id.viewsTextView);
            this.onQuestionListener = onQuestionListener;
            itemView.setOnClickListener(this);
        }

        void bindTo(QuestionItem questionItem) {
            questionTextView.setText(questionItem.getQuestionText());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onQuestionListener.onQuestionClick(getAdapterPosition());
        }
    }
    public interface onQuestionListener{
        void onQuestionClick(int position);
    }
}
