package com.example.boardgamesboji.view

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.boardgamesboji.adapter.GAMEID_MESSAGE
import com.example.boardgamesboji.databinding.ActivityDetailGameBinding
import com.example.boardgamesboji.viewmodel.GameDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.util.jar.Manifest

@AndroidEntryPoint
class DetailGameActivity : AppCompatActivity() {

    lateinit var binding:ActivityDetailGameBinding
    private val gameDetailViewModel: GameDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailGameBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val gameId = intent.getIntExtra(GAMEID_MESSAGE, 0)

        gameDetailViewModel.loadGame(gameId)

        gameDetailViewModel.game.observe(this, Observer { game ->
            with(binding){
                tvName2.text = "Nombre: " + game.name
                tvDesc.text = "Descripción: " + game.description
                tvPlayers.text = "Jugadores: " + game.players
                tvPlayingTime.text = "Tiempo de juego: " + game.playing_time
                tvAge2.text = "Edad: " + game.age
                tvPrice2.text = "Precio: " + game.price
                tvDesigner2.text = "Diseñador: " + game.Designer
                Picasso.get().load(game.image).into(imageView2)
            }

            //Evento btnSend
            binding.btnSend.setOnClickListener {
                val textMail = """
                Hola,
                
                Quisiera reservar este juego de mesa ${game.name}, favor de contactar a este correo
                o al siguiente número +56 94242 4242.
                
                Quedo Atent@
                """.trimIndent()

                //Intent para envío de email
                val intentMail = Intent(Intent.ACTION_SENDTO).apply {
                    type = "msage/rfc822"
                    data = Uri.parse("mailto:")
                    val to:Array<String> = arrayOf("Info@boji-games.cl")
                    putExtra(Intent.EXTRA_EMAIL, to)
                    putExtra(Intent.EXTRA_SUBJECT, "Quiero Reservar este juego: " + "${game.name}")
                    putExtra(Intent.EXTRA_TEXT, textMail)
                }
                startActivity(intentMail)
            }
            //End evento btnSend


            //Evento btnCall
            binding.btnCall.setOnClickListener {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("Tel: " + "+56 94242 4242")
                startActivity(dialIntent)
            }
        })
    }
}