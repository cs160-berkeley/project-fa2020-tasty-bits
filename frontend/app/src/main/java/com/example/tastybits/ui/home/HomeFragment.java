package com.example.tastybits.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.GetQuestionsQuery;
import com.example.GetSuggestedQuestionsQuery;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_home, container, false);


        RecyclerView suggestedQuestionsRecyclerView = baseView.findViewById(R.id.suggestedQuestionsRecyclerView);
        suggestedQuestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        suggestedQuestionsAdapter = new QuestionRecyclerViewAdapter(getActivity(), new ArrayList<QuestionItem>());
        suggestedQuestionsRecyclerView.setAdapter(suggestedQuestionsAdapter);

        RecyclerView yourQuestionsRecyclerView = baseView.findViewById(R.id.yourQuestionsRecyclerView);
        yourQuestionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        yourQuestionsAdapter = new QuestionRecyclerViewAdapter(getActivity(), new ArrayList<QuestionItem>());
        yourQuestionsRecyclerView.setAdapter(yourQuestionsAdapter);


        RecyclerView yourAnswersRecyclerView = baseView.findViewById(R.id.yourAnswersRecyclerView);
        yourAnswersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        yourAnswersAdapter = new AnswerRecyclerViewAdapter(getActivity(), new ArrayList<AnswerItem>());
        yourAnswersRecyclerView.setAdapter(yourAnswersAdapter);


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