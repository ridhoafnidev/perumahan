package com.ridhoafnidev.project.feature.auth.splash

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.ridhoafnidev.project.core_navigation.ModuleNavigator
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.feature.auth.viewmodel.AuthViewModel
import com.ridhoafnidev.project.feature_auth.R
import com.ridhoafnidev.project.feature_auth.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate),
    ModuleNavigator {

    private val authViewModel by viewModel<AuthViewModel>()

    override fun initView() {
        getCurrentUser()
        observeCurrentUser()
    }

    override fun initListener() {

    }

    private fun getCurrentUser() {
        Handler(Looper.getMainLooper()).postDelayed({
            authViewModel.getCurrentUser()
        }, 1500)
    }

    private fun observeCurrentUser() {
        authViewModel.currentUser.observe(requireActivity()) { currentUser ->
            if (currentUser != null) {
                navigateToHomeActivity(true)
            } else {
                findNavController()
                    .navigate(R.id.loginFragment)
            }
        }
    }
}