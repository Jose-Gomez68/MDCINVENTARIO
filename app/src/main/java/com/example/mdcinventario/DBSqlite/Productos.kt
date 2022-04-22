package com.example.mdcinventario.DBSqlite

class Productos {
    private var id: Int = 0
    private var articulo: String = ""
    private var codbar: String = ""
    private var cantidad: String = ""
    private var almacen: String = ""
    constructor(id: Int, articulo: String, codbar: String, cantidad: String, almacen: String) {
        this.id = id
        this.articulo = articulo
        this.codbar = codbar
        this.cantidad = cantidad
        this.almacen = almacen
    }

    fun getId(): Int{
        return id
    }
    fun getArticulo(): String {
        return articulo
    }
    fun getCodbar(): String {
        return codbar
    }
    fun getCantidad(): String {
        return cantidad
    }

    fun getAlmacen(): String {
        return almacen
    }

    override fun toString(): String {
        return articulo
    }


//    fun setId(id: Int){
//        this.id = id
//    }
//    fun setArticulo(articulo: String) {
//        this.articulo = articulo
//    }
//    override fun toString (): String{
//        return articulo
//    }
//
//    fun setCodbar(codbar: String) {
//        this.codbar = codbar
//    }
//    fun setCantidad(cantidad: String) {
//        this.cantidad = cantidad
//    }

}