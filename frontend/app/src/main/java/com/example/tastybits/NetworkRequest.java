package com.example.tastybits;

import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.CreateAnswerMutation;
import com.example.CreateQuestionMutation;
import com.example.DeleteAnswerMutation;
import com.example.EditAnswerMutation;
import com.example.EditQuestionMutation;
import com.example.EditUserMutation;
import com.example.GetAllCategoryQuestionsQuery;
import com.example.GetAnswerQuery;
import com.example.GetCategoriesQuery;
import com.example.GetQuestionsQuery;
import com.example.GetSentimentQuery;
import com.example.GetSuggestedQuestionsQuery;
import com.example.GetUserQuery;
import com.example.GetYourAnswersQuery;
import com.example.GetYourQuestionsQuery;
import com.example.UpsertAnswerVoteMutation;
import com.example.UpsertQuestionClickMutation;
import com.example.UpsertQuestionVoteMutation;
import com.example.UpsertUserMutation;
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

    private String accessToken;

    public NetworkRequest(String accessToken) {
        this.accessToken = accessToken;
        apolloClient =  ApolloClient.builder()
                        .serverUrl(URL)
                        .okHttpClient(
                            new OkHttpClient.Builder()
                                    .addInterceptor(new AuthorizationInterceptor())
                                    .build()
                        ).build();
        categoryIdMap = new HashMap<>();
    }



    public static void init(NetworkRequest initNetworkRequest) {
        if (networkRequest == null) {
            networkRequest = initNetworkRequest;
        }
    }

    public static NetworkRequest getInstance() {
        return networkRequest;
    }

    public void queryQuestions(String categoryName, AsyncCallback callback) {
        String categoryId = categoryIdMap.get(categoryName);

        apolloClient.query(new GetQuestionsQuery(categoryId)).enqueue(new ApolloCall.Callback<GetQuestionsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetQuestionsQuery.Data> response) {
                List<GetQuestionsQuery.GetQuestion> qList = response.getData().getQuestions();
                callback.onCompleted(qList);
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
                callback.onCompleted(aList);

            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
                callback.onException(e);
            }
        });
    }


    public void mutationUpsertUser(AsyncCallback callback) {
        apolloClient.mutate(new UpsertUserMutation()).enqueue(new ApolloCall.Callback<UpsertUserMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<UpsertUserMutation.Data> response) {
                callback.onCompleted(response.getData().upsertUser().id());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
                callback.onException(e);
            }
        });
    }

    public void mutationEditUser(String name, AsyncCallback callback) {
        apolloClient.mutate(new EditUserMutation(name)).enqueue(new ApolloCall.Callback<EditUserMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<EditUserMutation.Data> response) {
                EditUserMutation.EditUser user = response.getData().editUser();
                callback.onCompleted(user);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
                callback.onException(e);
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

    public void queryYourQuestions(AsyncCallback callback) {
        apolloClient.query(new GetYourQuestionsQuery()).enqueue(new ApolloCall.Callback<GetYourQuestionsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetYourQuestionsQuery.Data> response) {
                List<GetYourQuestionsQuery.GetYourQuestion> questions = response.getData().getYourQuestions();
                callback.onCompleted(questions);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
                callback.onException(e);
            }
        });
    }

    public void queryGetUser(AsyncCallback callback) {
        apolloClient.query(new GetUserQuery()).enqueue(new ApolloCall.Callback<GetUserQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetUserQuery.Data> response) {
                GetUserQuery.GetUser user = response.getData().getUser();
                callback.onCompleted(user);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
                callback.onException(e);
            }
        });
    }

    public void queryYourAnswers(AsyncCallback callback) {
        apolloClient.query(new GetYourAnswersQuery()).enqueue(new ApolloCall.Callback<GetYourAnswersQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetYourAnswersQuery.Data> response) {
                List<GetYourAnswersQuery.GetYourAnswer> answers = response.getData().getYourAnswers();
                callback.onCompleted(answers);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
                callback.onException(e);
            }
        });
    }


    public void querySuggestedQuestions(AsyncCallback callback) {
        apolloClient.query(new GetSuggestedQuestionsQuery()).enqueue(new ApolloCall.Callback<GetSuggestedQuestionsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetSuggestedQuestionsQuery.Data> response) {
                List<GetSuggestedQuestionsQuery.GetSuggestedQuestion> questions = response.getData().getSuggestedQuestions();
                callback.onCompleted(questions);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
                callback.onException(e);
            }
        });
    }

    public void queryAllCategoryQuestions(AsyncCallback callback) {
        apolloClient.query(new GetAllCategoryQuestionsQuery()).enqueue(new ApolloCall.Callback<GetAllCategoryQuestionsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetAllCategoryQuestionsQuery.Data> response) {
                List<GetAllCategoryQuestionsQuery.GetCategory> categories = response.getData().getCategories();
                callback.onCompleted(categories);
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
    public void queryCategories(AsyncCallback callback) {
        apolloClient.query(new GetCategoriesQuery()).enqueue(new ApolloCall.Callback<GetCategoriesQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<GetCategoriesQuery.Data> response) {
                for (GetCategoriesQuery.GetCategory category: response.getData().getCategories()) {
                    categoryIdMap.put(category.name(), category.id());
                }
                callback.onCompleted(response.getData().getCategories());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.e(TAG, e.toString());
                callback.onException(e);

            }
        });
    }



    public void mutationCreateQuestion(List<String> categoryNames, String title, String description,
                                      AsyncCallback callback) {
        Log.i(TAG, categoryIdMap.toString());
        CreateQuestionMutation createQuestionMutation =
                new CreateQuestionMutation(categoryNamesToIds(categoryNames), title,
                        description);
        apolloClient.mutate(createQuestionMutation
        ).enqueue(new ApolloCall.Callback<CreateQuestionMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<CreateQuestionMutation.Data> response) {

                CreateQuestionMutation.CreateQuestion question =
                        response.getData().createQuestion();
                Log.i(TAG, response.toString());
                callback.onCompleted(question);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                callback.onException(e);
            }
        });
    }

    public void mutationCreateAnswer(String questionId, String content, AsyncCallback callback) {
        CreateAnswerMutation createAnswerMutation = new CreateAnswerMutation(questionId, content);
        apolloClient.mutate(createAnswerMutation).enqueue(new ApolloCall.Callback<CreateAnswerMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<CreateAnswerMutation.Data> response) {
                CreateAnswerMutation.CreateAnswer answer = response.getData().createAnswer();
                callback.onCompleted(answer);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });

    }

    public void mutationUpsertQuestionVote(String questionId, Boolean upDown, AsyncCallback callback) {
        UpsertQuestionVoteMutation upsertQuestionVoteMutation =
                new UpsertQuestionVoteMutation(questionId, upDown);
        apolloClient.mutate(upsertQuestionVoteMutation).enqueue(new ApolloCall.Callback<UpsertQuestionVoteMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<UpsertQuestionVoteMutation.Data> response) {
                UpsertQuestionVoteMutation.UpsertQuestionVote upsertQuestionVote =
                        response.getData().upsertQuestionVote();
                callback.onCompleted(upsertQuestionVote);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    public void mutationEditQuestion(String questionId, String title, String description, AsyncCallback callback) {
        EditQuestionMutation editQuestionMutation =
                new EditQuestionMutation(questionId, title, description);
        apolloClient.mutate(editQuestionMutation).enqueue(new ApolloCall.Callback<EditQuestionMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<EditQuestionMutation.Data> response) {
                EditQuestionMutation.EditQuestion editQuestion =
                        response.getData().editQuestion();
                callback.onCompleted(editQuestion);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    public void mutationEditAnswer(String answerId, String content, AsyncCallback callback) {
        EditAnswerMutation editAnswerMutation =
                new EditAnswerMutation(answerId, content);
        apolloClient.mutate(editAnswerMutation).enqueue(new ApolloCall.Callback<EditAnswerMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<EditAnswerMutation.Data> response) {
                EditAnswerMutation.EditAnswer editAnswer =
                        response.getData().editAnswer();
                callback.onCompleted(editAnswer);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }


    public void mutationDeleteQuestion(String questionId, AsyncCallback callback) {
        DeleteAnswerMutation deleteAnswerMutation =
                new DeleteAnswerMutation(questionId, true);
        apolloClient.mutate(deleteAnswerMutation).enqueue(new ApolloCall.Callback<DeleteAnswerMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<DeleteAnswerMutation.Data> response) {
                DeleteAnswerMutation.EditAnswer deleteAnswer =
                        response.getData().editAnswer();
                callback.onCompleted(deleteAnswer);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    public void mutationDeleteAnswer(String answerId, AsyncCallback callback) {
        DeleteAnswerMutation deleteAnswerMutation =
                new DeleteAnswerMutation(answerId, true);
        apolloClient.mutate(deleteAnswerMutation).enqueue(new ApolloCall.Callback<DeleteAnswerMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<DeleteAnswerMutation.Data> response) {
                DeleteAnswerMutation.EditAnswer deleteAnswer =
                        response.getData().editAnswer();
                callback.onCompleted(deleteAnswer);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    public void mutationUpsertQuestionClick(String questionId, AsyncCallback callback) {
        UpsertQuestionClickMutation upsertQuestionClickMutation =
                new UpsertQuestionClickMutation(questionId);
        apolloClient.mutate(upsertQuestionClickMutation).enqueue(new ApolloCall.Callback<UpsertQuestionClickMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<UpsertQuestionClickMutation.Data> response) {
                UpsertQuestionClickMutation.UpsertQuestionClick upsertQuestionClick = response.getData().upsertQuestionClick();
                callback.onCompleted(upsertQuestionClick);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }

    public void mutationUpsertAnswerVote(String answerId, Boolean upDown, AsyncCallback callback) {
        UpsertAnswerVoteMutation upsertAnswerVoteMutation =
                new UpsertAnswerVoteMutation(answerId, upDown);
        apolloClient.mutate(upsertAnswerVoteMutation).enqueue(new ApolloCall.Callback<UpsertAnswerVoteMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<UpsertAnswerVoteMutation.Data> response) {
                UpsertAnswerVoteMutation.UpsertAnswerVote upsertAnswerVote =
                        response.getData().upsertAnswerVote();
                callback.onCompleted(upsertAnswerVote);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

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
                    getInstance().accessToken).build();
            return chain.proceed(request);
        }
    }
}
