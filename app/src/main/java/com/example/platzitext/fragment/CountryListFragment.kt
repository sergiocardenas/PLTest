package com.example.platzitext.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.platzitext.R
import com.example.platzitext.compose.CountryListScreen
import com.example.platzitext.viewmodel.CountryListViewModel
import com.example.platzitext.viewmodel.CountrySharedViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CountryListFragment : Fragment() {

    companion object {
        fun newInstance() = CountryListFragment()
    }

    private lateinit var viewModel: CountryListViewModel
    private lateinit var sharedViewModel: CountrySharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CountryListViewModel::class.java]
        sharedViewModel = ViewModelProvider(requireActivity())[CountrySharedViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val composeView = ComposeView(requireContext())
        composeView.setContent {
            CountryListScreen(viewModel){code ->
                goToDetail(code)
            }
        }
        return composeView
    }

    fun goToDetail(code: String){
        if(code.isNotEmpty()){
            sharedViewModel.passCountry(code)
            findNavController().navigate(
                resId = R.id.action_ListFragment_to_DetailFragment
            )
        }else{
            val snackbar = Snackbar
                .make(
                    requireActivity().window.getDecorView().findViewById(android.R.id.content),
                    "Country code not available",
                    Snackbar.LENGTH_LONG)
            snackbar.show()

        }
    }

}