package com.andreypmi.fragments.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreypmi.fragments.databinding.ItemUserBinding
import com.andreypmi.fragments.domain.UserModel

class UserAdapter(private val users: List<UserModel>, private val onItemClick: (UserModel) -> Unit) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.binding.userFullNameTextView.text = "${user.firstName} ${user.lastName}"
        holder.binding.userPhoneNumberTextView.text = user.phoneNumber
        holder.itemView.setOnClickListener {
            onItemClick(user)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}