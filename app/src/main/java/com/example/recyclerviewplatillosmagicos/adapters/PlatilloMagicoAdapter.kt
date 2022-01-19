package com.example.recyclerviewplatillosmagicos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.recyclerviewplatillosmagicos.R
import com.example.recyclerviewplatillosmagicos.model.DatosPlatilloMagico
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class PlatilloMagicoAdapter(val platilloMagico: MutableList<DatosPlatilloMagico>): RecyclerView.Adapter<PlatilloMagicoAdapter.PlatilloMagicoHolder>(){

    class PlatilloMagicoHolder(val view: View) : RecyclerView.ViewHolder(view){
        val cardView: MaterialCardView = view.findViewById(R.id.cardView_item_platilloMagico)
        val imageViewPlatilloMagico: ImageView = view.findViewById(R.id.imageView_platilloMagico)

        fun render(platilloMagico: DatosPlatilloMagico){
            // Carga de imagenes
            Glide.with(view).load(platilloMagico.image).error(R.drawable.kodemia)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(imageViewPlatilloMagico)

            cardView.setOnClickListener {
                showDialog(view.context, platilloMagico )
            }
        }

        fun showDialog(context: Context, platilloMagico: DatosPlatilloMagico){
            MaterialAlertDialogBuilder(context)
                .setTitle(platilloMagico.nombre)
                .setMessage("Pais de origen: ${platilloMagico.origen}")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    Snackbar.make(view, "Esperamos lo pruebes pronto <3", Snackbar.LENGTH_SHORT).show()
                }
                .show()
        }

    }

    fun insertarPlatilloMagico(datosPlatilloMagico: DatosPlatilloMagico) {
        this.platilloMagico.add(datosPlatilloMagico)
        notifyItemInserted(itemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloMagicoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlatilloMagicoHolder(layoutInflater.inflate(R.layout.item_cardview_platillomagico,parent,false))
    }

    override fun onBindViewHolder(holder: PlatilloMagicoAdapter.PlatilloMagicoHolder, position: Int) {
        holder.render(platilloMagico[position])
    }

    override fun getItemCount(): Int = platilloMagico.size
}