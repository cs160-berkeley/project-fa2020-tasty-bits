package com.example.tastybits;

import java.util.Map;

public class Constants {
    public static final String AUTH_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlFVZWpaeXFKYXVQOTllcWJXSWp0eiJ9.eyJpc3MiOiJodHRwczovL2NhbHN1cnZpdmFsZ3VpZGUudXMuYXV0aDAuY29tLyIsInN1YiI6ImF1dGgwfDVmYjUxYzgxYzU2YzYyMDA3NmRkZWVlMCIsImF1ZCI6WyJodHRwczovL2NhbHN1cnZpdmFsZ3VpZGUudXMuYXV0aDAuY29tL2FwaS92Mi8iLCJodHRwczovL2NhbHN1cnZpdmFsZ3VpZGUudXMuYXV0aDAuY29tL3VzZXJpbmZvIl0sImlhdCI6MTYwNTg0Mzg2OSwiZXhwIjoxNjA4NDM1ODY5LCJhenAiOiJuNUw5Z1k4elpndXVjMlUxMEdnRm1wclNSemhUbnF3MSIsInNjb3BlIjoib3BlbmlkIn0.BTVTPSsTZqM5w8Va_b-UvEUpYnQXCokkkFKHdWyyOAnYI1jXJ91HoQ1ajwEkK0SZen8xdygrs4uNLmX6TFtYM20ZnklqF0vnW3Rvwtz0bc54DoZK4lVsaa7dLDT4B5D-h4TW-z1HPgCRhEXjCWv0kJq55-Z9wvIMwc9fC2lAHkFImrORqAXKk8CYv67K2uj0nv3v6sg6IPO1JKDyRPn4pGXgVDdNKdQbrDJymiwlXC-AgYC68wb__QOKiygySgXPbCtRlDi2D0BcwEqCzHpIpJay59QH8NVdP0BUMPNVyHKpYjAM-_6phiYpxYp3GECQOifc-R_QINfodzrrsSVoUw";

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
