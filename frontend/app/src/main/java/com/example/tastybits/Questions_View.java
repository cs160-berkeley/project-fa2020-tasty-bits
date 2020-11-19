package com.example.tastybits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Questions_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions__view);

        RecyclerView recyclerView = findViewById(R.id.QuestionsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        QuestionRecyclerViewAdapter qrv_adapter = setQuestionRecyclerViewAdapter();
        recyclerView.setAdapter(qrv_adapter);
    }

    /**
     * Configures the recyclerview adapter with data upon loading this activity
     * @return
     */
    private QuestionRecyclerViewAdapter setQuestionRecyclerViewAdapter() {
        // TODO: call backend service
        return new QuestionRecyclerViewAdapter(this, new ArrayList<QuestionItem>());
    }
}