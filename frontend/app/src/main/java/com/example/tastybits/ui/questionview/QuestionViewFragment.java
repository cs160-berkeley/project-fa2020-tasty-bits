package com.example.tastybits.ui.questionview;

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

import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;

import java.util.ArrayList;

public class QuestionViewFragment extends Fragment implements QuestionRecyclerViewAdapter.onQuestionListener {
    private static final String TAG = "QuestionsViewFragment";
    private QuestionViewViewModel mViewModel;
    private QuestionRecyclerViewAdapter qrv_adapter;

    public static QuestionViewFragment newInstance() {
        return new QuestionViewFragment();
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
        RecyclerView recyclerView = baseView.findViewById(R.id.QuestionsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        qrv_adapter = setQuestionRecyclerViewAdapter();
        recyclerView.setAdapter(qrv_adapter);

        String categoryName = getArguments().getString("CategoryName");
        //Log.i(TAG, "loading question view fragment of " + categoryName);
        NetworkRequest.getInstance().loadCategoryQuestionsRequest(categoryName, this::addQuestionCallback);

        return baseView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(QuestionViewViewModel.class);
        // TODO: Use the ViewModel
    }


    /**
     * Configures the recyclerview adapter with data upon loading this activity
     * @return
     */
    private QuestionRecyclerViewAdapter setQuestionRecyclerViewAdapter() {
        // TODO: call backend service
        return new QuestionRecyclerViewAdapter(getActivity(), new ArrayList<>(), this);
    }

    private void addQuestionCallback(QuestionItem qi) {
        this.getActivity().runOnUiThread(() -> qrv_adapter.addQuestion(qi));
    }


    @Override
    public void onQuestionClick(int position) {
        Log.i(TAG, "loading answer of question " + qrv_adapter.getQuestion(position).getId());
        qrv_adapter.getQuestion(position).getId();
//        Bundle classBundle = new Bundle();
//        classBundle.putString("CategoryName", "classPlanning");
//        Navigation.createNavigateOnClickListener(R.id.answerview_fragment, classBundle);
    }
}