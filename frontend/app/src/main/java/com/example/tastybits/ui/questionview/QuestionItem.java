package com.example.tastybits.ui.questionview;

import androidx.annotation.NonNull;

/**
 * com.example.tastybits.ui.questionview.QuestionItem class represents the information of a specific question
 */
public class QuestionItem {

    private String id;
    private String questionText;
    private String descriptionText;
    private int upvotes;
    private int views;
    private boolean userDidVote;


    public QuestionItem(String id, String questionText, String descriptionText, int upvotes,
                        int views, boolean userDidVote) {
        this.id = id;
        this.questionText = questionText;
        this.descriptionText = descriptionText;
        this.upvotes = upvotes;
        this.views = views;
        this.userDidVote = userDidVote;

    }


    public QuestionItem(String id, String questionText) {
        this(id, questionText, "", 0, 0, false);
    }

    public QuestionItem(String id, String title, String description) {
        this(id, title, description, 0, 0, false);
    }

    @NonNull
    @Override
    public String toString() {
        return questionText + ", " + descriptionText;
    }

    public boolean getUserDidVote() { return userDidVote; }

    public void setUserDidVote(boolean userDidVote) {
        this.userDidVote = userDidVote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }


}
