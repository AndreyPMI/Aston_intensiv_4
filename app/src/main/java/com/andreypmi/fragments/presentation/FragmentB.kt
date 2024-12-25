package com.andreypmi.fragments.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andreypmi.fragments.R
import com.andreypmi.fragments.databinding.FragmentBBinding

const val FRAGMENT_B_NAME = "fragmentB"

class FragmentB : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonToC.setOnClickListener {
            val text = "Hello Fragment C"
            val bundle = Bundle()
            bundle.putString("KEY_STRING", text)
            val fragmentC = FragmentC()
            fragmentC.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragmentC)
                .addToBackStack(null)
                .commit()
        }

        binding.buttonBackToA.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}