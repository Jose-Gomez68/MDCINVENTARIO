package com.example.mdcinventario.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mdcinventario.R
import com.example.mdcinventario.controller.ItemsAdapter.*

class ItemsAdapter: RecyclerView.Adapter<ViewHolder2>(), Filterable {

    var itemModalList = ArrayList<ItemsModal>()//arreglo para pasar los datos del arreglo de mi data class
    var itemModalListFilter = ArrayList<ItemsModal>()//arreglo para el filtro de busqueda

    private lateinit var miListener: onItemClickListener//metodo clic recycler

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        miListener = listener
    }

    fun setData(itemModalList: ArrayList<ItemsModal>){
        this.itemModalList = itemModalList;
        this.itemModalListFilter = itemModalList;
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder2 {                                //paso miListener el click
        return ViewHolder2(LayoutInflater.from(parent.context).inflate(R.layout.cardview_lista_almacenes,parent, false),miListener)
    }

    override fun onBindViewHolder(holder: ViewHolder2, position: Int) {
       val itemModal = itemModalList[position];
        holder.tvAlmacen.text = itemModal.nombre
        holder.tvTienda.text = itemModal.tienda
    }

    override fun getItemCount(): Int {
        return itemModalList.size
    }

    class ViewHolder2(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
//        var nombre = itemView.findViewById<EditText>(R.id.tvAlmacen)
//        var tienda = itemView.findViewById<EditText>(R.id.tvTienda)
            var tvAlmacen: TextView
            //        var lvAlmacen: ListView
            var tvTienda: TextView

            init {
                tvAlmacen = itemView.findViewById(R.id.tvAlmacen)
                tvTienda = itemView.findViewById(R.id.tvTienda)
    //            lvAlmacen = itemView.findViewById(R.id.lvAlmacenes)
                //evento clic del recycler view al dar clic en un objeto de la lista
                itemView.setOnClickListener{
                    listener.onItemClick(adapterPosition)
                }
            }
    }

    override fun getFilter(): Filter {//SEARCH O BUSCADOR FILTRO
        return object: Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {

                val filterResult = FilterResults()
                if (p0 == null || p0.length < 0){
                    filterResult.count = itemModalListFilter.size
                    filterResult.values = itemModalListFilter
                }else{
                    var searchChr = p0.toString()//.toLowerCase() //lowercase es para busques solo en minusculas tienes que recibir tu arreglo de db en minusculas o mayusculas y usas las funciones de mayuscula o minusculas

                    val itemModal2 = ArrayList<ItemsModal>()
                    for (item in itemModalListFilter){
                        //en este if agrege que tambien busqie por el nomnbre de tienda si no pongo el nombre de tienda lo borro de aqui tambien
                        if (item.nombre.contains(searchChr) || item.tienda.contains(searchChr)){
                            itemModal2.add(item)
                        }
                    }
                    filterResult.count = itemModal2.size
                    filterResult.values = itemModal2
                }
                return filterResult;
            }                           //aqui en le var es p1, le cambie el nombre a filterResult
            override fun publishResults(p0: CharSequence?, filterResults: FilterResults?) {

                itemModalList = filterResults!!.values as ArrayList<ItemsModal>
                notifyDataSetChanged()

            }

        }
    }

}