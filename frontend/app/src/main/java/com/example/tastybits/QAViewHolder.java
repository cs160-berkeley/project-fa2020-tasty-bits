package com.example.tastybits;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.EditAnswerMutation;
import com.example.EditQuestionMutation;
import com.example.UpsertAnswerVoteMutation;
import com.example.UpsertQuestionClickMutation;
import com.example.UpsertQuestionVoteMutation;

public class QAViewHolder extends RecyclerView.ViewHolder {
    private final EditText titleTextView;
    private final EditText descriptionTextView;
    private final TextView categoryTextView;
    private final TextView nameTextView;
    private final TextView votesTextView;
    private final ToggleButton voteToggleButton;
    private final TextView clicksTextView;
    private final ToggleButton clicksToggleButton;
    private final ConstraintLayout baseView;
    private final ImageView nameIcon;
    private final ImageView categoryIcon;

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
        nameIcon = itemView.findViewById(R.id.nameIcon);
        categoryIcon = itemView.findViewById(R.id.categoryIcon);

        baseView = itemView.findViewById(R.id.baseView);
    }

    public void bindTo(Activity activity, QAItem item, int position, boolean isSingleCard, AsyncCallback callback) {

        // just make the click number invisible for now
        clicksTextView.setVisibility(View.INVISIBLE);

        if (item.getQaType() == QAItem.QAType.QUESTION) {

            clicksTextView.setText(String.valueOf(item.getClickScore()));
            clicksToggleButton.setChecked(item.isUserDidClick());

            View.OnClickListener baseCardClick = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NetworkRequest.getInstance().mutationUpsertQuestionClick(item.getId(),
                            new AsyncCallback() {
                                @Override
                                public void onCompleted(Object result) {
                                    UpsertQuestionClickMutation.UpsertQuestionClick upsertQuestionClick = (UpsertQuestionClickMutation.UpsertQuestionClick) result;
                                    item.setClickScore(upsertQuestionClick.question().clickScore());
                                    item.setUserDidClick(upsertQuestionClick.question().userDidClick());

                                    activity.runOnUiThread(() -> {
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
            };

            if (!isSingleCard) {
                baseView.setOnClickListener(baseCardClick);
            }

            votesTextView.setText(String.valueOf(item.getVoteScore()));
            voteToggleButton.setChecked(item.isUserDidVote());

            voteToggleButton.setOnClickListener((v) -> {
                NetworkRequest.getInstance().mutationUpsertQuestionVote(item.getId(),
                        voteToggleButton.isChecked(), new AsyncCallback() {
                            @Override
                            public void onCompleted(Object result) {
                                UpsertQuestionVoteMutation.UpsertQuestionVote upsertQuestionVote = (UpsertQuestionVoteMutation.UpsertQuestionVote) result;
                                item.setVoteScore(upsertQuestionVote.question().voteScore());
                                item.setUserDidVote(upsertQuestionVote.question().userDidVote());

                                activity.runOnUiThread(() -> {
                                    votesTextView.setText(String.valueOf(upsertQuestionVote.question().voteScore()));
                                    voteToggleButton.setChecked(upsertQuestionVote.question().userDidVote());
                                });
                            }

                            @Override
                            public void onException(Exception e) {

                            }
                        });
            });
            if (item.isUserOwns() && isSingleCard) {

            titleTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    switch (actionId) {
                        case EditorInfo.IME_ACTION_DONE:
                        case EditorInfo.IME_ACTION_NEXT:
                        case EditorInfo.IME_ACTION_PREVIOUS:
                            editQuestion(activity, item);
                            return true;
                    }
                    return false;
                }
            });

            titleTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        editQuestion(activity, item);
                    }
                }
            });

            descriptionTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    switch (actionId) {
                        case EditorInfo.IME_ACTION_DONE:
                        case EditorInfo.IME_ACTION_NEXT:
                        case EditorInfo.IME_ACTION_PREVIOUS:
                            editQuestion(activity, item);
                            return true;
                    }
                    return false;
                }
            });

            descriptionTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        editQuestion(activity, item);
                    }
                }
            });

        } else {
                titleTextView.setCompoundDrawables(null, null, null, null);
                if (!isSingleCard) {
                    titleTextView.setOnClickListener((v) -> baseCardClick.onClick(titleTextView));
                }
                titleTextView.setFocusable(false);
                descriptionTextView.setCompoundDrawables(null, null, null, null);
                if (!isSingleCard) {
                    descriptionTextView.setOnClickListener((v) -> baseCardClick.onClick(titleTextView));
                }
                descriptionTextView.setFocusable(false);
            }
        } else {

            clicksTextView.setVisibility(View.GONE);
            clicksToggleButton.setVisibility(View.GONE);


            votesTextView.setText(String.valueOf(item.getVoteScore()));
            voteToggleButton.setChecked(item.isUserDidVote());

            voteToggleButton.setOnClickListener((v) -> {
                NetworkRequest.getInstance().mutationUpsertAnswerVote(item.getId(),
                        voteToggleButton.isChecked(), new AsyncCallback() {
                            @Override
                            public void onCompleted(Object result) {
                                UpsertAnswerVoteMutation.UpsertAnswerVote upsertAnswerVote = (UpsertAnswerVoteMutation.UpsertAnswerVote) result;
                                item.setVoteScore(upsertAnswerVote.answer().voteScore());
                                item.setUserDidVote(upsertAnswerVote.answer().userDidVote());

                                activity.runOnUiThread(() -> {
                                    votesTextView.setText(String.valueOf(upsertAnswerVote.answer().voteScore()));
                                    voteToggleButton.setChecked(upsertAnswerVote.answer().userDidVote());
                                });
                            }

                            @Override
                            public void onException(Exception e) {

                            }
                        });

            });

            if (item.isUserOwns()) {

            titleTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    switch (actionId) {
                        case EditorInfo.IME_ACTION_DONE:
                        case EditorInfo.IME_ACTION_NEXT:
                        case EditorInfo.IME_ACTION_PREVIOUS:
                            editAnswer(activity, item);
                            return true;
                    }
                    return false;
                }
            });

            titleTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {

                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        editAnswer(activity, item);
                    }
                }
            });

            } else {
                titleTextView.setCompoundDrawables(null, null, null, null);
                titleTextView.setFocusable(false);
                descriptionTextView.setCompoundDrawables(null, null, null, null);
                descriptionTextView.setFocusable(false);
            }
        }

        Integer categoryLeftInt = Constants.queryCategoryToIconInteger.get(Constants.displayToQueryCategoryNameMap.get(item.getCategoryName()));
        Drawable categoryIconDrawable = ResourcesCompat.getDrawable(activity.getResources(), categoryLeftInt, null);

        Drawable profileIconDrawable = ResourcesCompat.getDrawable(activity.getResources(), R.drawable.ic_person_circle_outline, null);


        conditionalSetTextView(titleTextView, item.getTitleText(), null, null);
        conditionalSetTextView(descriptionTextView, item.getDescriptionText(), null, null);
        conditionalSetTextView(nameTextView, item.getNameText(), nameIcon, profileIconDrawable);
        conditionalSetTextView(categoryTextView, item.getCategoryName(), categoryIcon, categoryIconDrawable);
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

    public void editQuestion(Activity activity, QAItem item) {
        NetworkRequest.getInstance().mutationEditQuestion(item.getId(),
                titleTextView.getText().toString(), descriptionTextView.getText().toString(), new AsyncCallback() {
                    @Override
                    public void onCompleted(Object result) {
                        EditQuestionMutation.EditQuestion editQuestion = (EditQuestionMutation.EditQuestion) result;
                        item.setTitleText(editQuestion.title());
                        item.setDescriptionText(editQuestion.description());

                        activity.runOnUiThread(() -> {
                            titleTextView.setText(editQuestion.title());
                            descriptionTextView.setText(editQuestion.description());
                        });
                    }

                    @Override
                    public void onException(Exception e) {

                    }
                });
    }

    public void editAnswer(Activity activity, QAItem item) {
        NetworkRequest.getInstance().mutationEditAnswer(item.getId(),
                titleTextView.getText().toString(), new AsyncCallback() {
                    @Override
                    public void onCompleted(Object result) {
                        EditAnswerMutation.EditAnswer editAnswer = (EditAnswerMutation.EditAnswer) result;
                        item.setTitleText(editAnswer.content());

                        activity.runOnUiThread(() -> {
                            titleTextView.setText(editAnswer.content());
                        });
                    }

                    @Override
                    public void onException(Exception e) {

                    }
                });
    }

    private void conditionalSetTextView(TextView textView, String s, ImageView imageView, Drawable img) {
        if (s != null && !s.trim().equals("")) {
            textView.setText(s);
            textView.setVisibility(View.VISIBLE);
            if (img != null && imageView != null) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageDrawable(img);
            }
        } else {
            textView.setVisibility(View.GONE);
            if (img != null && imageView != null) {
                imageView.setVisibility(View.GONE);
            }
        }
    }
}