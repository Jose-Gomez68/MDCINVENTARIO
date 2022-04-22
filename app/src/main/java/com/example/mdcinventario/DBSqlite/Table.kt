package com.example.mdcinventario.DBSqlite

class Table {

    abstract class Producto {

        companion object{
            val _ID = "_id"
            val TABLE_NAME = "productos"
            val COLUMN_ARTICULO = "articulo"
            val COLUMN_CODBAR = "codbar"
            val COLUMN_CANTIDAD = "cantidad"
            val COLUMN_ALMACEN = "almacen"
            var producto: MutableList<Productos> = ArrayList()

        }

    }

}