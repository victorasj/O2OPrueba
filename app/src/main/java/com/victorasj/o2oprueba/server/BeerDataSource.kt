package com.victorasj.o2oprueba.server

import com.victorasj.data.source.RemoteDataSource
import com.victorasj.domain.Beer

class BeerDataSource : RemoteDataSource {

    override suspend fun getBeers(): List<Beer> = RetrofitInstance.SERVICE.getBeers("beers")

    override suspend fun getBeersFiltered(value: String?): List<Beer> = RetrofitInstance.SERVICE.getBeersFiltered("beers/", value)

    override suspend fun getBeer(id: Long): Beer = RetrofitInstance.SERVICE.getBeer("beers/$id")
}