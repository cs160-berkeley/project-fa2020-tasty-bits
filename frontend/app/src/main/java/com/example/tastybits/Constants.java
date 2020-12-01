package com.example.tastybits;

import java.util.Map;

public class Constants {

    public static final String[] cycleColors;
    public static final Map<String, String> displayToQueryCategoryNameMap;
    public static final Map<String, String> queryCategoryToDisplayNameMap;

    static {
        displayToQueryCategoryNameMap = Map.of(
                "Job Hunting", "jobHunting",
                "Housing", "housing",
                "Financial Aid", "financialAid",
                "Clubs and Decals", "clubsAndDecals",
                "Enrollment", "enrollment",
                "Class Planning", "classPlanning"
                );

        queryCategoryToDisplayNameMap = Map.of(
                "jobHunting", "Job Hunting",
                "housing", "Housing",
                "financialAid", "Financial Aid",
                "clubsAndDecals", "Clubs and Decals",
                "enrollment", "Enrollment",
                "classPlanning", "Class Planning"
                );

        cycleColors = new String[]{"#A5978E", "#8D5B4C", "#503F3E"};
    }




}
