package com.utaliiguides.fragment.signUp

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.utaliiguides.R
import com.utaliiguides.activity.LoginActivity
import com.utaliiguides.activity.SignUpActivity
import kotlinx.android.synthetic.main.fragment_signup_step_first.*
import java.util.*

class SignUpStepFirstFragment : Fragment(), View.OnClickListener {

    var c = Calendar.getInstance()
    var year = c.get(Calendar.YEAR)
    var month = c.get(Calendar.MONTH)
    var day = c.get(Calendar.DAY_OF_MONTH)

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
                cl_first_male.setBackgroundDrawable(ContextCompat.getDrawable(activity!!,R.drawable.orange_background))
                cl_second_female.setBackgroundColor(resources.getColor(R.color.colorWhite))
                cl_third_other.setBackgroundColor(resources.getColor(R.color.colorWhite))

                tv_male.setTextColor(Color.parseColor("#ffffff"))
                tv_female.setTextColor(Color.parseColor("#000000"))
                tv_other.setTextColor(Color.parseColor("#000000"))

            }
            R.id.cl_second_female -> {
                cl_first_male.setBackgroundColor(resources.getColor(R.color.colorWhite))
                cl_second_female.setBackgroundDrawable(ContextCompat.getDrawable(activity!!,R.drawable.orange_background))
                cl_third_other.setBackgroundColor(resources.getColor(R.color.colorWhite))

                tv_male.setTextColor(Color.parseColor("#000000"))
                tv_female.setTextColor(Color.parseColor("#ffffff"))
                tv_other.setTextColor(Color.parseColor("#000000"))

            }
            R.id.cl_third_other ->{
                cl_first_male.setBackgroundColor(resources.getColor(R.color.colorWhite))
                cl_second_female.setBackgroundColor(resources.getColor(R.color.colorWhite))
                cl_third_other.setBackgroundDrawable(ContextCompat.getDrawable(activity!!,R.drawable.orange_background))

                tv_male.setTextColor(Color.parseColor("#000000"))
                tv_female.setTextColor(Color.parseColor("#000000"))
                tv_other.setTextColor(Color.parseColor("#ffffff"))
            }
        }
    }
}