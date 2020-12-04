package com.example.tastybits.ui.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tastybits.R;

public class OnBoardFragment4 extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_onboard_4, container, false);

        ImageView text = root.findViewById(R.id.onboard4_text1);
        text.setBackgroundResource(R.drawable.onboard4_text);


        ImageView dot = root.findViewById(R.id.dot);
        dot.setBackgroundResource(R.drawable.dot4);

        return root;
    }
}
