package com.example.boardgamesboji.mapper

import com.example.boardgamesboji.db.GameEntity
import com.example.boardgamesboji.model.Game

class GameMapper {

    companion object {
        fun toEntity(game:Game):GameEntity{
            with(game){
                return GameEntity(
                    id, name, image, price, players, age, year, playing_time, description, official_link, Designer, Artist, Publisher
                )
            }
        }
    }
}