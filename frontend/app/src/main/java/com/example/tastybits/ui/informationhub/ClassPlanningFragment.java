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

public class ClassPlanningFragment extends Fragment {

    String[] transfer_titles = new String[]{
            "Degree Requirements",
            "Initial Transfer Credit Review",
            "General Education Requirements",
            "Next Step",

    };
    String[] transfer_contents = new String[] {
            "Review the Degree Requirements page to get a sense of all of the general education and unit requirements you will need.",
            "If you do not have seven-course breadth satisfied, that can be a good place to start to round out your first semester schedule. Since your Initial Transfer Credit Review will not yet be completed, see if there are any breadth requirements you are confident you have not yet satisfied with previous coursework (or use assist.org if you took California Community College coursework but did not satisfy IGETC).",
            "If you satisfied IGETC or UC Reciprocity, you may still have the following general education requirements remaining, which could be planned into your first semester schedule: \n\n" +
                    "        American History\n" +
                    "        American Institutions\n" +
                    "        American Cultures\n" ,
            "If you have completed all general education requirements, you can work on elective units. You may wish to start making progress toward the degree requirement of needing 6 upper division units outside of your major."
    };

    String[] gba_titles = new String[]{
            "Golden Bear Advising",
            "Incoming Freshman Advising",
            "Incoming Transfer Advising",
            "All Incoming Students"
    };
    String[] gba_contents = new String[] {
            "Golden Bear Advising(link is external) is mandatory, self-paced, online course that introduces you to academic resources and your college. Through Golden Bear Advising, you will learn how to build your first semester schedule and prepare to enroll in classes. \n" +
                    "The course includes:\n" +
                    "       Communication with an Academic Adviser\n" +
                    "       Overview of academic resources\n" +
                    "       Graduation requirements\n",
            "Once GBA begins for freshmen, an L&S College Adviser will reach out to you in your GBA bCourse with a welcome announcement or message. Check your inbox and your announcements to learn which L&S College Adviser will be working with you during GBA.",
            "Once GBA begins for transfers, you will have an opportunity to connect with your intended major's Undergraduate Major Adviser. This adviser will help you assess which courses you should take in Fall based on your progress toward the major and make sure you understand how to declare your major when you are ready.",
            "The academic adviser you work with during GBA is different than your Admissions Officer, who you can find through your MAP@berkeley(link is external) Admissions portal. Your L&S College or Undergraduate Major Adviser will focus on your academic questions related to starting at Cal. Your Admissions Officer will answer questions about your new student checklist or questions related to your admission."
    };

    String[] program_plan_titles = new String[]{
            "Create a Long-Term Program Plan",
            "Majors & Minors",
            "Unit Ceiling and Semester Limit",
    };
    String[] program_plan_contents = new String[] {
            "The Create a Long-Term Program Plan page offers an overview of whether you need to do this work now and provides step-by-step guidance. You can also find ideas for exploring outside-of-the-classroom opportunities including study abroad, internships, and research under \"Helpful Links for Program Planning\" on this page.",
            "The Majors & Minors section includes several pages that will help you learn more about declaring or changing majors, adding double majors and simultaneous degrees, minors, and more. ",
            "The Unit Ceiling and Semester Limit will help you understand the time allowed for degree completion. "
    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_infohub_classplanning, container, false);
        final TextView transfer = root.findViewById(R.id.class_planning_transfer_title);
        final TextView goldenBear = root.findViewById(R.id.class_planning_golden_bear_advising_title);
        final TextView programPlan = root.findViewById(R.id.class_planning_program_plan_title);

        final DrawerLayout drawer = root.findViewById(R.id.drawerlayout_class_planning);
        final NavigationView navigationView = (NavigationView) root.findViewById(R.id.class_planning_nv);
        final ScrollView sv = root.findViewById(R.id.info_scroll);
        TextView class_planning_transfer_content = root.findViewById(R.id.class_planning_transfer_content);
        SpannableStringBuilder transfer_ssb = new SpannableStringBuilder();
        for (int i = 0; i < transfer_titles.length; i++) {
            String line = transfer_titles[i] + "\n" + transfer_contents[i] + "\n";
            int size = transfer_titles[i].length();
            SpannableString transfer_ss = new SpannableString(line);
            transfer_ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            transfer_ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            transfer_ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            transfer_ssb.append(transfer_ss);


            //avoid last "\n"
            if(i+1<transfer_titles.length)
                transfer_ssb.append("\n");

        }
        class_planning_transfer_content.setText(transfer_ssb);

        TextView class_planning_gba_content = root.findViewById(R.id.class_planning_golden_bear_advising_content);
        SpannableStringBuilder gba_ssb = new SpannableStringBuilder();
        for (int i = 0; i < gba_titles.length; i++) {
            String line = gba_titles[i] + "\n" + gba_contents[i] + "\n";
            int size = gba_titles[i].length();
            SpannableString gba_ss = new SpannableString(line);
            gba_ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            gba_ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            gba_ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            gba_ssb.append(gba_ss);


            //avoid last "\n"
            if(i+1<gba_titles.length)
                gba_ssb.append("\n");

        }
        class_planning_gba_content.setText(gba_ssb);

        TextView class_planning_program_plan_content = root.findViewById(R.id.class_planning_program_plan_content);
        SpannableStringBuilder pp_ssb = new SpannableStringBuilder();
        for (int i = 0; i < program_plan_titles.length; i++) {
            String line = program_plan_titles[i] + "\n" + program_plan_contents[i] + "\n";
            int size = program_plan_titles[i].length();
            SpannableString pp_ss = new SpannableString(line);
            pp_ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            pp_ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            pp_ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            pp_ssb.append(pp_ss);


            //avoid last "\n"
            if(i+1<program_plan_titles.length)
                pp_ssb.append("\n");

        }
        class_planning_program_plan_content.setText(pp_ssb);

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
//                    case R.id.class_planning_major_and_career:
//                        break;
                    case R.id.class_planning_program_plan:
                        sv.scrollTo((int) programPlan.getX(), (int) programPlan.getY());
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