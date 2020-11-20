package com.example.tastybits;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.Auth0Exception;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.VoidCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    public LoginManager loginManager;

    public static final String EXTRA_CLEAR_CREDENTIALS = "com.auth0.CLEAR_CREDENTIALS";
    public static final String EXTRA_ACCESS_TOKEN = "com.auth0.ACCESS_TOKEN";
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.infohub, R.id.questionhub, R.id.chat, R.id.profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        setupLoginManager();
    }

    public void setupLoginManager() {
        this.loginManager = new LoginManager(this, new LoginManager.LoginCallback() {
            @Override
            public void onCompleted(final String accessToken) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, "access token: " + accessToken);
                        Toast.makeText(MainActivity.this, "Successfully logged in with accessToken: " + accessToken, Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onDialogException(final Dialog dialog) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.show();
                    }
                });
            }

            @Override
            public void onException(final Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Login Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }, new LoginManager.LogoutCallback() {
            @Override
            public void onCompleted() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Successfully logged out!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onException(final Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Logout Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}