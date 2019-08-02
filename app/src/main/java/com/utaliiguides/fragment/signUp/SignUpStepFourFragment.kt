package com.utaliiguides.fragment.signUp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import com.utaliiguides.R
import com.utaliiguides.activity.SignUpActivity
import com.utaliiguides.fragment.VerifyOTPDialogFragment
import com.utaliiguides.models.countryList.CountryListData
import com.utaliiguides.models.signUpQuestion.QuestionListData
import com.utaliiguides.models.signUpQuestion.SignUpQuestionAnswerModel
import com.utaliiguides.viewModel.SignUpProcessViewModel
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.fragment_signup_step_four.*
import kotlinx.android.synthetic.main.fragment_signup_step_two.*

class SignUpStepFourFragment : Fragment(), View.OnClickListener,  VerifyOTPDialogFragment.OnButtonClick{

    var mSignUpViewModel: SignUpProcessViewModel? = null
    var mQuestionList: ArrayList<QuestionListData>? = null
    var mGuideQusAnsList: ArrayList<SignUpQuestionAnswerModel>? = null
    var mOTP: String = ""
    var bottomSheetDialogFragment: VerifyOTPDialogFragment? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        setOnClickListener()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup_step_four, container, false)
        return view
    }

    private fun initView()
    {
        mSignUpViewModel = ViewModelProviders.of(activity!!).get(SignUpProcessViewModel::class.java)
        mQuestionList = ArrayList<QuestionListData>()
        mGuideQusAnsList = ArrayList<SignUpQuestionAnswerModel>()
        getQuestionList()
    }

    private fun setOnClickListener()
    {
        btn_saveQuesAns.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_saveQuesAns -> {
                if (Utils.isInternetAvailable(activity!!))
                {
                    if (isValidate())
                    {
                        initiateGuideRegisterProcess(false)
                        //activity!!.finish()
                    }
                }
                else {
                    Utils.showToast(activity!!, resources.getString(R.string.msg_no_internet))
                }
            }
        }
    }

    private fun updateUI()
    {
        var i=0
        while (i < mQuestionList!!.size) {
            if (i==0)
            {
                txtQuestionOne.text = mQuestionList!!.get(i).getQuestion()
            }
            if (i== 1)
            {
                txtQuestionTwo.text = mQuestionList!!.get(i).getQuestion()
            }
            if (i== 2)
            {
                txtQuestionThree.text = mQuestionList!!.get(i).getQuestion()
            }
            i++
        }
    }

    private fun getQuestionList()
    {
        if(Utils.isInternetAvailable(activity!!))
        {
            mSignUpViewModel!!.getQuestionList(activity!!).observe(activity!!, androidx.lifecycle.Observer {
                if (it != null && it.has("status") && it.get("status").asString.equals("1"))
                {
                    if (it.has("data") && it.get("data") is JsonArray) {
                        val type = object : TypeToken<List<QuestionListData>>() {}.type
                        var questionList = Gson().fromJson<List<QuestionListData>>(it.get("data"), type)
                        if (questionList != null && questionList.size > 0)
                        {
                            mQuestionList!!.addAll(questionList)
                            updateUI()
                        }
                    }
                }
                else {
                    if(it != null && it.has("message")){
                        Utils.showToast(activity!!,it.get("message").asString)
                        Log.e("TAG","message status 0 SignUp  === "+it.get("message").asString)
                    }
                }
            })
        }
        else
        {
            Utils.showToast(activity!!, getString(R.string.msg_no_internet))
        }
    }

    private  fun isValidate(): Boolean
    {
        var isValid = false
        if (TextUtils.isEmpty(et_ansQuestionFirst.text.toString()))
        {
            Utils.showToast(activity!!, "Please enter your first answer.")
            isValid = false
        }
        else if (TextUtils.isEmpty(et_ansQuestionTwo.text.toString()))
        {
            Utils.showToast(activity!!, "Please enter your second answer.")
            isValid = false
        }
        else if (TextUtils.isEmpty(et_ansQuestionThree.text.toString()))
        {
            Utils.showToast(activity!!, "Please enter your third answer.")
            isValid = false
        }
        else
        {
            isValid = true
            putAllDataToFieldMap()
        }
        return isValid
    }

    private fun putAllDataToFieldMap() {

        var mGuideRegisterModel = (activity as SignUpActivity).getGuideRegisterModel()
        //[{"ques_id":1,ans:"1"},{"ques_id":1,ans:"1"}]

        var i=0
        while (i < mQuestionList!!.size) {
            var guideQusAnsModel = SignUpQuestionAnswerModel()
            guideQusAnsModel.setQuestionId(mQuestionList!!.get(i).getId())
            guideQusAnsModel.setAnswer("1")
            mGuideQusAnsList!!.add(guideQusAnsModel)
            i++
        }

        var gson = Gson()
        var element = gson.toJsonTree(mGuideQusAnsList, object:TypeToken<ArrayList<SignUpQuestionAnswerModel>>() {
        }.getType())

        if (!element.isJsonArray())
        {
            // fail appropriately
            throw Exception()
        }
        val jsonArray = element.getAsJsonArray()

        var guideQuestionAnswer = jsonArray.toString()
        mGuideRegisterModel?.setQuestions(guideQuestionAnswer)

        mGuideRegisterModel?.setDevice_token("nqsjnjdnkjsndknknd")
        mGuideRegisterModel?.setGuide_about("This is test message about guide")
        mGuideRegisterModel?.setGuide_pool_price("10")
        mGuideRegisterModel?.setGuide_private_price("20")
    }

    private fun initiateGuideRegisterProcess(isOTP: Boolean)
    {
        var mGuideRegisterModel = (activity as SignUpActivity).getGuideRegisterModel()
        if (isOTP)
        {
            mGuideRegisterModel?.setOtp(mOTP)
        }
        else
        {
            mGuideRegisterModel?.setOtp("")
        }
        if (mGuideRegisterModel != null)
        {
            mSignUpViewModel!!.setUpGuideRegister(activity!!, mGuideRegisterModel).observe(activity!!, androidx.lifecycle.Observer {
                if (it != null && it.has("status") && it.get("status").asString.equals("1"))
                {
                    if(it.has("otp"))
                    {
                        mOTP = it.get("otp").asString
                        bottomSheetDialogFragment = VerifyOTPDialogFragment.newInstance(mOTP, this)
                        bottomSheetDialogFragment!!.show(childFragmentManager, "VerifyOTPDialogFragment")
                    }
                    else if(it.has("data") && !it.get("data").isJsonNull)
                    {
                        Utils.showToast(activity!!, it.get("message").asString)
                        activity!!.finish()
                    }
                    else
                    {
                        Utils.showToast(activity!!,resources.getString(R.string.msg_common_error))
                    }
                }
                else {
                    if(it != null && it.has("message")){
                        Utils.showToast(activity!!,it.get("message").asString)
                        Log.e("TAG","message status 0 SignUp  === "+it.get("message").asString)
                    }
                }
            })
        }
    }

    override fun onDismiss() {
        bottomSheetDialogFragment = null
    }

    override fun onResendClick() {
        bottomSheetDialogFragment!!.dismiss()
        initiateGuideRegisterProcess(false)
    }

    override fun onSubmitClick() {
        Utils.hideSoftKeyboard(activity!!)
        bottomSheetDialogFragment!!.dismiss()
        initiateGuideRegisterProcess(true)
    }
}