package com.andreypmi.fragments.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.andreypmi.fragments.R
import com.andreypmi.fragments.databinding.FragmentUserEditBinding
import com.andreypmi.fragments.domain.UserModel

const val USER_EDIT_FRAGMENT_NAME = "userEditResult"
const val USER_EDIT = "editedUser"
const val USER = "user"

class UserEditFragment : Fragment() {

    private var _binding: FragmentUserEditBinding? = null
    private val binding get() = _binding!!
    private lateinit var user: UserModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            user = arguments?.getParcelable(USER,UserModel::class.java) ?: UserModel(0,"", "", "")
        } else {
            @Suppress("DEPRECATION")
            user = arguments?.getParcelable(USER) ?: UserModel(0,"", "", "")
        }
        binding.firstNameEditText.setText(user.firstName)
        binding.lastNameEditText.setText(user.lastName)
        binding.phoneNumberEditText.setText(user.phoneNumber)

        binding.saveUserButton.setOnClickListener {
            saveUser()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveUser() {
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val phoneNumber = binding.phoneNumberEditText.text.toString()

        if (firstName.isBlank() || lastName.isBlank() || phoneNumber.isBlank()){
            Toast.makeText(requireContext(),
                getString(R.string.errorTostForUserEdit), Toast.LENGTH_SHORT).show()
            return
        }

        user.firstName = firstName
        user.lastName = lastName
        user.phoneNumber = phoneNumber

        val bundle = Bundle()
        bundle.putParcelable(USER_EDIT, user)

        parentFragmentManager.setFragmentResult(USER_EDIT_FRAGMENT_NAME, bundle)

        parentFragmentManager.popBackStack()
    }
}