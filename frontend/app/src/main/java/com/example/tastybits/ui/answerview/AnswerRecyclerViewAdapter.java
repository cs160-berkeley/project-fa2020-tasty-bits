package com.example.tastybits.ui.answerview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.UpsertAnswerVoteMutation;
import com.example.tastybits.AsyncCallback;
import com.example.tastybits.Constants;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;
import com.example.tastybits.ui.questionview.QuestionItem;
import com.example.tastybits.ui.questionview.QuestionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

    public void sortAnswerByUpvotes() {
        Collections.sort(answerList, (a1, a2) -> a2.getUpvotes() - a1.getUpvotes());
        notifyDataSetChanged();
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
            upvoteButton = itemView.findViewById(R.id.answerUpvoteButton);
            baseCardView = itemView.findViewById(R.id.baseView);
        }

        void bindTo(Activity activity, AnswerItem answerItem, int position) {
            answerTextView.setText(answerItem.getAnswerText());
            upvotesTextView.setText(String.valueOf(answerItem.getUpvotes()));
            baseCardView.setCardBackgroundColor(Color.parseColor(Constants.cycleColors[position % Constants.cycleColors.length]));
            upvoteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    NetworkRequest.getInstance().mutationUpsertAnswerVote(answerItem.getId(),
                            isChecked, new AsyncCallback() {
                                @Override
                                public void onCompleted(Object result) {
                                    UpsertAnswerVoteMutation.UpsertAnswerVote upsertAnswerVote = (UpsertAnswerVoteMutation.UpsertAnswerVote) result;
                                    answerItem.setUpvotes(upsertAnswerVote.answer().voteScore());
                                    activity.runOnUiThread(()->{
                                        upvotesTextView.setText(String.valueOf(upsertAnswerVote.answer().voteScore()));
                                        sortAnswerByUpvotes();
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
