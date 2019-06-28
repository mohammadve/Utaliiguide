package com.utaliiguides.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.utaliiguides.R
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.activity_otp.*

class OTPActivity : AppCompatActivity(), View.OnClickListener {
    var OTP : String =" "




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)


        tv_verify_btn.setOnClickListener(this)
        tv_resend_otp.setOnClickListener(this)

        initViews()


    }

    private fun initViews() {

        getEditText()

        tv_verify_btn.setOnClickListener(this)
        tv_resend_otp.setOnClickListener(this)
    }


    private fun getEditText() {

        et_otp_1.addTextChangedListener(object: TextWatcher {

            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {


            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {

            }
            override fun afterTextChanged(s: Editable) {
                if (et_otp_1.getText().toString().length === 1) {
                    et_otp_2.requestFocus()
                }

            }
        })



        et_otp_2.addTextChangedListener(object: TextWatcher {

            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {

            }
            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {


            }
            override fun afterTextChanged(s: Editable) {

                if (et_otp_2.getText().toString().length === 1) {
                    et_otp_3.requestFocus()
                }
                else if(et_otp_2.text.toString().length == 0){
                    et_otp_1.requestFocus()
                }

            }
        })


        et_otp_3.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if(et_otp_3.text.toString().length == 1){
                    et_otp_4.requestFocus()
                }
                else if(et_otp_3.text.toString().length == 0){
                    et_otp_2.requestFocus()
                }
            }


        })


        et_otp_4.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                if(et_otp_4.text.toString().length == 0){
                    et_otp_3.requestFocus()
                }
            }

        })

    }



    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tv_verify_btn->{
                val intent = Intent(this, ResetPasswordActivity::class.java)
                startActivity(intent)
            }

            R.id.tv_resend_otp->{

            }

        }

    }



    fun checkValidations(): Boolean {

        if (!Utils.isInternetAvailable(this)) {
            Utils.showToast(this, resources.getString(R.string.msg_no_internet))
            return false
        } else if (et_otp_1.text!!.length == 0 || et_otp_2.text!!.length == 0 || et_otp_3.text!!.length == 0 || et_otp_4.text!!.length == 0) {
            Utils.showToast(this, resources.getString(R.string.msg_invalid_otp))
            return false
        }
        else if (!OTP.equals(et_otp_1.text!!.toString() + et_otp_2.text!!.toString() + et_otp_3.text!!.toString() + et_otp_4.text!!.toString())) {
            Utils.showToast(this, resources.getString(R.string.msg_invalid_otp))
            return false
        }

        return true

    }





}