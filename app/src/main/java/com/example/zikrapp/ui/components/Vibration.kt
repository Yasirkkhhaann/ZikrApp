package com.example.zikrapp.ui.components

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi

class Vibration {

    @RequiresApi(Build.VERSION_CODES.O)
    fun vibratee(context: Context, duration: Long) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        vibrator.let {
                vibration ->
            if(vibration.hasVibrator()){

                val vibrationEffect = VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE)
                vibration.vibrate(vibrationEffect)

            }
        }
    }
}