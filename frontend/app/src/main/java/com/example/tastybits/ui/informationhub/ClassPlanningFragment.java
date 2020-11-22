package com.example.tastybits.ui.informationhub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class ClassPlanningFragment extends Fragment {

    //private InfoHubViewModel infoHubViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //infoHubViewModel =ViewModelProviders.of(this).get(InfoHubViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_infohub_classplanning, container, false);
        final TextView transfer = root.findViewById(R.id.class_planning_transfer_title);
        final TextView goldenBear = root.findViewById(R.id.class_planning_golden_bear_advising_title);

        final DrawerLayout drawer = root.findViewById(R.id.drawerlayout_class_planning);
        final NavigationView navigationView = (NavigationView) root.findViewById(R.id.class_planning_nv);
        final ScrollView sv = root.findViewById(R.id.info_scroll);

        FloatingActionButton expand = root.findViewById(R.id.expand);
        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.END)) {
                    drawer.closeDrawer(GravityCompat.END);
                } else {
                    drawer.openDrawer(GravityCompat.END);
                }
            }
        });
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer(GravityCompat.END);
                System.out.println(item.getItemId());

                switch (item.getItemId()){
                    case R.id.class_planning_transfer:
                        sv.scrollTo((int) transfer.getX(), (int) transfer.getY());
                        break;
                    case R.id.class_planning_golden_bear_advising:
                        sv.scrollTo((int) goldenBear.getX(), (int) goldenBear.getY());
                        break;
                    case R.id.class_planning_major_and_career:
                        break;
                    case R.id.class_planning_program_plan:
                        break;
                    default:

                }
                return true;
            }
        });
//        click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ScrollView sv = root.findViewById(R.id.info_scroll);
//                sv.scrollTo((int) click1.getX(), (int) click1.getY());
//            }
//        });



        /*final TextView textView = root.findViewById(R.id.infohub);
        infoHubViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

//        TextView click = root.findViewById(R.id.class_planning_transfer_title);
//        click.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.hello));




        return root;
    }


}