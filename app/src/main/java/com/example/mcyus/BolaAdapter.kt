package com.example.mcyus

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BolaAdapter (var mContext: Context, var BolaiList: List<DataBola>):
    RecyclerView.Adapter<BolaAdapter.ListViewHolder>() {
    inner class ListViewHolder(var  v: View) : RecyclerView.ViewHolder(v) {

        val imgTT = v.findViewById<ImageView>(R.id.imageData)

        val namaTT = v.findViewById<TextView>(R.id.namaKlub)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BolaAdapter.ListViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var v = inflater.inflate(R.layout.hasilbola, parent, false)
        return ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: BolaAdapter.ListViewHolder, position: Int) {
        val newList = BolaiList[position]

        holder.imgTT.loadImage(newList.imgurl)
        holder.namaTT.text = newList.nama


        holder.v.setOnClickListener {


            val desk = newList.deskripsi
            val imgg = newList.imgurl
            val nama = newList.nama


            val mIntent = Intent(mContext, Detail::class.java)

            mIntent.putExtra("DESKT", desk)

            mIntent.putExtra("IMGT", imgg)
            mIntent.putExtra("NAMAT", nama)

            mContext.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return BolaiList.size
    }
}

