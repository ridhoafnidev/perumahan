package com.ridhoafnidev.project.feature.detail_perumahan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.ridhoafnidev.project.core_resource.databinding.CompontentFormTambahCalonPemilikBinding
import com.ridhoafnidev.project.feature.detail_perumahan.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null
    private val binding: FragmentCheckoutBinding
        get() = _binding!!

    private val dummyPerumahan by lazy {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckoutBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSubmit.setOnClickListener {
                Toast.makeText(requireContext(), "Checkout berhasil", Toast.LENGTH_SHORT).show()
            }
            btnBuktiDp.setOnClickListener {
                val toPreviewDPActivity = CheckoutFragmentDirections
                    .actionCheckoutFragmentToPreviewDPActivity()
                findNavController().navigate(toPreviewDPActivity)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}