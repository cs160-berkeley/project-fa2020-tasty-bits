package com.example.tastybits.ui.informationhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.tastybits.R;

public class InfoHubFragment extends Fragment {

    private InfoHubViewModel infoHubViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //infoHubViewModel =ViewModelProviders.of(this).get(InfoHubViewModel.class);
        View root = inflater.inflate(R.layout.fragment_infohub, container, false);
        /*final TextView textView = root.findViewById(R.id.infohub);
        infoHubViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        ImageView classPlanning = root.findViewById(R.id.class_planning_infohub);
        ImageView enrollement = root.findViewById(R.id.enrollment_infohub);
        ImageView fafsa = root.findViewById(R.id.fafsa_infohub);
        ImageView clubs = root.findViewById(R.id.decal_infohub);
        ImageView jobHunting = root.findViewById(R.id.job_hunting_infohub);
        ImageView housing = root.findViewById(R.id.housing_infohub);
        ImageView firstGen = root.findViewById(R.id.firtsGen_infohub);

       // classPlanning.setOnClickListener(handleClick);
        classPlanning.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.info_class_planning));
        enrollement.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.info_enrollment));
        jobHunting.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.info_jobhunt));
        clubs.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.info_clubs));


        return root;
    }

//    private View.OnClickListener handleClick = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            switch(v.getId()) {
//                case R.id.class_planning_infohub:
//                    //System.out.println("class Planning");
//                    createNavigateOnClickListener
//                    break;
//                default:
//                    throw new IllegalStateException("Unexpected value: " + v.getId());
//            }
//        }
//    };
}