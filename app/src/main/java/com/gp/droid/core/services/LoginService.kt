package com.gp.droid.core.services

import com.gp.droid.entities.LoginBody
import com.gp.droid.entities.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("api/login")
    fun login(@Body loginBody: LoginBody) : Single<LoginResponse>
}