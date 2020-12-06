package com.example.tastybits.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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

import com.example.EditUserMutation;
import com.example.GetAnswerQuery;
import com.example.GetQuestionsQuery;
import com.example.GetSuggestedQuestionsQuery;
import com.example.GetUserQuery;
import com.example.GetYourAnswersQuery;
import com.example.GetYourQuestionsQuery;
import com.example.tastybits.AsyncCallback;
import com.example.tastybits.Constants;
import com.example.tastybits.LoginManager;
import com.example.tastybits.MainActivity;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.QAItem;
import com.example.tastybits.QARecyclerViewAdapter;
import com.example.tastybits.R;


import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private QARecyclerViewAdapter suggestedQuestionsAdapter;
    private QARecyclerViewAdapter yourQuestionsAdapter;
    private QARecyclerViewAdapter yourAnswersAdapter;


    private RecyclerView suggestedQuestionsRecyclerView;
    private RecyclerView yourQuestionsRecyclerView;
    private RecyclerView yourAnswersRecyclerView;

    private TextView suggestedButton;
    private TextView yourQuestionsButton;
    private TextView yourAnswersButton;
    private TextView explanationText;

    private EditText nameEditText;

    private boolean noSuggestedQuestions = false;
    private boolean noYourQuestions = false;
    private boolean noYourAnswers = false;


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
                if (noSuggestedQuestions) {
                    explanationText.setText("No suggested questions to display.");
                } else {
                    explanationText.setText("Questions that you could help answer or might interest you.");
                }
                break;
            case YOUR_QUESTIONS:
                toggleViewOff(suggestedQuestionsRecyclerView, suggestedButton);
                toggleViewOn(yourQuestionsRecyclerView, yourQuestionsButton);
                toggleViewOff(yourAnswersRecyclerView, yourAnswersButton);
                if (noYourQuestions) {
                    explanationText.setText("You haven't asked anything yet.");
                } else {
                    explanationText.setText("Here are the questions you've posted.");
                }
                break;
            case YOUR_ANSWERS:
                toggleViewOff(suggestedQuestionsRecyclerView, suggestedButton);
                toggleViewOff(yourQuestionsRecyclerView, yourQuestionsButton);
                toggleViewOn(yourAnswersRecyclerView, yourAnswersButton);
                if (noYourAnswers) {
                    explanationText.setText("You haven't answered any questions yet.");
                } else {
                    explanationText.setText("Here are answers that you've contributed to the community.");
                }
                break;
        }
    }

    public void editName() {
        String name = nameEditText.getText().toString();
        if (name.equals("")) {
            nameEditText.setText("Your Name");
            Toast.makeText(getActivity(), "Please fill out the name field.", Toast.LENGTH_LONG).show();
            return;
        }
        NetworkRequest.getInstance().mutationEditUser(name, new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                getActivity().runOnUiThread(() -> {
                    EditUserMutation.EditUser user = (EditUserMutation.EditUser) result;
                    nameEditText.setText(user.name() == null ? "Name Unset": user.name());
                });
            }

            @Override
            public void onException(Exception e) {

            }
        });
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_home, container, false);


        nameEditText = baseView.findViewById(R.id.nameText);

        nameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId){
                    case EditorInfo.IME_ACTION_DONE:
                    case EditorInfo.IME_ACTION_NEXT:
                    case EditorInfo.IME_ACTION_PREVIOUS:
                        editName();
                        return true;
                }
                return false;
            }
        });

        nameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    editName();
                }
            }
            });

        suggestedQuestionsRecyclerView = baseView.findViewById(R.id.suggestedQuestionsRecyclerView);
        suggestedQuestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        suggestedQuestionsAdapter = new QARecyclerViewAdapter(getActivity(), new ArrayList<QAItem>());
        suggestedQuestionsRecyclerView.setAdapter(suggestedQuestionsAdapter);


        yourQuestionsRecyclerView = baseView.findViewById(R.id.yourQuestionsRecyclerView);
        yourQuestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        yourQuestionsAdapter = new QARecyclerViewAdapter(getActivity(), new ArrayList<QAItem>());
        yourQuestionsRecyclerView.setAdapter(yourQuestionsAdapter);


        yourAnswersRecyclerView = baseView.findViewById(R.id.yourAnswersRecyclerView);
        yourAnswersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        yourAnswersAdapter = new QARecyclerViewAdapter(getActivity(), new ArrayList<QAItem>());
        yourAnswersRecyclerView.setAdapter(yourAnswersAdapter);

        explanationText = baseView.findViewById(R.id.explanationText);

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
                    coinsValue.setText(String.valueOf(user.answerScore() + user.answerVoteScore() + user.questionVoteScore() + user.questionScore()));

                    nameEditText.setText(user.name() == null ? "Your Name": user.name());

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


        NetworkRequest.getInstance().querySuggestedQuestions(new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                List<GetSuggestedQuestionsQuery.GetSuggestedQuestion> qList = (List<GetSuggestedQuestionsQuery.GetSuggestedQuestion>) result;

                for (GetSuggestedQuestionsQuery.GetSuggestedQuestion question : qList) {
                    String categoryName = question.categories().get(0) != null ?  Constants.queryCategoryToDisplayNameMap.get(question.categories().get(0).name()): "";
                    QAItem qaItem = new QAItem(QAItem.QAType.QUESTION, question.id(), categoryName, question.title(), question.description(),question.user().name(), question.voteScore(), question.clickScore(), question.userDidVote(), question.userDidClick());
                    getActivity().runOnUiThread(() -> suggestedQuestionsAdapter.addItem(qaItem));
                }
                if (qList.size() == 0) {
                    noSuggestedQuestions = true;
                }
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
                    String categoryName = question.categories().get(0) != null ?  Constants.queryCategoryToDisplayNameMap.get(question.categories().get(0).name()): "";
                    QAItem qaItem = new QAItem(QAItem.QAType.QUESTION, question.id(), categoryName, question.title(), question.description(),question.user().name(), question.voteScore(), question.clickScore(), question.userDidVote(), question.userDidClick());
                    getActivity().runOnUiThread(() -> yourQuestionsAdapter.addItem(qaItem));
                }
                if (qList.size() == 0) {
                    noYourQuestions = true;
                }
            }

            @Override
            public void onException(Exception e) {

            }
        });



        NetworkRequest.getInstance().queryYourAnswers(new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                List<GetYourAnswersQuery.GetYourAnswer> aList = (List<GetYourAnswersQuery.GetYourAnswer>) result;

                for (GetYourAnswersQuery.GetYourAnswer answer : aList) {
                    String categoryName = answer.question().categories().get(0) != null ?  Constants.queryCategoryToDisplayNameMap.get(answer.question().categories().get(0).name()): "";
                    QAItem qaItem = new QAItem(QAItem.QAType.ANSWER, answer.id(), categoryName, answer.content(), "", answer.user().name(), answer.voteScore(), -1, answer.userDidVote(), false);
                    getActivity().runOnUiThread(() -> yourAnswersAdapter.addItem(qaItem));
                }
                if (aList.size() == 0) {
                    noYourAnswers = true;
                }
            }

            @Override
            public void onException(Exception e) {

            }
        });

        return baseView;
    }
}