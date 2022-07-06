package com.fztkm.card.speedflashcard.screens.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fztkm.card.speedflashcard.databinding.FragmentQuizFinishBinding

class QuizFinishFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentQuizFinishBinding.inflate(inflater, container, false)
        val viewModel: QuizViewModel by activityViewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.replayButton.setOnClickListener {
            viewModel.onReplay()
            this.findNavController().navigate(
                QuizFinishFragmentDirections.actionQuizFinishFragmentToQuizStartFragment()
            )
        }
        binding.endQuizButton.setOnClickListener {
            activity?.finish()
        }
        return binding.root
    }
}