package com.example.hw6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap

class CatReciever(var mainActivity: MainActivity): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val image = intent?.getParcelableExtra<Bitmap>("file")
        if (image != null) {
            mainActivity.addCats(image)
        }
    }
}