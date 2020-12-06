package com.example.tastybits.ui.answerview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import com.example.CreateAnswerMutation;
import com.example.tastybits.AsyncCallback;
import com.example.tastybits.Constants;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;

public class AnswerPostFragment extends Fragment{
    private static final String TAG = "AnswerPostFragment";
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_answer_post, container, false);

        String questionId = getArguments().getString(getString(R.string.question_id_key));

        EditText contentText = baseView.findViewById(R.id.contentText);
        Button postAnswerButton = baseView.findViewById(R.id.answerConfirmPostButton);
        postAnswerButton.setOnClickListener((view) -> {
            if (contentText.getText().toString().equals("")) {
                Toast.makeText(getActivity(), "Please fill out the answer field.", Toast.LENGTH_LONG).show();
                return;
            }


                NetworkRequest.getInstance().querySentiment(contentText.getText().toString(), new AsyncCallback() {
                @Override
                public void onCompleted(Object result) {
                    String sentimentText = (String) result;
                    if (sentimentText.equals("VERY_ANGRY")) {
                        Toast.makeText(getActivity(), "Please make your answer more positive to help keep the community safe and happy for everyone.", Toast.LENGTH_LONG).show();
                    } else {
                        NetworkRequest.getInstance().mutationCreateAnswer(questionId, contentText.getText().toString(), new AsyncCallback() {
                            @Override
                            public void onCompleted(Object result) {
                                CreateAnswerMutation.CreateAnswer answer = (CreateAnswerMutation.CreateAnswer) result;

                                Activity activity = getActivity();
                                activity.runOnUiThread(() -> {
                                    Navigation.findNavController(view).navigateUp();
                                });

                            }

                            @Override
                            public void onException(Exception e) {

                            }
                        });
                    }
                }

                @Override
                public void onException(Exception e) {

                }
            });
        });



        return baseView;
    }
}
