package com.ridhoafnidev.project.feature.home

import android.util.Log
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.bumptech.glide.Glide
import com.ridhoafnidev.home.R
import com.ridhoafnidev.home.databinding.FragmentHomeBinding
import com.ridhoafnidev.project.core_data.data.APP_TIPE_PERUMAHAN_PHOTO_URL
import com.ridhoafnidev.project.core_data.data.remote.ApiEvent
import com.ridhoafnidev.project.core_data.domain.MenuStatus
import com.ridhoafnidev.project.core_domain.model.Menu
import com.ridhoafnidev.project.core_domain.model.TipeRumah
import com.ridhoafnidev.project.core_domain.model.tipe_rumah.ListTipePerumahanGetAll
import com.ridhoafnidev.project.core_domain.model.tipe_rumah.TipePerumahanGetAll
import com.ridhoafnidev.project.core_navigation.EXTRA_PERUMAHAN_ID
import com.ridhoafnidev.project.core_navigation.ModuleNavigator
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.core_util.dayTimeGreeting
import com.ridhoafnidev.project.core_util.setSnapHelper
import com.ridhoafnidev.project.feature.home.viewholder.ItemMenuViewHolder
import com.ridhoafnidev.project.feature.home.viewholder.ItemPerumahanViewHolder
import com.ridhoafnidev.project.feature.home.viewmodel.HomeViewModel
import lt.neworld.spanner.Spanner
import lt.neworld.spanner.Spans.bold
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), ModuleNavigator {

    private val itemsMenu by lazy {
        listOf(
            Menu(
                1,
                "Pengguna",
                R.drawable.ic_user
            ),
            Menu(
                2,
                "Perumahan",
                 R.drawable.ic_houses
            ),
            Menu(
                3,
                "Calon Pembeli",
                R.drawable.ic_seller
            ),
            Menu(
                4,
                "Tipe Rumah",
                R.drawable.ic_house_type
            ),

            Menu(
                5,
                "Persyaratan",
                R.drawable.ic_requirement
            ),
            Menu(
                6,
                "Simulasi KPR",
                R.drawable.ic_simultion
            ),
            Menu(
                7,
                "Info Perumahan",
                R.drawable.ic_info
            ),
            Menu(
                8,
                "Laporan",
                R.drawable.ic_reservation
            )
        )
    }

    private val dummyPerumahan by lazy {
        (1..10).map {
            TipeRumah(
                id = "P$it",
                namaTipe = "Enau - Perumahan Citra $it",
                ukuran = it * 1000,
                pondasi = "Semen",
                dinding = "Semen",
                lantai = "Granit",
                plafon = "Plafon $it",
                pintuDepan = "Baja ringan",
                dindingKamarMandi = "Granit",
                kusen = "Baja",
                rangkapAtap = "Baja ringan",
                atap = "Seng",
                sanitasi = "Air",
                listrik = "PLTA",
                air = "Bor",
                harga = it * 1000000,
                jumlahUnit = it,
                photo = ""
            )
        }
    }

    private val homeViewModel: HomeViewModel by viewModel()

    private val recyclerViewMenus by lazy { binding.rvGridMenu }

    private val recyclerViewPerumahan by lazy { binding.rvPerumahan }

    override fun initView() {
        getTipePerumahan()
        setupTipePerumahan()
        setupItems()
        setupMenus()
    }

    private fun getTipePerumahan() {
        homeViewModel.tipeRumahGetAll()
    }

    private fun setupTipePerumahan() {
        homeViewModel.listTipeRumah.observe(requireActivity()) { listEvent ->
            when (listEvent) {
                is ApiEvent.OnProgress -> {}
                is ApiEvent.OnSuccess -> {
                    Timber.d("${listEvent.getData()}")
                    setupRvPerumahan(listEvent.getData())
                }
                is ApiEvent.OnFailed -> {}
            }
        }
    }

    private fun setupItems() {
        with(binding){
            tvWelcome.text = Spanner().append("Halo ")
                .append("${dayTimeGreeting(requireContext())}, \n")
                .append(getString(R.string.welcome_name), bold())

            ivUser.load(R.drawable.photo_male){
                crossfade(true)
                transformations(CircleCropTransformation())
            }
        }
    }

    private fun setupRvPerumahan(listEvent: ListTipePerumahanGetAll) {

        setSnapHelper(recyclerViewPerumahan)

        recyclerViewPerumahan.setup {
            withDataSource(dataSourceOf(listEvent))
            withItem<TipePerumahanGetAll, ItemPerumahanViewHolder>(R.layout.layout_item_perumahan){
                onBind(::ItemPerumahanViewHolder){_, item ->
                    tvNamaTipePerumahan.text = item.namaTipe
                    tvUkuranPerumahan.text = getString(R.string.ukuran, item.ukuran)
                    tvTotalPerumahan.text = getString(R.string.total, item.jumlahUnit)
                    tvHargaPerumahan.text = getString(R.string.harga, item.harga)

                    if (item.foto.isNotEmpty()) {
                        Glide.with(requireActivity())
                            .load(APP_TIPE_PERUMAHAN_PHOTO_URL + item.foto[0].foto)
                            .into(ivPhotoPerumahan)
                    }
                }
                onClick {
                    navigateToDetailPerumahActivity(
                        extras = bundleOf(EXTRA_PERUMAHAN_ID to item.id)
                    )
                }
            }
        }
    }

    private fun setupMenus() {
        recyclerViewMenus.setup {
            withLayoutManager(GridLayoutManager(context, 4))
            withDataSource(dataSourceOf(itemsMenu))
            withItem<Menu, ItemMenuViewHolder>(R.layout.layout_item_menu){
                onBind(::ItemMenuViewHolder){_, item ->
                    titleMenu.text = item.title
                    ivMenu.load(item.image)
                    ivMenu.setOnClickListener {
                        when(item.id){
                            MenuStatus.Perumahan() -> {
                                navigateToPerumahanActivity()
                            }
                            MenuStatus.CalonPembeli() -> {
                                navigateToCalonPembeliActivity()
                            }
                            MenuStatus.TipeRumah() -> {
                                navigateToTipeRumahActivity()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun initListener(){
    }
}