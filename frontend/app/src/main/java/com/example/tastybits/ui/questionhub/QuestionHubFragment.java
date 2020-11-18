package com.example.tastybits.ui.questionhub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tastybits.R;

public class QuestionHubFragment extends Fragment {

    private com.example.tastybits.ui.questionhub.QuestionHubViewModel questionHubViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        questionHubViewModel =
                ViewModelProviders.of(this).get(com.example.tastybits.ui.questionhub.QuestionHubViewModel.class);
        View root = inflater.inflate(R.layout.fragment_questionhub, container, false);
//        final TextView textView = root.findViewById(R.id.questionhub);
//        questionHubViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}