package com.example.platzitext.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.findNavController
import com.example.platzitext.R
import com.example.platzitext.compose.CountryListScreen
import com.example.platzitext.viewmodel.CountryListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryListFragment : Fragment() {

    companion object {
        fun newInstance() = CountryListFragment()
    }

    private lateinit var viewModel: CountryListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CountryListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val composeView = ComposeView(requireContext())
        composeView.setContent {
            CountryListScreen(viewModel)
        }
        return composeView
    }

    fun goToDetail(code: String){
        //sharedViewModel.passItem(item)
        findNavController().navigate(
            resId = R.id.action_ListFragment_to_DetailFragment
        )
    }

}