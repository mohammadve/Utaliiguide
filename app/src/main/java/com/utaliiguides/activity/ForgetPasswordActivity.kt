package com.utaliiguides.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.utaliiguides.R
import kotlinx.android.synthetic.main.activity_forget_pass.*

class ForgetPasswordActivity : AppCompatActivity(), View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass)

        toolbar_recovery.title = ""
        toolbar_recovery.setNavigationIcon(R.drawable.arrow_back_black)
        setSupportActionBar(toolbar_recovery)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_recovery.setNavigationOnClickListener { finish() }

        tv_send_otp.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v!!.id){
            R.id.tv_send_otp->{
                var intent = Intent(this@ForgetPasswordActivity, OTPActivity::class.java)
                startActivity(intent)

            }

        }

    }



}