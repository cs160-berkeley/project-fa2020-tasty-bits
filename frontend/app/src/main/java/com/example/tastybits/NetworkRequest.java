package com.example.tastybits;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.GetCategoriesQuery;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class NetworkRequest {
    private static final String TAG = "app_" + NetworkRequest.class.getSimpleName();
    private static final String URL = "https://calsurvivalguide.wn.r.appspot.com/graphql";


    private static NetworkRequest networkRequest = null;
    private ApolloClient apolloClient;

    private NetworkRequest() {
        apolloClient =  ApolloClient.builder()
                        .serverUrl(URL)
                        .okHttpClient(
                            new OkHttpClient.Builder()
                                    .addInterceptor(new AuthorizationInterceptor())
                                    .build()
                        ).build();
    }

    public static NetworkRequest getInstance() {
        if (networkRequest == null) {
            networkRequest = new NetworkRequest();
        }
        return networkRequest;
    }

    public void sendQuestionRequest(String categoryId, AddQuestionCallback addQuestionCallback) {
        apolloClient.query(new GetCategoriesQuery(categoryId)).enqueue(new ApolloCall.Callback<GetCategoriesQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetCategoriesQuery.Data> response) {
                Log.i(TAG, "Apollo "+ response.getData());
                List<GetCategoriesQuery.GetQuestion> q_list = response.getData().getQuestions();
                for (int i=0; i<q_list.size(); i++) {
                    QuestionItem qItem = new QuestionItem(q_list.get(i).title());
                    addQuestionCallback.add(qItem);
                }
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, "Apollo Error", e);
            }
        });
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
