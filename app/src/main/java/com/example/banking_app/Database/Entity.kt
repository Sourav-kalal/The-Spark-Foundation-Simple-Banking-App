package com.example.banking_app.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class Entity(
        @PrimaryKey val uid: Int,
        @ColumnInfo(name = "firstName") var firstName: String,
        @ColumnInfo(name = "balance") var balance: String
)