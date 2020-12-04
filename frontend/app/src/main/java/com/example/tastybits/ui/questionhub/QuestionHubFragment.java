package com.example.tastybits.ui.questionhub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tastybits.Constants;
import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuestionHubFragment extends Fragment {


    private void setupCard(CardView v, int drawable, String categoryName, boolean lightBrown) {

        TextView categoryNameText = (TextView) v.findViewById(R.id.questionhubCategoryName);
        ImageView categoryNameImage = (ImageView) v.findViewById(R.id.questionhubCategoryIcon);

        v.setCardBackgroundColor(getActivity().getColor(lightBrown ? R.color.light_brown: R.color.light_brown_shade));
        categoryNameText.setText(Constants.queryCategoryToDisplayNameMap.get(categoryName));
        categoryNameImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), drawable));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_questionhub, container, false);



        CardView firstGen = view.findViewById(R.id.first_gen_questionhub);
        setupCard(firstGen, R.drawable.first_gen_icon, "firstGenResources", true);
        Bundle firstGenBundle = new Bundle();
        firstGenBundle.putString(getString(R.string.category_name_key), "firstGenResources");
        firstGen.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, firstGenBundle));


        CardView transfer = view.findViewById(R.id.transfer_questionhub);
        setupCard(transfer, R.drawable.transfer_icon, "transferResources", false);
        Bundle transferBundle = new Bundle();
        transferBundle.putString(getString(R.string.category_name_key), "transferResources");
        transfer.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, transferBundle));

        CardView international = view.findViewById(R.id.international_questionhub);
        setupCard(international, R.drawable.international_icon, "internationalResources", false);
        Bundle internationalBundle = new Bundle();
        internationalBundle.putString(getString(R.string.category_name_key), "internationalResources");
        international.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, internationalBundle));


        CardView classplan = view.findViewById(R.id.class_planning_questionhub);
        setupCard(classplan, R.drawable.class_planning_icon, "classPlanning", true);
        Bundle classBundle = new Bundle();
        classBundle.putString(getString(R.string.category_name_key), "classPlanning");
        classplan.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, classBundle));

        CardView enrollment = view.findViewById(R.id.enrollment_questionhub);
        setupCard(enrollment, R.drawable.enrollment_icon, "enrollment", true);
        Bundle enrollmentBundle = new Bundle();
        enrollmentBundle.putString(getString(R.string.category_name_key), "enrollment");
        enrollment.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, enrollmentBundle));

        CardView financialAid = view.findViewById(R.id.financial_aid_questionhub);
        setupCard(financialAid, R.drawable.financial_aid_icon, "financialAid", false);
        Bundle financialBundle = new Bundle();
        financialBundle.putString(getString(R.string.category_name_key), "financialAid");
        financialAid.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, financialBundle));


        CardView clubsAndDecals = view.findViewById(R.id.clubs_and_decals_questionhub);
        setupCard(clubsAndDecals, R.drawable.clubs_and_decals_icon, "clubsAndDecals", false);
        Bundle clubsBundle = new Bundle();
        clubsBundle.putString(getString(R.string.category_name_key), "clubsAndDecals");
        clubsAndDecals.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, clubsBundle));


        CardView housing = view.findViewById(R.id.housing_questionhub);
        setupCard(housing, R.drawable.housing_icon, "housing", true);
        Bundle housingBundle = new Bundle();
        housingBundle.putString(getString(R.string.category_name_key), "housing");
        housing.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, housingBundle));


        CardView jobs = view.findViewById(R.id.job_hunting_questionhub);
        setupCard(housing, R.drawable.job_hunting_icon, "jobHunting", true);
        Bundle jobBundle = new Bundle();
        jobBundle.putString(getString(R.string.category_name_key), "jobHunting");
        jobs.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionview_fragment, jobBundle));


        Button addQuestion = view.findViewById(R.id.addQuestionButton);
        addQuestion.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionpost_fragment));

        return view;
    }


}