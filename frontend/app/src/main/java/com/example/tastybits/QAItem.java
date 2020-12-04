package com.example.tastybits;

public class QAItem {

    public enum QAType {
        QUESTION,
        ANSWER,
    }

    private QAType qaType;
    private String id;
    private String categoryName = "";
    private String titleText = "";
    private String descriptionText = "";
    private String nameText = "";
    private int voteScore = 0;
    private int clickScore = 0;
    private boolean userDidVote = false;
    private boolean userDidClick = false;

    public QAItem(QAType qaType, String id, String categoryName, String titleText, String descriptionText, String nameText, int voteScore, int clickScore, boolean userDidVote, boolean userDidClick) {
        this.qaType = qaType;
        this.id = id;
        this.categoryName = categoryName;
        this.titleText = titleText;
        this.descriptionText = descriptionText;
        this.nameText = nameText;
        this.voteScore = voteScore;
        this.clickScore = clickScore;
        this.userDidVote = userDidVote;
        this.userDidClick = userDidClick;
    }

    public QAType getQaType() {
        return qaType;
    }

    public void setQaType(QAType qaType) {
        this.qaType = qaType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public int getVoteScore() {
        return voteScore;
    }

    public void setVoteScore(int voteScore) {
        this.voteScore = voteScore;
    }

    public int getClickScore() {
        return clickScore;
    }

    public void setClickScore(int clickScore) {
        this.clickScore = clickScore;
    }

    public boolean isUserDidVote() {
        return userDidVote;
    }

    public void setUserDidVote(boolean userDidVote) {
        this.userDidVote = userDidVote;
    }

    public boolean isUserDidClick() {
        return userDidClick;
    }

    public void setUserDidClick(boolean userDidClick) {
        this.userDidClick = userDidClick;
    }
}
