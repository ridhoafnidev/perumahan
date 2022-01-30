package com.ridhoafnidev.project.feature.calonpemilik

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.ridhoafnidev.project.feature.calonpemilik.databinding.FragmentAddCalonPemilikBinding

class AddCalonPemilikFragment : Fragment() {

    private var _binding: FragmentAddCalonPemilikBinding? = null
    private val binding: FragmentAddCalonPemilikBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCalonPemilikBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyPerumahan = resources.getStringArray(R.array.dummy_perumahan)
        setupEdtPerumahan(dummyPerumahan)
    }

    private fun setupEdtPerumahan(listPerumahan: Array<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_perumahan, listPerumahan)
        binding.edtPerumahan.setAdapter(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}