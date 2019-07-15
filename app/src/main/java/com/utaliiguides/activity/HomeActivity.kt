package com.utaliiguides.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.utaliiguides.R
import com.utaliiguides.RobotoMediumTextView
import com.utaliiguides.fragment.dashboard.*
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.app_bar_main.*

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    View.OnClickListener {

    private var mContext: Context? = null
    private var toolbar: Toolbar? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var mActionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var mManager: FragmentManager? = null
    private var mTransaction: FragmentTransaction? = null

    private var guideProfileImage: CircleImageView? = null
    private var guideName: RobotoMediumTextView? = null
    private var guideAddress: RobotoMediumTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initializeView()
        setUpDrawerLayout()
        setUpClickListener()
        setDisplayFragment(1)
    }

    /**
     * Initialise all view
     */
    private fun initializeView() {
        mContext = this@HomeActivity
        toolbar = findViewById(R.id.toolbar_main) as Toolbar

        val navigationView = findViewById(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)

        val headerLayout = navigationView.getHeaderView(0)
        guideProfileImage = headerLayout.findViewById<View>(R.id.profile_img) as CircleImageView
        guideName = headerLayout.findViewById<View>(R.id.tv_guideName) as RobotoMediumTextView
        guideAddress = headerLayout.findViewById<View>(R.id.tv_guideAddress) as RobotoMediumTextView
    }

    /**
     * Setup Drawer Layout
     */
    private fun setUpDrawerLayout() {
        mDrawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        mActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            mDrawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView)
            }

            override fun onDrawerOpened(drawerView: View) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView)
            }
        }

        mDrawerLayout!!.setDrawerListener(mActionBarDrawerToggle)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setHomeButtonEnabled(true)
    }

    /**
     * Setup clickListener
     */
    private fun setUpClickListener() {
        iv_toolbarMenu!!.setOnClickListener(this)
        iv_toolbarBack!!.setOnClickListener(this)
        iv_notification!!.setOnClickListener(this)
        iv_paymentSetting!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.iv_toolbarMenu ->
            {
                openOrCloseDrawerLayout()
            }
            R.id.iv_toolbarBack ->
            {
                handleBackPress()
            }

            R.id.iv_notification ->
            {
                setDisplayFragment(9)
//                var mIntent: Intent = Intent(this, NotificationActivity::class.java)
//                startActivity(mIntent)
            }
            R.id.iv_paymentSetting ->
            {
                setDisplayFragment(10)
            }
        }
    }

    /**
     * Open or Close DrawerLayout
     */
    private fun openOrCloseDrawerLayout() {
        if (mDrawerLayout!!.isDrawerOpen(Gravity.LEFT)) {
            mDrawerLayout!!.closeDrawers()
        } else {
            mDrawerLayout!!.openDrawer(Gravity.LEFT)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> setDisplayFragment(1)
            R.id.nav_todayTrip -> setDisplayFragment(2)
            R.id.nav_messages -> setDisplayFragment(3)
            R.id.nav_myProfile -> setDisplayFragment(4)
            R.id.nav_paymentSetting -> setDisplayFragment(5)
            R.id.nav_appSettings -> setDisplayFragment(6)
            R.id.nav_aboutApp -> setDisplayFragment(7)
        }

        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun setDisplayFragment(id: Int) {
        var mFragment: Fragment? = null
        when (id) {
            1 -> {
                mFragment = DashboardFragment()
                replaceFragment(mFragment)
            }
            2 -> {
                var mIntent: Intent = Intent(this, TripDetailActivity::class.java)
                startActivity(mIntent)
            }
            3 -> {
                mFragment = MessagesFragment()
                replaceFragment(mFragment)
            }
            4 -> {
                var mIntent: Intent = Intent(this, MyProfileActivity::class.java)
                startActivity(mIntent)
            }
            5 -> {
                mFragment = PaymentFragment()
                replaceFragment(mFragment)
            }
            6 -> {
                mFragment = AppSettingsFragment()
                replaceFragment(mFragment)
            }
            7 -> {
                mFragment = AboutAppFragment()
                replaceFragment(mFragment)
            }
            8 -> {
                mFragment = RequestFragment()
                replaceFragment(mFragment)
            }
            9 -> {
                mFragment = NotificationFragment()
                replaceFragment(mFragment)
            }
            10 -> {
                mFragment = AlreadyAddedAccountsFragment()
                replaceFragment(mFragment)
            }
            11 -> {
                mFragment = AddBankAccountFragment()
                replaceFragment(mFragment)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        mManager = supportFragmentManager
        mTransaction = mManager!!.beginTransaction()
        mTransaction!!.replace(R.id.container, fragment)
        mTransaction!!.addToBackStack(null)
        mTransaction!!.commit()
    }

    private fun addFragment(fragment: Fragment) {
        mManager = supportFragmentManager
        mTransaction = mManager!!.beginTransaction()
        mTransaction!!.add(R.id.container, fragment)
        mTransaction!!.addToBackStack(null)
        mTransaction!!.commit()
    }

    fun handleBackPress() {
        if (mManager!!.findFragmentById(R.id.container) is DashboardFragment)
        {
            finish()
            //mManager!!.popBackStackImmediate()
        } else if (mManager!!.findFragmentById(R.id.container) is MessagesFragment)
        {
            mManager!!.popBackStackImmediate()
        } else if (mManager!!.findFragmentById(R.id.container) is PaymentFragment)
        {
            mManager!!.popBackStackImmediate()
        } else if (mManager!!.findFragmentById(R.id.container) is AppSettingsFragment)
        {
            mManager!!.popBackStackImmediate()
        } else if (mManager!!.findFragmentById(R.id.container) is AboutAppFragment)
        {
            mManager!!.popBackStackImmediate()
        }
        else if (mManager!!.findFragmentById(R.id.container) is RequestFragment)
        {
            mManager!!.popBackStackImmediate()
        }
        else if (mManager!!.findFragmentById(R.id.container) is NotificationFragment)
        {
            mManager!!.popBackStackImmediate()
        }
        else if (mManager!!.findFragmentById(R.id.container) is PaymentSettingFragment)
        {
            mManager!!.popBackStackImmediate()
        }
        else if (mManager!!.findFragmentById(R.id.container) is AlreadyAddedAccountsFragment)
        {
            mManager!!.popBackStackImmediate()
        }
        else if (mManager!!.findFragmentById(R.id.container) is AddBankAccountFragment)
        {
            mManager!!.popBackStackImmediate()
        }
        else
        {
            mManager!!.popBackStackImmediate()
        }
    }

    override fun onBackPressed() {
        if (mDrawerLayout!!.isDrawerOpen(GravityCompat.START))
        {
            mDrawerLayout!!.closeDrawer(GravityCompat.START)
        } else
        {
            handleBackPress()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        val fragment = mManager!!.findFragmentById(R.id.container)
//        if (fragment != null && fragment.isVisible) {
//            if (fragment is MyProfileActivity) {
//                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
//            }
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        val fragment = mManager!!.findFragmentById(R.id.container)
//        if (fragment != null && fragment.isVisible) {
//            if (fragment is MyProfileActivity) {
//                fragment.onActivityResult(requestCode, resultCode, data)
//            }
//        }
    }

    fun setToolbarTitle(title: String)
    {
        toolbarTitle!!.text = title
    }

    fun setToolbarMenuVisibility(isVisible: Boolean) {
        if (isVisible) {
            iv_toolbarMenu!!.setVisibility(View.VISIBLE)
        } else {
            iv_toolbarMenu!!.setVisibility(View.GONE)
        }
    }

    fun setToolbarBackVisibility(isVisible: Boolean) {
        if (isVisible) {
            iv_toolbarBack!!.setVisibility(View.VISIBLE)
        } else {
            iv_toolbarBack!!.setVisibility(View.GONE)
        }
    }

    fun setToolbarNotificationVisibility(isVisible: Boolean) {
        if (isVisible) {
            iv_notification!!.setVisibility(View.VISIBLE)
        } else {
            iv_notification!!.setVisibility(View.GONE)
        }
    }

    fun setToolbarPaymentSettingVisibility(isVisible: Boolean) {
        if (isVisible) {
            iv_paymentSetting!!.setVisibility(View.VISIBLE)
        } else {
            iv_paymentSetting!!.setVisibility(View.GONE)
        }
    }
}
