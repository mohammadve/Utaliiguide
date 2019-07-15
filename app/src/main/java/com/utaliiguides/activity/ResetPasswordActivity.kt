package com.utaliiguides.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.utaliiguides.R
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity(), View.OnClickListener{

    var showPassword: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)


        toolbar_recoveryPass.title = ""
        toolbar_recoveryPass.setNavigationIcon(R.drawable.arrow_back_black)
        setSupportActionBar(toolbar_recoveryPass)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_recoveryPass.setNavigationOnClickListener { finish() }

        initViews()
    }

    private fun initViews() {

        iv_password_toggle.setOnClickListener(this)
        iv_reTypePassword_toggle.setOnClickListener(this)
        tv_save.setOnClickListener(this)


    }




    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tv_save->{

               resetPassword()

            }

            R.id.iv_reTypePassword_toggle->{
                if (showPassword) {
                    et_retype_password.transformationMethod = PasswordTransformationMethod.getInstance()

                    iv_reTypePassword_toggle.setImageResource(R.mipmap.ic_eye_open)
                } else {

                    et_retype_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    iv_reTypePassword_toggle.setImageResource(R.mipmap.ic_eye_closed)
                }
                et_retype_password.setSelection(et_retype_password.text!!.length)
                showPassword = !showPassword

            }

            R.id.iv_password_toggle ->{
                if (showPassword) {
                    et_password.transformationMethod = PasswordTransformationMethod.getInstance()

                    iv_password_toggle.setImageResource(R.mipmap.ic_eye_open)
                } else {

                    et_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    iv_password_toggle.setImageResource(R.mipmap.ic_eye_closed)
                }
                et_password.setSelection(et_password.text!!.length)
                showPassword = !showPassword

            }



        }

    }


    private fun resetPassword() {
        val intent = Intent(this@ResetPasswordActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


    public fun checkValidations() : Boolean{

        if (!Utils.isInternetAvailable(this)) {
            Utils.showToast(this, resources.getString(R.string.msg_no_internet))
            return false
        }
        else if(et_password.text!!.length == 0){
            Utils.showToast(this, resources.getString(R.string.msg_empty_pass))
            return false
        }
        else if(et_password.text!!.length < 6){
            Utils.showToast(this, resources.getString(R.string.msg_invalid_pass))
            return false
        }
        else if(!(et_password.text.toString()).equals(et_retype_password.text.toString())){
            Utils.showToast(this, resources.getString(R.string.msg_not_same_pass))
            return false
        }

        return true
    }

}