package com.example.investigacion01_ejerciciosoloxml

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logoImage = findViewById<ImageView>(R.id.logoImage)
        val appName = findViewById<TextView>(R.id.appName)

        // Cargar animación de rebote
        val bounceAnim = AnimationUtils.loadAnimation(this, R.anim.bounce)

        // Mostrar logo y animar rebote
        logoImage.alpha = 1f
        logoImage.startAnimation(bounceAnim)

        // Mostrar nombre de la app con fade in después de 500ms
        appName.animate()
            .alpha(1f)
            .setStartDelay(500)
            .setDuration(1000)
            .start()

        // Pasar al MainActivity después de 3 segundos
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)
    }
}
