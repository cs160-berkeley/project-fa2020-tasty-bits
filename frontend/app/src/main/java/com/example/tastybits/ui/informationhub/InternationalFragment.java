package com.example.tastybits.ui.informationhub;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BulletSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class InternationalFragment extends Fragment {
    String[] bio = new String[]{
        "Berkeley International Office",
        "BIO Social and Cultural Events",
        "F-1 international students",
        "   On-Campus Work Authorization",
        "   Curricular Practical Training (CPT)",
        "   Optional Practical Training (OPT)",
        "   STEM OPT Extension",
        "   Cap-Gap Extension",
        "J-1 international students",
        "   On-Campus Work Authorization",
        "   Academic Training (AT)",
        "F-1 and J-1 work authorization workshops and webinars",
        "H-1B Employment Visa Workshops",
        "Social Security Number",
        "Taxes in the US"
    };
    String[] contents = new String[] {

        };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_infohub_international, container, false);

        TextView content1 = root.findViewById(R.id.content11);

        TextView content12 = root.findViewById(R.id.content12);



        SpannableStringBuilder ssb = new SpannableStringBuilder();
        for (int i = 0; i < bio.length; i++) {
            String line = bio[i] + "\n" + "\n";
            int size = bio[i].length();
            SpannableString ss = new SpannableString(line);
            ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(ss);


            //avoid last "\n"
            if(i+1<bio.length)
                ssb.append("\n");

        }



        content1.setText(ssb);

        final DrawerLayout drawer = root.findViewById(R.id.drawer_international);
        FloatingActionButton expand = root.findViewById(R.id.expand_international);
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


        NavigationView nv = root.findViewById(R.id.nv_international);
        nv.bringToFront();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                ScrollView sv = root.findViewById(R.id.scroll_international);
                TextView target;

                switch(id)
                {
                    case R.id.international_bio:
                        target = root.findViewById(R.id.title1);
                        break;
                    case R.id.international_English:
                        target = root.findViewById(R.id.title2);
                        break;
//                    case R.id.jobhunt_linkedin:
//                        target = root.findViewById(R.id.title3);
//                        break;
//                    case R.id.jobhunt_indeed:
//                        target = root.findViewById(R.id.title4);
//                        break;
//                    case R.id.jobhunt_glassdoor:
//                        target = root.findViewById(R.id.title5);
//                        break;
                    default:
                        target = root.findViewById(R.id.title1);
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
