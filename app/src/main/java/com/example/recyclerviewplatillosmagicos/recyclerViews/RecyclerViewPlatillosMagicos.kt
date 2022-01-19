package com.example.recyclerviewplatillosmagicos.recyclerViews

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewplatillosmagicos.R
import com.example.recyclerviewplatillosmagicos.adapters.PlatilloMagicoAdapter
import com.example.recyclerviewplatillosmagicos.model.DatosPlatilloMagico
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_recyclerview_platillosmagicos.*
import kotlinx.android.synthetic.main.dialog_agregar_platillomagico.view.*

class RecyclerViewPlatillosMagicos : AppCompatActivity() {

    val listPlatilloMagico: MutableList<DatosPlatilloMagico> = mutableListOf()
    var adapterPlatilloMagico = PlatilloMagicoAdapter(listPlatilloMagico)
    var parent_view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview_platillosmagicos)
        parent_view = findViewById(android.R.id.content)
        initRecyclerPlatillosMagicos()
        title = "RecyclerView Platillos Magicos"
        initFABAddPlatilloMagico(this)
    }

    private fun initFABAddPlatilloMagico(context: Context){
        fab_add_platilloMagico_recyclerView.setOnClickListener {
            val layoutInflater = LayoutInflater.from(context).inflate(R.layout.dialog_agregar_platillomagico, null)
            val dialog = AlertDialog.Builder(context).setView(layoutInflater).setCancelable(true)

            val instanciaDialogo = dialog.show()
            layoutInflater.button_add_platilloMagico.setOnClickListener {
                val urlImage = layoutInflater.til_image_url.editText?.text.toString().trim()
                val nombre = layoutInflater.til_nombre.editText?.text.toString()
                val origen = layoutInflater.til_origen.editText?.text.toString()

                if (urlImage.isNotEmpty() && nombre.isNotEmpty() && origen.isNotEmpty()) {
                    adapterPlatilloMagico.insertarPlatilloMagico(
                        DatosPlatilloMagico(
                            urlImage,
                            nombre,
                            origen
                        )
                    )
                    recyclerView_platillosMagicos.scrollToPosition(adapterPlatilloMagico.itemCount - 1)
                    instanciaDialogo.dismiss()
                } else {
                    Snackbar.make(
                        parent_view!!,
                        "Los datos no pueden estar vacios",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

            layoutInflater.button_cancel_dialog_platilloMagico.setOnClickListener {
                instanciaDialogo.dismiss()
            }
        }
    }


    private fun initRecyclerPlatillosMagicos() {
        listPlatilloMagico.add(
            DatosPlatilloMagico(
                "https://dam.ngenespanol.com/wp-content/uploads/2019/03/tacos-al-pastor-mexico.png",
                "Tacos al pastor",
                "Mexico"
            )
        )
        listPlatilloMagico.add(
            DatosPlatilloMagico(
                "https://dam.ngenespanol.com/wp-content/uploads/2019/03/pizza-napolitana-italia.png",
                "Pizza Napolitana ",
                "Italia"
            )
        )
        listPlatilloMagico.add(
            DatosPlatilloMagico(
                "https://dam.ngenespanol.com/wp-content/uploads/2019/03/Lasana-italia.png",
                "Lasaña",
                "Italia"
            )
        )
        listPlatilloMagico.add(
            DatosPlatilloMagico(
                "https://dam.ngenespanol.com/wp-content/uploads/2019/03/Brasil-churrasco.png",
                "Churrasco",
                "Brasil"
            )
        )
        listPlatilloMagico.add(
            DatosPlatilloMagico(
                "https://dam.ngenespanol.com/wp-content/uploads/2019/03/Khachapuri-Georgia.png",
                "Khachapuri",
                "Georgia"
            )
        )
        listPlatilloMagico.add(
            DatosPlatilloMagico(
                "https://dam.ngenespanol.com/wp-content/uploads/2019/03/cevapi-Bosnia-y-Herzegovina.png",
                "Ćevapi",
                "Bosnia y Herzegovina"
            )
        )
        listPlatilloMagico.add(
            DatosPlatilloMagico(
                "https://dam.ngenespanol.com/wp-content/uploads/2019/03/ceviche-peruano.png",
                "Ceviche",
                "Peru"
            )
        )


        recyclerView_platillosMagicos.layoutManager = GridLayoutManager(this,3)
        recyclerView_platillosMagicos.setHasFixedSize(true)
        adapterPlatilloMagico = PlatilloMagicoAdapter(listPlatilloMagico)
        recyclerView_platillosMagicos.adapter = adapterPlatilloMagico
    }

    private fun hideKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        // Check if no view has focus
        val currentFocusedView = activity.currentFocus
        currentFocusedView?.let {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}