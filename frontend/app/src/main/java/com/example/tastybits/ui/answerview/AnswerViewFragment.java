package com.example.tastybits.ui.answerview;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.GetAnswerQuery;
import com.example.GetQuestionsQuery;
import com.example.tastybits.AsyncCallback;
import com.example.tastybits.Constants;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.QAItem;
import com.example.tastybits.QARecyclerViewAdapter;
import com.example.tastybits.QAViewHolder;
import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

public class AnswerViewFragment extends Fragment {
    private static final String TAG = "AnswerViewFragment";
    private QARecyclerViewAdapter ans_adapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_answer_view, container, false);
        RecyclerView recyclerView = baseView.findViewById(R.id.AnswersRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ans_adapter = new QARecyclerViewAdapter(getActivity(), new ArrayList<QAItem>());
        recyclerView.setAdapter(ans_adapter);
        String questionId = getArguments().getString(getString(R.string.question_id_key));
        String categoryName = getArguments().getString(getString(R.string.question_category_name_key));

        NetworkRequest.getInstance().queryQuestions(categoryName, new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                List<GetQuestionsQuery.GetQuestion> qList = (List<GetQuestionsQuery.GetQuestion>) result;
                for (GetQuestionsQuery.GetQuestion question: qList) {
                    if (question.id().equals(questionId)) {
                        getActivity().runOnUiThread(() -> {
                            QAViewHolder vh = new QAViewHolder(baseView.findViewById(R.id.questionCardView));
                            String categoryName = question.categories().get(0) != null ?  Constants.queryCategoryToDisplayNameMap.get(question.categories().get(0).name()): "";
                            QAItem qaItem = new QAItem(QAItem.QAType.QUESTION, question.id(), categoryName, question.title(), question.description(),question.user().name(), question.voteScore(), question.clickScore(), question.userDidVote(), question.userDidClick());
                            vh.bindTo(getActivity(), qaItem, 0, true);
                        });
                    }

                }
            }

            @Override
            public void onException(Exception e) {

            }
        });

        NetworkRequest.getInstance().queryAnswer(questionId, new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                List<GetAnswerQuery.GetAnswer> aList = (List<GetAnswerQuery.GetAnswer>) result;
                for (GetAnswerQuery.GetAnswer answer: aList) {
                    String categoryName = answer.question().categories().get(0) != null ?  Constants.queryCategoryToDisplayNameMap.get(answer.question().categories().get(0).name()): "";
                    QAItem qaItem = new QAItem(QAItem.QAType.ANSWER, answer.id(), categoryName, answer.content(), "", answer.user().name(), answer.voteScore(), -1, answer.userDidVote(), false);
                    getActivity().runOnUiThread(() -> ans_adapter.addItem(qaItem));
                }
            }

            @Override
            public void onException(Exception e) {

            }
        });
        Button addAnswer = baseView.findViewById(R.id.addAnswerButton);
        Bundle bundle = new Bundle();
        bundle.putString("QuestionId", questionId);
        addAnswer.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionhub_answer, bundle));
        return baseView;
    }



}
