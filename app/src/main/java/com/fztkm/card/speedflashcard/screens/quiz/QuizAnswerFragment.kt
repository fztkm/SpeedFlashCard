package com.fztkm.card.speedflashcard.screens.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.fztkm.card.speedflashcard.databinding.FragmentQuizAnswerBinding


class QuizAnswerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentQuizAnswerBinding.inflate(inflater, container, false)
        val viewModel: QuizViewModel by activityViewModels()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventFinishQuiz.observe(viewLifecycleOwner, Observer {
            if (it) {
                this.findNavController().navigate(
                    QuizAnswerFragmentDirections.actionQuizAnswerFragmentToQuizFinishFragment()
                )
                viewModel.onQuizFinishedCompleted()
            }
        })

        binding.nextText.setOnClickListener {
            this.findNavController().navigate(
                QuizAnswerFragmentDirections.actionQuizAnswerFragmentToQuizFragment()
            )
            viewModel.onNextQuiz()
        }

        binding.retryQuizText.setOnClickListener {
            this.findNavController().navigate(
                QuizAnswerFragmentDirections.actionQuizAnswerFragmentToQuizFragment()
            )
            viewModel.onFailedQuiz()
        }
        return binding.root
    }

}