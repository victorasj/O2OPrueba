package com.victorasj.interactor

import com.victorasj.data.repository.BeersRepository
import com.victorasj.domain.Beer

class GetBeerById(private val beersRepository: BeersRepository) {
    suspend fun invoke(id : Long) : Beer = beersRepository.getBeer(id);
}