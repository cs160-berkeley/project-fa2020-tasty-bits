package com.example.tastybits;

import java.text.SimpleDateFormat;

public class QAItem {

    public enum QAType {
        QUESTION,
        ANSWER,
    }

    private QAType qaType;
    private boolean isDetailsItem;
    private String id;
    private String otherId;
    private String categoryName = "";
    private String titleText = "";
    private String descriptionText = "";
    private String nameText = "";
    private int voteScore = 0;
    private int clickScore = 0;
    private boolean userDidVote = false;
    private boolean userDidClick = false;
    private boolean userOwns = false;
    private long createdAt = 0;
    private long updatedAt = 0;
    private long deletedAt = 0;

    public QAItem(QAType qaType, boolean isDetailsItem, String id, String otherId, String categoryName, String titleText, String descriptionText, String nameText, int voteScore, int clickScore, boolean userDidVote, boolean userDidClick, boolean userOwns, Object createdAt, Object updatedAt, Object deletedAt) {
        this.qaType = qaType;
        this.isDetailsItem = isDetailsItem;
        this.id = id;
        this.otherId = otherId;
        this.categoryName = categoryName;
        this.titleText = titleText;
        this.descriptionText = descriptionText;
        this.nameText = nameText;
        this.voteScore = voteScore;
        this.clickScore = clickScore;
        this.userDidVote = userDidVote;
        this.userDidClick = userDidClick;
        this.userOwns = userOwns;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        long longUpdatedAt = 0;
        long longCreatedAt = 0;
        long longDeletedAt = 0;

        try {
            longUpdatedAt = sdf.parse((String) updatedAt).getTime();
            longCreatedAt = sdf.parse((String) createdAt).getTime();
            if (deletedAt != null) {
                longDeletedAt = sdf.parse((String) deletedAt).getTime();
            }
        } catch (Exception e) {

        }

        this.createdAt = longCreatedAt;
        this.updatedAt = longUpdatedAt;
        this.deletedAt = longDeletedAt;
    }

    public QAType getQaType() {
        return qaType;
    }

    public void setQaType(QAType qaType) {
        this.qaType = qaType;
    }

    public boolean getIsDetailsItem() {
        return isDetailsItem;
    }

    public void setIsDetailsItem(boolean isDetailsItem) {
        this.isDetailsItem = isDetailsItem;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
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

    public boolean isUserOwns() {
        return userOwns;
    }

    public void setUserOwns(boolean userOwns) {
        this.userOwns = userOwns;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        long longDeletedAt = 0;

        try {
            longDeletedAt = sdf.parse((String) deletedAt).getTime();
            if (deletedAt != null) {
                longDeletedAt = sdf.parse((String) deletedAt).getTime();
            }
        } catch (Exception e) {

        }
        this.deletedAt = longDeletedAt;
    }
}
