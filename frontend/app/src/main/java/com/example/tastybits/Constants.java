package com.example.tastybits;

import java.util.Map;

public class Constants {

    public static final Map<String, String> displayToQueryCategoryNameMap;
    public static final Map<String, String> queryCategoryToDisplayNameMap;
    public static final Map<String, String> queryCategoryToMarkdownArticle;

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



        queryCategoryToMarkdownArticle = Map.of(
                "jobHunting", "![Job Hunting Banner](https://berkeleysquareit.com/wp-content/uploads/2019/06/people-shaking-hands-640x350.jpg)\n" +
                        "\n" +
                        "# Job Hunting\n" +
                        "\n" +
                        "## International Students Strategies\n" +
                        "\n" +
                        "- Expand Your Network and Make it Work for You\n" +
                        "\n" +
                        "Networking is one of the most effective ways people find out about positions and get hired.\n" +
                        "\n" +
                        "- Refine Your Communication and Interpersonal Skills\n" +
                        "\n" +
                        "Strong English language skills, non-verbal communication skills and interpersonal skills are all crucial for international students who wish to work and succeed in the US, and right now is the best time to polish those skills.\n" +
                        "\n" +
                        "- Research International Employee Friendly Employers\n" +
                        "\n" +
                        "Be intentional about your job or internship search by researching which companies have hired international candidates in the past.\n" +
                        "\n" +
                        "- US Style Application Documents: Resume & Cover Letter\n" +
                        "\n" +
                        "Make sure you know how to write a US style resume and cover letter and seek feedback from native speakers of English and Career Counselors at the Career Center.\n" +
                        "\n" +
                        "- Become Confident with Interviewing\n" +
                        "\n" +
                        "Review the Interviewing for a US employer page to explore Career Center resources on interviewing and specific tips for international students.\n" +
                        "\n" +
                        "- Review these international student-specific job search resources\n" +
                        "\n" +
                        "[International Student Job Search](http://www.internationalstudent.com/jobsearch/)\n" +
                        "\n" +
                        "[UNIWORLD: Directories of American Firms Operating in Foreign Countries and Foreign Firms Operating in the U.S.](https://uniworldonline.com/)\n" +
                        "\n" +
                        "- Have a Plan B\n" +
                        "\n" +
                        "Expand your pool of opportunities by applying for positions in the US and in your home country (or perhaps a third country too). Explore where your education and experiences are in demand and be open to options you previously hadn’t considered!\n" +
                        "\n" +
                        "- Understand Your Off-Campus Work Authorization and Employment Visa Options\n" +
                        "\n" +
                        "It is essential for international students to understand when and how they can start working off campus. Students need to be able to articulate their student visa work authorization options and their employment visa options to employers, as not all U.S. employers are experienced in hiring international candidates. If you have detailed questions on student visa work authorization options, please consult with a Berkeley International Office (BIO) advisor during drop-in advising hours.\n" +
                        "\n" +
                        "- Get Involved\n" +
                        "\n" +
                        "It will be difficult to get a job or internship through strong grades alone. US employers value students with extracurricular activities, related hands-on experience, and leadership experience.\n" +
                        "\n" +
                        "- Review the US Jobs and Internships for International Student\n" +
                        "\n" +
                        "This excerpt from the Career Center Job & Internship Guide (JIG) includes job search strategies, advice about US resumes and interviewing, permits and visas, the long distance job search, and more\n" +
                        "\n" +
                        "## Handshake\n" +
                        "\n" +
                        "Handshake is the college career network of the future, built to transform the recruiting experience for college students, career centers and employers.\n" +
                        "\n" +
                        "## LinkedIn\n" +
                        "\n" +
                        "LinkedIn is the world's largest professional network on the internet. You can use LinkedIn to find the right job or internship, connect and strengthen professional relationships, and learn the skills you need to succeed in your career.\n" +
                        "\n" +
                        "## Indeed\n" +
                        "\n" +
                        "Indeed is the #1 job site in the world1 with over 250 million unique visitors2 every month. Indeed strives to put job seekers first, giving them free access to search for jobs, post resumes, and research companies. Every day, we connect millions of people to new opportunities.\n" +
                        "\n" +
                        "## Glassdoor\n" +
                        "\n" +
                        "Glassdoor is a website where current and former employees anonymously review companies. Glassdoor also allows users to anonymously submit and view salaries as well as search and apply for jobs on its platform.\n",
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
