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
                        "Section under construction\n",
                "classPlanning", "![Class Planning Banner](https://nature.berkeley.edu/sites/default/files/Advising_banner.jpg)\n" +
                        "\n" +
                        "# Class Planning\n" +
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
                "housing", "Housing",
                "financialAid", "Financial Aid",
                "clubsAndDecals", "Clubs and Decals",
                "enrollment", "Enrollment",
                "internationalResources", "International Student Resources"
        );



    }
}
