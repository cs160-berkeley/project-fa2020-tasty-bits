package com.example.tastybits;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.UpsertAnswerVoteMutation;
import com.example.UpsertQuestionClickMutation;
import com.example.UpsertQuestionVoteMutation;

import java.util.ArrayList;
import java.util.Collections;

public class QARecyclerViewAdapter extends RecyclerView.Adapter<QAViewHolder> {
    private ArrayList<QAItem> qaList;
    private Activity activity;

    public QARecyclerViewAdapter(Activity activity, ArrayList<QAItem> qaList) {
        this.qaList = qaList;
        this.activity = activity;
    }

    /**
     * Needs to be executed on main UI thread
     * @param qaItem
     */
    public void addItem(QAItem qaItem) {
        if (qaItem.getTitleText() != null && !qaItem.getTitleText().trim().equals("") && qaItem.getDeletedAt() == 0) {
            qaList.add(0, qaItem);
            notifyItemInserted(0);
        }
    }

    public void sortByUpvote() {
        //
        Collections.sort(qaList, (a1, a2) -> a2.getVoteScore() - a1.getVoteScore());
        notifyDataSetChanged();
    }

    public void sortByCreatedAt() {
        //
        Collections.sort(qaList, (a1, a2) -> (a2.getCreatedAt() - a1.getCreatedAt()) > 0 ? 1: -1);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public QAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_qa, parent, false);
        return new QAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QAViewHolder holder, int position) {
        QAItem qaItem = qaList.get(position);
        holder.bindTo(activity, qaItem, position, new AsyncCallback() {
            @Override
            public void onCompleted(Object result) {
                String command = (String) result;
                if (command.equals("sort")) {
                    sortByUpvote();
                }
            }

            @Override
            public void onException(Exception e) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return qaList.size();
    }


}
