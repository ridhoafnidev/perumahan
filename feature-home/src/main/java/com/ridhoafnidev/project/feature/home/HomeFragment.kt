package com.ridhoafnidev.project.feature.home

import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.ridhoafnidev.home.R
import com.ridhoafnidev.home.databinding.FragmentHomeBinding
import com.ridhoafnidev.project.core_domain.model.MenuStatus
import com.ridhoafnidev.project.core_domain.model.Menu
import com.ridhoafnidev.project.core_navigation.ActivityClassPath
import com.ridhoafnidev.project.core_navigation.EXTRA_HARGA_PROPERTI
import com.ridhoafnidev.project.core_navigation.ModuleNavigator
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.core_util.dayTimeGreeting
import com.ridhoafnidev.project.core_util.showAlertDialog
import com.ridhoafnidev.project.feature.home.menu.AdminMenu
import com.ridhoafnidev.project.feature.home.menu.KonsumenMenu
import com.ridhoafnidev.project.feature.home.viewholder.ItemMenuViewHolder
import com.ridhoafnidev.project.feature.home.viewmodel.AuthViewModel
import lt.neworld.spanner.Spanner
import lt.neworld.spanner.Spans.bold
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), ModuleNavigator {

    private val authViewModel: AuthViewModel by viewModel()

    override fun initView() {
        getCurrentUser()
        setupMenu()
    }

    override fun initListener(){
    }

    private fun getCurrentUser() {
        authViewModel.getCurrentUser()
    }

    private fun setupMenu() {
        authViewModel.currentUser.observe(requireActivity()) { currentUser ->
            if (currentUser != null) {
                val name = currentUser.namaLengkap
                setupItems(name)
                val listMenu = when (currentUser.role) {
                    "direktur", "pegawai" -> AdminMenu.menus
                    else -> KonsumenMenu.menus
                }
                setListMenu(listMenu)
            }
        }
    }

    private fun setupItems(name: String) {
        with(binding){
            tvWelcome.text = Spanner().append("Halo ")
                .append("${dayTimeGreeting(requireContext())}, \n")
                .append(name, bold())

            ivUser.load(R.drawable.photo_male){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }

    private fun setListMenu(listMenu: List<Menu>) {
        binding.rvGridMenu.setup {
            withLayoutManager(GridLayoutManager(context, 4))
            withDataSource(dataSourceOf(listMenu))
            withItem<Menu, ItemMenuViewHolder>(R.layout.layout_item_menu){
                onBind(::ItemMenuViewHolder){_, item ->
                    titleMenu.text = item.title
                    ivMenu.load(item.image)
                }
                onClick {
                    when(item.id){
                        MenuStatus.Perumahan() -> {
                            navigateToPerumahanActivity()
                        }
                        MenuStatus.Pengguna() -> {
                            navigateToPenggunaActivity()
                        }
                        MenuStatus.CalonPembeli() -> {
                            navigateToCalonPembeliActivity()
                        }
                        MenuStatus.TipeRumah() -> {
                            navigateToTipeRumahActivity()
                        }
                        MenuStatus.Persyaratan() -> {
                            navigateToPersyaratanActivity()
                        }
                        MenuStatus.SimulasiKPR() -> {
                            navigateToSimulasiKPRActivity(extras = bundleOf(EXTRA_HARGA_PROPERTI to 0))
                        }
                        MenuStatus.Laporan() -> {
                            navigateToLaporanActivity()
                        }
                        MenuStatus.Logout() -> {
                            showAlertDialog(getString(R.string.message_logout)) {
                                authViewModel.logout()
                                navigateToAuthActivity(true)
                            }
                        }
                    }
                }
            }
        }
    }
}