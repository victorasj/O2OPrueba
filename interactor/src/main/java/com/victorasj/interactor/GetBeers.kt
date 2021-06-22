package com.victorasj.interactor

import com.victorasj.data.repository.BeersRepository
import com.victorasj.domain.Beer

class GetBeers(private val beersRepository: BeersRepository) {
    suspend fun invoke() : List<Beer> = beersRepository.getBeers();
}