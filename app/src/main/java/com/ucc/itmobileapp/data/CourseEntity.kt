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
)
