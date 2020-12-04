package com.example.tastybits.ui.answerview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tastybits.AsyncCallback;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;

public class QuestionAnswerFragment extends Fragment{
    private static final String TAG = "QuestionAnswerFragment";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_answer, container, false);
        String questionId = getArguments().getString(getString(R.string.question_id_key));
        EditText editText = view.findViewById(R.id.editAnswer);
        String content = editText.getText().toString();
        Button posta = view.findViewById(R.id.postAnswer);
        posta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkRequest.getInstance().mutationCreateAnswer(questionId, content, new AsyncCallback() {
                    @Override
                    public void onCompleted(Object result) {
//                        AnswerItem ai = (AnswerItem) result;
                        // TODO : navigate back to question hub
                        // Navigation.findNavController(view).navigate(R.id.questionhub);
                    }

                    @Override
                    public void onException(Exception e) {

                    }
                });
            }
        });

        return view;
    }
}
