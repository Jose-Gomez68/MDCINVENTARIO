package com.example.mdcinventario.view.excelcrear

import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.mdcinventario.DBSqlite.DataDbHelper
import com.example.mdcinventario.R
import org.apache.poi.hssf.usermodel.HSSFCellStyle
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class CrearExcel : AppCompatActivity() {

    private val PERMISO_SCRITURA: Int = 777
    private var db: DataDbHelper? = null
    lateinit var cust: Array<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_excel)
        db = DataDbHelper(this)//aqui debo incializar la conexion
        var btnGuardarExcel = findViewById<Button>(R.id.btnGuardarExcel)
        //lg sera var donde se guardara si la oopcion es local o global
        /*if (lg.equals("global")){
            //aqui se pone la funcion donde consulta la db firebase y genere el excel global
        }else{
            //aqui ira la consulta sqlite y funcion donde de genera el excel local
        }*/

        /*if (db!!.getData().size == 0){//if para verificar si hay o no hay datos en la db, verificar si hay datos en la firebase tambien
            //el boton de excel local se deshabilitara por que la tabla no contiene datos
        }else if (db!!.getData().size >= 1){
            //se habilita el boton ya que la tabla contiene datos
        }*/

        btnGuardarExcel.setOnClickListener {
//            guardarExcel()
            excel3 ()
//            solicitarPermiso()//TODO CUANDO SE DESINSTALE LA APP DEBES BORRAR LOS ARCHIVOS QUE SE CREEARON LOS EXCEL
            }
    }


    fun guardarExcel2() {//https://www.youtube.com/watch?v=D5-AABf0vaY&ab_channel=4SoftwareDevelopers

//PPOSIBLEMENTE SEA ESTE EL CORRECTO https://www.youtube.com/watch?v=xGpAgQ-fqMo&ab_channel=EMETechnologies
        // Inflate the layout for this fragment
        val columns = arrayOf("Articulo", "CodBar", "Cantidad")
        val wb: Workbook = HSSFWorkbook()
        val stream = ByteArrayOutputStream()


        // Inflate the layout for this fragment
        var sheet: Sheet = wb.createSheet("Productos")
        var row: Row = sheet.createRow(0)

        for (i in columns.indices) {
            var cell: Cell = row.createCell(i)
            cell.setCellValue(columns[i])
        }

        var initRow = 1
        for (j in db!!.getData()){
            row = sheet.createRow(initRow)
            row.createCell(0).setCellValue(j.getArticulo())
            row.createCell(0).setCellValue(j.getCodbar())
            row.createCell(0).setCellValue(j.getAlmacen())
            initRow++
        }

    }

    fun excel2(){

        val columns = arrayOf("Articulo", "CodBar", "Cantidad")
        val wb: Workbook = HSSFWorkbook()
        var cell: Cell? = null
        val cellStyle = wb.createCellStyle()
        cellStyle.fillForegroundColor = HSSFColor.LIGHT_BLUE.index
        cellStyle.fillPattern = HSSFCellStyle.SOLID_FOREGROUND
        var sheet: Sheet? = null
        sheet = wb.createSheet("Lista de usuarios")
        var row: Row? = null
        //aqui aplicare un for del tamaño del arreglo que obtenga de la db para agregar los productos
        for (i in columns.indices) {
            cell = row?.createCell(i)
            cell?.setCellValue(columns[i])
        }

        var initRow = 1
        for (j in db!!.getData()){
            row = sheet.createRow(initRow)
            row.createCell(0).setCellValue(j.getArticulo())
            row.createCell(0).setCellValue(j.getCodbar())
            row.createCell(0).setCellValue(j.getAlmacen())
            initRow++
        }

        //asi indico donde se guardara los archivos
        val file = File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOCUMENTS), "CargaProd.xls")//no recibe - : /
        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(file)
            wb.write(outputStream)
            Toast.makeText(applicationContext, "OK", Toast.LENGTH_LONG).show()
            println("OK")
        } catch (e: IOException) {
            e.printStackTrace()
            println("$e aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1")
            Toast.makeText(applicationContext, "NO OK", Toast.LENGTH_LONG).show()
            try {
                outputStream!!.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
                println("$ex aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa2")
            }
        }

    }

    fun excel3 (){
        var columnas = arrayOf<String>("articulo", "cod", "cant")


        for (i in db!!.getData()){
            cust = arrayOf(
                arrayOf(
                    "${i.getArticulo()}","${i.getCodbar()}","${i.getCantidad()}"
                )
            )
        }

        println(cust[0][0].toString()+"aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        println(cust[0][1].toString()+"aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        println(cust[0][2].toString()+"aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
        val wb: Workbook = HSSFWorkbook()
        var cell: Cell? = null
        val cellStyle = wb.createCellStyle()
        cellStyle.fillForegroundColor = HSSFColor.LIGHT_BLUE.index
        cellStyle.fillPattern = HSSFCellStyle.SOLID_FOREGROUND

        var hoja: Sheet? = null
        hoja = wb.createSheet("Lista de usuarios")

        var row: Row? = null

        val header = arrayOf("ARTICULO", "CODIGO DE BARRAS", "CANTIDAD")


//        for (i in 0..cust.size) {
//            row = hoja.createRow(i) //se crea la fila
//            for (j in header.indices) {
//                if (i == 0) { //para la cabezera
//                    cell = row.createCell(j) //se crea las celdas para la cabezera
//                    //println("${cell = row.createCell(j)}")
//                    cell.setCellValue(header[j]) // se añade el contenido
//                } else {
//                    cell = row.createCell(j) //se crean celdas del contenido
//                    cell.setCellValue(cust[i - 1][j]) //se añade el contenido
//                    println(cust[i - 1][j])
//                }
//            }
//        }

        for (i in 0..db!!.getData().size) {
            row = hoja.createRow(i) //se crea la fila
            for (j in header.indices) {
                if (i == 0) { //para la cabezera
                    cell = row.createCell(j) //se crea las celdas para la cabezera
                    //println("${cell = row.createCell(j)}")
                    cell.setCellValue(header[j]) // se añade el contenido
                } else {
//                    cell = row.createCell(j) //se crean celdas del contenido
                    row.createCell(0).setCellValue(db!!.getData()[i - 1].getArticulo())//AQUI MANDO LA COLUMNA 0 EN LA CELDA EL CONETENIDO DE MI BASE DE DATOS QU LA RECORRE FOR I
                    row.createCell(1).setCellValue(db!!.getData()[i - 1].getCodbar())//AQUI MANDO LA COLUMNA 1 EN LA CELDA EL CONETENIDO DE MI BASE DE DATOS QU LA RECORRE FOR I
                    row.createCell(2).setCellValue(db!!.getData()[i - 1].getCantidad())
//                    cell.setCellValue(db!!.getData()[i - 1].getCantidad()) //se añade el contenido
//                    cell.setCellValue(db!!.getData()[i - 1].getCodbar()) //se añade el contenido
//                    println(cust[i - 1][j])
                }
            }
        }
//        for (j in header.indices) {
//
//                cell = row?.createCell(j) //se crea las celdas para la cabezera
//                cell?.setCellValue(header[j]) // se añade el contenido
//
//        }
//
//        var rowIndx = 1
//        for (i in db!!.getData()){
//            row = hoja.createRow(rowIndx)
//            row.createCell(0).setCellValue(i.getArticulo())
//            row.createCell(1).setCellValue(i.getCodbar())
//            row.createCell(2).setCellValue(i.getCantidad())
//
//        }

        val file = File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS), "USER.xls")
        var outputStream: FileOutputStream? = null

        try {
            outputStream = FileOutputStream(file)
            wb.write(outputStream)
            Toast.makeText(applicationContext, "OK", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "NO OK", Toast.LENGTH_LONG).show()
            try {
                outputStream!!.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }

    }

//excel androd https://www.youtube.com/watch?v=2m28IQz5LpY&ab_channel=ChekoPeralta
    fun guardarExcel() {

        val wb: Workbook = HSSFWorkbook()
        var cell: Cell? = null
        val cellStyle = wb.createCellStyle()
        cellStyle.fillForegroundColor = HSSFColor.LIGHT_BLUE.index
        cellStyle.fillPattern = HSSFCellStyle.SOLID_FOREGROUND
        var sheet: Sheet? = null
        sheet = wb.createSheet("Lista de usuarios")
        var row: Row? = null
        //aqui aplicare un for del tamaño del arreglo que obtenga de la db para agregar los productos
        row = sheet.createRow(0)
        cell = row.createCell(0)
        cell.setCellValue("CODMERCANTIL")
        cell.cellStyle = cellStyle
        sheet.createRow(1)
        cell = row.createCell(1)
        cell.setCellValue("STOCK")
        cell.cellStyle = cellStyle
        row = sheet.createRow(1)
        cell = row.createCell(0)
        cell.setCellValue("CEMAPA152055")
        cell = row.createCell(1)
        cell.setCellValue("100")
        //asi indico donde se guardara los archivos
        val file = File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOCUMENTS), "CargaProd.xls")//no recibe - : /
        var outputStream: FileOutputStream? = null
        try {
            outputStream = FileOutputStream(file)
            wb.write(outputStream)
            Toast.makeText(applicationContext, "OK", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            println("$e aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1")
            Toast.makeText(applicationContext, "NO OK", Toast.LENGTH_LONG).show()
            try {
                outputStream!!.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
                println("$ex aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa2")
            }
        }
    }

    private fun guardarExcelLocal(){

    }

    private fun solicitarPermiso(){//https://www.youtube.com/watch?v=4Gm_-XvZlpg guit https://github.com/xcheko51x/ApachePOI-Android-Studio
        when {
            ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED -> {
//                guardarExcel()
                excel2()
            }
            shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)->{
                mensajeMostrar("No aceptaste los permisos de escritura")
            }
            else -> {
                requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),PERMISO_SCRITURA)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        when (requestCode) {
            PERMISO_SCRITURA -> {
                //si le dio permiso se eejcuta
                if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    guardarExcel()
                    excel2()
                }
            }
            else -> {// si no dio permiso no ara nada
                super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }


    }

    private fun mensajeMostrar(mensaje: String){
        Toast.makeText(this,mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun consultaExelSqlite() {
        //aqui llamo mi consulta y la recorro para guardarla dentro del excel
        //nota creo que tendre que hacer dos funiones diferentes de guardar excel  esta sigue siendo mas viable
        //nota probar ejecutar esta funcion dentro del solicitar permiso para pasar los parametros de la consulta al excel
    }


}