package com.example.imarticus_class_3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imarticus_class_3.database.item


/*val context: Context,*/
class adapter(private val list: List<item>): RecyclerView.Adapter<adapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val id=view.findViewById<TextView>(R.id.textView2)
        val name=view.findViewById<TextView>(R.id.textView3)
        val price=view.findViewById<TextView>(R.id.textView4)
        val quantity=view.findViewById<TextView>(R.id.textView5)
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.oneblock,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder , position: Int) {
        val item=list[position]
        holder.id.text=item.id.toString()
        holder.name.text=item.itemName
        holder.price.text=item.itemPrice.toString()
        holder.quantity.text=item.quantityInStock.toString()
    }
}