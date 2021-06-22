package com.victorasj.o2oprueba.ui.main

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.victorasj.o2oprueba.EventObserver
import com.victorasj.o2oprueba.R
import com.victorasj.o2oprueba.databinding.MainFragmentBinding
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : ScopeFragment() {

    private lateinit var binding : MainFragmentBinding

    private lateinit var adapter: BeersAdapter
    private lateinit var navController: NavController

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()

        adapter = BeersAdapter(viewModel::onBeerClick)
        binding.recyclerviewBeers.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerviewBeers.adapter = adapter
        viewModel.beers.observe(viewLifecycleOwner, Observer (::updateUi))
        viewModel.navigateToBeer.observe(viewLifecycleOwner, EventObserver {
            val action = MainFragmentDirections.actionMainFragmentToBeerFragment(it.id)
            navController.navigate(action)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchMenu = menu.findItem(R.id.beer_search)
        val searchViewName = searchMenu.actionView as SearchView
        searchViewName.queryHint =  getString(R.string.beer_search)
        searchViewName.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(value: String?): Boolean {
                viewModel.filterBeer(value?.replace(" ", getString(R.string.separator)))
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }



    private fun updateUi(model : MainViewModel.UiModel) {
        binding.progress.visibility = if(model is MainViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when(model) {
            is MainViewModel.UiModel.Content -> adapter.beers = model.beer
        }
    }



}