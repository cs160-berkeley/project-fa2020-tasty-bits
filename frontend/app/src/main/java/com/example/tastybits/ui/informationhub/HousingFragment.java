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

public class HousingFragment extends Fragment {
    String[] resources = new String[]{
            "Cal housing office",
            "Residential life",
            "Cal fb housing group",
            "Cal rentals",
            "Berkeley student cooperative",
            "Cragilist",
            "GA’s housing guide",
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_infohub_housing, container, false);

        TextView content1 = root.findViewById(R.id.content1);
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        for (int i = 0; i < resources.length; i++) {
            String line = resources[i];
            SpannableString ss = new SpannableString(line);
            ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(ss);


            //avoid last "\n"
            if(i+1<resources.length)
                ssb.append("\n");

        }
        content1.setText(ssb);



        content1 = root.findViewById(R.id.content2);
        ssb = new SpannableStringBuilder();
        String line = "In 2016, the average rent for apartments is estimated by RentCafe at:\n\n   - Studio apartments: $2,004 per month\n   - 1-bedroom apartments: $2,796 a month\n   - 2-bedroom apartment: $3,526 per month\n\nPlease be advised that Cal Rentals does receive advertisements for rentals with lower rents than shown above, but house-hunters should not expect to encounter great numbers of drastically lower rents.\n\nAlthough there has been new construction in Berkeley over the past few years to match the demand for housing, rents for rooms and apartments in these new buildings tend to be significantly above average.\n\nYou can expect considerable competition for any well-priced units that are near to campus.";
        SpannableString ss = new SpannableString(line);
        ssb.append(ss);
        content1.setText(ssb);


        content1 = root.findViewById(R.id.content3);
        ssb = new SpannableStringBuilder();

        line = "UCPD\n\n";
        int size = line.length();
        line += "(310) 825-1491\n\n";
        ss = new SpannableString(line);
        ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.append(ss);

        line = "“Blue Light Telephones”\n\n";
        size = line.length();
        line += "Emergency “Blue Light” Telephones\n" +
                "\n" +
                "   Emergency “Blue Light” telephones allow you to connect with UC Police Department call center without much effort.  When you see a “Blue Light” telephone, you know you are only one button away from UCPD.\n" +
                "\n" +
                "What does Emergency “Blue Light” Telephones look like?\n" +
                "\n" +
                "   Emergency telephones can be identified by a blue light. Freestanding phone systems, in tall dark columns or yellow phone boxes, are topped with a blue light which remains lit at all times.\n" +
                "\n" +
                "How do they work?\n" +
                "\n" +
                "   Pushing the red button on the panel will automatically connect to the UC Police Department call center. The dispatcher will see the location of the caller.\n" +
                "\n" +
                "Where are they?\n" +
                "\n" +
                "   Emergency phones are installed throughout the campus.\n\n";
        ss = new SpannableString(line);
        ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.append(ss);

        line = "Tips\n\n";
        size = line.length();
        line += "Don't walk alone at night! Use these free UCB Police services:\n\n" +
                "   - BearWalk (campus safety escort)\n" +
                "   - Night Safety Shuttle (campus bus service)\n" +
                "   - Door-to-Door Service (a ride to locations near campus)\n\n" +
                "Save emergency numbers in your phone:\n\n" +
                "   - Off-campus: call 911\n" +
                "   - UCB Police: 510-642-3333  (faster than 911 while on-campus)\n\n" +
                "When meeting strangers in person:\n\n" +
                "   - Insist on a public meeting place like a cafe.\n" +
                "   - Don't invite strangers into your home.\n" +
                "   - Consider having a friend accompany you.\n" +
                "   - Don't meet in a secluded place.\n" +
                "   - Be especially careful when buying/selling high value items.\n" +
                "   - Tell a friend or family member where you're going.\n" +
                "   - Take your cell phone along if you have one.\n" +
                "   - Trust your instincts.";

        ss = new SpannableString(line);
        ss.setSpan(new BulletSpan((int) dp(10)), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new RelativeSizeSpan(1.2f), 0, size, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.append(ss);


        content1.setText(ssb);



        final DrawerLayout drawer = root.findViewById(R.id.drawer_housing);
        FloatingActionButton expand = root.findViewById(R.id.expand_housing);
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


        NavigationView nv = root.findViewById(R.id.nv_housing);
        nv.bringToFront();
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                ScrollView sv = root.findViewById(R.id.scroll_housing);
                TextView target;

                switch(id)
                {
                    case R.id.housing_resource:
                        target = root.findViewById(R.id.title1);
                        break;
                    case R.id.housing_price:
                        target = root.findViewById(R.id.title2);
                        break;
                    case R.id.safety:
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
