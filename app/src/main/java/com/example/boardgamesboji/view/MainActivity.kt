package com.example.boardgamesboji.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.boardgamesboji.R
import com.example.boardgamesboji.adapter.GameAdapter
import com.example.boardgamesboji.databinding.ActivityMainBinding
import com.example.boardgamesboji.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val gameViewModel : GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //RecylerView
        val recyclerView = binding.myRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)

        gameViewModel.loadGames()

        gameViewModel.games.observe(this, Observer { games ->
            binding.myRecycler.adapter = GameAdapter(games)
        })
    }
}