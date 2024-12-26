package com.andreypmi.fragments.presentation

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andreypmi.fragments.R
import com.andreypmi.fragments.databinding.FragmentUserListBinding
import com.andreypmi.fragments.domain.UserModel

class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: UserAdapter

    private val users = mutableListOf(
        UserModel(1, firstName = "Name1", lastName = "Иванов", phoneNumber = "+79991234567"),
        UserModel(2, firstName = "Name2", lastName = "Петров", phoneNumber = "+79997654321"),
        UserModel(3, firstName = "Name3", lastName = "Сидоров", phoneNumber = "+79991234321"),
        UserModel(4, firstName = "Name4", lastName = "Ещеодинов", phoneNumber = "+79994321123")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserAdapter(users) { user ->
            openEditFragment(user)
        }

        binding.usersRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.usersRecyclerView.adapter = adapter
        parentFragmentManager.setFragmentResultListener(USER_EDIT_FRAGMENT_NAME, this) { requestKey, bundle ->
            if (requestKey == USER_EDIT_FRAGMENT_NAME) {
                val editedUser = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    bundle.getParcelable(USER_EDIT, UserModel::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    bundle.getParcelable<UserModel>(USER_EDIT)
                }
                editedUser?.let { user ->
                    updateUser(user)
                }
            }
        }
    }


    private fun openEditFragment(user: UserModel) {
        val bundle = Bundle()
        bundle.putParcelable(USER, user)
        val fragment = UserEditFragment()
        fragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(USER_EDIT)
            .commit()
    }


    private fun updateUser(updatedUser: UserModel) {
        val index = users.indexOfFirst { it.id == updatedUser.id }
        if (index != -1) {
            users[index] = updatedUser
            adapter.notifyItemChanged(index)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}