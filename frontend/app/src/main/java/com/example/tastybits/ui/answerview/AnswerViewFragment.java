package com.example.tastybits.ui.answerview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;
import com.example.tastybits.ui.questionview.QuestionViewViewModel;

import java.util.ArrayList;

public class AnswerViewFragment extends Fragment {
    private static final String TAG = "AnswerViewFragment";
    private AnswerRecyclerViewAdapter ans_adapter;
    private AnswerViewViewModel mViewModel;
    public static  AnswerViewFragment newInstance() {
        return new AnswerViewFragment();
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
        ans_adapter = setAnswerRecyclerViewAdapter();
        recyclerView.setAdapter(ans_adapter);
        // TODO: getarguments? chang here
//        String categoryName = getArguments().getString("CategoryName");
//        //Log.i(TAG, "loading answer view fragment of " + categoryName);
//        NetworkRequest.getInstance().loadCategoryAnswersRequest(categoryName, this::addAnswerCallback);

        return baseView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AnswerViewViewModel.class);
        // TODO: Use the ViewModel
    }

    /**
     * Configures the recyclerview adapter with data upon loading this activity
     * @return
     */
    private AnswerRecyclerViewAdapter setAnswerRecyclerViewAdapter() {
        // TODO: call backend service
        return new AnswerRecyclerViewAdapter(getActivity(), new ArrayList<>());
    }

    private void addAnswerCallback(AnswerItem ai) {
        this.getActivity().runOnUiThread(() -> ans_adapter.addAnswer(ai));
    }
}
