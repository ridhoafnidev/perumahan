package com.ridhoafnidev.project.feature.tipe_rumah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.ridhoafnidev.project.core_navigation.ActionType
import com.ridhoafnidev.project.core_resource.databinding.CompontentFormTambahCalonPemilikBinding
import com.ridhoafnidev.project.feature.tipe_rumah.databinding.FragmentAddTipeRumahBinding

class AddTipeRumahFragment : Fragment() {

    private var _binding: FragmentAddTipeRumahBinding? = null
    private val binding: CompontentFormTambahCalonPemilikBinding
        get() = _binding!!.componentAddCalonPemilik

    private lateinit var actionType: ActionType
    private val args: AddTipeRumahFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTipeRumahBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        actionType = args.actionType

        when (actionType) {
            ActionType.Add -> {

            }
            ActionType.Detail -> {
                disableEditText()
                showActionDetailButtons()
                setupFormTipeRumah()
            }
            ActionType.Edit -> {
                setupFormTipeRumah()
            }
            ActionType.Delete -> {
            }
        }

        setupButtonActions()
    }

    private fun setupButtonActions() {
        binding.apply {
            btnSubmit.setOnClickListener {

            }

        }
    }

    private fun setupFormTipeRumah() {
        binding.apply {
            edtNamaTipe.setText("Wajik")
            edtPondasi.setText("Bata Bakar")
            edtLantai.setText("Granit")
            edtPintuDepan.setText("Kayu Jati")
            edtKusen.setText("Baja")
            edtAtap.setText("Kayu")
            edtListrik.setText("LPTA")
            edtHarga.setText("50000000")
            edtUkuran.setText("20x40")
            edtDinding.setText("Semen")
            edtPlafon.setText("Gypoum")
            edtDindingKamarMandi.setText("Karamik")
            edtRangkaAtap.setText("Baja Ringan")
            edtSanitasi.setText("Air")
            edtAir.setText("Sumur bor")
            edtJumlahUnit.setText("80")
        }
    }

    private fun disableEditText() {
        binding.apply {
            arrayOf(
                edtNamaTipe, edtPondasi, edtLantai,
                edtPintuDepan, edtKusen, edtAtap,
                edtListrik, edtHarga, edtUkuran,
                edtDinding, edtPlafon, edtDindingKamarMandi,
                edtRangkaAtap, edtSanitasi, edtAir, edtJumlahUnit
            ).forEach { editText ->
                editText.isEnabled = false
                editText.alpha = 0.5f
            }
        }
    }

    private fun showActionDetailButtons() {
        binding.apply {
            containerBtnSubmit.visibility = View.GONE
            containerBtnAction.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}