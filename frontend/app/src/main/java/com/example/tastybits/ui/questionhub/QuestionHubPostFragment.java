package com.example.tastybits.ui.questionhub;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.tastybits.R;

public class QuestionHubPostFragment extends Fragment{
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //questionHubViewModel = ViewModelProviders.of(this).get(com.example.tastybits.ui.questionhub.QuestionHubViewModel.class);
        View view = inflater.inflate(R.layout.fragment_questionhub_post, container, false);
        /*final TextView textView = root.findViewById(R.id.questionhub);
        questionHubViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/


        SearchView search = view.findViewById(R.id.searchBar);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //prints out the whole user inout once they submit it
                System.out.println(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //prints out the user's input as they entSystem.out.println(newText);
                return false;
            }
        });

        final EditText question = view.findViewById(R.id.question);
        question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println(s);
            }
        });

        final EditText description = view.findViewById(R.id.description);
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //System.out.println(s);

            }

            @Override
            public void afterTextChanged(Editable s) {
                //System.out.println(s);
            }
        });

        Button postButton = view.findViewById(R.id.post_button);
        /*postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(" post it");
                String questionTitle = question.getText().toString();
                String questionDescription = description.getText().toString();
                //pass the questionTitle and questionDescription to Sofia's class


            }
        });*/
        postButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.questionhub));

        final ImageButton classPlan = view.findViewById(R.id.class_planning);
        final ImageButton enrollment = view.findViewById(R.id.enrollment);
        final ImageButton fafsa = view.findViewById(R.id.fafsa);
        final ImageButton clubs = view.findViewById(R.id.clubs);
        final ImageButton minus1 = view.findViewById(R.id.minus1);
        final ImageButton minus2 = view.findViewById(R.id.minus2);
        final ImageButton minus3 = view.findViewById(R.id.minus3);
        final ImageButton minus4 = view.findViewById(R.id.minus4);
        classPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("class planning");
                v.setBackground(getResources().getDrawable(R.drawable.brown_image_button));
                enrollment.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                fafsa.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                clubs.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                minus1.setImageResource(R.drawable.tick);
                minus2.setImageResource(R.drawable.frame);
                minus3.setImageResource(R.drawable.frame);
                minus4.setImageResource(R.drawable.frame);
            }
        });


        enrollment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("Enrollment");
                v.setBackground(getResources().getDrawable(R.drawable.brown_image_button));
                classPlan.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                fafsa.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                clubs.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                minus2.setImageResource(R.drawable.tick);
                minus1.setImageResource(R.drawable.frame);
                minus3.setImageResource(R.drawable.frame);
                minus4.setImageResource(R.drawable.frame);

            }
        });


        fafsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //System.out.println("FAFSA");
                v.setBackground(getResources().getDrawable(R.drawable.brown_image_button));
                classPlan.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                enrollment.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                clubs.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                minus3.setImageResource(R.drawable.tick);
                minus2.setImageResource(R.drawable.frame);
                minus1.setImageResource(R.drawable.frame);
                minus4.setImageResource(R.drawable.frame);
            }
        });


        clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // System.out.println("Clubs and Decals");
                v.setBackground(getResources().getDrawable(R.drawable.brown_image_button));
                classPlan.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                fafsa.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                enrollment.setBackground(getResources().getDrawable(R.drawable.light_brown_image_button));
                minus4.setImageResource(R.drawable.tick);
                minus2.setImageResource(R.drawable.frame);
                minus3.setImageResource(R.drawable.frame);
                minus1.setImageResource(R.drawable.frame);
            }
        });

        return view;
    }
}
