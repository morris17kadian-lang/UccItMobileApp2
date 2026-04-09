package com.ucc.itmobileapp.data

object DefaultCourses {
    fun all(): List<Course> {
        return listOf(
            Course(
                code = "ITT101",
                name = "Computer and Information Systems",
                credits = 3,
                prerequisites = "None",
                description = "This course provides an overview of information systems and " +
                    "technology components while offering hands-on experience with " +
                    "productivity tools to analyze and solve business problems."
            ),
            Course(
                code = "ITT103",
                name = "Programming Techniques",
                credits = 3,
                prerequisites = "ITT101",
                description = "This course provides an overview of information systems and " +
                    "technology components while offering hands-on experience with " +
                    "productivity tools to analyze and solve business problems."
            ),
            Course(
                code = "ITT116",
                name = "Computer Essentials and Troubleshooting I",
                credits = 3,
                prerequisites = "ITT101",
                description = "This course provides hands-on experience in diagnosing and " +
                    "resolving common hardware and software issues encountered during " +
                    "regular computer usage."
            ),
            Course(
                code = "ITT200",
                name = "Object Oriented Programming Using C++",
                credits = 3,
                prerequisites = "ITT103",
                description = "This course expands on object-oriented programming concepts " +
                    "and features, requiring students to apply them in designing " +
                    "solutions for real-world problems."
            ),
            Course(
                code = "ITT203",
                name = "Data Structures & File Management I",
                credits = 3,
                prerequisites = "ITT200",
                description = "This course provides the theoretical framework for data " +
                    "structures and file management, introducing students to how data is " +
                    "organized, manipulated, and stored for advanced practical study."
            ),
            Course(
                code = "ITT208",
                name = "Internet Authoring I",
                credits = 3,
                prerequisites = "ITT103",
                description = "This introductory course teaches the design, development, and " +
                    "publication of websites using professional internet authoring tools."
            ),
            Course(
                code = "ITT211",
                name = "Computer Data Analysis",
                credits = 3,
                prerequisites = "None",
                description = "This course teaches the fundamental methods for collecting, " +
                    "processing, and visualizing data using statistical and " +
                    "computational tools to support informed business decision-making."
            ),
            Course(
                code = "ITT302",
                name = "Operating Systems",
                credits = 3,
                prerequisites = "None",
                description = "This course covers the fundamentals of operating system design " +
                    "and implementation, focusing on core components such as process " +
                    "scheduling, memory management, and file systems."
            ),
            Course(
                code = "ITT310",
                name = "Systems Analysis and Design",
                credits = 3,
                prerequisites = "ITT210",
                description = "This course explores the full Systems Development Life Cycle " +
                    "(SDLC) through advanced theories and practical case study " +
                    "simulations, preparing students to design, implement, and maintain " +
                    "complex information systems."
            ),
            Course(
                code = "ITT420",
                name = "Mobile Application Development",
                credits = 3,
                prerequisites = "None",
                description = "This course teaches the core skills for developing Android " +
                    "applications, focusing on user interface design, memory management, " +
                    "and network techniques for retrieving and storing data across " +
                    "platforms."
            )
        )
    }
}