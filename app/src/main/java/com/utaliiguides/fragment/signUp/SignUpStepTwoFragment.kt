package com.utaliiguides.fragment.signUp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.utaliiguides.activity.SignUpActivity
import com.utaliiguides.adapter.CountryListAdapter
import com.utaliiguides.adapter.SelectedStateListAdapter
import com.utaliiguides.adapter.StateListAdapter
import com.utaliiguides.models.countryList.CountryListData
import com.utaliiguides.models.countryList.StateListData
import com.utaliiguides.viewModel.SignUpProcessViewModel
import com.utalli.helpers.Utils
import kotlinx.android.synthetic.main.fragment_signup_step_two.*
import com.zhy.view.flowlayout.TagFlowLayout
import android.widget.TextView
import com.utaliiguides.R
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.FlowLayout


class SignUpStepTwoFragment : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    var mSignUpViewModel: SignUpProcessViewModel? = null
    lateinit var mCountryList: ArrayList<CountryListData>
    var mCountryAdapter: CountryListAdapter? = null
    lateinit var mStateList: ArrayList<StateListData>
    var mStateAdapter: StateListAdapter? = null

    lateinit var addMoreStateList: ArrayList<StateListData>
    var mSelectedMoreStateAdapter: SelectedStateListAdapter? = null

    lateinit var mLanguageList: ArrayList<String>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        setOnClickListener()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_signup_step_two, container, false)
        return view
    }

    private fun initView()
    {
        mSignUpViewModel = ViewModelProviders.of(activity!!).get(SignUpProcessViewModel::class.java)

        initialiseCountrySpinner()
        initialiseStateSpinner()
        initialiseAddMoreStateList()
        initialiseAddMoreLanguageList()
        getCountryList()
    }

    private fun setOnClickListener()
    {
        btn_save.setOnClickListener(this)
        tv_addMoreState.setOnClickListener(this)
        tv_addMoreLanguage.setOnClickListener(this)
    }

    private fun initialiseCountrySpinner()
    {
        mCountryList = ArrayList<CountryListData>()
        var country = CountryListData()
        country.setName("Select country")
        country.setId(-1)
        mCountryList.add(country)
        mCountryAdapter = CountryListAdapter(activity!!, mCountryList)
        spinner_countrySelection.adapter = mCountryAdapter
        spinner_countrySelection.onItemSelectedListener = this
    }

    private fun initialiseStateSpinner()
    {
        mStateList = ArrayList<StateListData>()
        var state = StateListData()
        state.setName("Select state")
        state.setId(-1)
        mStateList.add(state)
        mStateAdapter = StateListAdapter(activity!!, mStateList)
        spinner_chooseState.adapter = mStateAdapter
        spinner_chooseState.onItemSelectedListener = this
    }

    private fun initialiseAddMoreStateList()
    {
        addMoreStateList = ArrayList<StateListData>()

        mStateFlowLayout!!.setAdapter(object : TagAdapter<StateListData>(addMoreStateList) {
            override fun getView(parent: FlowLayout, position: Int, selectedState: StateListData): View {
                var view: View? = null
                val mInflater = LayoutInflater.from(activity!!)
                if (view == null) {
                    view = mInflater.inflate(R.layout.item_row_selected_state, mStateFlowLayout, false)
                }
                val mHoumourText = view!!.findViewById<View>(R.id.tv_state_name) as TextView
                val humourValue = selectedState.getName()
                mHoumourText.setText(humourValue)
                return view
            }
        })

//        mSelectedMoreStateAdapter = SelectedStateListAdapter(activity!!)
//
//        val layoutManager = FlexboxLayoutManager(activity)
//        layoutManager.flexDirection = FlexDirection.ROW
//        layoutManager.justifyContent = JustifyContent.SPACE_AROUND
        //rv_selectedStates.layoutManager = layoutManager
        //rv_selectedStates.adapter = mSelectedMoreStateAdapter
//        rv_selectedStates.isNestedScrollingEnabled = true
    }

    private fun initialiseAddMoreLanguageList()
    {
        mLanguageList = ArrayList<String>()
        mAddMoreLanguageFlowLayout!!.setAdapter(object : TagAdapter<String>(mLanguageList) {
            override fun getView(parent: FlowLayout, position: Int, language: String): View {
                var view: View? = null
                val mInflater = LayoutInflater.from(activity!!)
                if (view == null) {
                    view = mInflater.inflate(R.layout.item_row_selected_state, mAddMoreLanguageFlowLayout, false)
                }
                val stateTextView = view!!.findViewById<View>(R.id.tv_state_name) as TextView
                stateTextView.setText(language)
                return view
            }
        })

        mAddMoreLanguageFlowLayout.setOnTagClickListener(TagFlowLayout.OnTagClickListener { view, position, parent ->
            //Toast.makeText(getActivity(), mVals[position], Toast.LENGTH_SHORT).show();
            Utils.showToast(activity!!, mLanguageList[position])
            true;
        })
    }

    private fun getCountryList()
    {
        if(Utils.isInternetAvailable(activity!!))
        {
            mSignUpViewModel!!.getCountryList(activity!!, "").observe(activity!!, androidx.lifecycle.Observer {
                if (it != null && it.has("status") && it.get("status").asString.equals("1"))
                {
                    if (it.has("data") && it.get("data") is JsonArray) {

                        val type = object : TypeToken<List<CountryListData>>() {}.type
                        var countryList = Gson().fromJson<List<CountryListData>>(it.get("data"), type)
                        if (countryList != null)
                        {
                            mCountryList.clear()
                            var country = CountryListData()
                            country.setId(-1)
                            country.setName("Select country")
                            mCountryList.add(0, country)
                            mCountryList.addAll(countryList)
                            mCountryAdapter?.notifyDataSetChanged()
                        }
                        else
                        {

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

    private fun getStateList(selectedCountryId: Int)
    {
        if(Utils.isInternetAvailable(activity!!))
        {
            mSignUpViewModel!!.getStateList(activity!!, selectedCountryId).observe(activity!!, androidx.lifecycle.Observer {
                if (it != null && it.has("status") && it.get("status").asString.equals("1"))
                {
                    if (it.has("data") && it.get("data") is JsonObject) {
                        var dataObject = it.getAsJsonObject("data")
                        if (dataObject != null)
                        {
                            val type = object : TypeToken<List<StateListData>>() {}.type
                            var stateList = Gson().fromJson<List<StateListData>>(dataObject.get("states"), type)
                            if (stateList != null)
                            {
                                mStateList.clear()
                                var state = StateListData()
                                state.setId(-1)
                                state.setName("Select state")
                                mStateList.add(0, state)
                                mStateList.addAll(stateList)
                                mStateAdapter?.notifyDataSetChanged()
                            }
                            else
                            {

                            }
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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
    {
        when(p0?.id)
        {
            R.id.spinner_countrySelection ->
            {
                if (p2 != 0)
                {
                    val selectedCountryId = mCountryList.get(p2).getId()
                    getStateList(selectedCountryId!!)
                }
            }
            R.id.spinner_chooseState ->
            {
                if (p2 != 0)
                {
                    var selectedState = mStateList.get(p2)
                    addMoreStateList.add(selectedState)
                }
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_save -> {
                (activity as SignUpActivity).displayFragment(3)
            }

            R.id.tv_addMoreState -> {
                updateAddMoreCountries()
            }
            R.id.tv_addMoreLanguage -> {
                if (!TextUtils.isEmpty(et_languageName.text))
                {
                    mLanguageList.add(et_languageName.text.toString())
                    updateAddMoreLanguage()
                }
            }
        }
    }

    private fun updateAddMoreCountries()
    {
        spinner_chooseState.setSelection(0)
        mStateFlowLayout!!.adapter.notifyDataChanged()
//        mSelectedMoreStateAdapter!!.setStateList(addMoreStateList, activity!!)
//        mSelectedMoreStateAdapter!!.notifyDataSetChanged()
    }

    private fun updateAddMoreLanguage()
    {
        et_languageName.setText("")
        mAddMoreLanguageFlowLayout!!.adapter.notifyDataChanged()
    }
}
