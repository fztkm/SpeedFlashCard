package com.fztkm.card.speedflashcard.screens.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fztkm.card.speedflashcard.R

class AddQuizGroupFragment : Fragment() {

    companion object {
        fun newInstance() = AddQuizGroupFragment()
    }

    private lateinit var viewModel: AddQuizGroupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_quiz_group_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddQuizGroupViewModel::class.java)
        // TODO: Use the ViewModel
    }

}