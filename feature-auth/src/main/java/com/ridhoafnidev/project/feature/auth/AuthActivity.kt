package com.ridhoafnidev.project.feature.auth

import androidx.navigation.findNavController
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature_auth.R
import com.ridhoafnidev.project.feature_auth.databinding.ActivityAuthBinding

class AuthActivity : BaseActivity<ActivityAuthBinding>(ActivityAuthBinding::inflate) {

    private val navController by lazy { findNavController(R.id.activity_auth_nav_host_fragment) }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
    }

    override fun initListener() {
    }
}