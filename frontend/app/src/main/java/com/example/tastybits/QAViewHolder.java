package com.example.tastybits;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.UpsertAnswerVoteMutation;
import com.example.UpsertQuestionClickMutation;
import com.example.UpsertQuestionVoteMutation;

public class QAViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView;
    private final TextView descriptionTextView;
    private final TextView categoryTextView;
    private final TextView nameTextView;
    private final TextView votesTextView;
    private final ToggleButton voteToggleButton;
    private final TextView clicksTextView;
    private final ToggleButton clicksToggleButton;
    private final ConstraintLayout baseView;

    public QAViewHolder(@NonNull View itemView) {
        super(itemView);
        titleTextView = itemView.findViewById(R.id.titleText);
        descriptionTextView = itemView.findViewById(R.id.descriptionText);
        categoryTextView = itemView.findViewById(R.id.categoryText);
        nameTextView = itemView.findViewById(R.id.nameText);
        votesTextView = itemView.findViewById(R.id.voteScoreValue);
        voteToggleButton = itemView.findViewById(R.id.voteToggleButton);
        clicksTextView = itemView.findViewById(R.id.clickScoreValue);
        clicksToggleButton = itemView.findViewById(R.id.clickImageView);
        baseView = itemView.findViewById(R.id.baseView);
    }

    public void bindTo(Activity activity, QAItem item, int position, boolean isSingleCard, AsyncCallback callback) {

        // just make the click number invisible for now
        clicksTextView.setVisibility(View.INVISIBLE);

        if (item.getQaType() == QAItem.QAType.QUESTION) {

            clicksTextView.setText(String.valueOf(item.getClickScore()));
            clicksToggleButton.setChecked(item.isUserDidClick());

            if (!isSingleCard) {
                baseView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NetworkRequest.getInstance().mutationUpsertQuestionClick(item.getId(),
                                new AsyncCallback() {
                                    @Override
                                    public void onCompleted(Object result) {
                                        UpsertQuestionClickMutation.UpsertQuestionClick upsertQuestionClick = (UpsertQuestionClickMutation.UpsertQuestionClick) result;
                                        item.setClickScore(upsertQuestionClick.question().clickScore());
                                        activity.runOnUiThread(()->{
                                            clicksTextView.setText(String.valueOf(upsertQuestionClick.question().clickScore()));
                                            clicksToggleButton.setChecked(upsertQuestionClick.question().userDidClick());
                                        });
                                    }

                                    @Override
                                    public void onException(Exception e) {

                                    }
                                });

                        Bundle bundle = new Bundle();
                        bundle.putString(activity.getString(R.string.question_id_key), item.getId());
                        bundle.putString(activity.getString(R.string.question_category_name_key), Constants.displayToQueryCategoryNameMap.get(item.getCategoryName()));
                        Navigation.createNavigateOnClickListener(R.id.answerview_fragment, bundle).onClick(v);
                    }
                });
            }

            voteToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    NetworkRequest.getInstance().mutationUpsertQuestionVote(item.getId(),
                            isChecked, new AsyncCallback() {
                                @Override
                                public void onCompleted(Object result) {
                                    UpsertQuestionVoteMutation.UpsertQuestionVote upsertQuestionVote = (UpsertQuestionVoteMutation.UpsertQuestionVote) result;
                                    item.setVoteScore(upsertQuestionVote.question().voteScore());
                                    activity.runOnUiThread(()->{
                                        votesTextView.setText(String.valueOf(upsertQuestionVote.question().voteScore()));

                                    });
                                }

                                @Override
                                public void onException(Exception e) {

                                }
                            });

                }
            });

        } else {

            clicksTextView.setVisibility(View.GONE);
            clicksToggleButton.setVisibility(View.GONE);

            voteToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    NetworkRequest.getInstance().mutationUpsertAnswerVote(item.getId(),
                            isChecked, new AsyncCallback() {
                                @Override
                                public void onCompleted(Object result) {
                                    UpsertAnswerVoteMutation.UpsertAnswerVote upsertAnswerVote = (UpsertAnswerVoteMutation.UpsertAnswerVote) result;
                                    item.setVoteScore(upsertAnswerVote.answer().voteScore());
                                    activity.runOnUiThread(()->{
                                        votesTextView.setText(String.valueOf(upsertAnswerVote.answer().voteScore()));

                                    });
                                }

                                @Override
                                public void onException(Exception e) {

                                }
                            });

                }
            });
        }


        votesTextView.setText(String.valueOf(item.getVoteScore()));
        voteToggleButton.setChecked(item.isUserDidVote());


        conditionalSetTextView(titleTextView, item.getTitleText());
        conditionalSetTextView(descriptionTextView, item.getDescriptionText());
        conditionalSetTextView(nameTextView, item.getNameText());
        conditionalSetTextView(categoryTextView, item.getCategoryName());


    }
    public void prepareSingleCard(Activity activity) {
        baseView.setBackgroundColor(activity.getColor(R.color.primary));
        titleTextView.setTextColor(activity.getColor(R.color.white));
        descriptionTextView.setTextColor(activity.getColor(R.color.white));
        nameTextView.setTextColor(activity.getColor(R.color.white));
        categoryTextView.setTextColor(activity.getColor(R.color.white));
        votesTextView.setTextColor(activity.getColor(R.color.white));
        clicksTextView.setTextColor(activity.getColor(R.color.white));
    }

    public void bindTo(Activity activity, QAItem item, int position, boolean isSingleCard) {
        bindTo(activity, item, position, isSingleCard, null);
    }

    private void conditionalSetTextView(TextView textView, String s) {
        if (s != null && !s.equals("")) {
            textView.setText(s);
        } else {
            textView.setVisibility(View.GONE);
        }
    }
}