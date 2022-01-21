package com.example.jsonparsefromassets.recyclerviewAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonparsefromassets.R
import com.example.jsonparsefromassets.model.Avenger
import com.example.jsonparsefromassets.model.AvengersModelClass
import java.util.ArrayList

class AvengerItemAdapter(context: Context, val data: ArrayList<Avenger>) : RecyclerView.Adapter<AvengerItemAdapter.AvengerItemViewHolder>() {

//    var data = arrayListOf<Avenger>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvengerItemViewHolder {
        return AvengerItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: AvengerItemViewHolder, position: Int) {
        val item = data[position] // get the item which we will displayed
        holder.bind(item) // call(bind) viewHolder's bind method for item in avengerList
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class AvengerItemViewHolder(val rootView: CardView) : RecyclerView.ViewHolder(rootView) {
        // get reference to the textView which represent name, email, mobile
        val avengerName = rootView.findViewById<TextView>(R.id.avenger_name)
        val avengerEmail = rootView.findViewById<TextView>(R.id.avenger_email)
        val avengerMobile = rootView.findViewById<TextView>(R.id.avenger_mobile)

        companion object {
            // create helper method for instantiate layoutInflater()
            fun inflateFrom(parent: ViewGroup): AvengerItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.avenger_list_layout, parent, false) as CardView
                return AvengerItemViewHolder(view)
            }
        }
        fun bind(item: Avenger) {
            avengerName.text = item.name
            avengerEmail.text = item.email
            avengerMobile.text = item.contact.mobile
        }
    }
}