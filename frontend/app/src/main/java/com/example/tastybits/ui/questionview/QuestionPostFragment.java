package com.example.tastybits.ui.questionview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tastybits.Constants;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;

import java.util.Arrays;

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

        EditText questionText = view.findViewById(R.id.questionTextView);
        EditText descriptionText = view.findViewById(R.id.description);

        Spinner categoryTagsSpinner = view.findViewById(R.id.categoryTagsSpinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.category_display_names, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryTagsSpinner.setAdapter(spinnerAdapter);

        Button postButton = view.findViewById(R.id.post_button);
        postButton.setOnClickListener(view1 -> {
            String categoryDisplayName =
                    (String) spinnerAdapter.getItem(categoryTagsSpinner.getSelectedItemPosition());
            QuestionItem newQuestion = new QuestionItem(questionText.getText().toString(),
                    descriptionText.getText().toString());

            // send the mutation request to add this question
            // must convert the selected spinner tag into the actual category name listed in the
            // backend
            NetworkRequest.getInstance().createQuestionRequest(
                    Arrays.asList(Constants.displayToQueryCategoryNameMap.get(categoryDisplayName)),
                    newQuestion);
            Navigation.findNavController(view).navigate(R.id.questionhub);
        });

        return view;
    }
}