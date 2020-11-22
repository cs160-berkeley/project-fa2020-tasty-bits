package com.example.tastybits.ui.informationhub;

import android.os.Build;
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
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class JobHuntFragment extends Fragment {
    String[] titles = new String[]{
            "Expand Your Network and Make it Work for You",
            "Refine Your Communication and Interpersonal Skills",
            "Research International Employee Friendly Employers",
            "US Style Application Documents: Resume & Cover Letter",
            "Become Confident with Interviewing",
            "Review these international student-specific job search resources",
            "Have a Plan B",
            "Understand Your Off-Campus Work Authorization and Employment Visa Options",
            "Get Involved",
            "Review the US Jobs and Internships for International Students"
    };
    String[] contents = new String[] {
            "Networking is one of the most effective ways people find out about positions and get hired.",
            "Strong English language skills, non-verbal communication skills and interpersonal skills are all crucial for international students who wish to work and succeed in the US, and right now is the best time to polish those skills.",
            "Be intentional about your job or internship search by researching which companies have hired international candidates in the past.",
            "Make sure you know how to write a US style resume and cover letter and seek feedback from native speakers of English and Career Counselors at the Career Center.",
            "Review the Interviewing for a US employer page to explore Career Center resources on interviewing and specific tips for international students.",
            "http://www.internationalstudent.com/jobsearch/\nUNIWORLD: Directories of American Firms Operating in Foreign Countries and Foreign Firms Operating in the U.S.\nH1 Base: Free job search tips",
            "Expand your pool of opportunities by applying for positions in the US and in your home country (or perhaps a third country too). Explore where your education and experiences are in demand and be open to options you previously hadn’t considered!",
            "Tt is essential for international students to understand when and how they can start working off campus. Students need to be able to articulate their student visa work authorization options and their employment visa options to employers, as not all U.S. employers are experienced in hiring international candidates. If you have detailed questions on student visa work authorization options, please consult with a Berkeley International Office (BIO) advisor during drop-in advising hours.",
            "It will be difficult to get a job or internship through strong grades alone. US employers value students with extracurricular activities, related hands-on experience, and leadership experience.",
            "This excerpt from the Career Center Job & Internship Guide (JIG) includes job search strategies, advice about US resumes and interviewing, permits and visas, the long distance job search, and more."
    };
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_infohub_jobhunt, container, false);

        TextView content1 = root.findViewById(R.id.content1);


        SpannableStringBuilder ssb = new SpannableStringBuilder();
        for (int i = 0; i < titles.length; i++) {
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

        }

        content1.setText(ssb);

        final DrawerLayout drawer = root.findViewById(R.id.drawer_jobhunt);
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


        NavigationView nv = root.findViewById(R.id.nv_jobhunt);
        nv.bringToFront();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                ScrollView sv = root.findViewById(R.id.scroll_jobhunt);
                TextView target;

                switch(id)
                {
                    case R.id.jobhunt_international:
                        target = root.findViewById(R.id.title1);
                        break;
                    case R.id.jobhunt_handshake:
                        target = root.findViewById(R.id.title2);
                        break;
                    case R.id.jobhunt_linkedin:
                        target = root.findViewById(R.id.title3);
                        break;
                    case R.id.jobhunt_indeed:
                        target = root.findViewById(R.id.title4);
                        break;
                    case R.id.jobhunt_glassdoor:
                        target = root.findViewById(R.id.title5);
                        break;
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
