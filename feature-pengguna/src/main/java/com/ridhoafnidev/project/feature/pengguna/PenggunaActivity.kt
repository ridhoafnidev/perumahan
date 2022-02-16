package com.ridhoafnidev.project.feature.pengguna

import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.ridhoafnidev.project.core_domain.model.pengguna.ListPengguna
import com.ridhoafnidev.project.core_domain.model.pengguna.Pengguna
import com.ridhoafnidev.project.core_resource.components.base.BaseActivity
import com.ridhoafnidev.project.core_util.setSystemBarColor
import com.ridhoafnidev.project.feature.pengguna.databinding.ActivityPenggunaBinding
import com.ridhoafnidev.project.feature.pengguna.viewholder.ItemPenggunaViewHolder

class PenggunaActivity : BaseActivity<ActivityPenggunaBinding>(ActivityPenggunaBinding::inflate) {
    override fun initView() {
        setSystemBarColor(R.color.colorBackgroundSecondary)
        initToolbar(back = true, primary = true)
        setPageName(getString(R.string.title_pengguna))

        setRvPengguna(dummyPengguna)
    }

    override fun initListener() {

    }

    private fun setRvPengguna(listPengguna: ListPengguna) {
        binding.rvPengguna.setup {
            withDataSource(dataSourceTypedOf(listPengguna))
            withItem<Pengguna, ItemPenggunaViewHolder>(R.layout.item_pengguna) {
                onBind(::ItemPenggunaViewHolder) { _, item ->
                    tvUsername.text = item.username
                    tvRole.text = item.role
                }
            }
        }
    }

    companion object {
        private val dummyPengguna = listOf(
            Pengguna(1, "direktur", "direktur"),
            Pengguna(2, "pegawai", "pegawai"),
            Pengguna(3, "konsumen", "konsumen"),
            Pengguna(3, "abdulhafizramadan", "konsumen"),
        )
    }
}