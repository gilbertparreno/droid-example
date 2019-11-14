package com.gp.droid.entities

data class LoginResponse(
    val message: String
) {
    override fun toString() = "LoginResponse: $message"
}