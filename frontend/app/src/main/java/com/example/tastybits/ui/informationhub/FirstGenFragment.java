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

public class FirstGenFragment extends Fragment {
    String[] bullet1 = new String[] {
            "Berkeley Law Opportunity Scholarship",
            "Fiat Lux Scholarship",
            "Berkeley Community Scholarship",
    };
    String[] sub1 = new String[] {
            "The Berkeley Law Opportunity Scholarship (BLOS) is a 3 year, full tuition scholarship for first generation college graduates. The Berkeley Law Opportunity Scholarship provides recipients with funding to cover tuition and fees (including Student Health Insurance, materials fees, etc.) for three years (six semesters) of study at Berkeley Law.",
            "The Fiat Lux Scholarship creates opportunities for resilient students with unique life experiences to make a profound impact at the University of California, Berkeley. Established for high-achieving students from partner high schools in California, the scholarship provides monetary, community, and academic support to underrepresented and first-generation college students.",
            "Since 2008, Berkeley Community Scholarship has awarded 303 scholarships totaling more than $4 million to Berkeley High School students attending colleges and universities throughout California and across the United States. Students work with our college success advisors and a community mentor to help them navigate the demands and challenges of college.\\nBerkeley Community scholars represent the many and varied ethnic and racial groups in Berkeley. Over 80% are the first generation in their family to attend college. Many have/are overcoming obstacles, barriers, and inequities.\\nDespite these challenges, they excel in school and are often leaders in the community. Berkeley Community scholars show great promise and have an overwhelming sense of hope for the future. They inspire all of us to reach beyond our circumstances no matter how big or small striving to make a difference."
    };
    String[] bullet2 = new String[] {
            "Academic Centers",
            "Berkeley Connect",
            "The College of Engineering’s First-generation Mentor Program",
            "Biology Scholars, Chemistry Scholars, and Data Scholars",
            "Cal NERDS (New Experiences for Research and Diversity in Science)",
            "EOP (Educational Opportunity Program)",
            "Student Learning Center",
            "Undocumented Student Program"
    };
    String[] sub2 = new String[]{
            "Whether you would like extra help with coursework, need to talk to someone about choosing a major, or just want a place to study with your friends, the Academic Centers located in the residence halls are the place to go.",
            "Matches you with a graduate student mentor and connects you with professors, alumni, and students sharing similar interests.",
            "The College of Engineering’s First-generation Mentor Program aims to mitigate the challenges often faced by first gen students in this a year-long, two tiered program where first-generation upper-division engineering students mentor first-generation lower-division engineering students and alumni, faculty, and graduate students mentor first-generation upper-division engineering students.",
            "Advising, mentorship, academic support, and more for underrepresented students in specific fields.",
            "Works with nontraditional STEM students to provide opportunities in leadership development, undergraduate research, tech training, preparation for grad school, career planning, and more.",
            "Academic counseling, mentoring, and a support system for first-generation, low-income, and underrepresented students.",
            "Academic consultations, tutoring, workshops, study groups, and more. Available to all students.",
            "Guidance and support to undocumented undergraduates at Cal."
    };
    String[] bullet3 = new String[] {
            "Dean of Students Office",
            "Bears For Financial Success",
            "Financial Literacy and Economic Justice Conference",
            "LEAD Center",
            "Residential Theme Programs",
            "Student Clubs & Organizations"
    };

    String[] sub3 = new String[]{
            "A hub office that advocates for the needs of students. They help community members navigate UC Berkeley, promote diversity, and prepare students to contribute to a changing world.The office also aims to support student health and well-being by facilitating  belonging, community, and leadership development. Above all, they foster compassion and care.",
            "For most college students, money can be an overwhelming and stressful topic. This peer-to-peer financial wellness program offers workshops and one-on-one appointments to make sure students understand the basics of financial literacy and have the knowledge and tools to manage their personal finances in college and beyond.",
            "The Financial Literacy and Economic Justice Conference is an annual, student-organized event drawing together faculty, administrative offices, community partners and student leaders in facilitating workshops on financial literacy and economic justice to the greater student body. Workshop topics include filing taxes, planning personal budget, saving for retirement, and more.",
            "The LEAD (Leadership, Engagement, Advising, & Development) Center is UC Berkeley’s hub for student involvement, leadership development, and co-curricular advising.",
            "Close-knit living and learning communities of students sharing a common thematic and academic interest.",
            "Join one of 1,200 student clubs and organizations on campus. From the B-Side music magazine to the Cal Quidditch Team, from the Latinx Pre-Law Society to Virtual Reality at Berkeley, Berkeley has it all."
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_infohub_firstgen, container, false);

        TextView content = root.findViewById(R.id.content1);
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        for (int i = 0; i < bullet1.length; i++) {
            String line = bullet1[i] + "\n" + sub1[i] + "\n";
            int size = bullet1[i].length();
            SpannableString ss = new SpannableString(line);
            ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(ss);


            //avoid last "\n"
            if(i+1<bullet1.length)
                ssb.append("\n");

        }
        content.setText(ssb);



        content = root.findViewById(R.id.content2);
        ssb = new SpannableStringBuilder();
        for (int i = 0; i < bullet2.length; i++) {
            String line = bullet2[i] + "\n" + sub2[i] + "\n";
            int size = bullet2[i].length();
            SpannableString ss = new SpannableString(line);
            ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(ss);


            //avoid last "\n"
            if(i+1<bullet2.length)
                ssb.append("\n");

        }
        content.setText(ssb);

        content = root.findViewById(R.id.content3);
        ssb = new SpannableStringBuilder();
        for (int i = 0; i < bullet3.length; i++) {
            String line = bullet3[i] + "\n" + sub3[i] + "\n";
            int size = bullet3[i].length();
            SpannableString ss = new SpannableString(line);
            ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(ss);


            //avoid last "\n"
            if(i+1<bullet3.length)
                ssb.append("\n");

        }
        content.setText(ssb);



        final DrawerLayout drawer = root.findViewById(R.id.drawer_firstgen);
        FloatingActionButton expand = root.findViewById(R.id.expand_firstgen);
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

        NavigationView nv = root.findViewById(R.id.nv_firstgen);
        nv.bringToFront();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                ScrollView sv = root.findViewById(R.id.scroll_firstgen);
                TextView target;

                switch(id)
                {
                    case R.id.firstgen_scholarship:
                        target = root.findViewById(R.id.title1);
                        break;
                    case R.id.firstgen_academic_service:
                        target = root.findViewById(R.id.title2);
                        break;
                    case R.id.firstgen_community_service:
                        target = root.findViewById(R.id.title3);
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
