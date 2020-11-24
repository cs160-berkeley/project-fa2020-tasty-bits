package com.example.tastybits;

public interface AsyncCallback {
    void onCompleted(Object result);
    void onException(Exception e);
}

