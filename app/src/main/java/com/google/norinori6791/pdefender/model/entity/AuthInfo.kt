package com.google.norinori6791.pdefender.model.entity

import java.io.Serializable

data class AuthInfo (
    val _id: Int?,
    val category: Int?,
    val title: String,
    val url: String,
    val uid: String,
    val password: String,
    val memo: String?
): Serializable