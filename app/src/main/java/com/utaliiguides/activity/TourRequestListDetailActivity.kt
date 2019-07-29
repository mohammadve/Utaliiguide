package com.utaliiguides.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.utaliiguides.R
import com.utaliiguides.viewModel.TourRequestListDetailViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_tour_request_list_detail.*
import kotlinx.android.synthetic.main.activity_tour_request_list_detail.et_user_name
import kotlinx.android.synthetic.main.activity_tour_request_list_detail.iv_profile_image

class TourRequestListDetailActivity : AppCompatActivity(){

    var tourRequestListDetailViewModel : TourRequestListDetailViewModel?= null
    var tour_req_id : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tour_request_list_detail)

        tour_req_id = intent.getIntExtra("tour_req_id",0)

        initViews()

    }



    private fun initViews() {

        tourRequestListDetailViewModel = ViewModelProviders.of(this).get(TourRequestListDetailViewModel::class.java)


        tourRequestListDetailViewModel!!.getTourRequestListDetail(this,tour_req_id).observe(this, Observer {


            if(it != null && it.has("status") && it.get("status").asString.equals("1")){

                if(it.has("data")){

                    var dataObj = it.getAsJsonObject("data")

                    if(dataObj.has("tour")){

                        var tourObj = dataObj.getAsJsonObject("tour")

                        tv_arrivalDate.text = tourObj.get("tourStartdate").asString
                        tv_departureDate.text = tourObj.get("tourEnddate").asString
                        tv_totalTripPrice.text = tourObj.get("tourcost").asString

                        if(tourObj.get("tourdays").asString.equals("1")){
                            tv_totalDays.text = tourObj.get("tourdays").asString + " Day"
                        }
                        else{
                            tv_totalDays.text = tourObj.get("tourdays").asString + " Days"
                        }



                        if(tourObj.has("user")){
                            var userObj = tourObj.getAsJsonObject("user")

                            if(userObj.has("u_name") && userObj.has("profile_img")){

                                et_user_name.text = userObj.get("u_name").asString

                                Glide
                                    .with(this)
                                    .load(userObj.get("profile_img").asString)
                                    .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                                    .into(iv_profile_image)

                            }
                        }
                    }
                }
            }
        })
    }





}