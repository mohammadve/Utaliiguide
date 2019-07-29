package com.utaliiguides.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.utaliiguides.R
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.helper.ConstantFragmentName
import com.utaliiguides.viewModel.AboutAppViewModel


import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_about_app.*

import android.webkit.WebView





class AboutAppFragment : Fragment() {

    var  aboutAppViewModel : AboutAppViewModel?= null
    var url : String ="http://3.13.3.42:8000/info"
    var webView_aboutApp : WebView ?= null



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_about_app, container, false)

       webView_aboutApp = view.findViewById(R.id.webView_aboutApp) as WebView
        updateToolbar()
        return view
    }



    private fun updateToolbar()
    {
        (activity as HomeActivity).setToolbarTitle(ConstantFragmentName.ABOUT_APP_FRAGMENT)
        (activity as HomeActivity).setToolbarMenuVisibility(true)
        (activity as HomeActivity).setToolbarBackVisibility(false)
        (activity as HomeActivity).setToolbarNotificationVisibility(false)
        (activity as HomeActivity).setToolbarPaymentSettingVisibility(false)


        webView_aboutApp!!.getSettings().setJavaScriptEnabled(true)
        webView_aboutApp!!.loadUrl(url)



        aboutAppViewModel = ViewModelProviders.of(this).get(AboutAppViewModel::class.java)

       // getAboutAppData()

    }

    private fun getAboutAppData() {
        aboutAppViewModel!!.aboutApp(activity!!).observe(this, Observer {
            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){
                if(it.has("data")){
                    var dataObj = it.getAsJsonObject("data")
                    if(dataObj.has("appinfo")){
                     /*   webView_aboutApp!!.getSettings().setJavaScriptEnabled(true);
                        webView_aboutApp!!.loadUrl(dataObj.get("appinfo").asString);*/
                    }
                }
            }
        })
    }


    private fun initView() {

    }



}