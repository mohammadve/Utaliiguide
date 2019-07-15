package com.utaliiguides.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.utaliiguides.R
import com.utaliiguides.adapter.NotificationListAdapter
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        toolbar.title = ""
        toolbar.setNavigationIcon(R.mipmap.ic_back_white)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener { finish() }

        initializeView()
    }

    /**
     * Initialise all view
     */
    private fun initializeView() {
        notificationRecyclerView.setHasFixedSize(true)
        notificationRecyclerView.layoutManager = LinearLayoutManager(this)
        notificationRecyclerView.adapter = NotificationListAdapter(this)
    }
}