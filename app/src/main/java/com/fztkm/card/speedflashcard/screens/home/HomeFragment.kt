package com.fztkm.card.speedflashcard.screens.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fztkm.card.speedflashcard.databinding.FragmentHomeBinding
import com.fztkm.card.speedflashcard.screens.quiz.QuizActivity

class HomeFragment : Fragment() {

    companion object {
        val QUIZ_GROUP_KEY = "quiz_group_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val onClickListener = QuizGroupAdapter.OnClickListener { quizGroup ->
            val intent = Intent(context, QuizActivity::class.java)
            intent.putExtra(QUIZ_GROUP_KEY, quizGroup)
            startActivity(intent)
        }
        val adapter = QuizGroupAdapter(onClickListener)
        binding.quizGroupList.adapter = adapter
        val manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.quizGroupList.layoutManager = manager

        viewModel.quizGroups.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }
}