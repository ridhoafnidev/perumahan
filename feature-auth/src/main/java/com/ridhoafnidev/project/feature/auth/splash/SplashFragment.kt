package com.ridhoafnidev.project.feature.auth.splash

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.ridhoafnidev.project.core_navigation.ModuleNavigator
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.feature_auth.R
import com.ridhoafnidev.project.feature_auth.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate),
    ModuleNavigator {

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.loginFragment)
        }, 200)
    }

    override fun initListener() {

    }
}