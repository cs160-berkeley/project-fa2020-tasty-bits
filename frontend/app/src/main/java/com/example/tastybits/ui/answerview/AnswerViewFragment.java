package com.example.tastybits.ui.answerview;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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
import com.example.tastybits.ui.questionview.QuestionViewFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AnswerViewFragment extends Fragment {
    private static final String TAG = "AnswerViewFragment";
    private QARecyclerViewAdapter ans_adapter;


    private TextView newText;
    private TextView popularText;
    private AnswerViewFragment.SortState state;
    private enum SortState {
        NEW,
        POPULAR
    }

    public AnswerViewFragment.SortState getState() {
        return state;
    }

    private void toggleViewOn(TextView label) {
        label.setTextColor(getResources().getColor(R.color.blue_text, null));
        String labelText = label.getText().toString();
        SpannableString content = new SpannableString(labelText);
        content.setSpan(new UnderlineSpan(), 0, labelText.length(), 0);
        label.setText(content);
    }

    private void toggleViewOff(TextView label) {
        label.setTextColor(getResources().getColor(R.color.tab_indicator_text, null));
        label.setText(label.getText().toString());
    }

    public void setState(AnswerViewFragment.SortState sortState) {
        if (sortState == AnswerViewFragment.SortState.NEW) {
            state = AnswerViewFragment.SortState.NEW;
            toggleViewOn(newText);
            toggleViewOff(popularText);
            ans_adapter.sortByCreatedAt();
        } else {
            state = AnswerViewFragment.SortState.POPULAR;
            toggleViewOn(popularText);
            toggleViewOff(newText);
            ans_adapter.sortByUpvote();
        }
    }

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



        newText = baseView.findViewById(R.id.answersNewText);
        newText.setOnClickListener((v) -> {
            setState(AnswerViewFragment.SortState.NEW);
        });
        popularText = baseView.findViewById(R.id.answersPopularText);
        popularText.setOnClickListener((v) -> {
            setState(AnswerViewFragment.SortState.POPULAR);
        });

        setState(AnswerViewFragment.SortState.NEW);


        QAViewHolder vh = new QAViewHolder(baseView.findViewById(R.id.respondToQuestionCardView));
        vh.prepareSingleCard(getActivity());

        NetworkRequest.getInstance().queryQuestions(categoryName, new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                List<GetQuestionsQuery.GetQuestion> qList = (List<GetQuestionsQuery.GetQuestion>) result;
                for (GetQuestionsQuery.GetQuestion question: qList) {
                    if (question.id().equals(questionId)) {
                        String categoryName = question.categories().get(0) != null ?  Constants.queryCategoryToDisplayNameMap.get(question.categories().get(0).name()): "";

                        QAItem qaItem = new QAItem(QAItem.QAType.QUESTION, true, question.id(), null, categoryName, question.title(), question.description(),question.user().name(), question.voteScore(), question.clickScore(), question.userDidVote(), question.userDidClick(), question.userOwns(), question.createdAt(), question.updatedAt(), question.deletedAt());
                        getActivity().runOnUiThread(() -> {
                            vh.bindTo(getActivity(), qaItem, 0);
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

                    QAItem qaItem = new QAItem(QAItem.QAType.ANSWER, true, answer.id(), answer.questionId(), categoryName, answer.content(), "", answer.user().name(), answer.voteScore(), -1, answer.userDidVote(), false, answer.userOwns(), answer.createdAt(), answer.updatedAt(), answer.deletedAt());

                    getActivity().runOnUiThread(() -> ans_adapter.addItem(qaItem));
                }

                getActivity().runOnUiThread(() -> setState(getState()));

                if (aList.size() == 0) {
                    getActivity().runOnUiThread(() -> baseView.findViewById(R.id.noAnswersTextView).setVisibility(View.VISIBLE));
                }
            }

            @Override
            public void onException(Exception e) {

            }
        });
        Button addAnswer = baseView.findViewById(R.id.addAnswerButton);
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.question_id_key), questionId);
        addAnswer.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.answerpost_fragment, bundle));
        return baseView;
    }



}
