package com.utaliiguides.activity

import android.app.DatePickerDialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginTop
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.AppBarLayout
import com.transitionseverywhere.Recolor
import com.transitionseverywhere.TransitionManager
import com.utaliiguides.R
import com.utaliiguides.viewModel.CreateNewTourViewModel
import com.utalli.helpers.Utils
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

    var createNewTourViewModel : CreateNewTourViewModel ?= null

    var tour_price : Int =0
    var requesttype : Int = 0






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_tour)

        createNewTourViewModel = ViewModelProviders.of(this).get(CreateNewTourViewModel::class.java)

        getMyCreatedTourData()


        setupUI()
        setupClickListener()
    }




    private fun getMyCreatedTourData() {

        createNewTourViewModel!!.getMyCreatedTourData(this).observe(this, androidx.lifecycle.Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1"))
            {

                if(it.get("data").isJsonNull)
                {
                    Utils.showLog("my created tour DATA is null ... ")

                }
                else if(!it.get("data").isJsonNull)
                {
                   Utils.showLog("my created tour DATA is not null ... ")

                    var dataObj = it.getAsJsonObject("data")

                    if(dataObj.has("tourtype")){

                        if(dataObj.get("tourtype").asString.equals("1")){
                            radioGroup.check(privateTourRadioButton.id)
                            et_privatePrice.setText(""+dataObj.get("tourcost").asInt)
                        }
                        else if(dataObj.get("tourtype").asString.equals("2")){
                            radioGroup.check(poolTourRadioButton.id)
                            tvStartDate.setText(dataObj.get("tourStartdate").asString)
                            tvEndDate.setText(dataObj.get("tourEnddate").asString)
                            et_noOfPeopleInPool.setText(dataObj.get("no_of_members").asString)
                            et_poolPrice.setText("$ "+dataObj.get("tourcost").asString)
                            et_noteMessage.setText(dataObj.get("TourNotes").asString)
                        }
                    }
                }
            }
        })
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


            // radioGroup.check(privateTourRadioButton.id)

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

        tvCreatePrivateTour.setOnClickListener(this)
        tvCreatePoolTour.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.iv_cancel -> {
                finish()
            }

            R.id.iv_toolbarMore -> {

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

            R.id.tvCreatePoolTour ->{

                if(tvStartDate.text.toString().equals("")){
                    Utils.showToast(this, "Please choose start date")
                }else if(tvEndDate.text.toString().equals("")){
                    Utils.showToast(this, "Please choose end date")
                }else if(et_noOfPeopleInPool.text.toString().equals("")){
                    Utils.showToast(this, "Please enter number of people in this pool")
                }else if(et_poolPrice.text.toString().equals("")){
                    Utils.showToast(this, "Please enter price for the pool (per day)")
                }else if(et_noteMessage.text.toString().equals("")){
                    Utils.showToast(this, "Please enter description for this pool")
                } else{

                    if(et_poolPrice.text.toString().contains("$")){
                        var pool_price = et_poolPrice.text.toString().substring(2)
                        tour_price = pool_price.toInt()
                    } else{
                        tour_price = et_poolPrice.text.toString().toInt()
                    }
                    requesttype = 2
                    createPoolTourRequest()
                }
            }

            R.id.tvCreatePrivateTour -> {
                if(et_privatePrice.text.toString().equals("")){
                    Utils.showToast(this,"Please enter the private price (per day)")

                }else{
                    if(et_privatePrice.text.toString().contains("$")){
                        var private_price = et_privatePrice.text.toString().substring(2)
                        tour_price = private_price.toInt()
                    } else{
                        tour_price = et_privatePrice.text.toString().toInt()
                    }
                    requesttype = 1
                    createPrivateTourRequest()
                }
            }



        }

    }



    private fun createPoolTourRequest() {
        var tour_mem_id =0
        var userId =0
        createNewTourViewModel!!.createPoolTour(this,requesttype,
            tour_mem_id,
            tvStartDate.text.toString(),
            tvEndDate.text.toString(),
            et_noteMessage.text.toString(),
            Integer.parseInt(et_noOfPeopleInPool.text.toString()),
            userId,
            tour_price
        ).observe(this, androidx.lifecycle.Observer {

            if(it != null && it.has("status") && it.get("status").asString.equals("1")){

                Utils.showToast(this, it.get("message").asString)

                if(it.has("data")){

                    var dataObject = it.getAsJsonObject("data")

                    if(dataObject.get("tourtype").asString.equals("2")){
                        et_privatePrice.setText("")
                    }
                }
            }
        })

    }



    private fun createPrivateTourRequest() {
        createNewTourViewModel!!.createPrivateTour(this, requesttype, tour_price).observe(this, androidx.lifecycle.Observer {

            if(it!= null && it.has("status") && it.get("status").asString.equals("1")){

                  Utils.showToast(this, it.get("message").asString)

                if(it.has("data")){

                    var dataObject = it.getAsJsonObject("data")

                    if(dataObject.get("tourtype").asString.equals("1")){

                        tvStartDate.setText("")
                        tvEndDate.setText("")
                        et_noOfPeopleInPool.setText("")
                        et_poolPrice.setText("")
                        et_noteMessage.setText("")

                    }
                }
            }
        })

    }





}