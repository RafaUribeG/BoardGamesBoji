package com.example.boardgamesboji.di

import android.content.Context
import androidx.room.Room
import com.example.boardgamesboji.db.DataBase
import com.example.boardgamesboji.db.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun dataBase(@ApplicationContext context: Context):DataBase{
        return Room
            .databaseBuilder(context, DataBase::class.java,"games-db")
            .build()
    }


    @Provides
    @Singleton
    fun GameDao(db:DataBase) : GameDao{
        return db.gameDao()
    }
}