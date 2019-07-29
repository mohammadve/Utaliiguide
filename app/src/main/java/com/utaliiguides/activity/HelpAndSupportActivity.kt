package com.utaliiguides.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.utaliiguides.R
import com.utaliiguides.viewModel.HelpAndSupportViewModel
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.activity_help_and_support.*
import kotlinx.android.synthetic.main.activity_webview.*

class HelpAndSupportActivity : AppCompatActivity(), View.OnClickListener{

    var helpAndSupportViewModel : HelpAndSupportViewModel? =null


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

        helpAndSupportViewModel = ViewModelProviders.of(this).get(HelpAndSupportViewModel::class.java)

        webView_helpSupport.getSettings().setJavaScriptEnabled(true);
        webView_helpSupport.loadUrl("http://3.13.3.42:8000/support");


    /*    helpAndSupportViewModel!!.helpAndSupport(this).observe(this, Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

                if(it.has("data")){

                    var dataObject = it.getAsJsonObject("data")

                    if(dataObject.has("email") && dataObject.has("helpcontact")){

                        tv_contactNumber.setText(dataObject.get("email").asString)
                        tv_emailId.setText(dataObject.get("helpcontact").asString)
                    }
                }
            }
        })*/
    }


    override fun onClick(v: View?) {
        when(v?.id){


        }
    }
}