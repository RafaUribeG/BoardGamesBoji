package com.example.boardgamesboji.service

import com.example.boardgamesboji.db.GameDao
import com.example.boardgamesboji.helper.GameHelper
import com.example.boardgamesboji.mapper.GameMapper
import com.example.boardgamesboji.model.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GameRepository  @Inject constructor(
    private val gameApi: GameApi,
    private val gameDao: GameDao
){
    suspend fun findAll():List<Game> {
        return withContext(Dispatchers.IO){
            val response = gameApi.listGames()
            if( response.isSuccessful){
                val games = response.body() ?: emptyList()

                //borrar cache antigua
                gameDao.deleteAll()
                //cachear datos en BD
                games.forEach { gameModel ->
                    gameDao.insertAll( GameMapper.toEntity(gameModel))
                }
                games
            } else {
                gameDao.getAll()
            }
        }
    }

    suspend fun findById(id:Int):Game{
        return withContext(Dispatchers.IO){
            val response = gameApi.detailGame(id)
            if ( response.isSuccessful){
                val game = response.body() ?: GameHelper.emptyGameModel()

                //cachear en BD
                gameDao.insertAll(GameMapper.toEntity(game))
                game
            } else {
                gameDao.findById(id)
            }
        }
    }
}