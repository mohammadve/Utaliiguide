package com.utaliiguides.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.utaliiguides.R
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tv_getStarted.setOnClickListener(this)

        Handler().postDelayed(Runnable {

            var transition = Slide(Gravity.BOTTOM)
            transition.setDuration(500)
            TransitionManager.beginDelayedTransition(rl_splash_main, transition)
            tv_getStarted.visibility = View.VISIBLE
            tv_learnMore.visibility = View.VISIBLE

        }, 1000)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.tv_getStarted -> {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
    }
}