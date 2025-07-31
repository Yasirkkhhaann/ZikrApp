package com.example.zikrapp.ui.components

import android.content.Context
import android.media.MediaPlayer
import com.example.zikrapp.R


object SoundPlayer{
    fun playclick(context: Context){
        val mediaPlayer = MediaPlayer.create(context, R.raw.click3)
        mediaPlayer.setOnCompletionListener { it.release() }
        mediaPlayer.start()
    } 
}