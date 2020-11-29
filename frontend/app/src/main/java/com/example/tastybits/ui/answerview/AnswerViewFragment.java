package com.example.tastybits.ui.answerview;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.tastybits.AsyncCallback;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

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
//        Log.i(TAG, "loading answer view fragment of " + questionId);
        NetworkRequest.getInstance().queryAnswer(questionId, new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                AnswerItem ai = (AnswerItem) result;
                getActivity().runOnUiThread(() -> ans_adapter.addAnswer(ai));
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
