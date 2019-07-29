package com.utaliiguides.activity

import android.app.DatePickerDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import com.google.android.material.appbar.AppBarLayout
import com.transitionseverywhere.Recolor
import com.transitionseverywhere.TransitionManager
import com.utaliiguides.R
import kotlinx.android.synthetic.main.activity_create_new_tour.*
import kotlinx.android.synthetic.main.activity_trip_detail.*
import kotlinx.android.synthetic.main.activity_trip_detail.appBar
import kotlinx.android.synthetic.main.activity_trip_detail.toolbar
import java.text.SimpleDateFormat
import java.util.*


class CreateNewTourActivity : AppCompatActivity(), View.OnClickListener {
    var c = Calendar.getInstance()
    var year = c.get(Calendar.YEAR)
    var month = c.get(Calendar.MONTH)
    var day = c.get(Calendar.DAY_OF_MONTH)

    var ddStartDate: Date? = null
    var ddEndDate: Date? = null

    var startDateStr: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_tour)
        setupUI()
        setupClickListener()
    }

    private fun setupUI()
    {
        appBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {

                if (verticalOffset == 0) {

                    var transition = Recolor()
                    transition.setDuration(500)

                    TransitionManager.beginDelayedTransition(
                        toolbar,
                        transition
                    )

                    toolbar.setBackgroundDrawable(ColorDrawable(resources.getColor(android.R.color.transparent)))
                    tv_toolbarTitleText1.text=""
                    setMargins(cl_content, 20, 20, 20, 20)
                } else if (Math.abs(verticalOffset) == appBarLayout!!.getTotalScrollRange()) {

                    var transition = Recolor()
                    transition.setDuration(500)

                    TransitionManager.beginDelayedTransition(
                        toolbar,
                        transition
                    )
                    toolbar.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorPrimary)))
                    tv_toolbarTitleText1.text=resources.getString(R.string.create_new_tour)
                    setMargins(cl_content, 20, 140, 20, 20)
                }
            }
        })


        radioGroup.check(privateTourRadioButton.id)

        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener{

            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

                if(checkedId == R.id.privateTourRadioButton){
                    cl_poolTour.visibility = View.GONE
                    cl_privateTour.visibility = View.VISIBLE
                    textTourCreationType.setText("After private tour type is created you cannot accept pool type requests")
                }
                else if(checkedId == R.id.poolTourRadioButton){
                    cl_poolTour.visibility = View.VISIBLE
                    cl_privateTour.visibility = View.GONE
                    textTourCreationType.setText("After pool is created you can accept pool type requests for these particular day.")
                }

            }

        })




    }

    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is ViewGroup.MarginLayoutParams) {
            val p = view.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }

    private fun setupClickListener()
    {
        iv_cancel1.setOnClickListener(this)
        iv_cancel.setOnClickListener(this)
        tvStartDate.setOnClickListener(this)
        tvEndDate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.iv_cancel ->
            {
                finish()
            }
            R.id.iv_toolbarMore ->
            {

            }
            R.id.tvStartDate->{

                val datePickerDialog = DatePickerDialog(
                    this,
                    R.style.DialogTheme,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                        tvStartDate.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year)

                        startDateStr = ("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year)
                        var sdf = SimpleDateFormat("dd/MM/yyyy")
                        ddStartDate = sdf.parse(startDateStr)


                    }, year, month, day)

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000)  // disable the previous date from the calendar
                datePickerDialog.show()

            }

            R.id.tvEndDate ->{

                if (tvStartDate.text.toString().equals("")) {
                    Toast.makeText(this, "Please choose arrival date first", Toast.LENGTH_SHORT).show()
                } else {

                    val datePickerDialog = DatePickerDialog(
                        this,
                        R.style.DialogTheme,
                        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                            tvEndDate.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year)
                        }, year, month, day)
                    datePickerDialog.getDatePicker().setMinDate(ddStartDate!!.time)
                    datePickerDialog.show()

                }


            }




        }
    }
}