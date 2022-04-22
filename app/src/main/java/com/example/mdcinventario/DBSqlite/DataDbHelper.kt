package com.example.mdcinventario.DBSqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DataDbHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null,DATABASE_VERSION){//aqui se crea la base dadatos tipo sqlite

    private val db: SQLiteDatabase
    private val values: ContentValues

    companion object{
        private val DATABASE_VERSION = 1 //AQUI INDICO LA VERSION DE MI DB CREADA SI LLEGO A CAMBIARLA TENGO QUE CAMBIARLA A 2 O MARCARIA ERROR
        private val DATABASE_NAME = "cargaproducto" //AQUI INDICO EL NOMBRE DE MI DB
    }

    init {//con init creo estos objetos de esta misma clase y poder acceder
        db = this.writableDatabase
        values = ContentValues()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            //aqui le di implementar los metodos de esta clase y me hizo dos funciones
            db!!.execSQL("CREATE TABLE "+Table.Producto.TABLE_NAME+" ( "+
                    Table.Producto._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Table.Producto.COLUMN_ARTICULO + " TEXT NOT NULL, " +
                    Table.Producto.COLUMN_CODBAR + " TEXT NOT NULL, " +
                    Table.Producto.COLUMN_CANTIDAD + " TEXT NOT NULL, "+
                    Table.Producto.COLUMN_ALMACEN + " TEXT NOT NULL );");//aqui creo la tabla heredando e instanciando de mi clase Table.kt
        }catch (e: SQLiteException){
            println("ERROR: $e")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    //funcion de insertar datos en mi db local sqlite
    fun insert (producto: List<Productos>): String{
        try {
            values.put(Table.Producto.COLUMN_ARTICULO,producto[0].getArticulo())
            values.put(Table.Producto.COLUMN_CODBAR,producto[0].getCodbar())
            values.put(Table.Producto.COLUMN_CANTIDAD,producto[0].getCantidad())
            values.put(Table.Producto.COLUMN_ALMACEN,producto[0].getAlmacen())
            db.insert(Table.Producto.TABLE_NAME, null, values)
           // println(gatData().get(1).getArticulo()+"")
        }catch (ex: SQLiteException){
            return ex.message+"EROOOOOOOOOOOOOOOOOOOOOR"
        }
        return "PERFECTO"
    }

    fun getData(): MutableList<Productos>{//funcion consultar todo los registros
        Table.Producto.producto.clear();
        val columnas = arrayOf(Table.Producto._ID, Table.Producto.COLUMN_ARTICULO,
        Table.Producto.COLUMN_CODBAR, Table.Producto.COLUMN_CANTIDAD, Table.Producto.COLUMN_ALMACEN);
        val c = db.query(Table.Producto.TABLE_NAME, columnas, null, null, null, null, null)

        if (c.moveToFirst()){
            do {
                Table.Producto.producto.add(Productos(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)))
            }while (c.moveToNext())
        }
        return Table.Producto.producto
    }

    fun consultaCodBar (producto: List<Productos>): MutableList<Productos> {
        //funcion de consultar los datos por codbar y almacen
        Table.Producto.producto.clear();
        val columnas = arrayOf(Table.Producto.COLUMN_CODBAR,Table.Producto.COLUMN_ALMACEN);
        val arreglo = arrayOf(producto[0].getCodbar())
        val c = db.query(Table.Producto.TABLE_NAME,columnas,Table.Producto.COLUMN_CODBAR+ " =? "
            ,arreglo,null ,null, null)

        if (c.moveToFirst()){
            do {
                //aqui mando id de la tabla en 0 y las demas columnas en cadena vacia por que mi clase Productos.kt recibe forzozamente estos parametros
                    //y tengo que especificar al agregar y crear el arreglo aqui abajo mi getString como nadamas traigo la columnma de codigo barras obvio solo hay una y es la 0
                Table.Producto.producto.add(Productos(0,"",c.getString(0),"",c.getString(1)))
            }while (c.moveToNext())
        }
        return Table.Producto.producto

    }

    fun consultaCodBarA (a: String, b: String): MutableList<Productos> {
        //funcion de consultar los datos por codbar y almacen
        Table.Producto.producto.clear();

        val c = db.rawQuery("SELECT "+ Table.Producto.COLUMN_CODBAR+ " , "+ Table.Producto.COLUMN_ALMACEN+ " FROM "+
        Table.Producto.TABLE_NAME+" WHERE "+Table.Producto.COLUMN_CODBAR+"= '"+a+"' AND "+Table.Producto.COLUMN_ALMACEN+"='"+b+"' ",null)

        if (c.moveToFirst()){
            do {
                //aqui mando id de la tabla en 0 y las demas columnas en cadena vacia por que mi clase Productos.kt recibe forzozamente estos parametros
                //y tengo que especificar al agregar y crear el arreglo aqui abajo mi getString como nadamas traigo la columnma de codigo barras obvio solo hay una y es la 0
                Table.Producto.producto.add(Productos(0,"",c.getString(0),"",c.getString(1)))
            }while (c.moveToNext())
        }
        return Table.Producto.producto

    }

    fun actualizarProd (producto: List<Productos>){
        val arreglo = arrayOf(producto[0].getCodbar().toString())
        values.put(Table.Producto.COLUMN_CANTIDAD, producto[0].getCantidad())
        db.update(Table.Producto.TABLE_NAME, values, Table.Producto.COLUMN_CODBAR + "=?", arreglo)
    }

    fun actualizarProd2 (codB: String, alm: String, cantidad: String){
        //val arreglo = arrayOf(producto[0].getCodbar().toString())

        db.execSQL("UPDATE "+Table.Producto.TABLE_NAME+" SET "+Table.Producto.COLUMN_CANTIDAD+" = '"+cantidad+"'"+" WHERE "+
        Table.Producto.COLUMN_CODBAR+" = '"+codB+"' AND "+Table.Producto.COLUMN_ALMACEN+" = '"+alm+"' ")
    }

    fun eliminarTodosProductos (){
//        val arreglo = arrayOf(condition)
        db.delete(Table.Producto.TABLE_NAME,null,null)
    }

}