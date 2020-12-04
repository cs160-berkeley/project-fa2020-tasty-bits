package com.example.tastybits.ui.answerview;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.GetAnswerQuery;
import com.example.GetQuestionsQuery;
import com.example.tastybits.AsyncCallback;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

public class AnswerViewFragment extends Fragment {
    private static final String TAG = "AnswerViewFragment";
    private AnswerRecyclerViewAdapter ans_adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_answer_view, container, false);
        RecyclerView recyclerView = baseView.findViewById(R.id.AnswersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ans_adapter = new AnswerRecyclerViewAdapter(getActivity(), new ArrayList<AnswerItem>());
        recyclerView.setAdapter(ans_adapter);
        String questionId = getArguments().getString(getString(R.string.question_id_key));
        String title = getArguments().getString(getString(R.string.question_title_key));
        String description = getArguments().getString(getString(R.string.question_description_key));
        TextView questionTitle = baseView.findViewById(R.id.questionTitle);
        TextView questionDescription = baseView.findViewById(R.id.questionDescription);
        questionTitle.setText(title);
        questionDescription.setText(description);
        NetworkRequest.getInstance().queryAnswer(questionId, new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                List<GetAnswerQuery.GetAnswer> aList = (List<GetAnswerQuery.GetAnswer>) result;
                for (GetAnswerQuery.GetAnswer answer: aList) {
                    AnswerItem aItem = new AnswerItem(answer.id(), answer.content(), answer.voteScore(), questionId, answer.userDidVote());
                    getActivity().runOnUiThread(() -> ans_adapter.addAnswer(aItem));
                }
            }

            @Override
            public void onException(Exception e) {

            }
        });
        FloatingActionButton addAnswer = baseView.findViewById(R.id.addAnswerButton);
        Bundle bundle = new Bundle();
        bundle.putString("QuestionId", questionId);
        addAnswer.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionhub_answer, bundle));
        return baseView;
    }



}
