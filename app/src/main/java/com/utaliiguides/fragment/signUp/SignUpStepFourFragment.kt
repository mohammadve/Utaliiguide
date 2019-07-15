package com.utaliiguides.fragment.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utaliiguides.R
import kotlinx.android.synthetic.main.fragment_signup_step_four.*

class SignUpStepFourFragment : Fragment(), View.OnClickListener {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup_step_four, container, false)
        return view
    }

    private fun initView()
    {
        btn_saveQuesAns.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_saveQuesAns -> {
                activity!!.finish()
            }
        }
    }
}