package com.andreypmi.fragments.presentation
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andreypmi.fragments.R
import com.andreypmi.fragments.databinding.FragmentCBinding

class FragmentC : Fragment() {

    private var _binding: FragmentCBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val stringFromB = arguments?.getString("KEY_STRING")

        binding.textViewC.text = stringFromB

        binding.buttonToD.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, FragmentD())
                .addToBackStack(null)
                .commit()
        }
        binding.buttonToAReset.setOnClickListener {
            parentFragmentManager.popBackStack(FRAGMENT_B_NAME,1)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}