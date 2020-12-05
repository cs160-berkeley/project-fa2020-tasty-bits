package com.example.tastybits.ui.informationhub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

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

import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class TransferFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_infohub_transfer, container, false);

        TextView content1 = root.findViewById(R.id.t_content1);
        TextView content2 = root.findViewById(R.id.t_content2);


        SpannableStringBuilder ssb = new SpannableStringBuilder();
        /*for (int i = 0; i < titles.length; i++) {
            String line = titles[i] + "\n" + contents[i] + "\n";
            int size = titles[i].length();
            SpannableString ss = new SpannableString(line);
            ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(ss);


            //avoid last "\n"
            if(i+1<titles.length)
                ssb.append("\n");

        }*/

        String line1 =  "The Transfer Student Center provides services to assist students who transfer to Cal from other colleges and universities with navigating the academic and cultural landscape of this research university. Our programs and services focus on supporting a successful transition, helping transfers build connections and community, and assisting students as they explore and pursue their academic and career goals. The center plays a key role in campus outreach and recruitment through yield events and participation in programs involving California Community Colleges. We are located in 100 Cesar Chavez Student Building.\n" +
                "\n" +
                "https://transfers.berkeley.edu/\n";
        SpannableString ss1 = new SpannableString(line1);
        ss1.setSpan(new BulletSpan((int) dp(10)), 0, line1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss1.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss1.setSpan(new RelativeSizeSpan(1.2f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.append(ss1);

        content1.setText(ssb);

        String line2 = "The Center for Access to Engineering Excellence (CAEE) is committed to providing a supportive and welcoming environment conducive to academic and personal success. They pride themselves as champions for diversity, equity and inclusion in all of their programs and services.\n\n\n" +
                "For more information visit -->https://engineering.berkeley.edu/students/academic-support/\n";

        SpannableStringBuilder ssb1 = new SpannableStringBuilder();
        SpannableString ss2 = new SpannableString(line2);
        ss2.setSpan(new BulletSpan((int) dp(10)), 0, line2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss2.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss2.setSpan(new RelativeSizeSpan(1.2f), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb1.append(ss2);

        content2.setText(ssb1);



        final DrawerLayout drawer = root.findViewById(R.id.drawer_transfer);
        FloatingActionButton expand = root.findViewById(R.id.expand_transfer);
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


        NavigationView nv = root.findViewById(R.id.nv_transfer);
        nv.bringToFront();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                ScrollView sv = root.findViewById(R.id.scroll_transfer);
                TextView target;

                switch(id)
                {
                    case R.id.transfer_center:
                        target = root.findViewById(R.id.t_title1);
                        break;
                    case R.id.access_to_excellence:
                        target = root.findViewById(R.id.t_title2);
                        break;
                    case R.id.EOP:
                        target = root.findViewById(R.id.t_title3);
                        break;
                    case R.id.transfer_scholarship:
                        target = root.findViewById(R.id.t_title4);
                        break;
                    case R.id.transfer_internship:
                        target = root.findViewById(R.id.t_title5);
                        break;
                    default:
                        target = root.findViewById(R.id.t_title1);
                        break;
                }
                sv.scrollTo((int) target.getX(), (int) target.getY());
                drawer.closeDrawer(GravityCompat.END);
                return true;
            }
        });





        return root;
    }

    private float dp(int dp) {
        return getResources().getDisplayMetrics().density * dp;
    }
}