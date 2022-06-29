package com.fztkm.card.speedflashcard.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fztkm.card.speedflashcard.databinding.AddQuizGroupFragmentBinding

class AddQuizGroupFragment : Fragment() {

    private lateinit var viewModel: AddQuizGroupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = AddQuizGroupFragmentBinding.inflate(layoutInflater, container, false)
        val viewModel = ViewModelProvider(this).get(AddQuizGroupViewModel::class.java)

        val adapter = AddQuizAdapter()
        binding.quizList.adapter = adapter
        val manager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.quizList.layoutManager = manager

        viewModel.quizzes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }

}