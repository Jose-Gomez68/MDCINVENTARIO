package com.example.mdcinventario.view.almacenyproductos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mdcinventario.R
import com.example.mdcinventario.controller.AdapterAlamacenes
import com.example.mdcinventario.controller.ItemsAdapter
import com.example.mdcinventario.controller.ItemsModal

class SeleccAlmacen : AppCompatActivity() {
    var svBusqueda: SearchView ?= null
    val adapter = AdapterAlamacenes()

    val nombres = arrayOf(
        ItemsModal("almacen 1","5 DE FEBRERO"),
        ItemsModal("almacen 2","6 DE FEBRERO"),
        ItemsModal("almacen 3","7 DE FEBRERO"),
        ItemsModal("almacen 5","8 DE FEBRERO"),
        ItemsModal("almacen 6","9 DE FEBRERO"),
        ItemsModal("almacen 7","10 DE FEBRERO"),
        ItemsModal("almacen 8","11 DE FEBRERO"),
        ItemsModal("almacen 9","12 DE FEBRERO")
    )
    val itemsModalList = ArrayList<ItemsModal>()

    var itemsAdapter: ItemsAdapter? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecc_almacen)
// CREAR RECYCLER CON CLCIK LISTENER https://www.youtube.com/watch?v=dB9JOsVx-yY&ab_channel=Foxandroid Y SERACHVIEW https://www.youtube.com/watch?v=4RJEmQYKnbo&ab_channel=larntech
        val rvAlmacen = findViewById<RecyclerView>(R.id.rvAlmacen)
        val tvAlmacen = findViewById<TextView>(R.id.tvAlmacen)
        val btnSigAlm = findViewById<Button>(R.id.btnSigAlm)
        svBusqueda = findViewById(R.id.svBusqueda)
//        val adapter = AdapterAlamacenes()
        var user: String = intent.getStringExtra("user").toString()
        var pass: String = intent.getStringExtra("user").toString()

//        rvAlmacen.layoutManager = LinearLayoutManager(this)
//        rvAlmacen.adapter = adapter

        for (items in nombres){
            itemsModalList.add(items)
        }

        itemsAdapter = ItemsAdapter();
        itemsAdapter!!.setData(itemsModalList);

        rvAlmacen.layoutManager = LinearLayoutManager(this)
        rvAlmacen.setHasFixedSize(true)
        rvAlmacen.adapter = itemsAdapter
        itemsAdapter!!.setOnItemClickListener(object: ItemsAdapter.onItemClickListener{

            override fun onItemClick(position: Int) {
//                Toast.makeText(this@SeleccAlmacen,"posicion ${itemsModalList[position].nombre}",Toast.LENGTH_SHORT).show()
                val i = Intent(this@SeleccAlmacen, CargarProductosDb::class.java)
                i.putExtra("user","mdc")
                i.putExtra("pass","mdc123")
                i.putExtra("almacen",itemsModalList[position].nombre)
                startActivity(i)
                Toast.makeText(this@SeleccAlmacen,"posicion ${itemsModalList[position].nombre}",Toast.LENGTH_SHORT).show()
            }

        })

        buscar()//FUNCION DONDE GIARDO EL EVENT DE BUSQUEDA DE SERACHVIEW

        btnSigAlm.setOnClickListener {
//            val i = Intent(this, CargarProductosDb::class.java)
//            i.putExtra("user","mdc")
//            i.putExtra("pass","mdc123")
//            startActivity(i)
        }



    }

    private fun buscar (){
        svBusqueda!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                itemsAdapter!!.filter.filter(p0)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                itemsAdapter!!.filter.filter(p0)
                return true
            }

        })
    }


}