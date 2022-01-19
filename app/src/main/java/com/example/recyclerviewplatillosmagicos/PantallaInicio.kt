package com.example.recyclerviewplatillosmagicos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.recyclerviewplatillosmagicos.recyclerViews.RecyclerViewPlatillosMagicos
import kotlinx.android.synthetic.main.activity_pantalla_inicio.*

class PantallaInicio : AppCompatActivity() {

    private var parent_view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_inicio)
        parent_view = findViewById(android.R.id.content)

        fab_iniciar_recyclerview.setOnClickListener {
            startActivity(Intent(this,RecyclerViewPlatillosMagicos::class.java))
        }
    }
}