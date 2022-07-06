package com.fztkm.card.speedflashcard.screens.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.fztkm.card.speedflashcard.databinding.FragmentQuizBinding


class QuizFragment : Fragment() {
    lateinit var binding: FragmentQuizBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentQuizBinding.inflate(inflater, container, false)
        val viewModel: QuizViewModel by activityViewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.nextText.setOnClickListener {
            viewModel.showNextQuiz()
        }
        
        binding.checkAnswerText.setOnClickListener {
            this.findNavController().navigate(
                QuizFragmentDirections.actionQuizFragmentToQuizAnswerFragment()
            )
        }

        return binding.root
    }
}