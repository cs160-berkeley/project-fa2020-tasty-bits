package com.example.tastybits;

import java.util.Map;

public class Constants {

    public static final Map<String, String> displayToQueryCategoryNameMap;
    static {
        displayToQueryCategoryNameMap = Map.of(
                "Job Hunting", "jobHunting",
                "Housing", "housing",
                "Financial Aid", "financialAid",
                "Clubs and Decals", "clubsAndDecals",
                "Enrollment", "enrollment",
                "Class Planning", "classPlanning");
    }
}
