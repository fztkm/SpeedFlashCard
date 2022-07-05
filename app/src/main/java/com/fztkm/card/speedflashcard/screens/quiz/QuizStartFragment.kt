package com.fztkm.card.speedflashcard.screens.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.fztkm.card.speedflashcard.databinding.FragmentQuizStartBinding


class QuizStartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentQuizStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizStartBinding.inflate(inflater, container, false)
        val viewModel: QuizViewModel by activityViewModels()
        binding.viewModel = viewModel
        return binding.root
    }
}