package com.example.tastybits.ui.questionhub;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuestionHubViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public QuestionHubViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}