package com.ridhoafnidev.project.feature.home

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.ridhoafnidev.project.core_data.domain.Event
import com.ridhoafnidev.project.core_data.domain.MenuStatus
import com.ridhoafnidev.project.core_domain.model.Menu
import com.ridhoafnidev.project.core_navigation.ModuleNavigator
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.core_util.dayTimeGreeting
import com.ridhoafnidev.project.feature.home.viewholder.ItemMenuViewHolder
import com.ridhoafnidev.home.R
import com.ridhoafnidev.home.databinding.FragmentHomeBinding
import lt.neworld.spanner.Spanner
import lt.neworld.spanner.Spans.bold
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.ridhoafnidev.project.core_util.setSnapHelper
import com.ridhoafnidev.project.feature.home.viewholder.ItemCurrentEventViewHolder
import com.bumptech.glide.Glide
import com.ridhoafnidev.project.core_domain.model.TipeRumah
import com.ridhoafnidev.project.feature.home.viewholder.ItemPerumahanViewHolder

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
        getEvent()
        setupEvent()
        setupItems()
        setupMenus()
    }

    private fun getEvent() {
        homeViewModel.getEvent()
    }

    private fun setupEvent() {
//        homeViewModel.newEvent.observe(viewLifecycleOwner) { listEvent ->
//            if (listEvent != null) {
//                setupCurrentEvents()
//            }
//        }
        setupRvPerumahan(dummyPerumahan)
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

    private fun setupRvPerumahan(listEvent: List<TipeRumah>) {

        setSnapHelper(recyclerViewPerumahan)

        recyclerViewPerumahan.setup {
            withDataSource(dataSourceOf(listEvent))
            withItem<TipeRumah, ItemPerumahanViewHolder>(R.layout.layout_item_perumahan){
                onBind(::ItemPerumahanViewHolder){_, item ->
//                    Glide.with(requireActivity()).load(item.photo).into(ivPhotoPerumahan)
                    tvNamaTipePerumahan.text = item.namaTipe
                    tvUkuranPerumahan.text = getString(R.string.ukuran, item.ukuran.toString())
                    tvTotalPerumahan.text = getString(R.string.total, item.jumlahUnit.toString())
                    tvHargaPerumahan.text = getString(R.string.harga, item.harga.toString())
                }
                onClick {
                    navigateToDetailPerumahActivity()
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