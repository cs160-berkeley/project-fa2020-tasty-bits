package com.example.tastybits.ui.informationhub;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class EnrollmentFragment extends Fragment {

    //private InfoHubViewModel infoHubViewModel;
    NavigationView nv;
    View root;
    ScrollView sv;
    TextView cal_central;
    TextView terms;
    TextView resources;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_infohub_enrollment, container, false);
        final DrawerLayout drawer = root.findViewById(R.id.drawerlayout_enrollment);



        FloatingActionButton expand = root.findViewById(R.id.expand_enrollment);
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
        sv =  root.findViewById(R.id.info_scroll);
        cal_central = root.findViewById(R.id.cal_central_info);
        terms = root.findViewById(R.id.terms_info);


        nv = root.findViewById(R.id.nv_enrollment);
        nv.bringToFront();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.Cal_central:
                        sv.scrollTo((int) cal_central.getX(), (int) cal_central.getY());
                        break;
                    case R.id.terms:
                        sv.scrollTo((int) terms.getX(), (int) terms.getY());

                        break;
                    case R.id.resources:

                        break;
                    default:

                }
                drawer.closeDrawer(GravityCompat.END);
                return true;

            }
        });




        return root;
    }




}