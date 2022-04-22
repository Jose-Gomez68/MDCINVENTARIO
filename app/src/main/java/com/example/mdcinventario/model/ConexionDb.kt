package com.example.mdcinventario.model

import android.os.StrictMode
import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConexionDb {


    private val ip = "192.168.1.67:50294" //port 50294 1433
    private val db = "pruebas"
    private val username = "sa"
    private val password = "68120568"

    fun dbconection(): Connection? {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy)
        var conn: Connection? = null
        val connString: String
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance()
            connString = "jdbc:jtds:sqlserver://$ip;databaseName=$db;user=$username;password=$password;"
            conn = DriverManager.getConnection(connString)
        }catch(ex: SQLException){
            print(ex)
            ex.printStackTrace()
//con la var companion object mando el contexto de la activity para poder usar aqui mi toast
            //Toast.makeText(maincontext, "ERROR EN LA CONEXION", Toast.LENGTH_SHORT).show()
            Log.e("ERROR++++++", ex.message!!)

        }catch (ex1: ClassNotFoundException){
            print(ex1)
            ex1.printStackTrace()
            ex1.message?.let { Log.e("ERROR----", it) }//esta linea es lo mismo que lo de arriba
        }catch (ex2: Exception){
            Log.e("ERROR///////", ex2.message!!)
            ex2.printStackTrace()
        }
        return conn
    }


}