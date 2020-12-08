package com.example.tastybits.ui.informationhub;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.tastybits.Constants;
import com.example.tastybits.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.noties.markwon.AbstractMarkwonPlugin;
import io.noties.markwon.LinkResolver;
import io.noties.markwon.Markwon;
import io.noties.markwon.MarkwonConfiguration;
import io.noties.markwon.core.MarkwonTheme;
import io.noties.markwon.image.AsyncDrawable;
import io.noties.markwon.image.ImageSize;
import io.noties.markwon.image.ImageSizeResolver;
import io.noties.markwon.image.ImageSizeResolverDef;
import io.noties.markwon.image.ImagesPlugin;
import kotlin.text.MatchResult;
import kotlin.text.Regex;


public class InfoHubDetailsFragment extends Fragment {


    private ArrayList<String> titles;
    private ArrayList<String> splits;
    private ArrayList<TextView> contentViews;


    private static final String TAG = "InfoHubDetailsFragment";
    private ScrollView contentScrollView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i);
            menu.add(0, i, 0, title);
        }


        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        TextView target = contentViews.get(itemId);
        contentScrollView.scrollTo((int) target.getX(), (int) target.getY());

        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_infohub_details, container, false);

        String categoryName = getArguments().getString(getString(R.string.category_name_key));

        String md = Constants.queryCategoryToMarkdownArticle.get(categoryName);

        titles = new ArrayList<>(Arrays.asList(ArrayUtils.removeAllOccurrences(StringUtils.substringsBetween(md , "## ", "\n"), "")));
        splits = new ArrayList<>(Arrays.asList(ArrayUtils.removeAllOccurrences(StringUtils.splitByWholeSeparator(md , "## "), "")));
        contentViews = new ArrayList<>();

        contentScrollView = root.findViewById(R.id.contentScrollView);


        LinearLayout contentLinearLayout = root.findViewById(R.id.contentLinearLayout);


        final Markwon markwon = Markwon.builder(Constants.getMainActivity())
                .usePlugin(new AbstractMarkwonPlugin() {
                    @Override
                    public void configureTheme(@NonNull MarkwonTheme.Builder builder) {
                        builder.linkColor(getResources().getColor(R.color.blue_text)).headingBreakHeight(2);

                    }
                })
                .usePlugin(new AbstractMarkwonPlugin() {
                    @Override
                    public void configureConfiguration(@NonNull MarkwonConfiguration.Builder builder) {
                        builder.imageSizeResolver(new ImageSizeResolver() {
                            @NonNull
                            @Override
                            public Rect resolveImageSize(@NonNull AsyncDrawable drawable) {
                                //scale the rect to take up whole view
                                Rect r = new ImageSizeResolverDef().resolveImageSize(drawable);
                                float width = (float) (r.right - r.left);
                                float ratio = drawable.getLastKnownCanvasWidth() / width;
                                r.set(r.left, r.top, drawable.getLastKnownCanvasWidth(), (int) Math.floor(ratio * r.bottom));
                                return r;
                            }
                        });
                    }
                })
                .usePlugin(ImagesPlugin.create())
                .build();



        for (int i = 0; i < splits.size(); i++) {
            String split = splits.get(i);
            TextView tv = new TextView(Constants.getMainActivity());
            contentLinearLayout.addView(tv);
            tv.setTextSize(20);
            if(i == 0 && (splits.size() == titles.size() + 1)) {
                markwon.setMarkdown(tv, split);
            } else {
                markwon.setMarkdown(tv, "## " + split);
                contentViews.add(tv);
            }
        }


        return root;
    }




}
