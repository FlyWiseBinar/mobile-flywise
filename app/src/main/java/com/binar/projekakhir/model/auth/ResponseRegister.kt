package com.binar.projekakhir.model.auth

import com.google.gson.annotations.SerializedName

data class ResponseRegister(
    @field:SerializedName("data")
    val data: Data? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

data class DataRegister(

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("fullName")
    val fullName: String? = null,

    @field:SerializedName("telephone")
    val telephone: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("status")
    val status: Boolean? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)


