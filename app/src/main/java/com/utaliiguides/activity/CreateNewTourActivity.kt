package com.utaliiguides.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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



class CreateNewTourActivity : AppCompatActivity(), View.OnClickListener {

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
        }
    }
}