package com.utaliiguides.fragment.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.utaliiguides.R
import com.utaliiguides.activity.HelpAndSupportActivity
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.activity.WebViewActivity
import com.utaliiguides.helper.ConstantFragmentName
import com.utaliiguides.viewModel.AppSettingsViewModel
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.fragment_app_settings.*

class AppSettingsFragment : Fragment(),View.OnClickListener {
    var appSettingsViewModel : AppSettingsViewModel?= null




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_app_settings, container, false)
        updateToolbar()
        return view
    }

    private fun updateToolbar()
    {
        (activity as HomeActivity).setToolbarTitle(ConstantFragmentName.APP_SETTINGS_FRAGMENT)
        (activity as HomeActivity).setToolbarMenuVisibility(true)
        (activity as HomeActivity).setToolbarBackVisibility(false)
        (activity as HomeActivity).setToolbarNotificationVisibility(false)
        (activity as HomeActivity).setToolbarPaymentSettingVisibility(false)

        appSettingsViewModel = ViewModelProviders.of(this).get(AppSettingsViewModel::class.java)

    }

    private fun initView()
    {
        tv_help_Support.setOnClickListener(this)
        tv_privacyPolicy.setOnClickListener(this)
        tv_aboutUs.setOnClickListener(this)

    }



    override fun onClick(v: View?) {

        when(v?.id){
            R.id.tv_help_Support ->{
                var intent = Intent(activity, HelpAndSupportActivity::class.java)
                startActivity(intent)
            }

            R.id.tv_privacyPolicy ->{
               // getPrivacyPolicyData()
                var intent = Intent(activity, WebViewActivity::class.java)
                intent.putExtra("ScreenType","Privacy Policy")
                intent.putExtra("Url","http://3.13.3.42:8000/policy")
                startActivity(intent)
            }

            R.id.tv_aboutUs ->{
             //   getAboutUsData()
                var intent = Intent(activity, WebViewActivity::class.java)
                intent.putExtra("ScreenType","About Us")
                intent.putExtra("Url","http://3.13.3.42:8000/aboutus")
                startActivity(intent)
            }

        }

    }

    private fun getPrivacyPolicyData() {

        appSettingsViewModel!!.privacyPolicy(activity!!).observe(this, Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){


                if(it.has("data")){

                    var dataObject = it.getAsJsonObject("data")

                    if(dataObject.has("privacy_policy") && !dataObject.get("privacy_policy").isJsonNull){

                        var intent = Intent(activity, WebViewActivity::class.java)
                        intent.putExtra("ScreenType","Privacy Policy")
                        intent.putExtra("Url",dataObject.get("privacy_policy").asString)
                        startActivity(intent)
                    } else{

                        Utils.showToast(activity!!, resources.getString(R.string.msg_common_error))
                    }
                }
            }
        })
    }

    private fun getAboutUsData() {

        appSettingsViewModel!!.aboutUs(activity!!).observe(this, Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

                if(it.has("data")){

                    var dataObject = it.getAsJsonObject("data")

                    if(dataObject.has("aboutus") && !dataObject.get("aboutus").isJsonNull){

                        var intent = Intent(activity, WebViewActivity::class.java)
                        intent.putExtra("ScreenType","About Us")
                        intent.putExtra("Url",dataObject.get("aboutus").asString)
                        startActivity(intent)
                    } else{

                        Utils.showToast(activity!!, resources.getString(R.string.msg_common_error))
                    }

                }
            }
        })
    }


}