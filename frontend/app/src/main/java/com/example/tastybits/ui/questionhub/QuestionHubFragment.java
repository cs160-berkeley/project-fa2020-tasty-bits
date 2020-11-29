package com.example.tastybits.ui.questionhub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuestionHubFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questionhub, container, false);



        ImageView classplan = view.findViewById(R.id.classplanning);
        Bundle classBundle = new Bundle();
        classBundle.putString("CategoryName", "classPlanning");
        classplan.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, classBundle));

        ImageView enrollment = view.findViewById(R.id.enrollment);
        Bundle enrollmentBundle = new Bundle();
        enrollmentBundle.putString("CategoryName", "enrollment");
        enrollment.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, enrollmentBundle));

        ImageView financialAid = view.findViewById(R.id.financial);
        Bundle financialBundle = new Bundle();
        financialBundle.putString("CategoryName", "financialAid");
        financialAid.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, financialBundle));

        ImageView housing = view.findViewById(R.id.housing);
        Bundle housingBundle = new Bundle();
        housingBundle.putString("CategoryName", "housing");
        housing.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, housingBundle));

        ImageView clubsAndDecals = view.findViewById(R.id.clubs);
        Bundle clubsBundle = new Bundle();
        clubsBundle.putString("CategoryName", "clubsAndDecals");
        clubsAndDecals.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, clubsBundle));

        ImageView jobs = view.findViewById(R.id.job);
        Bundle jobBundle = new Bundle();
        jobBundle.putString("CategoryName", "housing");
        jobs.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, jobBundle));

        FloatingActionButton addQuestion = view.findViewById(R.id.addQuestionButton);
        addQuestion.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionpost_fragment));

        return view;
    }


}