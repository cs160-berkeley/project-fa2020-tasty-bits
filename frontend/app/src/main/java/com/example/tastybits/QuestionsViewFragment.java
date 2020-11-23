package com.example.tastybits;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class QuestionsViewFragment extends Fragment {

    private QuestionsViewViewModel mViewModel;
    private QuestionRecyclerViewAdapter qrv_adapter;

    public static QuestionsViewFragment newInstance() {
        return new QuestionsViewFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_questions_view, container, false);
        RecyclerView recyclerView = baseView.findViewById(R.id.QuestionsRecyclerView0);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        qrv_adapter = setQuestionRecyclerViewAdapter();
        recyclerView.setAdapter(qrv_adapter);

        NetworkRequest.getInstance().sendQuestionRequest("ckhpkonee002043nz25jc7gmd", this::sendQuestionsUpdate);
        return baseView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(QuestionsViewViewModel.class);
        // TODO: Use the ViewModel
    }


    /**
     * Configures the recyclerview adapter with data upon loading this activity
     * @return
     */
    private QuestionRecyclerViewAdapter setQuestionRecyclerViewAdapter() {
        // TODO: call backend service
        return new QuestionRecyclerViewAdapter(getActivity(), new ArrayList<QuestionItem>());
    }

    private void sendQuestionsUpdate(QuestionItem qi) {
        this.getActivity().runOnUiThread(() -> qrv_adapter.addQuestion(qi));
    }

}