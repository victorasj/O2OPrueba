package com.victorasj.o2oprueba.ui.beer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victorasj.domain.Beer
import com.victorasj.interactor.GetBeerById
import com.victorasj.o2oprueba.common.ScopedViewModel
import kotlinx.coroutines.launch

class BeerViewModel(private val getBeerById: GetBeerById, private val id : Long) : ScopedViewModel() {

    sealed class UiModel {
        class ContentBeer(val beer : Beer) : UiModel()
    }

    private val _beer = MutableLiveData<UiModel>()
    val beer : LiveData<UiModel>
    get() {
        if(_beer.value == null) loadBeer(id)
        return _beer
    }

    private fun loadBeer(id : Long){
        launch {
            _beer.value = UiModel.ContentBeer(getBeerById.invoke(id))
        }
    }

}