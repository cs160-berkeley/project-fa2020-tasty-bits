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

public class ClubsAndDecalsFragment extends Fragment {

    String[] titles = new String[]{
            "About The DeCal Program",
            "International",
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
            "The DeCal Program (or just DeCal) is an aggregate of student-run courses at the University of California, Berkeley – here, students create and facilitate their own classes on a variety of subjects, many of which are not addressed in the traditional curriculum.\n" +
                    "Each semester there are over 150 courses on topics ranging from Taiwanese Language to Simpsons and Philosophy. Around 3000-4000 UC Berkeley students take DeCals each semester. DeCals are an excellent way of meeting the University’s minimum unit requirement, developing a new or past interest, and meeting peers in a small, comfortable environment. The DeCal Program is undoubtedly one of the many unique aspects of Berkeley’s undergraduate program, and all students are encouraged to consider taking a course!\n" +
                    "DeCal classes, in a nutshell, are legitimate university classes run by students. The responsibility of such courses rests on the department chair, faculty member, and student facilitator, who all sign a contract of understanding before the DeCal is reviewed by COCI / the Academic Senate. A faculty member sponsors a student’s course as a 98 / 198 section. That faculty member, as far as the computer systems and transcripts are concerned, is the “instructor of record.” The student that runs the course can be viewed as the delegated instructor for the faculty sponsor. At the end of the semester, the student will provide grade “recommendations” for the faculty sponsor, who will then do the actual grade inputting. Grades are only offered as Pass / No Pass, and the academic credit for each class typically range from 0.5-2 units.\n" +
                    "Students facilitators are the go-to person if there is any question about the course (enrollment, auditing…etc). Specific contact information can be found in the course listings page. If the facilitator has chosen not to show their e-mail address on the course listing, then we cannot provide it to you (it would be against our privacy policy) but we would be happy to send them an email with your contact info. If there are any questions or concerns about FPF enrollment in DeCal course units, please contact your adviser.\n",
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
        FloatingActionButton expand = root.findViewById(R.id.expand_jobhunt);
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