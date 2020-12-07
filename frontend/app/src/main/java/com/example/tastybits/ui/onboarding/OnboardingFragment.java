package com.example.tastybits.ui.onboarding;

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

import java.util.ArrayList;
import java.util.List;

public class OnboardingFragment extends Fragment {
    private static final String TAG = "LoadingFragment";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fragment_onboarding, container, false);

        baseView.findViewById(R.id.onboardingRegisterLoginButton).setOnClickListener((v) -> {
            Bundle args = new Bundle();
            args.putBoolean("forceLogin", true);
            NavOptions navOptions = (new NavOptions.Builder()).setPopUpTo(R.id.onboarding, true).build();
            Navigation.findNavController(baseView).navigate(R.id.loading, args, navOptions);
        });
        return baseView;
    }

}