package com.utaliiguides.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.utaliiguides.R
import kotlinx.android.synthetic.main.activity_help_and_support.*

class HelpAndSupportActivity : AppCompatActivity(), View.OnClickListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_and_support)

        toolbar.title = ""
        toolbar.setNavigationIcon(R.mipmap.ic_back_white)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }

        initView()
    }



    private fun initView() {

    }


    override fun onClick(v: View?) {
        when(v?.id){

        }
    }
}