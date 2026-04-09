package com.ucc.itmobileapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
data class CourseEntity(
    @PrimaryKey val code: String,
    val name: String,
    val credits: Int,
    val prerequisites: String,
    val description: String,
) {
    fun toCourse(): Course {
        return Course(
            code = code,
            name = name,
            credits = credits,
            prerequisites = prerequisites,
            description = description,
        )
    }

    companion object {
        fun fromCourse(course: Course): CourseEntity {
            return CourseEntity(
                code = course.code,
                name = course.name,
                credits = course.credits,
                prerequisites = course.prerequisites,
                description = course.description,
            )
        }
    }
}
