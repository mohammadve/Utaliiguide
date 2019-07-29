package com.utaliiguides.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.transitionseverywhere.Recolor
import com.transitionseverywhere.TransitionManager
import com.utaliiguides.R
import com.utaliiguides.adapter.DashboardListAdapter
import com.utaliiguides.adapter.TripDetailListAdapter
import kotlinx.android.synthetic.main.activity_trip_detail.*

class TripDetailActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trip_detail)
        setupUI()
        setupClickListener()

    }

    private fun setupUI()
    {
        appBar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {

                if (verticalOffset == 0) {

                    //TransitionManager.beginDelayedTransition(coordinate_layout)
                    var transition = Recolor()
                    transition.setDuration(500)

                    TransitionManager.beginDelayedTransition(
                        toolbar,
                        transition
                    )                   // spacer.visibility = View.VISIBLE
                    /*   cl_countryDateDetails.visibility = View.VISIBLE
                       cl_countryDetails.visibility = View.GONE*/

                    toolbar.setBackgroundDrawable(ColorDrawable(resources.getColor(android.R.color.transparent)))


                } else if (Math.abs(verticalOffset) == appBarLayout!!.getTotalScrollRange()) {
                    // TransitionManager.beginDelayedTransition(coordinate_layout)

                    var transition = Recolor()
                    transition.setDuration(500)

                    TransitionManager.beginDelayedTransition(
                        toolbar,
                        transition
                    )
                    //  spacer.visibility = View.GONE
                    /* cl_countryDateDetails.visibility = View.GONE
                     cl_countryDetails.visibility = View.VISIBLE*/
                    toolbar.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorPrimary)))
                }
            }
        })

        tripDetailRecyclerView.setHasFixedSize(true)
        tripDetailRecyclerView.layoutManager = LinearLayoutManager(this)
        tripDetailRecyclerView.adapter = TripDetailListAdapter(this)
    }

    private fun setupClickListener()
    {
        iv_backArrow.setOnClickListener(this)
        iv_toolbarMore.setOnClickListener(this)
        cl_start_Tour.setOnClickListener(this)
        cl_end_Tour.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id)
        {
            R.id.iv_backArrow ->
            {
                finish()
            }
            R.id.iv_toolbarMore ->
            {

            }

            R.id.cl_start_Tour ->{
              cl_end_Tour.visibility = View.VISIBLE
                cl_start_Tour.visibility = View.GONE
            }

            R.id.cl_end_Tour ->{
                cl_start_Tour.visibility = View.VISIBLE
                cl_end_Tour.visibility = View.GONE
            }


        }
    }
}