package com.example.hw6

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

private lateinit var myAdapter: CatAdapter


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        myAdapter = CatAdapter(mutableListOf())

        rvCats.layoutManager = GridLayoutManager(this, 3)
        rvCats.adapter = myAdapter

        grabCatsButton.setOnClickListener {
            myAdapter.imageList.clear()
            Log.i("CatLoaderDebug", "loading")
            val intent = Intent(this, CatLoader::class.java)
            intent.putExtra("number", 2)
            intent.putExtra("dispatcher", "IO")
            startService(intent)
        }

        letCatsGo.setOnClickListener{
            myAdapter.imageList.clear()
            myAdapter.notifyDataSetChanged()
        }

        val reciever = CatReciever(this)
        val filter = IntentFilter()
        filter.addAction("file")
        registerReceiver(reciever, filter)

    }

    fun addCats(image: Bitmap){
        myAdapter.imageList.add(image)
        myAdapter.notifyDataSetChanged()
    }

}
