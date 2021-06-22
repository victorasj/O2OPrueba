package com.victorasj.interactor

import com.victorasj.data.repository.BeersRepository
import com.victorasj.domain.Beer

class GetBeersFiltered(private val beersRepository: BeersRepository) {
    suspend fun invoke(value : String?) : List<Beer> = beersRepository.getBeersFiltered(value);
}