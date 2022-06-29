package com.fztkm.card.speedflashcard.screens.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fztkm.card.speedflashcard.database.QuizGroup
import com.fztkm.card.speedflashcard.databinding.ListItemQuizTitleBinding

class QuizGroupAdapter :
    ListAdapter<QuizGroup, QuizGroupAdapter.ViewHolder>(QuizGroupDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    
    class ViewHolder private constructor(val binding: ListItemQuizTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: QuizGroup) {
            binding.quizGroup = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemQuizTitleBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class QuizGroupDiffCallback : DiffUtil.ItemCallback<QuizGroup>() {
    override fun areItemsTheSame(oldItem: QuizGroup, newItem: QuizGroup): Boolean {
        //TODO: compare with id
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: QuizGroup, newItem: QuizGroup): Boolean {
        return oldItem.name == newItem.name
    }
}