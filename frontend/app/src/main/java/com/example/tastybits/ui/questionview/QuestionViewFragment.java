package com.example.tastybits.ui.questionview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastybits.AsyncCallback;
import com.example.tastybits.Constants;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;

import java.util.ArrayList;

public class QuestionViewFragment extends Fragment{
    private static final String TAG = "QuestionsViewFragment";
    private QuestionRecyclerViewAdapter qrv_adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_questions_view, container, false);
        RecyclerView recyclerView = baseView.findViewById(R.id.QuestionsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        qrv_adapter =  new QuestionRecyclerViewAdapter(getActivity(), new ArrayList<QuestionItem>());
        recyclerView.setAdapter(qrv_adapter);

        String categoryName = getArguments().getString(getString(R.string.category_name_key));

        TextView categoryTitle = baseView.findViewById(R.id.category_title_text);
        categoryTitle.setText(Constants.queryCategoryToDisplayNameMap.get(categoryName));

        NetworkRequest.getInstance().queryQuestions(categoryName, new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                QuestionItem qi = (QuestionItem) result;
                getActivity().runOnUiThread(() -> qrv_adapter.addQuestion(qi));
            }

            @Override
            public void onException(Exception e) { }
        });

        return baseView;
    }

}