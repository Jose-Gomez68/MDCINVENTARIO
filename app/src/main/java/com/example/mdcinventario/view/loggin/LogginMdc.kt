package com.example.mdcinventario.view.loggin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.mdcinventario.R
import com.example.mdcinventario.view.almacenyproductos.SeleccAlmacen
import com.example.mdcinventario.view.almacenyproductos.SeleccionAlamacen

class LogginMdc : AppCompatActivity() {
    var btnIniciarSesion: Button?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loggin_mdc)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        btnIniciarSesion!!.setOnClickListener {
            validarUsuario()
        }
    }

    private fun validarUsuario () {
        var etUser = findViewById<EditText>(R.id.etUser)
        var etPasword = findViewById<EditText>(R.id.etPasword)
        var user: String = "mdc"
        var pass: String = "mdc"
        if (user.equals(etUser.text.toString()) && pass.equals(etPasword.text.toString())){
            //SeleccAlmacen
            val i = Intent(this, SeleccAlmacen::class.java)
            i.putExtra("user",user)
            i.putExtra("pass",pass)
            startActivity(i)
        }
    }

}