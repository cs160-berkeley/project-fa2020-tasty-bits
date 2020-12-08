package com.example.tastybits.ui.loading;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.GetQuestionsQuery;
import com.example.GetSuggestedQuestionsQuery;
import com.example.GetYourQuestionsQuery;
import com.example.tastybits.AsyncCallback;
import com.example.tastybits.Constants;
import com.example.tastybits.LoginManager;
import com.example.tastybits.MainActivity;
import com.example.tastybits.NetworkRequest;
import com.example.tastybits.R;
import com.example.tastybits.Utilities;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class LoadingFragment extends Fragment {
    private static final String TAG = "LoadingFragment";

    private View baseView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_loading, container, false);


        BottomNavigationView navView = Utilities.getMainActivity().findViewById(R.id.nav_view);
        navView.getMenu().findItem(R.id.homescreen).setEnabled(false);
        navView.getMenu().findItem(R.id.infohub).setEnabled(false);
        navView.getMenu().findItem(R.id.questionhub).setEnabled(false);

        LoginManager.init(new LoginManager(Utilities.getMainActivity()));

        boolean forceLogin = false;
        if (getArguments() != null) {
            forceLogin = getArguments().getBoolean("forceLogin");
        }

        if (LoginManager.getInstance().getAccessToken() == null && !forceLogin)  {

            NavOptions navOptions = (new NavOptions.Builder()).setPopUpTo(R.id.loading, true).build();
            Navigation.findNavController(Utilities.getMainActivity(), R.id.nav_host_fragment).navigate(R.id.onboarding, null, navOptions);
        } else {
            login();
        }

        return baseView;
    }

    public void login() {
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

                            Utilities.getMainActivity().runOnUiThread(() -> {
                                BottomNavigationView navView = Utilities.getMainActivity().findViewById(R.id.nav_view);
                                navView.getMenu().findItem(R.id.homescreen).setEnabled(true);
                                navView.getMenu().findItem(R.id.infohub).setEnabled(true);
                                navView.getMenu().findItem(R.id.questionhub).setEnabled(true);

                                NavOptions navOptions = (new NavOptions.Builder()).setPopUpTo(R.id.loading, true).build();
                                Navigation.findNavController(baseView).navigate(R.id.homescreen, null, navOptions);
                            });

                        }
                    }

                    @Override
                    public void onException(Exception e) {

                        Utilities.getMainActivity().runOnUiThread(() -> {
                            TextView loadingAppText = baseView.findViewById(R.id.startAppLoadingText);
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
                Utilities.getMainActivity().runOnUiThread(() -> {
                    dialog.show();
                });
            }

            @Override
            public void onException(Exception e) {
                Activity activity = Utilities.getMainActivity();
                activity.runOnUiThread(() -> {
                    Toast.makeText(activity, "Login Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

}