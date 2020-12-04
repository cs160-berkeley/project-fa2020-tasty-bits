package com.example.tastybits.ui.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.tastybits.R;

public class OnBoardFragment3 extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_onboard_3, container, false);

        ImageView text = root.findViewById(R.id.onboard3_text1);
        text.setBackgroundResource(R.drawable.onboard3_text1);

        ImageView text2 = root.findViewById(R.id.onboard3_text2);
            text2.setBackgroundResource(R.drawable.onboarding3_text2);

        ImageView dot = root.findViewById(R.id.dot);
        dot.setBackgroundResource(R.drawable.dot3);

        return root;
    }
}
