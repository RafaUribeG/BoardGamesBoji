package com.example.boardgamesboji.helper

import com.example.boardgamesboji.model.Classification
import com.example.boardgamesboji.model.GameModel

class GameHelper {

    companion object{
        fun emptyGameModel():GameModel{
            return GameModel(
                0,
                "No info",
                "No info",
                "No info",
                "No info",
                "No info",
                0,
                "No info",
                "No info",
                "No info",
                "No info",
                "No info",
                "No info",
                classification = Classification("No info","No info")
            )
        }
    }
}