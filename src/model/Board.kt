package com.kamol.repository

import java.util.Date

data class Board (
        val boardId: String,
        val name: String,
        val task:List<String >,
        val createdAt: Date,
        val updatedAt: Date? = null
    )