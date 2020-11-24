package com.example.tastybits.ui.informationhub;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.text.style.RelativeSizeSpan;
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

public class ClubsDecalsFragment extends Fragment {

    String[] international_bullets = new String[]{
            "International student organizations",
            "ISAB",
            "ASA",
            "TSA",
            "BCSSA",
            "BISA",
            "CJC",
            "HKSA",
            "ISA",
            "KUNA",
            "LLS",
            "SMSA",
            "VSA"
    };



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_infohub_clubsdecals, container, false);
        final TextView international = root.findViewById(R.id.clubs_decals_international_title);
        final TextView decals = root.findViewById(R.id.clubs_decals_decals_title);
        final TextView bso = root.findViewById(R.id.clubs_decals_bso_title);
        final DrawerLayout drawer = root.findViewById(R.id.drawer_clubsdecals);
        final NavigationView navigationView = (NavigationView) root.findViewById(R.id.nv_clubsdecals);
        final ScrollView sv = root.findViewById(R.id.scroll_clubsdecals);
        TextView clubs_decals_international_content = root.findViewById(R.id.clubs_decals_international_content);
        SpannableStringBuilder international_ssb = new SpannableStringBuilder();
        for (int i = 0; i < international_bullets.length; i++) {
            String line = international_bullets[i];
            int size = international_bullets[i].length();
            SpannableString international_ss = new SpannableString(line);
            international_ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            international_ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            international_ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            international_ssb.append(international_ss);
            //avoid last "\n"
            if(i+1<international_bullets.length)
                international_ssb.append("\n");

        }
        clubs_decals_international_content.setText(international_ssb);



        FloatingActionButton expand = root.findViewById(R.id.expand_decals);
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

                switch (item.getItemId()){
                    case R.id.clubs_decals_international:
                        sv.scrollTo((int) international.getX(), (int) international.getY());
                        break;
                    case R.id.clubs_decals_decals:
                        sv.scrollTo((int) decals.getX(), (int) decals.getY());
                        break;
//                    case R.id.class_planning_major_and_career:
//                        break;
                    case R.id.clubs_decals_bso:
                        sv.scrollTo((int) bso.getX(), (int) bso.getY());
                        break;
                    default:

                }
                return true;
            }
        });




        return root;
    }

    private float dp(int dp) {
        return getResources().getDisplayMetrics().density * dp;
    }


}