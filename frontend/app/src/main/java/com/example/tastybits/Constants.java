package com.example.tastybits;

import java.util.Map;

public class Constants {

    public static final Map<String, String> displayToQueryCategoryNameMap;
    public static final Map<String, String> queryCategoryToDisplayNameMap;

    static {
        displayToQueryCategoryNameMap = Map.of(
                "Job Hunting", "jobHunting",
                "Housing", "housing",
                "Financial Aid", "financialAid",
                "Clubs and Decals", "clubsAndDecals",
                "Enrollment", "enrollment",
                "Class Planning", "classPlanning",
                "International Student Resources", "internationalResources",
                "First Generation Student Resources", "firstGenResources",
                "Transfer Student Resources", "transferResources"

                );

        queryCategoryToDisplayNameMap = Map.of(
                "jobHunting", "Job Hunting",
                "housing", "Housing",
                "financialAid", "Financial Aid",
                "clubsAndDecals", "Clubs and Decals",
                "enrollment", "Enrollment",
                "classPlanning", "Class Planning",
                "internationalResources", "International Student Resources",
                "firstGenResources", "First Generation Student Resources",
                "transferResources", "Transfer Student Resources"

        );

    }




}
