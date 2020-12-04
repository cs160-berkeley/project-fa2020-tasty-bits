package com.example.tastybits.ui.answerview;

import androidx.annotation.NonNull;

public class AnswerItem {

    private String id;
    private String answerText;
    private String questionId;
    private int upvotes;
    private boolean userDidVote;

    public AnswerItem(String id, String answerText, int upvotes, String questionId, boolean userDidVote) {
        this.id = id;
        this.answerText = answerText;
        this.questionId = questionId;
        this.upvotes = upvotes;
        this.userDidVote = userDidVote;
    }


    public boolean getUserDidVote() {
        return userDidVote;
    }

    public void setUserDidVote(boolean userDidVote) {
        this.userDidVote = userDidVote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

}
