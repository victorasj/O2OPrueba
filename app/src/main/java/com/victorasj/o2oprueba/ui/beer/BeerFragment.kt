package com.victorasj.o2oprueba.ui.beer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.victorasj.o2oprueba.databinding.BeerFragmentBinding
import loadUrl
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class BeerFragment : ScopeFragment() {

    private lateinit var binding : BeerFragmentBinding

    private val args: BeerFragmentArgs by navArgs()

    private val viewModel: BeerViewModel by viewModel { parametersOf(args.id) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BeerFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.beer.observe(viewLifecycleOwner, Observer (::UpdateUI))
    }

    private fun UpdateUI(model : BeerViewModel.UiModel){
        when(model){
            is BeerViewModel.UiModel.ContentBeer -> {
                binding.beerNameToolbar.title = model.beer.name
                binding.beerInfo.text = model.beer.description
                binding.beerAbv.text = model.beer.abv
                binding.beerImage.loadUrl(model.beer.image_url)
            }
        }
    }
}