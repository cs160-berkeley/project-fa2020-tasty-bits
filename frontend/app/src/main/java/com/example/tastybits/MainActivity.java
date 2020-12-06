package com.example.tastybits;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tastybits.ui.onboarding.OnboardingFragmentActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        LoginManager.init(new LoginManager(this));

        //only after logged in execute the rest
        LoginManager.getInstance().login(new LoginManager.LoginCallback() {
            @Override
            public void onCompleted(String accessToken) {

                    NetworkRequest.init(new NetworkRequest(accessToken));

                    final boolean[] completedUserMutation = {false};
                    final boolean[] completedCategoryQuery = {false};

                    AsyncCallback checkCompletion = new AsyncCallback() {
                        @Override
                        public void onCompleted(Object result) {

                            if (completedCategoryQuery[0] == true && completedUserMutation[0] == true) {

                                runOnUiThread(() -> {
                                    //continue with setting up taskbar and finally navigate to home screen because managers now initialized
                                    BottomNavigationView navView = findViewById(R.id.nav_view);

                                    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                                            R.id.homescreen, R.id.infohub, R.id.questionhub)
                                            .build();
                                    NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment);
                                    NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, appBarConfiguration);
                                    NavigationUI.setupWithNavController(navView, navController);

                                    NavOptions navOptions = (new NavOptions.Builder()).setPopUpTo(R.id.loading, true).build();
                                    navController.navigate(R.id.homescreen, null, navOptions);
                                });

                            }
                        }

                        @Override
                        public void onException(Exception e) {

                            runOnUiThread(() -> {
                                TextView loadingAppText = findViewById(R.id.startAppLoadingText);
                                loadingAppText.setText("We've run into an issue loading essential information for our app, please try again later :(");
                            });
                            Log.i(TAG, "Could not load either user or categories. This is a fatal error for our app. Error: "  + e.getMessage());
                        }
                    };

                    NetworkRequest.getInstance().mutationUpsertUser(new AsyncCallback() {
                        @Override
                        public void onCompleted(Object result) {
                            completedUserMutation[0] = true;
                            checkCompletion.onCompleted(result);
                        }

                        @Override
                        public void onException(Exception e) {
                            checkCompletion.onException(e);
                        }
                    });

                    // load the categories, and only then setup the fragments since they're dependent on categories
                    NetworkRequest.getInstance().queryCategories(new AsyncCallback() {
                        @Override
                        public void onCompleted(Object result) {
                            completedCategoryQuery[0] = true;
                            checkCompletion.onCompleted(result);
                        }

                        @Override
                        public void onException(Exception e) {
                            checkCompletion.onException(e);
                        }
                    });

            }

            @Override
            public void onDialogException(Dialog dialog) {
                runOnUiThread(() -> {
                    dialog.show();
                });
            }

            @Override
            public void onException(Exception e) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, "Login Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}