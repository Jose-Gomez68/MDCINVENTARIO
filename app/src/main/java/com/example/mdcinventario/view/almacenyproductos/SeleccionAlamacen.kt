package com.example.mdcinventario.view.almacenyproductos

import android.app.Dialog
import android.graphics.Color

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mdcinventario.R
import com.example.mdcinventario.controller.AdapterAlamacenes


class SeleccionAlamacen : AppCompatActivity() {
    private var btnSiguienteAlmacen: Button? = null
    private var spAlmacenList: Spinner? = null
    private var ivInformacionAlmacen: ImageView? = null
    private var tvSppContenedor: TextView? = null
    var dialog: Dialog? = null
    private var edBuscar: EditText? = null
    private var lvAlmacenes: ListView? = null

    var array = arrayOf("Melbourne", "Vienna", "Vancouver", "Toronto", "Calgary", "Adelaide", "Perth", "Auckland", "Helsinki", "Hamburg", "Munich", "New York", "Sydney", "Paris", "Cape Town", "Barcelona", "London", "Bangkok")

    lateinit var almacenes: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccion_alamacen)
        //Botones
        //spAlmacenList = findViewById<Spinner>(R.id.spAlmacenList)
        ivInformacionAlmacen = findViewById<ImageView>(R.id.ivInformacionAlmacen)
        btnSiguienteAlmacen = findViewById<Button>(R.id.btnSiguienteAlmacen)
        tvSppContenedor = findViewById<TextView>(R.id.tvSppContenedor)
        edBuscar = findViewById<EditText>(R.id.edBuscar)
        val adapter = AdapterAlamacenes()

        val adapter1: ArrayAdapter<*>

        val personas = mutableListOf("juan","laura","Diego")

        var user = this.intent.getStringExtra("user")

        //CRE4AR EL SPINNER SERCH SE CREA EN OTRO LAYOUT https://www.youtube.com/watch?v=5iIXg4-Iw3U&ab_channel=AndroidCoding
        val Almacenes = arrayOf(
//intentar llenarlo con un maps. llenado manual https://parzibyte.me/blog/2019/05/12/llenar-spinner-android-array-string/
            "Almacen1",
            "Almacen2",
            "Almacen3",
            "Almacen4",
            "Almacen6",
            "Almacen7",

            )

        almacenes = ArrayList()
        almacenes.add("almacen 1")
        almacenes.add("almacen2")
        almacenes.add("almacen3")
        almacenes.add("almacen 4")
        almacenes.add("almacen5")
        almacenes.add("almacen6")

//        spAlmacenList!!.setAdapter(
//            ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_spinner_dropdown_item,
//                Almacenes
//            )
//        )

         adapter1 = ArrayAdapter(this, android.R.layout.simple_list_item_1, personas)
        //asignando funcionalidad
        tvSppContenedor!!.setOnClickListener {
            dialog = Dialog(this)
            dialog!!.setContentView(R.layout.my_dialog_serch_spinner)
            //mando las medidas de mi spinner que cree ancho y alto
            dialog!!.window!!.setLayout(650,800)
            //enviar la trasnparencia que quiero
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            //dialog show = que se visible o qe se muestre
            dialog!!.show()
            //llamando las partes de mi xml
            lvAlmacenes = findViewById(R.id.lvAlmacenes)

//            val adapter1: ArrayAdapter<String> = ArrayAdapter(
//                this,
//                android.R.layout.simple_list_item_1,
//                almacenes
//            )



            println("${ArrayAdapter(this, android.R.layout.activity_list_item, personas)}000000000000000000000000000000000000000000000000000000000")
                //lvAlmacenes.layoutManager = LinearLayoutManager(this)
            lvAlmacenes?.adapter = ArrayAdapter<String>(
                this,
                android.R.layout.activity_list_item,
                Almacenes
            )


            edBuscar?.addTextChangedListener(object: TextWatcher{
                override fun beforeTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {
                    TODO("Not yet implemented")
                    adapter1.filter.filter(charSequence)
                }

                override fun onTextChanged(charSequence: CharSequence, p1: Int, p2: Int, p3: Int) {
                    TODO("Not yet implemented")
                    adapter1.filter.filter(charSequence)
                }

                override fun afterTextChanged(p0: Editable?) {
                    TODO("Not yet implemented")
                }

            })

            lvAlmacenes?.onItemClickListener = object:
            AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    TODO("Not yet implemented")
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    TODO("Not yet implemented")
                    edBuscar!!.setText(adapter1.getItem(p2));
                }

            }


        }


        println(user+"++++++++++++++++++++++++++++++++++++++++++++++")
    }

    /*private fun SiguienteVentana (){//video https://www.youtube.com/watch?v=nzQVzIHIzUg&ab_channel=Codingraph
        btnSiguienteAlmacen!!.setOnClickListener {
            spAlmacenList!!.onItemClickListener = object:
            AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    TODO("Not yet implemented")
                    var almacen = p2
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    TODO("Not yet implemented")
                }
            }
        }
    }*/

}