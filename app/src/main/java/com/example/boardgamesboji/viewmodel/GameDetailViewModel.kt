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
class GameDetailViewModel @Inject constructor(val repo:GameRepository):ViewModel(){

    val game = MutableLiveData<Game>()

    fun loadGame(id:Int){
        viewModelScope.launch {
            val gameFromRepo = repo.findById(id)
            if (gameFromRepo != null){
                game.postValue(gameFromRepo)
            }
        }
    }
}