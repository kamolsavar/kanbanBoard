package com.kamol.repository

import java.sql.Blob
import java.util.Date

data class Task(
val taskId: String,
val task: String,
val status: String,
val owner: String? = null,
val createdAt: Date,
val updatedAt: Date
//val OwnerAvatar: Blob?
)
