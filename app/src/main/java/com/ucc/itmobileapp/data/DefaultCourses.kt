package com.ucc.itmobileapp.data

object DefaultCourses {
    fun all(): List<CourseEntity> {
        return listOf(
            CourseEntity(
                code = "ITT101",
                name = "Introduction to Information Technology",
                credits = 3,
                prerequisites = "None",
                description = "Overview of computing concepts, computer hardware/software, networks, and IT roles in organizations."
            ),
            CourseEntity(
                code = "ITT102",
                name = "Programming Fundamentals",
                credits = 3,
                prerequisites = "None",
                description = "Problem-solving with algorithms; variables, control structures, functions, and basic data structures."
            ),
            CourseEntity(
                code = "ITT103",
                name = "Database Fundamentals",
                credits = 3,
                prerequisites = "None",
                description = "Relational database concepts, ER modeling, normalization, SQL queries, and basic database design."
            ),
            CourseEntity(
                code = "ITT104",
                name = "Computer Networks I",
                credits = 3,
                prerequisites = "ITT101",
                description = "Networking basics including OSI/TCP-IP models, IP addressing, subnetting, switching, and routing concepts."
            ),
            CourseEntity(
                code = "ITT201",
                name = "Object-Oriented Programming",
                credits = 3,
                prerequisites = "ITT102",
                description = "Classes, objects, encapsulation, inheritance, polymorphism, interfaces, and basic design principles."
            ),
            CourseEntity(
                code = "ITT202",
                name = "Web Development",
                credits = 3,
                prerequisites = "ITT102",
                description = "Client-side web development fundamentals: HTML, CSS, basic JavaScript, and responsive design concepts."
            ),
            CourseEntity(
                code = "ITT203",
                name = "Systems Analysis and Design",
                credits = 3,
                prerequisites = "ITT101",
                description = "Requirements gathering, process modeling, use cases, SDLC, and designing information systems solutions."
            ),
            CourseEntity(
                code = "ITT204",
                name = "Data Structures and Algorithms",
                credits = 3,
                prerequisites = "ITT201",
                description = "Lists, stacks, queues, trees, hashing, sorting/searching, and algorithm efficiency analysis."
            ),
            CourseEntity(
                code = "ITT301",
                name = "Mobile Application Development",
                credits = 3,
                prerequisites = "ITT201",
                description = "Principles of mobile UI/UX, app architecture, data persistence, and deploying mobile applications."
            ),
            CourseEntity(
                code = "ITT302",
                name = "Information Security Fundamentals",
                credits = 3,
                prerequisites = "ITT101",
                description = "Security concepts including CIA triad, threats, vulnerabilities, risk management, and basic controls."
            ),
        )
    }
}
