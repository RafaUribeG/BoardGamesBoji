package com.example.boardgamesboji.model

data class GameModel(
    override val id: Int,
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
    override val Publisher: String,
    val classification: Classification,

):Game

data class Classification(
    val category: String,
    val mechanisms: String
)