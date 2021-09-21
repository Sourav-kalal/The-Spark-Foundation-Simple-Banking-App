package com.example.banking_app.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT * FROM User")
    fun getAll(): List<Entity>

    @Insert
    fun insert(entity: Entity);

    @Query("UPDATE User SET balance=:bal WHERE firstName=:user")
    fun balUpdate(user: String, bal: String)
}