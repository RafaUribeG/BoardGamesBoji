package com.example.boardgamesboji.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GameEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun gameDao():GameDao
}