package com.victorasj.o2oprueba.server

import com.victorasj.domain.Beer
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PunkAPI {

    @GET
    suspend fun getBeers(@Url url : String) : List<Beer>

    @GET
    suspend fun getBeersFiltered(@Url url : String, @Query("beer_name") value : String?) : List<Beer>


    @GET
    suspend fun getBeer(@Url url : String) : List<Beer>

}