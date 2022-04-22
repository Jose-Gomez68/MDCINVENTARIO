package com.example.mdcinventario

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mdcinventario.view.almacenyproductos.CargarProductosDb
import com.example.mdcinventario.view.almacenyproductos.SeleccAlmacen
import com.example.mdcinventario.view.excelcrear.CrearExcel
import com.example.mdcinventario.view.loggin.LogginMdc

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        startActivity(Intent(applicationContext, SeleccAlmacen::class.java))
        startActivity(Intent(applicationContext, CrearExcel::class.java))
        finish()
    }
}