package com.ridhoafnidev.project.feature.tipe_rumah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ridhoafnidev.project.feature.tipe_rumah.databinding.FragmentAddTipeRumahBinding

class AddTipeRumahFragment : Fragment() {

    private var _binding: FragmentAddTipeRumahBinding? = null
    private val binding: FragmentAddTipeRumahBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTipeRumahBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.title = getString(R.string.add_tipe_rumah)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}