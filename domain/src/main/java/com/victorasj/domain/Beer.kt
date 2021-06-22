package com.victorasj.domain

data class Beer(
    val id : Long,
    val name : String,
    val description: String,
    val image_url : String,
    val abv : String
)