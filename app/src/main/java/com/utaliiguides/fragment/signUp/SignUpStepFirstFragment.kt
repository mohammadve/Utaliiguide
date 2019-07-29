package com.utaliiguides.fragment.signUp

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.utaliiguides.R
import com.utaliiguides.activity.LoginActivity
import com.utaliiguides.activity.SignUpActivity
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.activity_reset_password.*
import kotlinx.android.synthetic.main.fragment_signup_step_first.*
import java.util.*

class SignUpStepFirstFragment : Fragment(), View.OnClickListener {

    var c = Calendar.getInstance()
    var year = c.get(Calendar.YEAR)
    var month = c.get(Calendar.MONTH)
    var day = c.get(Calendar.DAY_OF_MONTH)
    var genderType: String = ""

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup_step_first, container, false)
        return view
    }

    private fun initView()
    {
        btn_signUp.setOnClickListener(this)
        tv_sign_in.setOnClickListener(this)
        et_dateOfBirth.setOnClickListener(this)
        cl_first_male.setOnClickListener(this)
        cl_second_female.setOnClickListener(this)
        cl_third_other.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_signUp ->{
                (activity as SignUpActivity).displayFragment(2)
            }
            R.id.tv_sign_in ->{
                val intent = Intent(activity!!, LoginActivity::class.java)
                startActivity(intent)
                activity!!.finish()
            }
            R.id.et_dateOfBirth -> {
                val datePickerDialog = DatePickerDialog(activity!!,R.style.DialogTheme, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    et_dateOfBirth.setText("" + dayOfMonth + "-" + (monthOfYear+1) + "-" + year)
                    //dob = ("" + year + "-" + (monthOfYear+1) + "-" + dayOfMonth)
                }, year, month, day)

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000)

                datePickerDialog.show()
            }
            R.id.cl_first_male ->{
                genderType = "Male"
                cl_first_male.setBackgroundDrawable(ContextCompat.getDrawable(activity!!,R.drawable.orange_background))
                cl_second_female.setBackgroundColor(resources.getColor(R.color.colorWhite))
                cl_third_other.setBackgroundColor(resources.getColor(R.color.colorWhite))

                tv_male.setTextColor(Color.parseColor("#ffffff"))
                tv_female.setTextColor(Color.parseColor("#000000"))
                tv_other.setTextColor(Color.parseColor("#000000"))
            }
            R.id.cl_second_female -> {
                genderType = "Female"
                cl_first_male.setBackgroundColor(resources.getColor(R.color.colorWhite))
                cl_second_female.setBackgroundDrawable(ContextCompat.getDrawable(activity!!,R.drawable.orange_background))
                cl_third_other.setBackgroundColor(resources.getColor(R.color.colorWhite))

                tv_male.setTextColor(Color.parseColor("#000000"))
                tv_female.setTextColor(Color.parseColor("#ffffff"))
                tv_other.setTextColor(Color.parseColor("#000000"))

            }
            R.id.cl_third_other ->{
                genderType = "Other"
                cl_first_male.setBackgroundColor(resources.getColor(R.color.colorWhite))
                cl_second_female.setBackgroundColor(resources.getColor(R.color.colorWhite))
                cl_third_other.setBackgroundDrawable(ContextCompat.getDrawable(activity!!,R.drawable.orange_background))

                tv_male.setTextColor(Color.parseColor("#000000"))
                tv_female.setTextColor(Color.parseColor("#000000"))
                tv_other.setTextColor(Color.parseColor("#ffffff"))
            }
        }
    }

    private fun isValidate(): Boolean {
        var isValid: Boolean = false
        if (TextUtils.isEmpty(et_name.text.toString())) {
            Utils.showToast(activity!!, getString(R.string.msg_empty_user_name))
            isValid = false
        } else if (TextUtils.isEmpty(genderType)) {
            Utils.showToast(activity!!, getString(R.string.msg_empty_gender))
            isValid = false
        } else if (TextUtils.isEmpty(et_dateOfBirth.text.toString())) {
            Utils.showToast(activity!!, getString(R.string.msg_empty_date_of_birth))
            isValid = false
        } else if (TextUtils.isEmpty(et_email_id.text.toString())) {
            Utils.showToast(activity!!, resources.getString(R.string.msg_empty_email_id))
            isValid = false
        } else if (Utils.isEmailValid(et_email_id.text.toString())) {
            Utils.showToast(activity!!, resources.getString(R.string.msg_invalid_email_id))
            isValid = false
        } else if (TextUtils.isEmpty(et_mobileNumber.text.toString())) {
            Utils.showToast(activity!!, resources.getString(R.string.msg_empty_mobile_no))
            isValid = false
        }  else if (TextUtils.isEmpty(et_newPassword.text.toString())) {
            Utils.showToast(activity!!, resources.getString(R.string.msg_empty_pass))
            isValid = false
        } else if (et_newPassword.text.toString().length < 6) {
            Utils.showToast(activity!!, resources.getString(R.string.msg_invalid_pass))
            isValid = false
        } else if (TextUtils.isEmpty(et_reEnterNew_Password.text.toString())) {
            Utils.showToast(activity!!, resources.getString(R.string.msg_empty_confirm_password))
            isValid = false
        } else if (!(et_newPassword.text.toString()).equals(et_reEnterNew_Password.text.toString())) {
            Utils.showToast(activity!!, resources.getString(R.string.msg_password_does_not_match))
            isValid = false
        } else {
            isValid = true
            putAllDataToFieldMap()
        }
        return isValid
    }

    private fun putAllDataToFieldMap() {
        val mFieldMap = (activity as SignUpActivity).getGuideRegisterFieldMap()
        try {
            mFieldMap!!.put("name", et_name.text.toString())
            mFieldMap.put("gender", genderType)
            //mFieldMap.put("dob", et_dateOfBirth.text.toString())
            mFieldMap.put("email", et_email_id.text.toString())
            mFieldMap.put("mobile_no", et_mobileNumber.text.toString())
            mFieldMap.put("password", et_newPassword.text.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}