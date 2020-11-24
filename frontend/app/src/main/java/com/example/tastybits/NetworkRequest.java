package com.example.tastybits;

import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.CreateQuestionMutation;
import com.example.GetCategoriesQuery;
import com.example.GetQuestionsQuery;
import com.example.GetSentimentQuery;
import com.example.tastybits.ui.questionview.AddQuestionCallback;
import com.example.tastybits.ui.questionview.QuestionItem;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NetworkRequest {
    private static final String TAG = "app_" + NetworkRequest.class.getSimpleName();
    private static final String URL = "https://calsurvivalguide.wn.r.appspot.com/graphql";


    private static NetworkRequest networkRequest = null;
    private ApolloClient apolloClient;
    private Map<String, String> categoryIdMap;

    private NetworkRequest() {
        apolloClient =  ApolloClient.builder()
                        .serverUrl(URL)
                        .okHttpClient(
                            new OkHttpClient.Builder()
                                    .addInterceptor(new AuthorizationInterceptor())
                                    .build()
                        ).build();
        categoryIdMap = new HashMap<>();
    }

    public static NetworkRequest getInstance() {
        if (networkRequest == null) {
            networkRequest = new NetworkRequest();
        }
        return networkRequest;
    }

    public void sendQuestionRequest(String categoryId, AddQuestionCallback addQuestionCallback) {
        apolloClient.query(new GetQuestionsQuery(categoryId)).enqueue(new ApolloCall.Callback<GetQuestionsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetQuestionsQuery.Data> response) {
                List<GetQuestionsQuery.GetQuestion> q_list = response.getData().getQuestions();
                for (GetQuestionsQuery.GetQuestion question : q_list) {
                    QuestionItem qItem = new QuestionItem(question.id(), question.title(),
                            question.description());
                    addQuestionCallback.add(qItem);
                }
                //Log.i(TAG, response.toString());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
            }
        });
    }


    public void getSentiment(String text, AsyncCallback callback) {
        apolloClient.query(new GetSentimentQuery(text)).enqueue(new ApolloCall.Callback<GetSentimentQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetSentimentQuery.Data> response) {
                GetSentimentQuery.GetSentiment sentiment = response.getData().getSentiment();
                callback.onCompleted(sentiment.sentiment().rawValue());

                //Log.i(TAG, response.toString());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
            }
        });
    }

    /**
     * Send this request early to load the category ids.
     */
    public void sendCategoryRequest() {
        apolloClient.query(new GetCategoriesQuery()).enqueue(new ApolloCall.Callback<GetCategoriesQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetCategoriesQuery.Data> response) {
                for (GetCategoriesQuery.GetCategory category: response.getData().getCategories()) {
                    categoryIdMap.put(category.name(), category.id());
                }
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
            }
        });
    }

    /*
    category names: jobHunting, housing, financialAid, clubsAndDecals, enrollment, classPlanning
     */
    public void loadCategoryQuestionsRequest(String categoryName, AddQuestionCallback addQuestionCallback) {
        // assumes the categoryMap was already populated
        sendQuestionRequest(categoryIdMap.get(categoryName), addQuestionCallback);
    }

    public void createQuestionRequest(List<String> categoryNames, QuestionItem questionItem,
                                      AddQuestionCallback addQuestionCallback) {

        CreateQuestionMutation createQuestionMutation =
                new CreateQuestionMutation(categoryNamesToIds(categoryNames), questionItem.getQuestionText(),
                        questionItem.getDescriptionText());
        apolloClient.mutate(createQuestionMutation
        ).enqueue(new ApolloCall.Callback<CreateQuestionMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<CreateQuestionMutation.Data> response) {
                if (addQuestionCallback != null) {
                    CreateQuestionMutation.CreateQuestion question =
                            response.getData().createQuestion();
                    addQuestionCallback.add(new QuestionItem(question.id(), question.title(),
                            question.description()));
                }
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    public void createQuestionRequest(List<String> categoryNames, QuestionItem questionItem) {
        createQuestionRequest(categoryNames, questionItem, null);
    }

    private List<String> categoryNamesToIds(List<String> categoryNames) {
        List<String> cIds = new LinkedList<>();
        categoryNames.forEach((name) -> cIds.add(categoryIdMap.get(name)));
        return cIds;
    }

    /**
     * Authorization interceptor constructs the header for graphql requests
     */
    private class AuthorizationInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder().addHeader("Authorization",
                    Constants.AUTH_TOKEN).build();
            return chain.proceed(request);
        }
    }
}
