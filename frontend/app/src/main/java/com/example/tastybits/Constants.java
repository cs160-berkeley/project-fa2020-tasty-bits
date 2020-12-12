package com.example.tastybits;

import android.app.Activity;

import java.util.Map;

public class Constants {

    public static final String TAG_APP_ID = "com.example.tastybits";

    public static final Map<String, String> displayToQueryCategoryNameMap;
    public static final Map<String, String> queryCategoryToDisplayNameMap;
    public static final Map<String, String> queryCategoryToMarkdownArticle;
    public static final Map<String, Integer> queryCategoryToIconInteger;
    public static final Map<String, String> querySentimentToDisplaySentiment;
    public static final Map<String, String> displaySentimentToQuerySentiment;

    static {
        querySentimentToDisplaySentiment = Map.of(
                "","",
                "VERY_ANGRY", "🤬 Your post is currently very angry. Please make it more positive.",
                "ANGRY", "😠 Your post is currently somewhat angry. Please make it more positive.",
                "FRUSTRATED", "👍 Your post seems good to go!",
                "NO_OPINION", "👍 Your post is good to go!",
                "SATISFIED", "👍 Your post is definitely good to go!",
                "HAPPY", "😊 We appreciate your positive vibes a lot!",
                "VERY_HAPPY", "🤗 We really do appreciate the insane positivity! Much love."
        );

        displaySentimentToQuerySentiment = Map.of(
                "","",
                "🤬 Your post is currently very angry. Please make it more positive.","VERY_ANGRY",
                "😠 Your post is currently somewhat angry. Please make it more positive.","ANGRY",
                 "👍 Your post seems good to go!","FRUSTRATED",
                "👍 Your post is good to go!","NO_OPINION",
                 "👍 Your post is definitely good to go!","SATISFIED",
                "😊 We appreciate your positive vibes a lot!","HAPPY",
                "🤗 We really do appreciate the insane positivity! Much love.","VERY_HAPPY"
                );

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

        queryCategoryToIconInteger = Map.of(
                "jobHunting", R.drawable.job_hunting_icon,
                "housing", R.drawable.housing_icon,
                "clubsAndDecals", R.drawable.clubs_and_decals_icon,
                "financialAid", R.drawable.financial_aid_icon,
                "enrollment", R.drawable.enrollment_icon,
                "classPlanning", R.drawable.class_planning_icon,
                "internationalResources", R.drawable.international_icon,
                "transferResources", R.drawable.transfer_icon,
                "firstGenResources", R.drawable.first_gen_icon
        );


        queryCategoryToMarkdownArticle = Map.of(
                "classPlanning", "![Class Planning Banner](https://nature.berkeley.edu/sites/default/files/Advising_banner.jpg)\n" +
                        "\n" +
                        "# Class Planning\n" +
                        "\n" +
                        "## Overview\n" +
                        "\n" +
                        "Class planning requires a cumulative view of what classes you need to take in order to fufill the requirements for your major or minor. Typically a google search for your \"UC Berkeley [your major] [your department] worksheet\" will land you on a helpful worksheet that you can use to plan out what your major requirements are.\n" +
                        "\n" +
                        "Furthermore, helpful requirement fufillment information and planning tools are available on the \"Degree Requirements\" of the [Academics page of CalCentral](https://calcentral.berkeley.edu/academics). You can use the \"Resources\" section in this guide to help you plan your classes after you have checked\n" +
                        "\n" +
                        "## Resources\n" +
                        "\n" +
                        "- [BerkeleyTime](https://berkeleytime.com/catalog)\n" +
                        "\n" +
                        "  - Has all the classes offered at Cal with filters and sorting, grade distributions of past semesters for given classes, and up to date enrollment percentage information.\n" +
                        "\n" +
                        "- [Berkeley Academic Guide](https://classes.berkeley.edu/)\n" +
                        "\n" +
                        "  - The official UC Berkeley site for searching for classes to take.\n" +
                        "\n" +
                        "- [CalCentral Academics Page](https://calcentral.berkeley.edu/academics)\n" +
                        "\n" +
                        "  - Go to place for actually enrolling in classes, viewing your enrollment timeslot, planning out classes to fufill graduation requirements under \"Degree Requirements\", planning your schedule of classes under \"Schedule Planner\", viewing an overview of your enrolled classes and their timeslots for finals week.\n" +
                        "\n" +
                        "- [Rate My Professor](https://www.ratemyprofessors.com/)\n" +
                        "  - Take a quick glance between professor ratings if the class you are planning to take is offered during different semesters under different professors.\n" +
                        "\n" +
                        "## Transfer Student Tips\n" +
                        "\n" +
                        "- **Degree Requirements**\n" +
                        "\n" +
                        "Review the Degree Requirements page to get a sense of all of the general education and unit requirements you will need.\n" +
                        "\n" +
                        "- **Initial Transfer Credit Review**\n" +
                        "\n" +
                        "If you do not have seven-course breadth satisfied, that can be a good place to start to round out your first semester schedule. Since your Initial Transfer Credit Review will not yet be completed, see if there are any breadth requirements you are confident you have not yet satisfied with previous coursework (or use [assist.org](https://assist.org) if you took California Community College coursework but did not satisfy IGETC).\n" +
                        "\n" +
                        "- **General Education Requirements**\n" +
                        "\n" +
                        "If you satisfied IGETC or UC Reciprocity, you may still have the following general education requirements remaining, which could be planned into your first semester schedule: `American History`, `American Institutions`, `American Cultures`\n" +
                        "\n" +
                        "- **Next Step**\n" +
                        "\n" +
                        "If you have completed all general education requirements, you can work on elective units. You may wish to start making progress toward the degree requirement of needing 6 upper division units outside of your major.\n" +
                        "\n" +
                        "## Golden Bear Advising\n" +
                        "\n" +
                        "- **Golden Bear Advising**\n" +
                        "\n" +
                        "Golden Bear Advising(link is external) is mandatory, self-paced, online course that introduces you to academic resources and your college. Through Golden Bear Advising, you will learn how to build your first semester schedule and prepare to enroll in classes. The course includes:\n" +
                        "communication with an Academic Adviser,\n" +
                        "overview of academic resources, and graduation requirements.\n" +
                        "\n" +
                        "- **Incoming Transfer Advising**\n" +
                        "\n" +
                        "Once GBA begins for transfers, you will have an opportunity to connect with your intended major's Undergraduate Major Adviser. This adviser will help you assess which courses you should take in Fall based on your progress toward the major and make sure you understand how to declare your major when you are ready.\n" +
                        "\n" +
                        "- **All Incoming Students**\n" +
                        "\n" +
                        "The academic adviser you work with during GBA is different than your Admissions Officer, who you can find through your MAP@berkeley(link is external) Admissions portal. Your L&S College or Undergraduate Major Adviser will focus on your academic questions related to starting at Cal. Your Admissions Officer will answer questions about your new student checklist or questions related to your admission.\n" +
                        "\n" +
                        "# Creating a Program Plan\n" +
                        "\n" +
                        "- **Create a Long-Term Program Plan**\n" +
                        "\n" +
                        "The Create a Long-Term Program Plan page offers an overview of whether you need to do this work now and provides step-by-step guidance. You can also find ideas for exploring outside-of-the-classroom opportunities including study abroad, internships, and research under \"Helpful Links for Program Planning\" on this page.\n" +
                        "\n" +
                        "- **Majors & Minors**\n" +
                        "\n" +
                        "The Majors & Minors section includes several pages that will help you learn more about declaring or changing majors, adding double majors and simultaneous degrees, minors, and more.\n" +
                        "\n" +
                        "- **Unit Ceiling and Semester Limit**\n" +
                        "\n" +
                        "The Unit Ceiling and Semester Limit will help you understand the time allowed for degree completion.\n",
                "clubsAndDecals", "![Clubs and Decals Banner](https://chemistry.berkeley.edu/sites/default/files/styles/panopoly_image_original/public/student-organizations-sproul-900x521.jpg?itok=vuCBCxgj&timestamp=1426199113)\n" +
                        "\n" +
                        "## Decals\n" +
                        "\n" +
                        "Decals are student run courses that count for units. They are a great way to explore an enourmous variety of unique topics not typically offered in an academic setting, and get academic credit for it. Be on the lookout for unique classes you might be interested in every semester, as they are not permanent and change every semester. Decals are typically posted between one week before classes up until two weeks after classes have started.\n" +
                        "\n" +
                        "[Here's a good online resource for checking which decals are available for your semester.](https://decal.berkeley.edu/courses)\n" +
                        "\n" +
                        "## Clubs\n" +
                        "\n" +
                        "There are over 1300+ clubs offered at Cal with an enormous variety including clubs in STEM, Politics, Religion, Business, Professional, Greek Life, Cultural, LGBTQ+, Arts, Recreation, and many more.\n" +
                        "\n" +
                        "[Here's a good online resource for scoping out available clubs and filterable by category.](https://decal.berkeley.edu/courses)\n" +
                        "\n" +
                        "## Picks for International Students\n" +
                        "\n" +
                        "You can find more details about these clubs through the links.\n" +
                        "\n" +
                        "- [International Students Association at Berkeley (ISAB)](https://callink.berkeley.edu/organization/isab)\n" +
                        "- [Armenian Student Association (ASA)](https://callink.berkeley.edu/organization/armenianstudentsassociation)\n" +
                        "- [Taiwanese Student Association (TSA)](https://callink.berkeley.edu/organization/taiwanesestudentassociation)\n" +
                        "- [Berkeley Chinese Students and Scholars Association (BCSSA)](https://callink.berkeley.edu/organization/bcssa)\n" +
                        "- [Berkeley Indonesian Student Association (BISA)](https://callink.berkeley.edu/organization/bisa)\n" +
                        "- [Cal Japan Club (CJC)](https://callink.berkeley.edu/organization/caljapanclub)\n" +
                        "- [Hong Kong Student Association (HKSA)](https://callink.berkeley.edu/organization/hongkongstudentassociation)\n" +
                        "- [Indian Students Association(ISA)](https://callink.berkeley.edu/organization/ISA)\n" +
                        "- [Korean Undergraduate Networking Association (KUNA)](https://callink.berkeley.edu/organization/KUNA)\n" +
                        "- [Latin American Leadership Society (LLS)](https://callink.berkeley.edu/organization/LLS)\n" +
                        "- [Vietnamese Student Association (VSA)](https://callink.berkeley.edu/organization/calvsa)\n" +
                        "\n" +
                        "## Picks for First Generation Students\n" +
                        "\n" +
                        "- [EOP Student Association (EOP)](https://callink.berkeley.edu/organization/eopsa)\n" +
                        "- [First Generation Professionals (FGP)](https://callink.berkeley.edu/organization/fgp)\n" +
                        "- [First-Gen and/or Low-Income Graduate Students (FGLI Grads)](https://callink.berkeley.edu/organization/fgli_grads)\n" +
                        "- [Coalition for Diversity at Berkeley Law (CFD)](https://callink.berkeley.edu/organization/cfd)\n" +
                        "\n" +
                        "## Picks for Transfer Students\n" +
                        "\n" +
                        "- [Re-entry and Transfer Student Association (RTSA)](https://callink.berkeley.edu/organization/berkeleyrtsa)\n" +
                        "- [Mechanical Engineering Transfer Student Union (METSU)](https://callink.berkeley.edu/organization/metsu)\n",
                "enrollment", "![Enrollment Banner](https://www.eteknix.com/wp-content/uploads/2017/03/UC_Berkeley_processed_color_corrected-117.jpg)\n" +
                        "\n" +
                        "# Enrollment\n" +
                        "\n" +
                        "## Overview\n" +
                        "\n" +
                        "Enrollment occurs during an assigned period where students can enroll for classes on [CalCentral Academics Page](https://calcentral.berkeley.edu/academics). Each student is assigned an anrollment time for Phase 1, Phase 2, and Adjustment Period. During these phases, students can enroll in a specified amount of units (i.e. typically 10.5-13.5 units for Phase 1 depending on your major's department). freshman are given the last enrollment times, while seniors are given more priority enrollment times for first pick on classes.\n" +
                        "\n" +
                        "## Terms and Tips\n" +
                        "\n" +
                        "- Classes count for between 1-4 \"units\". They are basically a way to measure how much of a workload the class takes, where more units is higher workload. During Phase 1, and Phase 2, you are limited to a cap on the amount \"units\" you can enroll in (the sum of the units of the classes you'd like to enroll in).\n" +
                        "\n" +
                        "- Classes are identified by a number called a \"CCN\" (typically a five digit number). You can use this number to lookup the exact class you found on a class planner.\n" +
                        "\n" +
                        "- There are three states to a class on CalCentral enrollment: \"Open\", \"Waitlist\", and \"Closed\".\n" +
                        "\n" +
                        "- \"Open\" means that there are available seats. Sometimes trying to enroll in \"Open\" classes will fail, refer to the \"Actually Enrolling\" section in this guide.\n" +
                        "\n" +
                        "- \"Waitlist\" means that the class is at capacity, and you will be placed on a first come first serve queue of students to try to enroll in the class. If enrolled students drop the class, people from the waitlist will join in the order from the queue. If you are typically placed in a position >10% of the size of the class, you might consider trying to enroll in alternative classes.\n" +
                        "\n" +
                        "- \"Closed\" means you cannot enroll in the class anymore.\n" +
                        "\n" +
                        "- Classes often come as a bundle of a lecture section, discussion section, and sometimes a lab section. They repeat at some time on certain days of the week.\n" +
                        "\n" +
                        "- Sometimes the lecture section for a class is open, but the discussion sections, or labs are all waitlisted. Usually the professor decides that students waitlisted on a lab or discussion will not be able to join a class, but this is up to them.\n" +
                        "\n" +
                        "- Sometimes large classes expand to accomodate more students. Google around to check for ways to get updates about these sort of things.\n" +
                        "\n" +
                        "- Use your Phase 1 units (the very first period you can enroll in classes) for impacted classes that fufill major requirements. These classes fill up quickly.\n" +
                        "\n" +
                        "- Do not miss your enrollment time\n" +
                        "\n" +
                        "- Make sure lecture, discussion, and lab is factored into your schedule or you might not be able to enroll because of a scheduling conflict (i.e. overlapping time commitments).\n" +
                        "\n" +
                        "- Sometimes enrollment will fail because a class has met their reserved seating capacity for your major. For example lets say English 29A has 200 available spots. However, it has 190 reserved seats for English majors, and 10 spots for other majors. This means only 10 people from other majors will be able to enroll, but the class will still display \"Open\"\n" +
                        "\n" +
                        "- Rarely (but it happens), instructors will place enrollment restrictions on classes that will allow them to accept students on a case by case basis through a google forms application that you must submit to be considered. The class will display as \"Open\" but reject you from enrollment.\n" +
                        "\n" +
                        "- Classes are sometimes offered as only \"Pass, No pass (P/NP)\" or option to switch to (P/NP). Basically this means that if you change your grading option to P/NP and get a C or above, the class you take will result in a P on your transcript. The class may or may not fufill the requirement you took it for if you take it P/NP, you need to check in with major requirements. Furthermore P/NP classes do not count towards your GPA.\n" +
                        "\n" +
                        "## Actually enrolling\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "- **Enrolling in a Class**\n" +
                        "\n" +
                        "  - Phase 1\n" +
                        "  - Phase 2\n" +
                        "  - Adjustment Period\n" +
                        "\n" +
                        "- **Dropping a Class**\n" +
                        "\n" +
                        "- **Swapping a Class**\n" +
                        "\n" +
                        "- **Swapping a Class**\n" +
                        "\n" +
                        "## Withdrawl and Special Circumstances\n" +
                        "\n" +
                        "Section under construction\n",
                "financialAid", "![Financial Aid Banner](https://lacomadre.org/wp-content/uploads/2018/08/8.22-10am.jpg)\n" +
                        "\n" +
                        "# Financial Aid\n" +
                        "\n" +
                        "## Overview\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "## Resources\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "## Scholarships, Grants, Prizes, Loans\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "## Work-Study\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "## Disbursment\n" +
                        "\n" +
                        "Section under construction\n",
                "firstGenResources", "![First Gen Students Banner](https://www.universityofcalifornia.edu/sites/default/files/code-first-gen-uc-irvine.png)\n" +
                        "\n" +
                        "## Scholarships\n" +
                        "\n" +
                        "- **Berkeley Law Opportunity Scholarship**\n" +
                        "\n" +
                        "The Berkeley Law Opportunity Scholarship (BLOS) is a 3 year, full tuition scholarship for first generation college graduates. The Berkeley Law Opportunity Scholarship provides recipients with funding to cover tuition and fees (including Student Health Insurance, materials fees, etc.) for three years (six semesters) of study at Berkeley Law.\n" +
                        "\n" +
                        "- **Fiat Lux Scholarship**\n" +
                        "\n" +
                        "The Fiat Lux Scholarship creates opportunities for resilient students with unique life experiences to make a profound impact at the University of California, Berkeley. Established for high-achieving students from partner high schools in California, the scholarship provides monetary, community, and academic support to underrepresented and first-generation college students.\n" +
                        "\n" +
                        "- **Berkeley Community Scholarship**\n" +
                        "\n" +
                        "Since 2008, Berkeley Community Scholarship has awarded 303 scholarships totaling more than $4 million to Berkeley High School students attending colleges and universities throughout California and across the United States. Students work with our college success advisors and a community mentor to help them navigate the demands and challenges of college.\\\\nBerkeley Community scholars represent the many and varied ethnic and racial groups in Berkeley. Over 80% are the first generation in their family to attend college. Many have/are overcoming obstacles, barriers, and inequities.\\\\nDespite these challenges, they excel in school and are often leaders in the community. Berkeley Community scholars show great promise and have an overwhelming sense of hope for the future. They inspire all of us to reach beyond our circumstances no matter how big or small striving to make a difference.\n" +
                        "\n" +
                        "## Academics\n" +
                        "\n" +
                        "- **Academic Centers**\n" +
                        "\n" +
                        "Whether you would like extra help with coursework, need to talk to someone about choosing a major, or just want a place to study with your friends, the Academic Centers located in the residence halls are the place to go.\n" +
                        "\n" +
                        "- **Berkeley Connect**\n" +
                        "\n" +
                        "Matches you with a graduate student mentor and connects you with professors, alumni, and students sharing similar interests.\n" +
                        "\n" +
                        "- **The College of Engineering’s First-generation Mentor Program**\n" +
                        "\n" +
                        "The College of Engineering’s First-generation Mentor Program aims to mitigate the challenges often faced by first gen students in this a year-long, two tiered program where first-generation upper-division engineering students mentor first-generation lower-division engineering students and alumni, faculty, and graduate students mentor first-generation upper-division engineering students.\n" +
                        "\n" +
                        "- **Biology Scholars, Chemistry Scholars, and Data Scholars**\n" +
                        "\n" +
                        "Advising, mentorship, academic support, and more for underrepresented students in specific fields.\n" +
                        "\n" +
                        "- **Cal NERDS (New Experiences for Research and Diversity in Science)**\n" +
                        "\n" +
                        "Works with nontraditional STEM students to provide opportunities in leadership development, undergraduate research, tech training, preparation for grad school, career planning, and more.\n" +
                        "\n" +
                        "- **EOP (Educational Opportunity Program)**\n" +
                        "\n" +
                        "Academic counseling, mentoring, and a support system for first-generation, low-income, and underrepresented students.\n" +
                        "\n" +
                        "- **Student Learning Center**\n" +
                        "\n" +
                        "Academic consultations, tutoring, workshops, study groups, and more. Available to all students.\n" +
                        "\n" +
                        "- **Undocumented Student Program**\n" +
                        "\n" +
                        "Guidance and support to undocumented undergraduates at Cal.\n" +
                        "\n" +
                        "## Student Life and Community\n" +
                        "\n" +
                        "- **Dean of Students Office**\n" +
                        "\n" +
                        "A hub office that advocates for the needs of students. They help community members navigate UC Berkeley, promote diversity, and prepare students to contribute to a changing world.The office also aims to support student health and well-being by facilitating belonging, community, and leadership development. Above all, they foster compassion and care.\n" +
                        "\n" +
                        "- **Bears For Financial Success**\n" +
                        "\n" +
                        "For most college students, money can be an overwhelming and stressful topic. This peer-to-peer financial wellness program offers workshops and one-on-one appointments to make sure students understand the basics of financial literacy and have the knowledge and tools to manage their personal finances in college and beyond.\n" +
                        "\n" +
                        "- **Financial Literacy and Economic Justice Conference**\n" +
                        "\n" +
                        "The Financial Literacy and Economic Justice Conference is an annual, student-organized event drawing together faculty, administrative offices, community partners and student leaders in facilitating workshops on financial literacy and economic justice to the greater student body. Workshop topics include filing taxes, planning personal budget, saving for retirement, and more.\n" +
                        "\n" +
                        "- **LEAD Center**\n" +
                        "\n" +
                        "The LEAD (Leadership, Engagement, Advising, & Development) Center is UC Berkeley’s hub for student involvement, leadership development, and co-curricular advising.\n" +
                        "\n" +
                        "- **Residential Theme Programs**\n" +
                        "\n" +
                        "Close-knit living and learning communities of students sharing a common thematic and academic interest.\n" +
                        "\n" +
                        "- **Student Clubs & Organizations**\n" +
                        "\n" +
                        "Join one of 1,200 student clubs and organizations on campus. From the B-Side music magazine to the Cal Quidditch Team, from the Latinx Pre-Law Society to Virtual Reality at Berkeley, Berkeley has it all.\n",
                "housing", "![Housing Banner](https://housing.berkeley.edu/sites/default/files/images/Unit-1.jpg)\n" +
                        "\n" +
                        "# Housing\n" +
                        "\n" +
                        "## Resources\n" +
                        "\n" +
                        "- [Residential life](https://reslife.berkeley.edu/)\n" +
                        "- [Cal housing office](https://housing.berkeley.edu/)\n" +
                        "- [Campus housing rates](https://housing.berkeley.edu/rates)\n" +
                        "- Facebook Groups\n" +
                        "  - [Free and For Sale](https://www.facebook.com/groups/BerkeleyFreeAndForSale/)\n" +
                        "  - [Housing](https://www.facebook.com/groups/266259923468888/)\n" +
                        "  - [UC Berkeley Off Campus Housing](https://www.facebook.com/groups/ucberkeleyoffcampushousing/)\n" +
                        "- [Berkeley student cooperative (BSC or Co-ops)](https://www.bsc.coop/)\n" +
                        "- [Cal rentals](https://och.berkeley.edu/)\n" +
                        "- [Cragilist](https://sfbay.craigslist.org/)\n" +
                        "- [GA’s housing guide](https://ga.berkeley.edu/resources/housing-guide/)\n" +
                        "\n" +
                        "## Price Range\n" +
                        "\n" +
                        "At the time of writing, the average pricing on facebook groups for off-campus student housing are:\n" +
                        "\n" +
                        "- $500-$900 for a triple about 1-8 blocks away from campus\n" +
                        "- $600-$1200 for a double about 1-8 blocks away from campus\n" +
                        "- $750-$1500 for a single about 1-8 blocks away from campus\n" +
                        "- $900-$2500 for a single studio about 1-8 blocks away from campus\n" +
                        "\n" +
                        "Pricings for dorms and campus housing can be found on their housing page, found in the \"Campus housing rates\" resouce above.\n" +
                        "\n" +
                        "In 2016, the average rent for apartments is estimated by RentCafe at:\n" +
                        "\n" +
                        "- Studio apartments: $2,004 per month\n" +
                        "- 1-bedroom apartments: $2,796 a month\n" +
                        "- 2-bedroom apartment: $3,526 per month\n" +
                        "\n" +
                        "Please be advised that Cal Rentals does receive advertisements for rentals with lower rents than shown above, but house-hunters should not expect to encounter great numbers of drastically lower rents.\n" +
                        "\n" +
                        "Although there has been new construction in Berkeley over the past few years to match the demand for housing, rents for rooms and apartments in these new buildings tend to be significantly above average.\n" +
                        "\n" +
                        "You can expect considerable competition for any well-priced units that are near to campus.\n" +
                        "\n" +
                        "## Safety\n" +
                        "\n" +
                        "Berkely Crime Heatmap\n" +
                        "![Berkely Crime Heatmap (red is more dangerous)](https://miro.medium.com/max/1120/1*UqPthQyUJ-u5zRBZpUMBvA.png)\n" +
                        "\n" +
                        "- **Tips**\n" +
                        "\n" +
                        "- Places to generally avoid:\n" +
                        "\n" +
                        "  - Housing near People's Park\n" +
                        "  - Alone at night in Downtown Berkeley\n" +
                        "\n" +
                        "- Don't walk alone at night! Use these free UCB Police services:\n" +
                        "\n" +
                        "  - BearWalk (campus safety escort)\n" +
                        "  - Night Safety Shuttle (campus bus service)\n" +
                        "  - Door-to-Door Service (a ride to locations near campus)\n" +
                        "\n" +
                        "- Save emergency numbers in your phone:\n" +
                        "\n" +
                        "  - Off-campus: call 911\n" +
                        "  - UCB Police: 510-642-3333 (faster than 911 while on-campus)\n" +
                        "\n" +
                        "- When meeting strangers in person:\n" +
                        "\n" +
                        "  - Insist on a public meeting place like a cafe.\n" +
                        "  - Don't invite strangers into your home.\n" +
                        "  - Consider having a friend accompany you.\n" +
                        "  - Don't meet in a secluded place.\n" +
                        "  - Be especially careful when buying/selling high value items.\n" +
                        "  - Tell a friend or family member where you're going.\n" +
                        "  - Take your cell phone along if you have one.\n" +
                        "  - Trust your instincts.\n" +
                        "\n" +
                        "- Refer to emails from UCPD for day-to-day crimes and areas to avoid!\n" +
                        "\n" +
                        "  - UCPD Number (310) 825-1491\n" +
                        "\n" +
                        "- **Blue Light Telephones On Campus**\n" +
                        "\n" +
                        "- Overview of Emergency “Blue Light” Telephones\n" +
                        "\n" +
                        "  - Emergency “Blue Light” telephones allow you to connect with UC Police Department call center without much effort. When you see a “Blue Light” telephone, you know you are only one button away from UCPD.\n" +
                        "\n" +
                        "- What does Emergency “Blue Light” Telephones look like?\n" +
                        "\n" +
                        "  - Emergency telephones can be identified by a blue light. Freestanding phone systems, in tall dark columns or yellow phone boxes, are topped with a blue light which remains lit at all times.\n" +
                        "\n" +
                        "- How do they work?\n" +
                        "\n" +
                        "  - Pushing the red button on the panel will automatically connect to the UC Police Department call center. The dispatcher will see the location of the caller.\n" +
                        "\n" +
                        "- Where are they?\n" +
                        "  - Emergency phones are installed throughout the campus.\n",
                "internationalResources", "![International Students Banner](https://i2.wp.com/www.dailycal.org/assets/uploads/2014/07/ihouse.jpg?ssl=1)\n" +
                        "\n" +
                        "# International Students Resources\n" +
                        "\n" +
                        "## Berkeley International Office\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "[BIO Social and Cultural Events](https://events.berkeley.edu/index.php/calendar/sn/bio.html)\n" +
                        "\n" +
                        "## BIO Social and Cultural Events\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "## F-1 international students\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "- **On-Campus Work Authorization**\n" +
                        "\n" +
                        "- **Curricular Practical Training (CPT)**\n" +
                        "\n" +
                        "- **Optional Practical Training (OPT)**\n" +
                        "\n" +
                        "- **STEM OPT Extension**\n" +
                        "\n" +
                        "- **Cap-Gap Extension**\n" +
                        "\n" +
                        "## J-1 international students\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "- **On-Campus Work Authorization**\n" +
                        "\n" +
                        "- **Academic Training (AT)**\n" +
                        "\n" +
                        "## Visa, SSN, Taxes, Work Webinars\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "- **F-1 and J-1 work authorization workshops and webinars**\n" +
                        "\n" +
                        "- **H-1B Employment Visa Workshops**\n" +
                        "\n" +
                        "- **Social Security Number**\n" +
                        "\n" +
                        "- **Taxes in the US**\n" +
                        "\n" +
                        "## English Language Resources\n" +
                        "\n" +
                        "Section under construction\n",
                "jobHunting", "![Job Hunting Banner](https://berkeleysquareit.com/wp-content/uploads/2019/06/people-shaking-hands-640x350.jpg)\n" +
                        "\n" +
                        "# Job Hunting\n" +
                        "\n" +
                        "## International Students Strategies\n" +
                        "\n" +
                        "- **Expand Your Network and Make it Work for You**\n" +
                        "\n" +
                        "Networking is one of the most effective ways people find out about positions and get hired.\n" +
                        "\n" +
                        "- **Refine Your Communication and Interpersonal Skills**\n" +
                        "\n" +
                        "Strong English language skills, non-verbal communication skills and interpersonal skills are all crucial for international students who wish to work and succeed in the US, and right now is the best time to polish those skills.\n" +
                        "\n" +
                        "- **Research International Employee Friendly Employers**\n" +
                        "\n" +
                        "Be intentional about your job or internship search by researching which companies have hired international candidates in the past.\n" +
                        "\n" +
                        "- **US Style Application Documents: Resume & Cover Letter**\n" +
                        "\n" +
                        "Make sure you know how to write a US style resume and cover letter and seek feedback from native speakers of English and Career Counselors at the Career Center.\n" +
                        "\n" +
                        "- **Become Confident with Interviewing**\n" +
                        "\n" +
                        "Review the Interviewing for a US employer page to explore Career Center resources on interviewing and specific tips for international students.\n" +
                        "\n" +
                        "- **Review these international student-specific job search resources**\n" +
                        "\n" +
                        "[International Student Job Search](http://www.internationalstudent.com/jobsearch/)\n" +
                        "\n" +
                        "[UNIWORLD: Directories of American Firms Operating in Foreign Countries and Foreign Firms Operating in the U.S.](https://uniworldonline.com/)\n" +
                        "\n" +
                        "- **Have a Plan B**\n" +
                        "\n" +
                        "Expand your pool of opportunities by applying for positions in the US and in your home country (or perhaps a third country too). Explore where your education and experiences are in demand and be open to options you previously hadn’t considered!\n" +
                        "\n" +
                        "- **Understand Your Off-Campus Work Authorization and Employment Visa Options**\n" +
                        "\n" +
                        "It is essential for international students to understand when and how they can start working off campus. Students need to be able to articulate their student visa work authorization options and their employment visa options to employers, as not all U.S. employers are experienced in hiring international candidates. If you have detailed questions on student visa work authorization options, please consult with a Berkeley International Office (BIO) advisor during drop-in advising hours.\n" +
                        "\n" +
                        "- **Get Involved**\n" +
                        "\n" +
                        "It will be difficult to get a job or internship through strong grades alone. US employers value students with extracurricular activities, related hands-on experience, and leadership experience.\n" +
                        "\n" +
                        "- **Review the US Jobs and Internships for International Student**\n" +
                        "\n" +
                        "This excerpt from the Career Center Job & Internship Guide (JIG) includes job search strategies, advice about US resumes and interviewing, permits and visas, the long distance job search, and more\n" +
                        "\n" +
                        "## Writing Your Resume\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "## Preparing for interviews\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "- **Online resources for interview preperation**\n" +
                        "\n" +
                        "## Professional Networking and Job Hosting Sites\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "- **[Handshake](https://www.joinhandshake.com/)**\n" +
                        "\n" +
                        "Handshake is the college career network of the future, built to transform the recruiting experience for college students, career centers and employers.\n" +
                        "\n" +
                        "- **[LinkedIn](https://www.linkedin.com/)**\n" +
                        "\n" +
                        "LinkedIn is the world's largest professional network on the internet. You can use LinkedIn to find the right job or internship, connect and strengthen professional relationships, and learn the skills you need to succeed in your career.\n" +
                        "\n" +
                        "- **[Indeed](https://www.indeed.com/)**\n" +
                        "\n" +
                        "Indeed is the #1 job site in the world1 with over 250 million unique visitors2 every month. Indeed strives to put job seekers first, giving them free access to search for jobs, post resumes, and research companies. Every day, we connect millions of people to new opportunities.\n" +
                        "\n" +
                        "- **[Glassdoor](https://www.glassdoor.com/index.htm)**\n" +
                        "\n" +
                        "Glassdoor is a website where current and former employees anonymously review companies. Glassdoor also allows users to anonymously submit and view salaries as well as search and apply for jobs on its platform.\n" +
                        "\n" +
                        "- **[AngelList](https://angel.co/)**\n" +
                        "\n" +
                        "AngelList is a website that primarily hosts internships and job opportunities at startups or small companies. This is a great place to start with no formal experience.\n",
                "transferResources", "![Transfer Students Banner](https://images.squarespace-cdn.com/content/v1/53b34431e4b0988226f28acd/1542186814416-KU2W9QE6YUI5JFE2NXRY/ke17ZwdGBToddI8pDm48kHFOdRWtTaSh_3e3fsHKyDIUqsxRUqqbr1mOJYKfIPR7LoDQ9mXPOjoJoqy81S2I8N_N4V1vUb5AoIIIbLZhVYy7Mythp_T-mtop-vrsUOmeInPi9iDjx9w8K4ZfjXt2diy74WMocCmuRwRgLMcupLUYq5LxKaxUssnPB6mjRtF1JvwGh1qtNWvMhYKnvaKhbA/InstagramFacebookcover.jpg?format=1500w)\n" +
                        "\n" +
                        "# Transfer Student Resources\n" +
                        "\n" +
                        "## Transfer Student Center\n" +
                        "\n" +
                        "The Transfer Student Center provides services to assist students who transfer to Cal from other colleges and universities with navigating the academic and cultural landscape of this research university. Our programs and services focus on supporting a successful transition, helping transfers build connections and community, and assisting students as they explore and pursue their academic and career goals. The center plays a key role in campus outreach and recruitment through yield events and participation in programs involving California Community Colleges. We are located in 100 Cesar Chavez Student Building.\n" +
                        "\n" +
                        "[Berkeley transfer student homepage](https://transfers.berkeley.edu/)\n" +
                        "\n" +
                        "## The Center of Access to Engineering Excellence\n" +
                        "\n" +
                        "The Center for Access to Engineering Excellence (CAEE) is committed to providing a supportive and welcoming environment conducive to academic and personal success. They pride themselves as champions for diversity, equity and inclusion in all of their programs and services.\n" +
                        "\n" +
                        "[For more information visit the CAEE website](https://engineering.berkeley.edu/students/academic-support/)\n" +
                        "\n" +
                        "## Educational Opportunity Program (EOP)\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "## Scholarships for Transfer Students\n" +
                        "\n" +
                        "Section under construction\n" +
                        "\n" +
                        "## Internships for Transfer Students\n" +
                        "\n" +
                        "Section under construction\n"
        );



    }
}
