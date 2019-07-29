package com.utaliiguides.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utaliiguides.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebViewActivity : AppCompatActivity(){

    var screenType : String?= null
    var url : String?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        screenType = intent.getStringExtra("ScreenType")
        url = intent.getStringExtra("Url")


        toolbar_webView.title = ""
        toolbar_webView.setNavigationIcon(R.drawable.arrow_back_white)
        setSupportActionBar(toolbar_webView)
        toolbar_webView.setNavigationOnClickListener { finish() }

        toolbar_webview_text.text = screenType

        initViews()



    }


    private fun initViews() {


        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }







}