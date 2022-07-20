package com.example.hw6

import android.app.Service
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class CatLoader: Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val number = intent!!.getIntExtra("number", 0)
        val dispatcher = intent.getStringExtra("dispatcher")
        Log.i("CatLoaderDebugClass", "in catloader")
        val url = URL("https://cataas.com/cat?width=400&height=400")

        if (dispatcher.equals("IO"))
            repeat(number) {
                val scope = CoroutineScope(Dispatchers.IO)
                scope.launch {
                    val fullBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                    val ratio = fullBitmap.width.toDouble() / fullBitmap.height
                    val scaledBitmap = Bitmap.createScaledBitmap(fullBitmap, (800*ratio).toInt(),800, false)
                    val done = Intent("file")
                    done.putExtra("file", scaledBitmap)
                    sendBroadcast(done)
                }
            }

        Log.i("CatLoaderDebug", "loading")
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
