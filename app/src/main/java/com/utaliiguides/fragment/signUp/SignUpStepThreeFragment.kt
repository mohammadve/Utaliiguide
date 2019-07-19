package com.utaliiguides.fragment.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.utaliiguides.R
import com.utaliiguides.activity.SignUpActivity
import com.utaliiguides.callBack.UploadDocumentCallBack
import com.utaliiguides.helper.AppConstants
import com.utaliiguides.helper.ImageUtils
import kotlinx.android.synthetic.main.fragment_signup_step_first.*
import kotlinx.android.synthetic.main.fragment_signup_step_three.*

class SignUpStepThreeFragment : Fragment(), View.OnClickListener, UploadDocumentCallBack {

    var mImageUtils:ImageUtils? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        setUpClickListener()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup_step_three, container, false)
        return view
    }

    private fun initView()
    {
        mImageUtils = ImageUtils()
        btn_uploadDocument.setOnClickListener(this)
    }

    private fun setUpClickListener()
    {
        iv_identityCardFrontSide.setOnClickListener(this)
        iv_identityCardBackSide.setOnClickListener(this)

        iv_localIdFrontSide.setOnClickListener(this)
        iv_localIdBackSide.setOnClickListener(this)

        iv_uploadDrivingFrontSide.setOnClickListener(this)
        iv_uploadDrivingBackSide.setOnClickListener(this)


        btn_uploadDocument.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_uploadDocument -> {
                (activity as SignUpActivity).displayFragment(4)
            }
            R.id.iv_identityCardFrontSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.IDENTITY_CARD_FRONT)
            }
            R.id.iv_identityCardBackSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.IDENTITY_CARD_BACK)
            }
            R.id.iv_localIdFrontSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.LOCAL_ID_CARD_FRONT)
            }
            R.id.iv_localIdBackSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.LOCAL_ID_CARD_BACK)
            }
            R.id.iv_uploadDrivingFrontSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.DRIVING_LICENCE_FRONT)
            }
            R.id.iv_uploadDrivingBackSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.DRIVING_LICENCE_BACK)
            }
        }
    }

    override fun selectedImage(imagePath: String, documentType: Int) {
        when(documentType)
        {
            AppConstants.IDENTITY_CARD_FRONT ->
            {

            }
            AppConstants.IDENTITY_CARD_BACK ->
            {

            }
            AppConstants.LOCAL_ID_CARD_FRONT ->
            {

            }
            AppConstants.LOCAL_ID_CARD_BACK ->
            {

            }
            AppConstants.DRIVING_LICENCE_FRONT ->
            {

            }
            AppConstants.DRIVING_LICENCE_BACK ->
            {

            }
        }
    }
}