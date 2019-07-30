package com.utaliiguides.activity

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.utaliiguides.R
import com.utaliiguides.fragment.signUp.SignUpStepFirstFragment
import com.utaliiguides.fragment.signUp.SignUpStepFourFragment
import com.utaliiguides.fragment.signUp.SignUpStepThreeFragment
import com.utaliiguides.fragment.signUp.SignUpStepTwoFragment
import com.utaliiguides.helper.RealPathUtil
import com.utaliiguides.models.signUpQuestion.GuideRegisterModel
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_signup.*
import java.io.File
import java.io.IOException
import java.util.HashMap

class SignUpActivity : AppCompatActivity(), View.OnClickListener{

    private var mManager: FragmentManager? = null
    private var mTransaction: FragmentTransaction? = null
    private var guideRegisterFields: HashMap<String, String>? = null
    private var guideDocumentsFields: HashMap<String, File>? = null

    private var mGuideRegisterModel: GuideRegisterModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        toolbar_signUp.title = resources.getString(R.string.text_sign_up)
        toolbar_signUp.setNavigationIcon(R.drawable.arrow_back_black)
        setSupportActionBar(toolbar_signUp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_signUp.setNavigationOnClickListener { removeFragmentFromBackStack()}
        guideRegisterFields = HashMap()
        guideDocumentsFields = HashMap()
        mGuideRegisterModel = GuideRegisterModel()
        displayFragment(1)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.tv_sign_in->{
                finish()
            }
        }
    }

    fun displayFragment(id: Int) {
        var mFragment: Fragment? = null
        when (id) {
            1 -> {
                updateStepCount(id, true)
                mFragment = SignUpStepFirstFragment()
                replaceFragment(mFragment)
            }
            2 -> {
                updateStepCount(id, true)
                mFragment = SignUpStepTwoFragment()
                replaceFragment(mFragment)
            }
            3 -> {
                updateStepCount(id, true)
                mFragment = SignUpStepThreeFragment()
                replaceFragment(mFragment)
            }
            4 -> {
                updateStepCount(id, true)
                mFragment = SignUpStepFourFragment()
                replaceFragment(mFragment)
            }
        }
    }

    private fun updateStepCount(id: Int, manageStepCount: Boolean)
    {
        when(id)
        {
            1 -> {
                ll_stepCountFirst.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                iv_stepFirst.setImageResource(R.mipmap.ic_personal_on)
                viewStepFirst.setBackgroundColor(resources.getColor(R.color.StepCountLineGrey))

                ll_stepCountTwo.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                iv_stepTwo.setImageResource(R.mipmap.ic_places_off)
                viewStepTwo.setBackgroundColor(resources.getColor(R.color.StepCountLineGrey))

                ll_stepCountThree.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                iv_stepThree.setImageResource(R.mipmap.ic_document_off)
                viewStepThree.setBackgroundColor(resources.getColor(R.color.StepCountLineGrey))

                ll_stepCountFour.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                iv_stepFour.setImageResource(R.mipmap.ic_qa_off)
            }
            2 -> {
                if (manageStepCount)
                {
                    ll_stepCountFirst.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepFirst.setImageResource(R.mipmap.ic_personal_on)
                    viewStepFirst.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountTwo.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepTwo.setImageResource(R.mipmap.ic_places_on)
                    viewStepTwo.setBackgroundColor(resources.getColor(R.color.StepCountLineGrey))

                    ll_stepCountThree.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                    iv_stepThree.setImageResource(R.mipmap.ic_document_off)
                    viewStepThree.setBackgroundColor(resources.getColor(R.color.StepCountLineGrey))

                    ll_stepCountFour.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                    iv_stepFour.setImageResource(R.mipmap.ic_qa_off)
                }
                else
                {
                    ll_stepCountFirst.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepFirst.setImageResource(R.mipmap.ic_personal_on)
                    viewStepFirst.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountTwo.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                    iv_stepTwo.setImageResource(R.mipmap.ic_places_off)
                    viewStepTwo.setBackgroundColor(resources.getColor(R.color.StepCountLineGrey))

                    ll_stepCountThree.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                    iv_stepThree.setImageResource(R.mipmap.ic_document_off)
                    viewStepThree.setBackgroundColor(resources.getColor(R.color.StepCountLineGrey))

                    ll_stepCountFour.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                    iv_stepFour.setImageResource(R.mipmap.ic_qa_off)
                }
            }
            3 -> {
                if (manageStepCount)
                {
                    ll_stepCountFirst.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepFirst.setImageResource(R.mipmap.ic_personal_on)
                    viewStepFirst.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountTwo.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepTwo.setImageResource(R.mipmap.ic_places_on)
                    viewStepTwo.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountThree.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepThree.setImageResource(R.mipmap.ic_document_on)
                    viewStepThree.setBackgroundColor(resources.getColor(R.color.StepCountLineGrey))

                    ll_stepCountFour.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                    iv_stepFour.setImageResource(R.mipmap.ic_qa_off)
                }
                else
                {
                    ll_stepCountFirst.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepFirst.setImageResource(R.mipmap.ic_personal_on)
                    viewStepFirst.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountTwo.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepTwo.setImageResource(R.mipmap.ic_places_on)
                    viewStepTwo.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountThree.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                    iv_stepThree.setImageResource(R.mipmap.ic_document_off)
                    viewStepThree.setBackgroundColor(resources.getColor(R.color.StepCountLineGrey))

                    ll_stepCountFour.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                    iv_stepFour.setImageResource(R.mipmap.ic_qa_off)
                }
            }
            4 -> {
                if (manageStepCount)
                {
                    ll_stepCountFirst.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepFirst.setImageResource(R.mipmap.ic_personal_on)
                    viewStepFirst.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountTwo.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepTwo.setImageResource(R.mipmap.ic_places_on)
                    viewStepTwo.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountThree.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepThree.setImageResource(R.mipmap.ic_document_on)
                    viewStepThree.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountFour.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepFour.setImageResource(R.mipmap.ic_qa_on)
                }
                else
                {
                    ll_stepCountFirst.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepFirst.setImageResource(R.mipmap.ic_personal_on)
                    viewStepFirst.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountTwo.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepTwo.setImageResource(R.mipmap.ic_places_on)
                    viewStepTwo.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountThree.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_light_orange))
                    iv_stepThree.setImageResource(R.mipmap.ic_document_on)
                    viewStepThree.setBackgroundColor(resources.getColor(R.color.lightOrange))

                    ll_stepCountFour.setBackgroundDrawable(getDrawable(R.drawable.circle_bg_gray))
                    iv_stepFour.setImageResource(R.mipmap.ic_qa_off)
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
                mManager = supportFragmentManager
                mTransaction = mManager!!.beginTransaction()
                mTransaction!!.replace(R.id.layoutContainer, fragment)
                mTransaction!!.addToBackStack(null)
                mTransaction!!.commit()
    }

    private fun removeFragmentFromBackStack() {
        if (mManager!!.findFragmentById(R.id.layoutContainer) is SignUpStepFirstFragment)
        {
            finish()
        } else if (mManager!!.findFragmentById(R.id.layoutContainer) is SignUpStepTwoFragment)
        {
            updateStepCount(2, false);
            mManager!!.popBackStackImmediate()
        } else if (mManager!!.findFragmentById(R.id.layoutContainer) is SignUpStepThreeFragment)
        {
            updateStepCount(3, false);
            mManager!!.popBackStackImmediate()
        } else if (mManager!!.findFragmentById(R.id.layoutContainer) is SignUpStepFourFragment)
        {
            updateStepCount(4, false);
            mManager!!.popBackStackImmediate()
        }
        else
        {
            mManager!!.popBackStackImmediate()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val fragment = mManager!!.findFragmentById(R.id.layoutContainer)
        if (fragment != null && fragment.isVisible) {
            if (fragment is SignUpStepThreeFragment) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment = mManager!!.findFragmentById(R.id.layoutContainer)
        if (fragment != null && fragment.isVisible) {
            if (fragment is SignUpStepThreeFragment) {
                fragment.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    override fun onBackPressed() {
        removeFragmentFromBackStack()
    }

    fun getGuideRegisterFieldMap(): HashMap<String, String>? {
        return guideRegisterFields
    }

    fun getGuideDocumentFieldMap(): HashMap<String, File>? {
        return guideDocumentsFields
    }
    fun getGuideRegisterModel(): GuideRegisterModel? {
        return mGuideRegisterModel
    }
}