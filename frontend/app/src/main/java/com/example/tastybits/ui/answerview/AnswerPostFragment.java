package com.example.tastybits.ui.answerview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.tastybits.Utilities;

public class AnswerPostFragment extends Fragment{
    private static final String TAG = "AnswerPostFragment";

    private EditText contentText;
    private TextView sentimentText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_answer_post, container, false);

        String questionId = getArguments().getString(getString(R.string.question_id_key));

        contentText = baseView.findViewById(R.id.contentText);
        sentimentText = baseView.findViewById(R.id.answerSentimentText);

        contentText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    updateSentiment();
                }
            }
        });

        contentText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                    case EditorInfo.IME_ACTION_NEXT:
                    case EditorInfo.IME_ACTION_PREVIOUS:
                        updateSentiment();
                        return true;
                }
                return false;
            }
        });

        Button postAnswerButton = baseView.findViewById(R.id.answerConfirmPostButton);
        postAnswerButton.setOnClickListener((view) -> {
            if (contentText.getText().toString().trim().equals("")) {
                Toast.makeText(Utilities.getMainActivity(), "Please fill out the answer field.", Toast.LENGTH_LONG).show();
                return;
            }


                NetworkRequest.getInstance().querySentiment(contentText.getText().toString(), new AsyncCallback() {
                @Override
                public void onCompleted(Object result) {
                    String sentimentText = (String) result;
                    if (sentimentText.equals("VERY_ANGRY") || sentimentText.equals("ANGRY") ) {
                        Utilities.getMainActivity().runOnUiThread(() -> Toast.makeText(Utilities.getMainActivity(), "Please make your answer more positive to help keep the community safe and happy for everyone.", Toast.LENGTH_LONG).show());
                    } else {
                        NetworkRequest.getInstance().mutationCreateAnswer(questionId, contentText.getText().toString(), new AsyncCallback() {
                            @Override
                            public void onCompleted(Object result) {
                                CreateAnswerMutation.CreateAnswer answer = (CreateAnswerMutation.CreateAnswer) result;

                                Activity activity = Utilities.getMainActivity();
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

    public void updateSentiment() {
        if (!contentText.getText().toString().trim().equals("")) {
            NetworkRequest.getInstance().querySentiment(contentText.getText().toString(), new AsyncCallback() {
                @Override
                public void onCompleted(Object result) {
                    String sentiment = (String) result;
                    if (!Constants.displaySentimentToQuerySentiment.get(sentimentText.getText()).equals(sentiment)) {
                        Utilities.getMainActivity().runOnUiThread(() -> {
                            sentimentText.setText(Constants.querySentimentToDisplaySentiment.get(sentiment));
                        });
                    }

                }

                @Override
                public void onException(Exception e) {

                }
            });
        } else {
            sentimentText.setText("");
        }
    }
}
