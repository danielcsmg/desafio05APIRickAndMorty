package br.com.zup.desafiorickemorty.ui.splash.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.desafiorickemorty.R
import br.com.zup.desafiorickemorty.ui.listadepersonagens.view.ListaPersonagensActivity
import java.util.*

private val timer = Timer()
const val DELAY = 3000L

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        timer.schedule(object : TimerTask() {
            override fun run() {
                jump()
            }
        }, DELAY)

    }

    private fun jump() {
        timer.cancel()
        startActivity(Intent(this, ListaPersonagensActivity::class.java))
        this.finish()
    }
}