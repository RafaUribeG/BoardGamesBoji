package com.example.boardgamesboji.service

import com.example.boardgamesboji.model.GameModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GameApi {

    @GET("board_games")
    suspend fun listGames(): Response<List<GameModel>>

    @GET("board_games/{id}")
    suspend fun detailGame(@Path("id") gameId:Int):Response<GameModel>
}