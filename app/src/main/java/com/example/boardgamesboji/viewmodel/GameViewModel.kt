package com.example.boardgamesboji.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boardgamesboji.model.Game
import com.example.boardgamesboji.service.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(val repo:GameRepository): ViewModel() {

    val games = MutableLiveData<List<Game>>()

    fun loadGames(){
        viewModelScope.launch {
            val gamesFromRepo = repo.findAll()
            if (!gamesFromRepo.isNullOrEmpty()){
                games.postValue(gamesFromRepo)
            }
        }
    }
}