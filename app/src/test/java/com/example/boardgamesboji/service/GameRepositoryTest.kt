package com.example.boardgamesboji.service

import com.example.boardgamesboji.db.GameDao
import com.example.boardgamesboji.helper.GameHelper
import com.example.boardgamesboji.model.GameModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GameRepositoryTest{
    private lateinit var gameApi: GameApi
    private lateinit var gameDao: GameDao
    private lateinit var repository: GameRepository
    private lateinit var response: Response<List<GameModel>>

    @Before
    fun setup(){
        gameApi = mockk<GameApi>()
        gameDao = mockk<GameDao>(relaxed = true)
        response = mockk<Response<List<GameModel>>>()
        repository = GameRepository(gameApi, gameDao)
    }

    @Test
    fun `probar que se guarden en BD los datos de la API`() = runBlocking{
        //Config
        every { response.body() } returns listOf(GameHelper.emptyGameModel())
        every { response.isSuccessful } returns true
        coEvery { gameApi.listGames() } returns response

        //Check
        assertEquals(repository.findAll().size, 1)

        //chequeo de invocaciones
        coVerify(exactly = 1) { gameDao.deleteAll() }
        coVerify(exactly = 1) { gameDao.insertAll(any()) }
        coVerify(exactly = 0) { gameDao.getAll() }

    }

    @Test
    fun `probar que los datos vengan de BD cuando la API falla`() = runBlocking{
        //Config
        every { response.body() } returns listOf()
        every { response.isSuccessful } returns false
        coEvery { gameApi.listGames() } returns response

        //Check
        repository.findAll()

        //chequeo de invocaciones
        coVerify(exactly = 0) { gameDao.deleteAll() }
        coVerify(exactly = 0) { gameDao.insertAll(any()) }
        coVerify(exactly = 1) { gameDao.getAll() }

    }
}