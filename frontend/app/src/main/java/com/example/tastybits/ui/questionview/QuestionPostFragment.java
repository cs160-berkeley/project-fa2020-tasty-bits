package com.example.tastybits.ui.questionview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionPostFragment extends Fragment {


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

        EditText titleText = view.findViewById(R.id.titleTextView);
        EditText descriptionText = view.findViewById(R.id.description);
        TextView promptText = view.findViewById(R.id.promptTextView);


        Spinner categoryTagsSpinner = view.findViewById(R.id.categoryTagsSpinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
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
                Toast.makeText(getActivity(), "Please fill out the title for your question.", Toast.LENGTH_LONG).show();
                return;
            }

            NetworkRequest.getInstance().querySentiment(titleText.getText() + ". " + descriptionText.getText(), new AsyncCallback() {
                @Override
                public void onCompleted(Object result) {
                    String sentimentText = (String) result;
                    if (sentimentText.equals("VERY_ANGRY") || sentimentText.equals("ANGRY")) {
                        getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), "Please make your question more positive to help keep the community safe and happy for everyone.", Toast.LENGTH_LONG).show());
                    } else {
                        NetworkRequest.getInstance().mutationCreateQuestion(
                                categoryNames,
                                titleText.getText().toString(), descriptionText.getText().toString(), new AsyncCallback() {
                                    @Override
                                    public void onCompleted(Object result) {
                                        getActivity().runOnUiThread(() -> {
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
}