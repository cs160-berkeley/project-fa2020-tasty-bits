package com.example.tastybits;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Utilities {
    private static Activity activity;

    public static Activity getMainActivity() {
        return activity;
    }

    public static void init(Activity mActivity) {
        activity = mActivity;
    }

    public static void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
