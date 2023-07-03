package com.example.platzitext.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.example.platzitext.compose.CountryDetailScreen
import com.example.platzitext.viewmodel.CountrySharedViewModel
import com.example.platzitext.viewmodel.CountryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

    companion object {
        fun newInstance() = CountryDetailFragment()
    }

    private lateinit var viewModel: CountryDetailViewModel
    private lateinit var sharedViewModel: CountrySharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CountryDetailViewModel::class.java]
        sharedViewModel = ViewModelProvider(requireActivity())[CountrySharedViewModel::class.java]
        sharedViewModel.countryCode.value?.let {
            viewModel.searchCountry(it)
        }
        sharedViewModel.clearState()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext())
        composeView.setContent {
            CountryDetailScreen(viewModel)
        }
        return composeView
    }


}