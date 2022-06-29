package com.fztkm.card.speedflashcard.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fztkm.card.speedflashcard.database.Quiz
import com.fztkm.card.speedflashcard.databinding.ListItemAddQuizBinding

class AddQuizAdapter : ListAdapter<Quiz, AddQuizAdapter.ViewHolder>(AddQuizDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder private constructor(val binding: ListItemAddQuizBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Quiz) {
            binding.quiz = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAddQuizBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AddQuizDiffCallBack : DiffUtil.ItemCallback<Quiz>() {
    override fun areItemsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
        //TODO compare with ID
        return oldItem.question == newItem.question
    }

    override fun areContentsTheSame(oldItem: Quiz, newItem: Quiz): Boolean {
        return oldItem.question == newItem.question && oldItem.answer == newItem.answer
    }

}