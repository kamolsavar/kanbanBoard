package com.kamol.model

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.jodatime.datetime
import org.joda.time.DateTime


object Staff : Table() {
    val staffId = integer("staffId").autoIncrement() // Column<String>
    val name = varchar("name", 50) // Column<String>
    val team = varchar("team", 25)
    val role = varchar("role", 30)
    override val primaryKey = PrimaryKey(staffId, name = "PK_Staff_ID") // name is optional here
}

object Task : Table() {
    val taskId = integer("taskId").autoIncrement()
    val task = varchar("task", 100)
    val status = varchar("status", 20)
    val owner = (integer("owner") references Staff.staffId).nullable()
    val createdAt: Column<DateTime> = datetime("createdAt")
    val updatedAt: Column<DateTime> = datetime("updateAt")

    override val primaryKey = PrimaryKey(taskId, name = "PK_Task_ID")
}

object Board : Table() {
    val boardId = integer("boardId").autoIncrement()
    val name = varchar("name", 25)
    val task = (integer("task") references Task.taskId).nullable()
    val createdAt: Column<DateTime> = datetime("createdAt")
    val updatedAt: Column<DateTime> = datetime("updatedAt")
    override val primaryKey = PrimaryKey(boardId, name = "PK_Board_ID")
}
