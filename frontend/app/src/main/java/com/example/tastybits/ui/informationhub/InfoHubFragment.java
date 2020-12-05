package com.example.tastybits.ui.informationhub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tastybits.R;
import com.google.android.material.navigation.NavigationView;

public class InfoHubFragment extends Fragment {

    private InfoHubViewModel infoHubViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_infohub, container, false);


        ImageView classPlanning = root.findViewById(R.id.class_planning_infohub);
        ImageView enrollement = root.findViewById(R.id.enrollment_infohub);
        ImageView fafsa = root.findViewById(R.id.fafsa_infohub);
        ImageView clubs = root.findViewById(R.id.decal_infohub);
        ImageView jobHunting = root.findViewById(R.id.job_hunting_infohub);
        ImageView housing = root.findViewById(R.id.housing_infohub);
        ImageView firstGen = root.findViewById(R.id.first_gen_infohub);
        ImageView transfer = root.findViewById(R.id.transfer_infohub);
        ImageView international = root.findViewById(R.id.international_infohub);


        Bundle jobHuntBundle = new Bundle();
        jobHuntBundle.putString(getString(R.string.infohub_category_name_key), "jobHunting");
        jobHunting.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, jobHuntBundle));

        Bundle firstGenBundle = new Bundle();
        firstGenBundle.putString(getString(R.string.infohub_category_name_key), "firstGenResources");
        firstGen.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, firstGenBundle));

        Bundle transferBundle = new Bundle();
        transferBundle.putString(getString(R.string.infohub_category_name_key), "transferResources");
        transfer.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, transferBundle));

        Bundle classPlanningBundle = new Bundle();
        classPlanningBundle.putString(getString(R.string.infohub_category_name_key), "classPlanning");
        classPlanning.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.infohub_details, classPlanningBundle));

        enrollement.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.info_enrollment));
        housing.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.info_housing));
        clubs.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.info_clubs));
        international.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.info_international));


        return root;
    }

}
