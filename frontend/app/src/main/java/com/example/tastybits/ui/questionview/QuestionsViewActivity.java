//package com.example.tastybits.ui.questionview;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.tastybits.NetworkRequest;
//import com.example.tastybits.R;
//
//import java.util.ArrayList;
//
//public class QuestionsViewActivity extends AppCompatActivity {
//
//    private QuestionRecyclerViewAdapter qrv_adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_questions__view);
//
//        RecyclerView recyclerView = findViewById(R.id.QuestionsRecyclerView0);
//        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
//        qrv_adapter = setQuestionRecyclerViewAdapter();
//        recyclerView.setAdapter(qrv_adapter);
//
//        NetworkRequest.getInstance().sendQuestionRequest("ckhpkonee002043nz25jc7gmd", this::sendQuestionsUpdate);
//    }
//
//    /**
//     * Configures the recyclerview adapter with data upon loading this activity
//     * @return
//     */
//    private QuestionRecyclerViewAdapter setQuestionRecyclerViewAdapter() {
//        // TODO: call backend service
//        return new QuestionRecyclerViewAdapter(this, new ArrayList<QuestionItem>());
//    }
//
//    private void sendQuestionsUpdate(QuestionItem qi) {
//        this.runOnUiThread(() -> qrv_adapter.addQuestion(qi));
//    }
//}