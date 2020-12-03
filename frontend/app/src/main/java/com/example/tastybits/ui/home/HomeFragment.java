package com.example.tastybits.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.GetQuestionsQuery;
import com.example.GetSuggestedQuestionsQuery;
import com.example.GetUserQuery;
import com.example.GetYourQuestionsQuery;
import com.example.tastybits.AsyncCallback;
import com.example.tastybits.Constants;
import com.example.tastybits.LoginManager;
import com.example.tastybits.MainActivity;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;
import com.example.tastybits.ui.answerview.AnswerItem;
import com.example.tastybits.ui.answerview.AnswerRecyclerViewAdapter;
import com.example.tastybits.ui.questionview.QuestionItem;
import com.example.tastybits.ui.questionview.QuestionRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private QuestionRecyclerViewAdapter suggestedQuestionsAdapter;
    private QuestionRecyclerViewAdapter yourQuestionsAdapter;
    private AnswerRecyclerViewAdapter yourAnswersAdapter;


    private RecyclerView suggestedQuestionsRecyclerView;
    private RecyclerView yourQuestionsRecyclerView;
    private RecyclerView yourAnswersRecyclerView;

    private TextView suggestedButton;
    private TextView yourQuestionsButton;
    private TextView yourAnswersButton;

    private enum ViewState {
        SUGGESTED,
        YOUR_ANSWERS,
        YOUR_QUESTIONS,
    }

    private void toggleViewOn(RecyclerView view, TextView label) {
        view.setVisibility(View.VISIBLE);
        label.setTextColor(getResources().getColor(R.color.blue_text, null));
        String labelText = label.getText().toString();
        SpannableString content = new SpannableString(labelText);
        content.setSpan(new UnderlineSpan(), 0, labelText.length(), 0);
        label.setText(content);
    }

    private void toggleViewOff(RecyclerView view, TextView label) {
        view.setVisibility(View.GONE);
        label.setTextColor(getResources().getColor(R.color.tab_indicator_text, null));
        label.setText(label.getText().toString());
    }

    private void setState(ViewState state) {

        switch (state) {
            case SUGGESTED:
                toggleViewOn(suggestedQuestionsRecyclerView, suggestedButton);
                toggleViewOff(yourQuestionsRecyclerView, yourQuestionsButton);
                toggleViewOff(yourAnswersRecyclerView, yourAnswersButton);
                break;
            case YOUR_QUESTIONS:
                toggleViewOff(suggestedQuestionsRecyclerView, suggestedButton);
                toggleViewOn(yourQuestionsRecyclerView, yourQuestionsButton);
                toggleViewOff(yourAnswersRecyclerView, yourAnswersButton);
                break;
            case YOUR_ANSWERS:
                toggleViewOff(suggestedQuestionsRecyclerView, suggestedButton);
                toggleViewOff(yourQuestionsRecyclerView, yourQuestionsButton);
                toggleViewOn(yourAnswersRecyclerView, yourAnswersButton);
                break;
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_home, container, false);


        suggestedQuestionsRecyclerView = baseView.findViewById(R.id.suggestedQuestionsRecyclerView);
        suggestedQuestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        suggestedQuestionsAdapter = new QuestionRecyclerViewAdapter(getActivity(), new ArrayList<QuestionItem>());
        suggestedQuestionsRecyclerView.setAdapter(suggestedQuestionsAdapter);


        yourQuestionsRecyclerView = baseView.findViewById(R.id.yourQuestionsRecyclerView);
        yourQuestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        yourQuestionsAdapter = new QuestionRecyclerViewAdapter(getActivity(), new ArrayList<QuestionItem>());
        yourQuestionsRecyclerView.setAdapter(yourQuestionsAdapter);


        yourAnswersRecyclerView = baseView.findViewById(R.id.yourAnswersRecyclerView);
        yourAnswersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        yourAnswersAdapter = new AnswerRecyclerViewAdapter(getActivity(), new ArrayList<AnswerItem>());
        yourAnswersRecyclerView.setAdapter(yourAnswersAdapter);

        CardView coinsCardView = baseView.findViewById(R.id.coinCardView);

        coinsCardView.setOnClickListener((view) -> {
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW);
            websiteIntent.setData(Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSf1i-zoPee9YCrft34yf7w3K7XM9Yf2Q9kiOQ7HVOGTgpAbfQ/viewform"));
            startActivity(websiteIntent);
        });

        suggestedButton = baseView.findViewById(R.id.suggestedButton);
        suggestedButton.setOnClickListener((view) -> {
            setState(ViewState.SUGGESTED);
        });

        yourQuestionsButton = baseView.findViewById(R.id.yourQuestionsButton);
        yourQuestionsButton.setOnClickListener((view) -> {
            setState(ViewState.YOUR_QUESTIONS);
        });

        yourAnswersButton = baseView.findViewById(R.id.yourAnswersButton);
        yourAnswersButton.setOnClickListener((view) -> {
            setState(ViewState.YOUR_ANSWERS);
        });


        setState(ViewState.SUGGESTED);

        NetworkRequest.getInstance().queryGetUser(new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                getActivity().runOnUiThread(() -> {
                    GetUserQuery.GetUser user = (GetUserQuery.GetUser) result;

                    TextView coinsValue = baseView.findViewById(R.id.coinsValue);
                    coinsValue.setText(Integer.toString(user.answerScore() + user.answerVoteScore() + user.questionVoteScore() + user.questionScore()));

                    TextView name = baseView.findViewById(R.id.nameText);

                    name.setText(user.name() == null ? "Name Unset": user.name());

                    TextView studentType = baseView.findViewById(R.id.studentTypeText);

                    switch (user.studentType()) {
                        case GENERAL:
                            studentType.setText("General Cal Student");
                        case FIRSTGEN:
                            studentType.setText("First Generation Student");
                        case TRANSFER:
                            studentType.setText("Transfer Student");
                        case INTERNATIONAL:
                            studentType.setText("International Student");
                        case $UNKNOWN:
                            studentType.setText("Cal Student");
                    }
                });
            }

            @Override
            public void onException(Exception e) {

            }
        });


        NetworkRequest.getInstance().queryYourQuestions(new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                List<GetYourQuestionsQuery.GetYourQuestion> qList = (List<GetYourQuestionsQuery.GetYourQuestion>) result;

                for (GetYourQuestionsQuery.GetYourQuestion question : qList) {
                    QuestionItem qItem = new QuestionItem(question.id(), question.title(),
                            question.description());
                    getActivity().runOnUiThread(() -> yourQuestionsAdapter.addQuestion(qItem));
                }
            }

            @Override
            public void onException(Exception e) {

            }
        });

        NetworkRequest.getInstance().querySuggestedQuestions(new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                List<GetSuggestedQuestionsQuery.GetSuggestedQuestion> qList = (List<GetSuggestedQuestionsQuery.GetSuggestedQuestion>) result;

                for (GetSuggestedQuestionsQuery.GetSuggestedQuestion question : qList) {
                    QuestionItem qItem = new QuestionItem(question.id(), question.title(),
                            question.description());
                    getActivity().runOnUiThread(() -> suggestedQuestionsAdapter.addQuestion(qItem));
                }
            }

            @Override
            public void onException(Exception e) {

            }
        });

        return baseView;
    }



}