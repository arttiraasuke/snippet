package com.example.hw6

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cat_square.view.*
import java.net.URL

class CatAdapter(var imageList: MutableList<Bitmap>): RecyclerView.Adapter<CatAdapter.CatViewHolder>() {


    class CatViewHolder(v: View): RecyclerView.ViewHolder(v)



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.cat_square, parent, false)
            return CatViewHolder(view)
        }

        override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
            val data = imageList[position]
            holder.itemView.catPic.setImageBitmap(data)
        }

        override fun getItemCount(): Int {
            return imageList.size
        }




}

