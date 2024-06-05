package com.example.mcyus

import com.google.firebase.database.Exclude

data class DataBola(


    val deskripsi : String? = null,
    val imgurl : String? = null,
    val nama : String? = null,

    @get:Exclude
    @set:Exclude
    var key:String? = null,


)
