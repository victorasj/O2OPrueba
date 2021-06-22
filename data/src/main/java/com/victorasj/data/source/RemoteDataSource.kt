package com.victorasj.data.source

import com.victorasj.domain.Beer

interface RemoteDataSource {

    suspend fun getBeers() : List<Beer>
    suspend fun getBeersFiltered(value: String?): List<Beer>
    suspend fun getBeer(id : Long) : Beer

}