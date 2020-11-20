package com.example.tastybits.ui.informationhub;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ClassPlanning extends Fragment {

    //private InfoHubViewModel infoHubViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //infoHubViewModel =ViewModelProviders.of(this).get(InfoHubViewModel.class);
        final View root = inflater.inflate(R.layout.activity_class_planning, container, false);
        //final TextView click = root.findViewById(R.id.id1);
        //final TextView click1 = root.findViewById(R.id.hello);

        final DrawerLayout drawer = root.findViewById(R.id.drawerlayout_class_planning);



        FloatingActionButton expand = root.findViewById(R.id.expand);
        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        /*click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScrollView sv = root.findViewById(R.id.info_scroll);
                sv.scrollTo((int) click1.getX(), (int) click1.getY());
                System.out.println("i clicked");
            }
        });*/

        /*final TextView textView = root.findViewById(R.id.infohub);
        infoHubViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

//        TextView click = root.findViewById(R.id.id1);
//        click.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.hello));




        return root;
    }


}