package com.example.tastybits.ui.informationhub;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.noties.markwon.Markwon;
import kotlin.text.MatchResult;
import kotlin.text.Regex;


public class InfoHubDetailsFragment extends Fragment {

    private String md = "## International Students Strategies\n" +
            "\n" +
            "- Expand Your Network and Make it Work for You\n" +
            "\n" +
            "Networking is one of the most effective ways people find out about positions and get hired.\n" +
            "\n" +
            "- Refine Your Communication and Interpersonal Skills\n" +
            "\n" +
            "Strong English language skills, non-verbal communication skills and interpersonal skills are all crucial for international students who wish to work and succeed in the US, and right now is the best time to polish those skills.\n" +
            "\n" +
            "- Research International Employee Friendly Employers\n" +
            "\n" +
            "Be intentional about your job or internship search by researching which companies have hired international candidates in the past.\n" +
            "\n" +
            "- US Style Application Documents: Resume & Cover Letter\n" +
            "\n" +
            "Make sure you know how to write a US style resume and cover letter and seek feedback from native speakers of English and Career Counselors at the Career Center.\n" +
            "\n" +
            "- Become Confident with Interviewing\n" +
            "\n" +
            "Review the Interviewing for a US employer page to explore Career Center resources on interviewing and specific tips for international students.\n" +
            "\n" +
            "- Review these international student-specific job search resources\n" +
            "\n" +
            "http://www.internationalstudent.com/jobsearch/\\nUNIWORLD: Directories of American Firms Operating in Foreign Countries and Foreign Firms Operating in the U.S.\\nH1 Base: Free job search tips\n" +
            "\n" +
            "- Have a Plan B\n" +
            "\n" +
            "Expand your pool of opportunities by applying for positions in the US and in your home country (or perhaps a third country too). Explore where your education and experiences are in demand and be open to options you previously hadn’t considered!\n" +
            "\n" +
            "- Understand Your Off-Campus Work Authorization and Employment Visa Options\n" +
            "\n" +
            "It is essential for international students to understand when and how they can start working off campus. Students need to be able to articulate their student visa work authorization options and their employment visa options to employers, as not all U.S. employers are experienced in hiring international candidates. If you have detailed questions on student visa work authorization options, please consult with a Berkeley International Office (BIO) advisor during drop-in advising hours.\n" +
            "\n" +
            "- Get Involved\n" +
            "\n" +
            "It will be difficult to get a job or internship through strong grades alone. US employers value students with extracurricular activities, related hands-on experience, and leadership experience.\n" +
            "\n" +
            "- Review the US Jobs and Internships for International Student\n" +
            "\n" +
            "This excerpt from the Career Center Job & Internship Guide (JIG) includes job search strategies, advice about US resumes and interviewing, permits and visas, the long distance job search, and more\n" +
            "\n" +
            "## Handshake\n" +
            "\n" +
            "Handshake is the college career network of the future, built to transform the recruiting experience for college students, career centers and employers.\n" +
            "\n" +
            "## LinkedIn\n" +
            "\n" +
            "LinkedIn is the world's largest professional network on the internet. You can use LinkedIn to find the right job or internship, connect and strengthen professional relationships, and learn the skills you need to succeed in your career.\n" +
            "\n" +
            "## Indeed\n" +
            "\n" +
            "Indeed is the #1 job site in the world1 with over 250 million unique visitors2 every month. Indeed strives to put job seekers first, giving them free access to search for jobs, post resumes, and research companies. Every day, we connect millions of people to new opportunities.\n" +
            "\n" +
            "## Glassdoor\n" +
            "\n" +
            "Glassdoor is a website where current and former employees anonymously review companies. Glassdoor also allows users to anonymously submit and view salaries as well as search and apply for jobs on its platform.\n";

    private List<String> titles;
    private List<String> splits;
    private List<TextView> contentViews;


    private static final String TAG = "InfoHubDetailsFragment";
    private ScrollView contentScrollView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i);
            menu.add(0, i, 0, title);
        }


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TextView target = contentViews.get(item.getItemId());
        contentScrollView.scrollTo((int) target.getX(), (int) target.getY());

        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_infohub_jobhunt, container, false);

        titles = Arrays.asList(ArrayUtils.removeAllOccurrences(StringUtils.substringsBetween(md , "## ", "\n"), ""));
        splits = Arrays.asList(ArrayUtils.removeAllOccurrences(StringUtils.splitByWholeSeparator(md , "## "), ""));
        contentViews = new ArrayList<>();

        contentScrollView = root.findViewById(R.id.contentScrollView);


        LinearLayout contentLinearLayout = root.findViewById(R.id.contentLinearLayout);
        final Markwon markwon = Markwon.create(getActivity());

        for (int i = 0; i < splits.size(); i++) {
            String split = splits.get(i);
            TextView tv = new TextView(getActivity());
            contentLinearLayout.addView(tv);
            markwon.setMarkdown(tv, "## " + split);
            contentViews.add(tv);
        }


        return root;
    }




}
