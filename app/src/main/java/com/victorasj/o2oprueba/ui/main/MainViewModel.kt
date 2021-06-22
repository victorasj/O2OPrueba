package com.victorasj.o2oprueba.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.victorasj.domain.Beer
import com.victorasj.interactor.GetBeers
import com.victorasj.interactor.GetBeersFiltered
import com.victorasj.o2oprueba.Event
import com.victorasj.o2oprueba.common.ScopedViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val getBeers: GetBeers, private val getBeersFilterd: GetBeersFiltered) : ScopedViewModel() {

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val beer : List<Beer>) : UiModel()
    }

    private val _beers = MutableLiveData<UiModel>()
    val beers : LiveData<UiModel>
    get() {
        if(_beers.value == null) refresh()
        return _beers
    }


    private val _navigateToBeer = MutableLiveData<Event<Beer>>()
    val navigateToBeer: LiveData<Event<Beer>> get() = _navigateToBeer

    private fun refresh() {
        launch {
            _beers.value = UiModel.Loading
            _beers.value = UiModel.Content(getBeers.invoke())
        }
    }

    fun filterBeer(value : String?){
        launch {
            if(value?.isNotEmpty()!!) {
                _beers.value = UiModel.Content(getBeersFilterd.invoke(value))
            } else {
                _beers.value = UiModel.Content(getBeers.invoke())
            }
        }
    }

    fun onBeerClick(beer: Beer) {
        _navigateToBeer.value = Event(beer)
    }

}