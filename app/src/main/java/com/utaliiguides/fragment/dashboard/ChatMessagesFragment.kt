package com.utaliiguides.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sendbird.android.*
import com.utaliiguides.R
import com.utaliiguides.activity.HomeActivity
import com.utaliiguides.adapter.ChatMessageListAdapter
import com.utaliiguides.helper.ConstantFragmentName
import com.utalli.helpers.AppPreference
import kotlinx.android.synthetic.main.fragment_messages.*
import com.sendbird.android.SendBird.ConnectHandler





class ChatMessagesFragment : Fragment() {
    var chatListAdapter : ChatMessageListAdapter ?= null
    var userId : String = ""



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_messages, container, false)

        (activity as HomeActivity).setToolbarTitle(ConstantFragmentName.MESSAGES_FRAGMENT)
        (activity as HomeActivity).setToolbarMenuVisibility(true)
        (activity as HomeActivity).setToolbarBackVisibility(false)
        (activity as HomeActivity).setToolbarNotificationVisibility(true)
        (activity as HomeActivity).setToolbarPaymentSettingVisibility(false)

        userId = "utaliiGuide_" + AppPreference.getInstance(activity!!).getId()

        SendBird.connect(userId, ConnectHandler { user, e ->
            if (e != null) {    // Error.
                return@ConnectHandler
            }
        })








        return view
    }



    private fun initView()
    {
        chatUserRecyclerView.setHasFixedSize(true)
        chatUserRecyclerView.layoutManager = LinearLayoutManager(activity)
        chatListAdapter = ChatMessageListAdapter(activity!!)
        chatUserRecyclerView.adapter = chatListAdapter
    }
}