package com.example.tastybits.ui.informationhub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tastybits.Constants;
import com.example.tastybits.R;
import com.google.android.material.navigation.NavigationView;

public class InfoHubFragment extends Fragment {


    private void setupCard(CardView v, String categoryName, boolean lightBrown) {

        TextView categoryNameText = (TextView) v.findViewById(R.id.rectangleCategoryCardName);
        ImageView categoryNameImage = (ImageView) v.findViewById(R.id.rectangleCategoryCardIcon);


        v.setCardBackgroundColor(getActivity().getColor(lightBrown ? R.color.light_brown: R.color.light_brown_shade));
        categoryNameText.setText(Constants.queryCategoryToDisplayNameMap.get(categoryName));
        categoryNameImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), Constants.queryCategoryToIconInteger.get(categoryName)));
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infohub, container, false);



        CardView firstGen = view.findViewById(R.id.first_gen_informationhub);
        setupCard(firstGen, "firstGenResources", true);
        Bundle firstGenBundle = new Bundle();
        firstGenBundle.putString(getString(R.string.category_name_key), "firstGenResources");
        firstGen.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, firstGenBundle));


        CardView transfer = view.findViewById(R.id.transfer_informationhub);
        setupCard(transfer, "transferResources", false);
        Bundle transferBundle = new Bundle();
        transferBundle.putString(getString(R.string.category_name_key), "transferResources");
        transfer.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, transferBundle));

        CardView international = view.findViewById(R.id.international_informationhub);
        setupCard(international, "internationalResources", true);
        Bundle internationalBundle = new Bundle();
        internationalBundle.putString(getString(R.string.category_name_key), "internationalResources");
//        international.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, internationalBundle));


        CardView classplan = view.findViewById(R.id.class_planning_informationhub);
        setupCard(classplan, "classPlanning", false);
        Bundle classBundle = new Bundle();
        classBundle.putString(getString(R.string.category_name_key), "classPlanning");
        classplan.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, classBundle));

        CardView enrollment = view.findViewById(R.id.enrollment_informationhub);
        setupCard(enrollment, "enrollment", true);
        Bundle enrollmentBundle = new Bundle();
        enrollmentBundle.putString(getString(R.string.category_name_key), "enrollment");
//        enrollment.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, enrollmentBundle));

        CardView financialAid = view.findViewById(R.id.financial_aid_informationhub);
        setupCard(financialAid, "financialAid", false);
        Bundle financialBundle = new Bundle();
        financialBundle.putString(getString(R.string.category_name_key), "financialAid");
//        financialAid.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, financialBundle));


        CardView clubsAndDecals = view.findViewById(R.id.clubs_and_decals_informationhub);
        setupCard(clubsAndDecals, "clubsAndDecals", true);
        Bundle clubsBundle = new Bundle();
        clubsBundle.putString(getString(R.string.category_name_key), "clubsAndDecals");
//        clubsAndDecals.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, clubsBundle));


        CardView housing = view.findViewById(R.id.housing_informationhub);
        setupCard(housing, "housing", false);
        Bundle housingBundle = new Bundle();
        housingBundle.putString(getString(R.string.category_name_key), "housing");
//        housing.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, housingBundle));


        CardView jobs = view.findViewById(R.id.job_hunting_informationhub);
        setupCard(housing, "jobHunting", true);
        Bundle jobBundle = new Bundle();
        jobBundle.putString(getString(R.string.category_name_key), "jobHunting");
        jobs.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, jobBundle));

        return view;
    }

}
