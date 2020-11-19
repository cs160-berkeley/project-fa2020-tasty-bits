package com.example.tastybits;

/**
 * com.example.tastybits.QuestionItem class represents the information of a specific question
 */
public class QuestionItem {

    private String questionText;
    private int upvotes;
    private int views;

    public QuestionItem(String questionText) {
        this.questionText = questionText;
        upvotes = 0;
        views = 0;
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




}
