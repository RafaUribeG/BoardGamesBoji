package com.example.boardgamesboji.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.boardgamesboji.model.Classification
import com.example.boardgamesboji.model.Game

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey override val id: Int,
    override val name: String,
    override val image: String,
    override val price: String,
    override val players: String,
    override val age: String,
    override val year: Int,
    override val playing_time: String,
    override val description: String,
    override val official_link: String,
    override val Designer: String,
    override val Artist: String,
    override val Publisher: String
):Game


