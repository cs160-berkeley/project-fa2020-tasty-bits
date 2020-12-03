package com.example.tastybits.ui.questionview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.UpsertQuestionVoteMutation;
import com.example.tastybits.AsyncCallback;
import com.example.tastybits.Constants;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QuestionRecyclerViewAdapter extends RecyclerView.Adapter<QuestionRecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "app_" + NetworkRequest.class.getSimpleName();
    private ArrayList<QuestionItem> questionList;
    private Activity activity;

    public QuestionRecyclerViewAdapter(Activity activity, ArrayList<QuestionItem> questionData ) {
        this.questionList = questionData;
        this.activity = activity;
    }


    /**
     * Needs to be executed on main UI thread
     * @param questionItem
     */
    public void addQuestion(QuestionItem questionItem) {
        questionList.add(0, questionItem);
        Log.i(TAG, String.valueOf(questionItem.getUpvotes()));
        notifyItemInserted(0);
    }

    public QuestionItem getQuestion(int position) {
        return questionList.get(position);
    }

    public void sortQuestionsByUpvotes() {
        Collections.sort(questionList, (q1, q2) -> q2.getUpvotes() - q1.getUpvotes());
        notifyDataSetChanged();
    }

    public void sortQuestionsByViews() {
        Collections.sort(questionList, (q1, q2)-> q2.getViews() - q1.getViews());
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
        holder.bindTo(activity, qi, position);
        Bundle bundle = new Bundle();
        bundle.putString("QuestionId", qi.getId());
        holder.itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.answerview_fragment, bundle));
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
        private ToggleButton upvoteButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.questionTextView);
            upvotesTextView = itemView.findViewById(R.id.upvotesTextView);
            viewsTextView = itemView.findViewById(R.id.viewsTextView);
            baseCardView = itemView.findViewById(R.id.base_cardview);
            upvoteButton = itemView.findViewById(R.id.upvotesButton);
        }

        void bindTo(Activity activity, QuestionItem questionItem, int position) {
            questionTextView.setText(questionItem.getQuestionText());
            upvotesTextView.setText(String.valueOf(questionItem.getUpvotes()));
            viewsTextView.setText(String.valueOf(questionItem.getViews()));
            baseCardView.setCardBackgroundColor(Color.parseColor(Constants.cycleColors[position % Constants.cycleColors.length]));

            upvoteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    NetworkRequest.getInstance().mutationUpsertQuestionVote(questionItem.getId(),
                            isChecked, new AsyncCallback() {
                                @Override
                                public void onCompleted(Object result) {
                                    UpsertQuestionVoteMutation.UpsertQuestionVote upsertQuestionVote = (UpsertQuestionVoteMutation.UpsertQuestionVote) result;
                                    questionItem.setUpvotes(upsertQuestionVote.question().voteScore());
                                    activity.runOnUiThread(()->{
                                        upvotesTextView.setText(String.valueOf(upsertQuestionVote.question().voteScore()));
                                        sortQuestionsByUpvotes();
                                    });
                                }

                                @Override
                                public void onException(Exception e) {

                                }
                            });
                }
            });
        }
    }
}
