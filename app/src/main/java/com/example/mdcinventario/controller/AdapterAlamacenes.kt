package com.example.mdcinventario.controller

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.mdcinventario.R

class AdapterAlamacenes: RecyclerView.Adapter<AdapterAlamacenes.MyViewHolder1>() {

    //VIDEOS DE AYUDA PARA CREAR BIEN EL ADAPTER CON BASE DE DATOS Y LLENAR EL ARRAY LIST EN EL RYCYCLER https://www.youtube.com/watch?v=MyO_AiU7cEM&ab_channel=C%C3%B3digosdeProgramaci%C3%B3n-MR
    //https://www.youtube.com/watch?v=BHCVdFq4ccc&ab_channel=Jos%C3%A9Rom%C3%A1nDeveloper

    val tvalmacen = arrayOf("almacen 1","almacen 2","almacen 3","almacen 4","almacen 5","almacen 6","almacen 7","almacen 8","almacen 9")
    val almacen = arrayOf("almacen 1","almacen 2","almacen 3","almacen 4","almacen 5","almacen 6","almacen 7","almacen 8","almacen 9")
    val tvtienda = arrayOf("tienda 1","tienda 2","teinda 3","tienda 4","teinda 5","tienda 6","teinda 7","tienda 8","teinda 9")

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder1 {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardview_lista_almacenes, viewGroup, false)
        //val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.my_dialog_serch_spinner, viewGroup, false)
        return MyViewHolder1(v)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder1, i: Int) {
        viewHolder.tvAlmacen.text = tvalmacen[i]
        viewHolder.tvTienda.text = tvtienda[i]
        //viewHolder.lvAlmacen. = almacen[i]
    }

    override fun getItemCount(): Int {
        return tvalmacen.size
    }

    inner class MyViewHolder1 (itemView: View): RecyclerView.ViewHolder(itemView){
        var tvAlmacen: TextView
//        var lvAlmacen: ListView
        var tvTienda: TextView

        init {
            tvAlmacen = itemView.findViewById(R.id.tvAlmacen)
            tvTienda = itemView.findViewById(R.id.tvTienda)
//            lvAlmacen = itemView.findViewById(R.id.lvAlmacenes)
        }
    }

}