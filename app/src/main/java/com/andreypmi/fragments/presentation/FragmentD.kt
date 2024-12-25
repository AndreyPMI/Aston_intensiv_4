package com.andreypmi.fragments.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andreypmi.fragments.databinding.FragmentDBinding


class FragmentD : Fragment() {

    private var _binding: FragmentDBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonToBReset.setOnClickListener {
        parentFragmentManager.popBackStack(FRAGMENT_B_NAME,0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}