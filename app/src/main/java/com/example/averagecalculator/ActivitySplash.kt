package com.example.averagecalculator

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView

class ActivitySplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) //Bu activity çalıştığında bu layoutla beraber çalışsın


        var bottomtoUpButton = AnimationUtils.loadAnimation(this,R.anim.buttonappearsfrombottom)
        var uptoBottomIv = AnimationUtils.loadAnimation(this,R.anim.buttonappearsfromtop)
        var buttonGoesBottom = AnimationUtils.loadAnimation(this,R.anim.buttongoesbottom)
        var buttonGoesTop = AnimationUtils.loadAnimation(this,R.anim.buttongoestop)

        var btnX: ImageButton = findViewById(R.id.btnX)
        btnX.animation=bottomtoUpButton

        var iv_Baloon: ImageView = findViewById(R.id.iv_Baloon)
        iv_Baloon.animation = uptoBottomIv

        btnX.setOnClickListener {
            btnX.startAnimation(buttonGoesBottom)
            iv_Baloon.startAnimation(buttonGoesTop)


            object : CountDownTimer(1000,1000){
                override fun onTick(p0: Long) {

                }

                override fun onFinish() {
                    var intt = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intt)
                }

            }.start() //anonim inner class object oluşturduk




        }
    }
}