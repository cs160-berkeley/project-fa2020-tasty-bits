package com.example.tastybits.ui.questionview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tastybits.R;

public class QuestionAnswerFragment extends Fragment{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_answer, container, false);


        Button postq = view.findViewById(R.id.postquestion);
        postq.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionhub));
        return view;
    }
}
