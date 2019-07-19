package com.utaliiguides.fragment.signUp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.utaliiguides.R
import com.utaliiguides.activity.SignUpActivity
import com.utaliiguides.callBack.UploadDocumentCallBack
import com.utaliiguides.helper.AppConstants
import com.utaliiguides.helper.ImageUtils
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.fragment_signup_step_first.*
import kotlinx.android.synthetic.main.fragment_signup_step_three.*
import java.io.File

class SignUpStepThreeFragment : Fragment(), View.OnClickListener, UploadDocumentCallBack {

    var mImageUtils: ImageUtils? = null
    var identityFrontFile: File? = null
    var identityBackFile: File? = null
    var localIdFrontFile: File? = null
    var localIdBackFile: File? = null
    var dlIdFrontFile: File? = null
    var dlIdBackFile: File? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        setUpClickListener()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup_step_three, container, false)
        return view
    }

    private fun initView() {
        mImageUtils = ImageUtils()
    }

    private fun setUpClickListener() {
        iv_identityCardFrontSide.setOnClickListener(this)
        iv_identityCardBackSide.setOnClickListener(this)

        iv_localIdFrontSide.setOnClickListener(this)
        iv_localIdBackSide.setOnClickListener(this)

        iv_uploadDrivingFrontSide.setOnClickListener(this)
        iv_uploadDrivingBackSide.setOnClickListener(this)


        btn_uploadDocument.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_uploadDocument -> {
                if (Utils.isInternetAvailable(activity!!)) {
                    if (isValidate()) {
                        (activity as SignUpActivity).displayFragment(4)
                    }
                } else {
                    Utils.showToast(activity!!, resources.getString(R.string.msg_no_internet))
                }
                (activity as SignUpActivity).displayFragment(4)
            }
            R.id.iv_identityCardFrontSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.IDENTITY_CARD_FRONT, this)
            }
            R.id.iv_identityCardBackSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.IDENTITY_CARD_BACK, this)
            }
            R.id.iv_localIdFrontSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.LOCAL_ID_CARD_FRONT, this)
            }
            R.id.iv_localIdBackSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.LOCAL_ID_CARD_BACK, this)
            }
            R.id.iv_uploadDrivingFrontSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.DRIVING_LICENCE_FRONT, this)
            }
            R.id.iv_uploadDrivingBackSide -> {
                mImageUtils!!.selectImage(activity!!, AppConstants.DRIVING_LICENCE_BACK, this)
            }
        }
    }

    override fun selectedImage(imagePath: String, selectedDoc: File, documentType: Int) {
        when (documentType) {
            AppConstants.IDENTITY_CARD_FRONT -> {
                Glide
                    .with(this)
                    .load(imagePath)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                    .into(iv_identityCardFrontSide)
                identityFrontFile = selectedDoc
            }
            AppConstants.IDENTITY_CARD_BACK -> {
                Glide
                    .with(this)
                    .load(imagePath)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                    .into(iv_identityCardBackSide)
                identityBackFile = selectedDoc
            }
            AppConstants.LOCAL_ID_CARD_FRONT -> {
                Glide
                    .with(this)
                    .load(imagePath)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                    .into(iv_localIdFrontSide)
                localIdFrontFile = selectedDoc
            }
            AppConstants.LOCAL_ID_CARD_BACK -> {
                Glide
                    .with(this)
                    .load(imagePath)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                    .into(iv_localIdBackSide)
                localIdBackFile = selectedDoc
            }
            AppConstants.DRIVING_LICENCE_FRONT -> {
                Glide
                    .with(this)
                    .load(imagePath)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                    .into(iv_uploadDrivingFrontSide)
                dlIdFrontFile = selectedDoc
            }
            AppConstants.DRIVING_LICENCE_BACK -> {
                Glide
                    .with(this)
                    .load(imagePath)
                    .apply(RequestOptions().placeholder(R.mipmap.ic_profile_placeholder).error(R.mipmap.ic_profile_placeholder))
                    .into(iv_uploadDrivingBackSide)
                dlIdBackFile = selectedDoc
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mImageUtils!!.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mImageUtils!!.onActivityResult(requestCode, resultCode, data)
    }

    private fun isValidate(): Boolean {
        var isValid: Boolean = false
        if (identityFrontFile == null) {
            Utils.showToast(activity!!, "Please upload front side pick of your identity card")
            isValid = false
        } else if (identityBackFile == null) {
            Utils.showToast(activity!!, "Please upload back side pick of your identity card")
            isValid = false
        } else if (TextUtils.isEmpty(et_identityNo.text)) {
            Utils.showToast(activity!!, "Please enter your valid identity card number")
            isValid = false
        } else if (localIdFrontFile == null) {
            Utils.showToast(activity!!, "Please upload front side pick of your local identity card")
            isValid = false
        } else if (localIdBackFile == null) {
            Utils.showToast(activity!!, "Please upload back side pick of your local identity card")
            isValid = false
        } else if (TextUtils.isEmpty(et_localIDProofNo.text)) {
            Utils.showToast(activity!!, "Please enter your valid local identity card number")
            isValid = false
        } else if (dlIdFrontFile == null) {
            Utils.showToast(activity!!, "Please upload front side pick of your valid driving licence")
            isValid = false
        } else if (dlIdBackFile == null) {
            Utils.showToast(activity!!, "Please upload back side pick of your valid driving licence")
            isValid = false
        } else if (TextUtils.isEmpty(et_drivingLicenceNo.text)) {
            Utils.showToast(activity!!, "Please enter your valid driving licence number")
            isValid = false
        } else {
            isValid = true
        }
        return isValid
    }
}