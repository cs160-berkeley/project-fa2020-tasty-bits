package com.example.tastybits.ui.answerview;

import androidx.annotation.NonNull;

public class AnswerItem {

    private String id;
    private String answerText;
    private String questionId;
    private int upvotes;

    public AnswerItem(String id, String answerText, String questionId) {
        this.answerText = answerText;
        this.questionId = questionId;
        this.upvotes = 0;
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
