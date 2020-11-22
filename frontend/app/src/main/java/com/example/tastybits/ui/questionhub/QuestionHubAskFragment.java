package com.example.tastybits.ui.questionhub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tastybits.R;

public class QuestionHubAskFragment extends Fragment{

    //private com.example.tastybits.ui.questionhub.QuestionHubViewModel questionHubViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //questionHubViewModel = ViewModelProviders.of(this).get(com.example.tastybits.ui.questionhub.QuestionHubViewModel.class);
        View view = inflater.inflate(R.layout.fragment_questionhub_ask, container, false);
        /*final TextView textView = root.findViewById(R.id.questionhub);
        questionHubViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        Button postq = view.findViewById(R.id.postquestion);
        postq.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionhub));
        return view;
    }
}
