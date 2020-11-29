package com.example.tastybits;

import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.CreateQuestionMutation;
import com.example.GetAnswerQuery;
import com.example.GetCategoriesQuery;
import com.example.GetQuestionsQuery;
import com.example.GetSentimentQuery;
import com.example.tastybits.ui.answerview.AnswerItem;
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

    public void queryQuestions(String categoryName, AsyncCallback callback) {


        String categoryId = categoryIdMap.get(categoryName);

        apolloClient.query(new GetQuestionsQuery(categoryId)).enqueue(new ApolloCall.Callback<GetQuestionsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetQuestionsQuery.Data> response) {
                List<GetQuestionsQuery.GetQuestion> qList = response.getData().getQuestions();
                for (GetQuestionsQuery.GetQuestion question : qList) {
                    QuestionItem qItem = new QuestionItem(question.id(), question.title(),
                            question.description());
                    callback.onCompleted(qItem);
                }
                //Log.i(TAG, response.toString());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
                callback.onException(e);
            }
        });
    }

    public void queryAnswer(String questionId, AsyncCallback callback) {
        apolloClient.query(new GetAnswerQuery(questionId)).enqueue(new ApolloCall.Callback<GetAnswerQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetAnswerQuery.Data> response) {
                List<GetAnswerQuery.GetAnswer> aList = response.getData().getAnswers();
                for (GetAnswerQuery.GetAnswer answer: aList) {
                    AnswerItem aItem = new AnswerItem(answer.id(), answer.content(), questionId);
                    callback.onCompleted(aItem);
                }
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }


    public void querySentiment(String text, AsyncCallback callback) {
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
                callback.onException(e);
            }
        });
    }

    /**
     * Send this request early to load the category ids.
     */
    public void queryCategories() {
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



    public void mutationCreateQuestion(List<String> categoryNames, String title, String description,
                                      AsyncCallback callback) {

        CreateQuestionMutation createQuestionMutation =
                new CreateQuestionMutation(categoryNamesToIds(categoryNames), title,
                        description);
        apolloClient.mutate(createQuestionMutation
        ).enqueue(new ApolloCall.Callback<CreateQuestionMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<CreateQuestionMutation.Data> response) {

                CreateQuestionMutation.CreateQuestion question =
                        response.getData().createQuestion();
                callback.onCompleted(new QuestionItem(question.id(), question.title(),
                        question.description()));
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                callback.onException(e);
            }
        });
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
