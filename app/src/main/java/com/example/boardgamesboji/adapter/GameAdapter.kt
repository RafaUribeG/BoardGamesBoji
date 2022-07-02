package com.example.boardgamesboji.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.boardgamesboji.databinding.GameItemBinding
import com.example.boardgamesboji.model.Game
import com.example.boardgamesboji.view.DetailGameActivity
import com.squareup.picasso.Picasso

const val GAMEID_MESSAGE = "com.example.boardgamesboji.GAMEID"

class GameAdapter(private val data:List<Game>) :RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    class ViewHolder(val binding:GameItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = data.get(position)
        with(holder.binding){
            tvName.text = "Nombre: " + game.name
            tvAge.text = "Edad: " + game.age
            tvYear.text = "Año: " + game.year.toString()
            tvPublisher.text = "Editor: " + game.Publisher
            Picasso.get()
                .load(game.image)
                .resize(400, 380)
                .into(imageView)
        }

        //onclick game.item
        holder.binding.root.setOnClickListener(View.OnClickListener {
            val intent = Intent(it.context, DetailGameActivity::class.java).apply {
                putExtra(GAMEID_MESSAGE, game.id)
            }
            it.context.startActivity(intent)
        })

    }

    override fun getItemCount(): Int {
        return data.size
    }
}