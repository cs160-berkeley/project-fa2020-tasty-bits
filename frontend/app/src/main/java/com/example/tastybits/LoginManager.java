package com.example.tastybits;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.auth0.android.Auth0;
import com.auth0.android.Auth0Exception;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.VoidCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;

import java.util.Date;

public class LoginManager {

    public static final String TAG_APP_ID = "com.example.tastybits";

    public static final String TAG_ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String TAG_EXPIRES_AT = "EXPIRES_AT";

    private static final String TAG = "LoginManager";

    public interface LoginCallback {
        void onCompleted(String accessToken);

        void onDialogException(Dialog dialog);

        void onException(Exception e);
    }

    public interface LogoutCallback {
        void onCompleted();

        void onException(Exception e);
    }

    private Auth0 auth0;
    private Activity activity;


    private static LoginManager loginManager = null;
    public LoginManager(Activity activity) {
        this.activity = activity;

        auth0 = new Auth0(activity.getString(R.string.com_auth0_client_id), activity.getString(R.string.com_auth0_domain));
        auth0.setOIDCConformant(true);
    }

    public static void init(LoginManager initLoginManager) {
        if (loginManager == null) {
            loginManager = initLoginManager;
        }
    }

    public static LoginManager getInstance() {
        return loginManager;
    }

    public String getAccessToken() {
        SharedPreferences preferences = activity.getSharedPreferences(TAG_APP_ID, Context.MODE_PRIVATE);
        String accessToken = preferences.getString(TAG_ACCESS_TOKEN, null);
        String expiresAt = preferences.getString(TAG_EXPIRES_AT, null);

        if (accessToken != null && expiresAt != null) {
            long expiresAtMillis = Long.parseLong(expiresAt);
            Date expiresAtDate = new Date(expiresAtMillis);
            if (new Date().before(expiresAtDate)) {
                return accessToken;
            }
        }
        return null;
    }

    public void login(LoginCallback loginCallback) {
        String accessToken = getAccessToken();

        if (accessToken != null) {
            loginCallback.onCompleted(accessToken);
            return;
        }

        WebAuthProvider.login(auth0)
                .withScheme("demo")
                .withAudience("https://calsurvivalguide.us.auth0.com/api/v2/")
                .start(activity, new AuthCallback() {
                    @Override
                    public void onFailure(@NonNull final Dialog dialog) {
                        loginCallback.onDialogException(dialog);
                    }

                    @Override
                    public void onFailure(final AuthenticationException exception) {
                        loginCallback.onException(exception);
                    }

                    @Override
                    public void onSuccess(@NonNull final Credentials credentials) {
                        String token = credentials.getAccessToken();

                        long expires = credentials.getExpiresAt().getTime();
                        SharedPreferences preferences = activity.getSharedPreferences(TAG_APP_ID, Context.MODE_PRIVATE);
                        preferences.edit().putString(TAG_ACCESS_TOKEN, token).apply();
                        preferences.edit().putString(TAG_EXPIRES_AT, Long.toString(expires)).apply();

                        loginCallback.onCompleted(token);


                    }
                });
    }

    public void logout(LogoutCallback logoutCallback) {
        WebAuthProvider.logout(auth0)
                .withScheme("demo")
                .start(activity, new VoidCallback() {
                    @Override
                    public void onSuccess(Void payload) {
                        logoutCallback.onCompleted();
                    }

                    @Override
                    public void onFailure(Auth0Exception error) {
                        logoutCallback.onException(error);
                    }
                });
    }
}
