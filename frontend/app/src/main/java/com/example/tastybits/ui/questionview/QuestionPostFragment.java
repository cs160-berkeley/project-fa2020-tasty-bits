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

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tastybits.AsyncCallback;
import com.example.tastybits.Constants;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;

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

        Button sentimentButton = view.findViewById(R.id.sentiment_button);

        sentimentButton.setOnClickListener(button -> {

            NetworkRequest.getInstance().querySentiment(titleText.getText() + ". " + descriptionText.getText(), new AsyncCallback() {
                @Override
                public void onCompleted(Object result) {
                    String sentimentText = (String) result;
                    promptText.setText("Sentiment: " + sentimentText);
                }

                @Override
                public void onException(Exception e) {

                }
            });
        });



        Spinner categoryTagsSpinner = view.findViewById(R.id.categoryTagsSpinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.category_display_names, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryTagsSpinner.setAdapter(spinnerAdapter);

        Button postButton = view.findViewById(R.id.post_button);
        postButton.setOnClickListener(view1 -> {
            String categoryDisplayName = (String) spinnerAdapter.getItem(categoryTagsSpinner.getSelectedItemPosition());
            String categoryName = Constants.displayToQueryCategoryNameMap.get(categoryDisplayName);
            List<String> categoryNames = Arrays.asList(categoryName);

            //only have one category for a question (Even though api can handle multiple)
            assert(categoryNames.size() == 1);

            // send the mutation request to add this question
            // must convert the selected spinner tag into the actual category name listed in the
            // backend
            NetworkRequest.getInstance().mutationCreateQuestion(
                    categoryNames,
                    titleText.getText().toString(), descriptionText.getText().toString(), new AsyncCallback() {
                        @Override
                        public void onCompleted(Object result) {
                            getActivity().runOnUiThread(() -> {
                                Bundle args = new Bundle();
                                args.putString(getString(R.string.category_name_key), categoryName);
                                Navigation.findNavController(view).navigate(R.id.questionview_fragment, args);
                            });
                        }

                        @Override
                        public void onException(Exception e) {
                            //do-nothing
                        }
                    });

        });

        return view;
    }
}