package com.example.tastybits.ui.questionview;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tastybits.AsyncCallback;
import com.example.tastybits.Constants;
import com.example.tastybits.MainActivity;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;
import com.example.tastybits.Utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionPostFragment extends Fragment {

    private EditText titleText;
    private EditText descriptionText;
    private TextView sentimentText;


    public QuestionPostFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_post, container, false);

        titleText = view.findViewById(R.id.titleTextView);
        descriptionText = view.findViewById(R.id.description);
        sentimentText = view.findViewById(R.id.questionSentimentText);
        TextView promptText = view.findViewById(R.id.promptTextView);

        titleText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    updateSentiment();

                }
            }
        });

        titleText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                    case EditorInfo.IME_ACTION_NEXT:
                    case EditorInfo.IME_ACTION_PREVIOUS:
                        updateSentiment();
                        return true;
                }
                return false;
            }
        });

        descriptionText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    updateSentiment();
                }
            }
        });

        descriptionText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                    case EditorInfo.IME_ACTION_NEXT:
                    case EditorInfo.IME_ACTION_PREVIOUS:
                        updateSentiment();
                        return true;
                }
                return false;
            }
        });

        Spinner categoryTagsSpinner = view.findViewById(R.id.categoryTagsSpinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(Utilities.getMainActivity(),
                R.array.category_display_names, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryTagsSpinner.setAdapter(spinnerAdapter);


        boolean cameFromSpecificCategory = getArguments() != null;
        //prefill the spinner with the correct category if coming from a specific category
        if (cameFromSpecificCategory) {
            String argCategoryName = getArguments().getString(getString(R.string.category_name_key));
            if (argCategoryName != null) {
                for (int i = 0; i < spinnerAdapter.getCount(); i++) {
                    String displayCategoryName = spinnerAdapter.getItem(i).toString();
                    String queryCategoryName = Constants.displayToQueryCategoryNameMap.get(displayCategoryName);
                    if (queryCategoryName.equals(argCategoryName)) {
                        categoryTagsSpinner.setSelection(i);
                    }
                }
            }
        }


        Button postButton = view.findViewById(R.id.questionConfirmPostButton);
        postButton.setOnClickListener((view1) -> {
            String categoryDisplayName = (String) spinnerAdapter.getItem(categoryTagsSpinner.getSelectedItemPosition());
            String categoryName = Constants.displayToQueryCategoryNameMap.get(categoryDisplayName);
            List<String> categoryNames = Arrays.asList(categoryName);

            //only have one category for a question (Even though api can handle multiple)
            assert(categoryNames.size() == 1);

            if (titleText.getText().toString().trim().equals("")) {
                Toast.makeText(Utilities.getMainActivity(), "Please fill out the title for your question.", Toast.LENGTH_LONG).show();
                return;
            }

            NetworkRequest.getInstance().querySentiment(titleText.getText() + ". " + descriptionText.getText(), new AsyncCallback() {
                @Override
                public void onCompleted(Object result) {
                    String sentimentText = (String) result;
                    if (sentimentText.equals("VERY_ANGRY") || sentimentText.equals("ANGRY")) {
                        Utilities.getMainActivity().runOnUiThread(() -> Toast.makeText(Utilities.getMainActivity(), "Please make your question more positive to help keep the community safe and happy for everyone.", Toast.LENGTH_LONG).show());
                    } else {
                        NetworkRequest.getInstance().mutationCreateQuestion(
                                categoryNames,
                                titleText.getText().toString(), descriptionText.getText().toString(), new AsyncCallback() {
                                    @Override
                                    public void onCompleted(Object result) {
                                        Utilities.getMainActivity().runOnUiThread(() -> {
                                            if (cameFromSpecificCategory) {
                                                Navigation.findNavController(view).navigateUp();
                                            } else {
                                                Bundle args = new Bundle();
                                                args.putString(getString(R.string.category_name_key), categoryName);
                                                NavOptions navOptions = (new NavOptions.Builder()).setPopUpTo(R.id.questionpost_fragment, true).build();
                                                Navigation.findNavController(view).navigate(R.id.questionview_fragment, args, navOptions);
                                            }
                                        });
                                    }

                                    @Override
                                    public void onException(Exception e) {
                                        //do-nothing
                                    }
                                });
                    }
                }

                @Override
                public void onException(Exception e) {

                }
            });




        });

        return view;
    }


    public void updateSentiment() {
        if (!titleText.getText().toString().trim().equals("") ||  !descriptionText.getText().toString().trim().equals("")) {
            NetworkRequest.getInstance().querySentiment(titleText.getText() + ". " + descriptionText.getText(), new AsyncCallback() {
                @Override
                public void onCompleted(Object result) {
                    String sentiment = (String) result;
                    if (!Constants.displaySentimentToQuerySentiment.get(sentimentText.getText()).equals(sentiment)) {
                        Utilities.getMainActivity().runOnUiThread(() -> {
                            sentimentText.setText(Constants.querySentimentToDisplaySentiment.get(sentiment));
                        });
                    }
                }

                @Override
                public void onException(Exception e) {

                }
            });
        } else {
            sentimentText.setText("");
        }
    }
}