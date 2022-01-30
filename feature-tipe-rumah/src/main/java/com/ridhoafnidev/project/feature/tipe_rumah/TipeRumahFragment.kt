package com.ridhoafnidev.project.feature.tipe_rumah

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.afollestad.recyclical.setup
import com.afollestad.recyclical.withItem
import com.ridhoafnidev.project.core_domain.model.TipeRumah
import com.ridhoafnidev.project.core_util.showAlertDialog
import com.ridhoafnidev.project.feature.tipe_rumah.databinding.FragmentTipeRumahBinding
import kotlinx.parcelize.Parcelize

class TipeRumahFragment : Fragment() {

    private var _binding: FragmentTipeRumahBinding? = null
    private val binding: FragmentTipeRumahBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTipeRumahBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyTipeRumah = arrayListOf<TipeRumah>()

        (1..10).forEach {
            dummyTipeRumah.add(
                TipeRumah("Rumah tipe $it", it+100)
            )
        }

        setupRecyclerTipeRumah(dummyTipeRumah)

        binding.btnTambahTipeRumah.setOnClickListener {
            toFormTipeRumah(TipeRumahAction.Add)
        }
    }

    private fun setupRecyclerTipeRumah(listTipeRumah: List<TipeRumah>) {
        val dataSource = dataSourceTypedOf(listTipeRumah)
        binding.rvTipeRumah.setup {
            withDataSource(dataSource)
            withItem<TipeRumah, TipeRumahViewHolder>(R.layout.item_tipe_rumah) {
                onBind(::TipeRumahViewHolder) { _, item ->
                    tvNamaTipe.text = item.namaTipe
                    tvUkuran.text = item.ukuran.toString()

                    btnEdit.setOnClickListener {
                        toFormTipeRumah(TipeRumahAction.Edit)
                    }
                    btnDelete.setOnClickListener {
                        showAlertDialog(
                            getString(R.string.delete_tipe_rumah_title),
                            null
                        ) {
                            Toast.makeText(
                                context,
                                "Tipe rumah berhasil dihapus!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                onClick {
                    toFormTipeRumah(TipeRumahAction.Detail)
                }
            }
        }
    }

    private fun toFormTipeRumah(actionType: TipeRumahAction) {
        val toAddTipeRumah = TipeRumahFragmentDirections
            .actionTipeRumahFragmentToAddTipeRumahFragment(actionType)
        findNavController().navigate(toAddTipeRumah)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        sealed class TipeRumahAction : Parcelable {
            @Parcelize
            object Add : TipeRumahAction()
            @Parcelize
            object Detail : TipeRumahAction()
            @Parcelize
            object Edit : TipeRumahAction()
            @Parcelize
            object Delete : TipeRumahAction()
        }
    }
}