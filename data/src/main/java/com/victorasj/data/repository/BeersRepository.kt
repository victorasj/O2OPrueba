package com.victorasj.data.repository

import com.victorasj.data.source.RemoteDataSource
import com.victorasj.domain.Beer

class BeersRepository(private val beerDataSource: RemoteDataSource) {

    suspend fun getBeers() : List<Beer> {
        return beerDataSource.getBeers();
    }

    suspend fun getBeersFiltered(value: String?): List<Beer> {
        return beerDataSource.getBeersFiltered(value);
    }

    suspend fun getBeer(id : Long) : Beer {
        return beerDataSource.getBeer(id)
    }

}