package com.example.mdcinventario.view.almacenyproductos

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mdcinventario.DBSqlite.DataDbHelper
import com.example.mdcinventario.DBSqlite.Productos
import com.example.mdcinventario.R
import com.example.mdcinventario.model.ConexionDb
import com.google.zxing.integration.android.IntentIntegrator
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*
import kotlin.collections.ArrayList

class CargarProductosDb : AppCompatActivity() {

    var ivMas: ImageView ?= null
    var ivMenos: ImageView ?= null
    var etStockAgregar2: EditText ?= null
    var tvCodProd2: EditText ?= null
//var codigo de barras lector
    var codigobarras: String = ""
    var cantidadAgregar: Int = 0
    //var de conexicon sqlite
    private var db: DataDbHelper? = null
    //private var dbmdc: ConexionDb? = null
    var conecionsql = ConexionDb()
    private  var list: MutableList<Productos> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cargar_productos_db)
        //variable de scanner
        val ivQrScanner = findViewById<ImageView>(R.id.ivQrScanner)
        //variable de botones
        ivMas = findViewById<ImageView>(R.id.ivMas)
        ivMenos = findViewById<ImageView>(R.id.ivMenos)
        etStockAgregar2 = findViewById<EditText>(R.id.etStockAgregar2)
        var btnScan = findViewById<Button>(R.id.btnScan)
        var tvCodProd2 = findViewById<TextView>(R.id.tvCodProd2)
        etStockAgregar2!!.setText("0")
        cantidadAgregar = Integer.parseInt(etStockAgregar2!!.text.toString())
        println(intent.getStringExtra("almacen").toString())
        //dbmdc!!.consultProductos()

         //funciones para disminuir y aumentar el stock de uno en uno
         Mas()
         Menos()
         //metodo clic de la imagen de scanner
         ivQrScanner.setOnClickListener {
            scanner()
         }

        btnScan.setOnClickListener {
            //funcion escanear codigo en la db mdc
        }

//MEJOR CREO UNA DB SQLITE PARA GUARDAR EN EL ALMACENAMIENTO DE TELEFENO Y SEA MAS OPTIMO Y DESPUES SUBIR A FIRE BASE https://www.youtube.com/watch?v=wT9wR-nvbEM&ab_channel=AlexPagoada
        db = DataDbHelper(this)//aqui debo incializar la conexion
        println(db!!.getData().size)
        btnAgregar ()
        println(db!!.getData().size)

    }

    private fun scanner (){//propiedades de la libreria cuando abre el scannr puedo mostrar o quitar o hacer sonido de beep
        IntentIntegrator(this)
            .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            .setBeepEnabled(true)
            .setPrompt("Scan QR y CodBar")
            .initiateScan()
    }

    //funcion override de la libreria
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val resultado = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (resultado != null){
            if (resultado.contents != null){
                var etCodBar = findViewById<EditText>(R.id.etCodBar)
                codigobarras = resultado.contents+""
                etCodBar.setText(codigobarras)
                //AQI TENDRIA QUE METER LA FUNCION DE CONSULTA A LA BASE DE DATOS MANDAR POR CODIGO DE BARRAS

            }else{
                Toast.makeText(this,"Cancelado",Toast.LENGTH_SHORT).show()
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }


    }

    fun consultaProduct (){//RECIBIRA EL CODIGO DE BARRAS PARA BUSCAR POR WHERE
        var a = ""
        try{
            val consul: PreparedStatement = conecionsql.dbconection()?.prepareStatement("select *from prueba where id = '"+a+"';")!!
            consul.executeQuery();
            var rs: ResultSet = consul.executeQuery()
            while (rs.next()){
                tvCodProd2?.setText(rs.getString(2))//aqui las columnas de las tablas empiezan apartir de 1
            }

//            Toast.makeText(this, "CONSULTA",Toast.LENGTH_SHORT).show()
            consul.close()
        }catch (ex: SQLException){
//            Toast.makeText(this, "ERROR EN EL consulta: $ex",Toast.LENGTH_SHORT).show()
        }

    }

    private fun insertar (){//debo ver videos mas atras de como creoo el sett an getter y los arreglos https://www.youtube.com/watch?v=Ooz4QwfSV8c&t=480s&ab_channel=AlexPagoada
        //list.add(Productos(0,"PENSIL2520","669885545455","5"))
        db!!.insert(listOf(Productos(0,"PENSIL2520","669885545455","7","almacen 2")))

    }

    fun btnAgregar (){
        var btnAgregarProd = findViewById<Button>(R.id.btnAgregarProd)
        btnAgregarProd.setOnClickListener {

            /*if (db!!.getData().size == 0){
                println("COMPARO NO HAY NADA ")
//                insertar ()
            }else if (db!!.getData().size >= 1){//verifica si hay informacion en la tabla
                println("SE COMPARO SI ES IGUAL O MAYOR A 1 COMO SI TIENE CONTENIDO ENTRA AL IF DE ACTUALIZAR")
                var codb = ""
                var alm = ""
                var edit = "66988554545"
                var edit2 = "almacen 1"
//                if (db!!.consultaCodBar(listOf(Productos(0,"","669885545455","",""))).get(0).getCodbar().equals("669885545455") && db!!.consultaCodBar(listOf(Productos(0,"","669885545455","",""))).get(0).getAlmacen().equals("almacen 1")){//compara el codigo de barra escaneado con el de la base de datos sqlite
                for (i in db!!.consultaCodBarA("669885545455","almacen 1")){
                    codb = i.getCodbar()
                    alm = i.getAlmacen()
                }
                if (edit.equals(codb) && edit2.equals(alm)){
//                    db!!.actualizarProd(listOf(Productos(0,"",variableEdittexCodigoBarra,VariableEditTextCantidad)))// si ya esta registrada entonces suma la cantidad nueva
                //aqui debo tambien consultar la cantidad para convertirlas a entero y sumarlas y despues convertir a string y insertar
                    println("SI SON IGUALES SE ACTUALIZA")
                    db!!.actualizarProd2("669885545455","almacen 1","11")// si ya esta registrada entonces suma la cantidad nueva
                    println(db!!.getData().get(0).getId())//0 es fila 1
                    println(db!!.getData().get(0).getArticulo()+"")
                    println(db!!.getData().get(0).getCodbar()+"")
                    println(db!!.getData().get(0).getCantidad()+"")
                    println(db!!.getData().get(0).getAlmacen()+"\n")

                }else{
                    //de lo contrario se inserta producto nuevo
                    println("NO SON IGUALES SE INSERTANDO PRODUCTO NUEVO")
                    //db!!.insert(listOf(Productos(0,"PENSIL2520","669885545455","8","almacen 1")))
                }
//                var i: Int = 0
//                var a = db!!.getData() for para llenar el excel
//                for ( i in a){
//
//                }
            }*/

            println("CLICCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC")
//            insertar ()
            println(db!!.getData().size)
//            println(db!!.getData().get(0).getId())//0 es fila 1
//            println(db!!.getData().get(0).getArticulo()+"")
//            println(db!!.getData().get(0).getCodbar()+"")
//            println(db!!.getData().get(0).getCantidad()+"")
//            println(db!!.getData().get(0).getAlmacen()+"\n")
//            println(db!!.getData().get(1).getId())//el 1 es fila 2
//            println(db!!.getData().get(1).getArticulo()+"")
//            println(db!!.getData().get(1).getCodbar()+"")
//            println(db!!.getData().get(1).getAlmacen()+"\n\n")
            //println("ESSSSSSSS: ${db!!.consultaCodBar(listOf(Productos(0,"","669885545455","",""))).get(0).getAlmacen()+""}")
//            println(db!!.consultaCodBarA("669885545455","almacen 1").get(0))
//            for (i in db!!.consultaCodBarA("669885545455","almacen 1")){//asi recorro sin tener que llamar dos vecws la consulta
//                println("esto contieneeeeeeeeeeeeeeeee: ${i.getCodbar()}")
//                println("esto contieneeeeeeeeeeeeeeeee: ${i.getAlmacen()}")
//            }
//            var i = 1
            for (i in db!!.getData()){//asi recorro sin tener que llamar dos vecws la consulta
                println("esto contieneeeeeeeeeeeeeeeee: ${i.getCodbar()}")
                println("esto contieneeeeeeeeeeeeeeeee: ${i.getAlmacen()}")
                println("esto contieneeeeeeeeeeeeeeeee: ${i.getCantidad()}")
            }

//            db!!.actualizarProd(listOf(Productos(0,"","669885545455","10")))
//            println(db!!.getData().get(2).getId())//el 1 es fila 2
//            println(db!!.getData().get(2).getArticulo()+"")
//            println(db!!.getData().get(2).getCodbar()+"")
//            println(db!!.getData().get(2).getCantidad()+"")
//            println(db!!.getData().get(2).getAlmacen()+"\n")
//            println(db!!.getData().get(3).getId())//el 1 es fila 2
//            println(db!!.getData().get(3).getArticulo()+"")
//            println(db!!.getData().get(3).getCodbar()+"")
//            println(db!!.getData().get(3).getCantidad()+"")
//            println(db!!.getData().get(3).getAlmacen()+"\n")
//            println(db!!.getData().get(4).getId())//el 1 es fila 2
//            println(db!!.getData().get(4).getArticulo()+"")
//            println(db!!.getData().get(4).getCodbar()+"")
//            println(db!!.getData().get(4).getCantidad()+"")
//            println(db!!.getData().get(4).getAlmacen()+"\n")
        }


    }

//funcion para disminuir cantidad del stock para cargar
    fun Menos(){

        ivMenos!!.setOnClickListener {
            cantidadAgregar = Integer.parseInt(etStockAgregar2!!.getText().toString())//refresh para que cuando escriba un numero este le aumente 1 en donde se quedo extrayendo del editetex
            if (cantidadAgregar >= 1 ){
                cantidadAgregar -= 1
                etStockAgregar2!!.setText(cantidadAgregar.toString());

            }else{
                Toast.makeText(this,"No Puedes Tener Numeros Negativos",Toast.LENGTH_SHORT).show()
            }
        }

    }
    //funcion para aumentar cantidad del stock para cargar
    fun Mas (){

        if (cantidadAgregar <= 0){3

            ivMas!!.setOnClickListener {
                cantidadAgregar = Integer.parseInt(etStockAgregar2!!.getText().toString())//refresh para que cuando escriba un numero este le aumente 1 en donde se quedo extrayendo del editetex
                cantidadAgregar++
                etStockAgregar2!!.setText(cantidadAgregar.toString())
            }
        }else{
            Toast.makeText(this,"Verifica SI la cantidad es correcta",Toast.LENGTH_SHORT).show()
        }

    }

    private fun seeData (listData: MutableList<Productos>){
        val adapter = ArrayAdapter<Productos>(this,android.R.layout.simple_list_item_1,listData)
        //listvie.
    }

}