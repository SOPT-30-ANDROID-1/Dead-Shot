package com.example.sopt_study

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.sopt_study.databinding.RepositoryListBinding


class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    val userList = mutableListOf<UserData>()
    //질문하기 :: private

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder{
        val binding =
            RepositoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int = userList.size

    class RepositoryViewHolder(
        private val binding: RepositoryListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: UserData) {
            binding.textName.text = data.name
            binding.textIntroduce.text = data.introduction
        }
    }


}