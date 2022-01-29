package com.ridhoafnidev.project.feature.home

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.afollestad.recyclical.datasource.dataSourceOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.ridhoafnidev.project.core_data.domain.Event
import com.ridhoafnidev.project.core_data.domain.ListEvents
import com.ridhoafnidev.project.core_data.domain.MenuStatus
import com.ridhoafnidev.project.core_domain.model.Menu
import com.ridhoafnidev.project.core_navigation.ModuleNavigator
import com.ridhoafnidev.project.core_resource.components.base.BaseFragment
import com.ridhoafnidev.project.core_util.dayTimeGreeting
import com.ridhoafnidev.project.feature.home.viewholder.ItemMenuViewHolder
import com.ridhoafnidev.project.feature.home.viewholder.ItemNewEventViewHolder
import com.ridhoafnidev.home.R
import com.ridhoafnidev.home.databinding.FragmentHomeBinding
import lt.neworld.spanner.Spanner
import lt.neworld.spanner.Spans.bold
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.ridhoafnidev.project.core_util.setSnapHelper
import com.ridhoafnidev.project.feature.home.viewholder.ItemCurrentEventViewHolder
import com.bumptech.glide.Glide
import com.ridhoafnidev.project.core_util.BaseDateTime
import com.ridhoafnidev.project.core_util.BaseDateTime.Companion.convertTo12H
import com.ridhoafnidev.project.core_util.BaseDateTime.Companion.currentDate
import com.ridhoafnidev.project.core_util.BaseDateTime.Companion.withIndFormat

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

    private val homeViewModel: HomeViewModel by viewModel()

    private val recyclerViewMenus by lazy { binding.rvGridMenu }

    private val recyclerViewNewEvents by lazy { binding.rvNewEvent }

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
        homeViewModel.newEvent.observe(viewLifecycleOwner) { listEvent ->
            if (listEvent != null) {
                setupCurrentEvents(listEvent)
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

    private fun setupCurrentEvents(listEvent: ListEvents) {

        setSnapHelper(recyclerViewNewEvents)

        recyclerViewNewEvents.setup {
            withLayoutManager(LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false))
            withDataSource(dataSourceOf(listEvent))
            withItem<Event, ItemCurrentEventViewHolder>(R.layout.layout_item_current_event){
                onBind(::ItemCurrentEventViewHolder){_, item ->
                    Glide.with(requireActivity()).load(item.image).into(imageEvent)
                    titleEvent.text = item.name
                    dateEvent.text = item.startDate.take(2)
                    mounthEvent.text = item.startDate.trim().slice(3..6)
                    placeEvent.text = Spanner().append(item.location)
                        .append(" - ")
                        .append(item.poweredBy)
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
                        }
                    }
                }
            }
        }
    }

    override fun initListener(){
    }
}