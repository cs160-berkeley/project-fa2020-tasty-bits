package com.example.tastybits.ui.questionview;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.GetQuestionsQuery;
import com.example.tastybits.AsyncCallback;
import com.example.tastybits.Constants;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.QAItem;
import com.example.tastybits.QARecyclerViewAdapter;
import com.example.tastybits.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class QuestionViewFragment extends Fragment{
    private static final String TAG = "QuestionsViewFragment";
    private QARecyclerViewAdapter qrv_adapter;

    private TextView newText;
    private TextView popularText;
    private SortState state;

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

    private enum SortState {
        NEW,
        POPULAR
    }

    public SortState getState() {
        return state;
    }

    public void setState(SortState sortState) {
        if (sortState == SortState.NEW) {
            state = SortState.NEW;
            toggleViewOn(newText);
            toggleViewOff(popularText);
            qrv_adapter.sortByCreatedAt();
        } else {
            state = SortState.POPULAR;
            toggleViewOn(popularText);
            toggleViewOff(newText);
            qrv_adapter.sortByUpvote();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_questions_view, container, false);
        RecyclerView recyclerView = baseView.findViewById(R.id.QuestionsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(Constants.getMainActivity()));
        qrv_adapter =  new QARecyclerViewAdapter(Constants.getMainActivity(), new ArrayList<QAItem>());
        recyclerView.setAdapter(qrv_adapter);

        String categoryName = getArguments().getString(getString(R.string.category_name_key));

        TextView categoryTitle = baseView.findViewById(R.id.category_title_text);
        categoryTitle.setText(Constants.queryCategoryToDisplayNameMap.get(categoryName));


        newText = baseView.findViewById(R.id.questionsNewText);
        newText.setOnClickListener((v) -> {
            setState(SortState.NEW);
        });
        popularText = baseView.findViewById(R.id.questionsPopularText);
        popularText.setOnClickListener((v) -> {
            setState(SortState.POPULAR);
        });

        setState(SortState.NEW);

        Button addQuestion = baseView.findViewById(R.id.addQuestionButtonWithinCategory);
        Bundle args = new Bundle();
        args.putString(getString(R.string.category_name_key), categoryName);
        addQuestion.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionpost_fragment, args));


        NetworkRequest.getInstance().queryQuestions(categoryName, new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                List<GetQuestionsQuery.GetQuestion> qList = (List<GetQuestionsQuery.GetQuestion>) result;

                for (GetQuestionsQuery.GetQuestion question : qList) {
                    String categoryName = question.categories().get(0) != null ?  Constants.queryCategoryToDisplayNameMap.get(question.categories().get(0).name()): "";


                    QAItem qaItem = new QAItem(QAItem.QAType.QUESTION, false, question.id(), null, categoryName, question.title(), question.description(),question.user().name(), question.voteScore(), question.clickScore(), question.userDidVote(), question.userDidClick(), question.userOwns(), question.createdAt(), question.updatedAt(), question.deletedAt());

                    Constants.getMainActivity().runOnUiThread(() -> qrv_adapter.addItem(qaItem));
                }

                Constants.getMainActivity().runOnUiThread(() -> setState(getState()));

                if (qList.size() == 0) {
                    Constants.getMainActivity().runOnUiThread(() -> baseView.findViewById(R.id.noQuestionsTextView).setVisibility(View.VISIBLE));
                }
            }

            @Override
            public void onException(Exception e) { }
        });

        return baseView;
    }

}